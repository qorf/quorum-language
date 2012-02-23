/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author
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
        speed = speed / 100;
        if (forward == true)
            m.moveAtVelocity(speed);
        else
            m.backward();
    }
    
    public void MoveToPosition(int speed, int position)
    {
        speed = speed / 100;
        m.moveToPosition(speed, position);
        motorPosition = position;
    }
    
    public void MoveFromHere(int speed, int position)
    {
        speed = speed / 100;
        m.moveToPosition(speed, position);
        motorPosition = motorPosition + position;
    }
    
    public void Off(int motorInteger)
    {
        m.off();
    }
    
    public void Off()
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(0);
        m.off();
        m = new cbccore.motors.Motor(1);
        m.off();
        m = new cbccore.motors.Motor(2);
        m.off();
        m = new cbccore.motors.Motor(3);
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
