/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * An implementation of the Quorum DateTime class.
 * @author jeff
 */
public class QuorumDateTime {
    private boolean now = true; // if false, user has called SetEpochTime()
    private TimeZone timezone = new SimpleTimeZone(Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis()), "local timezone");
    private long epochTime = 0; // only used if user has called SetEpochTime()*/
    private Calendar cal;
    
    public int GetYear() {
        if (now)
            cal = Calendar.getInstance(timezone);
        
        return cal.get(Calendar.YEAR);
    }
    
    public int GetMonth() {
        if (now)
            cal = Calendar.getInstance(timezone);

        return cal.get(Calendar.MONTH) + 1;
    }
    
    public int GetDayOfMonth() {
        if (now)
            cal = Calendar.getInstance(timezone);

        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public int GetDayOfWeek() {
        if (now)
            cal = Calendar.getInstance(timezone);

        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    public int GetHour() {
        if (now)
            cal = Calendar.getInstance(timezone);

        return cal.get(Calendar.HOUR_OF_DAY);
    }
    
    public int GetMinute() {
        if (now)
            cal = Calendar.getInstance(timezone);

        return cal.get(Calendar.MINUTE);
    }
    
    public int GetSecond() {
        if (now)
            cal = Calendar.getInstance(timezone);
        
        return cal.get(Calendar.SECOND);
    }
    
    public int GetTimeZone() {
            return (timezone.getRawOffset()) / (1000 * 60 * 60);
    }
    
    public boolean IsDaylightSavings() {
        Calendar nowCal = Calendar.getInstance();

        return nowCal.getTimeZone().inDaylightTime(new Date());
    }
    
    public long GetEpochTime() {
        return System.currentTimeMillis();
    }
    
    public void SetEpochTime(long epochTime) {
        // Set the internal representation of time to the given epoch time.
        now = false;
        cal = Calendar.getInstance(timezone);
        cal.setTimeInMillis(epochTime);
    }
    
    public void SetTimeZone(int timeZoneOffset) {
        timezone = new SimpleTimeZone(timeZoneOffset * 1000 * 60 *60, "custom time zone");
    }
}
