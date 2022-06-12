package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        ImageButton to_reg = findViewById(R.id.imageView);
        to_reg.setOnClickListener(v->{
            Intent fake = new Intent(this, chat_page.class);
            startActivity(fake);
           /* Intent i = new Intent(this, RegisterPage.class);
            startActivity(i);*/
        });
    }
}