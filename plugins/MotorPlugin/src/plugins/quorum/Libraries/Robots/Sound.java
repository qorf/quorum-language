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
    
    //cbccore.low.Sound sound;
    
    public void Beep()
    {   //sound = new cbccore.low.Sound();
        Device.getLowSoundController().beep();
    }
    
    //public void  Tone(int frequency, int duration)
    //{
      //  sound.tone(frequency, duration);
    //}
    
}
