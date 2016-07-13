package com.example.rssfeeder.repository.api.requests;

import android.support.annotation.NonNull;
import com.android.volley.Response;
import com.example.rssfeeder.app.Config;
import com.example.rssfeeder.repository.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.example.rssfeeder.BuildConfig;
import com.example.rssfeeder.repository.model.RssItem;
import com.example.rssfeeder.repository.model.RssItemDeserializer;

import java.util.ArrayList;

/**
 * Api requests
 */
public class ApiRequests
{
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(RssItem.class, new RssItemDeserializer())
            .create();


    public static GsonGetRequest<RssItem> getRssItem
    (
            @NonNull final Response.Listener<RssItem> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = Config.RssUrl;

        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<RssItem>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }


    public static GsonGetRequest<ArrayList<RssItem>> getRssItemArray
    (
            @NonNull final Response.Listener<ArrayList<RssItem>> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = Config.RssUrl;

        return new GsonGetRequest<>
                (
                        url,
                        new TypeToken<ArrayList<RssItem>>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }


    public static GsonPostRequest postItem
    (
            @NonNull final Response.Listener<User> listener,
            @NonNull final Response.ErrorListener errorListener
    )
    {
        final String url = Config.RssUrl;

        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "Ficus");
        jsonObject.addProperty("password", "123");

        final JsonArray squareGuys = new JsonArray();
        final JsonObject dev1 = new JsonObject();
        final JsonObject dev2 = new JsonObject();
        dev1.addProperty("name", "Jake Wharton");
        dev2.addProperty("name", "Jesse Wilson");
        squareGuys.add(dev1);
        squareGuys.add(dev2);

        jsonObject.add("squareGuys", squareGuys);

        final GsonPostRequest gsonPostRequest = new GsonPostRequest<>
                (
                        url,
                        jsonObject.toString(),
                        new TypeToken<User>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );

        gsonPostRequest.setShouldCache(false);

        return gsonPostRequest;
    }
}