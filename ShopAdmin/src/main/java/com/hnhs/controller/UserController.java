package com.hnhs.controller;

import com.hnhs.info.LoginInfo;
import com.hnhs.info.RegisterInfo;
import com.hnhs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public Integer register(@RequestBody RegisterInfo registerInfo){
        return userService.register(registerInfo);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginInfo loginInfo){
        return userService.login(loginInfo);
    }
}
