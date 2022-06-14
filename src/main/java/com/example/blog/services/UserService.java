package com.example.blog.services;

import com.example.blog.dto.UserRegistrationDto;
import com.example.blog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.awt.print.Pageable;
import java.util.List;

public interface UserService extends UserDetailsService {
    boolean authenticate(String userName, String password);

    List<User> findAll();
    Page<User>findAll(Pageable pageable);
    User findByUserName(String userName);
    User findById(Long id);
    User create(User user);
    User save(UserRegistrationDto registrationDto);
    User edit(User user);
    void deleteById(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
