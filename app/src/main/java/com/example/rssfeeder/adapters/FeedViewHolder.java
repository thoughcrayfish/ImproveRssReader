package com.example.rssfeeder.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rssfeeder.R;

/**
 * Created by Андрей on 04.07.2016.
 */
public class FeedViewHolder extends RecyclerView.ViewHolder
{

    protected TextView textView;

    public FeedViewHolder(View view)
    {
        super(view);

        this.textView = (TextView) view.findViewById(R.id.title);

        // rounding the image

    }
}
