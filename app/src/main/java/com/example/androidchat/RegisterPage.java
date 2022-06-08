package com.example.androidchat;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        Button to_login = findViewById(R.id.btnAlreadyReg);
        to_login.setOnClickListener(v->{
           Intent i = new Intent(this, MainActivity.class);
           startActivity(i);
        });
    }
}