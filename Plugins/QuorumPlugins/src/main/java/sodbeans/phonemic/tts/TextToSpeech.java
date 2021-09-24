/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts;

import java.util.Iterator;
import org.sodbeans.phonemic.*;

/**
 * The TextToSpeech interface represents all functions that must be implemented
 * by supported Text To Speech engines. If a particular function is not
 * supported, the function should fail gracefully.
 *
 * @author Andreas Stefik and Jeff Wilson
 */
public interface TextToSpeech {
    /**
     * Determines of the engine can speak blocking calls.
     *
     * @return
     */
    public boolean canBlock();

    /**
     * Determines whether the text to speech engine can pause speech that is
     * currently being spoken.
     *
     * @return
     */
    public boolean canPause();

    /**
     * Determines whether the text to speech engine can resume paused speech.
     *
     * @return
     */
    public boolean canResume();

    /**
     * Determines whether the text to speech engine can stop speech.
     *
     * @return
     */
    public boolean canStop();

    /**
     * Determines whether the text to speech engine can set the current voice.
     *
     * @return
     */
    public boolean canSetVoice();

    /**
     * Determines whether the volume can be set for this text to speech engine.
     *
     * @return
     */
    public boolean canSetVolume();

    /**
     * Determines whether the speed the speech is read can be set for the
     * text to speech engine.
     *
     * @return
     */
    public boolean canSetSpeed();

    /**
     * Determines whether the engine supports pitch changes.
     *
     * @return
     */
    public boolean canSetPitch();
    
    /**
     * Get all available voices for use by the text to speech engine.
     *
     * @return available voices
     */
    public Iterator<SpeechVoice> getAvailableVoices();

    /**
     * Get the current voice in use by the text to speech engine.
     *
     * @return the current voice
     */
    public SpeechVoice getCurrentVoice();

    /**
     * Gets the current speed. (0.0 - 1.0)
     *
     * @return the speed
     */
    public double getSpeed();

    /**
     * Returns an enumerated type representing the current text to speech engine
     * that is loaded on the system.
     * 
     * @return the current text to speech engine
     */
    public TextToSpeechEngine getTextToSpeechEngine();
    
    /**
     * Sets the text to speech engine to be used by the system. Note that
     * after this function is called, all settings will be lost, such as
     * speed, pitch, volume and voice.
     * 
     * @param engine
     * @return true if the engine was changed successfully, false otherwise.
     */
    public boolean setTextToSpeechEngine(TextToSpeechEngine engine);
    
    /**
     * Returns the available engines on the system.
     * 
     * @return 
     */
    public Iterator<TextToSpeechEngine> getAvailableEngines();
    /**
     * Gets the current volume. (0.0 - 1.0)
     *
     * @return the volume
     */
    public double getVolume();

    /**
     * Gets the current pitch. (0.0 - 1.0)
     *
     * @return the pitch
     */
    public double getPitch();
    
    /**
     * Determines whether or not the engine is currently speaking.
     * Not supported by all engines. If not supported, false is always
     * returned.
     *
     * @return whether or not the engine is speaking
     */
    public boolean isSpeaking();

    /**
     * Pauses current speech (if any).
     *
     * @return indicates success or failure.
     */
    public boolean pause();
    
    /**
     * Reinitializes the text to speech engine.
     */
    public void reinitialize();

    /**
     * Respeaks the most recently uttered text.
     *
     * @return indicates success or failure.
     */
    public boolean respeak();
    
    /**
     * Copies the most recently uttered text to the clipboard.
     * 
     * @return indicates success or failure
     */
    public boolean copyToClipboard();
    
    /**
     * Resumes current speech, if any is currently paused.
     *
     * @return indicates success or failure.
     */
    public boolean resume();

    /**
     * Sets the current volume. (0.0 - 1.0)
     *
     * @param vol the new volume
     * @return indicates success or failure.
     */
    public boolean setVolume(double vol);

    /**
     * Sets the current speed. (0.0 - 1.0)
     *
     * @param speed the new speed
     * @return indicates success or failure.
     */
    public boolean setSpeed(double speed);

    /**
     * Sets the current pitch. (0.0 - 1.0)
     *
     * @param speed the new speed
     * @return indicates success or failure.
     */
    public boolean setPitch(double pitch);

    /**
     * Set the current voice.
     *
     * @param voice the new voice
     * @return indicates success or failure.
     */
    public boolean setVoice(SpeechVoice voice);

    /**
     * Speaks the given string.
     *
     * @param text The string to speak.
     * @return indicates success or failure.
     */
    public boolean speak(String text);
    
    /**
     * Speaks the given string with the given priority.
     *
     * @param text The string to speak.
     * @param priority Priority to assign to given text.
     * @return indicates success or failure.
     */
    public boolean speak(String text, SpeechPriority priority);

    /**
     * Speaks the given string with the given priority.
     *
     * @param text The string to speak.
     * @param priority Priority to assign to given text.
     * @param type A TEXT or CHARACTER request
     * @return indicates success or failure.
     */
    public boolean speak(String text, SpeechPriority priority, RequestType type);

    /**
     * Speaks the given character. Characters are
     * spoken differently by various engines. On OS X, for example, characters
     * are spoken with a higher pitch if they are capitalized letters.
     *
     * @param c The character to speak.
     * @return indicates success or failure.
     */
    public boolean speak(char c);
    
    /**
     * Speaks the given character with the given priority. Characters are
     * spoken differently by various engines. On OS X, for example, characters
     * are spoken with a higher pitch if they are capitalized letters.
     *
     *
     * @param c The character to speak.
     * @param priority Priority to assign to given text.
     * @return indicates success or failure.
     */
    public boolean speak(char c, SpeechPriority priority);

    /**
     * Speak using the SpeechProcessor proc.
     *
     * @param proc
     * @return
     */
    public boolean speak(SpeechProcessor proc);
    
    /**
     * Speaks the given string and blocks until speaking is complete.
     *
     * @param text The string to speak.
     * @return indicates success or failure.
     */
    public boolean speakBlocking(String text);

    /**
     * Speaks the given string with given priority and blocks until speaking
     * is complete.
     *
     * @param text The string to speak.
     * @param priority Priority to assign to given text.
     * @return indicates success or failure.
     */
    public boolean speakBlocking(String text, SpeechPriority priority);

    /**
     * Speaks the given string with given priority and blocks until speaking
     * is complete.
     *
     * @param text The string to speak.
     * @param priority Priority to assign to given text.
     * @param type A TEXT or CHARACTER request
     * @return indicates success or failure.
     */
    public boolean speakBlocking(String text, SpeechPriority priority, RequestType type);
    
    /**
     * Speaks the given character. Works exactly like speak(char), but blocks
     * until speaking is finished.
     *
     * @param c The character to speak.
     * @return indicates success or failure.
     */
    public boolean speakBlocking(char c);

    /**
     * Speaks the given character. Works exactly like speak(char), but blocks
     * until speaking is finished.
     *
     * @param c The character to speak.
     * @param priority Priority to assign to given text.
     * @return indicates success or failure.
     */
    public boolean speakBlocking(char c, SpeechPriority priority);

    /**
     * Stops current speech (if any).
     *
     * @return indicates success or failure.
     */
    public boolean stop();
    
    /**
     * Set whether or not speech is enabled. If speech is disabled, all
     * speak calls will be ignored.
     * @param enabled 
     */
    public void setSpeechEnabled(boolean enabled);
    
    /**
     * Returns whether or not speech is currently enabled. By default, speech
     * is enabled.
     * @return 
     */
    public boolean isSpeechEnabled();
    
    /**
     * Get the phonemic version being used by this instance of TextToSpeech. If
     * this instance is connected to a server, this returns the server's Phonemic
     * version.
     * 
     * @return 
     */
    public double getVersion();
}
