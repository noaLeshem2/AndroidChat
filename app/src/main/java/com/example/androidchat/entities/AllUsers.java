package com.example.androidchat.entities;

import com.example.androidchat.TempMsg;
import com.example.androidchat.User;
import com.example.androidchat.UserTest;

import java.util.List;

public class AllUsers {

    public class Chats{
        private String id;
        private List<TempMsg> messages;

        public Chats(String id, List<TempMsg> messages) {
            this.id = id;
            this.messages = messages;
        }

        public Chats() {
        }

        public void setMessages(List<TempMsg> messages) {
            this.messages = messages;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public List<TempMsg> getMessages() {
            return messages;
        }
    }

    private String id;
    private String name;
    private String server;
    private String password;
    private List<Chats> chats;
    private List<UserTest> userTests;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChats(List<Chats> chats) {
        this.chats = chats;
    }

    public void setUserTests(List<UserTest> userTests) {
        this.userTests = userTests;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getPassword() {
        return password;
    }

    public List<Chats> getChats() {
        return chats;
    }

    public List<UserTest> getUserTests() {
        return userTests;
    }

    public AllUsers(String id, String name, String server, String password, List<Chats> chats, List<UserTest> userTests) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.password = password;
        this.chats = chats;
        this.userTests = userTests;
    }
}
