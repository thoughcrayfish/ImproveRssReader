package com.example.rssfeeder.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rssfeeder.R;

/**
 * Created by Андрей on 19.07.2016.
 */
public class FeedGalleryViewHolder extends RecyclerView.ViewHolder
{
    protected TextView likesQuantity;
    protected TextView commentsQuantity;
    protected TextView userFirstName;
    protected TextView pregnancyDuration;
    protected ImageView userPicture;

    protected ImageView galleryPostOnePicture;
    protected ImageView galleryPostTwoPictures_1;
    protected ImageView galleryPostTwoPictures_2;
    protected ImageView galleryPostThreePictures_1;
    protected ImageView galleryPostThreePictures_2;
    protected ImageView galleryPostThreePictures_3;
    protected LinearLayout rootOnePicture;
    protected LinearLayout rootTwoPictures;
    protected LinearLayout rootMorePictures;

        public FeedGalleryViewHolder(View view)
        {
            super(view);

            this.galleryPostOnePicture = (ImageView) view.findViewById(R.id.imageView_onePicture);
            this.galleryPostTwoPictures_1 = (ImageView) view.findViewById(R.id.imageView_twoPictures_1);
            this.galleryPostTwoPictures_2 = (ImageView) view.findViewById(R.id.imageView_twoPictures_2);
            this.galleryPostThreePictures_1 = (ImageView) view.findViewById(R.id.imageView_threePictures_1);
            this.galleryPostThreePictures_2 = (ImageView) view.findViewById(R.id.imageView_threePictures_2);
            this.galleryPostThreePictures_3 = (ImageView) view.findViewById(R.id.imageView_threePictures_3);

            this.likesQuantity = (TextView) view.findViewById(R.id.editText_footer_likeQuantity);
            this.commentsQuantity = (TextView) view.findViewById(R.id.editText_footer_commentsQuantity);
            this.userFirstName = (TextView) view.findViewById(R.id.textView_header_userName);
            this.pregnancyDuration = (TextView) view.findViewById(R.id.textView_header_pregnancyDuration);
            this.userPicture = (ImageView) view.findViewById(R.id.imageView_header_userPic);

            this.rootOnePicture = (LinearLayout) view.findViewById(R.id.linearLayout_onePicture);
            this.rootTwoPictures = (LinearLayout) view.findViewById(R.id.linearLayout_twoPictures);
            this.rootMorePictures = (LinearLayout) view.findViewById(R.id.linearLayout_threePictures);
        }

}
