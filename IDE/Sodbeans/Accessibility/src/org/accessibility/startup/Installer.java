/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.startup;

import java.io.File;
import org.accessibility.options.AccessibilityOptions;
import org.openide.modules.InstalledFileLocator;
import org.openide.modules.ModuleInstall;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;

public class Installer extends ModuleInstall {
    
    public static final String MAC_SPEECH = "modules/ext/CocoaSpeechServer.app/Contents/MacOS/CocoaSpeechServer";
    public static final String CODE_NAME_BASE = "org.accessibility";
    public static final String STARTUP_STRING = "Starting NetBeans";

    @Override
    public void restored() {
        File file = InstalledFileLocator.getDefault().locate(
                MAC_SPEECH, CODE_NAME_BASE, false);
        //this is a workaround for NetBeans blowing away execute permissions
        //when it builds into the cluster.
        file.setExecutable(true);
        
        TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
        if(AccessibilityOptions.isSelfVoicing()) {
            speech.speak(STARTUP_STRING);
        }
    }

}
