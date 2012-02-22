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
    
    
    public void Go() {
        cbccore.motors.Motor m = new cbccore.motors.Motor(0);
        m.backward();
        m.moveAtVelocity(100);
        m = new cbccore.motors.Motor(1);
        m.moveAtVelocity(200);
        m = new cbccore.motors.Motor(2);
        m.moveAtVelocity(300);
        m = new cbccore.motors.Motor(3);
        m.moveAtVelocity(400);
        try { Thread.sleep(2000l); } catch (Exception e) {}
        cbccore.motors.Motor.allOff();
        m.moveRelativePosition(500, 5000);
        m.waitForDone();
    }
    
    public void SetConnection(int motorInteger)
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(motorInteger);
    }
     
    public void GetConnection()//int motorlocation)
    {
        //cbccore.motors.Motor m = new cbccore.motors.Motor(motorlocation);
        //return m;
    }
    
    public void ResetPosition(int motorInteger)
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(motorInteger);
        m.clearPositionCounter();
    }
    
    public int GetPosition(int motorInteger)
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(motorInteger);
        return m.getPosition();
    }
     
    public void Move(int motorInteger, int speed, boolean forward)
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(motorInteger);
        speed = speed * 100;
        if (forward == true)
            m.moveAtVelocity(speed);
        else
            m.backward();
    }
    
    public void MoveToPosition(double speed, boolean forward)
    {
    
    }
    
    public void MoveFromHere(double speed, boolean forward)
    {
    
    }
    
    public void Off(int motorInteger)
    {
        cbccore.motors.Motor m = new cbccore.motors.Motor(motorInteger);
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
    
    
    public void Wait(int motorInteger)
    {
       cbccore.motors.Motor m = new cbccore.motors.Motor(0);
        m.waitForDone();   
    }
    
    public boolean IsMotionComplete(int motorInteger)
    {
     cbccore.motors.Motor m = new cbccore.motors.Motor(motorInteger);
     boolean sucess;
     sucess = m.getDone();
     return sucess;
    }
}
