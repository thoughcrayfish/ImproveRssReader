package com.example.rssfeeder.ui.newsFeed;

import com.android.volley.VolleyError;
import com.example.rssfeeder.repository.model.BroadcastObject;

/**
 * Created by Андрей on 04.07.2016.
 */
public interface NewsFeedInteractor
{
    interface onListGetListener
    {
        void onSuccess(BroadcastObject object);
        void onError(VolleyError error);
    }

    void getRssList(int position, onListGetListener listener);
}
