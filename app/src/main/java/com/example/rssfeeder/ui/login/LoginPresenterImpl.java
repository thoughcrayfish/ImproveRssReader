package com.example.rssfeeder.ui.login;

import com.example.rssfeeder.repository.model.LoginUser;

/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginListener, LoginInteractor.onRegisterListener
{
    private LoginInteractorImp interactor;
    private LoginView view;
    LoginUser loginUser;

    public LoginPresenterImpl(LoginViewImpl view)
    {
        this.view = view;
        interactor = new LoginInteractorImp();
    }


    public void registrationCheck(String loginText, String passwordText)
    {
        if (loginText.isEmpty() || passwordText.isEmpty())
            onRegisterFail();
        else sendPOSTRequest(LoginPresenter.PostRequestType.REGISTRATION, loginText, passwordText);
    }

    public void loginCheck(String loginText, String passwordText) {
        if (loginText.isEmpty() || passwordText.isEmpty())
            onLoginFail();
        else sendPOSTRequest(PostRequestType.LOGIN, loginText, passwordText);
    }

    @Override
    public void onRegisterSuccess()
    {
        if (view != null)
        {
            // success logic
            view.hideProgress();
        }
    }
    @Override
    public void onRegisterFail()
    {
        if (view != null)
        {
            // fail logic
            view.showFail();
            view.hideProgress();
        }
    }
    @Override
    public void onLoginSuccess()
    {
        if (view != null)
        {
            // success logic
            view.hideProgress();
            view.success();
        }
    }
    @Override
    public void onLoginFail()
    {
        if (view != null)
        {
            // fail logic
            view.hideProgress();
            view.showFail();
        }
    }

    @Override
    public void onConnectionError()
    {
        if (view != null)
        {
            // show error
            view.hideProgress();
            view.showError();
        }
    }


    private void sendPOSTRequest(PostRequestType postRequestType, String username, String password)
    {
        view.showProgress();
        loginUser = new LoginUser();
        loginUser.setUserName(username); loginUser.setUserPassword(password);
        if (postRequestType == PostRequestType.LOGIN)
        {
            interactor.sendLogin(loginUser, this);
        }
        if (postRequestType == PostRequestType.REGISTRATION)
        {
            interactor.sendRegistration(loginUser, this);
        }
    }
}
