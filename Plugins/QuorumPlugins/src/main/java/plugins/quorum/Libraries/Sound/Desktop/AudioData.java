/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.Desktop;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.nio.ByteOrder;
import plugins.quorum.Libraries.Sound.AudioSamples;
import quorum.Libraries.Sound.AudioSamples_;
import static org.lwjgl.openal.AL10.*;
import org.lwjgl.openal.AL11;

/**
 *
 * @author alleew
 */
public abstract class AudioData extends DesktopData 
{
    
    private int bufferID = -1;
    private long soundID = -1;
    
    private float duration;
    
    protected AudioSamples_ quorumBuffer;
    protected boolean storeBuffer = false;
    
    void SetUp(byte[] pcm, int channels, int sampleRate)
    {
        int bytes = pcm.length - (pcm.length % (channels > 1 ? 4 : 2));
	int samples = bytes / (2 * channels);
	duration = samples / (float)sampleRate;

	ByteBuffer buffer = ByteBuffer.allocateDirect(bytes);
	buffer.order(ByteOrder.nativeOrder());
	buffer.put(pcm, 0, bytes);
	buffer.flip();
        
	if (bufferID == -1) 
        {
            bufferID = alGenBuffers();
            alBufferData(bufferID, channels > 1 ? AL_FORMAT_STEREO16 : AL_FORMAT_MONO16, buffer.asShortBuffer(), sampleRate);
	}
    }
    
    void SetUp(AudioSamples_ quorumBuffer)
    {
        AudioSamples bufferPlugin = ((quorum.Libraries.Sound.AudioSamples)quorumBuffer).plugin_;
        short[] shortArray = bufferPlugin.buffer;
        
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(shortArray.length * 2);
	byteBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
	shortBuffer.put(shortArray, 0, shortArray.length);
	shortBuffer.flip();
        
	if (bufferID == -1) 
        {
            bufferID = alGenBuffers();
            alBufferData(bufferID, bufferPlugin.channels > 1 ? AL_FORMAT_STEREO16 : AL_FORMAT_MONO16, shortBuffer, bufferPlugin.samplesPerSecond);
	}
    }
    
    @Override
    public void Play () 
    {   
	if (MANAGER.noDevice) 
            return;
        
        int sourceID;
        
        if (MANAGER.SoundIDIsActive(soundID))
            sourceID = (int)MANAGER.soundIDToSource.get(soundID);
        else
        {
            sourceID = MANAGER.ObtainSource(false);
        
            if (sourceID == -1) 
            {
                // Attempt to recover by stopping the least recently played sound
                MANAGER.Retain(this, true);
                sourceID = MANAGER.ObtainSource(false);
            }
            else
                MANAGER.Retain(this, false);
            
            soundID = MANAGER.GetSoundID(sourceID);
        }	
        // In case it still didn't work
	if (sourceID == -1) 
            return;
	
	alSourcei(sourceID, AL_BUFFER, bufferID);
	alSourcei(sourceID, AL_LOOPING, isLooping ? AL_TRUE : AL_FALSE);
	alSourcef(sourceID, AL_GAIN, volume);
        alSourcef(sourceID, AL_REFERENCE_DISTANCE, referenceDistance);
        alSourcef(sourceID, AL_ROLLOFF_FACTOR, rolloff);
	alSourcePlay(sourceID);
        
        if (dopplerEnabled)
            SetVelocity(velocityX, velocityY, velocityZ);
    
        SetPitch(pitch);
        SetPosition(x, y, z);
    }
    
    @Override
    public void Stop () 
    {
        if (MANAGER.noDevice)
            return;
        if (!MANAGER.SoundIDIsActive(soundID))
            return;
        MANAGER.StopSound(soundID);
    }

    @Override
    public void Dispose () 
    {
	if (MANAGER.noDevice) 
            return;
	if (bufferID == -1) 
            return;
	MANAGER.FreeBuffer(bufferID);
	alDeleteBuffers(bufferID);
	bufferID = -1;
	MANAGER.Forget(this);
    }
    
    @Override
    public void Pause()
    {
        if (MANAGER.noDevice)
            return;
        if (!MANAGER.SoundIDIsActive(soundID))
            return;
        MANAGER.PauseSound(soundID);
    }
    
    @Override
    public void Resume()
    {
        if (MANAGER.noDevice)
            return;
        if (!MANAGER.SoundIDIsActive(soundID))
            return;
        MANAGER.ResumeSound(soundID);
    }
    
    @Override
    public void SetPitch(float pitch)
    {
        if (MANAGER.noDevice)
            return;
        
        this.pitch = pitch;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        MANAGER.SetSoundPitch(soundID, pitch);
    }
    
    @Override
    public void SetVolume(float newVolume)
    {
        if (MANAGER.noDevice)
            return;
        
        volume = newVolume;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        MANAGER.SetSoundGain(soundID, volume);
    }
    
    @Override
    public void SetLooping(boolean looping)
    {
        if (MANAGER.noDevice)
            return;
        
        isLooping = looping;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        MANAGER.SetSoundLooping(soundID, looping);
    }
    
    @Override
    public void SetReferenceDistance(float distance)
    {
        if (MANAGER.noDevice)
            return;
        
        this.referenceDistance = distance;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        MANAGER.SetSoundReferenceDistance(soundID, distance);
    }
    
    @Override
    public void SetRolloff(float rolloff)
    {
        if (MANAGER.noDevice)
            return;
        
        this.rolloff = rolloff;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        MANAGER.SetSoundRolloff(soundID, rolloff);
    }
    
    @Override
    public void SetHorizontalPosition(float newPan)
    {
        if (MANAGER.noDevice)
            return;
        
        pan = newPan;
        fade = 0;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        this.x = (float)Math.cos((pan - 1) * (float)Math.PI / 2);
        this.y = (float)Math.sin((pan + 1) * (float)Math.PI / 2);
        this.z = 0;
        
        MANAGER.SetSoundPan(soundID, pan);
    }
    
    @Override
    public void SetFade(float newFade)
    {
        if (MANAGER.noDevice)
            return;
        
        fade = newFade;
        pan = 0;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        this.x = 0;
        this.y = (float)Math.sin((fade + 1) * (float)Math.PI / 2);
        this.z = -(float)Math.cos((fade - 1) * (float)Math.PI / 2);
        
        MANAGER.SetSoundFade(soundID, fade);
    }
    
    @Override
    public void SetX(float newX)
    {
        if (MANAGER.noDevice)
            return;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        this.x = newX;
        
        MANAGER.UpdatePosition(soundID, newX, y, z);
    }
    
    @Override
    public void SetY(float newY)
    {
        if (MANAGER.noDevice)
            return;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        this.y = newY;
        
        MANAGER.UpdatePosition(soundID, x, newY, z);
    }
    
    @Override
    public void SetZ(float newZ)
    {
        if (MANAGER.noDevice)
            return;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        this.z = newZ;
        
        MANAGER.UpdatePosition(soundID, x, y, newZ);
    }
    
    @Override
    public void SetPosition(float newX, float newY, float newZ)
    {
        if (MANAGER.noDevice)
            return;
        
        if (!MANAGER.SoundIDIsActive(soundID))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        
        MANAGER.UpdatePosition(soundID, newX, newY, newZ);
    }
    
    @Override
    public void EnableDoppler()
    {
        if (dopplerEnabled)
            return;
        
        dopplerEnabled = true;
        SetVelocity(velocityX, velocityY, velocityZ);
    }
    
    @Override
    public void DisableDoppler()
    {
        if (!dopplerEnabled || MANAGER.noDevice)
            return;
        
        dopplerEnabled = false;
        
        if (!MANAGER.SoundIDIsActive((soundID)))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        MANAGER.SetSoundVelocity(soundID, 0, 0, 0);
    }
    
    @Override
    public void SetVelocity(float newX, float newY, float newZ)
    {
        if (MANAGER.noDevice)
            return;
        
        if (!MANAGER.SoundIDIsActive((soundID)))
        {
            int sourceID = MANAGER.ObtainSource(false);
            soundID = (long)MANAGER.sourceToSoundID.get(sourceID);
        }
        
        velocityX = newX;
        velocityY = newY;
        velocityZ = newZ;
        
        MANAGER.SetSoundVelocity(soundID, newX, newY, newZ);
    }
    
    // Returns the duration of the sound in seconds.
    public float GetDuration()
    {
        return duration;
    }
    
    @Override
    public boolean IsStreaming()
    {
        return false;
    }
    
    @Override
    public boolean IsPlaying()
    {
        if (!MANAGER.SoundIDIsActive(soundID))
            return false;
        
        int sourceID = (int)MANAGER.soundIDToSource.get(soundID);
        
        return (alGetSourcei(sourceID, AL_SOURCE_STATE) == AL_PLAYING);
    }
    
    @Override
    public boolean IsLooping()
    {
        return isLooping;
    }
    
    @Override
    public void Update()
    {
        throw new RuntimeException("This audio was not set for streaming when loaded. Use LoadToStream to allow streaming the audio.");
    }
    
    @Override
    public void QueueSamples(AudioSamples_ samples)
    {
        throw new RuntimeException("This audio was not set for streaming AudioSamples when loaded. Use LoadToStream(AudioSamples) to allow for sample queueing.");
    }
    
    @Override
    public void UnqueueSamples(AudioSamples_ samples)
    {
        throw new RuntimeException("This audio was not set for streaming AudioSamples when loaded. Use LoadToStream(AudioSamples) to allow for sample queueing.");
    }
    
    @Override
    public int GetSampleOffset()
    {
        return MANAGER.GetSampleOffset(soundID);
    }
}
