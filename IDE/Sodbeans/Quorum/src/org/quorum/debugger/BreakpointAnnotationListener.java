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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import org.debugger.Debugger;
import org.netbeans.api.debugger.Breakpoint;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.api.debugger.DebuggerManagerAdapter;
import org.netbeans.spi.debugger.ui.BreakpointAnnotation;
import org.openide.filesystems.FileObject;
import org.openide.text.Line;
import org.openide.util.Lookup;
import org.quorum.debugger.DebuggerFactory;
//import org.sodbeans.compiler.api.descriptors.CompilerFileDescriptor;
//import org.sodbeans.phonemic.TextToSpeechFactory;
//import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * In Progress.
 *
 * @author Andreas Stefik
 */
public class BreakpointAnnotationListener extends DebuggerManagerAdapter
        implements PropertyChangeListener {

    //TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    private HashMap<QuorumBreakpoint, QuorumBreakpointAnnotation> breakpointToAnnotation = new HashMap<QuorumBreakpoint, QuorumBreakpointAnnotation>();
    //private org.sodbeans.compiler.api.Compiler compiler =
    //        Lookup.getDefault().lookup(org.sodbeans.compiler.api.Compiler.class);
    private Debugger debugger = DebuggerFactory.getQuorumDebugger();
    
    @Override
    public String[] getProperties() {
        return new String[]{DebuggerManager.PROP_BREAKPOINTS};
    }

    /**
     * Called when some breakpoint is added.
     *
     * @param b breakpoint
     */
    @Override
    public void breakpointAdded(Breakpoint b) {
        if (!(b instanceof QuorumBreakpoint)) {
            return;
        }

        QuorumBreakpoint hb = (QuorumBreakpoint) b;
        addAnnotation(hb);
        FileObject fo = hb.getFileObject();
        Line line = hb.getLine();

        // Add the new breakpoint to the debugger implementation
//        if (compiler != null) {
//            CompilerFileDescriptor fileDescriptor = compiler.getFileDescriptor(fo);
//            int targetLine = line.getLineNumber() + 1;
//            String name = QuorumSupport.findJVMClassName(fileDescriptor, targetLine);
//            if (name != null) {
//                QuorumSupport.addLineBreakpoint(name, targetLine);
//            }
//        }
    }

    /**
     * Called when some breakpoint is removed.
     *
     * @param breakpoint
     */
    @Override
    public void breakpointRemoved(Breakpoint b) {
        if (!(b instanceof QuorumBreakpoint)) {
            return;
        }

        QuorumBreakpoint hb = (QuorumBreakpoint) b;
        removeAnnotation(hb);
        Line line = hb.getLine();
        FileObject fo = hb.getFileObject();
        
        // Remove the breakpoint in TOD.
//        if (compiler != null) {
//            CompilerFileDescriptor fileDescriptor = compiler.getFileDescriptor(fo);
//            int targetLine = line.getLineNumber() + 1;
//            String name = QuorumSupport.findJVMClassName(fileDescriptor, targetLine);
//            if (name != null) {
//                QuorumSupport.removeLineBreakpoint(name, targetLine);
//            }
//        }
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source and
     * the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() != Breakpoint.PROP_ENABLED) {
            return;
        }

        removeAnnotation((QuorumBreakpoint) evt.getSource());
        addAnnotation((QuorumBreakpoint) evt.getSource());
    }

    private void addAnnotation(QuorumBreakpoint b) {
        breakpointToAnnotation.put(b, new QuorumBreakpointAnnotation(b.getLine(), b));
        b.addPropertyChangeListener(
                Breakpoint.PROP_ENABLED,
                this);
    }

    private void removeAnnotation(QuorumBreakpoint b) {
        BreakpointAnnotation annotation = breakpointToAnnotation.remove(b);
        if (annotation == null) {
            return;
        }

        annotation.detach();
        b.removePropertyChangeListener(
                Breakpoint.PROP_ENABLED,
                this);
    }
}
