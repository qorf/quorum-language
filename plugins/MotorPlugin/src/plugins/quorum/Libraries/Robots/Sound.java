/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author sahana
 */
public class Sound {
    
    cbccore.low.Sound sound;
    public void Beep()
    {
        sound.beep();
    }
    
    public void  Tone(int frequency, int duration)
    {
        sound.tone(frequency, duration);
    }
    
}
