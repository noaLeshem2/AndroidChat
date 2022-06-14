package com.example.androidchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidchat.adapters.UserAdapter;
import com.example.androidchat.api.UserApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
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


        RecyclerView lstPosts = findViewById(R.id.list_chats);
        final UserAdapter adapter = new UserAdapter(this);
        lstPosts.setAdapter(adapter);
        lstPosts.setLayoutManager(new LinearLayoutManager(this));


        //List<User> posts = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(new User(1,"hi", "Noa", "10:50", R.drawable.profile));
        users.add(new User(1,"hi", "Inbal", "10:50", R.drawable.profile));
        users.add(new User(3,"hghfgfhi", "Bar", "10:50", R.drawable.profile));

        adapter.setUsers(users);







            /*
            //convert the image to Bitmap

        //Image image = current.getImage();
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            byte[] bytes = new byte[buffer.capacity()];
            buffer.get(bytes);
            Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);

        Drawable image = getDrawable(R.drawable.logo);
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.logo);
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();


        byte[] bytes = new byte[buffer.capacity()];
        buffer.get(bytes);
        */


        /*
        posts.add(new User(1,"hi", "Noa", "10:50"));
        posts.add(new User(1,"hi", "Inbal", "10:50"));
        posts.add(new User(3,"hghfgfhi", "Bar", "10:50"));

        adapter.setPosts(posts);

         */


        //RecyclerView lstPosts = findViewById(R.id.)






        /*
        posts = new ArrayList<>();

        ListView lvPosts = findViewById(R.id.list_chats);
        adapter = new ArrayAdapter<Post>(this,
                R.layout.list_chats, posts);

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


*/

    }

    /*
    @Override
    protected void onResume(){
        super.onResume();
        posts.clear();
        posts.addAll(postDao.index());
        adapter.notifyDataSetChanged();
    }

     */
}