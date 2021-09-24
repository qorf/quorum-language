/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.Android;

import plugins.quorum.Libraries.Sound.Desktop.WavStreamingData;
import plugins.quorum.Libraries.Sound.Desktop.WavData;
import plugins.quorum.Libraries.Sound.Desktop.OggData;
import plugins.quorum.Libraries.Sound.Desktop.RawStreamingData;
import plugins.quorum.Libraries.Sound.Desktop.OggStreamingData;
import java.io.File;
import java.io.InputStream;
import quorum.Libraries.System.File_;
import quorum.Libraries.Sound.AudioSamples_;
import plugins.quorum.Libraries.Game.AndroidApplication;
import plugins.quorum.Libraries.Sound.Data;
import plugins.quorum.Libraries.Sound.DataLoader;

public class AndroidLoader implements DataLoader
{

    private final AndroidAudioManager audioManager;
    
    public AndroidLoader(AndroidAudioManager manager)
    {
        audioManager = manager;
    }
    
    @Override
    public Data Load(File_ quorumFile) 
    {
        return audioManager.LoadAndroidData(quorumFile);
    }

    @Override
    public Data LoadToStream(File_ quorumFile) 
    { 
        return audioManager.LoadAndroidStreamingData(quorumFile);
    }
    
    @Override
    public Data Load(AudioSamples_ buffer)
    {
        return null;
    }
    
    @Override
    public Data LoadToStream(AudioSamples_ buffer)
    {
        return null;
    }
    
}
