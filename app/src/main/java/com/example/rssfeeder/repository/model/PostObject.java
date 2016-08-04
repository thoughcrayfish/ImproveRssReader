package com.example.rssfeeder.repository.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Андрей on 04.07.2016.
 */
public class PostObject
{
    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("text") private String detail;
    @SerializedName("type") private String type;
    @SerializedName("comment_qty") private String commentQuantity;
    @SerializedName("like_qty") private String likeQuantity;
    @SerializedName("user_id") private int userID;
    @SerializedName("user") private UserObject userObject;
    @SerializedName("comments_array") private CommentObject[] postComments;
    @SerializedName("images") private ImageObject[] imagesArray;
    @SerializedName("created") private String timeCreated;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getCommentQuantity()
    {
        return commentQuantity;
    }
    public void setCommentQuantity(String commentQuantity)
    {
        this.commentQuantity = commentQuantity;
    }
    public String getLikeQuantity()
    {
        return likeQuantity;
    }
    public void setLikeQuantity(String likeQuantity)
    {
        this.likeQuantity = likeQuantity;
    }
    public int getUserID()
    {
        return userID;
    }
    public void setUserID(int userID)
    {
        this.userID = userID;
    }
    public UserObject getUserObject()
    {
        return userObject;
    }
    public void setUserObject(UserObject userObject)
    {
        this.userObject = userObject;
    }


    public ImageObject[] getImagesArray() {
        return imagesArray;
    }

    public void setImagesArray(ImageObject[] imagesArray) {
        this.imagesArray = imagesArray;
    }

    public String getTimeCreated()
    {
        long dv = Long.valueOf(timeCreated)*1000;// its need to be in milisecond
        Date df = new java.util.Date(dv);
        String vv = new SimpleDateFormat("dd.MM.yyyy hh:mm a").format(df);
        return vv;
    }

    public void setTimeCreated(String timeCreated)
    {
        this.timeCreated = timeCreated;
    }



    public void setPostComments(CommentObject[] postComments) {
        this.postComments = postComments;
    }

    public CommentObject[] getPostComments()
    {
        return postComments;
    }
}
