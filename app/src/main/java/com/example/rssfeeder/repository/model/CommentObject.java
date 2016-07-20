package com.example.rssfeeder.repository.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Андрей on 19.07.2016.
 */
public class CommentObject
{
    @SerializedName("id") private int id;
    @SerializedName("text") private String commentText;
    @SerializedName("user") private User user;
}
