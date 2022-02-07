/*
 * Adapted from the OpenALMusic class from LibGDX. You can find their work at:
 * http://libgdx.badlogicgames.com
 */

/*******************************************************************************
 * Copyright 2011 Mario Zechner <badlogicgames@gmail.com>,
 *                Nathan Sweet <nathan.sweet@gmail.com> 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package plugins.quorum.Libraries.Sound.Desktop;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.io.File;
import java.lang.Math;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL11;

import static org.lwjgl.openal.AL10.*;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author alleew
 */
public abstract class StreamingData extends DesktopData 
{
    
    static private final int bufferSize = 4096 * 10;
    static private final int bufferCount = 3;
    static private final int bytesPerSample = 2;
    static private final byte[] tempBytes = new byte[bufferSize];
    static private final ByteBuffer tempBuffer = BufferUtils.createByteBuffer(bufferSize);

    private IntBuffer buffers;
    private int sourceID = -1;
    private int format, sampleRate;
    private float renderedSeconds, secondsPerBuffer;

    protected final File file;
    protected final String filePath;
    protected int bufferOverhead = 0;
    protected boolean isPlaying = false;

    public StreamingData (File file) 
    {
	this.file = file;
        filePath = file.getAbsolutePath();
    }
    
    public StreamingData (String filePath)
    {
        file = null;
        this.filePath = filePath;
    }

    protected void SetUp (int channels, int sampleRate) 
    {
	this.format = channels > 1 ? AL_FORMAT_STEREO16 : AL_FORMAT_MONO16;
	this.sampleRate = sampleRate;
	secondsPerBuffer = (float)(bufferSize - bufferOverhead)  / (bytesPerSample * channels * sampleRate);
    }

    public void Play () 
    {
	if (MANAGER.noDevice)
            return;
	if (sourceID == -1) 
        {
            sourceID = MANAGER.ObtainSource(true);
            if (sourceID == -1)
                return;

            MANAGER.streamedAudio.add(this);

            if (buffers == null) 
            {
		buffers = BufferUtils.createIntBuffer(bufferCount);
		alGenBuffers(buffers);
		if (alGetError() != AL_NO_ERROR)
                    throw new RuntimeException("Unable to allocate audio buffers.");
            }
			
            alSourcei(sourceID, AL_LOOPING, AL_FALSE);
            
            if (dopplerEnabled)
                SetVelocity(velocityX, velocityY, velocityZ);
            
            SetVolume(volume);
            SetPitch(pitch);
            SetReferenceDistance(referenceDistance);
            SetRolloff(rolloff);
            SetPosition(x, y, z);

            boolean filled = false; // Check if there's anything to actually play.
            for (int i = 0; i < bufferCount; i++) 
            {
		int bufferID = buffers.get(i);
		if (!Fill(bufferID))
                    break;
		filled = true;
		alSourceQueueBuffers(sourceID, bufferID);
            }
			
            if (alGetError() != AL_NO_ERROR) 
            {
		Stop();
		return;
            }
	}
	if (!isPlaying) 
        {
            alSourcePlay(sourceID);
            isPlaying = true;
	}
    }

    @Override
    public void Stop () 
    {
	if (MANAGER.noDevice) 
            return;
	if (sourceID == -1) 
            return;
		
        MANAGER.streamedAudio.remove(this);
	Reset();
	MANAGER.FreeSource(sourceID);
	sourceID = -1;
	renderedSeconds = 0;
	isPlaying = false;
    }

    @Override
    public void Pause () 
    {
	if (MANAGER.noDevice)
            return;
	if (sourceID != -1)
            alSourcePause(sourceID);
	isPlaying = false;
    }

    @Override
    public boolean IsPlaying () 
    {
	if (MANAGER.noDevice) 
            return false;
	if (sourceID == -1) 
            return false;
	return isPlaying;
    }
    
    @Override
    public void SetLooping (boolean isLooping) 
    {
	this.isLooping = isLooping;
    }
    
    @Override
    public boolean IsLooping () 
    {
	return isLooping;
    }

    @Override
    public void SetVolume (float volume) 
    {
	this.volume = volume;
	if (MANAGER.noDevice) 
            return;
	if (sourceID != -1) 
            alSourcef(sourceID, AL_GAIN, volume);
    }
    
    @Override
    public float GetVolume () 
    {
	return this.volume;
    }
    
    @Override
    public void SetReferenceDistance(float distance) 
    {
	this.referenceDistance = distance;
	if (MANAGER.noDevice) 
            return;
	if (sourceID != -1) 
            alSourcef(sourceID, AL_REFERENCE_DISTANCE, distance);
    }
    
    @Override
    public void SetRolloff(float rolloff) 
    {
	this.rolloff = rolloff;
	if (MANAGER.noDevice) 
            return;
	if (sourceID != -1) 
            alSourcef(sourceID, AL_ROLLOFF_FACTOR, rolloff);
    }

    @Override
    public void SetHorizontalPosition (float pan) 
    {
	this.pan = pan;
        fade = 0;
	if (MANAGER.noDevice) 
            return;
        
        this.x = (float)Math.cos((pan - 1) * (float)Math.PI / 2);
        this.y = (float)Math.sin((pan + 1) * (float)Math.PI / 2);
        this.z = 0;
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_POSITION, x, y, z);
        alSourcef(sourceID, AL_GAIN, volume);
    }
    
    @Override
    public void SetFade(float newFade) 
    {
	this.fade = newFade;
        pan = 0;
	if (MANAGER.noDevice) 
            return;
        
        this.x = 0;
        this.y = (float)Math.sin((fade + 1) * (float)Math.PI / 2);
        this.z = (float)Math.cos((fade - 1) * (float)Math.PI / 2);
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_POSITION, x, y, z);
        alSourcef(sourceID, AL_GAIN, volume);
    }
    
    @Override
    public void SetX(float newX)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.x = newX;
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_POSITION, newX, y, z);
        alSourcef(sourceID, AL_GAIN, volume);
    }
    
    @Override
    public void SetY(float newY)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.y = newY;
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_POSITION, x, newY, z);
        alSourcef(sourceID, AL_GAIN, volume);
    }
    
    @Override
    public void SetZ(float newZ)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.z = newZ;
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_POSITION, x, y, newZ);
        alSourcef(sourceID, AL_GAIN, volume);
    }
    
    @Override
    public void SetPosition(float newX, float newY, float newZ)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_POSITION, newX, newY, newZ);
        alSourcef(sourceID, AL_GAIN, volume);
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
        if (sourceID == -1)
            return;
        alSource3f(sourceID, AL_VELOCITY, 0, 0, 0);
    }
    
    @Override
    public void SetVelocity(float newX, float newY, float newZ)
    {
        if (MANAGER.noDevice)
            return;
        
        velocityX = newX;
        velocityY = newY;
        velocityZ = newZ;
        
        if (sourceID == -1)
            return;
        
        alSource3f(sourceID, AL_VELOCITY, newX, newY, newZ);
    }

    public void SetPosition (float position) 
    {
	if (MANAGER.noDevice) 
            return;
	if (sourceID == -1) 
            return;
	boolean wasPlaying = isPlaying;
	isPlaying = false;
	alSourceStop(sourceID);
	alSourceUnqueueBuffers(sourceID, buffers);
	renderedSeconds += (secondsPerBuffer * bufferCount);
	if (position <= renderedSeconds) 
        {
            Reset();
            renderedSeconds = 0;
	}
		
        while (renderedSeconds < (position - secondsPerBuffer)) 
        {
            if (Read(tempBytes) <= 0) 
                break;
            renderedSeconds += secondsPerBuffer;
	}
	
        boolean filled = false;
	for (int i = 0; i < bufferCount; i++) 
        {
            int bufferID = buffers.get(i);
            if (!Fill(bufferID)) 
                break;
            filled = true;
            alSourceQueueBuffers(sourceID, bufferID);
	}
		
        if (!filled) 
        {
            Stop();
	}
		
        alSourcef(sourceID, AL11.AL_SEC_OFFSET, position - renderedSeconds);
	if (wasPlaying) 
        {
            alSourcePlay(sourceID);
            isPlaying = true;
	}
    }

    public float GetPosition () 
    {
	if (MANAGER.noDevice)
            return 0;
	if (sourceID == -1)
            return 0;
	return renderedSeconds + alGetSourcef(sourceID, AL11.AL_SEC_OFFSET);
    }

    /** Fills as much of the buffer as possible and returns the number of bytes filled. Returns <= 0 to indicate the end of the
     * stream. */
    abstract public int Read (byte[] buffer);

    /** Resets the stream to the beginning. */
    abstract public void Reset ();

    /** By default, does just the same as reset(). Used to add special behaviour in Ogg.Music. */
    protected void Loop () 
    {
	Reset();
    }

    public int GetChannels () 
    {
	return format == AL_FORMAT_STEREO16 ? 2 : 1;
    }

    public int GetRate () 
    {
	return sampleRate;
    }

    public void Update () 
    {
	if (MANAGER.noDevice)
            return;
	if (sourceID == -1)
            return;

	boolean end = false;
	int buffers = alGetSourcei(sourceID, AL_BUFFERS_PROCESSED);
	while (buffers-- > 0) 
        {
            int bufferID = alSourceUnqueueBuffers(sourceID);
            if (bufferID == AL_INVALID_VALUE) 
                break;
            renderedSeconds += secondsPerBuffer;
			
            if (end) 
                continue;
            if (Fill(bufferID))
                alSourceQueueBuffers(sourceID, bufferID);
            else
                end = true;
	}
	if (end && alGetSourcei(sourceID, AL_BUFFERS_QUEUED) == 0) 
        {
            Stop();
	}

	// A buffer underflow will cause the source to stop.
	if (isPlaying && alGetSourcei(sourceID, AL_SOURCE_STATE) != AL_PLAYING) 
            alSourcePlay(sourceID);
    }

    private boolean Fill (int bufferID) 
    {
	tempBuffer.clear();
	int length = Read(tempBytes);
	if (length <= 0) 
        {
            if (isLooping) 
            {
		Loop();
		renderedSeconds = 0;
		length = Read(tempBytes);
		if (length <= 0)
                    return false;
            } 
            else    
                return false;
	}
        
        tempBuffer.put(tempBytes, 0, length).flip();
	alBufferData(bufferID, format, tempBuffer, sampleRate);
	return true;
    }

    public void Dispose () 
    {
	Stop();
	if (MANAGER.noDevice)
            return;
	if (buffers == null)
            return;
	alDeleteBuffers(buffers);
	buffers = null;
    }

    public int GetSourceId () 
    {
	return sourceID;
    }
    
    @Override
    public boolean IsStreaming()
    {
        return true;
    }
        
    @Override
    public void SetPitch(float pitch)
    {
        this.pitch = pitch;
        
        if (sourceID == -1)
            return;
        
        alSourcef(sourceID, AL_PITCH, pitch);
    }
    
    // If streamed audio is paused, it is resumed by calling Play().
    @Override
    public void Resume()
    {
        Play();
    }
    
    @Override
    public void QueueSamples(AudioSamples_ samples)
    {
        throw new RuntimeException("AudioSamples may not be queued on Audio that is streaming a file. To queue AudioSamples, load the Audio with LoadToStream(AudioSamples) instead.");
    }
    
    @Override
    public void UnqueueSamples(AudioSamples_ samples)
    {
        throw new RuntimeException("AudioSamples may not be queued on Audio that is streaming a file. To queue AudioSamples, load the Audio with LoadToStream(AudioSamples) instead.");
    }
    
    @Override
    public int GetSampleOffset()
    {
        return 0;
    }
    
    public String GetFilePath()
    {
        if (file != null)
            return file.getAbsolutePath();
        else 
            return filePath;
    }
}
