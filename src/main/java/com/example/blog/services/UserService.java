package com.example.blog.services;

import com.example.blog.models.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService {
    boolean authenticate(String userName, String password);

    List<User> findAll();
    Page<User>findAll(Pageable pageable);
    User findByUserName(String userName);
    User findById(Long id);
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
}
