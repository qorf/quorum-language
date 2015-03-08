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
public class CleanBuild extends QuorumAction implements ActionListener{

    public CleanBuild(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        QuorumBuilder runner = new QuorumBuilder();
        Thread thread = new Thread(runner);
        thread.start();
    }
    
    private class QuorumBuilder implements Runnable {
        @Override
        public void run() {
            clean();
            build();
        }
    }
    
    @Override
    protected String getDisplayName() {
        return "Clean and Build";
    }
}
