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
        motorSpeed = speed * 10;
    }
    
    public int GetSpeed()
    {
        return (motorSpeed/10);
    }
    
    public void MoveForward()
    {
        m.forward();
    }
    
    public void MoveForward(int speed)
    {
        m.moveAtVelocity(speed*10);
    }
    
    public void MoveBackward()
    {
        m.backward();
    }
    
    public void MoveBackward(int speed)
    {   
        speed = speed * -1;
        m.moveAtVelocity(speed*10);
    }
    
    public void MoveToPosition(int position)
    {
        m.moveToPosition(motorSpeed, position);
    }
    
    public void MoveToPosition(int position, int speed)
    {
        m.moveToPosition(speed*10, position);
    }
    
    public void MoveFromHere(int position)
    {
        m.moveRelativePosition(motorSpeed, position);
    }
    
    public void MoveFromHere(int position, int speed)
    {
        m.moveRelativePosition(speed*10, position);
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

