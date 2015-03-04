/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Exceptions;
import org.quorum.projects.QuorumProject;

/**
 *
 * @author stefika
 */
public class Run extends QuorumAction implements ActionListener{

    public Run(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        build();
        //run();
        MyCancel cancel = new MyCancel();
        String taskName = project.getProjectDirectory().getName() + " (run)";
        
        final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
        cancel.progress = progress;
        
        // Compute the location of the project's root directory.
        File runDirectory = project.getRunDirectory();

        // Spawn a new Java process that will run "Default.jar" from the project directory.
        ProcessBuilder builder = new ProcessBuilder("java", "-Dsodbeans=1", "-jar", project.getExecutableName());
        builder.directory(runDirectory);

        // Start the process.
        Process process;
        try {
            process = builder.start();
            QuorumProcessWatcher watch = new QuorumProcessWatcher(process.getInputStream());
            watch.start();
            cancel.watcher = watch;
            progress.start();
            process.waitFor();
            process.destroy();
            progress.finish();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        
    }
    
    @Override
    protected String getDisplayName() {
        return "Run";
    }
}
