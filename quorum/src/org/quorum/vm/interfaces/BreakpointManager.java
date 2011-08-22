/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.vm.interfaces;

import java.util.*;


/**
 *
 * @author Andreas Stefik
 */
public class BreakpointManager {
    private TreeMap<Breakpoint, Breakpoint> breakpoints;
    /**
     * Do not use this variable, it is just for returning a blank iterator.
     */
    private Vector<Breakpoint> blankVector = new Vector<Breakpoint>();
    
    public BreakpointManager() {
        breakpoints = new TreeMap<Breakpoint, Breakpoint>();
    }
    
    public void addBreakpoint(Breakpoint key) {
        breakpoints.put(key, key);
    }
    
    public Breakpoint removeBreakpoint(Breakpoint key) {
        return breakpoints.remove(key);
    }
    
    public void clear() {
        breakpoints.clear();
    }
    
    public boolean isBreakpointAtPosition(Breakpoint key) {
        Breakpoint point = breakpoints.get(key);
        return point != null;
    }

    /** 
     * If a breakpoint exists at the location "key" (Line and a file), it turns
     * that break point off, otherwise it creates a new one.
     * @param key
     */
    public void toggleBreakpoint(Breakpoint key) {
        if(!breakpoints.containsKey(key)) {
            addBreakpoint(key);
        }
        else {
            removeBreakpoint(key);
        }
    }
}
