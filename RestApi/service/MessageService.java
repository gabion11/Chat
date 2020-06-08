package com.example.Chat.service;

import com.example.Chat.Models.Message;
import com.example.Chat.Models.User;
import com.example.Chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {


    @Autowired
    private MessageRepository repository;

    public Message addMessage(Message message){
            message.setId(UUID.randomUUID());
            message.setDate(new Date());
            message = repository.save(message);
            return message;
    }

    public List<Message> getMessages(){
        List<Message> messages =repository.findAll();
        if(messages.size()>0)
            return messages;
        else return new ArrayList<>();
    }

}
