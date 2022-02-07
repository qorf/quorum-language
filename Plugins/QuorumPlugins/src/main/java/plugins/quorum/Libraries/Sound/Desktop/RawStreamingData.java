/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.Desktop;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import plugins.quorum.Libraries.Sound.AudioSamples;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author Bill
 */
public class RawStreamingData extends DesktopData
{
    protected ArrayList<AudioSamples_> samplesArray = new ArrayList<>();
    protected IntBuffer buffers;
    protected int bufferCount = 5;
    protected ArrayList<Integer> unusedBuffers = new ArrayList<>();
    protected ArrayList<Integer> bufferQueue = new ArrayList<>();
    protected int sourceID = -1;
    
    /* 
    Maps AudioSamples in use to OpenAL buffers and vice versa. This allows the
    user to unqueue samples already in use, and remove references to samples
    when only the bufferID is known.
    */
    private final HashMap<AudioSamples_, Integer> samplesToBuffers = new HashMap<>();
    private final HashMap<Integer, AudioSamples_> buffersToSamples = new HashMap<>();
    
    public RawStreamingData()
    {
        
    }
    
    public RawStreamingData(AudioSamples_ samples)
    {
        QueueSamples(samples);
    }
    
    protected void MapSamplesToBuffer(AudioSamples_ samples, int buffer)
    {
        samplesToBuffers.put(samples, buffer);
        buffersToSamples.put(buffer, samples);
    }
    
    protected int UnmapSamples(AudioSamples_ samples)
    {
        int buffer = samplesToBuffers.remove(samples);
        buffersToSamples.remove(buffer);
        return buffer;
    }
    
    protected AudioSamples_ UnmapBuffer(int buffer)
    {
        AudioSamples_ samples = buffersToSamples.remove(buffer);
        samplesToBuffers.remove(samples);
        return samples;
    }
    
    @Override
    public void QueueSamples(AudioSamples_ samples)
    {
        samplesArray.add(samples);
    }
    
    @Override
    public void UnqueueSamples(AudioSamples_ samples)
    {
        if (samplesArray.remove(samples) == true)
            return;
        
        RecycleBuffers();
        
        if (samplesToBuffers.containsKey(samples))
        {
            int bufferID = samplesToBuffers.get(samples);
            int sampleOffset = -1;
            
            int sourceState = AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE);
            
            if (sourceState == AL10.AL_PLAYING || sourceState == AL10.AL_PAUSED)
            {
                sampleOffset = AL10.alGetSourcei(sourceID, AL11.AL_SAMPLE_OFFSET);
                AL10.alSourceStop(sourceID);
            }
            
            boolean first = true;
            int buffersProcessed = AL10.alGetSourcei(sourceID, AL10.AL_BUFFERS_PROCESSED);
            int i = buffersProcessed;
            while (i > 0) 
            {
                int currentID = UnqueueBuffer(sourceID);
                if (currentID == AL10.AL_INVALID_VALUE) 
                    break;
                
                if (i == buffersProcessed && currentID != bufferID)
                    first = false;

                i--;
                
                if (currentID == bufferID)
                {
                    // This buffer is discarded and may be reused.
                    unusedBuffers.add(currentID);
                    UnmapBuffer(currentID);
                }
                else
                {
                    // This buffer should be requeued.
                    QueueBuffer(sourceID, currentID);
                }
            }
            
            if (sampleOffset != -1)
            {
                // If it wasn't the first buffer, set the position of the audio using sampleOffset.
                if (!first)
                    AL10.alSourcei(sourceID, AL11.AL_SAMPLE_OFFSET, sampleOffset);
                
                AL10.alSourcePlay(sourceID);
                if (sourceState == AL10.AL_PAUSED)
                    AL10.alSourcePause(sourceID);
            }
            
        }
    }
    
    protected int GetAvailableBuffer()
    {
        if (unusedBuffers.isEmpty())
        {
            RecycleBuffers();
            
            if (unusedBuffers.isEmpty())
                return -1;
        }
        
        return unusedBuffers.remove(0);
    }
    
    private void RecycleBuffers()
    {
        int buffersProcessed = AL10.alGetSourcei(sourceID, AL10.AL_BUFFERS_PROCESSED);
        while (buffersProcessed-- > 0) 
        {
            int bufferID = UnqueueBuffer(sourceID);
            if (bufferID == AL10.AL_INVALID_VALUE) 
                break;
            unusedBuffers.add(bufferID);
            UnmapBuffer(bufferID);
        }
    }
    
    private void FillBuffer(int bufferID, AudioSamples_ samples)
    {
        AudioSamples bufferPlugin = ((quorum.Libraries.Sound.AudioSamples)samples).plugin_;
        short[] shortArray = bufferPlugin.buffer;
        
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(shortArray.length * 2);
	byteBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
	shortBuffer.put(shortArray, 0, shortArray.length);
	shortBuffer.flip();
        
        AL10.alBufferData(bufferID, bufferPlugin.channels > 1 ? AL10.AL_FORMAT_STEREO16 : AL10.AL_FORMAT_MONO16, shortBuffer, bufferPlugin.samplesPerSecond);
        MapSamplesToBuffer(samples, bufferID);
    }
    
    @Override
    public void Play()
    {
        if (sourceID == -1)
        {
            sourceID = MANAGER.ObtainSource(true);
            if (sourceID == -1)
                return;
            
            if (buffers == null)
            {
                buffers = BufferUtils.createIntBuffer(bufferCount);
                AL10.alGenBuffers(buffers);
                if (AL10.alGetError() != AL10.AL_NO_ERROR)
                    throw new RuntimeException("Unable to allocate audio buffers.");
                for (int i = 0; i < bufferCount; i++)
                    unusedBuffers.add(buffers.get(i));
            }
            
            AL10.alSourcei(sourceID, AL10.AL_LOOPING, AL10.AL_FALSE);
            
            if (dopplerEnabled)
                SetVelocity(velocityX, velocityY, velocityZ);
            
            SetPitch(pitch);
            SetVolume(volume);
            SetReferenceDistance(referenceDistance);
            SetRolloff(rolloff);
            SetPosition(x, y, z);
        }
        if (!IsPlaying()) 
        {
            // Use Update to fill the buffers.
            Update();
            AL10.alSourcePlay(sourceID);
	}
    }
    
    @Override
    public void Update()
    {
        if (MANAGER.noDevice)
            return;
        if (sourceID == -1)
            return;
        
        int bufferID;
        /*
        If the samples array isn't empty, fetch an available buffer. If one is
        available, enter loop - if the value is -1, no buffer was available.
        */
        while (!samplesArray.isEmpty() && (bufferID = GetAvailableBuffer()) != -1)
        {
            FillBuffer(bufferID, samplesArray.remove(0));
            QueueBuffer(sourceID, bufferID);
        }
    }
    
    @Override
    public void SetLooping(boolean looping)
    {
        if (looping == true)
            throw new UnsupportedOperationException("Looping is not supported for directly streamed AudioSamples.");
    }
    
    @Override
    public void Stop()
    {
        if (MANAGER.noDevice) 
            return;
	if (sourceID == -1) 
            return;
        
        AL10.alSourceStop(sourceID);
        RecycleBuffers();
	MANAGER.FreeSource(sourceID);
	sourceID = -1;
        samplesArray.clear();
    }
    
    @Override
    public void Dispose()
    {
        Stop();
        AL10.alDeleteBuffers(buffers);
        unusedBuffers.clear();
        buffers = null;
    }
    
    @Override
    public void Pause()
    {
        if (MANAGER.noDevice)
            return;
	if (sourceID != -1)
            AL10.alSourcePause(sourceID);
    }
    
    @Override
    public void Resume()
    {
        if (AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PAUSED)
            AL10.alSourcePlay(sourceID);
    }
    
    @Override
    public void SetPitch(float pitch)
    {
        this.pitch = pitch;
        
        if (sourceID == -1)
            return;
        
        AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
    }
    
    @Override
    public void SetVolume(float volume)
    {
        this.volume = volume;
	if (MANAGER.noDevice) 
            return;
	if (sourceID != -1) 
            AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetHorizontalPosition(float position)
    {
        this.pan = position;
        fade = 0;
	if (MANAGER.noDevice) 
            return;
        
        this.x = (float)Math.cos((pan - 1) * (float)Math.PI / 2);
        this.y = (float)Math.sin((pan + 1) * (float)Math.PI / 2);
        this.z = 0;
        
        if (sourceID == -1)
            return;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
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
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetX(float newX)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.x = newX;
        
        if (sourceID == -1)
            return;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, newX, y, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetY(float newY)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.y = newY;
        
        if (sourceID == -1)
            return;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, newY, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetZ(float newZ)
    {
        if (MANAGER.noDevice) 
            return;
        
        this.z = newZ;
        
        if (sourceID == -1)
            return;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, newZ);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
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
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, newX, newY, newZ);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public boolean IsStreaming()
    {
        return true;
    }
    
    @Override
    public void SetVelocity(float x, float y, float z)
    {
        if (MANAGER.noDevice)
            return;
        
        velocityX = x;
        velocityY = y;
        velocityZ = z;
        
        if (sourceID == -1)
            return;
        
        AL10.alSource3f(sourceID, AL10.AL_VELOCITY, x, y, z);
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
        AL10.alSource3f(sourceID, AL10.AL_VELOCITY, 0, 0, 0);
    }
    
    @Override
    public boolean IsPlaying()
    {
        boolean playing = AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
        
        // Unknown bug causes "INVALID_NAME" error from prior call. This flushes the error flags.
        AL10.alGetError();
        return playing;
    }
    
    @Override
    public void SetReferenceDistance(float distance)
    {
        this.referenceDistance = distance;
	if (MANAGER.noDevice) 
            return;
	if (sourceID != -1) 
            AL10.alSourcef(sourceID, AL10.AL_REFERENCE_DISTANCE, distance);
    }
    
    @Override
    public void SetRolloff(float rate)
    {
        this.rolloff = rate;
	if (MANAGER.noDevice) 
            return;
	if (sourceID != -1) 
            AL10.alSourcef(sourceID, AL10.AL_ROLLOFF_FACTOR, rate);
    }
    
    @Override
    public int GetSampleOffset()
    {
        if (sourceID == -1)
            return 0;
        
        return AL10.alGetSourcei(sourceID, AL11.AL_SAMPLE_OFFSET);
    }
    
    public AudioSamples_ GetCurrentAudioSamples()
    {
        if (sourceID == -1)
            return null;
            
        try
        {
            RecycleBuffers();
            Update();
            if (bufferQueue.size() > 0)
                return buffersToSamples.get(bufferQueue.get(0));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /*
    Unqueue the first buffer and return its buffer ID.
    */
    private int UnqueueBuffer(int sourceID)
    {
        if (bufferQueue.size() > 0)
            bufferQueue.remove(0);
        return AL10.alSourceUnqueueBuffers(sourceID);
    }
    
    /*
    Add to the queue.
    */
    private void QueueBuffer(int sourceID, int bufferID)
    {
        bufferQueue.add(bufferID);
        AL10.alSourceQueueBuffers(sourceID, bufferID);
    }
}
