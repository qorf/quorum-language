/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginManager;
import org.quorum.vm.interfaces.PluginReturn;
import org.quorum.execution.ExpressionValue;

/**
 *
 * @author Andreas Stefik
 */
public class InputStep extends NativeLibraryStep{
    private PluginCall call = new PluginCall();
    public static final String INPUT_TEXT = "Input:text";
    public static final String PLUGIN_KEY = "Libraries.System.Console";

    @Override
    public void execute() {
        PluginManager plugins = this.vm.getPluginManager();
        Plugin plug = plugins.get(PLUGIN_KEY);
        call.setActionName(INPUT_TEXT);
        call.setCallingObject(this.vm.getDataEnvironment().getThisObject());
        call.setVirtualMachine(vm);
        ExpressionValue value = vm.getDataEnvironment().getRegister(getParameterRegister());
        ExpressionValue copy = new ExpressionValue(value);
        call.addArgument("message", copy);
        PluginReturn ret = plug.execute(call);

        ExpressionValue result = ret.getReturnValue();
        result = new ExpressionValue(result); //make a copy
        this.vm.getDataEnvironment().setRegister(this.getResultRegister(), result);
    }

    @Override
    public void unexecute() {
        this.vm.getDataEnvironment().popRegister(this.getResultRegister());
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.INPUT_STEP.getName();
    }
}
