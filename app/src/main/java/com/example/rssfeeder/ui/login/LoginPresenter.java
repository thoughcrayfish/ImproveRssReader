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
    void sendPOSTRequest(PostRequestType postRequestType, String username, String password);

}
