package com.example.rssfeeder.ui.login;

import com.example.rssfeeder.data.User;

/**
 * Created by Андрей on 05.07.2016.
 */
public interface ILoginInteractor
{
    interface onLoginListener
    {
        public void onRegisterSuccess();

        public void onRegisterFail();

        public void onLoginSuccess();

        public void onLoginFail();

        public void onConnectionError();
    }
    void sendRegistration(User user, onLoginListener listener);
    void sendLogin(User user, onLoginListener listener);
}
