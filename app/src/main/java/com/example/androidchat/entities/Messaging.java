package com.example.androidchat.entities;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Messaging {

    public class Chats{
        private String id;
        private List<Message> messages;

        public Chats(String id, List<Message> messages) {
            this.id = id;
            this.messages = messages;
        }

        public Chats() {
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public List<Message> getMessages() {
            return messages;
        }
    }
    @PrimaryKey(autoGenerate = true)
    private int key;
    private String id;
    private String displayName;
    private String server;
    private String password;
    private List<Chats> chats;
    private Image image;
    //private String time;


    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public Messaging(int key, String id, String displayName, String server, String password, List<Chats> chats, Image image) {
        this.key = key;
        this.id = id;
        this.displayName = displayName;
        this.server = server;
        this.password = password;
        this.chats = chats;
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setChats(List<Chats> chats) {
        this.chats = chats;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<Chats> getChats() {
        return chats;
    }

    public Messaging(String id, String displayName, String server, String password, List<Chats> chats, Image image) {
        this.id = id;
        this.displayName = displayName;
        this.server = server;
        this.password = password;
        this.chats = chats;
        this.image = image;
    }

    public String getServer() {
        return server;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Messaging(String id, String server, String displayName, Image image) {
        this.id = id;
        this.server = server;
        this.displayName = displayName;
        this.image = image;
        //this.time = time;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public void setImage(Image image) {
        this.image = image;
    }

    /*public void setTime(String time) {
        this.time = time;
    }*/

    public Image getImage() {
        return image;
    }

   /* public String getTime() {
        return time;
    }*/

}
