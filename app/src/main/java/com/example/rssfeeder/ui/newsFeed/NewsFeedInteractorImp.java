package com.example.rssfeeder.ui.newsFeed;

import com.example.rssfeeder.model.AsyncRSSTask;
import com.example.rssfeeder.model.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedInteractorImp implements NewsFeedInteractor
{
    @Override
    public void getRssList(int position, String url, final onListGetListener listener)
    {



        new AsyncRSSTask(new AsyncRSSTask.callBack()
        {
            @Override
            public void onSuccess(List<RssItem> result)
            {
                listener.onSuccess(result);
            }

            @Override
            public void onError(String error)
            {
                listener.onError("error");
            }
        }).execute(url);
//       // get RSS callback

    }

}
