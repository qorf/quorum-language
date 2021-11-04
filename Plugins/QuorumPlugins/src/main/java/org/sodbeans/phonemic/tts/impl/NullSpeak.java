/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sodbeans.phonemic.tts.impl;

import java.util.ArrayList;
import java.util.Iterator;
import org.sodbeans.phonemic.RequestType;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.SpeechProcessor;
import org.sodbeans.phonemic.SpeechVoice;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.sodbeans.phonemic.tts.TextToSpeechEngine;

/**
 *
 * @author stefika
 */
public class NullSpeak implements TextToSpeech{

    public boolean canBlock() {
        return true;
    }

    public boolean canPause() {
        return true;
    }

    public boolean canResume() {
        return true;
    }

    public boolean canStop() {
        return true;
    }

    public boolean canSetVoice() {
        return true;
    }

    public boolean canSetVolume() {
        return true;
    }

    public boolean canSetSpeed() {
        return true;
    }

    public boolean canSetPitch() {
        return true;
    }

    public Iterator<SpeechVoice> getAvailableVoices() {
        ArrayList<SpeechVoice> speech = new ArrayList<SpeechVoice>();
        return speech.iterator();
    }

    public SpeechVoice getCurrentVoice() {
        SpeechVoice voice = new SpeechVoice();
        return voice;
    }

    public double getSpeed() {
        return 0.5;
    }

    public TextToSpeechEngine getTextToSpeechEngine() {
        return TextToSpeechEngine.NULL;
    }

    public boolean setTextToSpeechEngine(TextToSpeechEngine engine) {
        return false;
    }

    public Iterator<TextToSpeechEngine> getAvailableEngines() {
        ArrayList<TextToSpeechEngine> speech = new ArrayList<TextToSpeechEngine>();
        speech.add(TextToSpeechEngine.NULL);
        return speech.iterator();
    }

    public double getVolume() {
        return 1.0;
    }

    public double getPitch() {
        return 0.5;
    }

    public boolean isSpeaking() {
        return false;
    }

    public boolean pause() {
        return false;
    }

    public void reinitialize() {
    }

    public boolean respeak() {
        return false;
    }

    public boolean copyToClipboard() {
        return false;
    }

    public boolean resume() {
        return false;
    }

    public boolean setVolume(double vol) {
        return false;
    }

    public boolean setSpeed(double speed) {
        return false;
    }

    public boolean setPitch(double pitch) {
        return false;
    }

    public boolean setVoice(SpeechVoice voice) {
        return false;
    }

    public boolean speak(String text) {
        return false;
    }

    public boolean speak(String text, SpeechPriority priority) {
        return false;
    }

    public boolean speak(String text, SpeechPriority priority, RequestType type) {
        return false;
    }

    public boolean speak(char c) {
        return false;
    }

    public boolean speak(char c, SpeechPriority priority) {
        return false;
    }

    public boolean speak(SpeechProcessor proc) {
        return false;
    }

    public boolean speakBlocking(String text) {
        return false;
    }

    public boolean speakBlocking(String text, SpeechPriority priority) {
        return false;
    }

    public boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
        return false;
    }

    public boolean speakBlocking(char c) {
        return false;
    }

    public boolean speakBlocking(char c, SpeechPriority priority) {
        return false;
    }

    public boolean stop() {
        return false;
    }

    public void setSpeechEnabled(boolean enabled) {
    }

    public boolean isSpeechEnabled() {
        return false;
    }

    public double getVersion() {
        return TextToSpeechFactory.PHONEMIC_VERSION;
    }
    
}
