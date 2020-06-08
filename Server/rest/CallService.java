package com.example.demo.rest;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CallService {
    RestTemplate restTemplate = new RestTemplate();

    public List<User> getUsers() {

        User[] users = restTemplate.getForObject("http://localhost:8081/user", User[].class);
        List<User> userList = new ArrayList<>();

        for (User user : users) {
            userList.add(user);
        }
        return userList;
    }

    public User addUser(String name, String password    ) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        User user1 = restTemplate.postForObject("http://localhost:8081/user", user, User.class);
        return user1;
    }

    public List<Message> getMessage(UUID id1, UUID id2) {
        Message[] messages = restTemplate.getForObject("http://localhost:8081/message", Message[].class);
        List<Message> messageList = new ArrayList<>();
        for (Message message : messages) {

            if ((message.getSender().equals(id1) && message.getReceiver().equals(id2))
               ||message.getSender().equals(id2) && message.getReceiver().equals(id1)){

                messageList.add(message);
            }
        }

        return messageList;
    }

    public Message addMessage(Message message) {
        Message message1 = restTemplate.postForObject("http://localhost:8081/message",message, Message.class);
        return message1;
    }


}
