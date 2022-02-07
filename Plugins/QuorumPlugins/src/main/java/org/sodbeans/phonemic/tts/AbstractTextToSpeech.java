/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts;

import java.util.ArrayList;
import java.util.Iterator;
import org.sodbeans.phonemic.*;

/**
 *
 * @author Jeff Wilson
 */
public abstract class AbstractTextToSpeech implements TextToSpeech {
    protected double volume;
    protected double speed;
    protected double pitch;
    protected SpeechVoice voice;
    protected String recentSpeech;
    protected SpeechPriority recentPriority;

    // Member variables related to supported features
    protected boolean blockingSupported;
    protected boolean pauseSupported;
    protected boolean resumeSupported;
    protected boolean stopSupported;
    protected boolean voiceChangeSupported;
    protected boolean volumeChangeSupported;
    protected boolean speedChangeSupported;
    protected boolean pitchChangeSupported;

    // Public implemented methods
    public SpeechVoice getCurrentVoice() {
        return voice;
    }
    
    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public double getSpeed() {
        return speed;
    }
    
    @Override
    public double getPitch() {
        return pitch;
    }

    @Override
    public boolean canBlock() {
        return blockingSupported;
    }

    @Override
    public boolean canPause() {
        return pauseSupported;
    }

    @Override
    public boolean canResume() {
        return resumeSupported;
    }
    
    @Override
    public boolean canStop() {
        return stopSupported;
    }

    @Override
    public boolean canSetVoice() {
        return voiceChangeSupported;
    }
    
    @Override
    public boolean canSetVolume() {
        return volumeChangeSupported;
    }
    
    @Override
    public boolean canSetSpeed() {
        return speedChangeSupported;
    }
    
    @Override
    public boolean canSetPitch() {
        return pitchChangeSupported;
    }
    
    @Override
    public boolean copyToClipboard() {
        // Implemented in SpeechBridge.
        return true;
    }
    
    // Function overloads
    @Override
    public boolean speak(String text) {
        return speak(text, SpeechPriority.MEDIUM, RequestType.TEXT);
    }

    @Override
    public boolean speak(SpeechProcessor proc) {
        // Implemented in SpeechBridge.
        return false;
    }
    
    @Override
    public boolean speak(String text, SpeechPriority priority) {
        return speak(text, priority, RequestType.TEXT);
    }

    @Override
    public boolean speak(char c) {
        return speak(c, SpeechPriority.MEDIUM);
    }

    @Override
    public boolean speakBlocking(String text) {
        return speakBlocking(text, SpeechPriority.MEDIUM);
    }

    @Override
    public boolean speakBlocking(String text, SpeechPriority priority) {
        return speakBlocking(text, priority, RequestType.TEXT);
    }
    
    @Override
    public boolean speakBlocking(char c) {
        return speakBlocking(c, SpeechPriority.MEDIUM);
    }
    
    @Override
    public Iterator<TextToSpeechEngine> getAvailableEngines() {
        // Implemented in speech bridge--this should never be called.
        return null;
    }
    
    @Override
    public boolean setTextToSpeechEngine(TextToSpeechEngine engine) {
        // Implemented in SpeechBridge, except for WindowsSpeak.
        
        return false;
    }
    
    @Override
    public void setSpeechEnabled(boolean enabled) {
        // Do nothing. This is implemented in SpeechBridge.
    }
    
    @Override
    public boolean isSpeechEnabled() {
        // Always return true. This is also implemented in SpeechBridge.
        return true;
    }
    
    @Override
    public double getVersion() {
        return TextToSpeechFactory.PHONEMIC_VERSION;
    }
}
