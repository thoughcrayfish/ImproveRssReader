package com.example.rssfeeder.ui.newsFeedDetail;

/**
 * Created by Андрей on 02.08.2016.
 */
public interface NewsFeedDetailInteractor
{
    interface onDetailGetListener
    {
        void onSuccess(/* feedDetailObject goes here */);
        void onError(/* error goes here */);
    }

    void getFeedDetail(int position, onDetailGetListener listener);
}
