/*
 Copyright (c) 2013, Andreas Stefik and Matt Pedersen
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

import org.debugger.Debugger;
import org.debugger.DebuggerListener;
import org.debugger.events.DebuggerBreakpointEvent;
import org.debugger.events.DebuggerExceptionEvent;
import org.debugger.events.DebuggerLocationEvent;
import org.debugger.events.DebuggerStartEvent;
import org.debugger.events.DebuggerStepEvent;
import org.debugger.events.DebuggerStopEvent;
import org.openide.util.Cancellable;
import org.openide.util.Lookup;
//import org.sodbeans.actions.SodbeansStopDebuggerAction;
//import org.sodbeans.io.CommandLine;
//import org.sodbeans.phonemic.SpeechPriority;
//import org.sodbeans.phonemic.TextToSpeechFactory;
//import org.sodbeans.phonemic.tts.TextToSpeech;
//import org.sodbeans.tts.options.api.TextToSpeechOptions;

/**
 * This class listens to events from the debugger architecture.
 * 
 * @author Andreas Stefik
 */
public class QuorumDebuggerListener implements DebuggerListener{
    private QuorumDebuggerEngineProvider engine = null;
    private QuorumSupport support = new QuorumSupport();
    private quorum.Libraries.Language.Compile.Compiler compiler;
    private Debugger debugger;
    private QuorumDebugger quorumDebugger;
    private Cancellable cancel;
//    private SodbeansStopDebuggerAction kill = null;
    private QuorumAnnotationUpdater annotationUpdater = null;
//    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
//    private CommandLine console = Lookup.getDefault().lookup(CommandLine.class);
    
    @Override
    public void accept(DebuggerStartEvent event) {
        getSupport().refreshBreakpoints();
    }

    @Override
    public void accept(DebuggerStopEvent event) {
        stop();
    }

    @Override
    public void accept(DebuggerStepEvent event) {
        String fullyQualifiedClassName = event.getSource();
        int lineNumber = event.getLine();
        annotationUpdater.update(fullyQualifiedClassName, lineNumber);
        QuorumVariablesModel.update();
        QuorumCallStack.update();
        QuorumWatchModel.update();
        
        String message = "";
        message = "" + getLocationInformation(event);
//        if (TextToSpeechOptions.isScreenReading()) {
//            speech.speak(message, SpeechPriority.HIGHEST);
//        }
    }

    @Override
    public void accept(DebuggerBreakpointEvent event) {
        String fullyQualifiedClassName = event.getSource();
        int lineNumber = event.getLine();
        annotationUpdater.update(fullyQualifiedClassName, lineNumber);
        QuorumVariablesModel.update();
        QuorumCallStack.update();
        QuorumWatchModel.update();
        
        String message = "";
        message = "Breakpoint hit at line " + getLocationInformation(event);
//        if (TextToSpeechOptions.isScreenReading()) {
//            speech.speak(message, SpeechPriority.HIGHEST);
//        }
    }
    
    @Override
    public void accept(DebuggerExceptionEvent event) {
        String message = "";
        message = "Exception thrown with message: " + event.getMessage();
//        console.post(message, true);
//        if (TextToSpeechOptions.isScreenReading()) {
//            speech.speak(message, SpeechPriority.HIGHEST);
//        }
    }
    
    /**
     * Converts the source information from the debugger into reasonable
     * text to speech.
     * 
     * @param event
     * @return 
     */
    private String getLocationInformation(DebuggerLocationEvent event) {
        String message = "";
        if(event == null) {
            return "";
        }
        
        String quorum = "quorum/";
        int line = event.getLine();
        String source = event.getSource();
        
//        String text = QuorumSupport.getLineInEditor(source, line);
//        if(source.startsWith(quorum)) {
//            source = source.substring(quorum.length());
//        }
//        
//        return text + ", " + line + " " + source;
        return "";
    }

    private void stop() {
        debugger.stop();
        engine.getDestructor().killEngine();
        cancel.cancel();
        annotationUpdater.removeAnnotation();
        QuorumBreakpointActionProvider.removeListener(quorumDebugger);
    }
    
    @Override
    public String getName() {
        return "Quorum Listener";
    }

    /**
     * @return the engine
     */
    public QuorumDebuggerEngineProvider getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(QuorumDebuggerEngineProvider engine) {
        this.engine = engine;
    }

    /**
     * @return the compiler
     */
    public quorum.Libraries.Language.Compile.Compiler getCompiler() {
        return compiler;
    }

    /**
     * @param compiler the compiler to set
     */
    public void setCompiler(quorum.Libraries.Language.Compile.Compiler compiler) {
        this.compiler = compiler;
        getSupport().setCompiler(compiler);
    }

    /**
     * @return the debugger
     */
    public Debugger getDebugger() {
        return debugger;
    }

    /**
     * @param debugger the debugger to set
     */
    public void setDebugger(Debugger debugger) {
        this.debugger = debugger;
        getSupport().setDebugger(debugger);
    }

    /**
     * @return the cancel
     */
    public Cancellable getCancel() {
        return cancel;
    }

    /**
     * @param cancel the cancel to set
     */
    public void setCancel(Cancellable cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the support
     */
    public QuorumSupport getSupport() {
        return support;
    }

    /**
     * @param support the support to set
     */
    public void setSupport(QuorumSupport support) {
        this.support = support;
    }

//    /**
//     * @return the kill
//     */
//    public SodbeansStopDebuggerAction getKill() {
//        return kill;
//    }
//
//    /**
//     * @param kill the kill to set
//     */
//    public void setKill(SodbeansStopDebuggerAction kill) {
//        this.kill = kill;
//    }

    /**
     * @return the annotationProvider
     */
    public QuorumAnnotationUpdater getAnnotationUpdater() {
        return annotationUpdater;
    }

    /**
     * @param annotationProvider the annotationProvider to set
     */
    public void setAnnotationUpdater(QuorumAnnotationUpdater annotationProvider) {
        this.annotationUpdater = annotationProvider;
        this.annotationUpdater.setSupport(support);
    }

    /**
     * @return the quorumDebugger
     */
    public QuorumDebugger getQuorumDebugger() {
        return quorumDebugger;
    }

    /**
     * @param quorumDebugger the quorumDebugger to set
     */
    public void setQuorumDebugger(QuorumDebugger quorumDebugger) {
        this.quorumDebugger = quorumDebugger;
    }
}
