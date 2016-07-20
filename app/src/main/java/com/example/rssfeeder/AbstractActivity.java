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
public abstract class AbstractActivity extends ActionBarActivity
{
    private RelativeLayout progress;
    final Context context = this;
    private int animationLength = 1000;
    @Override
    public void setContentView(int layoutResID)
    {
        LayoutInflater layoutInflater = getLayoutInflater();
        final View container = layoutInflater.inflate(R.layout.progress_layout, (ViewGroup) getWindow().getDecorView(), false);
        layoutInflater.inflate(layoutResID, (ViewGroup) container.findViewById(R.id.cont_root), true);

        super.setContentView(container);
        setupUI(findViewById(R.id.scrollView_login));

        progress = (RelativeLayout) findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

    }

    public void showProgress()
    {
        progress = (RelativeLayout) findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        progress.setBackgroundColor(getResources().getColor(R.color.progressBarBackground));
    }
    public void hideProgress()
    {
        progress = (RelativeLayout) findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
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
            public void onAnimationUpdate(ValueAnimator animator) {
                progress.setBackgroundColor((int) animator.getAnimatedValue());
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
            public void onAnimationUpdate(ValueAnimator animator) {
                progress.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });
        colorAnimation.start();
    }

    public void showError()
    {
        AlertUtils.showSimpleAlert(getResources().getString(R.string.login_fail), getResources().getString(R.string.login_error), context);
    }

    protected void startActivity(Class activityClass)
    {
        startActivity(activityClass, false);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }


    protected void startActivity(Class activityClass, boolean lockBackAction)
    {
        Intent intent = new Intent(this, activityClass);
        if (lockBackAction)
        {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    protected void onPause()
    {
//        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        super.onPause();
    }

    @Override
    public void onBackPressed()
    {
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
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
