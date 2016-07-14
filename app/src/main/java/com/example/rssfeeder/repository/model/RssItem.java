package com.example.rssfeeder.repository.model;

import android.media.Image;
import android.nfc.Tag;
import android.widget.EditText;

/**
 * Created by Андрей on 04.07.2016.
 */
public class RssItem
{
    private String title;
    private String detail;

    private String type;
    private int commentQuantity;
    private int likeQuantity;
    private User user;

    public RssItem() {
    }

    public void setTitle(String _title)
    {
        this.title = _title;
    }

    public void setDetail(String _detail)
    {
        this.detail = _detail;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDetail()
    {
        return detail;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getCommentQuantity()
    {
        return commentQuantity;
    }

    public void setCommentQuantity(int commentQuantity)
    {
        this.commentQuantity = commentQuantity;
    }

    public int getLikeQuantity()
    {
        return likeQuantity;
    }

    public void setLikeQuantity(int likeQuantity)
    {
        this.likeQuantity = likeQuantity;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
