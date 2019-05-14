/*******************************************************************************
 * An adapation of the AndroidMusic class from the libGDX project. The original
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

import java.io.IOException;
import android.media.MediaPlayer;
import plugins.quorum.Libraries.Game.AndroidApplication;
import plugins.quorum.Libraries.Sound.Data;
import quorum.Libraries.Sound.AudioSamples_;

public class AndroidStreamingData extends Data
{
    private final AndroidAudioManager audioManager;
    private MediaPlayer player;
    private boolean isPrepared = true;
    protected boolean wasPlaying = false;
    
    // How strongly the audio plays in the left / right speakers for stereo.
    // Used to reflect horizontal position, modified by the volume.
    private float leftIntensity = 1;
    private float rightIntensity = 1;

    public AndroidStreamingData (AndroidAudioManager audio, MediaPlayer player) 
    {
        this.audioManager = audio;
        this.player = player;
    }

    @Override
    public void Dispose() 
    {
        if (player == null) 
            return;
        try 
        {
            player.release();
        }
        catch (Throwable t) 
        {
            AndroidApplication.LogStatic("AndroidAudioManager", "An error occurred while disposing of AndroidStreamingData.");
        } 
        finally 
        {
            player = null;
//                synchronized (audio.musics) {
//                        audio.musics.remove(this); }
        }
    }

    @Override
    public boolean IsLooping() 
    {
        if (player == null) 
            return false;
        try 
        {
            return player.isLooping();
        }
        catch (Exception e) 
        {
            // NOTE: isLooping() can potentially throw an exception and crash the application
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean IsPlaying() 
    {
        if (player == null) 
            return false;
        try 
        {
            return player.isPlaying();
        }
        catch (Exception e) 
        {
            // NOTE: isPlaying() can potentially throw an exception and crash the application
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void Pause() 
    {
        if (player == null) 
            return;

        try 
        {
            if (player.isPlaying()) 
                player.pause();
        } 
        catch (Exception e) 
        {
            // NOTE: isPlaying() can potentially throw an exception and crash the application
            e.printStackTrace();
        }
        wasPlaying = false;
    }

    @Override
    public void Play() 
    {
        if (player == null) 
            return;
        try 
        {
            if (player.isPlaying()) 
                return;
        } 
        catch (Exception e) 
        {
            // NOTE: isPlaying() can potentially throw an exception and crash the application
            e.printStackTrace();
            return;
        }

        try 
        {
            if (!isPrepared) 
            {
                player.prepare();
                isPrepared = true;
            }
            
            SetVolume(volume);
            SetPitch(pitch);
            SetLooping(isLooping);
            player.start();
        } 
        catch (IllegalStateException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void SetLooping(boolean isLooping) 
    {
        this.isLooping = isLooping;
        
        if (player == null) 
            return;
        
        player.setLooping(isLooping);
    }

    @Override
    public void SetVolume(float volume) 
    {
        this.volume = volume;

        if (player == null) 
            return;
        
        player.setVolume(LeftVolume(), RightVolume());
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
    public void Stop() 
    {
        if (player == null) 
            return;

        if (isPrepared) 
        {
            player.seekTo(0);
        }

        player.stop();
        isPrepared = false;
    }

    public void SetTrackPosition(float position) 
    {
        if (player == null) 
            return;

        try 
        {
            if (!isPrepared) 
            {
                player.prepare();
                isPrepared = true;
            }

            player.seekTo((int)(position * 1000));
        }
        catch (IllegalStateException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public float GetTrackPosition() 
    {
        if (player == null) 
            return 0.0f;

        return player.getCurrentPosition() / 1000f;
    }

    public float GetDuration() 
    {
        if (player == null) 
            return 0.0f;

        return player.getDuration() / 1000f;
    }

    @Override
    public void Resume() 
    {
        if (player == null || !isPrepared)
            return;
        
        player.start();
    }

    @Override
    public void SetPitch(float pitch) 
    {
        this.pitch = pitch;
        if (player == null)
            return;
        
        player.setPlaybackParams(player.getPlaybackParams().setSpeed(pitch).setPitch(pitch));
    }

    @Override
    public boolean IsStreaming() 
    {
        return true;
    }
    
    @Override
    public void Update() 
    {
        // Do nothing -- this isn't necessary on Android.
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
        
        if (player == null) 
            return;

        player.setVolume(LeftVolume(), RightVolume());
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
