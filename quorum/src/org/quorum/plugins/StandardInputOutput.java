/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

/**
 * A stub class for standard in/standard out operations.
 * 
 * @author Andreas Stefik
 */
public class StandardInputOutput implements Console{

    public void post(String post) {
        System.out.println(post);
    }

    /**
     * TODO: Not yet implemented.
     */
    public void unpost() {
    }

    /**
     * TODO: Not yet implemented.
     * 
     * @param post
     * @return 
     */
    public String getInput(String post) {
        return "";
    }

    /**
     * TODO: Not yet implemented.
     */
    public void clear() {
    }
    
}
