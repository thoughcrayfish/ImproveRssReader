package com.example.rssfeeder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.rssfeeder.R;
import com.example.rssfeeder.repository.model.ImageObject;
import com.example.rssfeeder.repository.model.PostObject;
import com.example.rssfeeder.utils.CircleTransform;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    List<PostObject> objects;
    Context mContext;
    String postTypeString;

    RecyclerView.ViewHolder viewHolder;
    private static LayoutInflater inflater = null;

    public void onBindViewHolder(RecyclerView.ViewHolder customViewHolder, int i)
    {
        PostObject postObject = objects.get(i);
        if (customViewHolder instanceof FeedViewHolder)
        {
            // header content
           ((FeedViewHolder) customViewHolder).userFirstName.setText(postObject.getUser().getFirstName() + " " + postObject.getUser().getSecondName());
            ((FeedViewHolder) customViewHolder).pregnancyDuration.setText(String.valueOf(postObject.getUser().getCurrentWeek()) + " недели");
            // user image
            Glide.with(mContext)
                    .load(postObject.getUser().getUserPicture())
                    .centerCrop()
                    .placeholder(R.drawable.ic_testlogo)
                    .transform(new CircleTransform(mContext))
                    .into(((FeedViewHolder) customViewHolder).userPicture);
            // footer content
            ((FeedViewHolder) customViewHolder).likesQuantity.setText(postObject.getLikeQuantity());
            ((FeedViewHolder) customViewHolder).commentsQuantity.setText(postObject.getCommentQuantity());
            // text post content
            ((FeedViewHolder) customViewHolder).title.setText(postObject.getTitle());
            ((FeedViewHolder) customViewHolder).detail.setText(postObject.getDetail());
        }
        if (customViewHolder instanceof FeedGalleryViewHolder)
        {
            // footer content
            ((FeedGalleryViewHolder) customViewHolder).likesQuantity.setText(postObject.getLikeQuantity());
            ((FeedGalleryViewHolder) customViewHolder).commentsQuantity.setText(postObject.getCommentQuantity());
            // header content
            ((FeedGalleryViewHolder) customViewHolder).userFirstName.setText(postObject.getUser().getFirstName() + " " + postObject.getUser().getSecondName());
            ((FeedGalleryViewHolder) customViewHolder).pregnancyDuration.setText(String.valueOf(postObject.getUser().getCurrentWeek()) + " недели");
            // gallery post content
            ImageObject[] imagesArray = postObject.getImagesArray();
            // user image
            Glide.with(mContext)
                    .load(postObject.getUser().getUserPicture())
                    .fitCenter()
                    .placeholder(R.drawable.ic_testlogo)
                    .transform(new CircleTransform(mContext))
                    .into(((FeedGalleryViewHolder) customViewHolder).userPicture);
            if (imagesArray != null && imagesArray.length == 1)
            {
                String imageURL = imagesArray[0].getImageURL();
                ((FeedGalleryViewHolder) customViewHolder).rootOnePicture.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imageURL)
                        .centerCrop()
                        .placeholder(R.drawable.ic_testlogo)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostOnePicture);
            }
            if (imagesArray != null && imagesArray.length == 2)
            {
                String imageURL1 = imagesArray[0].getImageURL(); String imageURL2 = imagesArray[1].getImageURL();
                ((FeedGalleryViewHolder) customViewHolder).rootTwoPictures.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imageURL1)
                        .fitCenter()
                        .placeholder(R.drawable.ic_testlogo)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostTwoPictures_1);
                Glide.with(mContext)
                        .load(imageURL2)
                        .fitCenter()
                        .placeholder(R.drawable.ic_testlogo)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostTwoPictures_2);
            }
            if (imagesArray != null && imagesArray.length > 2)
            {
                String imageURL1 = imagesArray[0].getImageURL(); String imageURL2 = imagesArray[1].getImageURL(); String imageURL3 = imagesArray[2].getImageURL();
                ((FeedGalleryViewHolder) customViewHolder).rootMorePictures.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imageURL1)
                        .fitCenter()
                        .placeholder(R.drawable.ic_testlogo)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_1);
                Glide.with(mContext)
                        .load(imageURL2)
                        .fitCenter()
                        .placeholder(R.drawable.ic_testlogo)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_2);
                Glide.with(mContext)
                        .load(imageURL3)
                        .fitCenter()
                        .placeholder(R.drawable.ic_testlogo)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_3);
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view;
        PostObject postObject = objects.get(i);
        postTypeString = postObject.getType();

        if (postTypeString.equals("text"))
        {
            view = inflater.inflate(R.layout.postobject_textcontent, viewGroup, false);
            viewHolder = new FeedViewHolder(view);
        }
        else if (postTypeString.equals("gallery"))
        {
            view = inflater.inflate(R.layout.postobject_gallerycontent, viewGroup, false);
            viewHolder = new FeedGalleryViewHolder(view);
        }

        return viewHolder;
    }

    public FeedAdapter(Context context, List<PostObject> postObjectList)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        objects = postObjectList;
    }

    public int getItemCount()
    {
        return (null != objects ? objects.size() : 0);
    }
}
