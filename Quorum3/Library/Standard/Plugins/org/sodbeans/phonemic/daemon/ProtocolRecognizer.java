/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sodbeans.phonemic.daemon;

import java.net.Socket;
import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * A ProtocolRecognizer handles individual client requests and executes them.
 * They may also produce a response to be sent to the client.
 * 
 * @author jeff
 */
public abstract class ProtocolRecognizer {
    /**
     * The text to speech engine the server will manipulate.
     */
    protected TextToSpeech textToSpeech = null;
    
    public abstract String parse(char[] data);

    /**
     * @return the textToSpeech
     */
    public TextToSpeech getTextToSpeech() {
        return textToSpeech;
    }

    /**
     * @param textToSpeech the textToSpeech to set
     */
    public void setTextToSpeech(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
    }
}
