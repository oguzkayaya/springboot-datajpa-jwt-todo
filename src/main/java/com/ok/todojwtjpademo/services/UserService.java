package com.ok.todojwtjpademo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ok.todojwtjpademo.models.User;
import com.ok.todojwtjpademo.repositories.UserRepository;
import com.ok.todojwtjpademo.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUserByName(String username) {
        return userRepository.findByUsername2(username);
    }


}
