package com.example.blog.services;

public interface UserService {
    boolean authenticate(String username, String password);
}