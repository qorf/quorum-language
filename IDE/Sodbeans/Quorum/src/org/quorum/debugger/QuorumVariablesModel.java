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
import java.util.List;
import org.debugger.Debugger;
import org.debugger.Variable;
import org.debugger.VariableColumns;
import org.debugger.VariablesModel;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.NodeModel;
import org.netbeans.spi.viewmodel.TableModel;
import org.netbeans.spi.viewmodel.TreeModel;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.quorum.debugger.DebuggerFactory;

/**
 * This class implements the variable window for Quorum.
 *
 * @author Andreas Stefik
 */
public class QuorumVariablesModel implements TreeModel, NodeModel, TableModel {

    private final Debugger debugger;// = DebuggerFactory.getQuorumDebugger();
    private VariablesModel model = null;
    private static final LinkedList<ModelListener> listeners = new LinkedList<ModelListener>();
    public static final String LOCALS_TYPE = "LocalsType";
    public static final String LOCALS_VALUE = "LocalsValue";
    public static final String LOCAL_VARIABLE_IMAGE
            = "org/netbeans/modules/debugger/resources/localsView/LocalVariable";

    public static final String LOCAL_VARIABLE_GROUP_IMAGE
            = "org/netbeans/modules/debugger/resources/localsView/LocalVariablesGroup";

    public static final String SUPER_VARIABLE_IMAGE
            = "org/netbeans/modules/debugger/resources/watchesView/SuperVariable";

    public static final String FIELD_IMAGE
            = "org/netbeans/modules/debugger/resources/watchesView/Field";

    public QuorumVariablesModel(ContextProvider contextProvider) {
        List<? extends QuorumDebuggerCookie> lookup = contextProvider.lookup("", QuorumDebuggerCookie.class);
        QuorumDebuggerCookie cookie = lookup.get(0);
        debugger = cookie.getDebugger();
    }

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
        model = debugger.getVariablesModel();
        return ROOT;
    }

    @Override
    public Object[] getChildren(Object node, int from, int to) throws UnknownTypeException {
        if (model == null) {
            return new Object[]{};
        }
        if (node == ROOT) {
            return model.getChildren(null, from, to);
        } else {
            Variable variable = (Variable) node;
            return model.getChildren(variable, from, to);
        }
    }

    @Override
    public boolean isLeaf(Object node) throws UnknownTypeException {
        if (model == null) {
            return true;
        }
        if (node == ROOT) {
            return model.isLeaf(null);
        } else {
            Variable variable = (Variable) node;
            return model.isLeaf(variable);
        }
    }

    @Override
    public int getChildrenCount(Object node) throws UnknownTypeException {
        if (model == null) {
            return 0;
        }
        if (node == ROOT) {
            return model.getChildrenCount(null);
        } else {
            Variable variable = (Variable) node;
            return model.getChildrenCount(variable);
        }
    }

    @Override
    public void addModelListener(ModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeModelListener(ModelListener l) {
        listeners.remove(l);
    }

    @Override
    public String getDisplayName(Object node) throws UnknownTypeException {
        if(model == null) {
            return "";
        }
        if (node != null) {
            Variable variable = (Variable) node;
            return model.getDisplayName(variable);
        }
        return "";
    }

    @Override
    public String getIconBase(Object node) throws UnknownTypeException {
        if(model == null) {
            return "";
        }
        if (node != null) {
            if (node instanceof Variable) {
                Variable variable = (Variable) node;
                if (variable.isField()) {
                    if(variable.isParent()) {
                        return SUPER_VARIABLE_IMAGE;
                    } else {
                        return FIELD_IMAGE;
                    }
                } else {
                    if (variable.isPrimitive()) {
                        return LOCAL_VARIABLE_IMAGE;
                    } else {
                        return FIELD_IMAGE;
                    }
                }
            }
        }
        return "";
    }

    @Override
    public String getShortDescription(Object node) throws UnknownTypeException {
        if(model == null) {
            return "";
        }
        if (node != null && node instanceof Variable) {
            Variable variable = (Variable) node;
            return model.getShortDescription(variable);
        }
        return "";
    }

    @Override
    public Object getValueAt(Object node, String column) throws UnknownTypeException {
        if(model == null) {
            return "";
        }
        if (node != null && node instanceof Variable) {
            Variable variable = (Variable) node;
            if (column.compareTo(LOCALS_TYPE) == 0) {
                return model.getValueAt(variable, VariableColumns.TYPE);
            } else if (column.compareTo(LOCALS_VALUE) == 0) {
                return model.getValueAt(variable, VariableColumns.VALUE);
            }
        }
        return "";
    }

    @Override
    public boolean isReadOnly(Object node, String column) throws UnknownTypeException {
        if(model == null) {
            return true;
        }
        if (node != null && node instanceof Variable) {
            Variable variable = (Variable) node;
            if (column.compareTo(LOCALS_TYPE) == 0) {
                return model.isReadOnly(variable, VariableColumns.TYPE);
            } else if (column.compareTo(LOCALS_VALUE) == 0) {
                return model.isReadOnly(variable, VariableColumns.VALUE);
            }
        }
        return false;
    }

    @Override
    public void setValueAt(Object node, String columnID, Object value) throws UnknownTypeException {

    }
}
