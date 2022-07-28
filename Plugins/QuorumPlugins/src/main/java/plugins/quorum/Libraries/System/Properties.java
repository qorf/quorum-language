/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import quorum.Libraries.Language.Types.Text;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.System.File;
import quorum.Libraries.System.File_;
import quorum.Libraries.Containers.HashTable_;
import quorum.Libraries.Containers.HashTable;

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

    public HashTable_ GetPropertiesNative() {
        HashTable_ table = new HashTable();
        java.util.Properties properties = System.getProperties();

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            Text_ quorumKey = new Text();
            Text_ quorumValue = new Text();

            quorumKey.SetValue(key);
            quorumValue.SetValue(value);
            table.Add(quorumKey, quorumValue);
        }

        return table;
    }

    public HashTable_ GetEnvironmentVariablesNative() {
        HashTable_ table = new HashTable();
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            String value = env.get(key);
            Text_ quorumKey = new Text();
            Text_ quorumValue = new Text();

            quorumKey.SetValue(key);
            quorumValue.SetValue(value);

            table.Add(quorumKey, quorumValue);
        }

        return table;
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
