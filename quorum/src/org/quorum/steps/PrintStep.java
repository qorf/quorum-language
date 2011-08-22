/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.steps;

import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginCall;
import org.quorum.vm.interfaces.PluginManager;
import org.quorum.execution.ExpressionValue;


/**
 * A native library wrapper for printing to the console.
 * 
 * @author Andreas Stefik
 */
public class PrintStep extends NativeLibraryStep{

    public static final String PLUGIN_KEY = "Libraries.System.Console";
    public static final String PRINT_TEXT = "Print:text";
    private PluginCall call = new PluginCall();
    String parameter = "";

    @Override
    public void execute() {
        PluginManager plugins = this.vm.getPluginManager();
        Plugin plug = plugins.get(PLUGIN_KEY);
        call.setActionName(PRINT_TEXT);
        call.setCallingObject(this.vm.getDataEnvironment().getThisObject());
        call.setVirtualMachine(vm);
        ExpressionValue value = vm.getDataEnvironment().getRegister(getParameterRegister());
        ExpressionValue copy = new ExpressionValue(value);
        call.addArgument("value", copy);
        
        parameter = value.getResult().text;

        //finally, make the plugin call
        plug.execute(call);
    }

    @Override
    public void unexecute() {
        PluginManager plugins = this.vm.getPluginManager();
        Plugin plug = plugins.get(PLUGIN_KEY);

        plug.unexecute(call); //no extra processing is required.
    }

    @Override
    public String getStaticKey() {
        return IntermediateConstants.PRINT_STEP.getName();
    }

    /**
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }
}
