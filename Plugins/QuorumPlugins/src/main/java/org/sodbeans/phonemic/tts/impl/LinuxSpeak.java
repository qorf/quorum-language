/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts.impl;

import java.util.Iterator;
import java.util.ArrayList;
import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.tts.AbstractTextToSpeech;
import org.sodbeans.phonemic.tts.TextToSpeechEngine;

/**
 * Linux Text to Speech support, implemented using speech-dispatcher.
 *
 * @author Jeff Wilson
 */
public class LinuxSpeak extends AbstractTextToSpeech {
    private ArrayList<SpeechVoice> voices;
    private RequestType lastRequest;
    
    // Methods that mus be implemented on the native side.
    native private boolean speakNative(String text);
    native private boolean speakCharNative(String c);
    native private boolean speakBlockingNative(String text);
    native private boolean speakCharBlockingNative(String c);
    native private boolean stopNative();
    native private boolean pauseNative();
    native private boolean resumeNative();
    native private String getVoicesNative();
    native private boolean setVoiceNative(String voice);
    native private boolean setVolumeNative(int vol);
    native private boolean setSpeedNative(int speed);
    native private boolean setPitchNative(int pitch);
    native private boolean isSpeakingNative();
    native private void initializeNative();
    native private void reinitializeNative();
    native private void destroyNative();

    public LinuxSpeak() {
        // Our supported features...
        blockingSupported = true;
        pauseSupported = true;
        resumeSupported = true;
        stopSupported = true;
        voiceChangeSupported = true;
        volumeChangeSupported = true;
        speedChangeSupported = true;
        pitchChangeSupported = true;
        
        volume = 1.0;
        speed = 0.5;
        pitch = 0.5;
        
        initializeNative();
        voices = SpeechVoice.parseNativeVoicesString(getVoicesNative());
    }

    @Override
    public void finalize() throws Throwable {
        destroyNative(); // clean up on the JNI side...
        super.finalize();
    }

    /**
     * Sets speech-dispatcher pitch. This function sets the pitch, but does
     * not change the `pitch' member variable. The public setPitch() method
     * calls this method and sets the `pitch' member variable.
     *
     * @param pitch
     * @return
     */
    private synchronized boolean setSpeechDispatcherPitch(double pitch) {
        double newPitch = pitch;

        if (pitch < 0.0)
            newPitch = 0.0;
        else if (pitch > 1.0)
            newPitch = 1.0;

        newPitch = (200 * newPitch) - 100;

        return setPitchNative((int)Math.ceil(newPitch));
    }
    
    /**
     * The main function for handling *all* types of speech requests.
     * @param text
     * @param type
     * @param block
     * @return
     */
    private boolean speak(String text, RequestType type, boolean block) {
        // Set the pitch as appropriate
        if (type == RequestType.TEXT) {
            setSpeechDispatcherPitch(pitch);
        }
        else if (type == RequestType.CHARACTER) {

            if (Character.isUpperCase(text.charAt(0))) {
                setSpeechDispatcherPitch(pitch + 0.5); // if pitch = 1.0, no change.
            }
            else {
                setSpeechDispatcherPitch(pitch); // reset
            }
        }

        lastRequest = type;

        if (block)
        {
            return speakBlockingNative(text);
        }
        else {
            return speakNative(text);
        }
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
    public synchronized boolean setVolume(double vol) {
        double newVol = vol;

        if (vol < 0.0)
            vol = 0.0;
        else if (vol > 1.0)
            vol = 1.0;

        newVol = (200 * newVol) - 100;

        boolean result = setVolumeNative((int)Math.ceil(newVol));

        if (result) {
            this.volume = vol;
        }

        return result;
    }

    @Override
    public synchronized boolean setSpeed(double speed) {
        double newSpeed = speed;

        if (speed < 0.0)
            speed = 0.0;
        else if (speed > 1.0)
            speed = 1.0;

        newSpeed = (Math.pow(10, newSpeed) * 11) - 10;

        boolean result = setSpeedNative((int)Math.ceil(newSpeed));

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
       
       if (setSpeechDispatcherPitch(pitch)) {
            this.pitch = pitch;

            return true;
        }

        return false;
    }
    
    @Override
    public synchronized boolean setVoice(SpeechVoice voice) {
        boolean result =  setVoiceNative(voice.getName());

        if (result) {
            this.voice = voice;
        }

        return result;
    }

    @Override
    public void reinitialize() {
        reinitializeNative();
    }

    @Override
    public boolean speak(String text, SpeechPriority priority, RequestType type) {
        return speak(text, type, false);
    }

    @Override
    public boolean speak(char c, SpeechPriority priority) {
        return speak(Character.toString(c), RequestType.CHARACTER, false);
    }
    
    @Override
    public synchronized boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
        return speak(text, type, true);
    }

    @Override
    public synchronized boolean speakBlocking(char c, SpeechPriority priority) {
        return speak(Character.toString(c), RequestType.CHARACTER, true);
    }

    @Override
    public boolean respeak() {
        // Implemented in SpeechBridge.
        return false;
    }
    
    @Override
    public synchronized boolean pause() {
        return pauseNative();
    }

    @Override
    public synchronized boolean resume() {
        return resumeNative();
    }

    @Override
    public boolean stop() {
        return stopNative();
    }
    
    @Override
    public TextToSpeechEngine getTextToSpeechEngine() {
        return TextToSpeechEngine.SPEECH_DISPATCHER;
    }
}
