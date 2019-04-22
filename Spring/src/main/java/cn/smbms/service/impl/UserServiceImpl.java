package cn.smbms.service.impl;

import cn.smbms.annotation.EnjoyService;
import cn.smbms.service.UserService;

/**
 * tomcat启动过程中： @EnjoyService
 * UserServiceImpl  UserServiceImpl ;    IOC容器  --》  map.put("userServiceImpl",userServiceImpl);
 * 默认是将  UserServiceImpl ---》小写的u
 * 我们变为UserServcieImpl作为key    注意和Controller 的注解value一致
 */


@EnjoyService("UserServcieImpl")
public class UserServiceImpl implements UserService {
    @Override
    public String getUser(String username, String age) {
        return "{username=" + username + ",age=" + age + "}";
    }
}
