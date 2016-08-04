package com.example.rssfeeder.repository.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Андрей on 15.07.2016.
 */
public class UserObject
{
    @SerializedName("week") private int currentWeek;
    @SerializedName("login") private String login;
    @SerializedName("email") private String email;
    @SerializedName("firstname") private String firstName;
    @SerializedName("secondname") private String secondName;
    @SerializedName("avatar_90x90") private String userPicture;
    @SerializedName("pregnant") private int pregnant;
    @SerializedName("planning") private int planning;

    public int getCurrentWeek()
    {
        return currentWeek;
    }
    public void setCurrentWeek(int currentWeek)
    {
        this.currentWeek = currentWeek;
    }
    public String getLogin()
    {
        return login;
    }
    public void setLogin(String login)
    {
        this.login = login;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getSecondName()
    {
        return secondName;
    }
    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture)
    {
        this.userPicture = userPicture;
    }
}
