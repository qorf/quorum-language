/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.netbeans.api.debugger.ActionsManager;
import org.netbeans.api.debugger.Breakpoint;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.spi.debugger.ActionsProviderSupport;
import org.openide.filesystems.FileObject;
import org.openide.text.Line;

/**
 * This class provides actions for breakpoints.
 *
 * @author Andreas Stefik
 */
public class QuorumBreakpointActionProvider extends ActionsProviderSupport  {
    private final static Set ACTIONS = Collections.singleton(
            ActionsManager.ACTION_TOGGLE_BREAKPOINT);
    private static ArrayList<BreakpointListener> listeners = new ArrayList<BreakpointListener>();
    
    public static void addListener(BreakpointListener listener) {
        listeners.add(listener);
    }
    
    public static void removeListener(BreakpointListener listener) {
        listeners.remove(listener);
    }
    
//    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();

    public QuorumBreakpointActionProvider() {
        setEnabled(ActionsManager.ACTION_TOGGLE_BREAKPOINT, true);
    }

    @Override
    public void doAction(Object action) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileObject fo = DebuggerUtils.getFileInEditor();
                boolean isQuorum = DebuggerUtils.isQuorumSource(fo);
                if (!isQuorum) {
                    return;
                }

                Line line = DebuggerUtils.getCurrentLine();

                Breakpoint[] breakpoints = DebuggerManager.getDebuggerManager().getBreakpoints();
                int i, k = breakpoints.length;

                //remove any breakpoints that are necessary.
                for (i = 0; i < k; i++) {
                    if (breakpoints[i] instanceof QuorumBreakpoint
                            && (((QuorumBreakpoint) breakpoints[i]).getLine() != null)
                            && ((QuorumBreakpoint) breakpoints[i]).getLine().equals(line)) {
                        
                        QuorumBreakpoint bp = (QuorumBreakpoint)breakpoints[i];
//                            if (TextToSpeechOptions.isScreenReading()) {
//                                speech.speak("Removed breakpoint at line " + (line.getLineNumber() + 1) + " in " + fo.getNameExt(), SpeechPriority.MEDIUM);
//                            }
                        DebuggerManager.getDebuggerManager().removeBreakpoint(bp);
                        Iterator<BreakpointListener> iterator = listeners.iterator();
                        while(iterator.hasNext()) {
                            BreakpointListener next = iterator.next();
                            next.removeLineBreakpoint(bp);
                        }
                        break;
                    }
                }
                
                //if we need to, add a breakpoint
                if (i == k) { //add a breakpoint
                    QuorumBreakpoint bp = DebuggerUtils.getBreakpointAtLine();
//                        if (TextToSpeechOptions.isScreenReading()) {
//                            speech.speak("Added Breakpoint at line " + (line.getLineNumber() + 1) + " in " + fo.getNameExt(), SpeechPriority.MEDIUM);
//                        }
                    DebuggerManager.getDebuggerManager().addBreakpoint(bp);
                    Iterator<BreakpointListener> iterator = listeners.iterator();
                    while(iterator.hasNext()) {
                        BreakpointListener next = iterator.next();
                        next.addLineBreakpoint(bp);
                    }
                }
            }
        });

    }

    @Override
    public Set getActions() {
        return ACTIONS;
    }
}