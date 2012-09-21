/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;

/**
 *
 * @author astefik
 */
public class BitwiseOperationsPlugin implements Plugin{

    public static final String KEY = "Libraries.Compute.BitwiseOperations";
    public static final String AND_INTEGER_INTEGER = "And:integer:integer";
    public static final String OR_INTEGER_INTEGER = "Or:integer:integer";
    public static final String EXCLUSIVE_OR_INTEGER_INTEGER = "ExclusiveOr:integer:integer";
    public static final String Negate = "Negate:integer";
    public static final String SHIFT_LEFT_INTEGER_INTEGER = "ShiftLeft:integer:integer";
    public static final String SHIFT_RIGHT_KEEP_SIGN_INTEGER_INTEGER = "ShiftRightKeepSign:integer:integer";
    public static final String SHIFT_RIGHT_INTEGER_INTEGER = "ShiftRight:integer:integer";
    
    @Override
    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        if(call.getActionName().equals(AND_INTEGER_INTEGER)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("left").getResult().integer;
            int right = call.getArgument("right").getResult().integer;
            value.getResult().integer = left & right;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(OR_INTEGER_INTEGER)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("left").getResult().integer;
            int right = call.getArgument("right").getResult().integer;
            value.getResult().integer = left | right;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(EXCLUSIVE_OR_INTEGER_INTEGER)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("left").getResult().integer;
            int right = call.getArgument("right").getResult().integer;
            value.getResult().integer = left ^ right;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(Negate)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("value").getResult().integer;
            value.getResult().integer = ~left;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(SHIFT_LEFT_INTEGER_INTEGER)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("value").getResult().integer;
            int right = call.getArgument("amount").getResult().integer;
            value.getResult().integer = left << right;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(SHIFT_RIGHT_INTEGER_INTEGER)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("value").getResult().integer;
            int right = call.getArgument("amount").getResult().integer;
            value.getResult().integer = left >> right;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(SHIFT_RIGHT_KEEP_SIGN_INTEGER_INTEGER)) {
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            int left = call.getArgument("value").getResult().integer;
            int right = call.getArgument("amount").getResult().integer;
            value.getResult().integer = left >>> right;
            ret.setReturnValue(value);
        }
        
        return ret;
    }

    @Override
    public PluginReturn unexecute(PluginCall call) {
        return null;
    }

    @Override
    public boolean isValidCall(PluginCall call) {
        return true;
    }

    @Override
    public boolean canDebugBackward() {
        return true;
    }

    @Override
    public boolean isDebugPlugin() {
        return true;
    }

    @Override
    public boolean isExecutePlugin() {
        return true;
    }

    @Override
    public String getName() {
        return "Bitwise Operations plugin";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public void reset() {
    }
    
}
