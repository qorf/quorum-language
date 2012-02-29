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
    private int motorInteger;
    private int motorPosition;
    
    public void SetConnection(int motorInt)
    {
        m = new cbccore.motors.Motor(motorInt);
        motorInteger = motorInt;
    }
     
    public int GetConnection()
    {
        return motorInteger;
    }
    
    public void ResetPosition()
    {
        m.clearPositionCounter();
        motorPosition = 0;
    }
    
    public int GetPosition()
    {
        return m.getPosition();
    }
     
    public void Move(int speed, boolean forward)
    {
        //int setspeed = m.getDefaultSpeed();
        //setspeed = setspeed * (speed / 100);
        m.motor(speed);
        if (forward == true)
            m.forward();
        else
            m.backward();
    }
    
    public void MoveToPosition(int speed, int position)
    {
        //int setspeed = m.getDefaultSpeed();
        //setspeed = setspeed * (speed / 100);
        m.moveToPosition(speed, position);
        motorPosition = position;
    }
    
    public void MoveFromHere(int speed, int position)
    {
        //int setspeed = m.getDefaultSpeed();
        //setspeed = setspeed * (speed / 100);
        m.moveToPosition(speed, position);
        motorPosition = motorPosition + position;
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
    