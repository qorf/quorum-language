/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author astefik
 */
public class Audio {
    public java.lang.Object me_ = null;
    private final int BUFFER_SIZE = 128000;
    private AudioInputStream audioStream;
    private AudioFormat audioFormat;
    private SourceDataLine sourceLine;
    
    private Thread playThread = null;
    private boolean playThreadShouldRun = false;
    private Thread recordThread = null;
    private boolean recordThreadShouldRun = false;
    
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
    
    public void RecordNative(String stringPath) {
        final String finPath = stringPath;
        recordThread = new Thread(new Runnable() {
            @Override
            public void run() {
                recordThreadShouldRun = true;
                RecordInternal(finPath);
            }
        });
        recordThread.start();
    }
    
    private void RecordInternal(String stringPath) {
        File path = new File(stringPath);
        TargetDataLine line;
        
        AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
        
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format); // format is an AudioFormat object
        if (!AudioSystem.isLineSupported(info)) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, "Cannot get a recording line from the system.", "");

        }
        // Obtain and open the line.
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            
            // Assume that the TargetDataLine, line, has already
            // been obtained and opened.
            ByteArrayOutputStream out  = new ByteArrayOutputStream();
            int numBytesRead = 0;
            byte[] data = new byte[line.getBufferSize() / 5];

            // Begin audio capture.
            line.start();

            // Here, stopped is a global boolean set by another thread.
            while (recordThreadShouldRun) {
               // Read the next chunk of data from the TargetDataLine.
               numBytesRead =  line.read(data, 0, data.length);
               // Save this chunk of data.
               out.write(data, 0, numBytesRead);
            } 
            
            int frameSize = format.getFrameSize();
            byte[] bites = out.toByteArray();
            InputStream stream = new ByteArrayInputStream(bites);
            AudioInputStream ais = new AudioInputStream(stream, format, bites.length / frameSize);
            
            AudioSystem.write(ais, Type.WAVE, path);
            
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, "Cannot get a recording line from the system.", ex);
        }
    }
    
    public void StopRecordingNative() {
        recordThreadShouldRun = false;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Audio audio = new Audio();
        audio.PlayNative("/Users/astefik/Desktop/fart.wav", false);
        
        
//        audio.RecordNative("/Users/astefik/Desktop/test_record.wav");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        audio.StopRecordingNative();
    }
}
