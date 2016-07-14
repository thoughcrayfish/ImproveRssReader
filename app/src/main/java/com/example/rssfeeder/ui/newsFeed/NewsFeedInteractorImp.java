package com.example.rssfeeder.ui.newsFeed;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.rssfeeder.BuildConfig;
import com.example.rssfeeder.repository.api.RssVolley;
import com.example.rssfeeder.repository.api.Urls;
import com.example.rssfeeder.repository.model.RssItem;
import com.example.rssfeeder.repository.model.RssItemDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedInteractorImp implements NewsFeedInteractor
{
    RequestQueue requestQueue;
    Type type = new TypeToken<List<RssItem>>(){}.getType();
    public static final String TAG = "JsonObject request";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(RssItem.class, new RssItemDeserializer())
            .create();
    RssItemDeserializer ds = new RssItemDeserializer();


    @Override
    public void getRssList(int position, String url, final onListGetListener listener)
    {
        Map<String, String> params = new HashMap<>();
        params.put("action", Urls.LIVEBROADCAST);
        params.put("type", "text");
        params.put("status", "all");
//        params.put("post_id", String.valueOf(post_id));
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
                        try
                        {
                            ArrayList<JSONObject> arrayList = new ArrayList<JSONObject>();
                            JSONArray jsonResult = response.getJSONArray("data");

                            for (int i = 0; i < jsonResult.length(); i++)
                            {
                                HashMap<String, String> map = new HashMap<String, String>();
                                JSONObject c = jsonResult.getJSONObject(i);
                                //map.put("title", c.getString("title").toString());
                                map.put("description", c.optString("title"));
                                arrayList.add(c);
                            }

//                            String str = gson.toJson(arrayList);
                            ArrayList<RssItem> items = gson.fromJson(arrayList.toString(), new TypeToken<ArrayList<RssItem>>(){}.getType());
                            listener.onSuccess(items);


//                            VolleyLog.v("Response:%n %s", arrayList.toString());
//                            VolleyLog.v("Response:%n %s", response.toString(4));
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
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



