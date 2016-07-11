package com.example.rssfeeder.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.rssfeeder.utils.RssReader;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class AsyncRSSTask extends AsyncTask<String, Void, List<RssItem>>
{
    private callBack mCallBack;
    private static final String TAG = "RSS Reader";
    List<RssItem> feedsList;

    public AsyncRSSTask(callBack callBack)
    {
        mCallBack = callBack;
    }
    @Override
    protected List<RssItem> doInBackground(String... urls)
    {

        // Debug the task thread name
        Log.d("ITCRssReader", Thread.currentThread().getName());

        try
        {
            // Create RSS reader
            RssReader rssReader = new RssReader(urls[0]);

            // Parse RSS, get items
            return rssReader.getItems();

        } catch (Exception e) {
            Log.e("ITCRssReader", e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<RssItem> result) {

        // Create a list adapter
        List<RssItem> resultList = result;
        if (resultList == null)
        {
            mCallBack.onError("nothing");
        }
        if (resultList != null)
        {
            for (int i = 0; i < resultList.size(); i++)
            {

                RssItem item = new RssItem();

//                item.setThumbnail(post.optString("thumbnail"));
            }
            mCallBack.onSuccess(resultList);
        }
    }


    public interface callBack
    {
        void onSuccess(List<RssItem> result);
        void onError(String error);
    }

}
