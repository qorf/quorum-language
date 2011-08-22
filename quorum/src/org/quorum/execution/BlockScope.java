/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.execution;

/**
 * Stores the data necessary in a block (typically used with if statements,
 * loops, and check/detect statements).
 *
 * @author Melissa Stefik
 */
public class BlockScope extends RuntimeScope{

    private String blockScopeName;

    /**
     * Set the unique check block identifier.
     *
     * @param name
     */
    public void setBlockName(String name){
        blockScopeName = name;
    }

    /**
     * Get the unique check block identifier.
     *
     * @return
     */
    public String getBlockName(){
        return blockScopeName;
    }
}
