/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.util.HashMap;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.vm.interfaces.Plugin;

/**
 * This plugin represents a native implementation of arrays on the system.
 * 
 * @author Andreas Stefik
 */
public class AudioPlugin implements Plugin {
    public static final String KEY = "Libraries.Sound.Audio";
    public static final String PLAY_NATIVE = "PlayNative:text:boolean";
    public static final String STOP_PLAYING_NATIVE = "StopPlayingNative";
    public static final String RECORD_NATIVE = "RecordNative:text";
    public static final String STOP_RECORDING_NATIVE = "StopRecordingNative";
    
    protected HashMap<Integer, Audio> instances;
    protected HashMap<String, Boolean> validCalls;

    public AudioPlugin() {
        instances = new HashMap<Integer, Audio>();
        
    }

    @Override
    public boolean canDebugBackward() {
        return false;
    }

    @Override
    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        String action = call.getActionName();

        if(!isValidCall(call)) {
            return ret;
        }

        RuntimeObject ro = call.getCallingObject();
        Audio audio = instances.get(ro.getHashKey());

        if(audio == null) { //the runtime object is in the heap, but the native array
                            //has not been initialized
            Audio newAudio = new Audio();
            instances.put(ro.getHashKey(), newAudio);
            audio = newAudio;
        }

        if(action.equals(PLAY_NATIVE)) {
            ExpressionValue path = call.getArgument("path");
            ExpressionValue block = call.getArgument("block");
            audio.PlayNative(path.getResult().text, block.getResult().boolean_value);
        }
        else if(action.equals(STOP_PLAYING_NATIVE)) {
            audio.StopPlayingNative();
        }
        else if(action.equals(RECORD_NATIVE)) {
            ExpressionValue path = call.getArgument("path");
            audio.RecordNative(path.getResult().text);
        }
        else if (action.equals(STOP_RECORDING_NATIVE)) {
            audio.StopRecordingNative();
        }
        return ret;
    }

    @Override
    public boolean isValidCall(PluginCall call) {
        return true;
    }

    @Override
    public String getName() {
        return "Audio";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    public PluginReturn unexecute(PluginCall call) {
        return null;
    }

    public boolean isDebugPlugin() {
        return true;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public void reset() {
        instances = new HashMap<Integer, Audio>();
    }
}
