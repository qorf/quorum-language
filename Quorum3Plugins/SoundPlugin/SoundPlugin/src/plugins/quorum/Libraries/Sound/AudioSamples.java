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
    
    public quorum.Libraries.Sound.AudioSamples_ Copy()
    {
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = channels;
        short[] array = buffer.clone();
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ Copy(int start, int end)
    {
        if (start > end)
        {
            int temp = start;
            start = end;
            end = temp;
        }
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = channels;
        short[] array = new short[(end - start + 1) * channels];
        System.arraycopy(buffer, start * channels, array, 0, (end - start + 1) * channels);
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ CopyChannel(int channel)
    {
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = channels;
        short[] array = new short[buffer.length / channels];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = buffer[i * channels + channel];
        }
        copy.plugin_.buffer = array;
        return copy;
    }
    
    public quorum.Libraries.Sound.AudioSamples_ CopyChannel(int channel, int start, int end)
    {
        if (start > end)
        {
            int temp = start;
            start = end;
            end = temp;
        }
        quorum.Libraries.Sound.AudioSamples copy = new quorum.Libraries.Sound.AudioSamples();
        copy.plugin_.samplesPerSecond = samplesPerSecond;
        copy.plugin_.channels = channels;
        short[] array = new short[(end - start + 1)];
        int channelStart = start + channel;
        for (int i = 0; i < array.length; i++)
        {
            array[i] = buffer[channelStart + i * channels];
        }
        copy.plugin_.buffer = array;
        return copy;
    }
}
