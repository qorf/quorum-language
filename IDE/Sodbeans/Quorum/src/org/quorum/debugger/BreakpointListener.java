/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

/**
 *
 * @author stefika
 */
public interface BreakpointListener {
    public void addLineBreakpoint(QuorumBreakpoint b);
    public void removeLineBreakpoint(QuorumBreakpoint b);
}
