package com.example.androidchat.api;


import android.media.session.MediaSession;
import android.util.Log;

import com.example.androidchat.MyApplication;
import com.example.androidchat.R;
import com.example.androidchat.User;
import com.example.androidchat.UserTest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    List<String> connected;

    public UserApi(){
        retrofit = new Retrofit.Builder()
//                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .baseUrl("http://10.0.2.2:7176/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }



    public void get(String username){
        Call<List<UserTest>> call = webServiceAPI.getUsers(username);
        call.enqueue(new Callback<List<UserTest>>() {
            @Override
            public void onResponse(Call<List<UserTest>> call, Response<List<UserTest>> response) {
                List<UserTest> users = response.body();
//                System.out.print("jtjgjfjjfdjdjrgjnghbjbjbgkjhbsd");
                Log.i("hello", "hello");
            }

            @Override
            public void onFailure(Call<List<UserTest>> call, Throwable t) {
                    Log.i("in failure", "failed");
            }
        });
    }
/*
    public void getUsers(){
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                System.out.print("jtjgjfjjfdjdjrgjnghbjbjbgkjhbsd");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {


            }
        });
    }



 */
    public void getPassword(String username){
        Call<ResponseBody> call = webServiceAPI.getUserPassword(username);
        String password;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string  = response.body().string();
                }catch (Exception e)
                {

                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.print("failedddd");
            }
        });
    }

}
