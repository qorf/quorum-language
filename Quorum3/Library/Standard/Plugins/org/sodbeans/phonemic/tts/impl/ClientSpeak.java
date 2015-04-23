/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sodbeans.phonemic.tts.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sodbeans.phonemic.RequestType;
import org.sodbeans.phonemic.SpeechLanguage;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.SpeechProcessor;
import org.sodbeans.phonemic.SpeechVoice;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.sodbeans.phonemic.tts.TextToSpeechEngine;
import java.io.PrintWriter;
import org.sodbeans.phonemic.OperatingSystem;

/**
 * Represents a Phonemic client.
 * 
 * @author jeff
 */
public class ClientSpeak implements TextToSpeech {
    /**
     * The server OS
     */
    private static final OperatingSystem os = OperatingSystem.getOS();
    /**
     * The port of the server we wish to connect to.
     */
    protected int port = 0;
    
    /**
     * The hostname of the server we wish to connect to.
     */
    protected String host = null;
    
    /**
     * Our connection to the server.
     */
    protected Socket socket = null;
    
    /**
     * Our input/output streams to the client socket.
     */
    protected BufferedReader input = null;
    protected OutputStream output = null;
    
    /**
     * Create a new client connected to address.
     * 
     * @param host the hostname of the server (typically "localhost" or "127.0.0.1")
     * @param port the port of the server.
     */
    public ClientSpeak(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    /**
     * Initiate a connection with the server.
     * 
     * @throws IOException 
     */
    public void connect() throws IOException {
        //wait until the connection will be accepted
        while (true) {
            try {
                this.socket = new Socket(this.host, this.port);
                break; //connection has been accepted if exectution reaches this point
            }
            catch (IOException ex) {
                //connection refused, try again in 100ms
                try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex2) {
                        Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex2);
                    }
            }
        }
        // Get our input and output streams.
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.output = this.socket.getOutputStream();
        
        // Write two bytes requesting protocol version 1. (Not used with mac server)
        if (os != OperatingSystem.MAC_OSX) {
            this.output.write(0x0000);
            this.output.write(0x00001);
        }
        
        // Wait for acceptance message.
        
        String okLine = this.input.readLine();
        if (okLine == null || !okLine.equals("OK")) {
            throw new IOException("Unknown response received from Phonemic server.");
        }
    }
    
    /**
     * Send a raw message.
     * @param msg 
     */
    private synchronized void sendRawMessage(String msg) {
        int length = msg.length();
        
        if (length > 0xFFFF) {
            length = 0xFFFF;
            msg = msg.substring(0, 0xFFFE);
        }
        try {
            //the mac server expects \r\n to flag the end of a message
            if (os == OperatingSystem.MAC_OSX) {
                PrintWriter out = new PrintWriter(this.output, true);
                out.print(msg + "\r\n");
                out.flush();
            }
            else {
                // Write length header (2 bytes).
                this.output.write(ByteBuffer.allocate(4).putInt(length).array(), 2, 2);

                // Write message itself.
                this.output.write(msg.getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Wait for a boolean response from the server.
     * 
     * @return 
     */
    private synchronized boolean getBooleanResponse() {
        try {
            String line = this.input.readLine();
            while (line.equals("dummyMessage"))
                line = this.input.readLine(); //ignore any return messages from mac server that aren't requested by Phonemic
            
            if (line == null)
                return false;
            
            return Boolean.parseBoolean(line);
        } catch (IOException ex) {
            Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Wait for a double response from the server.
     * 
     * @return 
     */
    private synchronized double getDoubleResponse() {
        try {
            String line = this.input.readLine();
            while (line.equals("dummyMessage"))
                line = this.input.readLine(); //ignore any return messages from mac server that aren't requested by Phonemic
            
            if (line == null)
                return 0.0;
            
            return Double.parseDouble(line);
        } catch (IOException ex) {
            Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }
    
    /**
     * Wait for a string response from the server.
     * 
     * @return 
     */
    private synchronized String getStringResponse() {
        try {
            String line = this.input.readLine();
            while (line.equals("dummyMessage"))
                line = this.input.readLine(); //ignore any return messages from mac server that aren't requested by Phonemic
            
            if (line == null)
                return "";
            
            return line;
        } catch (IOException ex) {
            Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    public synchronized boolean canBlock() {
        sendRawMessage("canBlock");
        
        return getBooleanResponse();
    }

    public synchronized boolean canPause() {
        sendRawMessage("canPause");
        
        return getBooleanResponse();
    }

    public synchronized boolean canResume() {
        sendRawMessage("canResume");
        
        return getBooleanResponse();
    }

    public synchronized boolean canStop() {
        sendRawMessage("canStop");
        
        return getBooleanResponse();
    }

    public synchronized boolean canSetVoice() {
        sendRawMessage("canSetVoice");
        
        return getBooleanResponse();
    }

    public synchronized boolean canSetVolume() {
        sendRawMessage("canSetVolume");
        
        return getBooleanResponse();
    }

    public synchronized boolean canSetSpeed() {
        sendRawMessage("canSetSpeed");
        
        return getBooleanResponse();
    }

    public synchronized boolean canSetPitch() {
        sendRawMessage("canSetPitch");
        
        return getBooleanResponse();
    }

    public synchronized Iterator<SpeechVoice> getAvailableVoices() {
        ArrayList<SpeechVoice> voices = new ArrayList<SpeechVoice>();
        
        sendRawMessage("getAvailableVoices");
        
        // Parse lines of input until we get a blank line.
        try {
            String line = this.input.readLine();
            while (line.equals("dummyMessage"))
                line = this.input.readLine(); //ignore any return messages from mac server that aren't requested by Phonemic
            while (!line.isEmpty()) {
                 SpeechVoice v = new SpeechVoice(line, SpeechLanguage.ENGLISH_US);
                 voices.add(v);
                line = this.input.readLine();
             }
        } catch (IOException ex) {
            Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
        }

        return voices.iterator(); 
    }

    public synchronized SpeechVoice getCurrentVoice() {
        sendRawMessage("getCurrentVoice");
        
        String name = getStringResponse();
        
        return new SpeechVoice(name, SpeechLanguage.ENGLISH_US);
    }

    public synchronized double getSpeed() {
        sendRawMessage("getSpeed");
        
        return getDoubleResponse();
    }

    public synchronized TextToSpeechEngine getTextToSpeechEngine() {
        sendRawMessage("getTextToSpeechEngine");
        
        String name = getStringResponse();
        
        return TextToSpeechEngine.valueOf(name);
    }

    public synchronized boolean setTextToSpeechEngine(TextToSpeechEngine engine) {
        sendRawMessage("setTextToSpeechEngine:" + engine.toString());
        
        return getBooleanResponse();
    }

    public synchronized Iterator<TextToSpeechEngine> getAvailableEngines() {
        ArrayList<TextToSpeechEngine> engines = new ArrayList<TextToSpeechEngine>();
        
        sendRawMessage("getAvailableEngines");
        
        // Parse lines of input until we get a blank line.
        String line = "";
//        do {
//            try {
//                line = this.input.readLine();
//                while (line.equals("dummyMessage"))
//                    line = this.input.readLine(); //ignore any return messages from mac server that aren't requested by Phonemic
//                if (!line.isEmpty())
//                    engines.add(TextToSpeechEngine.valueOf(line));
//            } catch (IOException ex) {
//                Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
//                line = "";
//            }
//        } while (!line.isEmpty());
        
            try {
                line = this.input.readLine();
                while (line.equals("dummyMessage"))
                    line = this.input.readLine(); //ignore any return messages from mac server that aren't requested by Phonemic
                while (!line.isEmpty()) {
                    engines.add(TextToSpeechEngine.valueOf(line));
                    line = this.input.readLine();
                 }
            } catch (IOException ex) {
                Logger.getLogger(ClientSpeak.class.getName()).log(Level.SEVERE, null, ex);
                line = "";
            }
        
        return engines.iterator();
    }

    public synchronized double getVolume() {
        sendRawMessage("getVolume");
        
        return getDoubleResponse();
    }

    public synchronized double getPitch() {
        sendRawMessage("getPitch");
        
        return getDoubleResponse();
    }

    public synchronized boolean isSpeaking() {
        sendRawMessage("isSpeaking");
        
        return getBooleanResponse();
    }

    public synchronized boolean pause() {
        sendRawMessage("pause");
        
        return getBooleanResponse();
    }

    public void reinitialize() {
        sendRawMessage("reinitialize");
    }

    public synchronized boolean respeak() {
        sendRawMessage("respeak");
        
        return getBooleanResponse();
    }

    public boolean copyToClipboard() {
        return false; // not implemented, TODO?
    }

    public synchronized boolean resume() {
        sendRawMessage("resume");
        
        return getBooleanResponse();
    }

    public synchronized boolean setVolume(double vol) {
        if (vol < 0.0)
            vol = 0.0;
        else if (vol > 1.0)
            vol = 1.0;
        
        sendRawMessage("setVolume:" + vol);
        
        return getBooleanResponse();
    }

    public synchronized boolean setSpeed(double speed) {
        if (speed < 0.0)
            speed = 0.0;
        else if (speed > 1.0)
            speed = 1.0;
        
        sendRawMessage("setSpeed:" + speed);
        
        return getBooleanResponse();    
    }

    public synchronized boolean setPitch(double pitch) {
        if (pitch < 0.0)
            pitch = 0.0;
        else if (pitch > 1.0)
            pitch = 1.0;   

        sendRawMessage("setPitch:" + pitch);
        
        return getBooleanResponse();    
    }

    public synchronized boolean setVoice(SpeechVoice voice) {
        sendRawMessage("setVoice:" + voice.toString());
        
        return getBooleanResponse();    
    }

    public boolean speak(String text) {
        return speak(text, SpeechPriority.MEDIUM);
    }

    public boolean speak(String text, SpeechPriority priority) {
        return speak(text, priority, RequestType.TEXT);
    }

    public boolean speak(String text, SpeechPriority priority, RequestType type) {
        sendRawMessage("speak:" + priority.toString() + ":" + type.toString() + ":" + text);
            return true; // assume success
    }

    public boolean speak(char c) {
        return speak(c, SpeechPriority.MEDIUM);
    }

    public boolean speak(char c, SpeechPriority priority) {
        sendRawMessage("speak:" + priority.toString() + ":CHARACTER:" + c);
        
        return true; // assume success
    }

    public boolean speak(SpeechProcessor proc) {
        String text = proc.process();
        RequestType type = proc.getRequestType();
        SpeechPriority priority = proc.getPriority();
        
        return speak(text, priority, type);
    }

    public boolean speakBlocking(String text) {
        return speakBlocking(text, SpeechPriority.MEDIUM);
    }

    public boolean speakBlocking(String text, SpeechPriority priority) {
        return speakBlocking(text, priority, RequestType.TEXT);
    }

    public synchronized boolean speakBlocking(String text, SpeechPriority priority, RequestType type) {
        sendRawMessage("speakBlocking:" + priority.toString() + ":" + type.toString() + ":" + text);
        
        return getBooleanResponse();    
    }

    public boolean speakBlocking(char c) {
        return speakBlocking(c, SpeechPriority.MEDIUM);
    }

    public synchronized boolean speakBlocking(char c, SpeechPriority priority) {
        sendRawMessage("speakBlocking:" + priority.toString() + ":CHARACTER:" + c);
        
        return getBooleanResponse();   
    }

    public synchronized boolean stop() {
        sendRawMessage("stop");
        
        return getBooleanResponse();
    }

    public void setSpeechEnabled(boolean enabled) {
        sendRawMessage("setSpeechEnabled:" + enabled);        
    }

    public synchronized boolean isSpeechEnabled() {
        sendRawMessage("isSpeechEnabled");
        
        return getBooleanResponse();
    }
    
    public synchronized double getVersion() {
        sendRawMessage("getVersion");
        
        return getDoubleResponse();
    }
}
