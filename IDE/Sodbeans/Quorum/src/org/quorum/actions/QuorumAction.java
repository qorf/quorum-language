/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.swing.Action;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Cancellable;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.RequestProcessor;
import org.quorum.projects.QuorumProject;
import quorum.Libraries.Containers.Array$Interface;

/**
 *
 * @author stefika
 */
public abstract class QuorumAction implements Action{
    protected QuorumProject project;
    protected boolean enabled = true;
    private HashMap<String, Object> values = new HashMap<String, Object>();
    private Process process = null;
    
    QuorumAction(QuorumProject project) {
        this.project = project;
        values.put("popupText", getDisplayName());
    }

    public void clean() {
        Lookup lookup = project.getLookup();
        FileObject projectDirectory = project.getProjectDirectory();
        FileObject build = projectDirectory.getFileObject(QuorumProject.BUILD_DIRECTORY);
        FileObject run = projectDirectory.getFileObject(QuorumProject.DISTRIBUTION_DIRECTORY);
        
        try {
            build.delete();
            run.delete();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    public void build() {
        Lookup lookup = project.getLookup();
        quorum.Libraries.Language.Compile.Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);
        FileObject projectDirectory = project.getProjectDirectory();
        File directory = FileUtil.toFile(projectDirectory);
        
        File file = new File(directory.getAbsolutePath() + "/" + QuorumProject.SOURCES_DIR);
        quorum.Libraries.System.File quorumFile = getQuorumFile(file);
        Array$Interface listing = quorumFile.GetDirectoryListing();
        compiler.Empty();
        quorum.Libraries.System.File f = (quorum.Libraries.System.File)listing.Get(0);
        compiler.SetMain(f);
        compiler.Compile(listing);
    }
    
    public void debugContinue() {
        
    }
    
    public void debug() {
        
    }
    
    public void run() {
        // Create a new "task" to be run by the process API. This task does
        // the following:
        //
        // 1. Spawns a new 'java' process.
        // 2. Spawns two threads to watch the output streams of this 'java' process (stdout and stderr).
        // 3. Waits for the 'java' process to exit (successfully or unsuccessfully). When the process exits, the thread terminates.
        //
        // This spawned process will catch the InterruptedException exception. When it does, the thread will terminate.
        QuorumRunnable runner = new QuorumRunnable();
        runner.taskName = project.toString();
        RequestProcessor requestProcessor = new RequestProcessor(runner.taskName, 1, true);
        RequestProcessor.Task processTask = requestProcessor.create(runner);
        processTask.schedule(0);
    }
    
    public class QuorumRunnable implements Runnable {
        public String taskName = "";
        
        @Override
        public void run() {
            //final Thread currentThread = Thread.currentThread();
            final ProgressHandle progress = ProgressHandleFactory.createHandle(taskName, new Cancellable() {
            public boolean cancel() {
                //currentThread.interrupt();
                if(process != null) {
                    process.destroy();
                }
                return true;
            }
            });
            try {
                progress.start();

                // Compute the location of the project's root directory.
                File projectDirectory = new File(project.getProjectDirectory().getPath());

                // Spawn a new Java process that will run "Default.jar" from the project directory.
                ProcessBuilder builder = new ProcessBuilder("java", "-Dsodbeans=1", "-jar", "Run/Default.jar");
                builder.directory(projectDirectory);

                // Start the process.
                process = builder.start();

                // Spawn a new thread to run QuorumRunner in.
    //            stdoutWatcher = new QuorumWatcher(process.getInputStream());
    //            stderrWatcher = new QuorumWatcher(process.getErrorStream());
    //            stdoutWatcher.start();
    //            stderrWatcher.start();

                // Wait for the process to exit.
                process.waitFor();
            } catch (InterruptedException ex) {
                // thread interrupt indicates cancelling of task
            } catch (IOException ex) {
                // error spawning Quorum process
            } finally {
                process.destroy();

    //            if (stdoutWatcher != null)
    //                stdoutWatcher.close();
    //
    //            if (stderrWatcher != null)
    //                stderrWatcher.close();
                progress.finish();
            }
        }
        
    }
    public void runToCursor() {
        
    }
    
    public void stepInto() {
        
    }
    
    public void stepOut() {
        
    }
    
    public void stepOver() {
        
    }
    
    public void stop() {
        
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
    
    /**
     * This private class watches the process running in the debugger and 
     * outputs any information it dumps to standard out to the console.
     */
    private class QuorumProcessWatcher implements Runnable {
        private BufferedReader bufferedReader = null;
        private Thread blinker = null;
        private boolean running = false;
        public ProgressHandle progress;
        
        public QuorumProcessWatcher(InputStream in) {
            bufferedReader = new BufferedReader(new InputStreamReader(in));
        }

        public void start() {
            if (!running) {
                blinker = new Thread(this);
                blinker.setDaemon(true);
                blinker.setName("Quorum Process Watcher");
                blinker.start();
            }
        }

        public void run() {
            Thread thisThread = Thread.currentThread();
            running = true;
            // Watch the input stream, send its output to the console.
            while (thisThread == blinker) {
                try {
                    String line = bufferedReader.readLine();
                    // If the line is null, the end of the input has been reached.
                    if (line == null) {
                        return;
                    }
                    //console.post(line);
                    Thread.sleep(0);
                } catch (IOException ex) {
                    return;
                } catch (InterruptedException ex) {
                    return;
                }
            }
            if(progress != null) {
                progress.finish();
            }
        }
    }
    
    class MyCancel implements Cancellable {
        public ProgressHandle progress;
        public boolean cancel() {
            //currentThread.interrupt();
            if(progress != null) {
                progress.finish();
            }
            return true;
        }
    };
}
