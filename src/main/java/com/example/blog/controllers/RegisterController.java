package com.example.blog.controllers;

import com.example.blog.services.UserService;
import com.example.blog.models.User;
import com.example.blog.configuration.WebMvcConfig;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @GetMapping("users/registration")
    public String register(){
        return "users/registration";
    }
}

