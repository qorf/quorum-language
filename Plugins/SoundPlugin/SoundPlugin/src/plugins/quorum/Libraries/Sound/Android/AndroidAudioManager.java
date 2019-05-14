/*******************************************************************************
 * An adapation of the AndroidAudio class from the libGDX project. The original
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

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import java.io.IOException;
import java.util.HashMap;
import plugins.quorum.Libraries.Game.AndroidApplication;
import plugins.quorum.Libraries.Sound.AudioManager;
import quorum.Libraries.System.File_;

/**
 *
 * @author alleew
 */
public class AndroidAudioManager implements AudioManager, OnLoadCompleteListener
{
    private static final HashMap<String, Integer> DURATION_MAP = new HashMap<>();
    private static final HashMap<Integer, AndroidData> SOUND_MAP = new HashMap<>();
    
    public final SoundPool soundPool;
    
    public AndroidAudioManager()
    {
        AudioAttributes audioAttrib = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(16).build();
        soundPool.setOnLoadCompleteListener(this);
    }
    
    /*
    Given an asset described by the path of a Quorum File object, return the
    approximate duration of the audio in seconds. This information is cached
    after the first time the duration is requested for a particular asset.
    */
    public double GetDuration(File_ quorumFile)
    {
        String path = quorumFile.GetPath();
        if (DURATION_MAP.containsKey(path))
            return DURATION_MAP.get(path);
        else
        {
            MediaPlayer mediaPlayer = new MediaPlayer();
            AssetFileDescriptor descriptor = AndroidApplication.QuorumFileToAssetFileDescriptor(quorumFile);
            
            try
            {
                mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                mediaPlayer.prepare();
                return mediaPlayer.getDuration() / 1000.0;
            }
            catch (IOException e)
            {
                AndroidApplication.LogStatic("AndroidAudioManager", "Failed to load duration of " + quorumFile.GetPath());
                e.printStackTrace();
                return -1;
            }
        }
    }
    
    public AndroidData LoadAndroidData(File_ quorumFile)
    {
        int soundID = soundPool.load(AndroidApplication.QuorumFileToAssetFileDescriptor(quorumFile), 1);
        
        AndroidData data = new AndroidData(soundPool, soundID, GetDuration(quorumFile));
        SOUND_MAP.put(soundID, data);
        return data;
    }
    
    public AndroidStreamingData LoadAndroidStreamingData(File_ quorumFile)
    {
        MediaPlayer mediaPlayer = new MediaPlayer();
        
        AssetFileDescriptor descriptor = AndroidApplication.QuorumFileToAssetFileDescriptor(quorumFile);
        
        try
        {
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            AndroidStreamingData data = new AndroidStreamingData(this, mediaPlayer);
            return data;
        }
        catch(IOException exception)
        {
            throw new RuntimeException("Could not load streaming audio file: " + quorumFile.GetPath());
        }
    }
    
    @Override
    public void onLoadComplete(SoundPool soundPool, int soundID, int status) 
    {
        // Status value of 0 indicates successful loading.
        if (status != 0)
            return;
        
        if (SOUND_MAP.containsKey(soundID))
            SOUND_MAP.get(soundID).OnLoadCompletion();
    }
    
    @Override
    public void SetListenerPosition(double x, double y, double z) 
    {
        
    }

    @Override
    public double GetListenerX() 
    {
        return 0;
    }

    @Override
    public double GetListenerY() 
    {
        return 0;
    }

    @Override
    public double GetListenerZ() 
    {
        return 0;
    }

    @Override
    public void SetListenerVelocity(double x, double y, double z) 
    {
        
    }

    @Override
    public double GetListenerVelocityX() 
    {
        return 0;
    }

    @Override
    public double GetListenerVelocityY() 
    {
        return 0;
    }

    @Override
    public double GetListenerVelocityZ() 
    {
        return 0;
    }

    @Override
    public void EnableListenerDoppler() 
    {
        
    }

    @Override
    public void DisableListenerDoppler() 
    {
        
    }

    @Override
    public boolean IsListenerDopplerEnabled() 
    {
        return false;
    }

    @Override
    public void SetListenerDirection(double x, double y, double z) 
    {
        
    }

    @Override
    public void SetListenerUp(double x, double y, double z) 
    {
        
    }

    @Override
    public double GetListenerDirectionX() 
    {
        return 0;
    }

    @Override
    public double GetListenerDirectionY() 
    {
        return 0;
    }

    @Override
    public double GetListenerDirectionZ() 
    {
        return 0;
    }

    @Override
    public double GetListenerUpX() 
    {
        return 0;
    }

    @Override
    public double GetListenerUpY() 
    {
        return 0;
    }

    @Override
    public double GetListenerUpZ() 
    {
        return 0;
    }

}
