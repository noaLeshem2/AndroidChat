package com.example.androidchat.api;

import com.example.androidchat.TempMsg;
import com.example.androidchat.User;
import com.example.androidchat.UserTest;
import com.example.androidchat.entities.AllUsers;
import com.example.androidchat.entities.Messaging;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    //@GET("contacts")
    //Call<List<User>> getUsers();

    @GET("Contacts/users")
    Call<List<AllUsers>> getAllUsers();

    @GET("Contacts/")
    Call<List<UserTest>> getInbalUsers();

    @GET("Contacts/{connected}")
    Call<List<UserTest>> getUsers(@Path("connected") String connected);

    @GET("Contacts/{connected}/password")
    Call<ResponseBody> getUserPassword(@Path("connected") String connected);

    @GET("Contacts/{myUsername}/{myFriend}/messages")
    Call<List<TempMsg>> getMsgs(@Path("myUsername") String myUsername, @Path("myFriend") String myFriend);

    @GET("Contacts/{connected}/name")
    Call<ResponseBody> getUserDisplayname(@Path("connected") String connected);


    @POST("Contacts/{myUsername}/{myFriend}/messages")
    Call<Void> addMsg(@Body TempMsg tempMsg, @Path("myUsername") String myUsername, @Path("myFriend") String myFriend);


    @POST("users")
    Call<Void> createUser(@Body User user);


    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

}
