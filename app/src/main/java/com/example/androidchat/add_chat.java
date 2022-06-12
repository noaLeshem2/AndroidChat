package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_chat extends AppCompatActivity {

    private AppDB db;
    private PostDao postDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB").allowMainThreadQueries().build();
        postDao = db.postDao();

        Button btnSave = findViewById(R.id.add_friend_btn);
        btnSave.setOnClickListener(view->{

            EditText etItem = findViewById(R.id.my_new_friend);
            Post post = new Post(0, etItem.getText().toString());
            postDao.insert(post);


            finish();
        });
    }
}