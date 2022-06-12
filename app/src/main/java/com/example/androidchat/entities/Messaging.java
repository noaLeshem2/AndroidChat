package com.example.androidchat;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Messaging {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private List<Message> messagesList;
    private Image image;
    private String time;

    public void setId(int id) {
        this.id = id;
    }

    public void setMessagesList(List<Message> messagesList) {
        this.messagesList = messagesList;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public List<Message> getMessagesList() {
        return messagesList;
    }

    public Image getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

    public Messaging(int id) {
        this.id = id;
    }
}
