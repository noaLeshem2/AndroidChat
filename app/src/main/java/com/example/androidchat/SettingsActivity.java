package com.example.androidchat;

import static android.os.SystemClock.sleep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);
        Button fakeSave = findViewById(R.id.fakeSave);
        fakeSave.setOnClickListener(v->{
            TextView textView= findViewById(R.id.canNot);
            textView.setVisibility(View.VISIBLE);
        });
        Button to_log = findViewById(R.id.returnToLog);
        to_log.setOnClickListener(v->{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
}
