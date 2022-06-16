package com.example.blog.web;

import com.example.blog.models.User;
import com.example.blog.services.UserService;
import com.example.blog.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {

        User existing = userService.findByEmail(registrationDto.getEmail());
        if(existing!=null){
            result.rejectValue("email", null,
                    "This email already exists in the system");
        }
        System.out.print("result.hasErrors():"+result.hasErrors());
        if(result.hasErrors()){
            return "redirect:/registration?error";
        }

        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
