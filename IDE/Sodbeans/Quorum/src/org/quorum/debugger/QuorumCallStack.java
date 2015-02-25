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

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.Action;
import org.debugger.CallStackModel;
import org.debugger.Debugger;
import org.debugger.StackFrame;
import org.debugger.VariableColumns;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.Models;
import org.netbeans.spi.viewmodel.NodeActionsProvider;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.TableModel;
import org.netbeans.spi.viewmodel.TreeModel;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.quorum.debugger.DebuggerFactory;

/**
 * This class provides the user interface in the call stack window for the call
 * stack. It provides basic functionality, like jumping to a source code file.
 *
 * @author Andreas Stefik
 */
public class QuorumCallStack implements TreeModel, NodeModel, TableModel, NodeActionsProvider {

    private final Debugger debugger = DebuggerFactory.getQuorumDebugger();
    private CallStackModel model;

    public static final String CALL_STACK
            = "org/netbeans/modules/debugger/resources/"
            + "callStackView/NonCurrentFrame";

    public static final String CURRENT_CALL_STACK
            = "org/netbeans/modules/debugger/resources/"
            + "callStackView/CurrentFrame";

    private static LinkedList<ModelListener> listeners = new LinkedList<ModelListener>();

    /**
     * This method fires a change event to the tree model, causing it to refresh
     * all of its values. As this "can" be expensive, it should be used
     * sparingly.
     */
    public static void update() {
        Iterator<ModelListener> listen = listeners.iterator();
        while (listen.hasNext()) {
            ModelListener next = listen.next();
            next.modelChanged(null);
        }
    }

    @Override
    public Object getRoot() {
        model = debugger.getCallStackModel();
        return ROOT;
    }

    @Override
    public Object[] getChildren(Object o, int from, int to) throws UnknownTypeException {
        if(model == null) {
            return new Object[0];
        }
        if (o != null && o instanceof StackFrame) {
            StackFrame frame = (StackFrame) o;
            return model.getChildren(frame, from, to);
        }
        return model.getChildren(null, from, to);
    }

    @Override
    public boolean isLeaf(Object o) throws UnknownTypeException {
        if(model == null) {
            return true;
        }
        if (o != null && o instanceof StackFrame) {
            StackFrame frame = (StackFrame) o;
            return model.isLeaf(frame);
        }
        return model.isLeaf(null);
    }

    @Override
    public int getChildrenCount(Object o) throws UnknownTypeException {
        if(model == null) {
            return 0;
        }
        if (o != null && o instanceof StackFrame) {
            StackFrame frame = (StackFrame) o;
            return model.getChildrenCount(frame);
        }
        return model.getChildrenCount(null);
    }

    @Override
    public String getDisplayName(Object o) throws UnknownTypeException {
        if(model == null) {
            return "";
        }
        if (o != null && o instanceof StackFrame) {
            StackFrame frame = (StackFrame) o;
            String name = model.getDisplayName((StackFrame) o);
            if (frame.isCurrent()) {
                return "<html><strong>" + name + "</strong></html>";
            } else {
                return name;
            }
        }
        return model.getDisplayName(null);
    }

    @Override
    public String getIconBase(Object o) throws UnknownTypeException {
        if(model == null) {
            return "";
        }
        if (o != null && o instanceof StackFrame) {
            StackFrame frame = (StackFrame) o;
            if (frame.isCurrent()) {
                return CURRENT_CALL_STACK;
            } else {
                return CALL_STACK;
            }
        }
        return "";
    }

    @Override
    public String getShortDescription(Object o) throws UnknownTypeException {
        return "";
    }

    @Override
    public void addModelListener(ModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelListener(ModelListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Object getValueAt(Object o, String string) throws UnknownTypeException {
        return model.getValueAt(null, VariableColumns.NAME);
    }

    @Override
    public boolean isReadOnly(Object o, String string) throws UnknownTypeException {
        return true;
    }

    @Override
    public void setValueAt(Object o, String string, Object o1) throws UnknownTypeException {
    }

    @Override
    public void performDefaultAction(Object node) throws UnknownTypeException {
        if (node instanceof StackFrame) {
            StackFrame stack = (StackFrame) node;
            //QuorumSupport.jumpToCallStackLocation(stack.getClassInformation().getFullyQualifiedName(), stack.getLine());
        }
    }

    @Override
    public Action[] getActions(Object o) throws UnknownTypeException {
        Action[] actions = new Action[1];
        actions[0] = Models.createAction("Go to Source", new QuorumCallStack.GoToSourceCallStack(), Models.MULTISELECTION_TYPE_EXACTLY_ONE);
        return actions;
    }

    /**
     * This inner Class allows us to have an action that jumps to the
     * appropriate editor when the user clicks on the call stack.
     */
    private static class GoToSourceCallStack implements Models.ActionPerformer {

        @Override
        public boolean isEnabled(Object arg) {
            return true;
        }

        @Override
        public void perform(Object[] nodes) {
            if (nodes[0] instanceof StackFrame) {
                StackFrame stack = (StackFrame) nodes[0];
                //QuorumSupport.jumpToCallStackLocation(stack.getClassInformation().getFullyQualifiedName(), stack.getLine());
            }
        }
    }

}
