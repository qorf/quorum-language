/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;

/**
 * A Plugin that handles speech on the system.
 * 
 * @author Andreas Stefik
 */
public class SpeechPlugin implements Plugin{
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    private static final String CAN_BLOCK = "CanBlock";
    private static final String CAN_PAUSE = "CanPause";
    private static final String CAN_RESUME = "CanResume";
    private static final String CAN_SET_VOICE = "CanSetVoice";
    private static final String CAN_SET_VOLUME = "CanSetVolume";
    private static final String SET_VOLUME = "SetVolume:number";
    private static final String GET_VOLUME = "GetVolume";
    private static final String SET_PITCH = "SetPitch:number";
    private static final String GET_PITCH = "GetPitch";
    private static final String SET_SPEED = "SetSpeed:number";
    private static final String GET_SPEED = "GetSpeed";
    private static final String CAN_SET_SPEED = "CanSetSpeed";

    private static final String SPEAK = "Say:text";
    private static final String SPEAK_BLOCK = "Say:text:boolean";
    public static final String KEY = "Libraries.Sound.Speech";

    public SpeechPlugin() {
        
    }

    @Override
    public boolean canDebugBackward() {
        return true;
    }

    @Override
    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();

        if(action.equals(CAN_BLOCK)) {
            setPluginReturnValue(ret, speech.canBlock());
        }
        else if(action.equals(CAN_PAUSE)) {
            setPluginReturnValue(ret, speech.canPause());
        }
        else if(action.equals(CAN_RESUME)) {
            setPluginReturnValue(ret, speech.canResume());
        }
        else if(action.equals(CAN_SET_VOICE)) {
            setPluginReturnValue(ret, speech.canSetVoice());
        }
        else if(action.equals(CAN_SET_VOLUME)) {
            setPluginReturnValue(ret, speech.canSetVolume());
        }
        else if(action.equals(GET_VOLUME)) {
            setPluginNumberValue(ret, speech.getVolume());
        }
        else if(action.equals(SET_VOLUME)) {
            ExpressionValue argument = call.getArgument("value");
            double volume = argument.getResult().number;
            speech.setVolume(volume);
            setPluginNumberValue(ret, volume);
        }
        else if(action.equals(GET_PITCH)) {
            setPluginNumberValue(ret, speech.getPitch());
        }
        else if(action.equals(SET_PITCH)) {
            ExpressionValue argument = call.getArgument("value");
            double volume = argument.getResult().number;
            speech.setPitch(volume);
            setPluginNumberValue(ret, volume);
        }
        else if(action.equals(GET_SPEED)) {
            setPluginNumberValue(ret, speech.getSpeed());
        }
        else if(action.equals(SET_SPEED)) {
            ExpressionValue argument = call.getArgument("value");
            double volume = argument.getResult().number;
            speech.setSpeed(volume);
            setPluginNumberValue(ret, volume);
        }
        else if(action.equals(CAN_SET_SPEED)) {
            setPluginReturnValue(ret, speech.canSetSpeed());
        }
        else if(action.equals(SPEAK)) {
            ExpressionValue argument = call.getArgument("value");
            String text = argument.getResult().text;
            speech.speak(text, SpeechPriority.HIGHEST);
        }
        else if(action.equals(SPEAK_BLOCK)) {
            ExpressionValue argument = call.getArgument("value");
            ExpressionValue block = call.getArgument("block");

            String text = argument.getResult().text;
            if(!block.getResult().boolean_value) {
                speech.speakBlocking(text, SpeechPriority.HIGHEST);
            }
            else {
                speech.speakBlocking(text);
            }
        }
        return ret;
    }

    private void setPluginNumberValue(PluginReturn ret, double dub) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = dub;
        ret.setReturnValue(value);
    }
    private void setPluginReturnValue(PluginReturn ret, boolean bool) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        value.getResult().boolean_value = bool;
        ret.setReturnValue(value);
    }

    @Override
    public boolean isValidCall(PluginCall call) {
        String action = call.getActionName();
        if(action.equals(CAN_BLOCK)) {
            return true;
        }
        else if(action.equals(CAN_PAUSE)) {
            return true;
        }
        else if(action.equals(CAN_RESUME)) {
            return true;
        }
        else if(action.equals(CAN_SET_VOICE)) {
            return true;
        }
        else if(action.equals(CAN_SET_VOLUME)) {
            return true;
        }
        else if(action.equals(CAN_SET_SPEED)) {
            return true;
        }
        else if(action.equals(SPEAK)) {
            return true;
        }
        else if(action.equals(SPEAK_BLOCK)) {
            return true;
        }

        return false;
    }

    @Override
    public String getName() {
        return "Text-To-Speech";
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
        speech.stop();
    }
}
