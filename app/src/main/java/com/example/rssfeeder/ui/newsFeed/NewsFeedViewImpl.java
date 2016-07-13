package com.example.rssfeeder.ui.newsFeed;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;


import com.example.rssfeeder.AbstractActivity;
import com.example.rssfeeder.R;
import com.example.rssfeeder.adapters.FeedAdapter;
import com.example.rssfeeder.app.Config;
import com.example.rssfeeder.repository.model.RssItem;
import com.example.rssfeeder.utils.AlertUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedViewImpl extends AbstractActivity implements NewsFeedView, SwipeRefreshLayout.OnRefreshListener
{
    private static final String TAG = "RSS Reader";
    int lastItem = 0;
    private NewsFeedPresenterImp presenter;
    final Context context = this;

    // ButterKnife
    @BindView(R.id.progressBar_feed) ProgressBar progressBar;
    @BindView(R.id.recyclerView_feed) RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh_feed) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fab_feed) FloatingActionButton feedFAB;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Refresh Listener
        mSwipeRefreshLayout.setOnRefreshListener(NewsFeedViewImpl.this);
        // FAB
        feedFAB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addSourceAlert();
            }
        });
        // presenter
        presenter = new NewsFeedPresenterImp(this);
        presenter.getRssList(lastItem);

    }

    private void addSourceAlert()
    {
        AlertUtils.showAddSourceAlert(getResources().getString(R.string.alert_newRss), getResources().getString(R.string.login_error), context, presenter);
    }

    @Override
    public void onRefresh()
    {
//        presenter.getRssList(lastItem, Config.RssUrl);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить
            }
        }, 3000);
    }

    public void loadList(List<RssItem> feedList)
    {
        FeedAdapter adapter = new FeedAdapter(NewsFeedViewImpl.this, feedList);
        mRecyclerView.setAdapter(adapter);
    }

    private void setColors()
    {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }
}