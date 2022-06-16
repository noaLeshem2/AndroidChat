package com.example.androidchat;

import static com.example.androidchat.MyApplication.context;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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

import okhttp3.ResponseBody;
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
        ImageView send_btn = findViewById(R.id.send_msg);


        RecyclerView lstMsgs = findViewById(R.id.lstMessages);
        final MessageAdapter adapter = new MessageAdapter(this);
        lstMsgs.setAdapter(adapter);
        lstMsgs.setLayoutManager(new LinearLayoutManager(this));

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
            String myUsername = values.getString("myUsername");
            String otherUsername = values.getString("userFriend");
            Call<List<TempMsg>> call = webServiceAPI.getMsgs(myUsername,otherUsername);
            call.enqueue(new Callback<List<TempMsg>>() {
                @Override
                public void onResponse(Call<List<TempMsg>> call, Response<List<TempMsg>> response) {
                    List<TempMsg> tempMsgs = response.body();
                    Call<ResponseBody> callName = webServiceAPI.getUserDisplayname(otherUsername);
                    callName.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> callName, Response<ResponseBody> response) {
                            System.out.print("inside");
                            try {

                                String displayName  = response.body().string();
                                TextView mTextView = (TextView) findViewById(R.id.displayFriendName);
                                mTextView.setText(displayName);

                                int i = 0;
                                List<Message> messageList = new ArrayList<>();


                                for(TempMsg msg : tempMsgs){
                                    Message message;
                                    if(msg.isSent()){
                                         message = new Message(msg.getContent(),msg.getCreated(),1,displayName);
                                    } else
                                        message = new Message(msg.getContent(),msg.getCreated(),0,displayName);

                                    messageList.add(message);
                                }
                                adapter.setMessages(messageList);


                                System.out.print("hgcfghfgh");

                            }catch (Exception e)
                            {
                                System.out.print(e);

                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> callName, Throwable t) {
                            System.out.print("failedddd");
                        }
                    });

                    Log.i("hello", "hello");
                }

                @Override
                public void onFailure(Call<List<TempMsg>> call, Throwable t) {
                    Log.i("in failure", "failed");
                }

            });

            //lstPosts.setOnClickListener();


        }

        to_chatPage.setOnClickListener(v->{
            Intent i = new Intent(this, chat_page.class);
            String myUsername = values.getString("myUsername");
            i.putExtra("username",myUsername);
            startActivity(i);
        });

        send_btn.setOnClickListener(v -> {
            EditText editTextMsg = (EditText)findViewById(R.id.inputMessage);

            String textMsg = editTextMsg.getText().toString();
            editTextMsg.setText("");
            //empty msg
            if(textMsg.compareTo("") != 0) {
                TempMsg userMsg = new TempMsg(true,0,textMsg,"string");
                TempMsg friendMsg = new TempMsg(false,0,textMsg,"string");

                if (values != null) {
                    String myUsername = values.getString("myUsername");
                    String otherUsername = values.getString("userFriend");
                    Call<Void> callMyuser = webServiceAPI.addMsg(userMsg,myUsername, otherUsername);

                    callMyuser.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> callMyuser, Response<Void> response) {
                            //List<Void> tempMsgs = response.body();
                            Call<Void> callFriend = webServiceAPI.addMsg(friendMsg,otherUsername, myUsername);
                            callFriend.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> callFriend, Response<Void> response) {

                                    String myUsername = values.getString("myUsername");
                                    String otherUsername = values.getString("userFriend");
                                    Call<List<TempMsg>> call = webServiceAPI.getMsgs(myUsername,otherUsername);
                                    call.enqueue(new Callback<List<TempMsg>>() {
                                        @Override
                                        public void onResponse(Call<List<TempMsg>> call, Response<List<TempMsg>> response) {
                                            List<TempMsg> tempMsgs = response.body();
                                            Call<ResponseBody> callName = webServiceAPI.getUserDisplayname(otherUsername);

                                            callName.enqueue(new Callback<ResponseBody>() {
                                                @Override
                                                public void onResponse(Call<ResponseBody> callName, Response<ResponseBody> response) {
                                                    System.out.print("inside");
                                                    try {

                                                        String displayName  = response.body().string();
                                                        TextView mTextView = (TextView) findViewById(R.id.displayFriendName);
                                                        mTextView.setText(displayName);

                                                        int i = 0;
                                                        List<Message> messageList = new ArrayList<>();


                                                        for(TempMsg msg : tempMsgs){
                                                            Message message;
                                                            if(msg.isSent()){
                                                                message = new Message(msg.getContent(),msg.getCreated(),1,displayName);
                                                            } else
                                                                message = new Message(msg.getContent(),msg.getCreated(),0,displayName);

                                                            messageList.add(message);
                                                        }
                                                        adapter.setMessages(messageList);


                                                        System.out.print("hgcfghfgh");

                                                    }catch (Exception e)
                                                    {
                                                        System.out.print(e);

                                                    }
                                                }
                                                @Override
                                                public void onFailure(Call<ResponseBody> callName, Throwable t) {
                                                    System.out.print("failedddd");
                                                }
                                            });

                                            Log.i("hello", "hello");
                                        }

                                        @Override
                                        public void onFailure(Call<List<TempMsg>> call, Throwable t) {
                                            Log.i("in failure", "failed");
                                        }

                                    });
                                    //onResume();
                                }
                                @Override
                                public void onFailure(Call<Void> callFriend, Throwable t) {
                                    System.out.print("failedddd");
                                }
                            });



                    /*
                    //final UserAdapter adapter = new UserAdapter(context);
                    lstPosts.setAdapter(adapter);
                    lstPosts.setLayoutManager(new LinearLayoutManager(context));
                    adapter.setUsers(users);

                     */

//                System.out.print("jtjgjfjjfdjdjrgjnghbjbjbgkjhbsd");
                            Log.i("hello", "hello");
                        }

                        @Override
                        public void onFailure(Call<Void> callMyuser, Throwable t) {
                            Log.i("in failure", "failed");
                        }

                    });


                }


            }
        });

        /*
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

         */
    }

}
