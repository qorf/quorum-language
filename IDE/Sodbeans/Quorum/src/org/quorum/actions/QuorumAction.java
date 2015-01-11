/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.Action;
import org.quorum.projects.QuorumProject;

/**
 *
 * @author stefika
 */
public abstract class QuorumAction implements Action{
    protected QuorumProject project;
    protected boolean enabled = true;
    private HashMap<String, Object> values = new HashMap<String, Object>();
    
    QuorumAction(QuorumProject project) {
        this.project = project;
        values.put("popupText", getDisplayName());
    }

    @Override
    public Object getValue(String key) {
        return values.get(key);
    }

    @Override
    public void putValue(String key, Object value) {
        values.put(key, value);
    }

    @Override
    public void setEnabled(boolean b) {
        enabled = b;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    protected abstract String getDisplayName();
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public quorum.Libraries.System.File getQuorumFile(File file) {
        quorum.Libraries.System.File quorumFile = new quorum.Libraries.System.File();
        quorumFile.SetWorkingDirectory(file.getParent());
        quorumFile.SetPath(file.getName());
        
        return quorumFile;
    }
}
