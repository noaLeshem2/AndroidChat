package com.example.androidchat;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidchat.entities.Message;

@Database(entities = {Post.class}, version = 1)
public abstract class AppDB extends RoomDatabase{

    public abstract PostDao postDao();
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();

}
