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

    public static void showAddSourceAlert(String title, String detail, Context context, final NewsFeedPresenterImp presenter)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
        builder .setView(input).setTitle(title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        String value = input.getText().toString().trim();
                        Urls.RssUrl = value;
                        presenter.getRssList(0);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                dialog.cancel();
                            }
                        });

        builder.create();
        builder.show();
    }


        // add RSS feed source to-do
//        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        final EditText input = new EditText(context);
//        alert.setView(input).setTitle(title);
//        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
//        {
//            public void onClick(DialogInterface dialog, int whichButton)
//            {
//                String value = input.getText().toString().trim();
//                Urls.RssUrl = value;
//                presenter.getRssList(0);
//            }
//        });
//
//        alert.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        dialog.cancel();
//                    }
//                });
//        alert.show();

}
