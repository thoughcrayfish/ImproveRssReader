package com.example.rssfeeder.ui.newsFeed;

import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rssfeeder.app.RssFeederApplication;
import com.example.rssfeeder.repository.api.requests.ApiRequests;
import com.example.rssfeeder.repository.api.requests.GsonGetRequest;
import com.example.rssfeeder.repository.model.RssItem;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedInteractorImp implements NewsFeedInteractor
{
    RequestQueue requestQueue;
    public static final String TAG = "JsonObject request";

    @Override
    public void getRssList(int position, String url, final onListGetListener listener)
    {
        final GsonGetRequest<ArrayList<RssItem>> gsonGetRequest =
                ApiRequests.getRssItemArray
                        (
                                new Response.Listener<ArrayList<RssItem>>()
                                {
                                    @Override
                                    public void onResponse(ArrayList<RssItem> RssItemObjectArrayList)
                                    {
                                        // Deal with the DummyObject here
                                        listener.onSuccess(RssItemObjectArrayList);
                                    }
                                }
                                ,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error)
                                    {
                                        // Deal with the error here
                                        listener.onError(error);
                                    }
                                }
                        );

        RssFeederApplication.addRequest(gsonGetRequest, TAG);
    }
}



