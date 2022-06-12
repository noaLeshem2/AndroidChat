package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class chat_page extends AppCompatActivity {

    private AppDB db;
    private PostDao postDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB").allowMainThreadQueries().build();
        postDao = db.postDao();

        Button addFriendBtn = findViewById(R.id.add_friend_btn);
        addFriendBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, add_chat.class);
            startActivity(intent);
        });


    }
}