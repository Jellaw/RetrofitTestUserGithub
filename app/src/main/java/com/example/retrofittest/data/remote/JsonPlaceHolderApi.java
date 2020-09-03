package com.example.retrofittest.data.remote;
import com.example.retrofittest.data.model.Comment;
import com.example.retrofittest.data.model.User;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;



public interface JsonPlaceHolderApi {
    @GET("users")
    Call<List<User>> getUser();

    @GET("users/{login}")
    Call<Comment> getComments(@Path("login") String login);



}
