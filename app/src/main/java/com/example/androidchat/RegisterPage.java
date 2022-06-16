package com.example.androidchat;

import static com.example.androidchat.MyApplication.context;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.androidchat.api.WebServiceAPI;
import com.example.androidchat.entities.AllUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPage extends AppCompatActivity {

    public class UserToReg{
        String id;
        String name;
        String server;
        String password;

        public UserToReg(String id, String name, String server, String password) {
            this.id = id;
            this.name = name;
            this.server = server;
            this.password = password;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getServer() {
            return server;
        }

        public String getPassword() {
            return password;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        ImageButton to_login = findViewById(R.id.btnAlreadyReg);
        to_login.setOnClickListener(v->{
           Intent i = new Intent(this, MainActivity.class);
           startActivity(i);
        });

        Bundle values = getIntent().getExtras();
        Retrofit retrofit;
        WebServiceAPI webServiceAPI;
        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .baseUrl("http://10.0.2.2:7176/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);



        Button regBtn = findViewById(R.id.registerBtn);
        regBtn.setOnClickListener(view->{

            EditText id_friend = findViewById(R.id.editTextTextPersonName);
            String server_friend = "localhost:7100";
            EditText display_friend = findViewById(R.id.editTextTextDisplayPersonName);
            EditText password_friend = findViewById(R.id.editTextTextPassword);
            EditText password_confirm = findViewById(R.id.confirm_password);

            if(password_confirm.getText().toString().compareTo(password_friend.getText().toString()) == 0 &&
                    id_friend.getText().toString().compareTo("") != 0 &&
                    display_friend.getText().toString().compareTo("") != 0 && password_friend.getText().toString().compareTo("") != 0){
                // nothing not empty and passwords are the same

                    Call<List<AllUsers>> call = webServiceAPI.getAllUsers();
                    call.enqueue(new Callback<List<AllUsers>>() {
                        @Override
                        public void onResponse(Call<List<AllUsers>> call, Response<List<AllUsers>> response) {
                            List<AllUsers> tempMsgs = response.body();
                            if (tempMsgs != null) {
                                for (AllUsers user : tempMsgs) {
                                    // if user already exist
                                    if (user.getId().compareTo(id_friend.getText().toString()) == 0 &&
                                            user.getServer().compareTo(server_friend) == 0 &&
                                            user.getName().compareTo(display_friend.getText().toString()) == 0) {}
                                    else{
                                        Call<Void> callReg = webServiceAPI.regFriend(new UserToReg(id_friend.getText().toString(), display_friend.getText().toString(), server_friend, password_friend.getText().toString()));
                                        callReg.enqueue(new Callback<Void>() {
                                            public void onResponse(Call<Void> callReg, Response<Void> response) {
                                                Intent i = new Intent(context, chat_page.class);
                                                i.putExtra("username", id_friend.getText().toString());
                                                startActivity(i);
                                            }
                                            public void onFailure(Call<Void> callReg, Throwable t) {
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
            }

            //User user = new User(0,null, display_friend.getText().toString(), null, R.drawable.profile);

            // userDao.insert(user);


            //check!!!!!!!!!!!




                //lstPosts.setOnClickListener();


            /*
            RecyclerView lstPosts = findViewById(R.id.list_chats);
            final UserAdapter adapter = new UserAdapter(this);
            lstPosts.setAdapter(adapter);
            lstPosts.setLayoutManager(new LinearLayoutManager(this));

             */



        });
    }
}