package com.example.rssfeeder.ui.newsFeed;

import com.example.rssfeeder.model.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedPresenterImp implements NewsFeedPresenter, NewsFeedInteractor.onListGetListener
{
    private String url;
    private NewsFeedViewImpl view;
    private NewsFeedInteractorImp interactor;

    public NewsFeedPresenterImp(NewsFeedViewImpl view)
    {
        this.view = view;
        interactor = new NewsFeedInteractorImp();
    }

    @Override
    public void getRssList(int position, String url)
    {
        if (view != null)
        {
            // show progress
            view.showProgress();
            interactor.getRssList(position, url, this);

        }
    }

    @Override
    public void onSuccess(List<RssItem> result)
    {
        // hide progress
        if (view != null)
        {
            view.hideProgress();
            view.loadList(result);
        }

    }

    public void onError(String error)
    {
        // show error
        if (view != null)
        {
            view.hideProgress();
            view.showError();
        }
    }
}
