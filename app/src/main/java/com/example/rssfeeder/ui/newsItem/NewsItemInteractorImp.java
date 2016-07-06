package com.example.rssfeeder.ui.newsItem;

import com.example.rssfeeder.model.AsyncRSSTask;
import com.example.rssfeeder.data.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsItemInteractorImp implements INewsItemInteractor
{
    String parsingResult;

    @Override
    public void getRssList(int position, final onListGetListener listener, String url)
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
