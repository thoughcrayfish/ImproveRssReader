package com.example.rssfeeder.ui.login;

import android.content.Intent;
import android.widget.Toast;

import com.example.rssfeeder.data.User;
import com.example.rssfeeder.model.RetrofitRequest;
import com.example.rssfeeder.ui.newsItem.NewsItemActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginInteractorImp implements ILoginInteractor
{
    public void sendRegistration(User user, final onLoginListener listener)
    {

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitRequest.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitRequest apiService = retrofit.create(RetrofitRequest.class);

        Call<User> call = apiService.createUser(user);

        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                listener.onLoginSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
               listener.onLoginFail();
            }
        });


    }
    public void sendLogin(User user, final onLoginListener listener)
    {
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitRequest.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // prepare call in Retrofit 2.0
        RetrofitRequest request = retrofit.create(RetrofitRequest.class);

        Call<User> call = request.getUser("vogella");
        //asynchronous call
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int code = response.code();
                if (code == 200)
                {
                    User user = response.body();
                    listener.onLoginSuccess();
                } else {
                    listener.onLoginFail();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                    listener.onConnectionError();
            }
        });

    }

}
