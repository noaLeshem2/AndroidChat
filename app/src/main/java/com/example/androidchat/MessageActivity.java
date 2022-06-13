package com.example.androidchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidchat.adapters.MessageAdapter;
import com.example.androidchat.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_example);

        ImageView to_chatPage = findViewById(R.id.imageBack);
        to_chatPage.setOnClickListener(v->{
            Intent i = new Intent(this, chat_page.class);
            startActivity(i);
        });

        RecyclerView lstMsgs = findViewById(R.id.lstMessages);
        final MessageAdapter adapter = new MessageAdapter(this);
        lstMsgs.setAdapter(adapter);
        lstMsgs.setLayoutManager(new LinearLayoutManager(this));

        TextView mTextView = (TextView) findViewById(R.id.displayFriendName);
        mTextView.setText("Inbal");
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("hi",1, "14:30","inbal"));
        messages.add(new Message("blaaa",0,"12:00","amit"));
        messages.add(new Message("mako",1, "13:00","inbal"));
        messages.add(new Message("tov",0,"12:00","amit"));
        messages.add(new Message("bye",0,"1:00","inbal"));
        messages.add(new Message("byeeeeee",1,"14:20","amit"));
        messages.add(new Message("hi",1, "14:30","inbal"));
        messages.add(new Message("blaaa",0,"12:00","amit"));
        messages.add(new Message("mako",1, "13:00","inbal"));
        messages.add(new Message("tov",0,"12:00","amiy"));
        messages.add(new Message("bye",0,"1:00","i"));
        messages.add(new Message("byeeeeee",1,"14:20","m"));
        adapter.setMessages(messages);
    }
}
