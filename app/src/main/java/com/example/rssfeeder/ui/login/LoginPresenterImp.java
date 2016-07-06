package com.example.rssfeeder.ui.login;

import com.example.rssfeeder.data.User;
import com.example.rssfeeder.model.RetrofitRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginPresenterImp implements ILoginPresenter, ILoginInteractor.onLoginListener
{
    private LoginInteractorImp interactor;
    private ILoginActivity view;
    User user;

    public LoginPresenterImp(LoginActivity view)
    {
        this.view = view;
        interactor = new LoginInteractorImp();
    }

    @Override
    public void onRegisterSuccess()
    {
        // success logic
        view.hideProgress();
    }
    @Override
    public void onRegisterFail()
    {
        // fail logic
        view.hideProgress();
    }
    @Override
    public void onLoginSuccess()
    {
        // success logic
        view.hideProgress();
        view.success();
    }
    @Override
    public void onLoginFail()
    {
        // fail logic
        view.hideProgress();
    }

    @Override
    public void onConnectionError()
    {
        // show error
        view.hideProgress();
        view.showError();
    }



    public void sendLogin(String username, String password)
    {
        // validation here probably?
        view.showProgress();
        user = new User();
        user.setUserName(username); user.setUserPassword(password);

        interactor.sendLogin(user, this);

    }

    public void sendRegistration(String username, String password)
    {
        view.showProgress();
        user = new User();
        user.setUserName(username); user.setUserPassword(password);
        // validation here probably?
        interactor.sendRegistration(user, this);
    }
}
