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
        samplesArray.remove(samples);
    }
    
    protected int GetAvailableBuffer()
    {
        if (unusedBuffers.isEmpty())
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
            
            if (unusedBuffers.isEmpty())
                return -1;
        }
        
        return unusedBuffers.remove(0);
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
            
            // Use Update to fill the buffers.
            Update();
        }
        if (!isPlaying) 
        {
            AL10.alSourcePlay(sourceID);
            isPlaying = true;
	}
    }
    
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
        
	// A buffer underflow will cause the source to stop.
	if (isPlaying && AL10.alGetSourcei(sourceID, AL10.AL_SOURCE_STATE) != AL10.AL_PLAYING) 
            AL10.alSourcePlay(sourceID);
    }
    
    @Override
    public void SetLooping(boolean looping)
    {
        // To be implemented;
    }
    
    @Override
    public void Stop()
    {
        // To be implemented;
    }
    
    @Override
    public void Dispose()
    {
        // To be implemented;
    }
    
    @Override
    public void Pause()
    {
        // To be implemented;
    }
    
    @Override
    public void Resume()
    {
        // To be implemented;
    }
    
    @Override
    public void SetPitch(float pitch)
    {
        // To be implemented;
    }
    
    @Override
    public void SetVolume(float volume)
    {
        // To be implemented;
    }
    
    @Override
    public void SetHorizontalPosition(float position)
    {
        // To be implemented;
    }
    
    @Override
    public void SetFade(float position)
    {
        // To be implemented;
    }
    
    @Override
    public void SetX(float newX)
    {
        // To be implemented;
    }
    
    @Override
    public void SetY(float newY)
    {
        // To be implemented;
    }
    
    @Override
    public void SetZ(float newZ)
    {
        // To be implemented;
    }
    
    @Override
    public void SetPosition(float newX, float newY, float newZ)
    {
        // To be implemented;
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
        // To be implemented;
        return false;
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
