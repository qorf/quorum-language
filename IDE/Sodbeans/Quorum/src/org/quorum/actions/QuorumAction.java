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
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Cancellable;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.windows.*;
import org.quorum.debugger.QuorumDebugger;
import org.quorum.projects.ImageSheetManager;
import org.quorum.projects.QuorumProject;
import org.quorum.windows.CompilerErrorTopComponent;
import quorum.Libraries.Containers.Array$Interface;
import quorum.Libraries.Containers.Blueprints.Iterator$Interface;
import quorum.Libraries.Language.Compile.CompilerErrorManager$Interface;
import quorum.Libraries.Language.Object$Interface;

/**
 *
 * @author stefika
 */
public abstract class QuorumAction implements Action {
    private boolean logExceptionsToConsoleOutput = true; 
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

    public synchronized void clean() {
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
    public synchronized boolean build() {
        Lookup lookup = project.getLookup();
        final quorum.Libraries.Language.Compile.Compiler compiler = lookup.lookup(quorum.Libraries.Language.Compile.Compiler.class);
        FileObject projectDirectory = project.getProjectDirectory();
        
        ImageSheetManager imageSheetManager = project.getImageSheetManager();
        //if image sheet support is disabled, don't bother going further.
        if(imageSheetManager.isEnableImageSheetSupport()) {
            String message = "Automatically building the following image sheets: ";
            Iterator<String> it = imageSheetManager.getImageSheetIterator();
            while(it.hasNext()) {
                String next = it.next();
                if(it.hasNext()) {
                    message = message + next + ", ";
                } else {
                    message = message + next + ".";
                }
                
            }
            io.getOut().println(message);
            long start = System.currentTimeMillis();
            imageSheetManager.buildAllImageSheets(FileUtil.toFile(projectDirectory));
            long finish = System.currentTimeMillis();
            double total = (finish - start);
            total = total / 1000.0;
            io.getOut().println("Image sheets complete in " + total + " seconds.");
        }
        File directory = FileUtil.toFile(projectDirectory);

        File file = new File(directory.getAbsolutePath() + "/" + QuorumProject.SOURCES_DIR);
        quorum.Libraries.System.File quorumFile = getQuorumFile(file);
        Array$Interface listing = quorumFile.GetDirectoryListing();
        
        
        Iterator$Interface it = listing.GetIterator();
        while(it.HasNext()) {
            quorum.Libraries.System.File$Interface next = (quorum.Libraries.System.File$Interface) it.Next();
            FileObject fo = org.quorum.support.Utility.toFileObject(next);
            try {
                DataObject dataObj = DataObject.find(fo);
                if (dataObj != null) {
                    SaveCookie cookie = dataObj.getLookup().lookup(SaveCookie.class);
                    if (cookie != null) {
                        cookie.save();
                    }
                }
            } catch (DataObjectNotFoundException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        long start = System.currentTimeMillis();
        compiler.Empty();
        try {
            compiler.Compile(listing);
        } catch (final Exception e) {
            if(logExceptionsToConsoleOutput) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        io.select();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        Date date = new Date();
                        String format = dateFormat.format(date);

                        io.getOut().println("Quorum Compiler Threw Error at " + dateFormat.format(date) + " (This is a bug, please report it at https://quorum.atlassian.net).");
                        io.getOut().println(e.toString());
                        StackTraceElement[] stackTrace = e.getStackTrace();
                        for (int i = 0; i < stackTrace.length; i++) {
                            io.getOut().println(stackTrace[i].toString());
                        }
                        io.setInputVisible(true);
                        io.getOut().close();
                    }
                });
            }
            return false;
        }
        
        //NetBeans HACK: The NetBeans platform seems to strip away file permissions
        //for the executable in the .app file on mac. If we're on mac, 
        //reset the permissions of that file, if it exists.
        File run = new File(directory.getAbsolutePath() + "/" + QuorumProject.DISTRIBUTION_DIRECTORY +
                "/jni/CocoaSpeechServer.app/Contents/MacOS/CocoaSpeechServer");
        if(run.exists()) {
            run.setExecutable(true);
        }
        
        long finish = System.currentTimeMillis();
        double value = (finish - start);
        value = value / 1000.0;
        final double total = value;
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

                    io.getOut().println("Build Successful at " + dateFormat.format(date) + " in " + total + " seconds.");
                    io.setInputVisible(true);
                    io.getOut().close();
                }
            }
        });
        return compiler.IsCompilationErrorFree();
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
        private OutputStream outputStream;
        private InputStream inputStream;
        BufferedWriter bufferedWriter;
        private Thread blinker = null;
        public boolean running = false;
        public boolean cancelled = false;
        public boolean wasDestroyed = false;

        public QuorumProcessWatcher(InputStream in) {
            inputStream = in;
            bufferedReader = new BufferedReader(new InputStreamReader(in));
        }

        public void start() {
            if (!running) {
                blinker = new Thread(this);
                blinker.setName("Quorum Process Watcher");
                blinker.start();
                
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Reader in = io.getIn();
                        BufferedReader br = new BufferedReader(in);
                        
                        while (!cancelled) {
                            try {
                                if(br.ready()) {
                                    String line = br.readLine();
                                    if(bufferedWriter != null) {
                                    try {
                                            bufferedWriter.write(line);
                                            bufferedWriter.newLine();
                                            bufferedWriter.flush();
                                        } catch (IOException ex) {
                                            Exceptions.printStackTrace(ex);
                                        }
                                    }
                                }
                                Thread.sleep(20);
                            } catch (IOException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (InterruptedException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        }
                        try {
                            br.close();
                            in.close();
                            bufferedWriter.close();
                        } catch (IOException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                });
                thread.setName("IDE Input");
                thread.start();
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
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            io.getOut().println(line);
                            io.getOut().flush();
                        }
                    });
                }

            } catch (IOException ex) {
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    io.getOut().close();
                }
            });
        }

        @Override
        public void run() {
            running = true;

            //io.setInputVisible(true);
            //BufferedReader inputLineReader = new BufferedReader(io.getIn());
            // Watch the input stream, send its output to the console.
            while (!cancelled) {
                try {

                    if (bufferedReader.ready()) {
                        final String line = bufferedReader.readLine();
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                io.getOut().println(line);
                                io.getOut().flush();
                            }
                        });
                    }
                    Thread.sleep(20);
                } catch (IOException ex) {
                } catch (InterruptedException ex) {
                } catch (InvocationTargetException ex) {
                }
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    io.getOut().close();
                }
            });
        }

        /**
         * @return the stream
         */
        public OutputStream getStream() {
            return outputStream;
        }

        /**
         * @param stream the stream to set
         */
        public void setStream(OutputStream stream) {
            this.outputStream = stream;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(stream));
        }
    }

    public class ProcessCancel implements Cancellable {

        public ProgressHandle progress;
        public QuorumProcessWatcher watcher;
        public Process process;
        public QuorumDebugger debugger;
        public boolean flush = false;

        @Override
        public boolean cancel() {
            if (debugger != null) {
                QuorumDebugger debug2 = debugger;
                debugger = null;
                debug2.stop(false);
                return true;
            }
            if (progress != null) {
                progress.finish();
            }
            if (watcher != null) {
                watcher.running = false;
                watcher.cancelled = true;
            }

            if (flush) {
                watcher.flush();
            }
            if (process != null) {
                process.destroy();
            }
            return true;
        }
    };
}
