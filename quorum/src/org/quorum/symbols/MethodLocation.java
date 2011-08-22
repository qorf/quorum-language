/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.symbols;

/**
 * This class defines the location of the method in the final build
 * of a program, including any initialization it has, its starting location,
 * ending location, etc. All values are given
 * -1 by default if no location is known.
 * 
 * @author Andreas Stefik
 */
public class MethodLocation {
    private int start = -1;
    private int end = -1;

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
}
