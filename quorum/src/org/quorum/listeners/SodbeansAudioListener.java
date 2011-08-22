/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.listeners;

import org.quorum.debugger.Action;
import org.quorum.debugger.audio.SodbeansSoundMap;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.quorum.vm.interfaces.VirtualMachineEvent;
import org.quorum.vm.interfaces.VirtualMachineListener;

/**
 *
 * @author Andreas Stefik
 */
public class SodbeansAudioListener implements VirtualMachineListener{
    private SodbeansSoundMap map = new SodbeansSoundMap();
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();

    public void actionPerformed(VirtualMachineEvent event) {
        //for now do nothing, just to test the routing through the architecture
        Action action = map.getAction(event);
        if(action != null) {
            String message = action.speak();
            if(message.compareTo("") != 0) { //something needs to be said
                speech.speak(message, SpeechPriority.MEDIUM);
            }
        }
    }
}
