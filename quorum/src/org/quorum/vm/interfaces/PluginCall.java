/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.util.HashMap;
import org.quorum.execution.ExpressionValue;
import org.quorum.execution.RuntimeObject;

/**
 * This class represents a set of parameters and state data regarding the current
 * call. From this class, you can object any objects that are being passed
 * to the method and the current state of the This pointer on the system.
 * @author Andreas Stefik
 */
public class PluginCall {
    private RuntimeObject This;
    private HashMap<String, ExpressionValue> arguments =
            new HashMap<String, ExpressionValue>();
    private String pluginName;
    private String actionName;
    private AbstractVirtualMachine virtualMachine;

    /**
     * Adds a new argument to the plugin call. This should only be called
     * by the object setting up the PluginCall.
     *
     * @param key
     * @param argument
     */
    public void addArgument(String key, ExpressionValue argument) {
        arguments.put(key, argument);
    }

    /**
     * Returns an argument with the name key. For example, given the
     * method system action Set(integer index, Type object), the parameters
     * would be named index and object and can be queried as such.
     *
     * @param key
     * @return
     */
    public ExpressionValue getArgument(String key) {
        return arguments.get(key);
    }

    /**
     * Returns the number of arguments in the call.
     *
     * @return
     */
    public int getNumberArguments() {
        return arguments.size();
    }
    /**
     * Returns the name of the plugin.
     *
     * @return the pluginName
     */
    public String getPluginName() {
        return pluginName;
    }

    /**
     * Returns the equivalent of the "This" pointer currently in the virtual
     * machine.
     * 
     * @return
     */
    public RuntimeObject getCallingObject() {
        return This;
    }

    /**
     * Sets the This pointer for this call. This method does not actually
     * set the This pointer in the virtual machine. It should only be
     * called by the object that is setting up the the PluginCall object.
     * @param object
     */
    public void setCallingObject(RuntimeObject object) {
        this.This = object;
    }

    /**
     * @param pluginName the pluginName to set
     */
    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    /**
     * Return the full name of the calling action. For example,
     * system action Set(integer index, Type object) would be
     * "Set:integer:Libraries.Language.Object".
     * 
     * @return the actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the full name of the calling action. For example,
     * system action Set(integer index, Type object) would be
     * "Set:integer:Libraries.Language.Object". This should only
     * be called by the object setting up the PluginCall.
     * 
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * @return the virtualMachine
     */
    public AbstractVirtualMachine getVirtualMachine() {
        return virtualMachine;
    }

    /**
     * @param virtualMachine the virtualMachine to set
     */
    public void setVirtualMachine(AbstractVirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine;
    }
}
