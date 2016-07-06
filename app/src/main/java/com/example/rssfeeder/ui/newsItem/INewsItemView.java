package com.example.rssfeeder.ui.newsItem;

import com.example.rssfeeder.data.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public interface INewsItemView
{
    public void showProgress();
    public void hideProgress();
    public void showError(String error);
    public void loadList(List<RssItem> feedList);
}
