package com.example.androidchat;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidchat.entities.Message;
import com.example.androidchat.User;

@Database(entities = {Post.class, User.class, Message.class}, version = 2)
public abstract class AppDB extends RoomDatabase{


    public abstract PostDao postDao();
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();

}
