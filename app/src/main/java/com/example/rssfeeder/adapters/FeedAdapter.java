package com.example.rssfeeder.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rssfeeder.R;
import com.example.rssfeeder.repository.model.CommentObject;
import com.example.rssfeeder.repository.model.ImageObject;
import com.example.rssfeeder.repository.model.PostObject;
import com.example.rssfeeder.ui.newsFeed.NewsFeedActivity;
import com.example.rssfeeder.utils.AlertUtils;
import com.example.rssfeeder.utils.CircleTransform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Андрей on 04.07.2016.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener
{
    private NewsFeedActivity newsFeedView;
    List<PostObject> objects;
    Context mContext;
    String postTypeString;
    public static final int TYPE_TEXT_POST = 0;
    public static final int TYPE_GALLERY_POST = 1;
    private int type = 2;
    RecyclerView.ViewHolder viewHolder;
    private static LayoutInflater inflater = null;
    public void onBindViewHolder(RecyclerView.ViewHolder customViewHolder, int i)
    {

        PostObject postObject = objects.get(i);
        if (customViewHolder instanceof FeedViewHolder)
        {
            // header content
            setHeader(postObject, customViewHolder);
            // user image
            Glide.with(mContext)
                    .load(postObject.getUserObject().getUserPicture())
                    .centerCrop()
                    .placeholder(R.drawable.ic_imageplaceholder)
                    .transform(new CircleTransform(mContext))
                    .into(((FeedViewHolder) customViewHolder).userPicture);
            // footer content
            setFooter(postObject, customViewHolder);
            // text post content
            setTextPost(postObject, customViewHolder);
            // text post comments
            setComments(postObject , customViewHolder);
        }
        if (customViewHolder instanceof FeedGalleryViewHolder)
        {
            if (!((FeedGalleryViewHolder) customViewHolder).hasImage)
            {
                // text post content
                setTextPost(postObject, customViewHolder);
                // footer content
                setFooter(postObject, customViewHolder);
                // header content
                setHeader(postObject, customViewHolder);
                // gallery post content
                ImageObject[] imagesArray = postObject.getImagesArray();
                // user image
                Glide.with(mContext)
                        .load(postObject.getUserObject().getUserPicture())
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
                    ((FeedGalleryViewHolder) customViewHolder).hasImage = true;
                }
                if (imagesArray != null && imagesArray.length == 2)
                {
                    String imageURL1 = imagesArray[0].getImageURL();
                    String imageURL2 = imagesArray[1].getImageURL();
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
                    ((FeedGalleryViewHolder) customViewHolder).hasImage = true;
                }
                if (imagesArray != null && imagesArray.length > 2)
                {
                    String imageURL1 = imagesArray[0].getImageURL();
                    String imageURL2 = imagesArray[1].getImageURL();
                    String imageURL3 = imagesArray[2].getImageURL();
                    ((FeedGalleryViewHolder) customViewHolder).rootMorePictures.setVisibility(View.VISIBLE);
                    Glide.with(mContext)
                            .load(imageURL1)
                            .fitCenter()
                            .placeholder(R.drawable.ic_imageplaceholder)
                            .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_1);
                    Glide.with(mContext)
                            .load(imageURL2)
                            .fitCenter()
                            .placeholder(R.drawable.ic_imageplaceholder)
                            .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_2);
                    Glide.with(mContext)
                            .load(imageURL3)
                            .fitCenter()
                            .placeholder(R.drawable.ic_imageplaceholder)
                            .into(((FeedGalleryViewHolder) customViewHolder).galleryPostThreePictures_3);
                    ((FeedGalleryViewHolder) customViewHolder).hasImage = true;
                }
            }
        }
    }
    private void setTextPost(PostObject postObject, RecyclerView.ViewHolder customViewHolder)
    {
        switch (type)
        {
            case TYPE_TEXT_POST:
                ((FeedViewHolder) customViewHolder).title.setText(String.valueOf(postObject.getTitle()));
                ((FeedViewHolder) customViewHolder)
                        .detail.setText(String.valueOf(postObject.getDetail()));
                return;
            case TYPE_GALLERY_POST:
                ((FeedGalleryViewHolder) customViewHolder).title.setText(String.valueOf(postObject.getTitle()));
                ((FeedGalleryViewHolder) customViewHolder).detail.setText(String.valueOf(postObject.getDetail()));
        }
    }

    private void setHeader(PostObject postObject, RecyclerView.ViewHolder customViewHolder)
    {
        switch (type)
        {
            case TYPE_TEXT_POST:
                ((FeedViewHolder) customViewHolder).userFirstName.setText(postObject.getUserObject().getFirstName() + " " + postObject.getUserObject().getSecondName());
                ((FeedViewHolder) customViewHolder).pregnancyDuration.setText(String.valueOf(postObject.getUserObject().getCurrentWeek()) + " недели");
                ((FeedViewHolder) customViewHolder).buttonHeaderLike.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showToast(mContext, "Like added", Toast.LENGTH_SHORT);
                    }
                });
                return;
            case TYPE_GALLERY_POST:
                ((FeedGalleryViewHolder) customViewHolder).userFirstName.setText(postObject.getUserObject().getFirstName() + " " + postObject.getUserObject().getSecondName());
                ((FeedGalleryViewHolder) customViewHolder).pregnancyDuration.setText(String.valueOf(postObject.getUserObject().getCurrentWeek()) + " недели");
                ((FeedGalleryViewHolder) customViewHolder).buttonHeaderLike.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showToast(mContext, "Like added", Toast.LENGTH_SHORT);
                    }
                });
        }
    }
    private void setFooter(PostObject postObject, RecyclerView.ViewHolder customViewHolder)
    {
        switch (type)
        {
            case TYPE_TEXT_POST:
                ((FeedViewHolder) customViewHolder).dateCreated.setText(String.valueOf(postObject.getTimeCreated()));
                ((FeedViewHolder) customViewHolder).likesQuantity.setText(String.valueOf(postObject.getLikeQuantity()));
                ((FeedViewHolder) customViewHolder).commentsQuantity.setText(String.valueOf(postObject.getCommentQuantity()));
                ((FeedViewHolder) customViewHolder).buttonFooterMore.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showSimpleAlert("Something", "blablabla", mContext);
                    }
                });
                return;
            case TYPE_GALLERY_POST:
                ((FeedGalleryViewHolder) customViewHolder).dateCreated.setText(String.valueOf(postObject.getTimeCreated()));
                ((FeedGalleryViewHolder) customViewHolder).likesQuantity.setText(String.valueOf(postObject.getLikeQuantity()));
                ((FeedGalleryViewHolder) customViewHolder).commentsQuantity.setText(String.valueOf(postObject.getCommentQuantity()));
                ((FeedGalleryViewHolder) customViewHolder).buttonFooterMore.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showSimpleAlert("Something", "blablabla", mContext);
                    }
                });
        }

    }
    private void setComments(PostObject postObject, RecyclerView.ViewHolder customViewHolder)
    {
       fillFiles(((FeedViewHolder) customViewHolder).layoutComments, postObject, mContext);
    }

    public void fillFiles(LinearLayout mFileLay, PostObject postObject, Context mContext)
    {
        CommentObject[] commentObjects = postObject.getPostComments();
        if (commentObjects != null)
        {
            List<View> mFileList = new ArrayList<>(commentObjects.length);
            for (int i = 0; i < commentObjects.length; i++)
            {
                mFileList.add(setItemStyle(String.valueOf(commentObjects[i].getUserObject().getFirstName()), String.valueOf(commentObjects[i].getCommentText()), String.valueOf(commentObjects[i].getUserObject().getUserPicture()),mContext));
            }
            mFileLay.removeAllViews();

            for (View view : mFileList)
            {
                mFileLay.addView(view);
            }
            mFileLay.invalidate();
        }
    }

    private RelativeLayout setItemStyle(String name, String body, String userImageURL, Context mContext)
    {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(
                R.layout.feed_postobject_comment, null);

        // Setting Author string
        TextView textViewAuthor = (TextView) layout.findViewById(R.id.textView_commentAuthor);
        textViewAuthor.setText(Html.fromHtml(name).toString());
        // Setting Comment body text
        TextView textViewBody = (TextView) layout.findViewById(R.id.textView_commentBody);
        textViewBody.setText(Html.fromHtml(body).toString());

        ImageView userImage = (ImageView) layout.findViewById(R.id.imageView_commentAuthorImage);
        Glide.with(mContext)
                .load(userImageURL)
                .centerCrop()
                .placeholder(R.drawable.ic_imageplaceholder)
                .transform(new CircleTransform(mContext))
                .into(userImage);
//        textViewBody.setPaintFlags(textViewBody.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textViewAuthor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //comment button pressed logic
            }
        });
        return layout;
    }

    // defining post type
    @Override
    public int getItemViewType(int position)
    {
        int pos = TYPE_TEXT_POST;
        PostObject postObject = objects.get(position);
        postTypeString = postObject.getType();
        if (postTypeString.equals("text")) pos = TYPE_TEXT_POST; type = pos;
        if (postTypeString.equals("gallery")) pos = TYPE_GALLERY_POST; type = pos;

        return pos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = null;
        switch (viewType)
        {
            case TYPE_TEXT_POST:
                view = inflater.inflate(R.layout.feed_postobject_textcontent, viewGroup, false);
                viewHolder = new FeedViewHolder(view);
                return viewHolder;
            case TYPE_GALLERY_POST:
                view = inflater.inflate(R.layout.feed_postobject_gallerycontent, viewGroup, false);
                viewHolder = new FeedGalleryViewHolder(view);
                return viewHolder;
        }
        view.setOnClickListener(this);
        return viewHolder;
    }

    public FeedAdapter(NewsFeedActivity newsFeedView, Context context, List<PostObject> postObjectList)
    {
        this.newsFeedView = newsFeedView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        objects = postObjectList;
    }

    public int getItemCount()
    {
        return (null != objects ? objects.size() : 0);
    }

    @Override
    public void onClick(View view)
    {
        PostObject object = objects.get(viewHolder.getPosition());
        String id = String.valueOf(object.getId());
        newsFeedView.loadNewsFeedDetailActivity(id);
    }

}
