/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.tts;

import java.util.HashMap;

/**
 * Provides a standard enumerated type for representing the text to speech
 * engine on the system.
 * 
 * @author Andreas Stefik
 */
public enum TextToSpeechEngine {
    JAWS ("JAWS"),
    NVDA ("NVDA"),
    MICROSOFT_SAPI ("MICROSOFT_SAPI"),
    APPLE_CARBON ("APPLE_CARBON"),
    APPLE_COCOA ("APPLE_COCOA"),
    APPLE_SAY ("APPLE_SAY"),
    VOICEOVER ("VOICE_OVER"),
    SPEECH_DISPATCHER ("SPEECH_DISPATCHER"),
    NULL ("NULL");

    private String engineName;
    TextToSpeechEngine(String name) {
        engineName = name;
    }

    /**
     * Returns a string representation of the name of the engine.
     * 
     * @return the engineName
     */
    public String getEngineName() {
        return engineName;
    }
}
