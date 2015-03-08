/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.actions;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Cancellable;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.RequestProcessor;
import org.openide.windows.*;
import org.quorum.projects.QuorumProject;
import org.quorum.windows.CompilerErrorTopComponent;
import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.Containers.Blueprints.Iterator$Interface;
import quorum.Libraries.Language.Compile.CompilerErrorManager$Interface;

/**
 *
 * @author stefika
 */
public abstract class QuorumAction implements Action {

    protected QuorumProject project;
    protected boolean enabled = true;
    private HashMap<String, Object> values = new HashMap<String, Object>();
    private Process process = null;
    InputOutput io;

    QuorumAction(QuorumProject project) {
        this.project = project;
        values.put("popupText", getDisplayName());
        io = IOProvider.getDefault().getIO(project.getProjectDirectory().getName(), false);
    }

    public void clean() {
        FileObject projectDirectory = project.getProjectDirectory();
        FileObject build = projectDirectory.getFileObject(QuorumProject.BUILD_DIRECTORY);
        FileObject run = projectDirectory.getFileObject(QuorumProject.DISTRIBUTION_DIRECTORY);

        try {
            if (build != null && build.isValid()) {
                build.delete();
            }
            if (build != null && run.isValid()) {
                run.delete();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    /**
     * This method builds a program. It returns true if the build was
     * successful.
     *
     * @return
     */
    public boolean build() {
        Lookup lookup = project.getLookup();
        final quorum.Libraries.Language.Compile.Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);
        FileObject projectDirectory = project.getProjectDirectory();
        File directory = FileUtil.toFile(projectDirectory);

        File file = new File(directory.getAbsolutePath() + "/" + QuorumProject.SOURCES_DIR);
        quorum.Libraries.System.File quorumFile = getQuorumFile(file);
        Array$Interface listing = quorumFile.GetDirectoryListing();
        compiler.Empty();
        //quorum.Libraries.System.File f = (quorum.Libraries.System.File)listing.Get(0);
        //compiler.SetMain(f);
        compiler.Compile(listing);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CompilerErrorTopComponent errors = (CompilerErrorTopComponent) WindowManager.getDefault().findTopComponent("CompilerErrorTopComponent");
                if (!compiler.IsCompilationErrorFree()) {
                    CompilerErrorManager$Interface manager = compiler.GetCompilerErrorManager();
                    errors.resetErrors(manager);

                    boolean open = errors.isOpened();
                    if (open) {
                        errors.requestActive();
                    } else {
                        errors.open();
                        errors.requestActive();
                    }
                } else {
                    errors.clear();
                    io.select();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    Date date = new Date();
                    System.out.println();

                    io.getOut().println("Build Successful at " + dateFormat.format(date) + ".");
                    io.setInputVisible(true);
                    io.getOut().close();
                }
            }
        });
        return compiler.IsCompilationErrorFree();
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
                    if (process != null) {
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
    protected class QuorumProcessWatcher implements Runnable {

        private BufferedReader bufferedReader = null;
        private OutputStream stream;
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        private Thread blinker = null;
        public boolean running = false;
        public boolean cancelled = false;
        public boolean wasDestroyed = false;

        public QuorumProcessWatcher(InputStream in) {
            bufferedReader = new BufferedReader(new InputStreamReader(in));
        }

        public void start() {
            if (!running) {
                blinker = new Thread(this);
                blinker.setName("Quorum Process Watcher");
                blinker.start();
            }
        }

        /**
         * This method is used to flush only if a process was destroyed before
         * fully flushing the output stream.
         */
        public void flush() {
            try {
                while (bufferedReader.ready()) {
                    final String line = bufferedReader.readLine();
                    // If the line is null, the end of the input has been reached.
                    if (line == null) {
                        io.getOut().flush();
                        io.getOut().close();
                        return;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            io.getOut().println(line);
                            io.getOut().flush();
                        }
                    });
                }
                io.getOut().close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        @Override
        public void run() {
            Thread thisThread = Thread.currentThread();
            running = true;

            //io.setInputVisible(true);
            //BufferedReader inputLineReader = new BufferedReader(io.getIn());
            // Watch the input stream, send its output to the console.
            while (!cancelled) {
                try {

                    if (bufferedReader.ready()) {
                        final String line = bufferedReader.readLine();
                        // If the line is null, the end of the input has been reached.
                        if (line == null) {
                            io.getOut().flush();
                            io.getOut().close();
                            return;
                        }
                        SwingUtilities.invokeAndWait(new Runnable() {

                            @Override
                            public void run() {
                                io.getOut().println(line);
                                io.getOut().flush();
                            }
                        });
                    }
                    Thread.sleep(0);
                } catch (IOException ex) {
                    running = false;
                } catch (InterruptedException ex) {
                    running = false;
                } catch (InvocationTargetException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

            io.getOut().close();
            //io.setInputVisible(false);
        }

        /**
         * @return the stream
         */
        public OutputStream getStream() {
            return stream;
        }

        /**
         * @param stream the stream to set
         */
        public void setStream(OutputStream stream) {
            this.stream = stream;
            outputStreamWriter = new OutputStreamWriter(stream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        }
    }

    class MyCancel implements Cancellable {

        public ProgressHandle progress;
        public QuorumProcessWatcher watcher;
        public Process process;

        public boolean cancel() {
            //currentThread.interrupt();
            if (progress != null) {
                progress.finish();
            }
            if (watcher != null) {
                watcher.running = false;
                watcher.cancelled = true;
            }
            if (process != null) {
                process.destroy();
            }
            return true;
        }
    };
}
