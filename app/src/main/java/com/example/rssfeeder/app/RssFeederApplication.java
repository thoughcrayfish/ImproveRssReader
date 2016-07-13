package com.example.rssfeeder.app;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.rssfeeder.repository.api.RssVolley;

/**
 * Created by Андрей on 11.07.2016.
 */
public class RssFeederApplication extends Application
{
    public static final String TAG = RssFeederApplication.class.getSimpleName();
    private static RssFeederApplication instance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;

    }

    private void initVolley()
    {
        RssVolley.init(this);
    }
    public static RssFeederApplication getInstance()
    {
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
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
        getInstance().getRequestQueue().add(request);
    }

}
