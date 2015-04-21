/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts.impl;

import java.util.Iterator;
import java.io.*;
import java.util.ArrayList;
import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.tts.*;

/**
 * Mac OS X Text To Speech Support (Carbon)
 * 
 * @author Jeff Wilson and Andreas Stefik
*/
public class CarbonSpeak extends AbstractTextToSpeech {
    private ArrayList<SpeechVoice> voices;

    // Methods that mus be implemented on the native side.
    native private boolean speakNative(String text);
    native private boolean speakBlockingNative(String text);
    native private boolean stopNative();
    native private boolean pauseNative();
    native private boolean resumeNative();
    native private boolean isSpeakingNative();
    native private boolean setVoiceNative(int index);
    native private boolean setVolumeNative(double vol);
    native private boolean setSpeedNative(double speed);
    native private boolean setPitchNative(double pitch);
    native private void initializeNative();
    native private void reinitializeNative();
    native private void destroyNative();
    
    public CarbonSpeak() {
        voices = new ArrayList<SpeechVoice>();
        
        // Our supported features...
        blockingSupported = true;
        pauseSupported = true;
        resumeSupported = true;
        stopSupported = true;
        voiceChangeSupported = true;
        volumeChangeSupported = true;
        speedChangeSupported = true;
        pitchChangeSupported = true;

        // Load up all our voices in to the voices vector.
        loadVoices();

        // Some nice defaults for our parameters.
        volume = 1.0;
        speed = 0.5;
        pitch = 0.5;

        // No voice set yet.
        voice = null;

        // Initialize on the native side.
        initializeNative();
    }

    /**
     * The main function for handling *all* types of speech requests.
     * @param text
     * @param type
     * @param block
     * @return
     */
    private boolean speak(String text, RequestType type, boolean block) {
        // Set the pitch as appropriate.
        if (type == RequestType.TEXT) {
            setCarbonPitch(pitch);
        }
        else if (type == RequestType.CHARACTER) {
            text = text.trim();
            if (Character.isUpperCase(text.charAt(0))) {
                setCarbonPitch(pitch + 0.5); // if pitch = 1.0, no change.
            }
            else {
                setCarbonPitch(pitch); // reset
            }
        }

        if (block)
        {
            return speakBlockingNative(text);
        }
        else {
            return speakNative(text);
        }
    }
    
    private void loadVoices() {
        // All available system voices are listed in
        // /system/library/speech/voices/
        File voicesDir = new File("/system/library/speech/voices/");
        FileFilter voiceFilter = new FileFilter() {
            public boolean accept(File file) {
                return (file.getName().endsWith(".SpeechVoice") &&
                        !file.isDirectory());
            }
        };

        String[] voiceFiles = voicesDir.list();
        for (int i = 0; i < voiceFiles.length; i++)
        {
            String voiceName = voiceFiles[i].split("\\.")[0];
            SpeechVoice newVoice = new SpeechVoice(voiceName, SpeechLanguage.ENGLISH_US);
            voices.add(newVoice);
        }
    }

    @Override
    public boolean speak(String text, SpeechPriority priority, RequestType type) {
        return speak(text, type, false);
    }

    @Override
    public boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
         return speak(text, type, true);
    }

    @Override
    public boolean speakBlocking(char c, SpeechPriority priority) {
        return speak(Character.toString(c), RequestType.CHARACTER, true);
    }

    @Override
    public boolean isSpeaking() {
        return isSpeakingNative();
    }

    @Override
    public Iterator<SpeechVoice> getAvailableVoices() {
        return voices.iterator();
    }

    @Override
    public boolean setVolume(double vol) {        
        if (vol < 0.0)
            vol = 0.0;
        else if (vol > 1.0)
            vol = 1.0;

        boolean result = setVolumeNative(vol);

        if (result) {
            this.volume = vol;
        }

        return result;
    }

    @Override
    public boolean setSpeed(double speed) {
        double newSpeed = speed;
        
        if (speed < 0.0)
            speed = 0.0;
        else if (speed > 1.0)
            speed = 1.0;
        
        newSpeed = (Math.pow(10.0, newSpeed) * 50) + 100;

        boolean result = setSpeedNative(newSpeed);

        if (result) {
            this.speed = speed;
        }

        return result;
    }

    @Override
    public boolean setPitch(double pitch) {
        if (pitch < 0.0)
            pitch = 0.0;
        else if (pitch > 1.0)
            pitch = 1.0;
        
        if (setCarbonPitch(pitch)) {
            this.pitch = pitch;

            return true;
        }

        return false;
    }

    /**
     * Sets Carbon pitch. This function sets the pitch, but does not change
     * the `pitch' member variable. The public setPitch() method calls this
     * method and sets the `pitch' member variable.
     * 
     * @param pitch
     * @return
     */
    private boolean setCarbonPitch(double pitch) {
        double newPitch = pitch;

        if (pitch < 0.0)
            newPitch = 0.0;
        else if (pitch > 1.0)
            newPitch = 1.0;

        newPitch = (26 * newPitch) + 30;

        return setPitchNative(newPitch);
    }

    @Override
    public boolean setVoice(SpeechVoice voice) {
        int index = 1;
        boolean found = false;
        Iterator v = voices.iterator();

        while (v.hasNext())
        {
            SpeechVoice curVoice = (SpeechVoice)v.next();

            if (curVoice.getName().compareTo(voice.getName()) == 0)
            {
                this.voice = curVoice;
                found = true;
                break;
            }
            index++;
        }

        if (!found)
            return false;
        else
            return setVoiceNative(index);
    }

    @Override
    public void reinitialize() {
        reinitializeNative();
    }

    @Override
    public boolean speak(char c, SpeechPriority priority) {
        return speak(Character.toString(c), RequestType.CHARACTER, false);
    }

    @Override
    public boolean respeak() {
        // Implemented in SpeechBridge.
        return false;
    }

    @Override
    public boolean pause() {
        return pauseNative();
    }

    @Override
    public boolean resume() {
        return resumeNative();
    }

    @Override
    public boolean stop() {
        return stopNative();
    }

    @Override
    public TextToSpeechEngine getTextToSpeechEngine() {
        return TextToSpeechEngine.APPLE_CARBON;
    }
}