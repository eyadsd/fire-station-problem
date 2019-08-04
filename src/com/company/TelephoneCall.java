package com.company;

public class TelephoneCall  {
    private CallPriority priority;
    private int durationInSeconds;
    private Employee employee;
    public boolean success;

    TelephoneCall(CallPriority priority, int durationInSeconds)
    {
        this.priority = priority;
        this.durationInSeconds = durationInSeconds;
        this.success = false;
    }

    public CallPriority getPriority()
    {
        return this.priority;
    }

    public int getDurationInSeconds()
    {
        return this.durationInSeconds;
    }
    public void setPriority(CallPriority priority)
    {
        this.priority = priority;
    }

}
