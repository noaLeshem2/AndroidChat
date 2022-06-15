package com.example.androidchat;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

public class add_chat extends AppCompatActivity {

    private AppDB db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UsersDB").allowMainThreadQueries().build();
        userDao = db.userDao();

        Button btnSave = findViewById(R.id.add_friend_btn);
        btnSave.setOnClickListener(view->{

            EditText id_friend = findViewById(R.id.my_new_friend);
            EditText server_friend = findViewById(R.id.friend_server);
            EditText display_friend = findViewById(R.id.display_friend);
            User user = new User(0,null, display_friend.getText().toString(), null, R.drawable.profile);

            userDao.insert(user);


            finish();
        });
    }
}