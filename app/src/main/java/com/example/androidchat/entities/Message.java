package com.example.androidchat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id_mess;
    private String content;
    private String time;

    public Message(int id_mess) {
        this.id_mess = id_mess;
    }

    public void setId_mess(int id_mess) {
        this.id_mess = id_mess;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId_mess() {
        return id_mess;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
