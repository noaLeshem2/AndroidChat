package com.example.androidchat;

import static com.example.androidchat.MyApplication.context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidchat.adapters.MessageAdapter;
import com.example.androidchat.api.WebServiceAPI;
import com.example.androidchat.entities.Message;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_example);

        ImageView to_chatPage = findViewById(R.id.imageBack);

        Bundle values = getIntent().getExtras();
        Retrofit retrofit;
        WebServiceAPI webServiceAPI;
        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .baseUrl("http://10.0.2.2:7176/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);




        if (values != null) {
            String otherUsername = values.getString("userFriend");
            Call<List<UserTest>> call = webServiceAPI.getUsers(otherUsername);
            call.enqueue(new Callback<List<UserTest>>() {
                @Override
                public void onResponse(Call<List<UserTest>> call, Response<List<UserTest>> response) {
                    List<UserTest> tempUsersTest = response.body();
                    int i = 0;
                    List<User> users = new ArrayList<>();

                    for(UserTest user : tempUsersTest){
                        User tempUser = new User(user.getId(),user.getLast(),user.getName(),user.getLastDate(), R.drawable.profile);
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
