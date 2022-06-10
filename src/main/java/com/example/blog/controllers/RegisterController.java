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

    private UserService userService;

    public RegisterController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public WebMvcConfig userRegistrationDto() {
        return new WebMvcConfig();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userObj = userService.findByUserName(user.getUserName());
        if(userObj != null){
            bindingResult.rejectValue("email", "error.user", "This email id is already registered.");
        }
        modelAndView.setViewName("register");
        if(bindingResult.hasErrors()){
            return modelAndView;
        }else{
            userService.create(user);
            modelAndView.addObject("user",new User());
            modelAndView.addObject("successMessage", "User registered successfully");
        }
        return modelAndView;
    }
}

