/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALC11;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author alleew
 */
public class Microphone 
{
    static
    {
        // Ensure OpenAL is initialized by loading dummy audio.
        Audio audio = new Audio();
        quorum.Libraries.Sound.AudioSamples_ samples = new quorum.Libraries.Sound.AudioSamples();
        samples.SetTotalSize(0);
        audio.Load(samples);
        audio.Dispose();
    }
    
    public Object me_ = null;
    
    protected long device = 0;
    protected boolean isRecording = false;
    private int size = 44100;
    
    public void Record()
    {
        if (device == 0)
            device = ALC11.alcCaptureOpenDevice((CharSequence)null, 44100, AL10.AL_FORMAT_MONO16, size);
        
        if (isRecording)
            throw new RuntimeException("The microphone is already recording! Call Stop() before recording again.");
        
        isRecording = true;
        ALC11.alcCaptureStart(device);
    }
    
    public void Stop()
    {
        if (!isRecording)
            return;
        
        ALC11.alcCaptureStop(device);
        isRecording = false;
    }
    
    public AudioSamples_ GetSamples()
    {
        IntBuffer temp = BufferUtils.createIntBuffer(1);
        ALC10.alcGetIntegerv(device, ALC11.ALC_CAPTURE_SAMPLES, temp);
        int samplesAvailable = temp.get(0);

        if (samplesAvailable <= 0)
            return null;

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(samplesAvailable * 2);
        byteBuffer.order(ByteOrder.nativeOrder());
        ALC11.alcCaptureSamples(device, byteBuffer, samplesAvailable);
        ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
        
        AudioSamples_ samples = new quorum.Libraries.Sound.AudioSamples();
        AudioSamples samplesPlugin = ((quorum.Libraries.Sound.AudioSamples)samples).plugin_;
        samplesPlugin.channels = 1;
        samplesPlugin.samplesPerSecond = 44100;
        samplesPlugin.buffer = new short[shortBuffer.limit()];
        shortBuffer.get(samplesPlugin.buffer);
        
        return samples;
    }
    
    public boolean IsRecording()
    {
        return isRecording;
    }
    
    public void SetSize(int newSize)
    {
        if (newSize == size)
            return;
        
        if (device != 0)
        {
            if (IsRecording())
                throw new RuntimeException("The size of the Microphone can't be changed while the Microphone is recording.");
            
            ALC11.alcCaptureCloseDevice(device);
            device = 0;
        }
        
        size = newSize;
    }
    
    public int GetSize()
    {
        return size;
    }
}
