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
    
    public void Move(double speed) {
        
    }
    
    public void Move(double speed, boolean forward) {
        
    }
}
