/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

import cbccore.Device;
/**
 *
 * @author sahana
 */
public class Sound {
    public java.lang.Object $me = null;
    
    public void Beep()
    {   
        Device.getLowSoundController().beep();
    }
    
}
