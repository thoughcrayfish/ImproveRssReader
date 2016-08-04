package com.example.rssfeeder.adapters;

import android.content.Context;
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
import com.example.rssfeeder.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

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
    private FeedViewHolder textViewHolder; private FeedGalleryViewHolder galleryViewHolder;
    private PostObject postObject = null;
    public void onBindViewHolder(RecyclerView.ViewHolder customViewHolder, int i)
    {
        postObject = objects.get(i);
        if (customViewHolder instanceof FeedViewHolder)
        {
            textViewHolder = (FeedViewHolder) customViewHolder;
            setHeader();
            GlideUtils.loadRoundPicture(mContext, postObject.getUserObject().getUserPicture(), textViewHolder.userPicture);
            setFooter();
            setTextPost();
            setComments(textViewHolder);
        }
        if (customViewHolder instanceof FeedGalleryViewHolder)
        {
            galleryViewHolder = (FeedGalleryViewHolder) customViewHolder;
            if (!galleryViewHolder.hasImage)
            {
                setTextPost();
                setFooter();
                setHeader();
                // gallery post content
                ImageObject[] imagesArray = postObject.getImagesArray();
                // user image
                GlideUtils.loadRoundPicture(mContext, postObject.getUserObject().getUserPicture(), galleryViewHolder.userPicture);
                if (imagesArray != null && imagesArray.length == 1)
                {
                    String imageURL = imagesArray[0].getImageURL();
                    galleryViewHolder.rootOnePicture.setVisibility(View.VISIBLE);
                    GlideUtils.loadNewsPostPicture(mContext, imageURL, galleryViewHolder.galleryPostOnePicture);
                    galleryViewHolder.hasImage = true;
                }
                if (imagesArray != null && imagesArray.length == 2)
                {
                    String imageURL1 = imagesArray[0].getImageURL();
                    String imageURL2 = imagesArray[1].getImageURL();
                    galleryViewHolder.rootTwoPictures.setVisibility(View.VISIBLE);
                    GlideUtils.loadNewsPostPicture(mContext, imageURL1, galleryViewHolder.galleryPostTwoPictures_1);
                    GlideUtils.loadNewsPostPicture(mContext, imageURL2, galleryViewHolder.galleryPostTwoPictures_2);
                    galleryViewHolder.hasImage = true;
                }
                if (imagesArray != null && imagesArray.length > 2)
                {
                    String imageURL1 = imagesArray[0].getImageURL();
                    String imageURL2 = imagesArray[1].getImageURL();
                    String imageURL3 = imagesArray[2].getImageURL();
                    galleryViewHolder.rootMorePictures.setVisibility(View.VISIBLE);
                    GlideUtils.loadNewsPostPicture(mContext, imageURL1, galleryViewHolder.galleryPostThreePictures_1);
                    GlideUtils.loadNewsPostPicture(mContext, imageURL2, galleryViewHolder.galleryPostThreePictures_2);
                    GlideUtils.loadNewsPostPicture(mContext, imageURL3, galleryViewHolder.galleryPostThreePictures_3);
                    galleryViewHolder.hasImage = true;
                }
            }
        }
    }
    private void setTextPost()
    {
        switch (type)
        {
            case TYPE_TEXT_POST:
                textViewHolder.title.setText(String.valueOf(postObject.getTitle()));
                textViewHolder.detail.setText(String.valueOf(postObject.getDetail()));
                return;
            case TYPE_GALLERY_POST:
                galleryViewHolder.title.setText(String.valueOf(postObject.getTitle()));
                galleryViewHolder.detail.setText(String.valueOf(postObject.getDetail()));
        }
    }

    private void setHeader()
    {
        switch (type)
        {
            case TYPE_TEXT_POST:
                textViewHolder.userFirstName.setText(postObject.getUserObject().getFirstName() + " " + postObject.getUserObject().getSecondName());
                textViewHolder.pregnancyDuration.setText(String.valueOf(postObject.getUserObject().getCurrentWeek()) + " недели");
                textViewHolder.buttonHeaderLike.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showToast(mContext, "Like added", Toast.LENGTH_SHORT);
                    }
                });
                return;
            case TYPE_GALLERY_POST:
                galleryViewHolder.userFirstName.setText(postObject.getUserObject().getFirstName() + " " + postObject.getUserObject().getSecondName());
                galleryViewHolder.pregnancyDuration.setText(String.valueOf(postObject.getUserObject().getCurrentWeek()) + " недели");
                galleryViewHolder.buttonHeaderLike.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showToast(mContext, "Like added", Toast.LENGTH_SHORT);
                    }
                });
        }
    }
    private void setFooter()
    {
        switch (type)
        {
            case TYPE_TEXT_POST:
                textViewHolder.dateCreated.setText(String.valueOf(postObject.getTimeCreated()));
                textViewHolder.likesQuantity.setText(String.valueOf(postObject.getLikeQuantity()));
                textViewHolder.commentsQuantity.setText(String.valueOf(postObject.getCommentQuantity()));
                textViewHolder.buttonFooterMore.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showSimpleAlert("Something", "blablabla", mContext);
                    }
                });
                return;
            case TYPE_GALLERY_POST:
                galleryViewHolder.dateCreated.setText(String.valueOf(postObject.getTimeCreated()));
                galleryViewHolder.likesQuantity.setText(String.valueOf(postObject.getLikeQuantity()));
                galleryViewHolder.commentsQuantity.setText(String.valueOf(postObject.getCommentQuantity()));
                galleryViewHolder.buttonFooterMore.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        AlertUtils.showSimpleAlert("Something", "blablabla", mContext);
                    }
                });
        }

    }
    private void setComments(RecyclerView.ViewHolder customViewHolder)
    {
       fillFiles(((FeedViewHolder) customViewHolder).layoutComments, mContext);
    }

    public void fillFiles(LinearLayout mFileLay, Context mContext)
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
                R.layout.item_comment, null);

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
                view = inflater.inflate(R.layout.item_textpost, viewGroup, false);
                viewHolder = new FeedViewHolder(view);
                return viewHolder;
            case TYPE_GALLERY_POST:
                view = inflater.inflate(R.layout.item_gallerypost, viewGroup, false);
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
