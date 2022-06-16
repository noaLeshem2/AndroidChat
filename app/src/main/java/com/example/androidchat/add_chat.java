package com.example.androidchat;

import static com.example.androidchat.MyApplication.context;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidchat.api.WebServiceAPI;
import com.example.androidchat.entities.AllUsers;
import com.example.androidchat.entities.Message;
import com.example.androidchat.entities.Messaging;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class add_chat extends AppCompatActivity {

    private AppDB db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

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

        Button btnSave = findViewById(R.id.add_friend_btn);
        btnSave.setOnClickListener(view->{

            EditText id_friend = findViewById(R.id.my_new_friend);
            EditText server_friend = findViewById(R.id.friend_server);
            EditText display_friend = findViewById(R.id.display_friend);
            //User user = new User(0,null, display_friend.getText().toString(), null, R.drawable.profile);

           // userDao.insert(user);


            //check!!!!!!!!!!!



            if (values != null) {
                String myUsername = values.getString("myUsername");
                Call<List<AllUsers>> call = webServiceAPI.getAllUsers();
                call.enqueue(new Callback<List<AllUsers>>() {
                    @Override
                    public void onResponse(Call<List<AllUsers>> call, Response<List<AllUsers>> response) {
                        List<AllUsers> tempMsgs = response.body();
                        if (tempMsgs != null) {
                            for (AllUsers user : tempMsgs) {
                                if (user.getId().compareTo(id_friend.getText().toString()) == 0 &&
                                        user.getServer().compareTo(server_friend.getText().toString()) == 0 &&
                                        user.getName().compareTo(display_friend.getText().toString()) == 0) {

                                    // check if we are friends already
                                    Call<List<UserTest>> callName = webServiceAPI.getUsers(myUsername);
                                    callName.enqueue(new Callback<List<UserTest>>() {
                                        @Override
                                        public void onResponse(Call<List<UserTest>> callName, Response<List<UserTest>> response) {
                                            System.out.print("inside");
                                            try {

                                                List<UserTest> contacts = response.body();
                                                if (contacts != null) {
                                                    for (UserTest contact : contacts) {
                                                        if (contact.getId().compareTo(id_friend.getText().toString()) != 0) {
                                                            System.out.print("good");
                                                        }
                                                    }
                                                }

                                            } catch (Exception e) {
                                                System.out.print(e);

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<UserTest>> callName, Throwable t) {
                                            System.out.print("failedddd");
                                        }
                                    });

                                }
                            }
                        }
                    }

                @Override
                public void onFailure(Call<List<AllUsers>> call, Throwable t) {
                    System.out.print("failedddd");
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


            finish();
        });
    }
}