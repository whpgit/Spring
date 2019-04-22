package cn.smbms.controller;

import cn.smbms.annotation.EnjoyAutowired;
import cn.smbms.annotation.EnjoyController;
import cn.smbms.annotation.EnjoyRequestMapping;
import cn.smbms.annotation.EnjoyRequestParam;
import cn.smbms.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnjoyController
@EnjoyRequestMapping("/user")
public class UserController {

    //    @Autowired("UserServiceImpl")
    @EnjoyAutowired("UserServcieImpl")
    private UserServiceImpl userService;

    @EnjoyRequestMapping(value = "/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @EnjoyRequestParam("username") String username,
                      @EnjoyRequestParam("age") String age) {

        try {
            PrintWriter pw = response.getWriter();
            String result = userService.getUser(username, age);
            pw.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
