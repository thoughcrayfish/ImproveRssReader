package com.example.rssfeeder.ui.newsItem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.rssfeeder.R;
import com.example.rssfeeder.adapters.FeedAdapter;
import com.example.rssfeeder.data.RssItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsItemActivity extends Activity implements INewsItemView, SwipeRefreshLayout.OnRefreshListener
{
    private static final String TAG = "RSS Reader";
    int lastItem = 0;
    private NewsItemPresenterImp presenter;
    final Context context = this;
    String url;

    // ButterKnife
    @BindView(R.id.feed_progress_bar) ProgressBar progressBar;
    @BindView(R.id.feed_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.feed_swipe_to_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.feed_fab) FloatingActionButton feedFAB;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        /* Allow activity to show indeterminate progressbar */
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);
        url = getResources().getString(R.string.rss_URL);
        setColors();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Refresh Listener
        mSwipeRefreshLayout.setOnRefreshListener(NewsItemActivity.this);

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
        presenter = new NewsItemPresenterImp(this);
        presenter.getRssList(lastItem, url);

    }

    private void addSourceAlert()
    {
        // add RSS feed source to-do
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        alert.setView(input).setTitle("Add New RSS source");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString().trim();
                presenter.getRssList(lastItem, value);
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        alert.show();

    }

    @Override
    public void onRefresh()
    {
        Toast.makeText(this, R.string.refresh_started, Toast.LENGTH_SHORT).show();
        presenter.swipeToRefresh(lastItem);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить
                Toast.makeText(NewsItemActivity.this, R.string.refresh_finished, Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    @Override
    public void showProgress()
    {
        // show progress functionality
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgress()
    {
        // hide progress functionality
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void showError(String error)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.login_fail_title);
        alertDialog.setMessage("Unable to connect");

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
        // show error functionality
    }
    public void loadList(List<RssItem> feedList)
    {
        FeedAdapter adapter = new FeedAdapter(NewsItemActivity.this, feedList);
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