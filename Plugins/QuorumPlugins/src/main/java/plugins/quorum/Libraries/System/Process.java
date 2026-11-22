/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Containers.Array_;
import quorum.Libraries.Containers.HashTable_;
import quorum.Libraries.Containers.Iterator_;
import quorum.Libraries.Language.Object_;
import quorum.Libraries.Language.Types.Text_;
import quorum.Libraries.System.File_;
import quorum.Libraries.System.Process_;

/**
 *
 * @author stefika
 */
public class Process {
    public java.lang.Object me_ = null;
    private Process_ myProcess = null;
    private File_ directory = null;
    public boolean cancelled = false;
    QuorumProcessWatcher watch = null;
    java.lang.Process process = null;
    int exit = -1;
    int wait = 0;
    // RunWithoutWaiting: where the output goes, and whether the current
    // process was started that way (it has no watcher, so Cancel ends it)
    private File_ outputFile = null;
    private File_ errorFile = null;
    private boolean runningWithoutWaiting = false;

    public void Run(String name, Array_ flags) {
        myProcess = (Process_) me_;
        runningWithoutWaiting = false;
        try {
            ProcessBuilder builder = CreateBuilder(name, flags);
            myProcess.FireProcessStartedEvent();
            process = builder.start();
            // A local copy, since the watcher sets watch to null when it
            // finishes, which can be before the join below for a quick process.
            QuorumProcessWatcher watcher = new QuorumProcessWatcher(process.getInputStream(), process.getErrorStream());
            watch = watcher;
            OutputStream outputStream = process.getOutputStream();
            watcher.setStream(outputStream);
            watcher.start();

            exit = process.waitFor();
            cancelled = true;
            watcher.GetThread().join();
        } catch (IOException ex) {
            sendExceptionToStandardError(ex);
        } catch (InterruptedException ex) {
            sendExceptionToStandardError(ex);
        }
    }

    /*
        Starts the process and returns at once. The output goes straight to
        files (or is thrown away), so no thread has to read it and the
        process can't block on a full pipe. Standard input is closed, so a
        program that reads it gets end of input instead of waiting forever.
    */
    public void RunWithoutWaiting(String name, Array_ flags) {
        myProcess = (Process_) me_;
        runningWithoutWaiting = true;
        cancelled = false;
        exit = -1;
        try {
            ProcessBuilder builder = CreateBuilder(name, flags);
            builder.redirectOutput(GetRedirectFile(outputFile));
            builder.redirectError(GetRedirectFile(errorFile));
            process = builder.start();
            process.getOutputStream().close();
        } catch (IOException ex) {
            process = null;
            sendExceptionToStandardError(ex);
        }
    }

    /*
        The command (the program's name, then its flags), the environment
        variables and the folder, shared by Run and RunWithoutWaiting.
    */
    private ProcessBuilder CreateBuilder(String name, Array_ flags) {
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        for(int i = 0; i < flags.GetSize(); i++) {
            Text_ t = (Text_) flags.Get(i);
            list.add(t.GetValue());
        }
        ProcessBuilder builder = new ProcessBuilder(list);

        //set Environment Variables.
        Map<String, String> map = builder.environment();
        HashTable_ table = myProcess.GetEnvironment();
        Iterator_ iterator = table.GetKeyIterator();
        while(iterator.HasNext()) {
            Object_ keyObject = iterator.Next();
            String key = ((Text_) keyObject).GetValue();
            String value = ((Text_) table.GetValue(keyObject)).GetValue();
            map.put(key, value);
        }

        builder.directory(new File(directory.GetAbsolutePath()));
        return builder;
    }

    /*
        The file for one output stream. With no file set, the output is
        thrown away by sending it to the system's null device, since
        Redirect.DISCARD needs Java 9 and the plugins build for Java 8.
    */
    private File GetRedirectFile(File_ file) {
        if(file == null) {
            boolean windows = System.getProperty("os.name").toLowerCase().contains("win");
            return new File(windows ? "NUL" : "/dev/null");
        }
        File result = new File(file.GetAbsolutePath());
        File parent = result.getParentFile();
        if(parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        return result;
    }

    public void SetOutputFile(File_ file) {
        outputFile = file;
    }

    public File_ GetOutputFile() {
        return outputFile;
    }

    public void SetErrorFile(File_ file) {
        errorFile = file;
    }

    public File_ GetErrorFile() {
        return errorFile;
    }

    // -1 while running or if it never started; works for Run and RunWithoutWaiting
    public int GetExitCode() {
        if(process == null || process.isAlive()) {
            return -1;
        }
        return process.exitValue();
    }

    public void sendExceptionToStandardError(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        String line = sw.toString();
        if(myProcess != null) {
            myProcess.FireProcessErrorEvent(line);
        }
    }
    
    public boolean IsAlive() {
        if(process != null) {
            return process.isAlive();
        }
        return false;
    }
    
    public void SetMonitorWaitTime(int time) {
        wait = time;
    }
    
    public int GetMonitorWaitTime() {
        return wait;
    }
    
    public void SetDirectory(File_ folder) {
        directory = folder;
    }
    
    public File_ GetDirectory() {
        return directory;
    }
    
    public void Cancel() {
        cancelled = true;
        // Run's watcher ends its process when it sees cancelled. A process
        // run without waiting has no watcher, so it is ended here.
        if(runningWithoutWaiting && process != null) {
            process.destroy();
        }
    }
    
    public void SendInput(String value) {
        if(watch != null) {
            watch.SendInput(value);
        }
    }
    
    protected class QuorumProcessWatcher implements Runnable {
        private BufferedReader bufferedReader = null;
        private BufferedReader bufferedErrorReader = null;
        private OutputStream outputStream;
        private InputStream inputStream;
        private InputStream errorStream;
        BufferedWriter bufferedWriter;
        private Thread blinker = null;
        public boolean running = false;

        public QuorumProcessWatcher(InputStream in, InputStream errors) {
            inputStream = in;
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            
            errorStream = errors;
            bufferedErrorReader = new BufferedReader(new InputStreamReader(errors));
        }

        public void SendInput(String value) {
            if(bufferedWriter != null) {
                try {
                    bufferedWriter.write(value);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        public void start() {
            if (!running) {
                blinker = new Thread(this);
                blinker.setName("Quorum Process Watcher");
                blinker.start();
            }
        }
        
        public Thread GetThread() {
            return blinker;
        }

        /**
         * This method is used to flush only if a process was destroyed before
         * fully flushing the output stream.
         */
        public void flush() {
            try {
                while (bufferedReader.ready()) {
                    final String line = bufferedReader.readLine();
                    if(myProcess != null) {
                        myProcess.FireProcessOutputEvent(line);
                    }
                }
                while (bufferedErrorReader.ready()) {
                    final String line = bufferedErrorReader.readLine();
                    if(myProcess != null) {
                        myProcess.FireProcessErrorEvent(line);
                    }
                }
            } catch (IOException ex) {
                sendExceptionToStandardError(ex);
            }
        }


        
        @Override
        public void run() {
            running = true;
            // Watch the input stream, send its output to the console.
            while (!cancelled && running) {
                try {
                    if (bufferedReader.ready()) {
                        final String line = bufferedReader.readLine();
                        if(myProcess != null) {
                            myProcess.FireProcessOutputEvent(line);
                        }
                    }
                    
                    if (bufferedErrorReader.ready()) {
                        final String line = bufferedErrorReader.readLine();
                        if(myProcess != null) {
                            myProcess.FireProcessErrorEvent(line);
                        }
                    }
                    Thread.sleep(wait);
                } catch (IOException ex) {
                    sendExceptionToStandardError(ex);
                } catch (InterruptedException ex) {
                    sendExceptionToStandardError(ex);
                } 
            }
            
            try {
                flush();
                if(inputStream != null) {
                    inputStream.close();
                }
                if(errorStream != null) {
                    errorStream.close();
                }
            } catch (IOException ex) {
                sendExceptionToStandardError(ex);
            } finally {
                process.destroy();
                myProcess.FireProcessStoppedEvent(exit);
                watch = null;
            }
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
}
