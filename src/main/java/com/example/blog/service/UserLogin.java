package com.example.blog.service;

import javax.validation.constraints.Size;

public class UserLogin {

    @Size(min=3, max=32, message="Username should be between 3 and 32 characters in length.")
    private String username;

    @Size(min=6, max=64, message="Password should be between 6 and 64 characters in length.")
    private String password;

    public UserLogin() {

    }
    
    public UserLogin(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}