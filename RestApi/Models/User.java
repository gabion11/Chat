package com.example.Chat.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    String username;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String password;

    public String getPassword() {
        System.out.println(password);
    return password;
}

    public void setPassword(String password) {
        this.password = hashPassword(password);
        System.out.println(password);
    }
    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

}
