package com.example.blogproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.blogproj.service.UserService;
import com.example.blogproj.dto.BlogDTO;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public BlogDTO blogDTO() {
        return new BlogDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }
    
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") BlogDTO registrationDTO) {
        userService.save(registrationDTO);
        return "redirect:/registration?success";
    } 
}