 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sodbeans.phonemic.start;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.mac.VoiceOver;
import org.sodbeans.phonemic.tts.*;

/**
 *
 * A main entry point for phonemic, for basic command line text to speech
 * support.
 * 
 * @author Andreas Stefik and Jeff Wilson
 */
public class Main {
    static public TextToSpeech speech;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.init();
        if(args.length == 0) {

            speech.speakBlocking("Monkey");
            speech.speakBlocking("Taco");
            speech.speakBlocking("Fish");
        }
        else if (args.length == 1) {
            String text = args[0];
            if(args[0].compareTo("-path") == 0) {
                String path = System.getProperty("java.library.path");
                System.out.println("Your JNI path for loading Phonemic libraries is: " + path);
            }
            else{
                speech.speakBlocking(text);
            }
        }
    }

    private static void init() {
        getDefaultTextToSpeech();
    }

    public synchronized static TextToSpeech getDefaultTextToSpeech() {
        if(speech == null) {
            speech = TextToSpeechFactory.getDefaultTextToSpeech();
        }
        return speech;
    }
}