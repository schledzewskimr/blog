package com.example.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog.service.UserLogin;
import com.example.blog.service.UserService;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/user/login")
    public String login(UserLogin userLogin) {
        return "user/login";
    }

    // @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    // public String loginPage(@Valid UserLogin userLogin, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
            
    //     }
    // }
}