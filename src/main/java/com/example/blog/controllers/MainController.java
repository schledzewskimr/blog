package com.example.blog.controllers;

import javax.validation.Valid;

import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.blog.services.NotiGangServ;
import com.example.blog.Forms.UserLogin;
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

    @GetMapping("users/login")
    public String login() {
        return "users/login";
    }
//    @GetMapping("users/registration")
//    public String register(){
//        return "users/registration";
//    }

//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }


    @RequestMapping( value= { "/", "/index" } )
    public String index(Model model){
        // Get last 5 post
        List<Post> latest5Posts = this.postService.findLatest5();
        // Send results to view model
        model.addAttribute("latest5Posts", latest5Posts);
        // From the 5-posts list, get another and limit it to 3
        List<Post> latest3Posts = latest5Posts.stream().limit(3).collect(Collectors.toList());
        // Send to view model
        model.addAttribute("latest3Posts", latest3Posts);
        //return "home/index"; // To have something like src/main/resources/templates/<CONTROLLER-NAME>/<Mapping-Name-index>
        return "index"; // "Normal use"
    }

    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public String loginPage(@Valid UserLogin userLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notifyServ.addErrorMessage("Error!  Error!  Please fill out form correctly!");
            return "users/login";
        }
        if (!userService.authenticate(
            userLogin.getUsername(), userLogin.getPassword())) {
                notifyServ.addErrorMessage("Invalid login!");
                return "users/login";
            }

        notifyServ.addInfoMessage("Successful login!");
        return "redirect:/";
    }

    @RequestMapping("/users/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject(user);
        modelAndView.setViewName("users/registration");
        return modelAndView;
    }
    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public ModelAndView registration(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = this.userService.findByUserName(user.getUserName());
        modelAndView.setViewName("users/registration");
        if( userExists != null ){
            bindingResult.rejectValue("userName", "error.user", "User exists");
        }
        if( !bindingResult.hasErrors() ){
            this.userService.create(user);
            modelAndView.addObject("successMessage", "User has been created");
            modelAndView.addObject("user", new User());
        }
        return modelAndView;

    }

    @RequestMapping("/users")
    public String index(Model model, @PageableDefault(sort = {"userName"}, value = 5) Pageable pageable){
        // Get the content of the table, TODO. find a way to paginate
        Page<User> users = this.userService.findAll(pageable);

        // Define variables to be passed to view
        model.addAttribute("users", users);
        // Return the view model itself
        return "users/index";
    }

}