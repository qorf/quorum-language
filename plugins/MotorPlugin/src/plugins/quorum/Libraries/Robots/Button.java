/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author sahana
 */
public class Button {
    public java.lang.Object $me = null;
    cbccore.sensors.buttons.AButton A = null;
    cbccore.sensors.buttons.BButton B = null;
    cbccore.sensors.buttons.BlackButton Bl = null;
    cbccore.sensors.buttons.LeftButton l = null;
    cbccore.sensors.buttons.DownButton d = null;
    cbccore.sensors.buttons.RightButton r = null;
    cbccore.sensors.buttons.UpButton u = null;
            
    public boolean IsAButtonPressed()
    {
        A = new cbccore.sensors.buttons.AButton();
        return A.getValue();
    }

    public boolean IsBButtonPressed()
    {
        B = new cbccore.sensors.buttons.BButton();
        return B.getValue();
    }
    
    public boolean IsBlackButtonPressed()
    {
        Bl = new cbccore.sensors.buttons.BlackButton();
        return Bl.getValue();
    }
    
    public boolean IsLeftArrowPressed()
    {
        l = new cbccore.sensors.buttons.LeftButton();
        return l.getValue();
    }
    
    public boolean IsDownArrowPressed()
    {
        d = new cbccore.sensors.buttons.DownButton();
        return d.getValue();
    }
    
    public boolean IsRightArrowPressed()
    {
        r = new cbccore.sensors.buttons.RightButton();
        return r.getValue();
    }
    
    public boolean IsUpArrowPressed()
    {
        u = new cbccore.sensors.buttons.UpButton();
        return u.getValue();
    }
}
