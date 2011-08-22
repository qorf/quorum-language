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
 * A plugin for outputting to a console. Users that want to print to either
 * a specialized console, like in Sodbeans, or a custom tool, must implement
 * the org.quorum.plugins.Console interface and pass that implementation
 * to this plugin.
 * 
 * @author Andreas Stefik
 */
public class ConsolePlugin implements Plugin{
    public static final String KEY = "Libraries.System.Console";
    public static final String PRINT_TEXT = "Print:text";
    public static final String INPUT = "Input";
    public static final String INPUT_TEXT = "Input:text";

    private static Console console;
    
    public ConsolePlugin() {
        if(console == null) {
            console = new StandardInputOutput();
        }
    }

    /**
     * Gets the current console that is being used in this plugin.
     * 
     * @return the console
     */
    public static Console getConsole() {
        return console;
    }

    /**
     * Sets the console that will be used for this plugin. By default, this 
     * is standard in and standard out.
     * 
     * @param aConsole the console to set
     */
    public static void setConsole(Console aConsole) {
        console = aConsole;
    }

    public PluginReturn execute(PluginCall call) {
        PluginReturn ret = new PluginReturn();
        
        if(call.getActionName().equals(PRINT_TEXT)) {
            ExpressionValue value = call.getArgument("value");
            if(value != null){
                getConsole().post(value.getResult().text);
            }
            
        } else if(call.getActionName().equals(INPUT)) {
            String input = getConsole().getInput("");
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
            value.getResult().text = input;
            ret.setReturnValue(value);
        }
        else if(call.getActionName().equals(INPUT_TEXT)) {
            ExpressionValue message = call.getArgument("message");
            String input = "";
            if(message != null){
                String argument = message.getResult().text;
                input = getConsole().getInput(argument);
            }
            ExpressionValue value = ExpressionValue.getPrimitiveDefault(TypeDescriptor.getTextType());
            value.getResult().text = input;
            ret.setReturnValue(value);
        }

        return ret;
    }

    public PluginReturn unexecute(PluginCall call) {

        if(call.getActionName().equals(PRINT_TEXT)) {
            getConsole().unpost();
        } //others require no undo operation
        return null;
    }

    public boolean isValidCall(PluginCall call) {
        if(call.getActionName().equals(PRINT_TEXT) ||
           call.getActionName().equals(INPUT_TEXT) ||
           call.getActionName().equals(INPUT)) {
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
        return "Console Plugin";
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
