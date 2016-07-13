package com.example.rssfeeder.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import com.example.rssfeeder.R;
import com.example.rssfeeder.app.Config;
import com.example.rssfeeder.ui.newsFeed.NewsFeedPresenterImp;
import com.example.rssfeeder.ui.newsFeed.NewsFeedViewImpl;

/**
 * Created by Андрей on 07.07.2016.
 */
public class AlertUtils
{
    public static void showSimpleAlert(String title, String detail, Context context)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(detail);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        alertDialog.show();
    }

    public static void showAlert(String title, String detail, Context context)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(detail);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        alertDialog.show();
    }

    public static void showAddSourceAlert(String title, String detail, Context context, final NewsFeedPresenterImp presenter)
    {
        // add RSS feed source to-do
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        final EditText input = new EditText(context);
        alert.setView(input).setTitle(title);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                String value = input.getText().toString().trim();
                Config.RssUrl = value;
                presenter.getRssList(0);
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }
}
