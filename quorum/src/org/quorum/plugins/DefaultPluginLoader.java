/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quorum.vm.interfaces.AbstractVirtualMachine;
import org.quorum.vm.interfaces.Plugin;
import org.quorum.vm.interfaces.PluginManager;

/**
 * A centralized location for loading plugins on the system. This class handles
 * loading of static plugins (e.g., Libraries.Language.Object), dynamic plugins, and
 * any native DLL/SO files that must be loaded.
 * 
 * @author Andreas Stefik
 */
public class DefaultPluginLoader {
    private ArrayList<Plugin> plugins = new ArrayList<Plugin>();
    private PluginLoadingResult loadResult;
    private static final Logger logger = Logger.getLogger(DefaultPluginLoader.class.getName());
    private File dynamicPluginDirectory;
    private File dynamicNativeDirectory;

    public DefaultPluginLoader() {
        load();
    }

    private void load() {
        //reset the loading result
        loadResult = new PluginLoadingResult();
        
        Plugin plugin = new MusicPlugin();
        plugins.add(plugin);

        //add the math plugin
        plugin = new MathPlugin();
        plugins.add(plugin);

        plugin = new ArrayPlugin();
        plugins.add(plugin);

        plugin = new ArrayDebugPlugin();
        plugins.add(plugin);

        plugin = new TextPlugin();
        plugins.add(plugin);

        plugin = new ObjectPlugin();
        plugins.add(plugin);

        plugin = new FilePlugin();
        plugins.add(plugin);

        plugin = new FileDebugPlugin();
        plugins.add(plugin);

        plugin = new FileReaderPlugin();
        plugins.add(plugin);
        
        plugin = new FileReaderDebugPlugin();
        plugins.add(plugin);
        
        plugin = new FileWriterPlugin();
        plugins.add(plugin);
        
        plugin = new FileWriterDebugPlugin();
        plugins.add(plugin);
        
        plugin = new FileRandomAccessPlugin();
        plugins.add(plugin);
        
        plugin = new FileRandomAccessDebugPlugin();
        plugins.add(plugin);
        
        plugin = new ConsolePlugin();
        plugins.add(plugin);

        plugin = new ErrorPlugin();
        plugins.add(plugin);

        plugin = new RandomPlugin();
        plugins.add(plugin);

        plugin = new DateTimePlugin();
        plugins.add(plugin);
        
        //add the speech plugin
        plugin = new SpeechPlugin();
        plugins.add(plugin);

        loadDynamicLibraries();
    }

    /**
     * Load dynamic libraries on the system. TODO: Implement this.
     */
    private void loadDynamicLibraries() {
        
    }

    /**
     * Loads a set of known plugins into a virtual machine.
     *
     * @param machine
     */
    public void loadIntoVirtualMachine(AbstractVirtualMachine machine) {
        PluginManager manager = machine.getPluginManager();
        Iterator<Plugin> plugs = plugins.iterator();
        while(plugs.hasNext()) {
            manager.add(plugs.next());
        }
    }

    /**
     * Returns an iterator of all plugins that have been loaded onto the system.
     * 
     * @return
     */
    public Iterator<Plugin> getLoadedPlugins() {
        return plugins.iterator();
    }

    /**
     * Returns an object detailing the result of loading plugins on the system.
     * If this method returns a failing result, the problem is output to the
     * standard log file.
     * 
     * @param machine
     * @return
     */
    public PluginLoadingResult checkConsistency(AbstractVirtualMachine machine) {
        PluginManager manager = machine.getPluginManager();
        boolean passed = manager.check();
        loadResult.passed = passed;
        if(loadResult != null && !loadResult.isPassed()) {
            logger.log(Level.INFO, "Quorum Plugin consistency check failed. Check that all plugins"
                    + " loaded on the system are provided both for debugging and execution.");
        }
        return loadResult;
    }
}
