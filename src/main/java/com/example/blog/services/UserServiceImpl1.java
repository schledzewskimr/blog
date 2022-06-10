package com.example.blog.services;

import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserServiceImpl1 implements UserService1 {

    @Override
    public boolean authenticate(String username, String password) {
        
        return Objects.equals(username, password);
    }
    
}