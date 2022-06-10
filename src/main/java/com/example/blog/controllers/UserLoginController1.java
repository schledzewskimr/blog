package com.example.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog.services.NotiGangServ;
import com.example.blog.services.UserLogin;
import com.example.blog.services.UserService;

@Controller
public class UserLoginController1 {

    @Autowired
<<<<<<< HEAD
    private UserService userService;
    
    @GetMapping("users/login")
=======
    private UserService1 userService;

    @Autowired
    private NotiGangServ notifyServ;

    @GetMapping("/login")
>>>>>>> 45a34b7af04ac099501ac3d9afa2bb985f4768dc
    public String login() {

        return "/login";
    }

<<<<<<< HEAD

=======
    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String loginPage(@Valid UserLogin userLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyServ.addErrorMessage("Error!  Error!  Please fill out form correctly!");
            return "user/login";
        }
        if (!userService.authenticate(
            userLogin.getUsername(), userLogin.getPassword())) {
                notifyServ.addErrorMessage("Invalid login!");
                return "user/login";
            }

        notifyServ.addInfoMessage("Successful login!");
        return "redirect:/";
    }
>>>>>>> 45a34b7af04ac099501ac3d9afa2bb985f4768dc
}