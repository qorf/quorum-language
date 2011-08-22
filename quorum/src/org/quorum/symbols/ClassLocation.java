/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 * This class defines the location of the class in the final build
 * of a program, including any initialization it has, its starting location,
 * ending location, and constructor location. All values are given
 * -1 by default if no location is known. For example, if a class
 * does not have a constructor defined, its constructor value will be
 * -1.
 * 
 * @author Andreas Stefik
 */
public class ClassLocation {
    
    /**
     * The first op-code that represents its constructor, if any.
     */
    private int start = -1;
    private int end = -1;
    private int initialization = -1;
    private int constructor = -1;

    /**
     * @return the start
     */
    public int getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * @return the initialization
     */
    public int getInitialization() {
        return initialization;
    }

    /**
     * @param initialization the initialization to set
     */
    public void setInitialization(int initialization) {
        this.initialization = initialization;
    }

    /**
     * @return the constructor
     */
    public int getConstructor() {
        return constructor;
    }

    /**
     * @param constructor the constructor to set
     */
    public void setConstructor(int constructor) {
        this.constructor = constructor;
    }
}
