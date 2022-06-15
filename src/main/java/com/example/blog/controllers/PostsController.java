package com.example.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.services.NotiGangServ;
import com.example.blog.services.PostService;
import com.example.blog.services.UserService;

import java.util.Optional;


@Controller
public class PostsController {
    /**
     * With the annotation @Service for the service implementation, it tells the Spring Framework that class will used by the application
     * controller as a service and it will be automatically instantiated and injected in the controllers(through the @Autowired annotation).
     */
    @Autowired
    private PostService postService;


    @RequestMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model){
        Optional<Post> optionalPost = Optional.ofNullable(this.postService.getPostById(id));
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post";
        }
        else{
            return "404";
        }
    }

    @GetMapping("/showNewPostForm")
    public String showNewPostForm(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "posts/create";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute("post") Post post){
        postService.savePost(post);
        return "redirect:/posts/posts";
    }

    @GetMapping("/showPostForUpdate/{id}")
    public String showPostForUpdate(@PathVariable(value = "id") long id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts/updatePost";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") Long id){
        this.postService.deletePostById(id);
        return "redirect:/posts/posts";
    }


}
