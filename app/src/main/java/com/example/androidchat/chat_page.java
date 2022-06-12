package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class chat_page extends AppCompatActivity {

    private AppDB db;
    private PostDao postDao;
    private List<Post> posts;
    private ArrayAdapter<Post> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB").allowMainThreadQueries().build();
        postDao = db.postDao();

        FloatingActionButton addFriendBtn = findViewById(R.id.add_friend_btn);
        addFriendBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, add_chat.class);
            startActivity(intent);
        });

        posts = new ArrayList<>();

        ListView lvPosts = findViewById(R.id.list_chats);
        adapter = new ArrayAdapter<Post>(this,
                android.R.layout.simple_list_item_1, posts);

        lvPosts.setAdapter(adapter);

        lvPosts.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Post post = posts.remove(i);
            postDao.delete(post);
            adapter.notifyDataSetChanged();
            return true;
        });

        lvPosts.setOnItemClickListener((adapterView, view, i, l) -> {
          Intent intent = new Intent(this, add_chat.class);
          intent.putExtra("id", posts.get(i).getId());
;         startActivity(intent);
        });


    }
    @Override
    protected void onResume(){
        super.onResume();
        posts.clear();
        posts.addAll(postDao.index());
        adapter.notifyDataSetChanged();
    }
}