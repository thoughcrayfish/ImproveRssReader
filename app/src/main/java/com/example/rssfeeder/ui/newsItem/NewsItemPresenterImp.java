package com.example.rssfeeder.ui.newsItem;

import com.example.rssfeeder.data.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsItemPresenterImp implements INewsItemPresenter, INewsItemInteractor.onListGetListener
{
    private String url;
    private NewsItemActivity view;
    private NewsItemInteractorImp interactor;

    public NewsItemPresenterImp(NewsItemActivity view)
    {
        this.view = view;
        interactor = new NewsItemInteractorImp();
    }

    @Override
    public void getRssList(int position, String url)
    {
        if (view != null)
        {
            // show progress
            view.showProgress();
            interactor.getRssList(position, this, url);

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

    public void swipeToRefresh(int position)
    {
        getRssList(position, url);
    }

    public void onError(String error)
    {
        // show error
        if (view != null)
        {
            view.hideProgress();
            view.showError(error);
        }
    }
}
