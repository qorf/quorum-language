/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author sahana
 */
public class Servo {
    public java.lang.Object $me = null;
    cbccore.motors.Servo s;
    
    public void SetPort(int port)
    {
        s = new cbccore.motors.Servo(port);
        s.enable();
    }
    
    public void Off()
    {
        s.disable();
    }
    
    public void MoveToPosition(int position)
    {
       s.setPosition(position);
    }
    
    public int GetPosition()
    {
       return s.getPosition();
    }
}
