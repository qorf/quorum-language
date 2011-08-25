/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.execution.ExecutionStepVisitor;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginManager;
import org.quorum.execution.ExpressionValue;
import org.quorum.symbols.TypeDescriptor;

/**
 * A Native wrapper for sending speech to the text-to-speech engine. This opcode
 * functions regardless of platform or screen reader architecture.
 * 
 * @author Andreas Stefik
 */
public class SpeakStep extends NativeLibraryStep{
    private PluginCall call = new PluginCall();
    private static final String SPEAK = "Say:text";
    private static final String SPEAK_BLOCK = "Say:text:boolean";
    public static final String PLUGIN_KEY = "Libraries.Sound.Speech";

    @Override
    public void execute() {
        PluginManager plugins = this.vm.getPluginManager();
        Plugin plug = plugins.get(PLUGIN_KEY);
        String callString = "";
        boolean block = false;
        if(this.vm.isRunning()) {
            callString = SPEAK_BLOCK;
            block = true;
        }
        else {
            callString = SPEAK;
        }
        call.setActionName(callString);
        call.setCallingObject(this.vm.getDataEnvironment().getThisObject());
        call.setVirtualMachine(vm);
        ExpressionValue value = vm.getDataEnvironment().getRegister(getParameterRegister());
        ExpressionValue copy = new ExpressionValue(value);
        call.addArgument("value", copy);

        value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getBooleanType());
        value.getResult().boolean_value = block;
        copy = new ExpressionValue(value);
        call.addArgument("block", copy);

        //finally, make the plugin call
        plug.execute(call);
    }

    @Override
    public void unexecute() {//nothing needs to be done
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.SPEAK_STEP.getName();
    }
    
    @Override
    public void visit(ExecutionStepVisitor visitor) {
        visitor.visit(this);
    }
}
