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
    public java.lang.Object $me = null;
    public String GetPropertyNative(String key) {
        String property = System.getProperty(key);
        return property;
    }
    public boolean HasPropertyNative(String key) {
        String property = System.getProperty(key);
        if (property == null) {
            return false;
        } else {
            return true;
        }
    }
}
