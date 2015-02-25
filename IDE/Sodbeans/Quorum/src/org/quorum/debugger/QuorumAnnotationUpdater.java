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

import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.cookies.EditorCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.util.Lookup;

/**
 * This class updates annotations in the editor. This includes annotations 
 * for running the debugger and breakpoints.
 * 
 * @author Andreas Stefik
 */
public class QuorumAnnotationUpdater {
    private static final LineAnnotation annotation = new LineAnnotation();
    //private final org.sodbeans.compiler.api.Compiler compiler
    //        = Lookup.getDefault().lookup(org.sodbeans.compiler.api.Compiler.class);
    private QuorumSupport support;
    
    
    /**
     * Does the actual updating of the line in the event dispatch thread.
     *
     * @param line
     */
    private void updateLine(final Line line) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                annotation.attach(line);
            }
        });
    }

    /**
     * Removes the program counter line, indicating that the debugger has
     * stopped.
     */
    public void removeAnnotation() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                annotation.detach();
            }
        });
    }
    
    /**
     * Updates the program counter.
     */
    public void update(String fullyQualifiedClassName, int line) {
        FileObject fo = support.lookupQuorumFile(fullyQualifiedClassName);
        if (fo == null) {
            return;
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
                if(document == null) { //open the document
                    document = ck.openDocument();
                }
                
                int lineNumber = line - 1;
                if (document != null) {

                    Element e = document.getDefaultRootElement();
                    if (e != null && lineNumber != -1) {
                        e = e.getElement(lineNumber);
                        final int startOfLine = e.getStartOffset();
                        Line myLine = NbEditorUtilities.getLine(document, startOfLine, false);
                        updateLine(myLine);
                        QuorumSupport.openEditorAndJump(dataObject, lineNumber);
                    } else {
                        if (dataObject != null) {
                            QuorumSupport.openEditor(dataObject);
                        }
                    }
                } else {
                    if (dataObject != null) {
                        QuorumSupport.openEditorAndJump(dataObject, lineNumber);
                    }
                }
            }
        } catch (Exception exception) {
        }
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
}
