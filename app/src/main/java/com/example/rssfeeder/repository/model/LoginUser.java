package com.example.rssfeeder.repository.model;

/**
 * Created by Андрей on 05.07.2016.
 */
public class LoginUser
{
    String userName;
    String userPassword;

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getUserPassword()
    {
        return userPassword;
    }
}
