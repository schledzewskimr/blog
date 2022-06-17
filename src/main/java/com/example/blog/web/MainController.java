package com.example.blog.web;

import javax.validation.Valid;

import com.example.blog.web.dto.UserRegistrationDto;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.blog.services.NotiGangServ;
//import com.example.blog.Forms.UserLogin;
import com.example.blog.services.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotiGangServ notifyServ;

    @Autowired
    private PostService postService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }

}