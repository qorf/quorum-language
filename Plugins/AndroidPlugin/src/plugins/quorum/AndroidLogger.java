/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum;

import android.util.Log;

/**
 *
 * @author Merlin
 */
public class AndroidLogger {
    public Object me_;
    
    String defaultTag = "Default Logger";
    
    public void Log(String message) {
        Log.d(defaultTag, message);
    }
    
    public void Log(String message, String tag) {
        Log.d(tag, message);
    }
}
