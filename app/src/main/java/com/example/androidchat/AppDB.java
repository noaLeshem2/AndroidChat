package com.example.androidchat;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Post.class, Password.class}, version = 4)
public abstract class AppDB extends RoomDatabase{

    public abstract PostDao postDao();
    public abstract PasswordDao passwordDao();
    private AppDB appDB;
    /*
    static AppDB getMyDb(Context context){

        appDB = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "PasswordDao")
                .allowMainThreadQueries()
                .build();

    }

     */

}
