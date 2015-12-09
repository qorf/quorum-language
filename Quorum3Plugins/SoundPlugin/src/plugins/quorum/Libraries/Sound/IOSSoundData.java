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
public class IOSSoundData extends Data
{
    //private ALBuffer soundBuffer;
    private String soundPath;
    
    //private ALChannelSource channel;
    //private NSArray<ALSource> sourcePool;
    //private IntArray streamIDs = new IntArray(8);
    
    private long soundID = -1;
    
    public IOSSoundData(quorum.Libraries.System.File_ quorumFile)
    {
        soundPath = quorumFile.GetPath().replace('\\', '/');
        //soundBuffer = OALSimpleAudio.sharedInstance().preloadEffect(soundPath);
        //channel = OALSimpleAudio.sharedInstance().getChannelSource();
        //sourcePool = channel.getSourcePool().getSources();
    }
    
    @Override
    public void Play() 
    {
        Play(volume, pitch, pan, isLooping);
    }
    
    public void Play(float volumeValue, float pitchValue, float panValue, boolean loop)
    {
        /*
        if (streamIds.size == 8) streamIds.pop();
        ALSource soundSource = OALSimpleAudio.sharedInstance().playBuffer(soundBuffer, volumeValue, pitchValue, panValue, loop);
        if (soundSource == null) return -1;
        if (soundSource.getSourceId() == -1) return -1;
        streamIds.insert(0, soundSource.getSourceId());
        soundID = soundSource.getSourceId();
        */
    }
    
    @Override
    public void Stop() 
    {
        /*
        ALSource source;
        for (int i = 0; i < streamIds.size; i++) 
        {
            if ((source = getSoundSource(streamIds.get(i))) != null)
                source.stop();
        }
        */
    }

    @Override
    public void Dispose() 
    {
        Stop();
        //soundBuffer.dispose();
    }
    
    @Override
    public void Pause() 
    {
        /*
        ALSource source;
        for (int i = 0; i < streamIDs.size; i++) 
        {
            if ((source = getSoundSource(streamIDs.get(i))) != null) 
                source.setPaused(true);
        }
        */
    }
    
    @Override
    public void Resume() 
    {
        /*
        ALSource source;
        for (int i = 0; i < streamIds.size; i++) 
        {
            if ((source = getSoundSource(streamIds.get(i))) != null)
                source.setPaused(false);
        }
        */
    }
    
    @Override
    public void SetPitch(float pitch) 
    {
        this.pitch = pitch;
        /*
        ALSource source;
        if ((source = getSoundSource(soundID)) != null)
            source.setPitch(pitch);
        */
    }

    @Override
    public void SetVolume(float volume)
    {
        this.volume = volume;
        /*
        ALSource source;
        if ((source = getSoundSource(soundID)) != null)
            source.setVolume(volume);
        */
    }
    
    @Override
    public void SetHorizontalPosition(float position) 
    {
        this.pan = position;
        /*
        ALSource source;
        if ((source = getSoundSource(soundID)) != null) 
        {
                source.setPan(pan);
        }
        */
    }
    
    @Override
    public void SetLooping(boolean looping) 
    {
        this.isLooping = looping;
        /*
        ALSource source;
        if ((source = getSoundSource(soundID)) != null)
            source.setLooping(looping);
        */
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

    private ALSource getSoundSource (long soundId) {	
            for (ALSource source : sourcePool) {
                    if (source.getSourceId() == soundId) return source;			
            }
            return null;
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
    public boolean IsStreaming() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
