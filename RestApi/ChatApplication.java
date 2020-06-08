package com.example.Chat;

import com.example.Chat.RestClient.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	/*	RestClient restClient = new RestClient();
		restClient.getUsers();*/
	}

}
