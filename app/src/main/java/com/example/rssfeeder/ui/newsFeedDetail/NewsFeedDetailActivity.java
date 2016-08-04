package com.example.rssfeeder.ui.newsFeedDetail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rssfeeder.AbstractActivity;
import com.example.rssfeeder.R;
import com.example.rssfeeder.utils.AlertUtils;

import butterknife.ButterKnife;

/**
 * Created by Андрей on 02.08.2016.
 */
public class NewsFeedDetailActivity extends AbstractActivity implements NewsFeedDetailView
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_postobject_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        AlertUtils.showToast(NewsFeedDetailActivity.this, id, Toast.LENGTH_SHORT);
    }

    @Override
    public void showFeedDetail()
    {

    }

    @Override
    public void showConnectionError()
    {

    }

    @Override
    public void updateFeedDetail()
    {

    }
}
