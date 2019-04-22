package cn.smbms.servlet;

import cn.smbms.annotation.*;
import cn.smbms.controller.UserController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet(name = "DispacherServlet")
public class DispacherServlet extends HttpServlet {

    List<String> classNames = new ArrayList<String>();   //不是容器
    Map<String, Object> beans = new HashMap<String, Object>();   //IOC容器
    Map<String, Object> handlerMap = new HashMap<String, Object>();   //存储路径和方法的匹配


    //init 要做什么？？？

    /**
     * 1.tomcat启动，  init： 扫描@Controller  @Service
     * 2.通过反射实例
     * 3.处理Autowired
     * 4.path ---method   路径映射
     * //记得配置web.xml
     *
     * @param config
     * @throws ServletException
     */

    //tomcat启动执行init()方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        scanPackage("cn.smbms");  //1
        instance();          //2
        doAutowired();    //3
        urlHandlingMapper();   //4
    }

    private void urlHandlingMapper() {
        //我们还需要将beans遍历，判断它有没有声明@Controller,  而且只要控制类有路径，service没有
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object instance = entry.getValue();

            Class<?> clazz = instance.getClass();
            if (clazz.isAnnotationPresent(EnjoyController.class)) {  //
                //拿路径
                //拿类上面的路径
                EnjoyRequestMapping map1 = clazz.getAnnotation(EnjoyRequestMapping.class);
                String classpath = map1.value();   //    /user
                //再拿方法的路径    但是不是所有的方法都加了@RequestMapping
                Method[] methods = clazz.getMethods();   //拿到所有的方法
                for (Method method : methods) {
                    //判断方法上面有没有声明@RequestMapping注解
                    if (method.isAnnotationPresent(EnjoyRequestMapping.class)) {
                        EnjoyRequestMapping map2 = method.getAnnotation(EnjoyRequestMapping.class);
                        String methodpath = map2.value();
                        //接下来路径就为  classpath + methodpath   /user/query   然后存储
                        handlerMap.put(classpath + methodpath, method);

                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }

    }

    private void doAutowired() {
        //对bean对行遍历，目前只处理UserController
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object instance = entry.getValue();  //获取容器的bean
            System.out.println("doAutowired    -->instance:" + instance);
            Class<?> clazz = instance.getClass();  //反射实例bean
            if (clazz.isAnnotationPresent(EnjoyController.class)) {  //如果有dao层加个if就好了
                Field[] fields = clazz.getDeclaredFields();//我们只需要找出声明@Autowired注解的变量
                for (Field field : fields) {//这个是在变量属性上判断有没有该注解
                    if (field.isAnnotationPresent(EnjoyAutowired.class)) {
                        //拿到@Autowired里面的值
                        EnjoyAutowired ea = field.getAnnotation(EnjoyAutowired.class);
                        String key = ea.value();  //拿到key    ==  UserServiceImpl
                        Object ins = beans.get(key);  //再从容器中通过key拿到value
                        // 再摄值到 private UserServiceImpl userService;  因为他是私有的，没有权限，所以要让他打开权限
                        field.setAccessible(true);  //打开权限了
                        try {
                            System.out.println("doAutowired    -->instance:" + instance);
                            field.set(instance, ins);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }

    }

    private void instance() {
        //将找到的类遍历出来
        for (String className : classNames) {
            //cn.smbms.xxx.xxx.class
            //接下来砍掉  .class
            String cn = className.replace(".class", "");
            try {
                Class<?> clazz = Class.forName(cn);  //拿到class对象   clazz是个很大的对象
                if (clazz.isAnnotationPresent(EnjoyController.class)) { //有没有Controller注解

                    Object instance = clazz.newInstance();  //通过反射创建实例

                    EnjoyRequestMapping map1 = clazz.getAnnotation(EnjoyRequestMapping.class);
                    String key = map1.value();
                    beans.put(key, instance);  //spring默认将类前面大写字母变为小写，，今天我们就要用自己的作为key
                    //也就是将@EnjoyRequestMapping("/user")的值作为key

                } else if (clazz.isAnnotationPresent(EnjoyService.class)) { //有没有Controller注解
                    //服务类
                    Object instance1 = clazz.newInstance();  //通过反射创建实例
                    //并且拿到servie上面的注解里面的值作为key
                    EnjoyService map2 = clazz.getAnnotation(EnjoyService.class);
                    String key = map2.value();
                    beans.put(key, instance1);
                    //也就是将@EnjoyService("UserServcieImpl")的值作为key

                } else {
                    continue;  //spring底层是这样做的
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    private void scanPackage(String basePackage) {
        //获取类加载器
        //cn.smbms.....   变成   cn/smbms/*/**      url = E:/workplace/Spring/
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        System.out.println("输出init()方法拿到的url" + url);
        String filestr = url.getFile();
        //用文件的方式来查找
        File file = new File(filestr);
        //过滤
        String[] filesStr = file.list();
        for (String path : filesStr) { //path是我们的路径名
            File filePath = new File(filestr + path); //
            if (filePath.isDirectory()) {  //是文件夹
                //递归
                scanPackage(basePackage + "." + path);
            } else {    //进来则可以认为他就是Java类
                //cn.smbms.xxx.xxx.class
                classNames.add(basePackage + "." + filePath.getName());// 加 . 是因为待会我门要反射，Java只认识  .
            }
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当tomcat的请求过来        HTTP  ----拿到路径     http://ip+port/工程名/user/query
        String uri = request.getRequestURI();   //  拿到的是工程名加请求路径的 ,因为我们就要拿/user/query
        System.out.println(uri);
        String context = request.getContextPath();    //拿到工程名
        System.out.println(context);
/*        String s = request.getSession().getServletContext().getRealPath("statics"+File.pathSeparator+"a");
        System.out.println("输出s的路径："+s);*/
        String path = uri.replace(context, "");    //  工程名/user/query  -->   /user/query     --method
        //path  也就是key拿到了  ，
        Method method = (Method) handlerMap.get(path);   //拿到准备调用的方法
        //拿到UserController实例
        UserController instance = (UserController) beans.get("/" + path.split("/")[1]);

        Object args[] = hand(request, response, method);

        try {
            method.invoke(instance, args);   //第一个参数：method属于哪个实例    第二个为方法参数：下面的hand方法
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    // method.invoke(instance,);  里的第二个参数    这里直接用if模式写完的
    private static Object[] hand(HttpServletRequest request, HttpServletResponse response, Method method) {
        //拿到当前待执行
        Class<?>[] paramClazzs = method.getParameterTypes();
        //根据参数的个数， new一个参数的数组，将方法里的所有参数赋值到args来
        Object[] args = new Object[paramClazzs.length];

        int args_i = 0;
        int index = 0;
        for (Class<?> paramClass : paramClazzs) {   //把参数体全部拿到  method来拿
            if (ServletRequest.class.isAssignableFrom(paramClass)) {
                args[args_i++] = request;   //拿到后进行遍历叠加
            }
            if (ServletResponse.class.isAssignableFrom(paramClass)) {
                args[args_i++] = response;
            }
            //从0-3判断有没有@RequestParam注解，很明显paramClazz为0和1时，不是，
            //当为2和3时，为@RequestParam注解，需要解析
            //[ @cn.smbms.annotation.EnjoyRequestParam(value=name)]
            Annotation[] paramAns = method.getParameterAnnotations()[index];
            if (paramAns.length > 0) {
                for (Annotation paramAn : paramAns) {
                    if (EnjoyRequestParam.class.isAssignableFrom(paramAn.getClass())) {
                        EnjoyRequestParam ep = (EnjoyRequestParam) paramAn;
                        //找到注解里的username 和 age
                        args[args_i++] = request.getParameter(ep.value());
                    }
                }
            }
            index++;
        }
        return args;

    }
}
