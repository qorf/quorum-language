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
public class Clean extends QuorumAction implements ActionListener{

    public Clean(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        QuorumCleaner runner = new QuorumCleaner();
        Thread thread = new Thread(runner);
        thread.start();
    }
    
    private class QuorumCleaner implements Runnable {
        @Override
        public void run() {
            ProcessCancel cancel = new ProcessCancel();
            String taskName = project.getProjectDirectory().getName() + " (clean)";

            final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
            progress.start();
            clean();
            progress.finish();
        }
    }
    
    @Override
    protected String getDisplayName() {
        return "Clean";
    }
}
