package com.example.androidchat.viewmodels;

import android.widget.ListPopupWindow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidchat.Post;
import com.example.androidchat.User;

import java.util.List;

public class PostsViewModle extends ViewModel {
    //private PostsR
    private LiveData<List<User>> users;
    //private LiveData<List<Post>> posts;

    //public LiveData<List<Post>> getPosts() {return posts;}

    public LiveData<List<User>> getPosts() {
        return users;
    }
}
