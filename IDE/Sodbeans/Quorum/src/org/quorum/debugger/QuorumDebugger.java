/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.debugger.Debugger;
import org.netbeans.spi.debugger.ActionsProviderSupport;
import org.netbeans.spi.debugger.ContextProvider;
import org.netbeans.spi.debugger.DebuggerEngineProvider;
import org.netbeans.spi.viewmodel.TreeModel;
import org.openide.util.Lookup;
import org.quorum.debugger.DebuggerFactory;
//import org.sodbeans.actions.*;

/**
 * Provides an interface for an omniscient debugger in the Quorum Programming
 * Language.
 *
 * @author Andreas Stefik
 */
public class QuorumDebugger extends ActionsProviderSupport {

//    private org.sodbeans.compiler.api.Compiler compiler
//            = Lookup.getDefault().lookup(org.sodbeans.compiler.api.Compiler.class);
    private static final Logger logger = Logger.getLogger(QuorumDebugger.class.getName());
    private Debugger debugger = DebuggerFactory.getQuorumDebugger();
    
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
//    private final SodbeansRunBackToCursorAction backToCursor = new SodbeansRunBackToCursorAction();
//    private final SodbeansStepBackIntoAction stepBackInto = new SodbeansStepBackIntoAction();
//    private final SodbeansStepBackOverAction stepBackOver = new SodbeansStepBackOverAction();
//    private final SodbeansRewindDebuggerAction rewind = new SodbeansRewindDebuggerAction();
//    private final SodbeansRewindStartDebuggerAction rewindToStart = new SodbeansRewindStartDebuggerAction();
//    private final SodbeansStopDebuggerAction kill = new SodbeansStopDebuggerAction();
//    private final SodbeansPauseDebuggerAction pause = new SodbeansPauseDebuggerAction();
//    private final SodbeansContinueAction continueAction = new SodbeansContinueAction();
//    private final SodbeansStepIntoAction stepInto = new SodbeansStepIntoAction();
//    private final SodbeansStepOverAction stepOver = new SodbeansStepOverAction();
//    private final SodbeansStepOutAction stepOut = new SodbeansStepOutAction();
//    private final SodbeansRunToCursorAction runToCursor = new SodbeansRunToCursorAction();
//    private QuorumDebuggerEngineProvider engineProvider;
//    private static final ProgramCounterAnnotationUpdater programCounterAnnotationUpdater
//            = new ProgramCounterAnnotationUpdater();
//
//    private static QuorumAnnotationUpdater annotationProvider = new QuorumAnnotationUpdater();
    private static boolean firstTODRun = true;

    public QuorumDebugger(ContextProvider contextProvider) {
//        engineProvider = (QuorumDebuggerEngineProvider) contextProvider.lookupFirst(null, DebuggerEngineProvider.class);
//        for (Iterator it = actions.iterator(); it.hasNext();) {
//            setEnabled(it.next(), true);
//        }
//        QuorumDebuggerListener listener = new QuorumDebuggerListener();
//        listener.setEngine(engineProvider);
//        listener.setKill(kill);
//        listener.setAnnotationUpdater(annotationProvider);
//        debugger.add(listener);
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
        int a = 5;
//        try {
//            if (action.equals(ACTION_RUN_BACK_TO_CURSOR)) {
//                backToCursor.actionPerformed(null);
//            } else if (action.equals(ACTION_STEP_BACK_INTO)) {
//                stepBackInto.actionPerformed(null);
//            } else if (action.equals(ACTION_STEP_BACK_OVER)) {
//                stepBackOver.actionPerformed(null);
//            } else if (action.equals(ACTION_REWIND)) {
//                rewind.actionPerformed(null);
//            } else if (action.equals(ACTION_REWIND_START)) {
//                rewindToStart.actionPerformed(null);
//            } else if (action.equals(ACTION_KILL)) {
//                kill.actionPerformed(null);
//                engineProvider.getDestructor().killEngine();
//                annotationProvider.removeAnnotation();
//            } else if (action.equals(ACTION_PAUSE)) {
//                pause.actionPerformed(null);
//            } else if (action.equals(ACTION_CONTINUE)) {
//                continueAction.actionPerformed(null);
//                annotationProvider.removeAnnotation();
//            } else if (action.equals(ACTION_START)) {
//            } else if (action.equals(ACTION_STEP_INTO)) {
//                stepInto.actionPerformed(null);
//            } else if (action.equals(ACTION_STEP_OVER)) {
//                stepOver.actionPerformed(null);
//            } else if (action.equals(ACTION_STEP_OUT)) {
//                stepOut.actionPerformed(null);
//            }else if (action.equals(ACTION_RUN_TO_CURSOR)) {
//                runToCursor.actionPerformed(null);
//            }
//        } catch (Exception exception) {
//            logger.log(Level.INFO, "An exception was thrown when trying to execute a debugger action.", exception);
//        }
    }

    @Override
    public Set getActions() {
        return actions;
    }
}
