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
public class AudioBuffer 
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
        SetSizeInSamples((int)(seconds * samplesPerSecond) * channels);
    }
    
    public double GetSizeInSeconds()
    {
        return (buffer.length / (double)samplesPerSecond) / channels;
    }
    
    public void SetSizeInSamples(int samples)
    {
        buffer = new short[samples];
    }
    
    public int GetSizeInSamples()
    {
        return buffer.length;
    }
    
    public void SetSample(int channel, int index, double value)
    {
        try {
        buffer[index * channels + channel] = (short)(value * MAX_SHORT);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(channel + ", " + index + ", " + value);
            System.out.println(buffer.length);
            System.out.println((index * channels + channel));
            throw e;
        }
    }
    
    public double GetSample(int channel, int index)
    {
        return buffer[index * channels + channel] / (double)MAX_SHORT;
    }
}
