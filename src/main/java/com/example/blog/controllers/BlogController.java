package com.example.blog.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.models.Blog;
import com.example.blog.services.BlogService;

@Controller
public class BlogController {
    
    @Autowired
	private BlogService blogService;
	
	// display list of blogs
	@GetMapping("post/index")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewBlog")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Blog employee = new Blog();
		model.addAttribute("employee", employee);
		return "new_blog";
	}
	
	@PostMapping("/saveBlog")
	public String saveEmployee(@ModelAttribute("employee") Blog employee) {
		// save employee to database
		blogService.saveBlog(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get blog from the service
		Blog blog = blogService.getBlogById(id);
		
		// set employee as a model attribute to pre-populate the form with blog
		model.addAttribute("employee", blog);
		return "update_blog";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		// call delete blog method 
		this.blogService.deleteBlogById(id);
		return "redirect:/";
	}
	
	//sort
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		//set amount of post on page
		int pageSize = 10;
		
		Page<Blog> page = blogService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Blog> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
