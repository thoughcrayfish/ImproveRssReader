package com.example.rssfeeder.ui.login;

/**
 * Created by Андрей on 05.07.2016.
 */
public interface LoginPresenter
{
    enum PostRequestType
    {
        LOGIN,
        REGISTRATION
    }
    void loginCheck(String loginText, String passwordText);
    void registrationCheck(String registrationText, String passwordText);

}
