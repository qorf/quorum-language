/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import org.netbeans.api.debugger.Breakpoint;
import org.netbeans.spi.debugger.ui.BreakpointAnnotation;
import org.openide.text.Annotatable;

/**
 * Provides a default annotation for showing breakpoints in an editor.
 *
 * @author Andreas Stefik
 */
public class QuorumBreakpointAnnotation extends BreakpointAnnotation{
    private final QuorumBreakpoint breakpoint;
    private final Annotatable annotatable;

    public QuorumBreakpointAnnotation(final Annotatable a, final QuorumBreakpoint b) {
        breakpoint = b;
        annotatable = a;
        attach(a);
    }
    
    @Override
    public String getAnnotationType() {
        return "Breakpoint";
    }

    @Override
    public String getShortDescription() {
        return "Breakpoint";
    }

    @Override
    public Breakpoint getBreakpoint() {
        return breakpoint;
    }
    
    @Override
    public String toString() {
        return breakpoint.toString();
    }
}
