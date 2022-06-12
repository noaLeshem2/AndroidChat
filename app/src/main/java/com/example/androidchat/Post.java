package com.example.androidchat;

import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Post {

    @PrimaryKey(autoGenerate=true)
    private int id;
    private String content;


    public int getId(){
        return id;
    }

    public void setId(int id1){
        this.id = id1;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String con){
        this.content = con;
    }

    public Post(int id, String content){
        this.id=id;
        this.content=content;
    }

    public Post(){
    }

    @Override
    public String toString(){
        return "Post{" +
                "Id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
