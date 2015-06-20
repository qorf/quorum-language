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
    public java.lang.Object me_ = null;
    
    public void SetPropertyNative(String key, String value) {
        System.setProperty(key, value);
    }
    
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
    
    public String GetEnvironmentVariableNative(String key) {
        String env = System.getenv(key);
        return env;
    }
    
    public boolean HasEnvironmentVariableNative(String key) {
        String property = System.getenv(key);
        if (property == null) {
            return false;
        } else {
            return true;
        }
    }
}
