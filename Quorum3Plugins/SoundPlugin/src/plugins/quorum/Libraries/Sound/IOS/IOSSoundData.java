/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.IOS;

import java.util.Arrays;
import org.robovm.apple.foundation.NSArray;
import plugins.quorum.Libraries.Sound.Data;

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
        soundPath = quorumFile.GetPath().replace('\\', '/');
        soundBuffer = OALSimpleAudio.sharedInstance().preloadEffect(soundPath);
        channel = OALSimpleAudio.sharedInstance().getChannelSource();
        sourcePool = channel.getSourcePool().getSources();
    }
    
    @Override
    public void Play() 
    {
        Play(volume, pitch, pan, isLooping);
    }
    
    public void Play(float volumeValue, float pitchValue, float panValue, boolean loop)
    {
        if (streamIDs.size == 8) 
            streamIDs.pop();
        ALSource soundSource = OALSimpleAudio.sharedInstance().playBuffer(soundBuffer, volumeValue, pitchValue, panValue, loop);
        if (soundSource == null)
            return;
        if (soundSource.getSourceId() == -1)
            return;
        streamIDs.insert(0, soundSource.getSourceId());
        soundID = soundSource.getSourceId();
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
    public void SetHorizontalPosition(float position) 
    {
        this.pan = position;
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null) 
        {
                source.setPan(pan);
        }
    }
    
    @Override
    public void SetLooping(boolean looping) 
    {
        this.isLooping = looping;
        ALSource source;
        if ((source = GetSoundSource(soundID)) != null)
            source.setLooping(looping);
    }
    
    /*

    @Override
    public void stop (long soundId) {
            ALSource source;
            if ((source = getSoundSource(soundId)) != null) source.stop();
    }

    @Override
    public void setLooping (long soundId, boolean looping) {
            ALSource source;
            if ((source = getSoundSource(soundId)) != null) source.setLooping(looping);
    }

    @Override
    public void setPan (long soundId, float pan, float volume) {
            ALSource source;
            if ((source = getSoundSource(soundId)) != null) {
                    source.setPan(pan);
                    source.setVolume(volume);
            }
    }

    @Override
    public void pause (long soundId) {
            ALSource source;
            if ((source = getSoundSource(soundId)) != null) source.setPaused(true);
    }

    @Override
    public void resume (long soundId) {
            ALSource source;
            if ((source = getSoundSource(soundId)) != null) source.setPaused(false);
    }
    */

    @Override
    public void SetFade(float position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetX(float newX) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetY(float newY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetZ(float newZ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetPosition(float newX, float newY, float newZ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean IsStreaming() 
    {
        return false;
    }

    @Override
    public boolean IsPlaying() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update()
    {
        throw new RuntimeException("This audio was not set for streaming when loaded. Use LoadToStream to allow streaming the audio.");
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
}
