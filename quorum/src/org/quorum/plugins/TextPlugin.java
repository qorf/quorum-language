/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.util.HashMap;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;

/**
 * This plugin supplements the Text wrapper class in object to give it greater
 * functionality.
 * 
 * @author Andreas Stefik
 */
public class TextPlugin implements Plugin {
    public static final String KEY = TypeDescriptor.TEXT_OBJECT;
    private static final String SET_VALUE_NATIVE = "SetValueNative:text";
    private static final String COMPARE_INT = "CompareInt:text:text";
    private static final String GET_CHARACTER_NATIVE = "GetCharacterNative:integer";
    private static final String GET_SUBSTRING_NATIVE = "GetSubstringNative:integer:integer";
    private static final String GET_LENGTH = "GetLength";
    public static final String GET_HASH_CODE = "GetHashCode";
    
    private HashMap<Integer, QuorumText> instances;

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        QuorumText inst = instances.get(call.getCallingObject().getHashKey());
        
        if (inst == null) {
            // This instance hasn't been logged yet. Put it in.
            inst = new QuorumText();
            instances.put(call.getCallingObject().getHashKey(), inst);
        }
        
        if (call.getActionName().equals(SET_VALUE_NATIVE)) {
            // Update our copy of the value.
            String value = call.getArgument("value").getResult().text;
            inst.setValue(value);
        }
        else if (call.getActionName().equals(COMPARE_INT)) {
            ExpressionValue le = call.getArgument("left");
            ExpressionValue re = call.getArgument("right");

            String left = le.getResult().text;
            String right = re.getResult().text;
            int compareTo = left.compareTo(right);
            ExpressionValue result = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            result.getResult().integer = compareTo;
            ret.setReturnValue(result);
        }
        else if (call.getActionName().equals(GET_CHARACTER_NATIVE)) {
            int index = call.getArgument("index").getResult().integer;
            
            setPluginReturnValue(ret, inst.getCharacter(index));
        }
        else if (call.getActionName().equals(GET_SUBSTRING_NATIVE)) {
            int start = call.getArgument("startIndex").getResult().integer;
            int end = call.getArgument("endIndex").getResult().integer;
            
            setPluginReturnValue(ret, inst.getSubstring(start, end));
        }
        else if (call.getActionName().equals(GET_LENGTH)) {
            setPluginReturnValue(ret, inst.getLength());
        }
        else if(call.getActionName().equals(GET_HASH_CODE)) {
            ExpressionValue hash = call.getCallingObject().getVariable("value");
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            String res = hash.getResult().text;
            if(res != null) {
                value.getResult().integer = res.hashCode();
            }
            else {
                value.getResult().integer = call.getCallingObject().getHashKey();
            }
            
            ret.setReturnValue(value);
        }
        return ret;
    }

    public PluginReturn unexecute(PluginCall call) {
        return null; //nothing needs be done at this point.
    }

    public boolean isValidCall(PluginCall call) {
        if(call.getActionName().equals(COMPARE_INT)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean canDebugBackward() {
        return true;
    }

    public boolean isDebugPlugin() {
        return true;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public String getName() {
        return "Text Plugin";
    }

    public int getVersion() {
        return 1;
    }

    public String getKey() {
        return KEY;
    }

    public void reset() {
        instances = new HashMap<Integer, QuorumText>();
    }
    
    private void setPluginReturnValue(PluginReturn ret, int num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().integer = num;
        ret.setReturnValue(value);
    }

    private void setPluginReturnValue(PluginReturn ret, String str){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().text = str;
        ret.setReturnValue(value);
    }
}
