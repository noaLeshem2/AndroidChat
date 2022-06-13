package com.example.androidchat.entities;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private String content;
    private String time;
    private int sent;
    private String display_name;

  /*  private int id_mess;
    private Image image;*/


    public void setSent(int sent) {
        this.sent = sent;
    }

    public int isSent() {
        return sent;
    }

   /* public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
*/
    public String getDisplay_name() {
        return display_name;
    }
/*
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
*/
    public Message(String content, int sent, String time, String display_name) {
        this.content = content;
        this.sent= sent;
        this.time = time;
    }
/*
    public void setId_mess(int id_mess) {
        this.id_mess = id_mess;
    }*/

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

 /*   public int getId_mess() {
        return id_mess;
    }*/

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
