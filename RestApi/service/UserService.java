package com.example.Chat.service;

import com.example.Chat.Models.User;
import com.example.Chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        List<User> users = repository.findAll();
        if (users.size() > 0) {
            return users;
        } else {
            return new ArrayList<>();
        }
    }

    public User createOrUpdate(User user){
        user.setId(UUID.randomUUID());
        user = repository.save(user);
        return  user;
    }
}
