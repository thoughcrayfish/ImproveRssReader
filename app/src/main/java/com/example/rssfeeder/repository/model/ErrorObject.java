package com.example.rssfeeder.repository.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Андрей on 11.07.2016.
 */
public class ErrorObject
{
    @SerializedName("FaultCode")
    private int FaultCode;

    @SerializedName("FaultMessage")
    private String FaultMessage;

    @SerializedName("Count")
    private int Count;

    @SerializedName("Reason")
    private String Reason;

    transient private Long Time; //sec.

    public int getFaultCode()
    {
        return FaultCode;
    }

    public void setFaultCode(int faultCode)
    {
        FaultCode = faultCode;
    }

    public String getFaultMessage()
    {
        return FaultMessage;
    }

    public void setFaultMessage(String faultMessage)
    {
        FaultMessage = faultMessage;
    }

    public int getCount()
    {
        return Count;
    }

    public void setCount(int count)
    {
        Count = count;
    }

    public Long getTime() {
        return Time;
    }

    public void setTime(Long time)
    {
        Time = time;
    }

    public String getReason()
    {
        return Reason;
    }

    public void setReason(String reason)
    {
        Reason = reason;
    }
}

