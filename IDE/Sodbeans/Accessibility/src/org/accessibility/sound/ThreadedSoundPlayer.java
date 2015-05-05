/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.sound;

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
 *  This class plays one sound in another thread and then completes.
 * 
 * @author Andreas Stefik
 */
public class ThreadedSoundPlayer implements Runnable{

    private final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    
    private String soundFile = "";
    @Override
    public void run() {
        play();
    }
    
    
    private void play() {
        try {
            File path = new File(getSoundFile());
            audioStream = AudioSystem.getAudioInputStream(path);
            
            audioFormat = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try {
                sourceLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceLine.open(audioFormat);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(ThreadedSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ThreadedSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        
            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[BUFFER_SIZE];
            while (nBytesRead != -1) {
                try {
                    nBytesRead = audioStream.read(abData, 0, abData.length);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadedSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (nBytesRead >= 0) {
                    sourceLine.write(abData, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ThreadedSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThreadedSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the soundFile
     */
    public String getSoundFile() {
        return soundFile;
    }

    /**
     * @param soundFile the soundFile to set
     */
    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }
}
