package com.example.rssfeeder.repository.api;

import android.content.Context;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.rssfeeder.R;
import com.example.rssfeeder.app.RssFeederApplication;
import com.example.rssfeeder.repository.model.ErrorObject;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Андрей on 11.07.2016.
 */
public class ErrorsWorker
{
    public static String getNetworkError(int code)
    {
        Context context = RssFeederApplication.getInstance();
        switch (code)
        {
            case 400:
                return context.getResources().getString(R.string.http_error_400);
            case 401:
                return context.getResources().getString(R.string.http_error_401);
            case 403:
                return context.getResources().getString(R.string.http_error_403);
            case 404:
                return context.getResources().getString(R.string.http_error_404);
            case 405:
                return context.getResources().getString(R.string.http_error_405);
            case 408:
                return context.getResources().getString(R.string.http_error_408);
            case 500:
                return context.getResources().getString(R.string.http_error_500);
            case 502:
                return context.getResources().getString(R.string.http_error_502);
            case 503:
                return context.getResources().getString(R.string.http_error_503);
            case 504:
                return context.getResources().getString(R.string.http_error_504);
            case (1000):
                return context.getResources().getString(R.string.http_no_internet);
            default:
                return context.getResources().getString(R.string.http_error_500);
        }
    }
    public static long getDuration(String textDur)
    {
        String time = textDur.substring(2);
        long duration = 0L;
        Object[][] indexs = new Object[][]{{"H", 3600}, {"M", 60}, {"S", 1}};
        for (int i = 0; i < indexs.length; i++)
        {
            int index = time.indexOf((String) indexs[i][0]);
            if (index != -1)
            {
                String value = time.substring(0, index);
                duration += Integer.parseInt(value) * (int) indexs[i][1] * 1000;
                time = time.substring(value.length() + 1);
            }
        }
        return duration / 1000L;
    }
}
