package com.example.rssfeeder;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.rssfeeder.app.RssFeederApplication;
import com.example.rssfeeder.utils.AlertUtils;

import java.util.List;

/**
 * Created by Андрей on 07.07.2016.
 */
public abstract class AbstractActivity extends AppCompatActivity
{
    final Context context = this;
    private int animationLength = 1000;
//    @Override
//    public void setContentView(int layoutResID)
//    {
//        LayoutInflater layoutInflater = getLayoutInflater();
//        final View container = layoutInflater.inflate(R.layout.feed_activity, (ViewGroup) getWindow().getDecorView(), false);
//        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.content_layout), true);
//
//        super.setContentView(container);
//        setupUI(findViewById(R.id.scrollView_login));
//
//    }

    public void showProgress()
    {

    }
    public void hideProgress()
    {
        progressFadeOut();
    }

    private void progressFadeOut()
    {
        int colorFrom = getResources().getColor(R.color.transparent);
        int colorTo = getResources().getColor(R.color.progressBarBackground);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(animationLength); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animator)
            {

            }
        });
        colorAnimation.start();
    }

    private void progressFadeIn()
    {
        int colorTo = ContextCompat.getColor(this, R.color.transparent);
        int colorFrom = ContextCompat.getColor(this, R.color.progressBarBackground);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(animationLength); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animator)
            {

            }
        });
        colorAnimation.start();
    }

    public void showError()
    {
        AlertUtils.showSimpleAlert(getResources().getString(R.string.login_fail), getResources().getString(R.string.login_error), context);
    }

    protected void startActivity(Class activityClass, boolean lockBackAction, String aditionalParameterName, String aditionalParameterValue) {
        Intent intent = new Intent(this, activityClass);
        if (lockBackAction) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        if (aditionalParameterName != null && aditionalParameterValue != null)
        {
            intent.putExtra(aditionalParameterName, aditionalParameterValue);
        }
        startActivity(intent);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private boolean isLastActivityInStack()
    {
        ActivityManager mngr = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if (taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName()))
        {
            return true;
        } else
            return false;
    }

    protected void setupUI(View view)
    {
        if (view != null)
        {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus)
                    {
                        onClickAway();
                    }
                }
            });
        }
    }

    protected void onClickAway()
    {
        //hide soft keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
        {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null)
            {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }

}
