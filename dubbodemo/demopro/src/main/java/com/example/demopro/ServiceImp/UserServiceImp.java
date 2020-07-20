package com.example.demopro.ServiceImp;

import UserService.UserServce;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class UserServiceImp implements UserServce {
    @Override
    public String getUserName() {
        return "zhang";
    }
}
