/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.startup;

import java.io.File;
import javax.swing.JFrame;
import org.accessibility.options.AccessibilityOptions;
import org.accessibility.windows.AccessibilityStartup;
import org.openide.modules.InstalledFileLocator;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;

public class Installer extends ModuleInstall implements Runnable{
    
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
        
        WindowManager manager = WindowManager.getDefault();
        manager.invokeWhenUIReady(this);
        
        
    }

    @Override
    public void run() {
        TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
        JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
        
        if(!AccessibilityOptions.isStartedOnce()) {
            AccessibilityStartup startup = new AccessibilityStartup(frame, true);
            startup.setLocationRelativeTo(frame);
            startup.setVisible(true);
            boolean voiced = startup.isSelfVoiced();
            AccessibilityOptions.setSelfVoicing(voiced);
            AccessibilityOptions.setStartedOnce(true);
        }
        
        if(AccessibilityOptions.isSelfVoicing()) {
            speech.speak(STARTUP_STRING);
        }
    }

}
