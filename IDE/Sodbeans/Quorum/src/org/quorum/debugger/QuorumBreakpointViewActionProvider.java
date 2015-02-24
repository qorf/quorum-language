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

import javax.swing.Action;
import org.netbeans.spi.viewmodel.Models;
import org.netbeans.spi.viewmodel.NodeActionsProvider;
import org.netbeans.spi.viewmodel.NodeActionsProviderFilter;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.openide.text.Line;
import org.quorum.debugger.QuorumBreakpoint;

/**
 * This breakpoint action provider was inspired by the PHP source http://hg.netbeans.org/main/file/5be291e817f4/php.dbgp/src/org/netbeans/modules/php/dbgp/breakpoints/BrkptsViewActionProvider.java
 * 
 * @author astefik
 */
public class QuorumBreakpointViewActionProvider implements NodeActionsProviderFilter {
    private static final String GO_TO_SOURCE_LABEL = "Go to Source";
    private static final Action GO_TO_SOURCE_ACTION = Models.createAction(
        GO_TO_SOURCE_LABEL, 
        new GoToSourcePerformer(), 
        Models.MULTISELECTION_TYPE_EXACTLY_ONE
    );

    @Override
    public void performDefaultAction(NodeActionsProvider original, Object node) throws UnknownTypeException {
        if (node instanceof QuorumBreakpoint) {
            goToSource((QuorumBreakpoint) node);
        } else {
            original.performDefaultAction(node);
        }
    }

    @Override
    public Action[] getActions(NodeActionsProvider original, Object node) throws UnknownTypeException {
        Action[] actions = original.getActions(node);
        if (node instanceof QuorumBreakpoint) {
            Action[] newActions = new Action[actions.length + 2];
            newActions[0] = GO_TO_SOURCE_ACTION;
            newActions[1] = null;
            System.arraycopy(actions, 0, newActions, 2, actions.length);
            actions = newActions;
        }
        return actions;
    }

    private static void goToSource(QuorumBreakpoint breakpoint) {
        Line line = breakpoint.getLine();
        if (line != null) {
            line.show(Line.ShowOpenType.REUSE, Line.ShowVisibilityType.FOCUS);
        }
    }

    private static class GoToSourcePerformer implements Models.ActionPerformer {
        @Override
        public boolean isEnabled(Object arg) {
            return true;
        }

        @Override
        public void perform(Object[] nodes) {
            goToSource((QuorumBreakpoint) nodes[0]);
        }
    }
}
