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

    protected TextView title;
    protected TextView detail;
    protected TextView likesQuantity;
    protected TextView commentsQuantity;
    protected TextView userFirstName;
    protected TextView pregnancyDuration;
    protected ImageView userPicture;

    public FeedViewHolder(View view)
    {
        super(view);

        this.title = (TextView) view.findViewById(R.id.textView_feedTextPostTitle);
        this.detail = (TextView) view.findViewById(R.id.textView_feedTextPostDetail);
        this.likesQuantity = (TextView) view.findViewById(R.id.editText_footer_likeQuantity);
        this.commentsQuantity = (TextView) view.findViewById(R.id.editText_footer_commentsQuantity);
        this.userFirstName = (TextView) view.findViewById(R.id.textView_header_userName);
        this.pregnancyDuration = (TextView) view.findViewById(R.id.textView_header_pregnancyDuration);
        this.userPicture = (ImageView) view.findViewById(R.id.imageView_header_userPic);
    }
}
