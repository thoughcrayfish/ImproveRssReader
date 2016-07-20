package com.example.rssfeeder.repository.api;

import com.example.rssfeeder.repository.model.PostObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Андрей on 15.07.2016.
 */
public class BroadcastObject
{
    @SerializedName("error") int error;
    @SerializedName("data") ArrayList<PostObject> postObjects;
    @SerializedName("last_id") String lastPostID;
    @SerializedName("last_type") String lastType;

    public ArrayList<PostObject> getPostObjects()
    {
        return postObjects;
    }
}
