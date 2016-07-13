package com.example.rssfeeder.ui.login;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.example.rssfeeder.app.Config;
import com.example.rssfeeder.app.RssFeederApplication;
import com.example.rssfeeder.repository.api.requests.ApiRequests;
import com.example.rssfeeder.repository.api.requests.GsonGetRequest;
import com.example.rssfeeder.repository.api.requests.GsonPostRequest;
import com.example.rssfeeder.repository.api.requests.JsonObjectRequest;
import com.example.rssfeeder.repository.model.RssItem;
import com.example.rssfeeder.repository.model.User;
import com.example.rssfeeder.ui.newsFeed.NewsFeedInteractor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginInteractorImp implements LoginInteractor
{
    public static final String TAG = "JsonObject post";
    public void sendRegistration(User user, final onLoginListener listener)
    {
        Gson gson = new Gson();
        gson.toJson(user);
        String jsonObject = gson.toJson(user);

        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Config.RssUrl, jsonObject, new JsonObjectRequest.JsonCallback() {
            @Override
            public void onSuccess(JSONArray result)
            {
                listener.onLoginSuccess();
            }

            @Override
            public void onError(String error)
            {
                listener.onConnectionError();
            }
        });


        RssFeederApplication.addRequest(postRequest, TAG);
    }

    public void sendLogin(User user, final onLoginListener listener)
    {
            sendRegistration(user, listener);  //  JUST FOR NOW
    }
}
