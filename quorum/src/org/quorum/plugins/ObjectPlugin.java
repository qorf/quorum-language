/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;

/**
 *
 * @author stefika
 */
public class ObjectPlugin implements Plugin{
    public static final String KEY = TypeDescriptor.OBJECT;
    public static final String GET_HASH_CODE = "GetHashCode";

    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        if(call.getActionName().equals(GET_HASH_CODE)) {
            int hash = call.getCallingObject().getHashKey();
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            value.getResult().integer = hash;
            ret.setReturnValue(value);
        }

        return ret;
    }

    public PluginReturn unexecute(PluginCall call) {
        return null;
    }

    public boolean isValidCall(PluginCall call) {
        if(call.getActionName().equals(GET_HASH_CODE)) {
            return true;
        }
        return false;
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
        return "Object Plugin";
    }

    public int getVersion() {
        return 1;
    }

    public String getKey() {
        return KEY;
    }

    public void reset() {
    }

}
