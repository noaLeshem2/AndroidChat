package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
/*
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
    }
}*/


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_list);

        ImageButton sndBtn = findViewById(R.id.sendBtn);
        sndBtn.setOnClickListener(v->{
            Intent i = new Intent(this, .class);
            startActivity(i);
        });
    }
}