%%{
machine phonemic;
################################################################################
# V1Recognizer.rl - The backend for the Phonemic TCP Protocol
#
# This is a finite state machine that defines the acceptable input for the
# Phonemic TCP protocol. To compile this code, use the 'ragel' utility, which
# can be obtained from:
#
# http://www.complang.org/ragel/
#
# The recommended ragel command to run is:
# ragel -J V1Recognizer.rl -o V1Recognizer.java
#
# See the fie "V1RecognizerActions.rl" for the common actions performed during
# parsing.
################################################################################
}%%

package org.sodbeans.phonemic.daemon;

import org.sodbeans.phonemic.*;
import org.sodbeans.phonemic.tts.*;
import java.util.Iterator;

/**
 * This class represents a DFA capable of recognizing Phonemic protocol
 * messages. After parsing the message, the appropriate Phonemic call is
 * executed using the provided TextToSpeech instance. Protocol messages
 * may be written back to the client using the required Writer instance.
 * 
 * @author jeff
 */
public class V1Recognizer extends ProtocolRecognizer {
    %%{
        # Defined actions.
        include "V1RecognizerActions.rl";

        ############################
        ## PROTOCOL SPECIFICATION ##
        ############################

        # Represents a decimal value greater than or equal to zero and less than 2.
        decimal = ([0-1] ('.' [0-9]+)?) $ { decimalString.append(fc); };

        # Represents text to speak--can be any set of ASCII characters.
        text = any+ $ { textToSpeak.append(fc); };
        
        # Priority parameters - uses embedded actions, so it looks a bit ugly.
        blocking = "BLOCKING" @ { priority = SpeechPriority.BLOCKING; };
        highest = "HIGHEST" @ { priority = SpeechPriority.HIGHEST; };
        high = "HIGH" @ { priority = SpeechPriority.HIGH; };
        medium_high = "MEDIUM_HIGH" @ { priority = SpeechPriority.MEDIUM_HIGH; };
        medium = "MEDIUM" @ { priority = SpeechPriority.MEDIUM; };
        medium_low = "MEDIUM_LOW" @ { priority = SpeechPriority.MEDIUM_LOW; };
        low = "LOW" @ { priority = SpeechPriority.LOW; };
        lowest = "LOWEST" @ { priority = SpeechPriority.LOWEST; };
        priority = blocking | highest | high | medium_high | medium | medium_low | low | lowest;

        # Represents the name of a voice.
        voiceName = any+ $ { voiceName.append(fc); };

        # Represents a speech voice. The only language supported is en_US, so hardcode this for now.
        voice = voiceName;

        # Supported engines--again, some embedded actions, so not too pretty.
        jaws = "JAWS" % { engine = TextToSpeechEngine.JAWS; };
        nvda = "NVDA" % { engine = TextToSpeechEngine.NVDA; };
        microsoft_sapi = "MICROSOFT_SAPI" % { engine = TextToSpeechEngine.MICROSOFT_SAPI; };
        apple_carbon = "APPLE_CARBON" % { engine = TextToSpeechEngine.APPLE_CARBON; };
        apple_say = "APPLE_SAY" % { engine = TextToSpeechEngine.APPLE_SAY; };
        speech_dispatcher = "SPEECH_DISPATCHER" % { engine = TextToSpeechEngine.SPEECH_DISPATCHER; };
        nullEngine = "NULL" % { engine = TextToSpeechEngine.NULL; };
        engine = jaws | nvda | microsoft_sapi | apple_carbon | apple_say | speech_dispatcher | nullEngine;

        # The request types--"text" and "character."
        textRequest = "TEXT" @ { requestType = RequestType.TEXT; };
        charRequest = "CHARACTER" @ { requestType = RequestType.CHARACTER; };
        requestType = textRequest | charRequest;

        # "Speak" commands...
        speak = "speak:" priority ":" requestType ":" text* %speak;
        speakBlocking = "speakBlocking:" priority ":" requestType ":" text* %speakBlocking;

        # Stop command
        stop = "stop" %stop;

        # Commands for getting/setting voice.
        setVoice = "setVoice:" voice %setVoice;
        getCurrentVoice = "getCurrentVoice" %getCurrentVoice;
        getAvailableVoices = "getAvailableVoices" %getAvailableVoices;

        # Commands for pausing/resuming.
        pause = "pause" %pause;
        resume = "resume" %resume;

        # Volume manipulation commands.
        getVolume = "getVolume" %getVolume;
        setVolume = "setVolume:" decimal %setVolume;

        # Speed manipulation commands.
        getSpeed = "getSpeed" %getSpeed;
        setSpeed = "setSpeed:" decimal %setSpeed;

        # Pitch manipulation commands.
        getPitch = "getPitch" %getSpeed;
        setPitch = "setPitch:" decimal %setPitch;
        
        # Check speech, turn it on and off.
        isSpeaking = "isSpeaking" %isSpeaking;
        isSpeechEnabled = "isSpeechEnabled" %isSpeechEnabled;
        enableSpeech = "setSpeechEnabled:true" %enableSpeech;
        disableSpeech = "setSpeechEnabled:false" %disableSpeech;

        # Capability checking methods.
        canBlock = "canBlock" %canBlock;
        canPause = "canPause" %canPause;
        canResume = "canResume" %canResume;
        canSetPitch = "canSetPitch" %canSetPitch;
        canSetSpeed = "canSetSpeed" %canSetSpeed;
        canSetVoice = "canSetVoice" %canSetVoice;
        canSetVolume = "canSetVolume" %canSetVolume;
        canStop = "canStop" %canStop;
        
        # Engine manipulation.
        getTextToSpeechEngine = "getTextToSpeechEngine" %getTextToSpeechEngine;
        setTextToSpeechEngine = "setTextToSpeechEngine:" engine %setTextToSpeechEngine;
        getAvailableEngines = "getAvailableEngines" %getAvailableEngines;
        reinitialize = "reinitialize" %reinitialize;
        respeak = "respeak" %respeak;

        # What version of phonemic is it?
        getVersion = "getVersion" %getVersion;

        # This is the main set of commands we will recognize. This is the "start rule," basically.
        command =   speak
                  | speakBlocking
                  | stop
                  | pause
                  | resume
                  | getAvailableVoices
                  | getCurrentVoice
                  | setVoice
                  | getVolume
                  | setVolume
                  | getSpeed
                  | setSpeed
                  | getPitch
                  | setPitch
                  | isSpeechEnabled
                  | enableSpeech
                  | disableSpeech
                  | isSpeaking
                  | getTextToSpeechEngine
                  | setTextToSpeechEngine
                  | getAvailableEngines
                  | canBlock
                  | canPause
                  | canResume
                  | canSetVoice
                  | canSetVolume
                  | canSetSpeed
                  | canSetPitch
                  | canStop
                  | reinitialize
                  | respeak
                  | getVersion
                  ;

        # Instantiate DFA "main" with "command" as the "start rule."
        main := command;

        # Write the DFA information.
        write data;
    }%%

    /**
     * Parse the given input packet and execute the command given. If any text
     * is to be written back to the client, it will be returned. If no text is
     * to be written, null is returned.
     */
    public String parse(char[] data) {
        /**
         * These values are required for Ragel,
         * and shouldn't be changed without good reason.
         */
        int cs = 0; // required for ragel (current state)
        int p = 0; // required for ragel (pointer to start)
        int pe = data.length; // required for ragel (pointer to end)
        int eof = data.length; // required for ragel (pointer to eof)
        
        /**
         * The parameters we will eventually pass into phonemic.
         * These must be good defaults, as they may never change from this initial
         * value (so "null" is not acceptable).
         */
        StringBuilder textToSpeak = new StringBuilder();
        StringBuilder decimalString = new StringBuilder();
        StringBuilder voiceName = new StringBuilder();
        RequestType requestType = RequestType.TEXT;
        SpeechPriority priority = SpeechPriority.MEDIUM;
        TextToSpeechEngine engine = textToSpeech.getTextToSpeechEngine(); 

        /**
         * The value to write to the client (null if none).
         */
        String response = null;

        // Initialize the machine.
        %% write init;

        // Execute the machine.
        %% write exec;   
     
        // Did we make it to the end of the input? If not, an error occurred.
        if (p != pe) {
            response = "error";
        }

        // If the response is non-null, attach a newline character to the end.
        if (response != null)
            response = response + "\n";

        // Give the user their answer (if any).
        return response;
    }
}