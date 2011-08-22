/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.plugins;

import org.quorum.vm.interfaces.Plugin;

/**
 *
 * @author Andreas Stefik
 */
public interface ArrayPluginInterface extends Plugin{
    public ArrayInterface getArray(Integer hash);
}
