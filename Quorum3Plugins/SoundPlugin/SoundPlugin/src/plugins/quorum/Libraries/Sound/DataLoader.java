/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

/**
 *
 * @author alleew
 */
public interface DataLoader 
{
    /*
    Loads a file that can be played normally.
    */
    public Data Load(quorum.Libraries.System.File_ quorumFile);
    
    /*
    Loads a file that can be streamed.
    */
    public Data LoadToStream(quorum.Libraries.System.File_ quorumFile);
    
    /*
    Loads a Quorum AudioBuffer directly into Audio.
    */
    public Data Load(quorum.Libraries.Sound.AudioSamples_ buffer);
    
    /*
    Loads a Quorum AudioBuffer into a streamable format.
    */
    public Data LoadToStream(quorum.Libraries.Sound.AudioSamples_ buffer);
}
