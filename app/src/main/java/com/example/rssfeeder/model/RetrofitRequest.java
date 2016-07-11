package com.example.rssfeeder.model;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.Call;

public interface RetrofitRequest
{
    String ENDPOINT = "http://jsonplaceholder.typicode.com";

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String user);

    @POST("/users/{user}")
    Call<User> createUser(@Body User user);
}
