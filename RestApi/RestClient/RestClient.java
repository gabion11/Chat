package com.example.Chat.RestClient;

import com.example.Chat.Models.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class RestClient {
    public void getUsers(){
        RestTemplate restTemplate = new RestTemplate();
        List<User> userList= (List<User>) restTemplate.getForEntity("http://localhost:8081/user",User.class);
        System.out.println(userList);

    }
}
