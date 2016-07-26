package com.example.rssfeeder.ui.newsFeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;


import com.example.rssfeeder.AbstractActivity;
import com.example.rssfeeder.R;
import com.example.rssfeeder.adapters.FeedAdapter;
import com.example.rssfeeder.repository.model.PostObject;
import com.example.rssfeeder.utils.AlertUtils;
import com.example.rssfeeder.utils.DividerItemDecoration;
import com.example.rssfeeder.utils.OnScrollListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Андрей on 04.07.2016.
 */
public class NewsFeedActivity extends AbstractActivity implements NewsFeedView, SwipeRefreshLayout.OnRefreshListener
{
    private static final String TAG = "RSS Reader";
    private static final int VERTICAL_ITEM_SPACE = 48;
    int lastItem = 0;
    private NewsFeedPresenterImp presenter;
    final Context context = this;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Календарь");
    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Прямой эфир");
    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Сообщества");
    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Найти подруг");
    PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Settings");
    private String[] drawerList;
    private Toolbar toolbar;
    // ButterKnife
    @BindView(R.id.progressBar_feed) ProgressBar progressBar;
    @BindView(R.id.recyclerView_feed) RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefresh_feed) SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);
        ButterKnife.bind(this);
        setUpDrawer();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Refresh Listener
        mSwipeRefreshLayout.setOnRefreshListener(NewsFeedActivity.this);
        // presenter
        presenter = new NewsFeedPresenterImp(this);
        presenter.getRssList(lastItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions_feed, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.action_filter:
                AlertUtils.alertSingleChoice(context, "Choose One", getResources().getStringArray(R.array.filter_options));
                return true;
            case R.id.action_search:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void loadList(List<PostObject> result)
    {
        FeedAdapter adapter = new FeedAdapter(NewsFeedActivity.this, result);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setOnScrollListener(new OnScrollListener()
        {
            @Override
            public void onHide()
            {
                hideToolbar();
            }

            @Override
            public void onShow()
            {
                showToolbar();
            }
        });
    }

    private void hideToolbar()
    {
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showToolbar()
    {
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    @Override
    public void showError()
    {
        AlertUtils.showSimpleAlert(getResources().getString(R.string.connection_error_title), getResources().getString(R.string.connection_error_detail), context);
    }

    @Override
    public void onRefresh()
    {
        presenter.getRssList(lastItem);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void setUpDrawer()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Прямой эфир");     // Replace with string from resources?
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.drawer_primaryTextColor));
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem().withName("Елена Ерохина").withEmail("erohina@narod.ru").withIcon(getResources().getDrawable(R.drawable.ic_autorization))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener()
                {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile)
                    {
                        return false;
                    }
                })
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1.withIcon(R.drawable.ic_calendar),
                        new DividerDrawerItem(),
                        item2.withIcon(R.drawable.ic_rss_drawer),
                        new DividerDrawerItem(),
                        item3.withIcon(R.drawable.ic_communities),
                        new DividerDrawerItem(),
                        item4.withIcon(R.drawable.ic_findfriends),
                        new DividerDrawerItem(),
                        item5,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
                    {
                        // do something with the clicked item :D
                        return false;
                    }
                })
                .build();
    }

}