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

    public class UserToInvited{
        String id;
        String server;
        String name;
        String connected;

        public UserToInvited(String id, String server, String name, String connected) {
            this.id = id;
            this.server = server;
            this.name = name;
            this.connected = connected;
        }

        public String getId() {
            return id;
        }

        public String getServer() {
            return server;
        }

        public String getName() {
            return name;
        }

        public String getConnected() {
            return connected;
        }
    }

    public class Invited{
        String from;
        String to;
        String server;

        public Invited(String from, String to, String server) {
            this.from = from;
            this.to = to;
            this.server = server;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getServer() {
            return server;
        }
    }
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
                                            try {

                                                List<UserTest> contacts = response.body();
                                                if (contacts != null) {
                                                    int is_friend=0;
                                                    for (UserTest contact : contacts) {
                                                        if (contact.getId().compareTo(id_friend.getText().toString()) == 0) {
                                                            is_friend = 1;
                                                            break;
                                                        }
                                                    }
                                                    if(is_friend == 0){
                                                        // Add friend to my contacts
                                                        String myUsername = values.getString("myUsername");
                                                        Call<Void> callMyContacts = webServiceAPI.addFriend(new UserToInvited(id_friend.getText().toString(), server_friend.getText().toString(), display_friend.getText().toString(), myUsername));
                                                        callMyContacts.enqueue(new Callback<Void>() {
                                                            public void onResponse(Call<Void> callMyContacts, Response<Void> response) {
                                                                Call<Void> inviteCall = webServiceAPI.inviteFriend(new Invited(myUsername, id_friend.getText().toString(), server_friend.getText().toString()));
                                                                inviteCall.enqueue(new Callback<Void>() {
                                                                    public void onResponse(Call<Void> inviteCall, Response<Void> response) {
                                                                        Intent i = new Intent(context, chat_page.class);
                                                                        i.putExtra("username",myUsername);
                                                                        startActivity(i);
                                                                    }
                                                                    public void onFailure(Call<Void> inviteCall, Throwable t) {
                                                                        System.out.print("failedddd");
                                                                    }
                                                                });

                                                            }
                                                            @Override
                                                            public void onFailure(Call<Void> callMyContacts, Throwable t) {
                                                                System.out.print("failedddd");
                                                            }


                                                        });
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

        });

        Button returnBtn = findViewById(R.id.returnBtn);
        returnBtn.setOnClickListener(v->{
            Intent i = new Intent(this, chat_page.class);
            String myUsername = values.getString("myUsername");
            i.putExtra("username",myUsername);
            startActivity(i);
        });
    }
}