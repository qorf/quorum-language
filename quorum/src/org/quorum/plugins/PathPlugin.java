/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

import java.util.HashMap;
import org.quorum.execution.ExecutionStep;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.ErrorTypeDescriptor;
import org.quorum.symbols.Structure;
import org.quorum.symbols.TypeDescriptor;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginReturn;

/**
 *
 * @author jeff
 */
class PathPlugin implements Plugin {
    protected AbstractVirtualMachine vm;
    public static final String KEY = "Libraries.System.Path";
    
    public static final String IS_PATH_ABSOLUTE_NATIVE = "IsPathAbsoluteNative:text";
    public static final String FIX_PATH_SEPARATORS_NATIVE = "FixPathSeparatorsNative:text";
    
    public PathPlugin() {
    }

    public PluginReturn debug(PluginCall call) {
        //return execute(call);
        return null;
    }

    public PluginReturn undebug(PluginCall call) {
        return null;
    }

    public boolean canDebugBackward() {
        return false;
    }

    public boolean isValidCall(PluginCall call) {
        return false;
    }

    public String getName() {
        return "File";
    }

    public int getVersion() {
        return 1;
    }

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
    }

    public PluginReturn execute(PluginCall call) {
        String action = call.getActionName();
        PluginReturn ret = new PluginReturn();
        
        vm = call.getVirtualMachine();
        
        if (action.equals(IS_PATH_ABSOLUTE_NATIVE)) {
            String path = call.getArgument("path").getResult().text;
            boolean result = QuorumPath.IsPathAbsoluteNative(path);
            setPluginReturnValue(ret, result);
        }
        else if (action.equals(FIX_PATH_SEPARATORS_NATIVE)) {
            String path = call.getArgument("path").getResult().text;
            String newPath = QuorumPath.FixPathSeparatorsNative(path);
            setPluginReturnValue(ret, newPath);
        }
        return ret;
    }

    protected void setPluginReturnValue(PluginReturn ret, int num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().integer = num;
        ret.setReturnValue(value);
    }
    
    protected void setPluginReturnValue(PluginReturn ret, double num) {
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        value.getResult().number = num;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, String str){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().text = str;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, boolean bool){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        value.getResult().boolean_value = bool;
        ret.setReturnValue(value);
    }

    protected void setPluginReturnValue(PluginReturn ret, Structure obj){
        ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        value.getResult().structure = obj;
        ret.setReturnValue(value);
    }

    protected void setExpressionValue(ExpressionValue exp, String str){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
        exp.getResult().text = str;
    }

    protected void setExpressionValue(ExpressionValue exp, double num){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getNumberType());
        exp.getResult().number = num;
    }

    protected void setExpressionValue(ExpressionValue exp, Boolean bool){
        exp = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        exp.getResult().boolean_value = bool;
    }
    
    protected void throwQuorumException(String message, ErrorTypeDescriptor e) {
        //This will throw the exception to the virtual machine
        ExecutionStep currentStep = vm.getExecution().getCurrentStep();
        currentStep.setRuntimeError(new RuntimeError(message, e, vm));
        currentStep.setCallStateBeforeException(vm.getDataEnvironment().callStackPeek());
        currentStep.setIsInErrorState(vm.throwException(currentStep.getRuntimeError()));
        currentStep.setWasInErrorState(true);
    }
}
