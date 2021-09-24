/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.IOS;

import java.util.Arrays;
import org.robovm.apple.foundation.NSArray;
import plugins.quorum.Libraries.Sound.Data;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.UIDevice;
import quorum.Libraries.Sound.AudioSamples_;

/**
 *
 * @author alleew
 */
public class IOSSoundData extends Data
{
    private ALBuffer soundBuffer;
    private String soundPath;
    
    private ALChannelSource channel;
    private NSArray<ALSource> sourcePool;
    private StreamContainer streamIDs = new StreamContainer(8);
    
    private long soundID = -1;
    
    public IOSSoundData(quorum.Libraries.System.File_ quorumFile)
    {
        soundPath = quorumFile.GetAbsolutePath().replace('\\', '/');
        soundBuffer = OALSimpleAudio.sharedInstance().preloadEffect(soundPath);
        channel = OALSimpleAudio.sharedInstance().getChannelSource();
        sourcePool = channel.getSourcePool().getSources();
    }
    
    @Override
    public void Play() 
    {
        Play(isLooping);
    }
    
    public void Play(boolean loop)
    {
        if (streamIDs.size == 8) 
            streamIDs.pop();
        ALSource soundSource = OALSimpleAudio.sharedInstance().playBuffer(soundBuffer, loop);
        if (soundSource == null)
            return;
        if (soundSource.getSourceId() == -1)
            return;
        streamIDs.insert(0, soundSource.getSourceId());
        soundID = soundSource.getSourceId();
        
        soundSource.setLooping(loop);
        soundSource.setVolume(volume);
        soundSource.setPitch(pitch);
        soundSource.setPosition(x, y, z);
    }
    
    @Override
    public void Stop() 
    {
        ALSource source;
        for (int i = 0; i < streamIDs.size; i++) 
        {
            if ((source = GetSoundSource(streamIDs.get(i))) != null)
                source.stop();
        }
    }

    @Override
    public void Dispose() 
    {
        Stop();
        soundBuffer.dispose();
    }
    
    @Override
    public void Pause() 
    {
        ALSource source;
        for (int i = 0; i < streamIDs.size; i++) 
        {
            if ((source = GetSoundSource(streamIDs.get(i))) != null) 
                source.setPaused(true);
        }
    }
    
    @Override
    public void Resume() 
    {
        ALSource source;
        for (int i = 0; i < streamIDs.size; i++) 
        {
            if ((source = GetSoundSource(streamIDs.get(i))) != null)
                source.setPaused(false);
        }
    }
    
    @Override
    public void SetPitch(float pitch) 
    {
        this.pitch = pitch;
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setPitch(pitch);
    }

    @Override
    public void SetVolume(float volume)
    {
        this.volume = volume;
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setVolume(volume);
    }
    
    @Override
    public void SetReferenceDistance(float distance)
    {
        throw new UnsupportedOperationException("Reference distance controls haven't been implemented yet for iOS.");
    }
    
    @Override
    public void SetRolloff(float rolloff)
    {
        throw new UnsupportedOperationException("Reference distance controls haven't been implemented yet for iOS.");
    }
    
    @Override
    public void SetX(float newX) 
    {
        SetPosition(newX, y, z);
    }

    @Override
    public void SetY(float newY) 
    {
        SetPosition(x, newY, z);
    }

    @Override
    public void SetZ(float newZ) 
    {
        SetPosition(x, y, newZ);
    }

    @Override
    public void SetPosition(float newX, float newY, float newZ) 
    {
        x = newX;
        y = newY;
        z = newZ;
        
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setPosition(x, y, z);
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
        if (!dopplerEnabled)
            return;
        
        dopplerEnabled = false;
        
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setVelocity(0, 0, 0);
    }
    
    @Override
    public void SetVelocity(float newX, float newY, float newZ)
    {
        velocityX = newX;
        velocityY = newY;
        velocityZ = newZ;
        
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setVelocity(newX, newY, newZ);
    }
    
    @Override
    public void SetHorizontalPosition(float position) 
    {
        this.pan = position;
        this.fade = 0;
        
        float newX = (float)Math.cos((pan - 1) * (float)Math.PI / 2);
        float newY = 0;
        float newZ = (float)Math.sin((pan + 1) * (float)Math.PI / 2);
        
        SetPosition(newX, newY, newZ);
    }
    
    @Override
    public void SetFade(float position) 
    {
        this.pan = 0;
        this.fade = position;
        
        float newX = 0;
        float newY = (float)Math.cos((pan - 1) * (float)Math.PI / 2);
        float newZ = (float)Math.sin((pan + 1) * (float)Math.PI / 2);
        
        SetPosition(newX, newY, newZ);
    }
    
    @Override
    public void SetLooping(boolean looping) 
    {
        this.isLooping = looping;
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setLooping(looping);
    }

    @Override
    public boolean IsStreaming() 
    {
        return false;
    }

    @Override
    public boolean IsPlaying() 
    {
        ALSource source = GetSoundSource(soundID);
        if (source == null)
        {
            return false;
        }
            
        return source.isPlaying() && !source.isPaused();
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
    
    private ALSource GetSoundSource(long soundId) 
    {	
        
        for (ALSource source : sourcePool) 
        {
            if (source.getSourceId() == soundId)
                return source;
        }
        return null;
    }
    

    /*
    A reduced implementation of the IntArray class from libGDX, using only the
    features needed for this class.
    */
    private class StreamContainer 
    {
	public int[] items;
	public int size;
	public boolean ordered;

	/** Creates an ordered array with a capacity of 16. */
	public StreamContainer () {
		this(true, 16);
	}

	/** Creates an ordered array with the specified capacity. */
	public StreamContainer (int capacity) {
		this(true, capacity);
	}

	/** @param ordered If false, methods that remove elements may change the order of other elements in the array, which avoids a
	 *           memory copy.
	 * @param capacity Any elements added beyond this will cause the backing array to be grown. */
	public StreamContainer (boolean ordered, int capacity) {
		this.ordered = ordered;
		items = new int[capacity];
	}

	public int get (int index) {
		if (index >= size) throw new IndexOutOfBoundsException("index can't be >= size: " + index + " >= " + size);
		return items[index];
	}

	public void insert (int index, int value) {
		if (index > size) throw new IndexOutOfBoundsException("index can't be > size: " + index + " > " + size);
		int[] items = this.items;
		if (size == items.length) items = resize(Math.max(8, (int)(size * 1.75f)));
		if (ordered)
			System.arraycopy(items, index, items, index + 1, size - index);
		else
			items[size] = items[index];
		size++;
		items[index] = value;
	}

	/** Removes and returns the last item. */
	public int pop () {
		return items[--size];
	}

	protected int[] resize (int newSize) {
		int[] newItems = new int[newSize];
		int[] items = this.items;
		System.arraycopy(items, 0, newItems, 0, Math.min(size, newItems.length));
		this.items = newItems;
		return newItems;
	}
    }
    
    @Override
    public int GetSampleOffset()
    {
        return 0;
    }
}
