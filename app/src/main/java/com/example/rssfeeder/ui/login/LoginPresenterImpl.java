package com.example.rssfeeder.ui.login;

import com.example.rssfeeder.repository.model.User;

/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginListener, LoginInteractor.onRegisterListener
{
    private LoginInteractorImp interactor;
    private LoginView view;
    User user;

    public LoginPresenterImpl(LoginViewImpl view)
    {
        this.view = view;
        interactor = new LoginInteractorImp();
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

    @Override
    public void sendPOSTRequest(PostRequestType postRequestType, String username, String password)
    {
        if (postRequestType == PostRequestType.LOGIN)
        {
            // validation here probably?
            view.showProgress();
            user = new User();
            user.setUserName(username); user.setUserPassword(password);
            interactor.sendLogin(user, this);
        }
        if (postRequestType == PostRequestType.REGISTRATION)
        {
            view.showProgress();
            user = new User();
            user.setUserName(username); user.setUserPassword(password);
            // validation here probably?
            interactor.sendRegistration(user, this);

        }
    }
}
