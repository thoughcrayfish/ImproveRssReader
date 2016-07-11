package com.example.rssfeeder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rssfeeder.R;
import com.example.rssfeeder.model.RssItem;

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

        //Download image using picasso library
//        String thumb = rssItem.getThumbnail();
//        if (Patterns.WEB_URL.matcher(thumb).matches())
//        {
//            Picasso.with(mContext).load(rssItem.getThumbnail())
//                    .error(R.drawable.ic_description_black_24dp)
//                    .placeholder(R.drawable.ic_description_black_24dp)
//                    .into(customViewHolder.imageView);
//        }
//        else
//        {
//            Picasso.with(mContext).load(R.drawable.ic_description_black_24dp)
//                    .into(customViewHolder.imageView);
//        }
        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(rssItem.getTitle()));

    }

    public FeedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {

        View view = inflater.inflate(R.layout.list_row, null);

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
