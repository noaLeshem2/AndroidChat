package com.example.androidchat;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.androidchat.api.UserApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button log_btn = findViewById(R.id.login_button);

        log_btn.setOnClickListener(v -> {
            // get the username and password.
            EditText tempUserName = (EditText)findViewById(R.id.editTextTextPersonName);
            String myUsername = tempUserName.getText().toString();

            EditText tempPassword = (EditText)findViewById(R.id.editTextTextPassword);
            String password = tempPassword.getText().toString();

            UserApi userApi = new UserApi();
            userApi.get(myUsername);

        });
/*
        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB").allowMainThreadQueries().build();
        PostDao postDao = db.postDao();

        Button btnSave = findViewById(R.id.add_friend_btn);
        btnSave.setOnClickListener(view->{

            EditText id_friend = findViewById(R.id.my_new_friend);
            EditText server_friend = findViewById(R.id.friend_server);
            EditText display_friend = findViewById(R.id.my_new_friend);
            Post post = new Post( parseInt(id_friend.getText().toString()), null, display_friend.getText().toString(), null);

            postDao.insert(post);


            finish();
        });
*/






        ImageButton to_reg = findViewById(R.id.imageView);
        to_reg.setOnClickListener(v->{
            Intent i = new Intent(this, chat_page.class);
            startActivity(i);
        });


    }
}


