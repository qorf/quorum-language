/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import java.io.File;
import org.netbeans.api.debugger.Breakpoint;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.text.Line;

/**
 *
 * @author Andreas Stefik
 */
public class QuorumBreakpoint extends Breakpoint{
    private Line line;
    private FileObject file;

    public QuorumBreakpoint(Line line, FileObject fo) {
        this.line = line;
        file = fo;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void disable() {
    }

    @Override
    public void enable() {
    }
    
    /**
     * Returns a representation of what line this breakpoint is on.
     *
     * @return
     */
    public Line getLine() {
        return line;
    }

    /**
     * Returns a representation of what file this breakpoint is in.
     * 
     * @return
     */
    public FileObject getFileObject() {
        return file;
    }
    
    public String toString() {
        if(file != null && line != null) {
            File f = FileUtil.toFile(file);
            return f.getName() + ":" + (line.getLineNumber() + 1);
        }
        return "";
    }
}
