package com.example.rssfeeder.ui.newsFeed;

import com.example.rssfeeder.repository.model.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public interface NewsFeedView
{
    public void loadList(List<RssItem> feedList);
}
