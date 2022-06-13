package com.example.androidchat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;

@Entity
public class Post {

    @PrimaryKey(autoGenerate=true)
    private int id;
    private String content;
    private String displayName;
    //private Image image;
    private String time;
    private byte[] image1;

    public void setDisplayName(String displayName){this.displayName = displayName;}

    public String getDisplayName(){return displayName;}

    public void setImage1(Bitmap bitmap){this.image1 = getBitmapAsByteArray(bitmap);}

    public void setImage1(byte[] image11){this.image1 = image11;}

    public byte[] getImage1(){ return image1;}

    public Bitmap getImage1Bitmap(){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image1 , 0, image1 .length);
        return bitmap;}
    /*
    public void setImage(Image image) {
        this.image1 = getBitmapAsByteArray(image);
    }

    public Image getImage() {
        return image;
    }
    */


    public void setTime(String time) {
        this.time = time;
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

    @Ignore
    public Post(int id, String content){
        this.id=id;
        this.content=content;
    }

    @Ignore
    public Post(int id, String content,String displayName,String time, byte[] image1){
        this.id=id;
        this.content=content;
        this.image1 = image1;
        this.time = time;
        this.displayName = displayName;
    }
    public Post(int id, String content,String displayName,String time){
        this.id=id;
        this.content=content;
        this.time = time;
        this.displayName = displayName;
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
