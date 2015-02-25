/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.debugger;

import java.util.Collections;
import java.util.Set;
import org.netbeans.api.debugger.ActionsManager;
import org.netbeans.api.debugger.Breakpoint;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.spi.debugger.ActionsProviderSupport;
import org.openide.filesystems.FileObject;
import org.openide.text.Line;
//import org.openide.util.Lookup;
//import org.sodbeans.compiler.api.descriptors.CompilerClassDescriptor;
//import org.sodbeans.compiler.api.descriptors.CompilerFileDescriptor;
//import org.sodbeans.phonemic.SpeechPriority;
//import org.sodbeans.phonemic.TextToSpeechFactory;
//import org.sodbeans.phonemic.tts.TextToSpeech;
//import org.sodbeans.tod.QuorumDebuggerUtils;
//import org.sodbeans.tts.options.api.TextToSpeechOptions;
//import org.tod.TODSessionFactory;
//import org.tod.TODUtils;

/**
 * This class provides actions for breakpoints.
 *
 * @author Andreas Stefik
 */
public class QuorumBreakpointActionProvider extends ActionsProviderSupport {

    //private org.sodbeans.compiler.api.Compiler compiler =
    //        Lookup.getDefault().lookup(org.sodbeans.compiler.api.Compiler.class);
    private final static Set ACTIONS = Collections.singleton(
            ActionsManager.ACTION_TOGGLE_BREAKPOINT);
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

                for (i = 0; i < k; i++) {
                    if (breakpoints[i] instanceof QuorumBreakpoint
                            && (((QuorumBreakpoint) breakpoints[i]).getLine() != null)
                            && ((QuorumBreakpoint) breakpoints[i]).getLine().equals(line)) {
                        //Remove a breakpoint
                        QuorumBreakpoint bp = (QuorumBreakpoint)breakpoints[i];
//                        if (!TODUtils.isTODEnabled()) {
////                            if (compiler != null) {
////                                compiler.toggleBreakpoint(bp.getLine().getLineNumber() + 1, bp.getFileObject());
////                            }
//                        } else {
//                            if (TextToSpeechOptions.isScreenReading()) {
//                                speech.speak("Removed breakpoint at line " + (line.getLineNumber() + 1) + " in " + fo.getNameExt(), SpeechPriority.MEDIUM);
//                            }
                            DebuggerManager.getDebuggerManager().removeBreakpoint(bp);
//                        }
//
//                        break;
                    }
                }
                if (i == k) { //add a breakpoint
                    QuorumBreakpoint bp = DebuggerUtils.getBreakpointAtLine();
//                    if (!TODUtils.isTODEnabled()) {
////                        if (compiler != null) {
////                            compiler.toggleBreakpoint(bp.getLine().getLineNumber() + 1, bp.getFileObject());
////                        }
//                    } else {
//                        if (TextToSpeechOptions.isScreenReading()) {
//                            speech.speak("Added Breakpoint at line " + (line.getLineNumber() + 1) + " in " + fo.getNameExt(), SpeechPriority.MEDIUM);
//                        }
                        DebuggerManager.getDebuggerManager().addBreakpoint(bp);
//                    }
                }
            }
        });

    }

    @Override
    public Set getActions() {
        return ACTIONS;
    }
}