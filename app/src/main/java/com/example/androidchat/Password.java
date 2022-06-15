package com.example.androidchat;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Password {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String myUsername;
    private String password;


    public Password(int id, String myUsername, String password) {
        this.id = id;
        this.myUsername = myUsername;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyUsername() {
        return myUsername;
    }

    public void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    public Password(){}

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
