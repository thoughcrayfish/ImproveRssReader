package com.example.rssfeeder.ui.newsFeed;

import com.android.volley.VolleyError;
import com.example.rssfeeder.repository.model.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public interface NewsFeedInteractor
{
    interface onListGetListener
    {
        void onSuccess(List<RssItem> feedList);
        void onError(VolleyError error);
    }

    void getRssList(int position, String url, onListGetListener listener);
}
