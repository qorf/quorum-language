/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.quorum.projects.QuorumProject;

/**
 *
 * @author stefika
 */
public class Pause extends QuorumAction implements ActionListener{

    public Pause(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    @Override
    protected String getDisplayName() {
        return "Pause";
    }
}
