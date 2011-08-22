package org.quorum.vm.interfaces;

/**
 * Implement this interface if you want to receive events from the compiler
 * when heavyweight debugger functions have finished executing.
 * 
 * @author Andreas Stefik
 */
public interface DebuggerListener {
    public void update();
}
