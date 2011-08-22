/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;
import org.quorum.symbols.TypeDescriptor;

/**
 * An array plugin implementation that allows for backward debugging.
 * 
 * @author Andreas Stefik
 */
public class ArrayDebugPlugin extends ArrayPlugin{
    
    @Override
    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        String action = call.getActionName();

        if(!isValidCall(call)) {
            return ret;
        }

        RuntimeObject ro = call.getCallingObject();
        ArrayInterface array = arrays.get(ro.getHashKey());

        if(array == null) { //the runtime object is in the heap, but the native array
                            //has not been initialized
            ArrayInterface newArray = new ArrayDebug();
            arrays.put(ro.getHashKey(), newArray);
            array = newArray;
        }

        if(action.equals(SET_NATIVE)) {
            ExpressionValue object = call.getArgument("value");
            ExpressionValue index = call.getArgument("location");
            array.set(index.getResult().integer, object);
        }
        else if(action.equals(GET_NATIVE)) {
            ExpressionValue index = call.getArgument("location");
            ExpressionValue result = array.get(index.getResult().integer);
            if(result == null) {
                result = ExpressionValue.getObjectDefault(TypeDescriptor.getSystemObject());
            }
            ret.setReturnValue(result);
        }
        else if(action.equals(SET_SIZE_NATIVE)) {
            ExpressionValue size = call.getArgument("size");
            array.setSize(size.getResult().integer);
        }
        else if(action.equals(GET_SIZE)) {
            int size = array.getSize();
            ExpressionValue integer = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            integer.getResult().integer = size;
            ret.setReturnValue(integer);
        }
        else if(action.equals(SET_RESIZABLE)) {
            ExpressionValue resizable = call.getArgument("resizable");
            array.setResizable(resizable.getResult().boolean_value);
        }
        else if(action.equals(IS_RESIZABLE)) {
            boolean resize = array.isResizable();
            ExpressionValue boolVal = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
            boolVal.getResult().boolean_value = resize;
            ret.setReturnValue(boolVal);
        }
        else if(action.equals(GET_MAX_SIZE)) {
            int capacity = array.getMaxSize();
            ExpressionValue integer = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getIntegerType());
            integer.getResult().integer = capacity;
            ret.setReturnValue(integer);
        }
        else if(action.equals(SET_MAX_SIZE)) {
            ExpressionValue size = call.getArgument("size");
            array.setMaxSize(size.getResult().integer);
        }
        else if(action.equals(ADD_AT_NATIVE)) {
            ExpressionValue object = call.getArgument("value");
            ExpressionValue index = call.getArgument("location");
            array.add(index.getResult().integer, object);
        }
        else if(action.equals(ADD_NATIVE)) {
            ExpressionValue object = call.getArgument("value");
            array.add(object);
        }
        else if(action.equals(IS_EMPTY)) {
            boolean empty = array.isEmpty();
            ExpressionValue boolVal = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
            boolVal.getResult().boolean_value = empty;
            ret.setReturnValue(boolVal);
        }
        else if(action.equals(EMPTY)) {
            array.empty();
        }
        else if(action.equals(REMOVE_AT)) {
            ExpressionValue index = call.getArgument("location");
            ExpressionValue result = array.removeAt(index.getResult().integer);
            if(result == null) {
                result = ExpressionValue.getObjectDefault(TypeDescriptor.getSystemObject());
            }
            ret.setReturnValue(result);
        }
        return ret;
    }
    
    @Override
    public boolean canDebugBackward() {
        return true;
    }

    @Override
    public String getName() {
        return "Array Debugger";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public PluginReturn unexecute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        String action = call.getActionName();

        if(!isValidCall(call)) {
            return ret;
        }

        RuntimeObject ro = call.getCallingObject();
        ArrayDebug array = (ArrayDebug) arrays.get(ro.getHashKey());

        if(array == null) { //the runtime object is in the heap, but the native array
                            //has not been initialized
            ArrayDebug newArray = new ArrayDebug();
            arrays.put(ro.getHashKey(), newArray);
            array = newArray;
        }

        if(action.equals(SET_NATIVE)) {
            ExpressionValue object = call.getArgument("value");
            ExpressionValue index = call.getArgument("location");
            array.unset(); //throw away return value
        }
        else if(action.equals(SET_SIZE_NATIVE)) {
            ExpressionValue size = call.getArgument("size");
            array.unsetSize();
        }
        else if(action.equals(SET_MAX_SIZE)) {
            ExpressionValue size = call.getArgument("size");
            array.unSetMaxSize();
        }
        else if(action.equals(SET_RESIZABLE)) {
            ExpressionValue resizable = call.getArgument("resizable");
            array.unSetResizable();
        }
        else if(action.equals(ADD_AT_NATIVE)) {
            ExpressionValue object = call.getArgument("value");
            ExpressionValue index = call.getArgument("location");
            array.unAddAt(); //throw away return value
        }
        else if(action.equals(ADD_NATIVE)) {
            ExpressionValue object = call.getArgument("value");
            array.unAdd();
        }
        else if(action.equals(EMPTY)) {
            array.unEmpty();
        }
        else if(action.equals(REMOVE_AT)) {
            ExpressionValue index = call.getArgument("location");
            array.unRemoveAt();
        }
        return null;
    }
    
    @Override
    public boolean isDebugPlugin() {
        return true;
    }

    @Override
    public boolean isExecutePlugin() {
        return false;
    }

    @Override
    public void reset() {
        super.reset();
    }
}
