/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.Desktop;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import java.lang.Math;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.ArrayList;

import static org.lwjgl.openal.AL10.*;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import plugins.quorum.Libraries.Sound.AudioManager;

/**
 *
 * @author alleew
 */
public class DesktopAudioManager implements AudioManager
{
    private final int deviceBufferSize;
    private final int deviceBufferCount;
    
    // These static variables contain the last set position of the listener.
    private static double listenerX = 0;
    private static double listenerY = 0;
    private static double listenerZ = 0;
    
    /*
    These static variables contain the last set velocity of the listener.
    The velocity is NOT automatically updated by OpenAL! It must be set and
    maintained manually.
    */
    private static double listenerVelocityX = 0;
    private static double listenerVelocityY = 0;
    private static double listenerVelocityZ = 0;
    private static boolean listenerDopplerEnabled = true;
    
    // These static variables contain the last set orientation of the listener.
    private static double listenerDirectionX = 0;
    private static double listenerDirectionY = 0;
    private static double listenerDirectionZ = -1;
    private static double listenerUpX = 0;
    private static double listenerUpY = 1;
    private static double listenerUpZ = 0;
    
    // These variables are an "IntArray" in libGDX. We have this class already
    // in Libraries/Games/libGDX, but to avoid dependency on the game engine for
    // basic audio, we will instead use a standard array.
    private ArrayList idleSources, allSources;
    
    HashMap soundIDToSource;
    HashMap sourceToSoundID;
    
    private long nextSoundID = 0;
    
    private AudioData[] recentSounds;
    private int mostRecentSound = -1;
    
    boolean noDevice = false;
    ArrayList<StreamingData> streamedAudio = new ArrayList();
    // Array<OpenALMusic> music = new Array(false, 1, OpenALMusic.class);
    
    private long device = 0;
    private long context = 0;
    
    
    public DesktopAudioManager()
    {
        this(64,9,512);
    }
    
    public DesktopAudioManager(int simultaneousSources, int bufferCount, int bufferSize)
    {
        deviceBufferSize = bufferSize;
        deviceBufferCount = bufferCount;
        
        try 
        {
            ALCreate();
	} 
        catch (Exception ex) 
        {
            noDevice = true;
            ex.printStackTrace();
            return;
	}
        
        allSources = new ArrayList(simultaneousSources);
        
        for (int i = 0; i < simultaneousSources; i++)
        {
            int sourceID = alGenSources();
            if (alGetError() != AL_NO_ERROR)
                break;
            allSources.add(sourceID);
        }
        
        idleSources = new ArrayList(allSources);
        
        soundIDToSource = new HashMap();
        sourceToSoundID = new HashMap();
        
        FloatBuffer orientation = (FloatBuffer)BufferUtils.createFloatBuffer(6)
            .put(new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f}).flip();
        alListenerfv(AL_ORIENTATION, orientation);
        
        FloatBuffer velocity = (FloatBuffer)BufferUtils.createFloatBuffer(3)
            .put(new float[] {0.0f, 0.0f, 0.0f}).flip();
	alListenerfv(AL_VELOCITY, velocity);
        
        FloatBuffer position = (FloatBuffer)BufferUtils.createFloatBuffer(3)
            .put(new float[] {0.0f, 0.0f, 0.0f}).flip();
	alListenerfv(AL_POSITION, position);
        
        recentSounds = new AudioData[simultaneousSources];
    }
    
    // This function finds an unused source, sets its properties to default
    // values, then returns the sourceID.
    int ObtainSource(boolean isStreaming)
    {
        if (noDevice)
            return 0;
        for (int i = 0, n = idleSources.size(); i < n; i++)
        {
            int sourceID = (int)idleSources.get(i);
            int state = alGetSourcei(sourceID, AL_SOURCE_STATE);
            if (state != AL_PLAYING && state != AL_PAUSED)
            {
                if (isStreaming)
                {
                    idleSources.remove(i);
                    
                    if (sourceToSoundID.containsKey(sourceID))
                    {
                        long soundID = (long)sourceToSoundID.get(sourceID);
                        sourceToSoundID.remove(sourceID);
                        soundIDToSource.remove(soundID);
                    }
                }
                else
                {
                    if (sourceToSoundID.containsKey(sourceID))
                    {
                        long soundID = (long)sourceToSoundID.get(sourceID);
                        sourceToSoundID.remove(sourceID);
                        soundIDToSource.remove(soundID);
                    }
                    
                    long soundID = nextSoundID++;
                    sourceToSoundID.put(sourceID, soundID);
                    soundIDToSource.put(soundID, sourceID);
                }
                alSourceStop(sourceID);
                alSourcei(sourceID, AL_BUFFER, 0);
                AL10.alSourcef(sourceID, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceID, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceID, AL10.AL_POSITION, 0, 0, 1f);
		return sourceID;
            }
        }
        return -1;
    }
    
    void FreeSource(int sourceID)
    {
        if (noDevice)
            return;
        alSourceStop(sourceID);
        alSourcei(sourceID, AL_BUFFER, 0);
	if (sourceToSoundID.containsKey(sourceID))
        {
            long soundID = (long)sourceToSoundID.remove(sourceID);
            soundIDToSource.remove(soundID);
	}
        
	idleSources.add(sourceID);
    }
    
    void FreeBuffer(int bufferID)
    {
        if (noDevice)
            return;
        for (int i = 0, n = idleSources.size(); i < n; i++)
        {
            int sourceID = (int)idleSources.get(i);
            if (alGetSourcei(sourceID, AL_BUFFER) == bufferID)
            {
                if (sourceToSoundID.containsKey(sourceID))
                {
                    long soundID = (long)sourceToSoundID.remove(sourceID);
                    soundIDToSource.remove(soundID);
                }
                alSourceStop(sourceID);
                alSourcei(sourceID, AL_BUFFER, 0);
            }
        }
    }
    
    void StopSourcesWithBuffer(int bufferID)
    {
        if (noDevice)
            return;
        for (int i = 0, n = idleSources.size(); i < n; i++)
        {
            int sourceID = (int)idleSources.get(i);
            if (alGetSourcei(sourceID, AL_BUFFER) == bufferID)
            {
                if (sourceToSoundID.containsKey(sourceID))
                {
                    long soundID = (long)sourceToSoundID.remove(sourceID);
                    soundIDToSource.remove(soundID);
                }
                alSourceStop(sourceID);
            }
        }
    }
    
    void PauseSourcesWithBuffer(int bufferID)
    {
        if (noDevice)
            return;
        for (int i = 0, n = idleSources.size(); i < n; i++)
        {
            int sourceID = (int)idleSources.get(i);
            if (alGetSourcei(sourceID, AL_BUFFER) == bufferID)
		alSourcePause(sourceID);
        }
    }
    
    
    void ResumeSourcesWithBuffer (int bufferID) 
    {
	if (noDevice) 
            return;
	for (int i = 0, n = idleSources.size(); i < n; i++) 
        {
            int sourceID = (int)idleSources.get(i);
            if (alGetSourcei(sourceID, AL_BUFFER) == bufferID) 
            {
                if (alGetSourcei(sourceID, AL_SOURCE_STATE) == AL_PAUSED)
		alSourcePlay(sourceID);
            }
        }
    }
    
    public long GetSoundID (int sourceID) 
    {
	if (!sourceToSoundID.containsKey(sourceID)) 
            return -1;
	return (long)sourceToSoundID.get(sourceID);
    }
    
    /*public void Update () 
    {
	if (noDevice) 
            return;
	for (int i = 0; i < music.size; i++)
			music.items[i].update();
    }*/
    
    public void StopSound (long soundID) 
    {
	if (!SoundIDIsActive(soundID))
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	alSourceStop(sourceId);
    }
	
    public void PauseSound (long soundID) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	alSourcePause(sourceId);
    }
	
    public void ResumeSound (long soundID) 
    {
	if (!SoundIDIsActive(soundID))
            return;
        int sourceId = (int)soundIDToSource.get(soundID);
	if (alGetSourcei(sourceId, AL_SOURCE_STATE) == AL_PAUSED)
            alSourcePlay(sourceId);
    }
    
    public void SetSoundGain (long soundID, float volume) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
    }

    public void SetSoundLooping (long soundID, boolean looping) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	alSourcei(sourceId, AL10.AL_LOOPING, looping ? AL10.AL_TRUE : AL10.AL_FALSE);
    }

    public void SetSoundPitch (long soundID, float pitch) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	AL10.alSourcef(sourceId, AL10.AL_PITCH, pitch);
    }
    
    public void SetSoundReferenceDistance (long soundID, float distance) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	AL10.alSourcef(sourceId, AL10.AL_REFERENCE_DISTANCE, distance);
    }
    
    public void SetSoundRolloff (long soundID, float rolloff) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceId = (int)soundIDToSource.get(soundID);
	AL10.alSourcef(sourceId, AL10.AL_ROLLOFF_FACTOR, rolloff);
    }
    
    public void SetSoundPan (long soundID, float pan) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceID = (int)soundIDToSource.get(soundID);

        // This next call establishes the sound at XYZ coordinates.
	AL10.alSource3f(sourceID, AL10.AL_POSITION, (float)Math.cos((pan - 1) * (float)Math.PI / 2), (float)Math.sin((pan + 1) * (float)Math.PI / 2), 0);
    }
    
    public void SetSoundFade (long soundID, float pan) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceID = (int)soundIDToSource.get(soundID);

        // This next call establishes the sound at XYZ coordinates.
	AL10.alSource3f(sourceID, AL10.AL_POSITION, 0, (float)Math.cos((pan - 1) * (float)Math.PI / 2),
			(float)Math.sin((pan + 1) * (float)Math.PI / 2));
    }
    
    public void UpdatePosition(long soundID, float newX, float newY, float newZ)
    {
        if (!SoundIDIsActive(soundID)) 
            return;
	int sourceID = (int)soundIDToSource.get(soundID);
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, newX, newY, newZ);
    }
    
    public void SetSoundVelocity(long soundID, float newX, float newY, float newZ)
    {
        if (!SoundIDIsActive(soundID))
            return;
        
        int sourceID = (int)soundIDToSource.get(soundID);
        
        AL10.alSource3f(sourceID, AL10.AL_VELOCITY, newX, newY, newZ);
    }
    
    public int GetSampleOffset(long soundID)
    {
        if (!SoundIDIsActive(soundID))
            return 0;
        
        int sourceID = (int)soundIDToSource.get(soundID);
        
        return AL10.alGetSourcei(sourceID, AL11.AL_SAMPLE_OFFSET);
    }

    public void Dispose () 
    {
        if (noDevice) 
            return;
        for (int i = 0, n = allSources.size(); i < n; i++) 
        {
            int sourceID = (int)allSources.get(i);
            int state = alGetSourcei(sourceID, AL_SOURCE_STATE);
            if (state != AL_STOPPED) 
                alSourceStop(sourceID);
            alDeleteSources(sourceID);
        }

        sourceToSoundID.clear();
        soundIDToSource.clear();

        ALDestroy();
    }
    
    private void ALCreate()
    {
        device = ALC10.alcOpenDevice((java.nio.ByteBuffer)null);
        ALCCapabilities caps = ALC.createCapabilities(device);
        
        context = ALC10.alcCreateContext(device, (java.nio.IntBuffer)null);
        ALC10.alcMakeContextCurrent(context);
        AL.createCapabilities(caps);
    }
    
    private void ALDestroy()
    {
        ALC10.alcMakeContextCurrent(0);
        ALC10.alcDestroyContext(context);
        ALC10.alcCloseDevice(device);
        context = 0;
        device = 0;
    }
        
    /** Retains a list of the most recently played sounds and stops the sound played least recently if necessary for a new sound to
     * play */
    protected void Retain (AudioData sound, boolean stop) 
    {
        // Move the pointer ahead and wrap
        mostRecentSound++;
        mostRecentSound %= recentSounds.length;

        if (stop) 
        {
            // Stop the least recent sound (the one we are about to bump off the buffer)
            if (recentSounds[mostRecentSound] != null) recentSounds[mostRecentSound].Stop();
        }

        recentSounds[mostRecentSound] = sound;
    }
        
    // Removes the disposed sound from the least recently played list 
    public void Forget(AudioData sound) 
    {
            for (int i = 0; i < recentSounds.length; i++) {
                    if (recentSounds[i] == sound) recentSounds[i] = null;
            }
    }

    // Tests if the given soundID is active and has a reserved source.
    public boolean SoundIDIsActive(long soundID)
    {
        return soundIDToSource.containsKey(soundID);
    }
    
    public void SetListenerPosition(double x, double y, double z)
    {
        listenerX = x;
        listenerY = y;
        listenerZ = z;
        alListener3f(AL_POSITION, (float)x, (float)y, (float)z);
    }
    
    public double GetListenerX()
    {
        return listenerX;
    }
    
    public double GetListenerY()
    {
        return listenerY;
    }
    
    public double GetListenerZ()
    {
        return listenerZ;
    }
    
    public void SetListenerVelocity(double x, double y, double z)
    {
        listenerVelocityX = x;
        listenerVelocityY = y;
        listenerVelocityZ = z;
        
        if (listenerDopplerEnabled)
            alListener3f(AL_VELOCITY, (float)x, (float)y, (float)z);
    }
    
    public double GetListenerVelocityX()
    {
        return listenerVelocityX;
    }
    
    public double GetListenerVelocityY()
    {
        return listenerVelocityY;
    }
    
    public double GetListenerVelocityZ()
    {
        return listenerVelocityZ;
    }
    
    public void EnableListenerDoppler()
    {
        if (listenerDopplerEnabled)
            return;
        
        listenerDopplerEnabled = true;
        SetListenerVelocity(listenerVelocityX, listenerVelocityY, listenerVelocityZ);
    }
    
    public void DisableListenerDoppler()
    {
        if (!listenerDopplerEnabled)
            return;
        
        listenerDopplerEnabled = false;
        alListener3f(AL_VELOCITY, 0, 0, 0);
    }
    
    public boolean IsListenerDopplerEnabled()
    {
        return listenerDopplerEnabled;
    }
    
    public void SetListenerDirection(double x, double y, double z)
    {
        FloatBuffer orientation = (FloatBuffer)BufferUtils.createFloatBuffer(6)
            .put(new float[] {(float)x, (float)y, (float)z, (float)listenerUpX, (float)listenerUpY, (float)listenerUpZ}).flip();
        
        alListenerfv(AL_ORIENTATION, orientation);
    }
    
    public void SetListenerUp(double x, double y, double z)
    {
        FloatBuffer orientation = (FloatBuffer)BufferUtils.createFloatBuffer(6)
            .put(new float[] {(float)listenerDirectionX, (float)listenerDirectionY, (float)listenerDirectionZ, (float)x, (float)y, (float)z}).flip();
        
        alListenerfv(AL_ORIENTATION, orientation);
    }
    
    public double GetListenerDirectionX()
    {
        return listenerDirectionX;
    }
    
    public double GetListenerDirectionY()
    {
        return listenerDirectionY;
    }
    
    public double GetListenerDirectionZ()
    {
        return listenerDirectionZ;
    }
    
    public double GetListenerUpX()
    {
        return listenerUpX;
    }
    
    public double GetListenerUpY()
    {
        return listenerUpY;
    }
    
    public double GetListenerUpZ()
    {
        return listenerUpZ;
    }
    
}
