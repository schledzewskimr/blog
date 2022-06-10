package com.example.blog.service;

import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean authenticate(String username, String password) {
        
        return Objects.equals(username, password);
    }
    
}