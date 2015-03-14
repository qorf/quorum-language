/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.quorum.projects.QuorumProject;

/**
 *
 * @author stefika
 */
public class Build extends QuorumAction implements ActionListener{

    public Build(QuorumProject project) {
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
            ProcessCancel cancel = new ProcessCancel();
            String taskName = project.getProjectDirectory().getName() + " (build)";

            final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
            progress.start();
            build();
            progress.finish();
        }
    }
    
    @Override
    protected String getDisplayName() {
        return "Build";
    }
}
