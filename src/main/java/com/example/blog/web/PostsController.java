package com.example.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.blog.models.Post;
import com.example.blog.services.PostService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class PostsController {
    /**
     * With the annotation @Service for the service implementation, it tells the Spring Framework that class will used by the application
     * controller as a service and it will be automatically instantiated and injected in the controllers(through the @Autowired annotation).
     */
    @Autowired
    private PostService postService;

    public PostsController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
    }
    @GetMapping("/posts")
    public String displayAllPosts(Model model){
        Collection<Post> posts = this.postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }
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

    @GetMapping("/create")
    public String showNewPostForm(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "create";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute("post") Post post){
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/updatePost/{id}")
    public String showPostForUpdate(@PathVariable(value = "id") long id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "/updatePost";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") Long id){
        this.postService.deletePostById(id);
        return "redirect:/posts";
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        //set amount of post on page
//        int pageSize = 10;
//
//        Page<Post> page = postService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Post> listUsers = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listUsers", listUsers);
//        return "index";
//    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                Model model) {
        int pageSize = 5;
        Page<Post> page = postService.findPaginated(pageNo, pageSize);
        List<Post> listPosts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("listPosts", listPosts);
// Get last 5 post
        List<Post> latest5Posts = this.postService.findLatest5();
        model.addAttribute("latest5Posts", latest5Posts);
        List<Post> latest3Posts = latest5Posts.stream().limit(3).collect(Collectors.toList());
        model.addAttribute("latest3Posts", latest3Posts);
        return "index";
    }

}
