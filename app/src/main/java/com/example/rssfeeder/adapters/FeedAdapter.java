package com.example.rssfeeder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rssfeeder.R;
import com.example.rssfeeder.repository.model.RssItem;

import java.util.List;

/**
 * Created by Андрей on 04.07.2016.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>
{
    List<RssItem> objects;
    Context mContext;

    private static LayoutInflater inflater = null;


    public void onBindViewHolder(FeedViewHolder customViewHolder, int i)
    {
        RssItem rssItem = objects.get(i);
        //Setting text view title
        customViewHolder.title.setText(Html.fromHtml(rssItem.getTitle()));
        customViewHolder.detail.setText(Html.fromHtml(rssItem.getDetail()));
    }

    public FeedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = inflater.inflate(R.layout.list_row, viewGroup, false);

        FeedViewHolder viewHolder = new FeedViewHolder(view);
        return viewHolder;
    }

    public FeedAdapter(Context context, List<RssItem> rssItemList)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        objects = rssItemList;
    }

    public int getItemCount()
    {
        return (null != objects ? objects.size() : 0);
    }
}
