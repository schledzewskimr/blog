package com.example.blog.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

import com.example.blog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {


    @Override
    public boolean authenticate(String userName, String password) {
        return Objects.equals(userName, password);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User edit(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
