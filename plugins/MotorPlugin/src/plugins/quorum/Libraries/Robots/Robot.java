/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author sahana
 */
public class Robot {
    public java.lang.Object $me = null;
    
    cbccore.low.Create r;
    
    public void SetConnection() 
    {
     r.create_connect();   
    }
    
    public void Disconnect()
    {              
        r.create_disconnect();
    }
 
    public void StopWheels()
    {
        r.create_stop();
    }
    
    public void MoveStraight(int speed)
    {
        r.create_drive_straight(speed);
    }
    
    public void MoveAsCurve(int speed, int radius)
    {
        //r. drive(speed, radius);
    }
    
    public void RotateClockwise(int speed)
    {
        r.create_spin_CW(speed);
    }
    
    public void RotateCounterClockwise(int speed)
    {
        r.create_spin_CCW(speed);
    }
    
    public int IsLeftSensorTriggered(float lag)
    {
        return r.get_create_lcliff(lag);
    }
    
    public int IsRightSensorTriggered(float lag)
    {
        return r.get_create_rcliff(lag);
    }
    
    public int IsLeftFrontSensorTriggered(float lag)
    {
        return  r.get_create_lfcliff(lag);
    }
    
    public int IsRightFrontSensorTriggered(float lag)
    {
        return r.get_create_lfcliff(lag);
    }
    
    public int IsLeftBumperPressed(float lag)
    {   
        return r.get_create_lbump(lag);
    }
    
    public int IsRighttBumperPressed(float lag)
    {   
        return r.get_create_rbump(lag);
    }
}

//import cbccore.create.CreateConnectException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

//cbccore.create.Create r;
//cbccore.create.CliffState c;
    //cbccore.create.CreateConnectException e;
    
    /*private int rightCliff;
    private int rightFrontCliff;
    private int leftCliff;
    private int leftFrontCliff;
    private int rightCliffAmount;
    private int rightFrontCliffAmount;
    private int leftCliffAmount;
    private int leftFrontCliffAmount;*/
            
    /*public void SetConnection() //throws CreateConnectException
    {
        try {
            r = new cbccore.create.Create();
        } catch (CreateConnectException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
          
    }
        c = new cbccore.create.CliffState(rightCliff, rightFrontCliff, leftCliff, leftFrontCliff, rightCliffAmount, rightFrontCliffAmount, leftCliffAmount, leftFrontCliffAmount);
        try {
            r.connect();
        } catch (CreateConnectException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
        }
        RuntimeException runtimeException = new RuntimeException("Compiled Code");
        //r.driveDirect(250, 250);
    }*/
/*
 public void Disconnect()
    {              
        r.disconnect();
    }
 
    public void StopWheels()
    {
        r.stop();
    }
    
    public void MoveStraight(int speed)
    {
        r.driveStraight(speed);
    }
    
    public void MoveAsCurve(int speed, int radius)
    {
        r.drive(speed, radius);
    }
    
    public void RotateClockwise(int speed)
    {
        r.spinCW(speed);
    }
    
    public void RotateCounterClockwise(int speed)
    {
        r.spinCCW(speed);
    }
    
    public int IsLeftSensorTriggered()
    {
        return c.getLeftCliff();
    }
    
    public int IsRightSensorTriggered()
    {
        return c.getRightCliff();
    }
    
    public int IsLeftFrontSensorTriggered()
    {
        return c.getLeftFrontCliff();
    }
    
    public int IsRightFrontSensorTriggered()
    {
        return c.getRightFrontCliff();
    }
}

 */