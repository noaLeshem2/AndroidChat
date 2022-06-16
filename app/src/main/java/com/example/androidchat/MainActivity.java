package com.example.androidchat;

import static com.example.androidchat.MyApplication.context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.androidchat.api.UserApi;
import com.example.androidchat.api.WebServiceAPI;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private AppDB db;
    private PasswordDao PasswordDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.login_page);


        //db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PasswordDao").allowMainThreadQueries().build();
        //PasswordDao = db.passwordDao();

        Retrofit retrofit;
        WebServiceAPI webServiceAPI;
        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .baseUrl("http://10.0.2.2:7176/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);



        int flagEqual = 0;
        Button log_btn = findViewById(R.id.login_button);

        log_btn.setOnClickListener(v -> {

            // get the username and password.
            EditText tempUserName = (EditText)findViewById(R.id.editTextTextPersonName);
            String myUsername = tempUserName.getText().toString();

            EditText tempPassword = (EditText)findViewById(R.id.editTextTextPassword);
            String password = tempPassword.getText().toString();

            /*
            Call<ResponseBody> call = webServiceAPI.getUserPassword(myUsername);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String string  = response.body().string();
                        if(string.compareTo(password)==0){
                            flagEqual = 1;
                            Intent i = new Intent(this, chat_page.class);
                            startActivity(i);
                        }

                    }catch (Exception e)
                    {

                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.print("failedddd");
                }
            });

             */


            //UserApi userApi = new UserApi();
            //Context context = getBaseContext();
            //userApi.getPassword(myUsername, context);
            Call<ResponseBody> call = webServiceAPI.getUserPassword(myUsername);
            //String password;
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    System.out.print("inside");
                    try {

                        String string  = response.body().string();

                        if(string.compareTo(password)==0){
                            Intent i = new Intent(context, chat_page.class);
                            i.putExtra("username",myUsername);
                            startActivity(i);
                        }
                        //Log.i("hello", "hello");
                        //Log.i("hello", "hello");

                    }catch (Exception e)
                    {
                        System.out.print(e);

                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.print("failedddd");
                }
            });

            /*
            db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PasswordDao").allowMainThreadQueries().build();
            PasswordDao = db.passwordDao();
            List<Password> passwords = this.PasswordDao.index();
            Password password1 = passwords.get(0);
            if(password.compareTo(password1.getPassword()) == 0){
                Intent i = new Intent(this, chat_page.class);
                startActivity(i);
            }

             */

        });

        ImageButton to_reg = findViewById(R.id.imageView);
        to_reg.setOnClickListener(v->{
            Intent i = new Intent(this, RegisterPage.class);
            startActivity(i);
        });
    }
}


