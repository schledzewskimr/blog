package com.example.blog.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.blog.models.Blog;

public interface BlogService {
    List<Blog> getAllBlogs();
    void saveBlog(Blog blog);
    Blog getBlogById(long id);
	void deleteBlogById(long id);
	Page<Blog> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

