/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.plugins;

/**
 * This interface represents a generic interface that can be implemented to
 * send information to a "console." A console is defined broadly in that,
 * any device that simply declares it is a console and that can post, unpost,
 * get input, and clear itself is acceptable. Not all implementations may
 * implement the unpost method.
 * 
 * @author Andreas Stefik
 */
public interface Console {
    
    
    /**
     * Posts a string to the console implementation.
     * @param post
     */
    public void post(String post);

    /**
     * Removes the previous post from the command line.
     */
    public void unpost();

    /**
     * Polls the command line for input from the user.
     * 
     * @return
     */
    public String getInput(String post);

    /**
     * Removes all previous post to the command line.
     */
    public void clear();    
}
