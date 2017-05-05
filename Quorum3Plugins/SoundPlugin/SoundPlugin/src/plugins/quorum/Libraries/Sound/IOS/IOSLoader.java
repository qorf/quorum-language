/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound.IOS;

import plugins.quorum.Libraries.Sound.Data;
import plugins.quorum.Libraries.Sound.DataLoader;
import quorum.Libraries.System.File_;

/**
 *
 * @author alleew
 */
public class IOSLoader implements DataLoader
{

    @Override
    public Data Load(File_ quorumFile) 
    {
        return new IOSSoundData(quorumFile);
    }

    @Override
    public Data LoadToStream(File_ quorumFile) 
    {
        String path = quorumFile.GetPath().replace('\\', '/');
        OALAudioTrack track = OALAudioTrack.create();
        if (track != null) 
        {
            if (track.preloadFile(path)) 
            {
                return new IOSStreamData(track);
            }
        }
        throw new RuntimeException("Error opening audio file at " + path);
    }
    
    @Override
    public Data Load(quorum.Libraries.Sound.AudioSamples_ buffer)
    {
        throw new UnsupportedOperationException("Direct AudioBuffer manipulation is not currently supported on this platform.");
    }
    
    @Override
    public Data LoadToStream(quorum.Libraries.Sound.AudioSamples_ buffer)
    {
        throw new UnsupportedOperationException("Direct AudioBuffer manipulation is not currently supported on this platform.");
    }
    
}
