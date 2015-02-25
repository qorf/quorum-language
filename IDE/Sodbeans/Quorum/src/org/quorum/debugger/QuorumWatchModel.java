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

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import org.debugger.Debugger;
import org.debugger.Variable;
import org.debugger.VariableColumns;
import org.debugger.VariablesModel;
import org.debugger.jdi.JDIVariable;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.api.debugger.Watch;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.spi.viewmodel.ExtendedNodeModel;
import org.netbeans.spi.viewmodel.ModelListener;
import org.netbeans.spi.viewmodel.Models;
import org.netbeans.spi.viewmodel.NodeActionsProvider;
import org.netbeans.spi.viewmodel.NodeActionsProviderFilter;
import org.netbeans.spi.viewmodel.TableModel;
import org.netbeans.spi.viewmodel.TreeModel;
import org.netbeans.spi.viewmodel.UnknownTypeException;
import org.openide.util.NbBundle;
import org.openide.util.datatransfer.PasteType;
import org.openide.windows.WindowManager;
import org.quorum.debugger.DebuggerFactory;

/**
 *  This class implements the variable window portion of the watch window.
 * 
 * @author Andreas Stefik
 */
public class QuorumWatchModel implements TreeModel, ExtendedNodeModel, TableModel, NodeActionsProviderFilter {

    private final Debugger debugger;// = DebuggerFactory.getQuorumDebugger();
    private VariablesModel model = null;
    private static final LinkedList<ModelListener> listeners = new LinkedList<ModelListener>();
    public static final String WATCH_TYPE = "WatchType";
    public static final String WATCH_VALUE = "WatchValue";
    
    
    public static final String WATCH_IMAGE
            = "org/netbeans/modules/debugger/resources/watchesView/watch_16.png";
    public static final String LOCAL_VARIABLE_IMAGE
            = "org/netbeans/modules/debugger/resources/localsView/LocalVariable.gif";

    public static final String LOCAL_VARIABLE_GROUP_IMAGE
            = "org/netbeans/modules/debugger/resources/localsView/LocalVariablesGroup.gif";

    public static final String SUPER_VARIABLE_IMAGE
            = "org/netbeans/modules/debugger/resources/watchesView/SuperVariable.gif";

    public static final String FIELD_IMAGE
            = "org/netbeans/modules/debugger/resources/watchesView/Field.gif";
    
    public QuorumWatchModel(ContextProvider contextProvider) {
        List<? extends QuorumDebuggerCookie> lookup = contextProvider.lookup("", QuorumDebuggerCookie.class);
        QuorumDebuggerCookie cookie = lookup.get(0);
        debugger = cookie.getDebugger();
        model = debugger.getVariablesModel();
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
        if (node == ROOT) {
            Watch[] watches = DebuggerManager.getDebuggerManager().getWatches();
            if(model != null) {
                Object[] objects = new Object[watches.length];
                for(int i = 0; i < watches.length; i++) {
                    Watch watch = watches[i];
                    QuorumWatch w = new QuorumWatch();
                    w.setWatch(watch);
                    if(watch.isEnabled()) {
                        org.debugger.Watch debuggerWatch = new org.debugger.Watch();
                        debuggerWatch.setExpression(watches[i].getExpression());
                        Variable var = model.getWatchResult(debuggerWatch);
                        w.setVariable(var);
                        objects[i] = w;
                    } else {
                        objects[i] = w;
                    }
                    QuorumWatch obj = (QuorumWatch) objects[i];
                    if(obj.getVariable() == null) {
                        JDIVariable var = new JDIVariable();
                        var.setWatchExpression(true);
                        var.setName(watch.getExpression());
                        var.setTypeName("");
                        var.setValue(">\"" + var.getName() + "\" is not a known variable in the current context.<");
                        w.setVariable(var);
                        objects[i] = w;
                    }
                }
                return objects;
            }
        } else if(node != null && node instanceof QuorumWatch && model != null) {
            QuorumWatch qw = (QuorumWatch) node;
            return model.getChildren(qw.getVariable(), from, to);
        }else if(node != null && node instanceof Variable && model != null) {
            Variable variable = (Variable) node;
            return model.getChildren(variable, from, to);
        }
        
        return new Object[0];
    }

    @Override
    public boolean isLeaf(Object node) throws UnknownTypeException {
        if (node == ROOT) {
            Watch[] watches = DebuggerManager.getDebuggerManager().getWatches();
            return watches.length == 0;
        } else if(node != null && node instanceof QuorumWatch && model != null) {
            QuorumWatch variable = (QuorumWatch) node;
            return model.isLeaf(variable.getVariable());
        } else if(node != null && node instanceof Variable && model != null) {
            Variable variable = (Variable) node;
            return model.isLeaf(variable);
        } else {
            return true;
        }
    }

    @Override
    public int getChildrenCount(Object node) throws UnknownTypeException {
        if (node == ROOT) {
            Watch[] watches = DebuggerManager.getDebuggerManager().getWatches();
            return watches.length;
        } else if(node != null && node instanceof QuorumWatch && model != null) {
            QuorumWatch variable = (QuorumWatch) node;
            return model.getChildrenCount(variable.getVariable());
        } else if(node != null && node instanceof Variable && model != null) {
            Variable variable = (Variable) node;
            return model.getChildrenCount(variable);
        } else {
            return 0;
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
        if (node != null && node instanceof Watch) {
            Watch watch = (Watch) node;
            String expression = watch.getExpression();
            return expression;
        } else if (node != null && model != null && node instanceof QuorumWatch) {
            QuorumWatch variable = (QuorumWatch) node;
            return model.getDisplayName(variable.getVariable());
        } else if (node != null && model != null && node instanceof Variable) {
            Variable variable = (Variable) node;
            return model.getDisplayName(variable);
        }
        return "";
    }

    @Override
    public String getIconBase(Object node) throws UnknownTypeException {
        if (node != null) {
            if (node instanceof Variable) {
                Variable variable = (Variable) node;
                if(variable.isWatchExpression()) {
                    return "org/netbeans/modules/debugger/resources/localsView/LocalVariablesGroup";
                }else if (variable.isField()) {
                    if (variable.isParent()) {
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
            } else if (node instanceof QuorumWatch) {
                return WATCH_IMAGE;
            }
        }
        return "";
    }
    
    @Override
    public String getIconBaseWithExtension(Object node) throws UnknownTypeException {
        if (node != null) {
            if (node instanceof Variable) {
                Variable variable = (Variable) node;
                if(variable.isWatchExpression()) {
                    return WATCH_IMAGE;
                }else if (variable.isField()) {
                    if (variable.isParent()) {
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
            } else if (node instanceof QuorumWatch) {
                return WATCH_IMAGE;
            }
        }
        return "";
    }
    
    @Override
    public String getShortDescription(Object o) throws UnknownTypeException {
        return "";
    }

    @Override
    public Object getValueAt(Object node, String column) throws UnknownTypeException {
        if (node != null && model != null && node instanceof Variable) {
            Variable variable = (Variable) node;
            if (column.compareTo(WATCH_TYPE) == 0) {
                return model.getValueAt(variable, VariableColumns.TYPE);
            } else if (column.compareTo(WATCH_VALUE) == 0) {
                return model.getValueAt(variable, VariableColumns.VALUE);
            }
        } else if (node != null && model != null && node instanceof QuorumWatch) {
            QuorumWatch qw = (QuorumWatch) node;
            Variable variable = qw.getVariable();
            if (column.compareTo(WATCH_TYPE) == 0) {
                return model.getValueAt(variable, VariableColumns.TYPE);
            } else if (column.compareTo(WATCH_VALUE) == 0) {
                return model.getValueAt(variable, VariableColumns.VALUE);
            }
        }
        return "";
    }

    @Override
    public boolean isReadOnly(Object o, String string) throws UnknownTypeException {
        return false;
    }

    @Override
    public void setValueAt(Object o, String string, Object o1) throws UnknownTypeException {

    }

    private static final String WATCH_ACTION_DELETE = "CTL_WatchAction_Delete";
    private static final String WATCH_ACTION_CUSTOMIZE = "CTL_WatchAction_Customize";

    private static final Action DELETE_ACTION = Models.createAction(
            NbBundle.getMessage(QuorumWatchModel.class, WATCH_ACTION_DELETE),
            new Models.ActionPerformer() {
                @Override
                public boolean isEnabled(Object node) {
                    return true;
                }

                @Override
                public void perform(Object[] nodes) {
                    for (Object node : nodes) {
                        if(node instanceof QuorumWatch) {
                            QuorumWatch qw = (QuorumWatch) node;
                            qw.remove();
                        }
                    }
                }
            },
            Models.MULTISELECTION_TYPE_ANY
    );

    private static final Action CUSTOMIZE_ACTION = Models.createAction(
            NbBundle.getMessage(QuorumWatchModel.class, WATCH_ACTION_CUSTOMIZE),
            new Models.ActionPerformer() {
                @Override
                public boolean isEnabled(Object node) {
                    return true;
                }

                @Override
                public void perform(Object[] nodes) {
                    if(nodes[0] instanceof QuorumWatch) {
                        edit((QuorumWatch) nodes[0]);
                    }
                }
            },
            Models.MULTISELECTION_TYPE_EXACTLY_ONE
    );

    static {
        DELETE_ACTION.putValue(
                Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke("DELETE")
        );
    }
     
    private static void edit(QuorumWatch node) {
        JFrame mainNetBeansWindow = (JFrame) WindowManager.getDefault().getMainWindow();
        QuorumWatchEditor editor = new QuorumWatchEditor(mainNetBeansWindow, true);
        editor.setLocationRelativeTo(mainNetBeansWindow);
        QuorumWatch qw = (QuorumWatch) node;
        editor.setExpressionText(qw.getWatch().getExpression());

        editor.setVisible(true);
        if(editor.isOk()) {
            qw.getWatch().setExpression(editor.getExpressionText());
            QuorumWatchModel.update();
        }
    }
    
     @Override
    public Action[] getActions(NodeActionsProvider original, Object node) throws UnknownTypeException {
        Action[] actions;
        try {
            if(node != null && node instanceof QuorumWatch) {
                actions = original.getActions(ROOT);
            } else {
                actions = original.getActions(node);
            }
        } catch (UnknownTypeException e) {
            actions = new Action[0];
        }

        Action[] varActions = getActions(node);
        Action[] result = new Action[actions.length + varActions.length];
        System.arraycopy(actions, 0, result, 0, actions.length);
        System.arraycopy(varActions, 0, result, actions.length,
                varActions.length);
        return result;
    }

    private Action[] getActions(Object node) throws UnknownTypeException {
        if (node == TreeModel.ROOT) {
            return new Action[0];
        }
        if (node instanceof QuorumWatch) {
            return new Action[]{
                DELETE_ACTION,
                null,
                CUSTOMIZE_ACTION
            };
        }

        return new Action[0];
    }

    @Override
    public void performDefaultAction(NodeActionsProvider nap, Object node) throws UnknownTypeException {
        if(node instanceof QuorumWatch) {
            edit((QuorumWatch) node);
        }
    }

    @Override
    public boolean canRename(Object o) throws UnknownTypeException {
        return false;
    }

    @Override
    public boolean canCopy(Object o) throws UnknownTypeException {
        return false;
    }

    @Override
    public boolean canCut(Object o) throws UnknownTypeException {
        return false;
    }

    @Override
    public Transferable clipboardCopy(Object o) throws IOException, UnknownTypeException {
        return null;
    }

    @Override
    public Transferable clipboardCut(Object o) throws IOException, UnknownTypeException {
        return null;
    }

    @Override
    public PasteType[] getPasteTypes(Object o, Transferable t) throws UnknownTypeException {
        return null;
    }

    @Override
    public void setName(Object o, String string) throws UnknownTypeException {
       
    }

    
}
