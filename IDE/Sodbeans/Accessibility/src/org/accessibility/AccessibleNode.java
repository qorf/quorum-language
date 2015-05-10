/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author stefika
 */
public abstract class AccessibleNode extends AbstractNode{
    public AccessibleNode(Children children){
        super(children);
    }

    public abstract ScreenReaderInformation getScreenReaderInformation();
}
