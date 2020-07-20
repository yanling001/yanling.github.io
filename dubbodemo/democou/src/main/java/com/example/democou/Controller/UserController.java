package com.example.democou.Controller;

import UserService.UserServce;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Reference
    UserServce userServce;
    @RequestMapping("/test")
    public  String get(){
        return userServce.getUserName();
    }
}
