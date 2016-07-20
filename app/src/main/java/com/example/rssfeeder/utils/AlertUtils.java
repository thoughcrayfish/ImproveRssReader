package com.example.rssfeeder.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import com.example.rssfeeder.repository.api.Urls;
import com.example.rssfeeder.ui.newsFeed.NewsFeedPresenterImp;

/**
 * Created by Андрей on 07.07.2016.
 */
public class AlertUtils
{
    public static void showSimpleAlert(String title, String detail, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(detail)
                .setPositiveButton("Ok", null)
                .setTitle(title);
        builder.create();
        builder.show();
    }

    // possibly more typles of alerts
}
