package com.example.androidchat;

import static com.example.androidchat.MyApplication.context;

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
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidchat.adapters.UserAdapter;
import com.example.androidchat.api.UserApi;
import com.example.androidchat.api.WebServiceAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class chat_page extends AppCompatActivity {

    private AppDB db;
    private UserDao userDao;
    private List<User> users;
    private UserAdapter adapter;
    // private ArrayAdapter<Post> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        Bundle values = getIntent().getExtras();
        Retrofit retrofit;
        WebServiceAPI webServiceAPI;
        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .baseUrl("http://10.0.2.2:7176/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UsersDB").allowMainThreadQueries().build();
        userDao = db.userDao();

        FloatingActionButton addFriendBtn = findViewById(R.id.add_friend_btn);
        addFriendBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, add_chat.class);
            startActivity(intent);
        });
        RecyclerView lstPosts = findViewById(R.id.list_chats);
        final UserAdapter adapter = new UserAdapter(context);

        //users = userDao.index();

        lstPosts.addOnItemTouchListener(
                new RecyclerItemClickListener(context, lstPosts ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        User chosenUser = adapter.getUsers().get(position);
                        String userFriend = chosenUser.getId();
                        Intent i = new Intent(context, MessageActivity.class);
                        i.putExtra("userFriend",userFriend);

                        String myUsername = values.getString("username");

                        i.putExtra("myUsername", myUsername);
                        startActivity(i);

                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        if (values != null) {

            String myUsername = values.getString("username");
            Call<List<UserTest>> call = webServiceAPI.getUsers(myUsername);
            call.enqueue(new Callback<List<UserTest>>() {
                @Override
                public void onResponse(Call<List<UserTest>> call, Response<List<UserTest>> response) {
                    List<UserTest> tempUsersTest = response.body();
                    int i = 0;



                    List<User> users = new ArrayList<>();

                    for(UserTest user : tempUsersTest){
                        User tempUser = new User(0,user.getId(),user.getLast(),user.getName(),user.getLastDate(), R.drawable.profile);
                        users.add(tempUser);
                    }

                    //final UserAdapter adapter = new UserAdapter(context);
                    lstPosts.setAdapter(adapter);
                    lstPosts.setLayoutManager(new LinearLayoutManager(context));
                    adapter.setUsers(users);

//                System.out.print("jtjgjfjjfdjdjrgjnghbjbjbgkjhbsd");
                    Log.i("hello", "hello");
                }

                @Override
                public void onFailure(Call<List<UserTest>> call, Throwable t) {
                    Log.i("in failure", "failed");
                }

            });
            //lstPosts.setOnClickListener();


            /*
            RecyclerView lstPosts = findViewById(R.id.list_chats);
            final UserAdapter adapter = new UserAdapter(this);
            lstPosts.setAdapter(adapter);
            lstPosts.setLayoutManager(new LinearLayoutManager(this));

             */

        }





        /*
        RecyclerView lstPosts = findViewById(R.id.list_chats);
         adapter = new UserAdapter(this);
        lstPosts.setAdapter(adapter);

        lstPosts.setLayoutManager(new LinearLayoutManager(this));
        adapter.setUsers(users);

        lstPosts.setLayoutManager(new LinearLayoutManager(this));

/*
        User u1 = new User(1,"hi", "Noa", "10:50", R.drawable.profile);
        User u2 = new User(1,"hi", "Inbal", "10:50", R.drawable.profile);
        User u3 = new User(3,"hghfgfhi", "Bar", "10:50", R.drawable.profile);
*/
      /*  userDao.insert(u1);
        userDao.insert(u2);
        userDao.insert(u3);

       */


/*
        //List<User> posts = new ArrayList<>();
        List<User> users = new ArrayList<>();
        users.add(new User(1,"hi", "Noa", "10:50", R.drawable.profile));
        users.add(new User(1,"hi", "Inbal", "10:50", R.drawable.profile));
        users.add(new User(3,"hghfgfhi", "Bar", "10:50", R.drawable.profile));


        adapter.setUsers(users);
        */









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


    @Override
    protected void onResume(){
        super.onResume();
        /*users.clear();
        users.addAll(userDao.index());
        adapter.notifyDataSetChanged();*/
    }


}