package com.example.rssfeeder.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Андрей on 20.07.2016.
 */
public abstract class OnScrollListener extends RecyclerView.OnScrollListener
{
    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
        if (recyclerView != null)
        {
            super.onScrolled(recyclerView, dx, dy);

            if (scrolledDistance > HIDE_THRESHOLD && controlsVisible)
            {
                onHide();
                controlsVisible = false;
                scrolledDistance = 0;
            } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible)
            {
                onShow();
                controlsVisible = true;
                scrolledDistance = 0;
            }

            if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0))
            {
                scrolledDistance += dy;
            }
        }
    }

    public abstract void onHide();
    public abstract void onShow();
}
