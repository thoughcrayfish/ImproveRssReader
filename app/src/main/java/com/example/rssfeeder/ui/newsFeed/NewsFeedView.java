package com.example.rssfeeder.ui.newsFeed;

import com.example.rssfeeder.repository.model.PostObject;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public interface NewsFeedView
{
    void loadList(List<PostObject> result);
    void loadNewsFeedDetailActivity(String id);
}
