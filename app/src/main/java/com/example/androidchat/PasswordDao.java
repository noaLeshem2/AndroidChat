package com.example.androidchat;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PasswordDao {
    @Query("SELECT * FROM password")
    List<Password> index();

    @Insert
    void insert(Password... passwords);

    @Update
    void update(Password... passwords);

    @Delete
    void delete(Password... passwords);
}
