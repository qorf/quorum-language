/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

/**
 *
 * @author astefik
 */
public class Properties {
    private String GetPropertyNative(String key) {
        String property = System.getProperty(key);
        return property;
    }
    private boolean HasPropertyNative(String key) {
        String property = System.getProperty(key);
        if (property == null) {
            return false;
        } else {
            return true;
        }
    }
}
