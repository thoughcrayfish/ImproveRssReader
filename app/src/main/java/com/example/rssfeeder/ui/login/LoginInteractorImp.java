package com.example.rssfeeder.ui.login;

import com.android.volley.Request;

import com.example.rssfeeder.repository.api.RssVolley;
import com.example.rssfeeder.repository.api.Urls;
import com.example.rssfeeder.repository.api.requests.JsonObjectRequest;
import com.example.rssfeeder.repository.model.LoginUser;
import com.google.gson.Gson;

import org.json.JSONArray;


/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginInteractorImp implements LoginInteractor
{
    public static final String TAG = "JsonObject post";
    public void sendRegistration(LoginUser loginUser, final onLoginListener listener)
    {
        Gson gson = new Gson();
        gson.toJson(loginUser);
        String jsonObject = gson.toJson(loginUser);

        final JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, Urls.RssUrl, jsonObject, new JsonObjectRequest.JsonCallback() {
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


        RssVolley.addRequest(postRequest, TAG);
    }

    public void sendLogin(LoginUser loginUser, final onLoginListener listener)
    {
            sendRegistration(loginUser, listener);  //  JUST FOR NOW
    }
}
