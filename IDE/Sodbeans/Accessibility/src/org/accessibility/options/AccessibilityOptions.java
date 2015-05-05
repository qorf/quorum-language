package org.accessibility.options;

import org.accessibility.windows.GeneralPanel;
import org.openide.util.NbPreferences;


/**
 *  This class provides a set of accessibility options that can be set programmatically
 *  by any other classes, with or without going through the preferences window.
 * 
 *  One important note with this class is that it is a helper class to determine
 *  what settings have been stored on the NetBeans platform. It does not
 *  interact with the components on the system that control accessibility. In other
 *  words, changing the settings here alone will have no impact unless you interact
 *  with other components as well. It is useful, however, for changing the 
 *  settings from someplace other than the properties window.
 * 
 * @author Andreas Stefik
 */
public class AccessibilityOptions {
    /**
     * This flag determines whether the accessibility module has started at least
     * one time. If it hasn't, then an accessibility window pops up asking if
     * the user would like self-voicing turned on.
     */
    public static final String STARTED_ONCE = "startedOnce";
    
    public static boolean isStartedOnce() {
        return NbPreferences.forModule(GeneralPanel.class).getBoolean(STARTED_ONCE, false);
    }
    
    public static void setStartedOnce(boolean read) {
        NbPreferences.forModule(GeneralPanel.class).putBoolean(STARTED_ONCE, read);
    }
    
    /**
     * Determines whether self voicing is active on the system. If this
     * property is off, then menus, navigation, and the editor, will
     * not read.
     */
    public static final String SELF_VOICING = "selfVoicing";
    
    /**
     * Determines whether self voicing is active on the system. If this
     * property is off, then menus, navigation, and the editor, will
     * not read.
     * 
     * @return 
     */
    public static boolean isSelfVoicing() {
        return NbPreferences.forModule(GeneralPanel.class).getBoolean(SELF_VOICING, false);
    }
    
   /**
     * Sets whether self voicing is active on the system. If this
     * property is off, then menus, navigation, and the editor, will
     * not read.
     * 
     * @param read if true, the screen will be self voiced.
     */
    public static void setSelfVoicing(boolean read) {
        NbPreferences.forModule(GeneralPanel.class).putBoolean(SELF_VOICING, read);
    }
    
   /**
     * Determines whether any accessible debugger will talk on the system.
     */
    public static final String TALKING_DEBUGGING = "talkingDebugging";
    
   /**
     * Determines whether any accessible debugger will talk on the system.
     * 
     * @return 
     */
    public static boolean isTalkingDebugging() {
        return NbPreferences.forModule(GeneralPanel.class).getBoolean(TALKING_DEBUGGING, true);
    }
    
   /**
     * Sets whether any accessible debugger will talk on the system.
     * 
     * @param read if true, the screen will be self voiced.
     */
    public static void setTalkingDebugging(boolean read) {
        NbPreferences.forModule(GeneralPanel.class).putBoolean(TALKING_DEBUGGING, read);
    }
    
    /**
     * This option specifies whether or not NetBeans should issue a sound
     * on a compiler error in the editor.
     */
    public static final String SOUND_ON_ERROR = "soundOnError";

   /**
     * This option specifies whether or not NetBeans should issue a sound
     * on a compiler error in the editor.
     * 
     * @return 
     */
    public static boolean isSoundOnError() {
        return NbPreferences.forModule(GeneralPanel.class).getBoolean(SOUND_ON_ERROR, true);
    }
    
   /**
     * This option specifies whether or not NetBeans should issue a sound
     * on a compiler error in the editor.
     * 
     * @param read
     */
    public static void setSoundOnError(boolean read) {
        NbPreferences.forModule(GeneralPanel.class).putBoolean(SOUND_ON_ERROR, read);
    }
    
    /**
     * This option specifies whether or not the user can request a particular
     * user interface element is spoken through speech.
     */
    public static final String SPEECH_ON_REQUEST = "speechOnRequest";
    
   /**
     * This option specifies whether or not the user can request a particular
     * user interface element is spoken through speech.
     * 
     * @return 
     */
    public static boolean isSpeechOnRequest() {
        return NbPreferences.forModule(GeneralPanel.class).getBoolean(SPEECH_ON_REQUEST, true);
    }
    
   /**
     * This option specifies whether or not the user can request a particular
     * user interface element is spoken through speech.
     * 
     * @param read
     */
    public static void setSpeechOnRequest(boolean read) {
        NbPreferences.forModule(GeneralPanel.class).putBoolean(SPEECH_ON_REQUEST, read);
    }
    
    /**
     * This option determines whether magnification should be on by default.
     */
    public static final String MAGNIFICATION_ON = "magnificationOn";
    
   /**
     * This option determines whether magnification should be on by default.
     * 
     * @return 
     */
    public static boolean isMagnificationOn() {
        return NbPreferences.forModule(GeneralPanel.class).getBoolean(MAGNIFICATION_ON, true);
    }
    
   /**
     * This option determines whether magnification should be on by default.
     * 
     * @param read
     */
    public static void setMagnificationOn(boolean read) {
        NbPreferences.forModule(GeneralPanel.class).putBoolean(MAGNIFICATION_ON, read);
    }

    /**
     * Stores the current speech volume. (0.0 to 1.0).
     */
    public static final String SPEECH_VOLUME = "speechVolume";
    
    /**
     * Stores the current speech volume. (0.0 to 1.0).
     */
    public static int getSpeechVolume() {
        return NbPreferences.forModule(GeneralPanel.class).getInt(SPEECH_VOLUME, 100);
    }
    
    /**
     * Stores the current speech volume. (0.0 to 1.0).
     */
    public static void setSpeechVolume(int vol) {
        NbPreferences.forModule(GeneralPanel.class).putInt(SPEECH_VOLUME, vol);
    }
    
    /**
     * Stores the current speech speed. (0.0 to 1.0).
     */
    public static final String SPEECH_SPEED = "speechSpeed";
    
    /**
     * Stores the current speech speed. (0.0 to 1.0).
     */
    public static int getSpeechSpeed() {
        return NbPreferences.forModule(GeneralPanel.class).getInt(SPEECH_SPEED, 50);
    }

    /**
     * Stores the current speech speed. (0.0 to 1.0).
     */
    public static void setSpeechSpeed(int speed) {
        NbPreferences.forModule(GeneralPanel.class).putInt(SPEECH_SPEED, speed);
    }
    /**
     * Stores the current speech pitch. (0.0 to 1.0)
     */
    public static final String SPEECH_PITCH = "speechPitch";
    
   /**
     * Stores the current speech pitch. (0.0 to 1.0)
     */
    public static int getSpeechPitch() {
        return NbPreferences.forModule(GeneralPanel.class).getInt(SPEECH_PITCH, 50);
    }

   /**
     * Stores the current speech pitch. (0.0 to 1.0)
     */
    public static void setSpeechPitch(int pitch) {
        NbPreferences.forModule(GeneralPanel.class).putInt(SPEECH_PITCH, pitch);
    }
    
    /**
     * Stores the current speech engine.
     */
    public static final String SPEECH_ENGINE = "speechEngine";
    
   /**
     * Stores the current speech engine.
     */
    public static String getSpeechEngine() {
        return NbPreferences.forModule(GeneralPanel.class).get(SPEECH_ENGINE, "");
    }
    
   /**
     * Stores the current speech engine.
     */
    public static void setSpeechEngine(String engine) {
        NbPreferences.forModule(GeneralPanel.class).put(SPEECH_ENGINE, engine);
    }
    
    /**
     * Returns the currently selected voice on the system. If
     * this value returns the empty string, then its value is set to
     * whatever voice the system defaults to.
     */
    public static final String SELECTED_VOICE = "selectedVoice";
    
    /**
     * Returns the currently selected voice on the system. If
     * this value returns the empty string, then its value is set to
     * whatever voice the system defaults to.
     */
    public static void setSelectedVoice(String voice) {
        NbPreferences.forModule(GeneralPanel.class).put(SELECTED_VOICE, voice);
    }
    
    /**
     * Returns the currently selected voice on the system. If
     * this value returns the empty string, then its value is set to
     * whatever voice the system defaults to.
     */
    public static String getSelectedVoice() {
        return NbPreferences.forModule(GeneralPanel.class).get(SELECTED_VOICE, "");
    }
}
