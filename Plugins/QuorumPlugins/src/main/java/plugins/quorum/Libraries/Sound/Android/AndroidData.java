/*******************************************************************************
 * An adapation of the AndroidSound class from the libGDX project. The original
 * license information is preserved below.
 * 
 * Copyright 2011 See AUTHORS file.
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
package plugins.quorum.Libraries.Sound.Android;

import android.media.AudioManager;
import android.media.SoundPool;
import java.util.ArrayList;
import plugins.quorum.Libraries.Game.AndroidApplication;
import plugins.quorum.Libraries.Sound.Data;
import quorum.Libraries.Sound.AudioSamples_;

public class AndroidData extends Data
{
    final SoundPool soundPool;
    final int soundID;

    protected int streamID = -1;
    
    /* 
    The last time this audio started playing, stored as nanoseconds returned
    by System.nanoTime(). Must be converted to seconds before comparison against
    the duration. 
    */
    protected long startTime = 0;
    
    /* 
    The duration of the sound in seconds. Used to determine if the sound is 
    still playing. Set to -1 if the duration is not yet known.
    */
    protected double duration = -1;
    
    /*
    How many nanoseconds passed between playing the audio and pausing it. Used 
    to ensure correct results from IsPlaying() in case audio was paused. The 
    offset is -1 if the audio is not paused, and a non-negative value otherwise.
    */
    private long pauseOffset = -1;
    
    // How strongly the audio plays in the left / right speakers for stereo.
    // Used to reflect horizontal position, modified by the volume.
    private float leftIntensity = 1;
    private float rightIntensity = 1;
    
    private boolean loadFinished = false;
    private boolean playOnLoad = false;

    public AndroidData(SoundPool pool, int soundID, double duration) 
    {
        this.soundPool = pool;
        this.soundID = soundID;
        this.duration = duration;
    }
    
    public void OnLoadCompletion()
    {
        loadFinished = true;
        if (playOnLoad)
            Play();
    }

    @Override
    public void Dispose()
    {
        soundPool.unload(soundID);
    }

    @Override
    public void Play() 
    {
        if (!loadFinished)
        {
            playOnLoad = true;
            return;
        }
        
        if (streamID != -1 && IsPlaying())
            Stop();
        
        pauseOffset = -1;
        
        startTime = System.nanoTime();
        
        // Parameters are: soundID, volume in left ear, volume in right ear, priority, looping, rate
        streamID = soundPool.play(soundID, LeftVolume(), RightVolume(), 1, isLooping ? -1 : 0, pitch);
    }

    @Override
    public void Stop() 
    {
        if (streamID != -1)
            soundPool.stop(streamID);
        
        startTime = 0;
        streamID = -1;
    }

    @Override
    public void Pause() 
    {
        pauseOffset = System.nanoTime() - startTime;
        soundPool.pause(streamID);
    }

    @Override
    public void Resume() 
    {
        startTime = System.nanoTime() - pauseOffset;
        pauseOffset = -1;
        soundPool.resume(streamID);
    }

    @Override
    public void SetPitch(float pitch) 
    {
        this.pitch = pitch;
        
        if (streamID != -1)
            soundPool.setRate(streamID, pitch);
    }

    @Override
    public void SetVolume (float volume) 
    {
        this.volume = volume;
        soundPool.setVolume(streamID, LeftVolume(), RightVolume());
    }
    
    private float LeftVolume()
    {
        return leftIntensity * volume;
    }
    
    private float RightVolume()
    {
        return rightIntensity * volume;
    }

    @Override
    public void SetLooping(boolean looping) 
    {
        isLooping = looping;
        
        if (streamID != -1)
            soundPool.setLoop(streamID, isLooping ? -1 : 0);
    }

    @Override
    public void SetHorizontalPosition(float position) 
    {
        if (position < -1)
            position = -1;
        else if (position > 1)
            position = 1;
        
        leftIntensity = 1;
        rightIntensity = 1;
        
        if (position < 0)
            rightIntensity =+ position;
        else if (position > 0)
            leftIntensity =- position;

        if (streamID != -1)
            soundPool.setVolume(streamID, LeftVolume(), RightVolume());
    }

    @Override
    public void SetFade(float position) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to SetFade() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void SetX(float newX) 
    {
        SetHorizontalPosition(newX);
    }

    @Override
    public void SetY(float newY) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to SetY() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void SetZ(float newZ) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to SetZ() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void SetPosition(float newX, float newY, float newZ) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "In SetPosition(x, y, z), only set X coordinate. Full 3D audio isn't supported yet on Android.");
        SetHorizontalPosition(newX);
    }

    @Override
    public boolean IsStreaming() 
    {
        return false;
    }

    @Override
    public boolean IsPlaying() 
    {
        if (streamID == - 1 || pauseOffset != -1)
            return false;
        
        return (System.nanoTime() - startTime) / 1000000000.0 < duration;
    }

    @Override
    public void Update() 
    {
        throw new RuntimeException("This audio was not set for streaming when loaded. Use LoadToStream to allow streaming the audio.");
    }
    
    @Override
    public void SetVelocity(float x, float y, float z) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to SetVelocity() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void EnableDoppler() 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to EnableDoppler() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void DisableDoppler() 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to DisableDoppler() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void SetReferenceDistance(float distance) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to SetReferenceDistance() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void SetRolloff(float rate) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to SetRolloff() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void QueueSamples(AudioSamples_ samples) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to QueueSamples() -- this functionality isn't supported yet on Android.");
    }

    @Override
    public void UnqueueSamples(AudioSamples_ samples) 
    {
        AndroidApplication.LogStatic("Quorum Audio", "Ignored call to UnqueueSamples() -- this functionality isn't supported yet on Android.");
    }
    
    @Override
    public int GetSampleOffset()
    {
        return 0;
    }
}