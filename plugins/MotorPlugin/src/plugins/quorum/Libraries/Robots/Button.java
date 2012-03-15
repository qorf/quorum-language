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
    
    public boolean IsAButtonPressed()
    {
        cbccore.sensors.buttons.AButton b = new cbccore.sensors.buttons.AButton();
        return b.getValue();
    }

    public boolean IsBButtonPressed()
    {
        cbccore.sensors.buttons.BButton b = new cbccore.sensors.buttons.BButton();
        return b.getValue();
    }
    
    public boolean IsBlackButtonPressed()
    {
        cbccore.sensors.buttons.BlackButton b = new cbccore.sensors.buttons.BlackButton();
        return b.getValue();
    }
    
    public boolean IsLeftButtonPressed()
    {
        cbccore.sensors.buttons.LeftButton b = new cbccore.sensors.buttons.LeftButton();
        return b.getValue();
    }
    
    public boolean IsDownButtonPressed()
    {
        cbccore.sensors.buttons.DownButton b = new cbccore.sensors.buttons.DownButton();
        return b.getValue();
    }
    
    public boolean IsRightButtonPressed()
    {
        cbccore.sensors.buttons.RightButton b = new cbccore.sensors.buttons.RightButton();
        return b.getValue();
    }
    
    public boolean IsUpButtonPressed()
    {
        cbccore.sensors.buttons.UpButton b = new cbccore.sensors.buttons.UpButton();
        return b.getValue();
    }
}
