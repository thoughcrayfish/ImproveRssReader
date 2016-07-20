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
    public static final int TYPE_TEXT_POST = 0;
    public static final int TYPE_GALLERY_POST = 1;
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
                    .placeholder(R.drawable.ic_imageplaceholder)
                    .transform(new CircleTransform(mContext))
                    .into(((FeedViewHolder) customViewHolder).userPicture);
            // footer content
            ((FeedViewHolder) customViewHolder).dateCreated.setText(postObject.getTimeCreated());
            ((FeedViewHolder) customViewHolder).likesQuantity.setText(postObject.getLikeQuantity());
            ((FeedViewHolder) customViewHolder).commentsQuantity.setText(postObject.getCommentQuantity());
            // text post content
            ((FeedViewHolder) customViewHolder).title.setText(postObject.getTitle());
            ((FeedViewHolder) customViewHolder).detail.setText(postObject.getDetail());
        }
        if (customViewHolder instanceof FeedGalleryViewHolder)
        {
            // text post content
            ((FeedGalleryViewHolder) customViewHolder).title.setText(postObject.getTitle());
            ((FeedGalleryViewHolder) customViewHolder).detail.setText(postObject.getDetail());
            // footer content
            ((FeedGalleryViewHolder) customViewHolder).dateCreated.setText(postObject.getTimeCreated());
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
                    .placeholder(R.drawable.ic_imageplaceholder)
                    .transform(new CircleTransform(mContext))
                    .into(((FeedGalleryViewHolder) customViewHolder).userPicture);
            if (imagesArray != null && imagesArray.length == 1)
            {
                String imageURL = imagesArray[0].getImageURL();
                ((FeedGalleryViewHolder) customViewHolder).rootOnePicture.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imageURL)
                        .centerCrop()
                        .placeholder(R.drawable.ic_imageplaceholder)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostOnePicture);
            }
            if (imagesArray != null && imagesArray.length == 2)
            {
                String imageURL1 = imagesArray[0].getImageURL(); String imageURL2 = imagesArray[1].getImageURL();
                ((FeedGalleryViewHolder) customViewHolder).rootTwoPictures.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imageURL1)
                        .fitCenter()
                        .placeholder(R.drawable.ic_imageplaceholder)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostTwoPictures_1);
                Glide.with(mContext)
                        .load(imageURL2)
                        .fitCenter()
                        .placeholder(R.drawable.ic_imageplaceholder)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostTwoPictures_2);
            }
            if (imagesArray != null && imagesArray.length > 2)
            {
                String imageURL1 = imagesArray[0].getImageURL(); String imageURL2 = imagesArray[1].getImageURL(); String imageURL3 = imagesArray[2].getImageURL();
                ((FeedGalleryViewHolder) customViewHolder).rootMorePictures.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .load(imageURL1)
                        .centerCrop()
                        .placeholder(R.drawable.ic_imageplaceholder)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_1);
                Glide.with(mContext)
                        .load(imageURL2)
                        .centerCrop()
                        .placeholder(R.drawable.ic_imageplaceholder)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_2);
                Glide.with(mContext)
                        .load(imageURL3)
                        .fitCenter()
                        .placeholder(R.drawable.ic_imageplaceholder)
                        .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_3);
            }
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        int pos = TYPE_TEXT_POST;
        PostObject postObject = objects.get(position);
        postTypeString = postObject.getType();
        if (postTypeString.equals("text")) pos = TYPE_TEXT_POST;
        if (postTypeString.equals("gallery")) pos = TYPE_GALLERY_POST;

        return pos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view;

//        PostObject postObject = objects.get(i);
        switch (viewType)
        {
            case TYPE_TEXT_POST:
                view = inflater.inflate(R.layout.postobject_textcontent, viewGroup, false);
                viewHolder = new FeedViewHolder(view);
                return viewHolder;
            case TYPE_GALLERY_POST:
                view = inflater.inflate(R.layout.postobject_gallerycontent, viewGroup, false);
                viewHolder = new FeedGalleryViewHolder(view);
                return viewHolder;
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
