package com.example.androidchat.api;

import com.example.androidchat.User;

import java.util.List;

public interface WebServiceAPI {
    @GET("users")
    call<List<User>> getUsers();

    @POST("users")
    Call<void> createUser(@Body User user);


    @DELETE("users/{id}")
    Call<void> deleteUser(@Path("id") int id);

}
