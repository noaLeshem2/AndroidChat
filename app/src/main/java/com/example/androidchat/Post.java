package com.example.androidchat;

import android.graphics.Bitmap;
import android.media.Image;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;

@Entity
public class Post {

    @PrimaryKey(autoGenerate=true)
    private int id;
    private String content;
    private String displayName;
    private Image image;
    private String time;
    private byte[] image1;

    public void setDisplayName(String displayName){this.displayName = displayName;}

    public String getDisplayName(){return displayName;}

    public void setImage(Image image) {
        this.image1 = getBitmapAsByteArray(image);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Image getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

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

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
