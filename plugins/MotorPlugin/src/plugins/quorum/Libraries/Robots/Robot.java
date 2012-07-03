/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author sahana
 */

import cbccore.create.Create;
import cbccore.create.CreateConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cbccore.Device;

public class Robot {
    public java.lang.Object $me = null;
    
    cbccore.create.Create r = null;
    
    private double robot_speed, robot_rspeed, robot_lspeed;
    
    public void Connect() 
    {
        try {
            r = new cbccore.create.Create();
            r.connect();
        } catch (CreateConnectException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
          
        }
        
        RuntimeException runtimeException = new RuntimeException("Compiled Code"); 
    }

    public void Disconnect()
    {              
        r.disconnect();
    }
 
    public void StopWheels()
    {
        r.stop();
    }
    
    public void MoveStraight(double speed)
    {
        int speed_int = (int)(speed * 500);
        r.driveStraight(speed_int);
        robot_speed = speed;
    }
    
    public void MoveStraight(double speed, double distance)
    {
        int speed_int = (int)(speed * 500);
        distance = distance * 1000;
        if (speed >= 0) {
            double maxDistance = Device.getLowCreateController().get_create_distance(0) + distance;
            while(r.getDistance() < maxDistance)
                r.driveStraight(speed_int);
        } else {
            double maxDistance = Device.getLowCreateController().get_create_distance(0) - distance;
            while(r.getDistance() > maxDistance)
                r.driveStraight(speed_int);
        }
        robot_speed = speed;
    }
    
    public void MoveWheels(double rightspeed, double leftspeed)
    {
        int rspeed =(int)(rightspeed * 500);
        int lspeed =(int)(leftspeed * 500);
        r.driveDirect(rspeed, lspeed);
        robot_rspeed = rightspeed;
        robot_lspeed = leftspeed;
    }
    
     public void MoveInCirle(double speed, double radius)
    {
        int radius_int = (int) (radius * 1000);
        int speed_int = (int)(speed * 500);
        r.drive(speed_int, radius_int);
        robot_speed = speed;
    }
    
    public void TurnLeft(double turn, double speed)     
    {   
        int speed_int = (int)(speed * 500);
        int angle = (int)(turn * 360);
        int finalangle = Device.getLowCreateController().get_create_total_angle(0) + angle;
        int speedneg = speed_int * -1;
        while (Device.getLowCreateController().get_create_total_angle(0) < finalangle)
	{
           r.driveDirect(speed_int, speedneg);
        }
        robot_speed = speed;
    }
    
    public void TurnRight(double turn, double speed)     
    {
        int speed_int = (int)(speed * 500);
        int angle = (int)(turn * 360);
        int finalangle = Device.getLowCreateController().get_create_total_angle(0) - angle;
        int speedneg = speed_int * -1;
        while (Device.getLowCreateController().get_create_total_angle(0) > finalangle)
	{
           r.driveDirect(speedneg, speed_int);
        }
        robot_speed = speed;
    }
    
    public boolean IsLeftBumperTriggered(double lag)
    {
        float lag1 = (float)lag;
        int value = Device.getLowCreateController().get_create_lbump(lag1);
        if (value == 0)
            return false;
        else
            return true;
    }
   
    public boolean IsRightBumperTriggered(double lag)
    {
        float lag1 = (float)lag;
        int value = Device.getLowCreateController().get_create_rbump(lag1);
        if (value == 0)
            return false;
        else
            return true;
    }
    
    public boolean IsCenterWheelTriggered(double lag)
    {
        float lag1 = (float)lag;
        int value = Device.getLowCreateController().get_create_cwdrop(lag1);
        if (value == 0)
            return false;
        else
            return true;
    }
    
    public boolean IsLeftWheelTriggered(double lag)
    {
        float lag1 = (float)lag;
        int value = Device.getLowCreateController().get_create_lwdrop(lag1);
        if (value == 0)
            return false;
        else
            return true;
    }
    
    public boolean IsRightWheelTriggered(double lag)
    {
        float lag1 = (float)lag;
        int value = Device.getLowCreateController().get_create_rwdrop (lag1);
        if (value == 0)
            return false;
        else
            return true;
    }
    
    public void TurnClockwise(double speed)
    {
        int speed_int = (int)(speed * 500);
        r.spinCW(speed_int);
        robot_speed = speed;
    }
    
    public void TurnCounterClockwise(double speed)
    {
        int speed_int = (int)(speed * 500);
        r.spinCCW(speed_int);
        robot_speed = speed;
    }
    
    public boolean IsLeftSensorTriggered()
    {
        if ( Device.getLowCreateController().get_create_lcliff(0.1f) == 1)
            return true;
        else
            return false;
    }
    
    public boolean IsRightSensorTriggered()
    {
        if (Device.getLowCreateController().get_create_rcliff(0.1f) == 1)
            return true;
        else
            return false;
    }
    
    public boolean IsLeftFrontSensorTriggered()
    {
        if (Device.getLowCreateController().get_create_lfcliff(0.1f) == 1)
            return true;
        else
            return false;
    }
    
    public boolean IsRightFrontSensorTriggered()
    {
        if ( Device.getLowCreateController().get_create_rfcliff(0.1f) == 1)
            return true;
        else
            return false;
    }
    
    public int GetTotalAngle()
    {
       return Device.getLowCreateController().get_create_total_angle(0);
    }
    
    /*public void SetTotalAngle(int angle)
    {
       Device.getLowCreateController().set_create_total_angle(angle);
    }*/
    
    public double GetTotalDistance()
    {
       return (double)(r.getDistance() * 0.001);
    }
      
    /*public void SetTotalDistance(double distance)
    {
       int distance_int = (int)(distance * 1000); 
           Device.getLowCreateController().set_create_distance(distance_int);
    }*/
    
    public void RunDemo(int demo)
    {
        r.demo(demo);
    }
    
    public double GetMaximumSpeed()
    {
        return 1.0;
    }
    
    public void TurnOff()
    {
        r.setMode(Create.Mode.Off);
    }

    public void TurnSafety(boolean on)
    {
        if (on == true)
            r.setMode(Create.Mode.Safe);
        else
            r.setMode(Create.Mode.Full);
    }
    
    public int GetMode()
    {
        return Device.getLowCreateController().get_create_mode(0.1f);
    }
    
    public boolean IsWallDetected(double lag)
    {
        float lag_float = (float) lag;
        return Device.getLowCreateController().get_create_wall(lag_float) == 1;
    }
    
    public double GetSpeed(double lag) 
    {
        return robot_speed;
    }
    
    public double GetRightWheelSpeed(double lag) 
    {
        return robot_rspeed;
    }
    
    public double GetLeftWheelSpeed(double lag) 
    {
        return robot_lspeed;
    }
    
    public int GetRightSensorValue()
    {
        return Device.getLowCreateController().get_create_rcliff_amt(0.1f);
    }
    
    public int GetRightFrontSensorValue()
    {
        return Device.getLowCreateController().get_create_rcliff_amt(0.1f);
    }
    
    public int GetLeftSensorValue()
    {
        return Device.getLowCreateController().get_create_rcliff_amt(0.1f);
    }
    
    public int GetLeftFrontSensorValue()
    {
        return Device.getLowCreateController().get_create_rcliff_amt(0.1f);
    }
}
    