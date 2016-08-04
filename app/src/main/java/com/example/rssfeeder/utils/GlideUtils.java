package com.example.rssfeeder.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rssfeeder.R;

/**
 * Created by Андрей on 04.08.2016.
 */
public class GlideUtils
{
    public static void loadRoundPicture(Context context, String URL, ImageView imageView)
    {
        Glide.with(context)
                .load(URL)
                .centerCrop()
                .placeholder(R.drawable.ic_imageplaceholder)
                .transform(new CircleTransform(context))
                .into(imageView);
    }

    public static void loadNewsPostPicture(Context context, String URL, ImageView imageView)
    {
        Glide.with(context)
                .load(URL)
                .fitCenter()
                .placeholder(R.drawable.ic_imageplaceholder)
                .into(imageView);
    }

}
