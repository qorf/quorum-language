/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;

import java.lang.Math;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.ArrayList;

import static org.lwjgl.openal.AL10.*;

/**
 *
 * @author alleew
 */
public class AudioManager {
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
    
    public AudioManager()
    {
        this(16,9,512);
    }
    
    public AudioManager(int simultaneousSources, int bufferCount, int bufferSize)
    {
        deviceBufferSize = bufferSize;
        deviceBufferCount = bufferCount;
        
        try 
        {
            AL.create();
	} 
        catch (LWJGLException ex) 
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
        alListener(AL_ORIENTATION, orientation);
        
        FloatBuffer velocity = (FloatBuffer)BufferUtils.createFloatBuffer(3)
            .put(new float[] {0.0f, 0.0f, 0.0f}).flip();
	alListener(AL_VELOCITY, velocity);
        
        FloatBuffer position = (FloatBuffer)BufferUtils.createFloatBuffer(3)
            .put(new float[] {0.0f, 0.0f, 0.0f}).flip();
	alListener(AL_POSITION, position);
        
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
    
    public void SetSoundPan (long soundID, float pan) 
    {
	if (!SoundIDIsActive(soundID)) 
            return;
	int sourceID = (int)soundIDToSource.get(soundID);

        // This next call establishes the sound at XYZ coordinates.
	AL10.alSource3f(sourceID, AL10.AL_POSITION, (float)Math.cos((pan - 1) * (float)Math.PI / 2), 0,
			(float)Math.sin((pan + 1) * (float)Math.PI / 2));
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

        AL.destroy();
        while (AL.isCreated()) 
        {
            try 
            {
                Thread.sleep(10);
            } 
            catch (InterruptedException e) 
            {
            // Ignored exception
            }
        }
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
    
    public static void SetListenerPosition(double x, double y, double z)
    {
        listenerX = x;
        listenerY = y;
        listenerZ = z;
        alListener3f(AL_POSITION, (float)x, (float)y, (float)z);
    }
    
    public static double GetListenerX()
    {
        return listenerX;
    }
    
    public static double GetListenerY()
    {
        return listenerY;
    }
    
    public static double GetListenerZ()
    {
        return listenerZ;
    }
    
    public static void SetListenerVelocity(double x, double y, double z)
    {
        listenerVelocityX = x;
        listenerVelocityY = y;
        listenerVelocityZ = z;
        
        if (listenerDopplerEnabled)
            alListener3f(AL_VELOCITY, (float)x, (float)y, (float)z);
    }
    
    public static double GetListenerVelocityX()
    {
        return listenerVelocityX;
    }
    
    public static double GetListenerVelocityY()
    {
        return listenerVelocityY;
    }
    
    public static double GetListenerVelocityZ()
    {
        return listenerVelocityZ;
    }
    
    public static void EnableListenerDoppler()
    {
        if (listenerDopplerEnabled)
            return;
        
        listenerDopplerEnabled = true;
        SetListenerVelocity(listenerVelocityX, listenerVelocityY, listenerVelocityZ);
    }
    
    public static void DisableListenerDoppler()
    {
        if (!listenerDopplerEnabled)
            return;
        
        listenerDopplerEnabled = false;
        alListener3f(AL_VELOCITY, 0, 0, 0);
    }
    
    public static boolean IsListenerDopplerEnabled()
    {
        return listenerDopplerEnabled;
    }
    
    /*
    FloatBuffer orientation = (FloatBuffer)BufferUtils.createFloatBuffer(6)
            .put(new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f}).flip();
        alListener(AL_ORIENTATION, orientation);
    */
    public static void SetListenerDirection(double x, double y, double z)
    {
        FloatBuffer orientation = (FloatBuffer)BufferUtils.createFloatBuffer(6)
            .put(new float[] {(float)x, (float)y, (float)z, (float)listenerUpX, (float)listenerUpY, (float)listenerUpZ}).flip();
        
        alListener(AL_ORIENTATION, orientation);
    }
    
    public static void SetListenerUp(double x, double y, double z)
    {
        FloatBuffer orientation = (FloatBuffer)BufferUtils.createFloatBuffer(6)
            .put(new float[] {(float)listenerDirectionX, (float)listenerDirectionY, (float)listenerDirectionZ, (float)x, (float)y, (float)z}).flip();
        
        alListener(AL_ORIENTATION, orientation);
    }
    
    public static double GetListenerDirectionX()
    {
        return listenerDirectionX;
    }
    
    public static double GetListenerDirectionY()
    {
        return listenerDirectionY;
    }
    
    public static double GetListenerDirectionZ()
    {
        return listenerDirectionZ;
    }
    
    public static double GetListenerUpX()
    {
        return listenerUpX;
    }
    
    public static double GetListenerUpY()
    {
        return listenerUpY;
    }
    
    public static double GetListenerUpZ()
    {
        return listenerUpZ;
    }
    
    /*
	public AudioDevice newAudioDevice (int sampleRate, final boolean isMono) {
		if (noDevice) return new AudioDevice() {
			@Override
			public void writeSamples (float[] samples, int offset, int numSamples) {
			}

			@Override
			public void writeSamples (short[] samples, int offset, int numSamples) {
			}

			@Override
			public void setVolume (float volume) {
			}

			@Override
			public boolean isMono () {
				return isMono;
			}

			@Override
			public int getLatency () {
				return 0;
			}

			@Override
			public void dispose () {
			}
		};
		return new OpenALAudioDevice(this, sampleRate, isMono, deviceBufferSize, deviceBufferCount);
	}

	public AudioRecorder newAudioRecorder (int samplingRate, boolean isMono) {
		if (noDevice) return new AudioRecorder() {
			@Override
			public void read (short[] samples, int offset, int numSamples) {
			}

			@Override
			public void dispose () {
			}
		};
		return new JavaSoundAudioRecorder(samplingRate, isMono);
	}
}
    */
    
    
}
