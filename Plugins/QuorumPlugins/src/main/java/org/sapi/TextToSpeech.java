/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sapi;

/**
 * This class needs to be converted over to the new system, along
 * with the appropriate package changes, but for now, it handles the native
 * declarations for the old SAPI dll.
 * 
 * @author Andreas Stefik
 */
public class TextToSpeech {
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        destroyTTSNative();
    }

    /** This method should never be called directly.
     *
     * @param string
     */
    public native void speakNative(String string);

     /** This method should never be called directly.
     *
     * @param string
     */
     public native void speakBlockNative(String string);

     /**
     * Determines whether the screen reader is currently speaking or not
     * @return boolean
     */

    public native boolean isSpeaking();

     /** This method sets the voice native to the platform. For example,
     *  on windows you can change between Microsoft Mary and
     *  Microsoft Anna.
     *
     * @param string
     * @return
     */
    public native boolean setVoiceNative(String string);

    /**
     * This function attempts to change the speech engine to another type,
     * if it is available. For example, you can request to load JAWS. If a
     * a problem occurs (e.g., JAWS is not available), this method
     * returns null and the request is ignored.
     *
     * @param engine
     * @return
     */
    public native boolean setTextToSpeechEngine(String string);

    /**
     * Returns a string representation of the currently located text to
     * speech engine.
     * 
     * @return
     */
    public native String getTextToSpeechEngine();

    /**
     * Pauses the text to speech engine.
     *
     * @return
     */
    public native boolean pauseNative();

    /**
     * Resumes the text to speech engine.
     * @return
     */
    public native boolean resumeNative();
    
    /** This method should never be called directly.
     */
    public native void stopNative();

    /**
     * Sets the rate of the current text to speech engine.
     * @param rate
     * @return
     */
    public native boolean setRateNative(long rate);

    /**
     * Gets the rate of the current text to speech engine.
     * @return
     */
    public native long getRateNative();

     /**
     * Sets the rate of the current text to speech engine.
     * @param rate
     * @return
     */
    public native boolean setVolumeNative(long rate);

    /**
     * Gets the rate of the current text to speech engine.
     * @return
     */
    public native long getVolumeNative();

    /** This method should never be called directly.
     */
    public native void createTTSNative();

    /** This method should never be called directly.
     */
    public native void destroyTTSNative();

    /** Returns a list of every voice on the system. For example, on windows
     *  this returns a list of all voices listed in the windows registry.
     *
     * @return
     */
    public native String getVoicesNative();

    /** Unloads screen reader currently being used and reloads a screen reader.
     */
    public native void reinitializeNative();
}
