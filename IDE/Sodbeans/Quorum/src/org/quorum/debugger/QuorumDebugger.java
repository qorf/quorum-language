/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import org.debugger.Debugger;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.debugger.ActionsProviderSupport;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.spi.debugger.DebuggerEngineProvider;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.text.Line;
import org.openide.util.Cancellable;
import org.openide.windows.TopComponent;
import org.quorum.actions.QuorumAction.ProcessCancel;
import org.quorum.projects.QuorumProject;

/**
 * Provides an interface for an omniscient debugger in the Quorum Programming
 * Language.
 *
 * @author Andreas Stefik
 */
public class QuorumDebugger extends ActionsProviderSupport implements BreakpointListener{
    private static final Logger logger = Logger.getLogger(QuorumDebugger.class.getName());
    private final Debugger debugger;
    
    /**
     * Action constant for Step Over Action.
     */
    public static final Object ACTION_STEP_OVER = "stepOver";
    /**
     * Action constant for Step Over Action.
     */
    public static final Object ACTION_STEP_BACK_OVER = "stepBackOver";
    /**
     * Action constant for breakpoint hit action.
     */
    public static final Object ACTION_RUN_INTO_METHOD = "runIntoMethod";
    /**
     * Action constant for Step Into Action.
     */
    public static final Object ACTION_STEP_INTO = "stepInto";
    /**
     * Action constant for Step Into Action.
     */
    public static final Object ACTION_STEP_BACK_INTO = "stepBackInto";
    /**
     * Action constant for Step Out Action.
     */
    public static final Object ACTION_STEP_OUT = "stepOut";
    /**
     * Action constant for Step Operation Action.
     */
    public static final Object ACTION_STEP_OPERATION = "stepOperation";
    /**
     * Action constant for Continue Action.
     */
    public static final Object ACTION_CONTINUE = "continue";
    /**
     * Action constant for Continue Action.
     */
    public static final Object ACTION_REWIND = "rewind";
    /**
     * Action constant for Continue Action.
     */
    public static final Object ACTION_REWIND_START = "rewindToStart";
    /**
     * Action constant for Start Action.
     */
    public static final Object ACTION_START = "start";
    /**
     * Action constant for Kill Action.
     */
    public static final Object ACTION_KILL = "kill";
    /**
     * Action constant for Make Caller Current Action.
     */
    public static final Object ACTION_MAKE_CALLER_CURRENT = "makeCallerCurrent";
    /**
     * Action constant for Make Callee Current Action.
     */
    public static final Object ACTION_MAKE_CALLEE_CURRENT = "makeCalleeCurrent";
    /**
     * Action constant for Pause Action.
     */
    public static final Object ACTION_PAUSE = "pause";
    /**
     * Action constant for Run to Cursor Action.
     */
    public static final Object ACTION_RUN_TO_CURSOR = "runToCursor";
    /**
     * Action constant for Run to Cursor Action.
     */
    public static final Object ACTION_RUN_BACK_TO_CURSOR = "runBackToCursor";
    /**
     * Action constant for Pop Topmost Call Action.
     */
    public static final Object ACTION_POP_TOPMOST_CALL = "popTopmostCall";
    /**
     * Action constant for Fix Action.
     */
    public static final Object ACTION_FIX = "fix";
    /**
     * Action constant for Restart Action.
     */
    public static final Object ACTION_RESTART = "restart";
    /**
     * Action constant for Toggle Breakpoint Action.
     */
    public static final Object ACTION_TOGGLE_BREAKPOINT = "toggleBreakpoint";
    /**
     * Action constant for New Watch Action.
     *
     * @since 1.24
     */
    public static final Object ACTION_NEW_WATCH = "newWatch";
    private final QuorumDebuggerEngineProvider engineProvider;
    private final QuorumProject project;
    private final ProcessCancel cancel;
    private final QuorumSupport support = new QuorumSupport();
    private static final QuorumAnnotationUpdater annotationProvider = new QuorumAnnotationUpdater();

    public QuorumDebugger(ContextProvider contextProvider) {
        List<? extends QuorumDebuggerCookie> lookup = contextProvider.lookup("", QuorumDebuggerCookie.class);
        QuorumDebuggerCookie cookie = lookup.get(0);
        debugger = cookie.getDebugger();
        project = cookie.getProject();
        cancel = (ProcessCancel) cookie.getCancel();
        cancel.debugger = this;
        
        support.setDebugger(debugger);
        support.setCompiler(project.getLookup().lookup(quorum.Libraries.Language.Compile.Compiler.class));
        
        engineProvider = (QuorumDebuggerEngineProvider) contextProvider.lookupFirst(null, DebuggerEngineProvider.class);
        for (Iterator it = actions.iterator(); it.hasNext();) {
            setEnabled(it.next(), true);
        }
        QuorumDebuggerListener listener = new QuorumDebuggerListener();
        listener.setEngine(engineProvider);
        listener.getSupport().setCompiler(project.getLookup().lookup(quorum.Libraries.Language.Compile.Compiler.class));
        //listener.setCompiler(project.getLookup().lookup(quorum.Libraries.Language.Compile.Compiler.class));
        listener.setDebugger(debugger);
        listener.setCancel(cancel);
        listener.setAnnotationUpdater(annotationProvider);
        listener.setQuorumDebugger(this);
        debugger.add(listener);
        QuorumBreakpointActionProvider.addListener(this);
    }
    
    /**
     * This set contains all actions for the debugger. Actions are contained
     * in the Sodbeans Actions module.
     */
    private static final Set actions = new HashSet();

    static {
        actions.add(ACTION_RUN_BACK_TO_CURSOR);
        actions.add(ACTION_STEP_BACK_INTO);
        actions.add(ACTION_STEP_BACK_OVER);
        actions.add(ACTION_REWIND_START);
        actions.add(ACTION_REWIND);
        actions.add(ACTION_KILL);
        actions.add(ACTION_PAUSE);
        actions.add(ACTION_CONTINUE);
        actions.add(ACTION_START);
        actions.add(ACTION_STEP_INTO);
        actions.add(ACTION_STEP_OUT);
        actions.add(ACTION_STEP_OVER);
        actions.add(ACTION_RUN_TO_CURSOR);
    }

    @Override
    public void doAction(Object action) {
        if (action.equals(ACTION_RUN_BACK_TO_CURSOR)) {
        } else if (action.equals(ACTION_STEP_BACK_INTO)) {
        } else if (action.equals(ACTION_STEP_BACK_OVER)) {
        } else if (action.equals(ACTION_REWIND)) {
        } else if (action.equals(ACTION_REWIND_START)) {
        } else if (action.equals(ACTION_KILL)) {
            stop(false);
        } else if (action.equals(ACTION_PAUSE)) {
            debugger.pause();
        } else if (action.equals(ACTION_CONTINUE)) {
            debugger.forward();
        } else if (action.equals(ACTION_START)) {
        } else if (action.equals(ACTION_STEP_INTO)) {
            debugger.stepInto();
        } else if (action.equals(ACTION_STEP_OVER)) {
            debugger.stepOver();
        } else if (action.equals(ACTION_STEP_OUT)) {
            debugger.stepOut();
        }else if (action.equals(ACTION_RUN_TO_CURSOR)) {
            runToCursor();
        }
    }
    
    public void runToCursor() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Node[] n = TopComponent.getRegistry().getActivatedNodes();
                if (n.length == 1) {
                    EditorCookie ec = n[0].getLookup().lookup(EditorCookie.class);
                    if (ec != null) {
                        JEditorPane[] panes = ec.getOpenedPanes();
                        if (panes.length > 0) {
                            int cursor = panes[0].getCaret().getDot();
                            Line line = NbEditorUtilities.getLine(panes[0].getDocument(), cursor, true);
                            FileObject fo = NbEditorUtilities.getFileObject(panes[0].getDocument());
                            String jvmName = support.findJVMClassName(fo, line.getLineNumber() + 1);
                            debugger.runForwardToLine(jvmName, line.getLineNumber() + 1);
                        }
                    }
                }
            }
        });
    }
    
    public void stop(boolean isAtEnd) {
        debugger.stop();
        engineProvider.getDestructor().killEngine();
        cancel.flush = isAtEnd;
        if(isAtEnd) {
            cancel.debugger = null;
        }
        cancel.cancel();
        annotationProvider.removeAnnotation();
        QuorumBreakpointActionProvider.removeListener(this);
    }

    @Override
    public Set getActions() {
        return actions;
    }

    @Override
    public void addLineBreakpoint(QuorumBreakpoint b) {
        support.addLineBreakpoint(b);
    }

    @Override
    public void removeLineBreakpoint(QuorumBreakpoint b) {
        support.removeLineBreakpoint(b);
    }
}
