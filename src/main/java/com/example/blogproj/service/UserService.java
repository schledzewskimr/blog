package com.example.blogproj.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.blogproj.model.User;
import com.example.blogproj.dto.BlogDTO;

public interface UserService extends UserDetailsService {
    User save(BlogDTO registrationDTO);
}