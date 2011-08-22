/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

/**
 * This interface represents a plugin on the system. This plugin can come from
 * a wide variety of sources, including internal Sodbeans classes, dynamically
 * loaded JAR files, or other features. Plugins should be loaded externally
 * (not by the virtual machine), and then sent to the virtual machine to
 * be tracked. This helps with portability, allowing reuse of the plugin classes
 * without any dependencies on the NetBeans platform.
 * 
 * @author Andreas Stefik
 */
public interface Plugin {
    
    /**
     * Executes the plugin for a particular action. Arguments for an action
     * on the Quorum side are available from the the plugin call as a Hash, indexed
     * by the name of the argument. Actions themselves are referenced by their
     * full name (e.g., system action Say(text value) would be indexed by Say:text).
     *
     * @param call
     * @return
     */
    public PluginReturn execute(PluginCall call);

    /**
     *
     * @param call
     * @return
     */
    public PluginReturn unexecute(PluginCall call);

    /**
     * Determines whether a particular action is supported by this plugin.
     *
     * @param call returns true if a call of this type is supported.
     * 
     * @return
     */
    public boolean isValidCall(PluginCall call);

    /**
     * This variable determines whether the author has built in support
     * for backward debugging into this native library. Libraries where it does
     * not mean anything to backward debug (e.g., a Math Library), should
     * return true and simply ignore the backward debugging calls.
     * 
     * @return
     */
    public boolean canDebugBackward();

    /**
     * This method returns true if this plugin should only be used for
     * debugging.
     *
     * @return
     */
    public boolean isDebugPlugin();

    /**
     * This method should return true if this plugin should only be used
     * for debugging (not executing at full speed).
     * 
     * @return
     */
    public boolean isExecutePlugin();

    /**
     * Gets the name of the plugin, to be displayed to the user.
     * 
     * @return
     */
    public String getName();

    /**
     * Gets the version number of the plugin.
     * 
     * @return
     */
    public int getVersion();

    /**
     * Gets a key representing the plugin. This key must be unique and must
     * match the fully qualified name of a Quorum class. For example, the key
     * for the Speech class must be "Libraries.System.Speech". If the key
     * does not match the name of a Quorum class, the plugin will never be
     * executed.
     * 
     * @return
     */
    public String getKey();

    /**
     * Plugins must implement a reset interface. This is used as a reset
     * when new runs of a program occur. For example, a plugin that stores
     * natively a consecutive list of objects in an iterator might reset
     * that iterator on a call to reset. Or, an array implementation that
     * has to store what arrays are active on the system would need
     * to clear this cache out on consecutive runs.
     *
     */
    public void reset();
}
