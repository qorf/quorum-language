/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.implementation;

import org.quorum.vm.interfaces.LibraryIndexEntry;

/**
 * An extension to LibraryIndexEntry to read in Quorum files.
 * 
 * @author Andreas Stefik
 */
public class QuorumLibraryIndexEntry extends LibraryIndexEntry{

    /**
     * This function takes a line of text from an index file and sets
     * values in this object appropriately.
     *
     * @param line
     */
    public void setAttributesFromLine(String line) {
        String[] split = line.split("\\s");
        name = split[0];
        fullPackageName = split[1];
        path = split[2];
    }
}
