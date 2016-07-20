package com.example.rssfeeder.repository.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Андрей on 19.07.2016.
 */
public class ImageObject
{
    @SerializedName("title") private String imageTitle;
    @SerializedName("url")private String imageURL;
    @SerializedName("width") private String imageWidth;
    @SerializedName("height") private String imageHeight;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }
}
