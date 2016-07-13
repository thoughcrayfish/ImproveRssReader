package com.example.rssfeeder.ui.login;

import com.example.rssfeeder.repository.model.User;

/**
 * Created by Андрей on 05.07.2016.
 */
public interface LoginInteractor
{
    interface onLoginListener
    {
        public void onLoginSuccess();
        public void onLoginFail();
        public void onConnectionError();
    }

    interface onRegisterListener
    {
        public void onRegisterSuccess();
        public void onRegisterFail();
    }
    void sendRegistration(User user, onLoginListener listener);
    void sendLogin(User user, onLoginListener listener);
}
