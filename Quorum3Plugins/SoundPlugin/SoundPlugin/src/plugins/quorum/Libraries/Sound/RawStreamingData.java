/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author Bill
 */
public class RawStreamingData extends DesktopData
{
    protected ArrayList<AudioSamples_> samplesArray = new ArrayList<>();
    protected IntBuffer buffers;
    protected int bufferCount = 3;
    protected ArrayList<Integer> unusedBuffers = new ArrayList<>();
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
    
    public void QueueSamples(AudioSamples_ samples)
    {
        samplesArray.add(samples);
    }
    
    public void UnqueueSamples(AudioSamples_ samples)
    {
        // FIX ME: Need to be able to remove samples that are already in the buffer
        samplesArray.remove(samples);
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
            int bufferID = AL10.alSourceUnqueueBuffers(sourceID);
            if (bufferID == AL10.AL_INVALID_VALUE) 
                break;
            unusedBuffers.add(bufferID);
            UnmapBuffer(bufferID);
        }
    }
    
    private void FillBuffer(int bufferID, AudioSamples_ samples)
    {
//        System.out.println("Filling buffer " + testCounter++);
//        System.out.println("Using samples with hashcode " + samples.hashCode());
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
            sourceID = manager.ObtainSource(true);
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
            
            /*
            alSourcei(sourceID, AL_LOOPING, AL_FALSE);
            
            if (dopplerEnabled)
                SetVelocity(velocityX, velocityY, velocityZ);
            
            SetVolume(volume);
            SetReferenceDistance(referenceDistance);
            SetRolloff(rolloff);
            SetPosition(x, y, z);
            */
        }
        if (!IsPlaying()) 
        {
            // Use Update to fill the buffers.
            Update();
            AL10.alSourcePlay(sourceID);
	}
    }
    
    boolean wasEmpty = true;
    
    @Override
    public void Update()
    {
        if (manager.noDevice)
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
            AL10.alSourceQueueBuffers(sourceID, bufferID);
        }
    }
    
    @Override
    public void SetLooping(boolean looping)
    {
        // To be implemented;
    }
    
    @Override
    public void Stop()
    {
        if (manager.noDevice) 
            return;
	if (sourceID == -1) 
            return;
        
        AL10.alSourceStop(sourceID);
        RecycleBuffers();
	manager.FreeSource(sourceID);
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
        if (manager.noDevice)
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
        if (sourceID == -1)
            return;
        
        this.pitch = pitch;
        
        AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
    }
    
    @Override
    public void SetVolume(float volume)
    {
        this.volume = volume;
	if (manager.noDevice) 
            return;
	if (sourceID != -1) 
            AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetHorizontalPosition(float position)
    {
        // To be implemented;
    }
    
    @Override
    public void SetFade(float newFade)
    {
        this.fade = newFade;
        pan = 0;
	if (manager.noDevice) 
            return;
	if (sourceID == -1)
            return;
        
        this.x = 0;
        this.y = (float)Math.sin((fade + 1) * (float)Math.PI / 2);
        this.z = (float)Math.cos((fade - 1) * (float)Math.PI / 2);
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetX(float newX)
    {
        if (manager.noDevice) 
            return;
	if (sourceID == -1)
            return;
        
        this.x = newX;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, newX, y, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetY(float newY)
    {
        if (manager.noDevice) 
            return;
	if (sourceID == -1)
            return;
        
        this.y = newY;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, newY, z);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetZ(float newZ)
    {
        if (manager.noDevice) 
            return;
	if (sourceID == -1)
            return;
        
        this.z = newZ;
        
        AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, newZ);
        AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
    }
    
    @Override
    public void SetPosition(float newX, float newY, float newZ)
    {
        if (manager.noDevice) 
            return;
	if (sourceID == -1)
            return;
        
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        
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
        // To be implemented;
    }
    
    @Override
    public void EnableDoppler()
    {
        // To be implemented;
    }

    @Override
    public void DisableDoppler()
    {
        // To be implemented;
    }
    
    @Override
    public boolean IsPlaying()
    {
        return AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }
    
    @Override
    public void SetReferenceDistance(float distance)
    {
        // To be implemented;
    }
    
    @Override
    public void SetRolloff(float rate)
    {
        // To be implemented; 
    }
}
