package com.example.rssfeeder.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rssfeeder.R;
import com.example.rssfeeder.ui.newsItem.NewsItemActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginActivity extends Activity implements ILoginActivity
{
    private LoginPresenterImp presenter;

    @BindView(R.id.login_username_editText)EditText loginEditText;
    @BindView(R.id.login_password_editText) EditText passwordEditText;
    @BindView(R.id.login_button)Button loginButton;
    @BindView(R.id.login_register_button) Button registerButton;
    @BindView(R.id.login_progressBar)ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);

        setColors();



        presenter = new LoginPresenterImp(this);

        loginButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                presenter.sendLogin(loginEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                registerCheck();
            }
        });
    }

    public void showProgress()
    {
        progressBar.setVisibility(View.VISIBLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        // logic here
    }
    public void hideProgress()
    {
        progressBar.setVisibility(View.INVISIBLE);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        // logic here
    }
    public void showError()
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
        // logic here
    }

    private void registerCheck()
    {
        if (loginEditText != null && passwordEditText != null)
            presenter.sendRegistration(loginEditText.getText().toString(), passwordEditText.getText().toString());
    }

    public void success()
    {
        Intent intent = new Intent(this, NewsItemActivity.class);
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


}
