package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
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

        ImageButton to_reg = findViewById(R.id.imageView);
        to_reg.setOnClickListener(v->{
            Intent i = new Intent(this, chat_page.class);
            startActivity(i);
        });


    }
}


