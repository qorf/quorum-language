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

import java.io.File;
import java.util.Iterator;
import javax.swing.JEditorPane;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import org.debugger.Breakpoint;
import org.debugger.Debugger;
import org.debugger.jdi.JDIBreakpoint;
import org.debugger.jdi.JDIClassInformation;
import org.netbeans.api.debugger.DebuggerManager;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.util.Lookup;
import quorum.Libraries.Language.Compile.Symbol.Class$Interface;
import quorum.Libraries.Language.Compile.Symbol.SymbolTable$Interface;
import quorum.Libraries.System.File$Interface;
//import org.quorum.debugger.DebuggerFactory;
//import org.sodbeans.compiler.api.descriptors.CompilerClassDescriptor;
//import org.sodbeans.compiler.api.descriptors.CompilerFileDescriptor;

/**
 *
 * This class provides a number of utility functions for working with the Quorum
 * JVM Debugger.
 *
 * @author Andreas Stefik
 */
public class QuorumSupport {
    private Debugger debugger;
    private quorum.Libraries.Language.Compile.Compiler compiler;
//    private static org.sodbeans.compiler.api.Compiler compiler
//            = Lookup.getDefault().lookup(org.sodbeans.compiler.api.Compiler.class);

    /**
     * This method adds a breakpoint to the virtual machine running a Quorum
     * program.
     *
     * @param name
     * @param targetLine
     */
    public void addLineBreakpoint(String name, int targetLine) {
        Breakpoint breakpoint = getBreakpoint(name, targetLine);
        getDebugger().add(breakpoint);
    }

    /**
     * Add a breakpoint to the debugger.
     * 
     * @param bp 
     */
    public void addLineBreakpoint(QuorumBreakpoint bp) {
        Breakpoint breakpoint = getBreakpoint(bp);
        if(breakpoint != null) {
            getDebugger().add(breakpoint);
        }
    }
    
    /**
     * This method converts a name and target into a breakpoint object that can
     * be used by the virtual machine.
     *
     * @param name
     * @param targetLine
     * @return
     */
    public Breakpoint getBreakpoint(String name, int targetLine) {
        JDIBreakpoint point = new JDIBreakpoint();
        point.setLine(targetLine);

        JDIClassInformation info = new JDIClassInformation();
        info.setClassName(name);
        point.setClassInformation(info);
        return point;
    }
    
    /**
     * This is a helper function for converting breakpoints in the IDE
     * into something the debugger understands.
     * 
     * @param bp
     * @return 
     */
    public Breakpoint getBreakpoint(QuorumBreakpoint bp) {
        JDIBreakpoint point = null;
        Line line = bp.getLine();
        FileObject fo = bp.getFileObject();
        int targetLine = line.getLineNumber() + 1;
        String name = findJVMClassName(fo, targetLine);
        if (name != null) {
            point = new JDIBreakpoint();
            point.setLine(targetLine);

            JDIClassInformation info = new JDIClassInformation();
            info.setClassName(name);
            point.setClassInformation(info);
        }
        return point;
    }

    /**
     * This method removes a breakpoint from the debugger.
     *
     * @param name
     * @param targetLine
     */
    public void removeLineBreakpoint(String name, int targetLine) {
        Breakpoint breakpoint = getBreakpoint(name, targetLine);
        getDebugger().remove(breakpoint);
    }
    
    /**
     * This method removes a breakpoint from the debugger.
     * 
     * @param bp 
     */
    public void removeLineBreakpoint(QuorumBreakpoint bp) {
        Breakpoint breakpoint = getBreakpoint(bp);
        if(breakpoint != null) {
            getDebugger().remove(breakpoint);
        }
    }

    /**
     * On each run of a program, given that new virtual machines are booted, the
     * breakpoints that are in the IDE need to be refreshed and placed into the
     * new virtual machine. This method gathers the breakpoints from the
     * NetBeans debugger manager and re-adds them into the Quorum Debugger.
     */
    public void refreshBreakpoints() {
        org.netbeans.api.debugger.Breakpoint[] breakpoints = DebuggerManager.getDebuggerManager().getBreakpoints();
        getDebugger().clearBreakpoints();
        for (int i = 0; i < breakpoints.length; i++) {
            org.netbeans.api.debugger.Breakpoint bp = breakpoints[i];
            if (bp instanceof QuorumBreakpoint) {
                QuorumBreakpoint qb = (QuorumBreakpoint) bp;
                Line line = qb.getLine();
                FileObject fo = qb.getFileObject();
                int targetLine = line.getLineNumber() + 1;
                String name = findJVMClassName(fo, targetLine);
                if (name != null) {
                    addLineBreakpoint(name, targetLine);
                }
            }
        }
    }

    /**
     * This method takes in a compiler file descriptor, a description of the
     * source file the user is writing, and finds the actual JVM class name.
     *
     * @param fileDescriptor
     * @param targetLine
     * @return
     */
    public String findJVMClassName(FileObject fo, int targetLine) {
        SymbolTable$Interface table = getCompiler().Get$Libraries$Language$Compile$Compiler$symbolTable();
        Class$Interface clazz = table.GetClassInFile(FileUtil.toFile(fo).getAbsolutePath());
        if(clazz != null) {
            return staticKeyToJVMName(clazz.GetStaticKey());
        }
        
        return null;
//        Iterator<CompilerClassDescriptor> classes = fileDescriptor.getClasses();
//        while (classes.hasNext()) {
//            CompilerClassDescriptor next = classes.next();
//            if (targetLine >= next.getLine()) {
//                return staticKeyToJVMName(next.getStaticKey());
//            }
//        }
//        return null;
    }

    /**
     * This method converts a static key from the compiler into a typical JVM
     * name.
     *
     * @param name
     * @return
     */
    public static String staticKeyToJVMName(String name) {
        String newName = name.replace('.', '/');
        if (newName.startsWith("/")) {
            return "quorum" + newName;
        }
        return "quorum/" + newName;
    }

    /**
     * This method takes a fully qualified class name and returns 
     * the name of the class.
     * 
     * @param fullyQualifiedClassName
     * @return 
     */
    private static String getClassName(String fullyQualifiedClassName) {
        int lastSlash = fullyQualifiedClassName.lastIndexOf("/");
        return fullyQualifiedClassName.substring(lastSlash + 1);
    }

    /**
     * This method converts a fully qualified dot name (e.g., quorum.Main or
     * quorum.Libraries.Containers.Array) to a static key that Quorum's
     * virtual machine understands, like .Main or Libraries.Containers.Array.
     * 
     * @param dot
     * @return 
     */
    public static String DotNameToStaticKey(String dot) {
        if (dot == null) {
            return dot;
        }
        
        String[] split = dot.split("\\.");
        if(split.length == 2) { //this class is in Quorum's default package
                                //so prepend a dot, like .Main
            return split[1];
        } else if (split.length > 2) {
            String result = "";
            for(int i = 1; i < split.length; i++) {
                result = result + split[i] + ".";
            }
            result = result.substring(0, result.length() - 1);
            return result;
        } else { //this should never happen, as the Quorum compiler
                 //cannot even generate names like this, so throw an exception
            throw new RuntimeException("Invalid dot name passed to DotNameToStaticKey:"
                    + " Name must have at least 2 items.");
        }
    }
    
    /**
     * This method looks up a Quorum class on the system.
     * 
     * @param fullyQualifiedClassName
     * @return 
     */
    public FileObject lookupQuorumFile(String staticKey) {
        String key = DotNameToStaticKey(staticKey);
        SymbolTable$Interface table = getCompiler().Get$Libraries$Language$Compile$Compiler$symbolTable();
        Class$Interface clazz = table.GetClass(key);
        File$Interface f = clazz.GetFile();
        String path = f.GetAbsolutePath();
        File file = new File(path);
        return FileUtil.toFileObject(file);
    }

    /**
     * This method jumps to the location specified by a call stack frame.
     * 
     * @param fullyQualifiedClassName
     * @param targetLine 
     */
    public void jumpToCallStackLocation(String fullyQualifiedClassName, int targetLine) {
//        if (compiler == null) {
//            return;
//        }
        FileObject fo = lookupQuorumFile(fullyQualifiedClassName);
        if (fo == null) {
            return;
        }
        DataObject dataObject = null;
        try { //if this is not the file, open it
            dataObject = DataObject.find(fo);
        } catch (DataObjectNotFoundException exception) {
        }

        if(dataObject == null) {
            return;
        }
        
        try {
            EditorCookie ck = dataObject.getCookie(EditorCookie.class);
            if (ck != null) {
                StyledDocument document = ck.getDocument();
                int lineNumber = targetLine - 1;
                if (document != null) {
                    Element e = document.getDefaultRootElement();
                    if (e != null && lineNumber != -1) {
                        jumpToLine(dataObject, lineNumber);
                    } else {
                        if (dataObject != null) {
                            openEditor(dataObject);
                        }
                    }
                } else {
                    if (dataObject != null) {
                        openEditorAndJump(dataObject, lineNumber);
                    }
                }
            }
        } catch (Exception exception) {
        }
    }

    /**
     * Forces the editor to jump to the line in question, useful for debugging.
     *
     * @param dataObj
     * @param line
     */
    public static void jumpToLine(final DataObject dataObj, final int line) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    EditorCookie ck = dataObj.getCookie(EditorCookie.class);
                    if (ck != null) {
                        //ck.openDocument();
                        JEditorPane[] p = ck.getOpenedPanes();
                        if (p.length > 0) {
                            //Need to do this since we're disabling the window system's
                            //auto focus mechanism
                            p[0].requestFocus();
                            if (dataObj != null) {
                                LineCookie lc = dataObj.getCookie(LineCookie.class);
                                if (lc == null) {
                                    return;
                                }
                                Line l = lc.getLineSet().getOriginal(line);
                                l.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS);
                            }
                        }
                    }
                } catch (Exception exception) {
                }
            }
        });
    }

    /**
     * This method opens the editor.
     * 
     * @param dataObj 
     */
    public static void openEditor(DataObject dataObj) {
        final EditorCookie.Observable ec = dataObj.getCookie(EditorCookie.Observable.class);
        if (ec != null) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ec.open();
                }
            });
        }
    }

    /**
     * This method finds a line in an editor and returns it.
     */
    public String getLineInEditor(String fullyQualifiedClassName, int line) {
//        if (compiler == null) {
//            return "";
//        }
        FileObject fo = lookupQuorumFile(fullyQualifiedClassName);
        if (fo == null) {
            return "";
        }
        DataObject dataObject = null;

        try { //if this is not the file, open it
            dataObject = DataObject.find(fo);
        } catch (DataObjectNotFoundException exception) {
        }

        try {
            EditorCookie ck = dataObject.getCookie(EditorCookie.class);
            if (ck != null) {
                StyledDocument document = ck.getDocument();
                int lineNumber = line - 1;
                if (document != null) {

                    Element e = document.getDefaultRootElement();
                    if (e != null && lineNumber != -1) {
                        e = e.getElement(lineNumber);
                        final int startOfLine = e.getStartOffset();
                        Line myLine = NbEditorUtilities.getLine(document, startOfLine, false);
                        return myLine.getText();
                    }
                }
            }
        } catch (Exception exception) {
        }
        return "";
    }
    
    /**
     * This method opens the editor and jumps to the location in the file.
     * 
     * @param dataObj
     * @param line 
     */
    public static void openEditorAndJump(final DataObject dataObj, final int line) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    EditorCookie ck = dataObj.getCookie(EditorCookie.class);
                    final EditorCookie.Observable ec = dataObj.getCookie(EditorCookie.Observable.class);
                    if (ck != null && ec != null) {
                        ec.open();
                        //open the document
                        JEditorPane[] p = ck.getOpenedPanes();
                        if (p.length > 0) {
                            //Need to do this since we're disabling the window system's
                            //auto focus mechanism
                            p[0].requestFocus();
                            if (dataObj != null) {
                                LineCookie lc = dataObj.getCookie(LineCookie.class);
                                if (lc == null) {
                                    return;
                                }
                                Line l = lc.getLineSet().getOriginal(line);
                                l.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS);
                            }
                        }
                    }
                } catch (Exception exception) {
                }
            }
        });
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
    }
}
