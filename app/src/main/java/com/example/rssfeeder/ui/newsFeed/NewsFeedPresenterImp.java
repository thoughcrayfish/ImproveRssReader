package com.example.rssfeeder.ui.newsFeed;

import com.android.volley.VolleyError;
import com.example.rssfeeder.repository.api.BroadcastObject;
import com.example.rssfeeder.repository.api.Urls;
import com.example.rssfeeder.repository.model.PostObject;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedPresenterImp implements NewsFeedPresenter, NewsFeedInteractor.onListGetListener
{
    private NewsFeedActivity view;
    private NewsFeedInteractorImp interactor;

    public NewsFeedPresenterImp(NewsFeedActivity view)
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
            interactor.getRssList(position, this);
        }
    }

    @Override
    public void onSuccess(BroadcastObject result)
    {
        // hide progress
        if (view != null)
        {
            view.hideProgress();
            view.loadList(result.getPostObjects());
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
