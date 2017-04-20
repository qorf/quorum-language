/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import org.lwjgl.openal.ALCdevice;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author alleew
 */
public class Microphone 
{
    protected ALCdevice device;
    private AudioSamples_ lastSample;
    
    public void Record()
    {
        if (device == null)
            device = ALC11.alcCaptureOpenDevice(null, 44100, AL10.AL_FORMAT_MONO16, 2048);
        else
            throw new RuntimeException("The microphone is already recording! Call Stop() before recording again.");
        
        ALC11.alcCaptureStart(device);
    }
    
    public void Stop()
    {
        ALC11.alcCaptureStop(device);
        lastSample = GetSamples();
        device = null;
    }
    
    public AudioSamples_ GetSamples()
    {
        return null;
    }
    
    public boolean IsRecording()
    {
        return device != null;
    }
}
