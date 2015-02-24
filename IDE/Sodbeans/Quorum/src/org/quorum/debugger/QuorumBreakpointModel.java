/*
 Copyright (c) 2013, Andreas Stefik
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those
 of the authors and should not be interpreted as representing official policies, 
 either expressed or implied, of the FreeBSD Project.
 */
package org.quorum.debugger;

import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.quorum.debugger.QuorumBreakpoint;

/**
 *
 * @author astefik
 */
public class QuorumBreakpointModel implements NodeModel
{
    //Stolen from the PHP debugger in the NetBeans source
    public static final String BREAKPOINT ="org/netbeans/modules/debugger/resources/breakpointsView/NonLineBreakpoint"; 
    public static final String LINE_BREAKPOINT ="org/netbeans/modules/debugger/resources/breakpointsView/Breakpoint";
    public static final String CURRENT_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/NonLineBreakpointHit";
    public static final String CURRENT_LINE_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/BreakpointHit";
    public static final String DISABLED_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/DisabledNonLineBreakpoint";
    public static final String DISABLED_LINE_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/DisabledBreakpoint";
    public static final String DISABLED_CURRENT_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/DisabledNonLineBreakpointHit";
    public static final String DISABLED_CURRENT_LINE_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/DisabledBreakpointHit";
    public static final String LINE_CONDITIONAL_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/ConditionalBreakpoint";
    public static final String CURRENT_LINE_CONDITIONAL_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/ConditionalBreakpointHit";
    public static final String DISABLED_LINE_CONDITIONAL_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/DisabledConditionalBreakpoint";
    public static final String BROKEN_LINE_BREAKPOINT = "org/netbeans/modules/debugger/resources/breakpointsView/Breakpoint_broken";

    @Override
    public String getDisplayName(Object node) throws UnknownTypeException {
        if(node instanceof QuorumBreakpoint) {
            return node.toString();
        }
        return "";
    }

    @Override
    public String getIconBase(Object node) throws UnknownTypeException {
        if(node instanceof QuorumBreakpoint) {
            return LINE_BREAKPOINT;
        }
        return "";
    }

    @Override
    public String getShortDescription(Object node) throws UnknownTypeException {
        if(node instanceof QuorumBreakpoint) {
            QuorumBreakpoint breakpoint = (QuorumBreakpoint) node;
            return breakpoint.getLine().getDisplayName();
        }
        return "";
    }

    @Override
    public void addModelListener(ModelListener l) {
    }

    @Override
    public void removeModelListener(ModelListener l) {
    }
    
}
