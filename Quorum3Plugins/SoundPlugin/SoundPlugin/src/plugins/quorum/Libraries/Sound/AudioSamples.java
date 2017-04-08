/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

/**
 *
 * @author alleew
 */
public class AudioSamples 
{
    public Object me_ = null;
    
    public static final int MAX_SHORT = java.lang.Short.MAX_VALUE;
    public short[] buffer;
    public int samplesPerSecond = 44100;
    public int channels = 1;
    
    public void SetChannels(int c)
    {
        channels = c;
    }
    
    public int GetChannels()
    {
        return channels;
    }
    
    public void SetSamplesPerSecond(int samples)
    {
        samplesPerSecond = samples;
    }
    
    public int GetSamplesPerSecond()
    {
        return samplesPerSecond;
    }
    
    public void SetSizeInSeconds(double seconds)
    {
        SetTotalSize((int)(seconds * samplesPerSecond) * channels);
    }
    
    public double GetSizeInSeconds()
    {
        return (buffer.length / (double)samplesPerSecond) / channels;
    }
    
    public void SetTotalSize(int samples)
    {
        buffer = new short[samples];
    }
    
    public int GetTotalSize()
    {
        return buffer.length;
    }
    
    public void Set(int index, double value, int channel)
    {
        buffer[index * channels + channel] = (short)(value * MAX_SHORT);
    }
    
    public double Get(int index, int channel)
    {
        return buffer[index * channels + channel] / (double)MAX_SHORT;
    }
}
