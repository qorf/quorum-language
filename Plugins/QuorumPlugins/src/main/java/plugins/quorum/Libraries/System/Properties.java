/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.System.File;
import quorum.Libraries.System.File_;

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
    
    public File_ GetRunLocation() {
        try {
            java.io.File file = new java.io.File(Properties.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            File quorumFile = new File();
            quorumFile.SetWorkingDirectory(file.getParent());
            quorumFile.SetPath(file.getName());
            return quorumFile;
           
        } catch (URISyntaxException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
