package com.example.rssfeeder.ui.newsFeed;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rssfeeder.BuildConfig;
import com.example.rssfeeder.repository.model.BroadcastObject;
import com.example.rssfeeder.repository.api.RssVolley;
import com.example.rssfeeder.repository.api.Urls;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedInteractorImp implements NewsFeedInteractor
{
    public static final String TAG = "JsonObject request";
    private static final Gson gson = new Gson();

    @Override
    public void getRssList(int position, final onListGetListener listener)
    {
        Map<String, String> params = new HashMap<>();
        params.put("action", Urls.LIVEBROADCAST);
//        params.put("type", "gallery");
        params.put("status", "all");
        params.put("platform", "android");
        params.put("version", String.valueOf(BuildConfig.VERSION_CODE));
        params.put("mobile", String.valueOf("mobile"));

        String finalURL = RssVolley.getRequestURL(params, Urls.RssUrl);

        JsonObjectRequest req = new JsonObjectRequest(finalURL, new JSONObject(params),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        BroadcastObject object = gson.fromJson(response.toString(), BroadcastObject.class);
                        listener.onSuccess(object);

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                listener.onError(error);
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        RssVolley.addRequest(req, TAG);
    }
}



