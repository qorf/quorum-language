/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.sound;

import java.io.File;
import org.openide.modules.InstalledFileLocator;

/**
 *
 * @author Andreas Stefik, with code borrowed from the web
 */
public class SoundUtility {
    public static final String SOUND_FILE_ROOT = "sounds";
    public static final String CODE_NAME_BASE = "org.accessibility";
    private File root = null;
    private static SoundUtility player = null;

    private SoundUtility() {
        File file = InstalledFileLocator.getDefault().locate(
                SOUND_FILE_ROOT, CODE_NAME_BASE, false);
        root = file;
    }

    public static synchronized SoundUtility instance() {
        if(player == null) {
            player = new SoundUtility();
        }
        return player;
    }
    
    public void play(String name) {
        ThreadedSoundPlayer sound = new ThreadedSoundPlayer();
        File file = new File(root.getAbsolutePath() + "/" + name);
        String path = file.getAbsolutePath();
        sound.setSoundFile(path);
        Thread thread = new Thread(sound);
        thread.start();
    }
    
    /**
     * Plays a short clicking sound.
     */
    public void playClick() {
        //if (AccessibilityOptions.isSelfVoicing())
            play("Click.wav");
    }
    
    public void playTopOfEditor() {
        playBeep();
    }
    
    public void playTopOfTree() {
        playBeep();
    }
    
    public void playTopOfList() {
        playBeep();
    }
    
    /**
     * A gentle beep to notify the user that the movement they attempted cannot
     * be performed, such as going higher in a tree view when already at the top,
     * etc.
     */
    private void playBeep() {
        //if (AccessibilityOptions.isSelfVoicing())
            play("topOfEditor.wav");
    }

    /**
     * A quick beep notifying the user when they can no longer go up or down
     * in a table, or when they can no longer go left or right.
     */
    public void playTopOfTable() {
        playBeep();
    }

    /**
     * The sound to play when a compiler error is encountered in the editor.
     */
    public void playCompilerError() {
        //if (AccessibilityOptions.isSelfVoicing())
            play("CompilerError.wav");
    }
}