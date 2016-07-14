package com.example.rssfeeder.ui.login;

import com.example.rssfeeder.repository.model.LoginUser;

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
    void sendRegistration(LoginUser loginUser, onLoginListener listener);
    void sendLogin(LoginUser loginUser, onLoginListener listener);
}
