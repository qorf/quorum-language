package plugins.quorum.Libraries.Sound;

import plugins.quorum.Libraries.Sound.Android.AndroidLoader;
import plugins.quorum.Libraries.Sound.Desktop.DesktopAudioManager;
import plugins.quorum.Libraries.Sound.Desktop.DesktopLoader;
import plugins.quorum.Libraries.Sound.Desktop.AudioData;
import plugins.quorum.Libraries.Sound.IOS.IOSLoader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugins.quorum.Libraries.Sound.Android.AndroidAudioManager;
import plugins.quorum.Libraries.Sound.Desktop.RawStreamingData;
import plugins.quorum.Libraries.Sound.IOS.IOSAudioManager;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author alleew
 */
public class Audio {
    
    public java.lang.Object me_ = null;
    
    Data data;
    
    public static final DataLoader loader;
    public static final AudioManager audioManager;
    
    static
    {
        String os = System.getProperty("os.name");
        
        /*
        We only need to find LWJGL if we are on a Desktop. If we are on Mac, 
        OpenAL will be accessed via static library.
        */
        if (os.contains("Windows") || os.contains("Mac") || os.contains("Linux"))
        {
            if (os.contains("Linux") && System.getProperty("java.runtime.name").contains("Android Runtime"))
            {
                System.loadLibrary("GameEngineCPlugins");
                audioManager = new AndroidAudioManager();
                loader = new AndroidLoader((AndroidAudioManager)audioManager);
            }
            else
            {
                try 
                {
                    URI uri = Audio.class.getProtectionDomain().getCodeSource().getLocation().toURI();
                    String uriPath = uri.getPath();

                    if (uri.getAuthority() != null)
                        uriPath = "\\\\" + uri.getAuthority() + uriPath;

                    java.io.File file = new java.io.File(uriPath);

                    String runLocation = file.getParentFile().getAbsolutePath();
                    String lwjgl = runLocation + "/jni";
                    System.setProperty("org.lwjgl.librarypath", lwjgl);
                } 
                catch (URISyntaxException ex) 
                {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                }
                loader = new DesktopLoader();
                audioManager = new DesktopAudioManager();
            }
        }
        else
        {
            loader = new IOSLoader();
            audioManager = new IOSAudioManager();
        }
    }
    
    public void Load(quorum.Libraries.System.File_ quorumFile)
    {
        if (data != null)
            throw new RuntimeException("This audio has already been loaded! To reuse this audio, call Dispose() before loading again.");
        
        data = loader.Load(quorumFile);
    }
    
    public void LoadToStream(quorum.Libraries.System.File_ quorumFile)
    {
        if (data != null)
            throw new RuntimeException("This audio has already been loaded! To reuse this audio, call Dispose() before loading again.");
        
        data = loader.LoadToStream(quorumFile);
    }
    
    public void Load(AudioSamples_ samples)
    {
        if (data != null)
            throw new RuntimeException("This audio has already been loaded! To reuse this audio, call Dispose() before loading again.");
        
        data = loader.Load(samples);
    }
    
    public void LoadToStream(AudioSamples_ samples)
    {
        if (data != null)
            throw new RuntimeException("This audio has already been loaded! To reuse this audio, call Dispose() before loading again.");
        
        data = loader.LoadToStream(samples);
    }
    
    public boolean IsLoaded()
    {
        return data != null;
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
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetPitch((float)pitch);
    }
    
    public void SetVolume(double volume)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        if (volume < 0)
            volume = 0;
        data.SetVolume((float)volume);
    }
    
    public void SetMaximumVolumeDistance(double distance)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        if (distance < 0.01)
            distance = 0.01;
        data.SetReferenceDistance((float)distance);
    }
    
    public void SetRolloffRate(double rolloff)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        if (rolloff < 0)
            rolloff = 0;
        data.SetRolloff((float)rolloff);
    }
    
    public void SetDefaultMaximumVolumeDistance(double distance)
    {
        if (distance < 0.01)
            distance = 0.01;
        AudioData.SetDefaultReferenceDistance((float)distance);
    }
    
    public void SetDefaultRolloffRate(double rolloff)
    {
        if (rolloff < 0)
            rolloff = 0;
        AudioData.SetDefaultRolloff((float)rolloff);
    }
    
    public void SetBalance(double position)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        if (position < -1)
            position = - 1;
        else if (position > 1)
            position = 1;
        
        data.SetHorizontalPosition((float)position);
    }
    
    public void SetFade(double fade)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        if (fade < -1)
            fade = - 1;
        else if (fade > 1)
            fade = 1;
        
        data.SetFade((float)fade);
    }
    
    public void SetX(double newX)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetX((float)newX);
    }
    
    public void SetY(double newY)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetY((float)newY);
    }
    
    public void SetZ(double newZ)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetZ((float)newZ * -1);
    }
    
    public double GetX()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetX();
    }
    
    public double GetY()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetY();
    }
    
    public double GetZ()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetZ() * -1;
    }
    
    public void SetPosition(double newX, double newY, double newZ)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetPosition((float)newX, (float)newY, (float)newZ * -1);
    }
    
    public void EnableDoppler()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.EnableDoppler();
    }
    
    public void DisableDoppler()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.DisableDoppler();
    }
    
    public boolean IsDopplerEnabled()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.IsDopplerEnabled();
    }
    
    public void SetVelocityX(double x)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetVelocityX((float)x);
    }
    
    public void SetVelocityY(double y)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetVelocityY((float)y);
    }
    
    public void SetVelocityZ(double z)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetVelocityZ((float)z * -1);
    }
    
    public void SetVelocity(double x, double y, double z)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetVelocity((float)x, (float)y, (float)z * -1);
    }
    
    public double GetVelocityX()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.velocityX;
    }
    
    public double GetVelocityY()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.velocityY;
    }

    public double GetVelocityZ()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.velocityZ * -1;
    }
    
    public void SetRotation(double rotation)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.SetRotation(rotation);
    }
    
    public void Rotate(double rotation)
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        data.Rotate(rotation);
    }
    
    public boolean IsStreaming()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.IsStreaming();
    }
    
    public boolean IsPlaying()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.IsPlaying();
    }
    
    public boolean IsLoopingEnabled()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.IsLooping();
    }
    
    public double GetBalance()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetBalance();
    }
    
    public double GetVolume()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetVolume();
    }
    
    public double GetPitch()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetPitch();
    }
    
    public double GetMaximumVolumeDistance()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetReferenceDistance();
    }
    
    public double GetRolloffRate()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetRolloff();
    }
    
    public double GetDefaultMaximumVolumeDistance()
    {
        return AudioData.GetDefaultReferenceDistance();
    }
    
    public double GetDefaultRolloffRate()
    {
        return AudioData.GetDefaultRolloff();
    }
    
    public double GetRotation()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetRotation();
    }
    
    public double GetFade()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetFade();
    }
    
    public void Stream()
    {
        if (data == null)
            throw new RuntimeException("Can't stream audio before it's loaded -- call LoadToStream() first.");
        data.Update();
    }
    
    public void AddToQueue(AudioSamples_ samples)
    {
        if (data == null)
            data = loader.LoadToStream(samples);
        else
            data.QueueSamples(samples);
    }
    
    public void RemoveFromQueue(AudioSamples_ samples)
    {
        if (data == null)
            throw new RuntimeException("Can't dequeue samples before the Audio has been loaded - call LoadToStream() or AddToQueue() first.");
        data.UnqueueSamples(samples);
    }
    
    public void SetListenerPosition(double x, double y, double z)
    {
        audioManager.SetListenerPosition(x, y, z * -1);
    }
    
    public void SetListenerX(double x)
    {
        audioManager.SetListenerPosition(x, audioManager.GetListenerY(), audioManager.GetListenerZ());
    }
    
    public void SetListenerY(double y)
    {
        audioManager.SetListenerPosition(audioManager.GetListenerX(), y, audioManager.GetListenerZ());
    }
    
    public void SetListenerZ(double z)
    {
        audioManager.SetListenerPosition(audioManager.GetListenerX(), audioManager.GetListenerY(), z * -1);
    }
    
    public double GetListenerX()
    {
        return audioManager.GetListenerX();
    }
    
    public double GetListenerY()
    {
        return audioManager.GetListenerY();
    }
    
    public double GetListenerZ()
    {
        return audioManager.GetListenerZ() * -1;
    }
    
    public void SetListenerVelocity(double x, double y, double z)
    {
        audioManager.SetListenerVelocity(x, y, z * -1);
    }
    
    public void SetListenerVelocityX(double x)
    {
        SetListenerVelocity(x, audioManager.GetListenerVelocityY(), audioManager.GetListenerVelocityZ());
    }
    
    public void SetListenerVelocityY(double y)
    {
        SetListenerVelocity(audioManager.GetListenerVelocityX(), y, audioManager.GetListenerVelocityZ());
    }
    
    public void SetListenerVelocityZ(double z)
    {
        SetListenerVelocity(audioManager.GetListenerVelocityX(), audioManager.GetListenerVelocityY(), z * -1);
    }
    
    public double GetListenerVelocityX()
    {
        return audioManager.GetListenerVelocityX();
    }
    
    public double GetListenerVelocityY()
    {
        return audioManager.GetListenerVelocityY();
    }
    
    public double GetListenerVelocityZ()
    {
        return audioManager.GetListenerVelocityZ() * -1;
    }
    
    public void EnableListenerDoppler()
    {
        audioManager.EnableListenerDoppler();
    }
    
    public void DisableListenerDoppler()
    {
        audioManager.DisableListenerDoppler();
    }
    
    public boolean IsListenerDopplerEnabled()
    {
        return audioManager.IsListenerDopplerEnabled();
    }
    
    public void SetListenerDirection(double x, double y, double z)
    {
        audioManager.SetListenerDirection(x, y, z * -1);
    }
    
    public void SetListenerUp(double x, double y, double z)
    {
        audioManager.SetListenerUp(x, y, z * -1);
    }
    
    public double GetListenerDirectionX()
    {
        return audioManager.GetListenerDirectionX();
    }
    
    public double GetListenerDirectionY()
    {
        return audioManager.GetListenerDirectionY();
    }
    
    public double GetListenerDirectionZ()
    {
        return audioManager.GetListenerDirectionZ() * -1;
    }
    
    public double GetListenerUpX()
    {
        return audioManager.GetListenerUpX();
    }
    
    public double GetListenerUpY()
    {
        return audioManager.GetListenerUpY();
    }
    
    public double GetListenerUpZ()
    {
        return audioManager.GetListenerUpZ() * -1;
    }
    
    public int GetSampleOffset()
    {
        if (data == null)
            throw new RuntimeException("This Audio object hasn't been loaded yet.");
        return data.GetSampleOffset();
    }
    
    public AudioSamples_ GetCurrentAudioSamples()
    {
        if (data instanceof RawStreamingData)
            return ((RawStreamingData)data).GetCurrentAudioSamples();
        
        throw new RuntimeException("GetCurrentAudioSamples is only supported for audio that is streaming AudioSamples on desktop platforms.");
    }
}
