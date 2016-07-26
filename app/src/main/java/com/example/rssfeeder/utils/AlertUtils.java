package com.example.rssfeeder.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

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
    public static void showToast(Context context, String text, int length)
    {
        Toast toast = Toast.makeText(context, text, length);
        toast.show();
    }
    public static void alertSingleChoice(final Context context, String title, String[] options){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set the dialog title
        builder.setTitle(title)

                .setSingleChoiceItems(options, 0, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        java.lang.String arg = String.valueOf(arg1) + "option selected";
                        showToast(context, arg, Toast.LENGTH_SHORT);
                    }

                })

                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // user clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        showToast(context, "selectedPosition: " + selectedPosition, Toast.LENGTH_SHORT);

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // removes the dialog from the screen

                    }
                })

                .show();

    }
    // possibly more typles of alerts
}
