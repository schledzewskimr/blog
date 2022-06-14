package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog.models.Blog;
import com.example.blog.repositories.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
	private BlogRepository blogRepository;

	@Override
	public List<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public void saveBlog(Blog blog) {
		this.blogRepository.save(blog);
	}

	@Override
	public Blog getBlogById(long id) {
		Optional<Blog> optional = blogRepository.findById(id);
		Blog person = null;
		if (optional.isPresent()) {
			person = optional.get();
		} else {
			throw new RuntimeException(" not found for id :: " + id);
		}
		return person;
	}

	@Override
	public void deleteBlogById(long id) {
		this.blogRepository.deleteById(id);
	}

	@Override
	public Page<Blog> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.blogRepository.findAll(pageable);
	}
    
}
