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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quorum.Libraries.Containers.Array_;
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
    
    public void Run(String name, Array_ flags) {
        myProcess = (Process_) me_;
        ProcessBuilder builder;
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        for(int i = 0; i < flags.GetSize(); i++) {
            Object_ o = flags.Get(i);
            Text_ t = (Text_) o;
            String text = t.GetValue();
            list.add(text);
        }
        try {
            builder = new ProcessBuilder(list);
            builder.directory(new File(directory.GetAbsolutePath()));
            myProcess.FireProcessStartedEvent();
            process = builder.start();
            watch = new QuorumProcessWatcher(process.getInputStream(), process.getErrorStream());
            OutputStream outputStream = process.getOutputStream();
            watch.setStream(outputStream);
            watch.start();
            
            int exit = process.waitFor();
            watch.wasDestroyed = true;
            cancelled = true;
            
            watch.flush();
            process.destroy();
            myProcess.FireProcessStoppedEvent(exit);
            watch = null;
        } catch (IOException ex) {
            myProcess.FireProcessErrorEvent(ex.getMessage());
        } catch (InterruptedException ex) {
            myProcess.FireProcessErrorEvent(ex.getMessage());
        }
    }
    
    public boolean IsAlive() {
        if(process != null) {
            return process.isAlive();
        }
        return false;
    }
    
    public void SetDirectory(File_ folder) {
        directory = folder;
    }
    
    public File_ GetDirectory() {
        return directory;
    }
    
    public void Cancel() {
        cancelled = true;
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
        public boolean wasDestroyed = false;

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
                    Thread.sleep(50);
                } catch (IOException ex) {
                } catch (InterruptedException ex) {
                } 
            }
            
            boolean alive = process.isAlive();
            if(alive) {
                process.destroyForcibly();
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
