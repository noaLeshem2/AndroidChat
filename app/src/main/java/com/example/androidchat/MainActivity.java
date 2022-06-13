package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidchat.adapters.MessageAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        ImageButton to_reg = findViewById(R.id.imageView);
        to_reg.setOnClickListener(v->{
            Intent i = new Intent(this, RegisterPage.class);
            startActivity(i);
        });

        Button loginB = findViewById(R.id.logBtn);
        loginB.setOnClickListener(v->{
            Intent i = new Intent(this, MessageActivity.class);
            startActivity(i);
        });
    }
}