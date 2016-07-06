package com.example.rssfeeder.data;

import android.media.Image;
import android.nfc.Tag;
import android.widget.EditText;

/**
 * Created by Андрей on 04.07.2016.
 */
public class RssItem
{
    private String title;
    private String thumbnail;
    private String description;
    private String articleImage;
    public String pubDate;
    public String link;

    public void setTitle(String _title)
    {
        this.title = _title;
    }

    public void setDescription(String _description)
    {
        this.description = _description;
    }

    public String getDescription() { return  description; }

    public void setThumbnail (String _thumbnail)
    {
        this.thumbnail = _thumbnail;
    }

    public String getThumbnail()
    {
        return thumbnail;
    }


    public String getTitle()
    {
        return title;
    }

    public void setLink(String link)
    {
        this.link = link;
    }
}
