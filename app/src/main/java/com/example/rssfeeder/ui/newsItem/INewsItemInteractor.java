package com.example.rssfeeder.ui.newsItem;

import com.example.rssfeeder.data.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public interface INewsItemInteractor
{
    interface onListGetListener
    {
        void onSuccess(List<RssItem> feedList);
        void onError(String error);
    }

    void getRssList(int position, onListGetListener listener, String url);
}
