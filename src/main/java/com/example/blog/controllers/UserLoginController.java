package com.example.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog.services.UserLogin;
import com.example.blog.services.UserService;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String login() {

        return "login";
    }


}