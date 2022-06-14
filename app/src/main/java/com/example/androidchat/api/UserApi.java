package com.example.androidchat.api;

import com.example.androidchat.MyApplication;
import com.example.androidchat.R;
import com.example.androidchat.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserApi(){
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


    public void get(){
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

}
