package plugins.quorum.Libraries.System;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The default implementation of the date time plugin for the compiler.
 * 
 * @author Andreas Stefik
 */
public class DateTime {
    public java.lang.Object me_ = null;
    private QuorumDateTime dateTime = new QuorumDateTime();
    
    public int GetYear() {
        return dateTime.GetYear();
    }
    public int GetMonth() {
        return dateTime.GetMonth();
    }
    public int GetDayOfMonth() {         
        return dateTime.GetDayOfMonth();     
    }
    public int GetDayOfWeek() {         
        return dateTime.GetDayOfWeek();     
    }
    public int GetHour() {         
        return dateTime.GetHour();     
    }
    public int GetMinute() {         
        return dateTime.GetMinute();     
    }
    public int GetSecond() {         
        return dateTime.GetSecond();     
    }
    public int GetTimeZone() {         
        return dateTime.GetTimeZone();     
    }
    public boolean IsDaylightSavings() {         
        return dateTime.IsDaylightSavings();     
    }
    public double GetEpochTime() {         
        return dateTime.GetEpochTime();     
    }
    public void SetEpochTime(double epochTime) {
        dateTime.SetEpochTime((long)epochTime);
    }
    public void SetTimeZone(int timeZoneOffset) {
        dateTime.SetTimeZone(timeZoneOffset);
    }
}
