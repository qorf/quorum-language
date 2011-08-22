/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents a Plugin system for the virtual machine. The plugin
 * manager accepts implemented plugins.
 * 
 * @author Andreas Stefik
 */
public class PluginManager {
    private HashMap<String, Plugin> plugins = new HashMap<String, Plugin>();
    private HashMap<String, Plugin> pluginsDebugMode = new HashMap<String, Plugin>();
    private HashMap<String, Plugin> currentPlugins;
    private boolean isDebugMode = false;

    public PluginManager() {
        currentPlugins = plugins;
        isDebugMode = false;
    }

    /**
     * Adds a plugin onto the system. If a plugin with the same key already
     * exists, this call will return false. All plugins must have a unique key.
     * 
     * @param plugin
     * @return
     */
    public boolean add(Plugin plugin) {
        String key = plugin.getKey();        
        if(plugin.isExecutePlugin() && plugins.containsKey(key)){
            return false;
        } else if(plugin.isExecutePlugin() && !plugins.containsKey(key)) {
            plugins.put(key, plugin);
        }

        if(plugin.isDebugPlugin() && pluginsDebugMode.containsKey(key)) {
            return false;
        }
        else if(plugin.isDebugPlugin() && !pluginsDebugMode.containsKey(key)) {
            pluginsDebugMode.put(key, plugin);
        }
        
        return true;
    }

    /** Returns a plugin according to the appropriate mode. For example,
     * if the user is debugging, a plugin will be returned from the
     * container of debug plugins.
     * 
     * @param key
     * @return
     */
    public Plugin get(String key) {
        Plugin get = currentPlugins.get(key);
        return get;
    }

    public Iterator<String> getKeyIterator() {
        return plugins.keySet().iterator();
    }

    public Iterator<Plugin> getPluginIterator() {
        return plugins.values().iterator();
    }

    public boolean hasPlugin(String key) {
        return plugins.containsKey(key);
    }

    /**
     * @return the isDebugMode
     */
    public boolean isInDebugMode() {
        return isDebugMode;
    }

    /**
     * Set this value to true if the plugin manager should be in debug mode.
     * 
     * @param isDebugMode the isDebugMode to set
     */
    public void setDebugMode(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
        if(isDebugMode) {
            currentPlugins = pluginsDebugMode;
        }
        else {
            currentPlugins = plugins;
        }
    }

    /**
     * This method checks the consistency of the plugins currently loaded on 
     * the system, to ensure that the system can be both debugged and executed.
     * @return
     */
    public boolean check() {
        if(plugins.size() != pluginsDebugMode.size()) {
            return false;
        }
        //check to see that they have all the same keys
        Iterator<Plugin> iterator = plugins.values().iterator();
        while(iterator.hasNext()) {
            Plugin plug = iterator.next();
            if(!pluginsDebugMode.containsKey(plug.getKey())) {
                return false;
            }
        }
        //the plugin models have the same size and all of the keys match. Hooray!
        return true;
    }
    /**
     * Resets all of the plugins on the system to their default, initialized
     * values.
     */
    public void clear() {
        //these iterators are required to have the same size
        Iterator<Plugin> execit = plugins.values().iterator();
        Iterator<Plugin> debugit = pluginsDebugMode.values().iterator();
        while(execit.hasNext()) {
            Plugin plug = execit.next();
            plug.reset();
            plug = debugit.next();
            plug.reset();
        }
    }
}
