/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Sound;

import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * This is a plugin for supporting text-to-speech in compiled Quorum code.
 * 
 * @author Andreas Stefik
 */
public class Speech {
    public java.lang.Object me_ = null;
    private static TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    
    /*
    Determines whether or not the currently loaded text-to-speech engine
    can issue a speech blocking call.

    Attribute: Returns whether the speech engine can block.

    Attribute: Example
    Speech speech
    boolean block = speech:CanBlock()
    */
    public boolean CanBlock() {
        return speech.canBlock();
    }

    /*
    Determines whether or not the currently loaded text-to-speech engine
    can issue a speech pause call.

    Attribute: Returns whether the speech engine can pause.

    Attribute: Example
    Speech speech
    boolean pause = speech:CanPause()
    */
    public boolean CanPause() {
        return speech.canPause();
    }

    /*
    Determines whether or not the currently loaded text-to-speech engine
    can issue a speech resume call. This only matters if the speech
    engine is currently paused.

    Attribute: Returns whether the speech engine can resume from a pause.

    Attribute: Example
    Speech speech
    boolean resume = speech:CanResume()
    */
    public boolean CanResume() {
        return speech.canResume();
    }

    /*
    Determines whether or not the currently loaded text-to-speech engine
    can have its voice changed.

    Attribute: Returns whether the speech engine can have a voice change.

    Attribute: Example
    Speech speech
    boolean voice = speech:CanSetVoice()
    */
    public boolean CanSetVoice() {
        return speech.canSetVolume();
    }

    /*
    Determines whether or not the currently loaded text-to-speech engine
    can have its volume changed.

    Attribute: Returns whether the speech engine can have a volume change.

    Attribute: Example
    Speech speech
    boolean volume = speech:CanSetVolume()
    */
    public boolean CanSetVolume() {
        return speech.canSetVolume();
    }

    /*
    Sets the current volume of the text-to-speech engine. The legal
    values are from 0.0, no volume, to 1.0 maximum volume.

    Attribute: Parameter value The new volume

    Attribute: Example
    Speech speech
    speech:SetVolume(1.0)
    */
    public void SetVolume(double value) {
        speech.setVolume(value);
    }

    /*
    Sets the current volume of the text-to-speech engine. The legal
    values are from 0.0, no volume, to 1.0 maximum volume.

    Attribute: Returns The current volume on the system.

    Attribute: Example
    Speech speech
    number volume = speech:GetVolume()
    */
    public double GetVolume() {
        return speech.getVolume();
    }

    /*
    Sets the current pitch of the text-to-speech engine. The legal
    values are from 0.0, lowest pitch, to 1.0 maximum pitch.

    Attribute: Parameter value The new pitch.

    Attribute: Example
    Speech speech
    speech:SetPitch(1.0)
    */
    public void SetPitch(double value) {
        speech.setPitch(value);
    }

    /*
    Sets the current pitch of the text-to-speech engine. The legal
    values are from 0.0, lowest pitch, to 1.0 maximum pitch.

    Attribute: Returns The current pitch on the system.

    Attribute: Example
    Speech speech
    number pitch = speech:GetPitch()
    */
    public double GetPitch() {
        return speech.getPitch();
    }

    /*
    Sets the current speed of the text-to-speech engine. The legal
    values are from 0.0, slowest speed, to 1.0 maximum speed. Each speech engine
    implementation calculates speed slightly different, but this speech
    engine should sound approximately equal across implementations.

    Attribute: Parameter value The new volume

    Attribute: Example
    Speech speech
    speech:SetSpeed(1.0)
    */
    public void SetSpeed(double value) {
        speech.setSpeed(value);
    }

    /*
    Sets the current speed of the text-to-speech engine. The legal
    values are from 0.0, lowest speed, to 1.0 maximum speed. Each speech engine
    implementation calculates speed slightly different, but this speech
    engine should sound approximately equal across implementations.

    Attribute: Returns The current volume on the system.

    Attribute: Example
    Speech speech
    number speed = speech:GetSpeed()
    */
    public double GetSpeed() {
        return speech.getSpeed();
    }

    /*
    Determines whether or not the currently loaded text-to-speech engine
    can have its speed changed.

    Attribute: Returns whether the speech engine can have a speed change.

    Attribute: Example
    Speech speech
    boolean speed = speech:CanSetSpeed()
    */
    public boolean CanSetSpeed() {
        return speech.canSetSpeed();
    }

    /*
    Instructs the system to speak a particular phrase through the current
    text-to-speech engine.
    
    Attribute: Parameter value The text to be spoken.

    Attribute: Example
    Speech speech
    speech:Say("Hello, World!")
    */
    public void Say(String value) {
        speech.speak(value);
    }

    /*
    Instructs the system to speak a particular phrase through the current
    text-to-speech engine.

    Attribute: Parameter value The text to be spoken.
    Attribute: Parameter block Whether or not this is a blocking call, which will
        cause the rest of the program to pause as it is executed.

    Attribute: Example
    Speech speech
    speech:Say("Hello, World!")
    */
    public void Say(String value, boolean block) {
        if(block) {
            speech.speakBlocking(value);
        }
        else {
            speech.speak(value);
        }
    }
    
    /**
     * Instructs the system to speak a particular phrase through the current
     * text-to-speech engine. This method is used for the compiler
     * to call a static say function when the "say" command is issued
     * in Quorum. By default, say calls are "blocking." The reason they 
     * are blocking calls by default is because otherwise if a user 
     * wrote a program like say "Hello, World!" and ran it, phonemic would
     * , correctly, only have threaded calls remaining, which would allow
     * the entire program to quit. As such, it would appear as if Quorum
     * was not doing anything when issuing say calls.
     * 
     * @param value 
     */
    public static void StaticSay(String value) {
        speech.speakBlocking(value);
    }
    
    public static void StaticSay(int value) {
        speech.speakBlocking("" + value);
    }
    
    public static void StaticSay(double value) {
        speech.speakBlocking("" + value);
    }
    
    public static void StaticSay(boolean value) {
        speech.speakBlocking("" + value);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Speech speech = new Speech();
        speech.Say("Hello, world!");
    }
}
