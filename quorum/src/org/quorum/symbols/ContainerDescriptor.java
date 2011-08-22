/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 * This class represents the container that a particular class is in. This
 * is similar to a java package, but does not require explicit folders
 * and paths setup to match the package structure.
 *
 * @author Andreas Stefik
 */
public class ContainerDescriptor {
    private String container = "";

    /**
     * @return the container
     */
    public String getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(String container) {
        this.container = container;
    }

}
