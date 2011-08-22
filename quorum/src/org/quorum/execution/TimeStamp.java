/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a time stamp in the virtual machine. It is a lightweight
 * way to represent both the time in the virtual machine and actual time in the
 * world.
 * @author Andreas Stefik
 */
public class TimeStamp {
    private int machineTime = -1;
    public long realTime = -1;
    private String realDateTime;

    private void setToCurrentTime() {
        realTime = System.currentTimeMillis();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
        Date date = new Date();
        realDateTime = dateFormat.format(date);
    }

    public TimeStamp(int machineTime) {
        this.machineTime = machineTime;
        setToCurrentTime();
    }

    public TimeStamp(TimeStamp stamp) {
        if(stamp != null) {
            machineTime = stamp.machineTime;
            realTime = stamp.realTime;
        }
    }

    /**
     * @return the machineTime
     */
    public int getMachineTime() {
        return machineTime;
    }

    /**
     * Returns the actual real-world time when this value was set.
     * @return
     */
    public String getFormattedDateTime() {
        return realDateTime;
    }
}
