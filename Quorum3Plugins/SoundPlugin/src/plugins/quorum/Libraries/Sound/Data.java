/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author alleew
 */
public abstract class Data {
    
    static protected AudioManager manager;
    protected boolean isLooping = false;
    protected boolean isPlaying = false;
    protected float volume = 1;
    protected float pan = 0;
    protected float fade = 0;
    protected float pitch = 1;
    protected double rotation = 0;
    
    protected float x = 0;
    protected float y = 0;
    protected float z = 1;
    
    static
    {
        manager = new AudioManager();
    }
    
    public abstract void Play();
    
    public abstract void SetLooping(boolean looping);
    
    public abstract void Stop();
    
    public abstract void Dispose();
    
    public abstract void Pause();
    
    public abstract void Resume();
    
    // STILL NEED TO REVIEW THIS FOR STREAMING DATA!
    public abstract void SetPitch(float pitch);
    
    public abstract void SetVolume(float volume);
    
    public abstract void SetHorizontalPosition(float position);
    
    public abstract void SetFade(float position);
    
    public abstract void SetX(float newX);
    
    public abstract void SetY(float newY);
    
    public abstract void SetZ(float newZ);
    
    public abstract void SetPosition(float newX, float newY, float newZ);
    
    public abstract boolean IsStreaming();
    
    public double GetX()
    {
        return x;
    }
    
    public double GetY()
    {
        return y;
    }
    
    public double GetZ()
    {
        return z;
    }
    
    public void SetRotation(double rotation)
    {
        this.rotation = rotation;
        
        double newX = Math.sin(Math.toRadians(rotation));
        double newY = Math.cos(Math.toRadians(rotation));
        double newZ = 0;
        
        SetPosition((float)newX, (float)newY, (float)newZ);
    }
    
    public void Rotate(double addRotation)
    {
        SetRotation(rotation + addRotation);
    }
    
    public boolean IsLooping()
    {
        return isLooping;
    }
    
    public float GetBalance()
    {
        return pan;
    }
    
    public float GetVolume()
    {
        return volume;
    }
    
    public float GetPitch()
    {
        return pitch;
    }
    
    public abstract boolean IsPlaying();
    
    // Continues streaming data for streaming audio classes.
    // This will throw an error in non-streaming audio classes.
    public abstract void Update();
    
    protected static FileInputStream FileToStream(File file)
    {
        FileInputStream stream;
        
        try
        {
            stream = new FileInputStream(file);
        }
        catch (Throwable ex)
        {
            throw new RuntimeException("Could not load sound file with path: " + file.getAbsolutePath());
        }
        
        return stream;
    }
}
