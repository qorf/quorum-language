/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.windows.nodes;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.quorum.debugger.QuorumSupport;

/**
 *
 * @author stefika
 */
public class JumpToEditorAction implements Action{
    public int line;
    public FileObject file;
    
    @Override
    public Object getValue(String key) {
        return null;
    }

    @Override
    public void putValue(String key, Object value) {
    }

    @Override
    public void setEnabled(boolean b) {
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataObject find = DataObject.find (file);
            QuorumSupport.openEditorAndJump(find, line - 1);
        } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
