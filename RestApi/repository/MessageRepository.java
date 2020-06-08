package com.example.Chat.repository;

import com.example.Chat.Models.Message;
import com.example.Chat.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message,UUID> {

}