package com.example.blog.services;

import com.example.blog.models.User;
import com.example.blog.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    void save(UserRegistrationDto registrationDto);
}