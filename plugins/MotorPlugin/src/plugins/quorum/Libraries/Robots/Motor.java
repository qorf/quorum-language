/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 * This class represents a motor, as implemented natively through Java.
 *
 * @author Sahana Tambi
 */
public class Motor {
    public java.lang.Object $me = null;
    
    cbccore.motors.Motor m;
    private int motorPort;
    private int motorSpeed = 0;
    //private int motorPosition;
    
    public void SetPort(int port)
    {
        m = new cbccore.motors.Motor(port);
        motorPort = port;
    }
     
    public int GetPort()
    {
        return motorPort;
    }
    
    public void ResetPosition()
    {
        m.clearPositionCounter();
    }
    
    public int GetPosition()
    {
        return m.getPosition();
    }
    
    public void SetSpeed(int speed)
    {
        m.motor(speed);
        motorSpeed = speed;
    }
    
    public int GetSpeed()
    {
        return motorSpeed;
    }
    
    public void MoveForward()
    {
        m.forward();
    }
    
    public void MoveForward(int speed)
    {
        m.motor(speed);
        m.forward();
    }
    
    public void MoveBackward()
    {
        m.backward();
    }
    
    public void MoveBackward(int speed)
    {
        m.motor(speed);
        m.backward();
    }
    
    public void MoveToPosition(int position)
    {
        m.moveToPosition(motorSpeed, position);
    }
    
    public void MoveToPosition(int position, int speed)
    {
        int setspeed = m.getDefaultSpeed();
        setspeed = 2 * setspeed * (speed / 100);
        m.moveToPosition(setspeed, position);
    }
    
    public void MoveFromHere(int position)
    {
        m.moveRelativePosition(motorSpeed, position);
    }
    
    public void MoveFromHere(int position, int speed)
    {
        int setspeed = m.getDefaultSpeed();
        setspeed = 2 * setspeed * (speed / 100);
        m.moveRelativePosition(speed, position);
    }
    
    public void Off()
    {
        m.off();
    }
   
    public void Wait()
    {
       m.waitForDone();   
    }
    
    public boolean IsMotionComplete()
    {
        boolean sucess;
        sucess = m.getDone();
        return sucess;
    }
    
    public void AllMotorOff()
    {
        cbccore.motors.Motor.allOff();
    }
}

//This has to be moved to the general class
    /*public void Off()
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(0);
        m.off();
        m = new cbccore.motors.Motor(1);
        m.off();
        m = new cbccore.motors.Motor(2);
        m.off();
        m = new cbccore.motors.Motor(3);
        m.off();
    }*/

    /*public void Move(int speed, boolean forward)
    {
        //int setspeed = m.getDefaultSpeed();
        //setspeed = setspeed * (speed / 100);
        m.motor(speed);
        if (forward == true)
            m.forward();
        else
            m.backward();
    }*/