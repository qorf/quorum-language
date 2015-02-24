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

import javax.swing.JEditorPane;
import javax.swing.text.Caret;
import javax.swing.text.StyledDocument;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.LineCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.text.Line;
import org.openide.text.NbDocument;
import org.openide.windows.TopComponent;
import org.quorum.debugger.QuorumBreakpoint;

/**
 * Taken largely from jean-yves Mengant's org.netbeans.modules.python.debugger.Utils
 * in the python.debugger module.
 * 
 * @author Andreas Stefik
 */
public class DebuggerUtils {

    private final static String QUORUM_FILE_EXTENSION = "quorum";

    /**
     * Returns a Line object if it is valid for this particular debugger.
     * 
     * @return
     */
    public static Line getCurrentLine() {
        Node[] nodes = TopComponent.getRegistry().getCurrentNodes();
        if (nodes == null) {
            return null;
        }
        if (nodes.length != 1) {
            return null;
        }
        Node n = nodes[0];
        FileObject fo = (FileObject) n.getLookup().lookup(FileObject.class);
        if (fo == null) {
            DataObject dobj = (DataObject) n.getLookup().lookup(DataObject.class);
            if (dobj != null) {
                fo = dobj.getPrimaryFile();
            }
        }
        if (fo == null) {
            return null;
        }
        if (!isQuorumSource(fo)) {
            return null;
        }
        LineCookie lineCookie = (LineCookie) n.getCookie(LineCookie.class);
        if (lineCookie == null) {
            return null;
        }
        EditorCookie editorCookie = (EditorCookie) n.getCookie(EditorCookie.class);
        if (editorCookie == null) {
            return null;
        }
        JEditorPane jEditorPane = getEditorPane(editorCookie);
        if (jEditorPane == null) {
            return null;
        }
        StyledDocument document = editorCookie.getDocument();
        if (document == null) {
            return null;
        }
        Caret caret = jEditorPane.getCaret();
        if (caret == null) {
            return null;
        }
        int lineNumber = NbDocument.findLineNumber(document, caret.getDot());
        try {
            Line.Set lineSet = lineCookie.getLineSet();
            assert lineSet != null : lineCookie;
            return lineSet.getCurrent(lineNumber);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public static FileObject getFileInEditor() {
        Node[] nodes = TopComponent.getRegistry().getCurrentNodes();
        if (nodes == null) {
            return null;
        }
        if (nodes.length != 1) {
            return null;
        }
        Node n = nodes[0];
        FileObject fo = (FileObject) n.getLookup().lookup(FileObject.class);
        if (fo == null) {
            DataObject dobj = (DataObject) n.getLookup().lookup(DataObject.class);
            if (dobj != null) {
                fo = dobj.getPrimaryFile();
            }
        }
        return fo;
    }

    public static QuorumBreakpoint getBreakpointAtLine() {
        Line line = getCurrentLine();
        FileObject fo = getFileInEditor();
        
        QuorumBreakpoint breakpoint = new QuorumBreakpoint(line, fo);
        return breakpoint;
    }

    /**
     * Determine whether a particular source file is that from a Quorum project.
     *
     * @param fo
     * @return
     */
    public static boolean isQuorumSource(FileObject fo) {
        if (fo.getExt().equals(QUORUM_FILE_EXTENSION)) {
            return true;
        }
        return false;
    }

    /**
     * Returns an editor pane.
     * 
     * @param editorCookie
     * @return
     */
    private static JEditorPane getEditorPane(EditorCookie editorCookie) {
        JEditorPane[] op = editorCookie.getOpenedPanes();
        if ((op == null) || (op.length < 1)) {
            return null;
        }
        return op[0];
    }
}
