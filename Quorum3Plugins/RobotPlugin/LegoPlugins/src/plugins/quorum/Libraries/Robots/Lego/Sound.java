package plugins.quorum.Libraries.Robots.Lego;

import java.io.File;

public class Sound {
    public Object me_ = null;
    
    public void PlayTone(int frequency, int duration) {
        lejos.hardware.Sound.playTone(frequency, duration);
    }
    
    public int GetVolume() {
        return lejos.hardware.Sound.getVolume();
    }
    
    public void SetVolume(int volume) {
        lejos.hardware.Sound.setVolume(volume); //0-100
    }
    
    public void PlayAudio(quorum.Libraries.System.File_ quorumFile) {
        File audioFile;
        audioFile = new File(quorumFile.GetAbsolutePath());
        lejos.hardware.Sound.playSample(audioFile);
    }
    
    public void PlayAudio(String filePath) {
        File audioFile;
        audioFile = new File(filePath);
        lejos.hardware.Sound.playSample(audioFile);
    }
    
    public int GetRemainingTime() {
        return lejos.hardware.Sound.getTime();
    }
    
    public boolean IsPlaying() {
        return lejos.hardware.Sound.getTime() > 0;
    }
    //system sounds
    public void Beep() {
        lejos.hardware.Sound.beep();
    }
    
    public void BeepSequenceDown() {
        lejos.hardware.Sound.beepSequence();
    }
    
    public void BeepSequenceUp() {
        lejos.hardware.Sound.beepSequenceUp();
    }
    
    public void BeepTwice() {
        lejos.hardware.Sound.twoBeeps();
    }
    
    public void Buzz() {
        lejos.hardware.Sound.buzz();
    }
}