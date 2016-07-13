package com.example.rssfeeder.ui.newsFeed;

import com.android.volley.VolleyError;
import com.example.rssfeeder.app.Config;
import com.example.rssfeeder.repository.model.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedPresenterImp implements NewsFeedPresenter, NewsFeedInteractor.onListGetListener
{
    private NewsFeedViewImpl view;
    private NewsFeedInteractorImp interactor;

    public NewsFeedPresenterImp(NewsFeedViewImpl view)
    {
        this.view = view;
        interactor = new NewsFeedInteractorImp();
    }

    @Override
    public void getRssList(int position)
    {
        if (view != null)
        {
            // show progress
            view.showProgress();
            interactor.getRssList(position, Config.RssUrl, this);

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

    public void onError(VolleyError error)
    {
        // show error
        if (view != null)
        {
            view.hideProgress();
            view.showError();
        }
    }
}
