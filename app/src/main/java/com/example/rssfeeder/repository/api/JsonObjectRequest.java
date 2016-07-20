package com.example.rssfeeder.repository.api;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Андрей on 12.07.2016.
 */
public class JsonObjectRequest extends com.android.volley.toolbox.JsonRequest<JSONArray>
{

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse networkResponse)
    {
        try
        {
            String jsonString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));

            return Response.success(jsonString.isEmpty() ? null : new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        } catch (JSONException je)
        {
            return Response.error(new ParseError(je));
        }
    }

    public JsonObjectRequest(int method, String url, String requestBody, final JsonCallback callback)
    {
        super(method, url, requestBody, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {

                callback.onSuccess(response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                callback.onError("fail");
            }
        });
    }

    public interface JsonCallback
    {
        void onSuccess(JSONArray result);
        void onError(String error);
    }
}
