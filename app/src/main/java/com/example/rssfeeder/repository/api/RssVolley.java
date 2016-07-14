package com.example.rssfeeder.repository.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Андрей on 11.07.2016.
 */
public class RssVolley
{
    private static RequestQueue mRequestQueue;

    private RssVolley()
    {
        // no instances
    }

    public static void init(Context context)
    {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue()
    {
        if (mRequestQueue != null)
        {
            return mRequestQueue;
        }
        else
        {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static String getRequestURL(Map<String, String> params, String baseURL)      // Creating POST url
    {
        StringBuilder result = new StringBuilder();
        result.append(baseURL);
        result.append('?');

        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        try
        {
            boolean first = true;
            for (String key : keys)
            {
                if (!first)
                {
                    result.append('&');
                }
                result.append(key);
                result.append('=');
                String value = params.get(key);
                result.append(URLEncoder.encode(value, "UTF-8"));
                first = false;
            }
        } catch (Exception ignore)
        {

        }
        return result.toString();
    }

    public interface volleyCallbacks<T>
    {

        void onSuccess(T result);

        void onError(String error, Long timeout, int... code);

    }

    public static Map<String, String> getDefaultHeaders(Map<String, String> headers)
    {
        if (headers == null)
            headers = new HashMap<>();

        headers.put("lang", Locale.getDefault().getLanguage());
        return headers;
    }
    public void cancelPendingRequests(Object tag)
    {
        if (mRequestQueue != null)
        {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void addRequest(Request<?> request, String tag)
    {
        request.setTag(tag);
        addRequest(request);
    }

    public static void addRequest(Request<?> request)
    {
        getRequestQueue().add(request);
    }
}
