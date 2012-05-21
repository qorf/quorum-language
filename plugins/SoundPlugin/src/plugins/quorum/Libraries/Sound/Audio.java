/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author astefik
 */
public class Audio {
    public java.lang.Object $me = null;
    private final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    
    private Thread playThread = null;
    private boolean playThreadShouldRun = false;
    
    public void PlayNative(String path, boolean block) {
        final String finPath = path;
        if(block) {
            playThreadShouldRun = true;
            PlayInternal(path);
            playThreadShouldRun = false;
        }
        else {
            playThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    playThreadShouldRun = true;
                    PlayInternal(finPath);
                    playThreadShouldRun = false;
                }
            });
            playThread.start();
        }
    }
    
    private void PlayInternal(String stringPath) {
        try {
            File path = new File(stringPath);
            audioStream = AudioSystem.getAudioInputStream(path);
            
            audioFormat = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try {
                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(false == playThreadShouldRun) {
                finishOffAudio();
                return;
            }
        
            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[BUFFER_SIZE];
            while (nBytesRead != -1) {
                try {
                    if(false == playThreadShouldRun) {
                        finishOffAudio();
                        return;
                    }
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                } catch (Exception ex) {
                    Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (nBytesRead >= 0) {
                    sourceLine.write(abData, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void finishOffAudio() {
        audioStream = null;
        audioFormat = null;
        if(sourceLine != null) {
            sourceLine.drain();
            sourceLine.close();
            sourceLine = null;
        }
    }
    
    public void StopPlayingNative() {
        playThreadShouldRun = false;
    }
    public void RecordNative(String path) {
        
    }
    
    public void StopRecordingNative() {
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Audio audio = new Audio();
        audio.PlayNative("/Users/astefik/Desktop/fart.wav", false);
    }
}
