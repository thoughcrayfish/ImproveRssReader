package com.example.rssfeeder.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.rssfeeder.AbstractActivity;
import com.example.rssfeeder.R;
import com.example.rssfeeder.ui.newsFeed.NewsFeedViewImpl;
import com.example.rssfeeder.utils.AlertUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginViewImpl extends AbstractActivity implements LoginView
{
    private LoginPresenterImpl presenter;

    @BindView(R.id.editText_username)EditText loginEditText;
    @BindView(R.id.editText_password) EditText passwordEditText;
    @BindView(R.id.button_login)Button loginButton;
    @BindView(R.id.button_register) Button registerButton;
    @BindView(R.id.scrollView_login) ScrollView scrollView;

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        hideProgress();

        setColors();

        presenter = new LoginPresenterImpl(this);
    }

    public void showFail()
    {
        AlertUtils.showAlert(getResources().getString(R.string.login_fail), getResources().getString(R.string.login_error_detail), context);
    }


    private void registerCheck()
    {
        if (loginEditText != null && passwordEditText != null)
            presenter.sendRegistration(loginEditText.getText().toString(), passwordEditText.getText().toString());
    }

    private void loginCheck()
    {
        if (loginEditText != null && passwordEditText != null)
        presenter.sendLogin(loginEditText.getText().toString(), passwordEditText.getText().toString());
    }

    public void success()
    {
        Intent intent = new Intent(this, NewsFeedViewImpl.class);
        startActivity(intent);
        this.finish();
    }

    private void setColors()
    {
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }


    @OnClick(R.id.button_register)
    public void onClick(View view)
    {
        {
            switch (view.getId()) {
                case R.id.button_login:
                    loginCheck();
                    break;

                case R.id.button_register:
                    registerCheck();
                    break;

            }
        }
    }
}
