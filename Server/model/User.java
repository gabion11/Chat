package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;
public class User {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    String password;

    public String getPassword() {
        System.out.println("From db"+password);

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }



    public boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }
}
