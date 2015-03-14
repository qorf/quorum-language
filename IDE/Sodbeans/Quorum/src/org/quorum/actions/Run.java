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
import java.io.OutputStream;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Exceptions;
import org.quorum.projects.QuorumProject;

/**
 *
 * @author stefika
 */
public class Run extends QuorumAction implements ActionListener {
    private boolean isRunning = false;
    public Run(QuorumProject project) {
        super(project);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!isRunning) {
            QuorumRunner runner = new QuorumRunner();
            Thread thread = new Thread(runner);
            thread.start();
        }
    }
    
    private class QuorumRunner implements Runnable {

        @Override
        public void run() {
            isRunning = true;
            ProcessCancel cancel = new ProcessCancel();
            String taskName = project.getProjectDirectory().getName() + " (run)";

            final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, cancel);
            cancel.progress = progress;
            progress.start();
            boolean success = build();
            if(!success) {
                progress.finish();
                return;
            }
            

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
                OutputStream outputStream = process.getOutputStream();
                watch.setStream(outputStream);
                watch.start();
                cancel.process = process;
                cancel.watcher = watch;
                process.waitFor();
                watch.wasDestroyed = true;
                watch.cancelled = true;
                watch.flush();
                process.destroy();
                progress.finish();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
            isRunning = false;
        }
    }
    @Override
    protected String getDisplayName() {
        return "Run";
    }
}
