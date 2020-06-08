package com.example.Chat.Controller;

import com.example.Chat.Models.Message;
import com.example.Chat.Models.User;
import com.example.Chat.repository.MessageRepository;
import com.example.Chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService services;

    @GetMapping
    public ResponseEntity<List<Message>> getMessages(){
        List<Message> messages = services.getMessages();
        return new ResponseEntity<List<Message>>(messages, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        Message message1 = services.addMessage(message);
        return  new ResponseEntity<Message>(message1, new HttpHeaders(),HttpStatus.CREATED);
    }
}
