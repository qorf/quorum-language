/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts.impl;

import java.util.*;
import org.sapi.TextToSpeech;
import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.tts.TextToSpeechEngine;
import org.sodbeans.phonemic.tts.AbstractTextToSpeech;

/**
 * Provides an implementation of text-to-speech on Windows XP, Vista, and 7.
 * 
 * @author Andreas Stefik
 */
public class WindowsSpeak extends AbstractTextToSpeech{
    // MS_SAPI_SYNTH_DELAY is the number of milliseconds it *might* take to
    // generate speech output. This is necessary because SAPI does not
    // report it is speaking when speech is being generated. As a result,
    // priorities are broken without this slight delay. 
    private final int MS_SAPI_SYNTH_DELAY = 150;
    private TextToSpeech sapi = new TextToSpeech();
    private ArrayList<SpeechVoice> voices;
    private String pitchString = "<pitch absmiddle=\"0\">";
    private long lastRequestTime = 0;
    
    public WindowsSpeak() {
        sapi.createTTSNative();
        // Our supported features are dependent on which engine is loaded.
        // Basically, SAPI supports a lot of things and the rest don't.
        
        String textToSpeechEngine = sapi.getTextToSpeechEngine();
        if (textToSpeechEngine.compareTo(TextToSpeechEngine.MICROSOFT_SAPI.getEngineName()) == 0) {
            blockingSupported = true;
            pauseSupported = true;
            resumeSupported = true;
            stopSupported = true;
            voiceChangeSupported = true;
            volumeChangeSupported = true;
            speedChangeSupported = true;
            pitchChangeSupported = true;
        }
        else
        {
            blockingSupported = false;
            pauseSupported = false;
            resumeSupported = false;
            stopSupported = true;
            voiceChangeSupported = false;
            volumeChangeSupported = false;
            speedChangeSupported = false;
            pitchChangeSupported = false;  
        }
        
        // Parse voices
        voices = SpeechVoice.parseNativeVoicesString(sapi.getVoicesNative());
    }

    /**
     * The main function for handling *all* types of speech requests.
     * @param text
     * @param type
     * @param block
     * @return
     */
    private boolean speak(String text, RequestType type, boolean block) {
        String customPitchStr = this.pitchString;
        text = text.trim();
        // Was the last request a character? If so, fix the pitch.
        if (type == RequestType.CHARACTER) {
            if (Character.isUpperCase(text.charAt(0))) {
                customPitchStr = "<pitch absmiddle=\"10\">";
            }
        }

        if (block)
        {
            if (getTextToSpeechEngine() == TextToSpeechEngine.MICROSOFT_SAPI)
            {
                sapi.speakBlockNative(customPitchStr + text + "</pitch>");
            }
            else {
                sapi.speakBlockNative(text);
            }
            return true;
        }
        else {
            if (getTextToSpeechEngine() == TextToSpeechEngine.MICROSOFT_SAPI)
            {
                sapi.speakNative(customPitchStr + text + "</pitch>");
                lastRequestTime = System.currentTimeMillis();
            }
            else
            {
                sapi.speakNative(text);
            }
            return true;
        }
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
    public boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
        return speak(text, type,  true);
    }

    @Override
    public boolean speakBlocking(char c, SpeechPriority priority) {
        return speak(Character.toString(c), RequestType.CHARACTER, true);
    }
    
    @Override
    public boolean isSpeaking() {
        if (getTextToSpeechEngine() == TextToSpeechEngine.MICROSOFT_SAPI)
        {
            long timeDifference = System.currentTimeMillis() - lastRequestTime;
            return (timeDifference < MS_SAPI_SYNTH_DELAY || sapi.isSpeaking());
        }
        
        return sapi.isSpeaking();
    }

    @Override
    public Iterator<SpeechVoice> getAvailableVoices() {
        return voices.iterator();
    }
    
    @Override
    public Iterator<TextToSpeechEngine> getAvailableEngines() {
        ArrayList<TextToSpeechEngine> engines = new ArrayList<TextToSpeechEngine>();
        engines.add(TextToSpeechEngine.MICROSOFT_SAPI);
        engines.add(TextToSpeechEngine.JAWS);
        engines.add(TextToSpeechEngine.NVDA);
        return engines.iterator();
    }

    @Override
    public boolean setVolume(double vol) {

        // NOTE: Currently, only SAPI appears to support volume changes.
        if (getTextToSpeechEngine() != TextToSpeechEngine.MICROSOFT_SAPI)
            return false;

        if (vol < 0.0)
            vol = 0.0;
        else if (vol > 1.0)
            vol = 1.0;
        
        long newVol = (long) (100 * vol);
        if (sapi.setVolumeNative(newVol)) {
            this.volume = vol;

            return true;
        }

        return false;
    }

    @Override
    public boolean setSpeed(double speed) {
        // NOTE: Currently, only SAPI appears to support rate changes.
        if (getTextToSpeechEngine() != TextToSpeechEngine.MICROSOFT_SAPI)
            return false;

        if (speed < 0.0)
            speed = 0.0;
        else if (speed > 1.0)
            speed = 1.0;

        long newRate = (long) ((20 * speed) - 10);
        if (sapi.setRateNative(newRate)) {
            this.speed = speed;

            return true;
        }

        return false;
    }

    @Override
    public boolean setPitch(double pitch) {
        // NOTE: Currently, only SAPI appears to support pitch changes.
        if (getTextToSpeechEngine() != TextToSpeechEngine.MICROSOFT_SAPI)
            return false;

        if (pitch < 0.0)
            pitch = 0.0;
        else if (pitch > 1.0)
            pitch = 1.0;

        int newPitch = (int)(20 * pitch) - 10;
        this.pitchString = "<pitch absmiddle=\"" + newPitch + "\">";
        this.pitch = pitch;
        
        return true;
    }
    
    @Override
    public boolean setVoice(SpeechVoice voice) {
        String voiceName = voice.getName();
        boolean result = sapi.setVoiceNative(voiceName);

        if (result) {
            this.voice = voice;
        }
        return result;
    }

    @Override
    public void reinitialize() {
        sapi.reinitializeNative();
        
        // Reset our defaults.
        this.setVolume(0.5);
        this.setSpeed(0.5);
        this.setPitch(0.5);

    // Parse voices again
        voices = SpeechVoice.parseNativeVoicesString(sapi.getVoicesNative());
    }
    
    @Override
    public boolean respeak() {
        // Implemented in SpeechBridge.
        return false;
    }

    @Override
    public boolean pause() {
        sapi.pauseNative();
        return false;
    }

    @Override
    public boolean resume() {
        sapi.resumeNative();
        return false;
    }

    @Override
    public boolean stop() {
        sapi.stopNative();
        return true;
    }

    /**
     * This function attempts to change the speech engine to another type,
     * if it is available. For example, you can request to load JAWS. If a
     * a problem occurs (e.g., JAWS is not available), this method
     * returns null and the request is ignored.
     * 
     * @param engine
     * @return
     */
    @Override
    public boolean setTextToSpeechEngine(TextToSpeechEngine engine) {
        boolean result = sapi.setTextToSpeechEngine(engine.getEngineName());

        if (result) {
            // Restore defaults.
            this.setVolume(1.0);
            this.setSpeed(0.5);
            this.setPitch(0.5);
            
            // Update our capabilities.
            if (engine == TextToSpeechEngine.MICROSOFT_SAPI) {
                blockingSupported = true;
                pauseSupported = true;
                resumeSupported = true;
                stopSupported = true;
                voiceChangeSupported = true;
                volumeChangeSupported = true;
                speedChangeSupported = true;
                pitchChangeSupported = true;
            }
            else
            {
                blockingSupported = false;
                pauseSupported = false;
                resumeSupported = false;
                stopSupported = true;
                voiceChangeSupported = false;
                volumeChangeSupported = false;
                speedChangeSupported = false;
                pitchChangeSupported = false;  
            }
            
            // Parse voices
            voices = SpeechVoice.parseNativeVoicesString(sapi.getVoicesNative());
        }
        
        return result;
    }

    @Override
    public TextToSpeechEngine getTextToSpeechEngine() {
        String name = sapi.getTextToSpeechEngine();

        if(name.compareTo(TextToSpeechEngine.MICROSOFT_SAPI.getEngineName()) == 0) {
            return TextToSpeechEngine.MICROSOFT_SAPI;
        }
        else if(name.compareTo(TextToSpeechEngine.JAWS.getEngineName()) == 0) {
            return TextToSpeechEngine.JAWS;
        }
        else if(name.compareTo(TextToSpeechEngine.NVDA.getEngineName()) == 0) {
            return TextToSpeechEngine.NVDA;
        }

        return TextToSpeechEngine.NULL;
    }
}
