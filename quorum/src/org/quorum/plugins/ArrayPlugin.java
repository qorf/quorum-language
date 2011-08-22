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

/**
 * This plugin represents a native implementation of arrays on the system.
 * 
 * @author Andreas Stefik
 */
public class ArrayPlugin implements ArrayPluginInterface {
    public static final String KEY = "Libraries.Containers.Array";
    public static final String SET_NATIVE = "SetNative:integer:" + TypeDescriptor.OBJECT;
    public static final String GET_NATIVE = "GetNative:integer";
    public static final String SET_SIZE_NATIVE = "SetSizeNative:integer";
    public static final String GET_SIZE = "GetSize";
    public static final String SET_RESIZABLE = "SetAutoResize:boolean";
    public static final String IS_RESIZABLE = "GetAutoResize";
    public static final String GET_MAX_SIZE = "GetMaxSize";
    public static final String ADD_AT_NATIVE = "AddNative:integer:" + TypeDescriptor.OBJECT;
    public static final String ADD_NATIVE = "AddNative:" + TypeDescriptor.OBJECT;
    public static final String IS_EMPTY = "IsEmpty";
    public static final String EMPTY = "Empty";
    public static final String REMOVE_AT = "RemoveAtNative:integer";
    public static final String SET_MAX_SIZE = "SetMaxSize:integer";
    protected HashMap<Integer, ArrayInterface> arrays;
    protected HashMap<String, Boolean> validCalls;

    public ArrayPlugin() {
        arrays = new HashMap<Integer, ArrayInterface>();
        validCalls = new HashMap<String, Boolean>();
        validCalls.put(SET_NATIVE, Boolean.TRUE);
        validCalls.put(GET_NATIVE, Boolean.TRUE);
        validCalls.put(SET_SIZE_NATIVE, Boolean.TRUE);
        validCalls.put(GET_SIZE, Boolean.TRUE);
        validCalls.put(SET_RESIZABLE, Boolean.TRUE);
        validCalls.put(IS_RESIZABLE, Boolean.TRUE);
        validCalls.put(GET_MAX_SIZE, Boolean.TRUE);
        validCalls.put(SET_MAX_SIZE, Boolean.TRUE);
        validCalls.put(ADD_AT_NATIVE, Boolean.TRUE);
        validCalls.put(ADD_NATIVE, Boolean.TRUE);
        validCalls.put(IS_EMPTY, Boolean.TRUE);
        validCalls.put(EMPTY, Boolean.TRUE);
        validCalls.put(REMOVE_AT, Boolean.TRUE);
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
        ArrayInterface array = arrays.get(ro.getHashKey());

        if(array == null) { //the runtime object is in the heap, but the native array
                            //has not been initialized
            Array newArray = new Array();
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

    public ArrayInterface getArray(Integer hash) {
        if(this.arrays.containsKey(hash)) {
            return arrays.get(hash);
        }
        return null;
    }

    @Override
    public boolean isValidCall(PluginCall call) {
        String action = call.getActionName();
        if(validCalls.containsKey(action)) {
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return "Array";
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
        return false;
    }

    public boolean isExecutePlugin() {
        return true;
    }

    public void reset() {
        arrays = new HashMap<Integer, ArrayInterface>();
    }
}
