package com.example.androidchat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Entity;
import androidx.room.Ignore;

import java.io.ByteArrayOutputStream;

@Entity
public class User {

    private String id;
    private String content;
    private String displayName;
    //private Image image;
    private String time;
    int img;
    private byte[] image1;


    public User(String id, String content,String displayName,String time, byte[] image1){
        this.id=id;
        this.content=content;
        this.image1 = image1;
        this.time = time;
        this.displayName = displayName;
    }

    public User(String id, String content,String displayName,String time){
        this.id=id;
        this.content=content;
        this.time = time;
        this.displayName = displayName;
    }

    public User(String id, String content,String displayName,String time, int img){
        this.id=id;
        this.content=content;
        this.time = time;
        this.displayName = displayName;
        this.img = img;
    }

    public void setImg(int img){this.img = img;}
    public int getImg(){return img;}
    public void setDisplayName(String displayName){this.displayName = displayName;}

    public String getDisplayName(){return displayName;}

    public void setImage1(Bitmap bitmap){this.image1 = getBitmapAsByteArray(bitmap);}

    public void setImage1(byte[] image11){this.image1 = image11;}

    public byte[] getImage1(){ return image1;}

    public Bitmap getImage1Bitmap(){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image1 , 0, image1 .length);
        return bitmap;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }

    public String getId(){
        return id;
    }

    public void setId(String id1){
        this.id = id1;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String con){
        this.content = con;
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
