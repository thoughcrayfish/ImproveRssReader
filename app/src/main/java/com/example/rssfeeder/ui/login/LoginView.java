package com.example.rssfeeder.ui.login;

/**
 * Created by Андрей on 05.07.2016.
 */
public interface LoginView
{
    public void showProgress();
    public void hideProgress();
    public void showError();
    public void showFail();
    public void success();
}
