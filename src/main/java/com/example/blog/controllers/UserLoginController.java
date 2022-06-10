package com.example.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog.services.UserLogin;
import com.example.blog.services.UserService1;

@Controller
public class UserLoginController {

    @Autowired
    private UserService1 userService;

    @GetMapping("users/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("users/registration")
    public String register(){
        return "users/registration";
    }

    // @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    // public String loginPage(@Valid UserLogin userLogin, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
            
    //     }
    // }
}