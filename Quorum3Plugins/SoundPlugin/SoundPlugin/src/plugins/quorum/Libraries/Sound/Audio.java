/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import plugins.quorum.Libraries.Sound.IOS.IOSLoader;
import quorum.Libraries.System.File_;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alleew
 */
public class Audio {
    
    public java.lang.Object me_ = null;
    
    Data data;
    
    public static final DataLoader loader;
    
    static
    {
        String os = System.getProperty("os.name");
        
        /*
        We only need to find LWJGL if we are on a Desktop. If we are on Mac, 
        OpenAL will be accessed via static library.
        */
        if (os.contains("Windows") || os.contains("Mac"))
        {
            try 
            {
                java.io.File file = new java.io.File(Audio.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                String runLocation = file.getParentFile().getAbsolutePath();
                String lwjgl = runLocation + "/jni";
                System.setProperty("org.lwjgl.librarypath", lwjgl);
            } 
            catch (URISyntaxException ex) 
            {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            }
            loader = new DesktopLoader();
        }
        else
        {
            loader = new IOSLoader();
        }
        
        /*quorum.Libraries.System.Properties properties = new quorum.Libraries.System.Properties();

        String path;
        quorum.Libraries.System.File file = new quorum.Libraries.System.File();

        file.SetPath("Run/jni");
        
        path = file.GetAbsolutePath();

        properties.SetProperty("org.lwjgl.librarypath", path);*/
    }
    
    public void Load(quorum.Libraries.System.File_ quorumFile)
    {
        if (data != null)
            throw new RuntimeException("An audio file is already loaded! To reuse this variable, call Dispose() before loading a new file.");
        
        data = loader.Load(quorumFile);
    }
    
    public void LoadToStream(quorum.Libraries.System.File_ quorumFile)
    {
        if (data != null)
            throw new RuntimeException("An audio file is already loaded! To reuse this variable, call Dispose() before loading a new file.");
        
        data = loader.LoadToStream(quorumFile);
    }
    
    public void Play()
    {
        if (data == null)
            throw new RuntimeException("Can't play audio before it's loaded -- use Load first.");
        data.Play();
    }
    
    public void EnableLooping()
    {
        if (data == null)
            throw new RuntimeException("Can't enable audio looping before it's loaded -- use Load first.");
        data.SetLooping(true);
    }
    
    public void DisableLooping()
    {
        if (data == null)
            throw new RuntimeException("Can't disable audio looping before it's loaded -- use Load first.");
        data.SetLooping(false);
    }
    
    public void Stop()
    {
        if (data == null)
            throw new RuntimeException("Can't stop audio before it's loaded -- use Load first.");
        data.Stop();
    }
    
    public void Dispose()
    {
        if (data == null)
            throw new RuntimeException("There was no data to dispose! Use Load first.");
        data.Dispose();
        data = null;
    }
    
    public void Pause()
    {
        if (data == null)
            throw new RuntimeException("Can't pause audio before it's loaded -- use Load first.");
        data.Pause();
    }
    
    public void Resume()
    {
        if (data == null)
            throw new RuntimeException("Can't resume audio before it's loaded -- use Load first.");
        data.Resume();
    }
    
    public void SetPitch(double pitch)
    {
        data.SetPitch((float)pitch);
    }
    
    public void SetVolume(double volume)
    {
        if (volume < 0)
            volume = 0;
        data.SetVolume((float)volume);
    }
    
    public void SetBalance(double position)
    {
        if (position < -1)
            position = - 1;
        else if (position > 1)
            position = 1;
        
        data.SetHorizontalPosition((float)position);
    }
    
    public void SetFade(double fade)
    {
        if (fade < -1)
            fade = - 1;
        else if (fade > 1)
            fade = 1;
        
        data.SetFade((float)fade);
    }
    
    public void SetX(double newX)
    {
        data.SetX((float)newX);
    }
    
    public void SetY(double newY)
    {
        data.SetY((float)newY);
    }
    
    public void SetZ(double newZ)
    {
        data.SetZ((float)newZ);
    }
    
    public double GetX()
    {
        return data.GetX();
    }
    
    public double GetY()
    {
        return data.GetY();
    }
    
    public double GetZ()
    {
        return data.GetZ();
    }
    
    public void SetPosition(double newX, double newY, double newZ)
    {
        data.SetPosition((float)newX, (float)newY, (float)newZ);
    }
    
    public void SetRotation(double rotation)
    {
        data.SetRotation(rotation);
    }
    
    public void Rotate(double rotation)
    {
        data.Rotate(rotation);
    }
    
    public boolean IsStreaming()
    {
        return data.IsStreaming();
    }
    
    public boolean IsPlaying()
    {
        return data.IsPlaying();
    }
    
    public boolean IsLoopingEnabled()
    {
        return data.IsLooping();
    }
    
    public double GetBalance()
    {
        return data.GetBalance();
    }
    
    public double GetVolume()
    {
        return data.GetVolume();
    }
    
    public double GetPitch()
    {
        return data.GetPitch();
    }
    
    public double GetRotation()
    {
        return data.GetRotation();
    }
    
    public double GetFade()
    {
        return data.GetFade();
    }
    
    public void Stream()
    {
        if (data == null)
            throw new RuntimeException("Can't stream audio before it's loaded -- call Load first.");
        data.Update();
    }
    
    public void SetListenerPosition(double x, double y, double z)
    {
        AudioManager.SetListenerPosition(x, y, z);
    }
    
    public void SetListenerX(double x)
    {
        AudioManager.SetListenerPosition(x, AudioManager.GetListenerY(), AudioManager.GetListenerZ());
    }
    
    public void SetListenerY(double y)
    {
        AudioManager.SetListenerPosition(AudioManager.GetListenerX(), y, AudioManager.GetListenerZ());
    }
    
    public void SetListenerZ(double z)
    {
        AudioManager.SetListenerPosition(AudioManager.GetListenerX(), AudioManager.GetListenerY(), z);
    }
    
    public double GetListenerX()
    {
        return AudioManager.GetListenerX();
    }
    
    public double GetListenerY()
    {
        return AudioManager.GetListenerY();
    }
    
    public double GetListenerZ()
    {
        return AudioManager.GetListenerZ();
    }
}
