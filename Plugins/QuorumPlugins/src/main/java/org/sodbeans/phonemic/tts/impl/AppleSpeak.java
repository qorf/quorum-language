package org.sodbeans.phonemic.tts.impl;

import java.util.ArrayList;
import java.util.Iterator;
import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.tts.*;

/**
 * Text to speech on the Mac and iOS, through AVSpeechSynthesizer. The native
 * side is quorum-natives' src/apple/AppleSpeech.m, which is part of
 * libGameEngineCPlugins on the Mac and of the native archive on iOS.
 *
 * This replaces CocoaSpeechServer, a helper app Quorum used to talk to over
 * a socket. It is used inside a SpeechBridge, which handles priorities and
 * the speech queue.
 */
public class AppleSpeak extends AbstractTextToSpeech {
    private final ArrayList<SpeechVoice> voices = new ArrayList<SpeechVoice>();
    private final ArrayList<String> voiceIdentifiers = new ArrayList<String>();
    private String voiceIdentifier = "";

    native private void initializeNative();
    native private boolean speakNative(String text, String voice, float rate, float pitch, float volume, boolean block);
    native private boolean stopNative();
    native private boolean pauseNative();
    native private boolean resumeNative();
    native private boolean isSpeakingNative();
    native private String[] getVoicesNative();

    public AppleSpeak() {
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
        voice = null;

        initializeNative();
        loadVoices();
    }

    /**
     * Each native voice is "identifier, name, language", separated by tabs.
     * A voice is shown as "name (language)", since some names exist in
     * several languages.
     */
    private void loadVoices() {
        String[] list = getVoicesNative();
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.length; i++) {
            String[] parts = list[i].split("\t");
            if (parts.length < 3) {
                continue;
            }
            voiceIdentifiers.add(parts[0]);
            voices.add(new SpeechVoice(parts[1] + " (" + parts[2] + ")", SpeechLanguage.ENGLISH_US));
        }
    }

    private boolean speak(String text, RequestType type, boolean block) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        // An upper case letter is spoken a little higher, as the other
        // speech engines do.
        double currentPitch = pitch;
        if (type == RequestType.CHARACTER) {
            String trimmed = text.trim();
            if (!trimmed.isEmpty() && Character.isUpperCase(trimmed.charAt(0))) {
                currentPitch = Math.min(1.0, pitch + 0.25);
            }
        }

        return speakNative(text, voiceIdentifier, toRate(speed), toPitchMultiplier(currentPitch), (float) volume, block);
    }

    /**
     * AVSpeechUtterance's rate runs from 0.0 to 1.0, with 0.5 as normal, the
     * same as Quorum's speed.
     */
    private static float toRate(double value) {
        return (float) value;
    }

    /**
     * AVSpeechUtterance's pitch multiplier runs from 0.5 to 2.0, with 1.0 as
     * normal. Quorum's 0.0, 0.5 and 1.0 become 0.5, 1.0 and 2.0.
     */
    private static float toPitchMultiplier(double value) {
        return (float) (0.5 * Math.pow(4.0, value));
    }

    private static double clamp(double value) {
        if (value < 0.0) {
            return 0.0;
        }
        else if (value > 1.0) {
            return 1.0;
        }
        return value;
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

    /* The volume, speed and pitch are used from the next thing spoken. */
    @Override
    public boolean setVolume(double vol) {
        volume = clamp(vol);
        return true;
    }

    @Override
    public boolean setSpeed(double value) {
        speed = clamp(value);
        return true;
    }

    @Override
    public boolean setPitch(double value) {
        pitch = clamp(value);
        return true;
    }

    @Override
    public boolean setVoice(SpeechVoice newVoice) {
        if (newVoice == null) {
            return false;
        }

        for (int i = 0; i < voices.size(); i++) {
            if (voices.get(i).getName().equals(newVoice.getName())) {
                voice = voices.get(i);
                voiceIdentifier = voiceIdentifiers.get(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void reinitialize() {
        stopNative();
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
        return TextToSpeechEngine.APPLE_COCOA;
    }
}
