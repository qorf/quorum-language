/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.options;

import org.openide.util.NbPreferences;
/**
 * The settings manager for the magnifier.
 * @author jeff
 */
public class MagnificationOptions {
    /**
     * Determines whether or not magnification is enabled.
     */
    public static final String MAGNIFICATION_ENABLED = "MagnificationEnabled";
    
    /**
     * Determines whether or not full screen mode is enabled. The alternative is
     * windowed mode.
     */
    public static final String FULLSCREEN_ENABLED = "FullScreenEnabled";
    
    /**
     * Determines the zoom level used by the magnifier.
     */
    public static final String ZOOM_LEVEL = "ZoomLevel";
    
    /**
     * The width of the magnifier window (when in windowed mode).
     */
    public static final String MAGNIFIER_WIDTH = "MagnifierWidth";
    
    /**
     * The height of the magnifier window (when in windowed mode).
     */
    public static final String MAGNIFIER_HEIGHT = "MagnifierHeight";
    
    /**
     * Is magnification enabled?
     * 
     * @return true if magnification is enabled, false otherwise. 
     */
    public static boolean isMagnificationEnabled() {
        return NbPreferences.forModule(MagnificationOptions.class).getBoolean(MAGNIFICATION_ENABLED, false);
    }
    
    /**
     * Set whether or not the magnifier is turned on. By default, it is off.
     * @param enabled 
     */
    public static void setMagnificationEnabled(boolean enabled) {
        NbPreferences.forModule(MagnificationOptions.class).putBoolean(MAGNIFICATION_ENABLED, enabled);
    }
    
    /**
     * Is full-screen magnification enabled?
     * 
     * @return true if full-screen magnification is enabled, false otherwise.
     */
    public static boolean isFullscreenEnabled() {
        return NbPreferences.forModule(MagnificationOptions.class).getBoolean(FULLSCREEN_ENABLED, true);
    }
    
    /**
     * Set whether or not full screen mode is enabled. It is enabled by default.
     * 
     * @param enabled 
     */
    public static void setFullScreenEnabled(boolean enabled) {
        NbPreferences.forModule(MagnificationOptions.class).putBoolean(FULLSCREEN_ENABLED, enabled);
    }
    
    /**
     * Returns the set width of the magnifier (for use in windowed mode).
     * @return 
     */
    public static int getMagnifierWidth() {
        return NbPreferences.forModule(MagnificationOptions.class).getInt(MAGNIFIER_WIDTH, 520);
    }
    
    /**
     * Set the width of the magnifier window, in pixels. Applies only to windowed mode.
     * @param width 
     */
    public static void setMagnifierWidth(int width) {
        NbPreferences.forModule(MagnificationOptions.class).putInt(MAGNIFIER_WIDTH, width);
    }
    
    /**
     * Returns the set height of the magnifier (for use in windowed mode).
     * @return 
     */
    public static int getMagnifierHeight() {
        return NbPreferences.forModule(MagnificationOptions.class).getInt(MAGNIFIER_HEIGHT, 278);
    }
    
    /**
     * Set the height of the magnifier window, in pixels. Applies only to windowed mode.
     * 
     * @param height 
     */
    public static void setMagnifierHeight(int height) {
        NbPreferences.forModule(MagnificationOptions.class).putInt(MAGNIFIER_HEIGHT, height);
    }
    
    /**
     * Get the current zoom level of the magnifier. Default is 2x normal size.
     * 
     * @return 
     */
    public static float getZoomLevel() {
        return NbPreferences.forModule(MagnificationOptions.class).getFloat(ZOOM_LEVEL, 2.0f);
    }
    
    /**
     * Set the magnifier's zoom level.
     * 
     * @param zoomLevel the zoom level to set (2.0 is default)
     */
    public static void setZoomLevel(float zoomLevel) {
        NbPreferences.forModule(MagnificationOptions.class).putFloat(ZOOM_LEVEL, zoomLevel);
    }
}
