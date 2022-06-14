package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.models.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    
}
