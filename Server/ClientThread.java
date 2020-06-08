package com.example.demo;

import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.rest.CallService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

public class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            User curentUser = null;
            CallService callService = new CallService();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request;
            String raspuns = "";
            while (true) {

                request = in.readLine();

                if (request.contains("Login")) {

                    String username = in.readLine();
                    String password = in.readLine();

                    List<User> userList = callService.getUsers();
                    boolean ok = false;
                    for (User user : userList) {
                        if (user.getUsername().equals(username)){
                            System.out.println(user.checkPass(password,user.getPassword()));
                            System.out.println("Here"+user.getPassword());
                        if (user.getUsername().equals(username)&&user.checkPass(password,user.getPassword())) {
                            ok = true;
                            curentUser = user;
                        }}
                    }
                    if (ok) {
                        raspuns = "OK";
                    } else {
                        raspuns = "BAD";
                    }
                    out.println(raspuns);
                    out.flush();
                } else if (request.contains("SignIn")) {
                    String username = in.readLine();
                    String password = in.readLine();

                    List<User> userList = callService.getUsers();
                    boolean ok = false;
                    for (User user : userList) {
                        if (user.getUsername().equals(username)) {
                            ok = true;
                        }
                    }
                    if (ok) {
                        raspuns = "BAD";
                    } else {
                        curentUser = callService.addUser(username,password);
                        raspuns = "OK";
                    }
                    out.println(raspuns);
                    out.flush();
                } else if (request.contains("GetUsers")) {
                    List<User> userList = callService.getUsers();
                    out.println(userList.size());
                    out.flush();
                    for (User user : userList) {
                        out.println(user.getUsername());
                        out.flush();
                    }

                } else if (request.contains("AddMessage")) {
                    String username = in.readLine();
                    String content = in.readLine();
                    Message message = new Message();
                    List<User> userList = callService.getUsers();
                    for (User user : userList) {
                        if (username.equals(user.getUsername()))
                            message.setReceiver(user.getId());
                    }
                    message.setSender(curentUser.getId());
                    message.setContent(content);
                    callService.addMessage(message);
                } else if (request.contains("GetMessages")) {
                    String username = in.readLine();
                    List<User> userList = callService.getUsers();
                    UUID receiverId = null;
                    for (User user : userList) {
                        if (username.equals(user.getUsername()))
                            receiverId = user.getId();
                    }
                    List<Message> messageList = callService.getMessage(curentUser.getId(), receiverId);
                    out.println(messageList.size());
                    out.flush();
                    for (Message message : messageList) {
                        System.out.println(message.getContent()+"    "+message.getReceiver());
                        if(message.getReceiver().equals(receiverId)){
                            out.println(username);
                            out.flush();
                        }
                        else {
                            out.println(curentUser.getUsername());
                            out.flush();
                        }

                        out.println(    message.getContent());
                        out.flush();
                    }

                }


            }
        } catch (
                IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }
}
