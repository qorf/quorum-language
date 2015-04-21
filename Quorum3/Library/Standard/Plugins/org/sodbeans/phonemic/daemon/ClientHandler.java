/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sodbeans.phonemic.daemon;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * A handler for Phonemic clients.
 * @author jeff
 */
public class ClientHandler extends Thread {
    /**
     * Our connection to the client.
     */
    private Socket client = null;
    
    /**
     * The text to speech engine we wish to use.
     */
    private TextToSpeech textToSpeech = null;
    
    private static final int SOCKET_TIMEOUT = 10000;
    /**
     * Initialize a new client handler.
     * 
     * @param textToSpeech
     * @param client 
     */
    public ClientHandler(TextToSpeech textToSpeech, Socket client) {
        this.textToSpeech = textToSpeech;
        this.client = client;
        try {
            this.client.setSoTimeout(SOCKET_TIMEOUT);
        } catch (SocketException ex) {
            // protocol error.
        }
        this.setDaemon(true);
        this.setName("Phonemic Client thread");
    }
    
    /**
     * The main loop.
     */
    @Override
    public void run() {
        // The client's input stream.
        InputStream input = null;
        OutputStream output = null;
        
        // The recognizer we will use.
        ProtocolRecognizer recognizer = null;
        
        try {
            // Get an input and output stream from the client.
            input = this.client.getInputStream();
            output = this.client.getOutputStream();
        } catch (IOException ex) {
            return; // failed to get an input or output stream (broken pipe?)
        }
        
        // Version will be between 0x00000000 and 0xFFFFFFFF.
        int version = 0;
        
        try {
            version = input.read();
            version = version << 8;
            version |= input.read();
        } catch (SocketTimeoutException e) {
            // do nothing.
        } catch (IOException ex) {
            return; // failed to read first two bytes (broken pipe?)
        }
        
        // Select an appropriate recognizer.
        if (version == 0x0001) {
            recognizer = new V1Recognizer();
            recognizer.setTextToSpeech(textToSpeech);
            try {
                output.write("OK\n".getBytes());
                output.flush();
            } catch (IOException ex) {
                return; // failed to write to client (broken pipe?)
            }
        } else {
            // Inappropriate version flag. Terminate connection.
            try {
                this.client.close();
            } catch (IOException ex) {
                // silently ignore the client
            }
            
            return;
        }
        
        // Read input from the client, pass to recognizer.
        while (true) {
            // Get the message size. will be between 0x0000 and 0xFFFF (but not -1).
            int messageSize = 0;
            try {
                messageSize = input.read();
                if (messageSize == -1)
                    return; // connection failure.
                messageSize = messageSize << 8;
                messageSize |= input.read();
            } catch (SocketTimeoutException e) {
                // do nothing--this is merely here to prevent zombie threads.
            } catch (IOException ex) {
                // Couldn't read from client. Close connection silently.
                return;
            }
            
            if (messageSize > 0) {
                byte[] data = new byte[messageSize];
                try {
                    input.read(data);
                    String result = recognizer.parse(new String(data).toCharArray());
                    // If there's anything to send, do that now.
                    if (result != null) {
                        output.write(result.getBytes());
                        output.flush();
                    }
                } catch (IOException ex) {
                    // do nothing.
                } catch (Exception ex) {
                    // do nothing
                }
            }
            
            // A messageSize of zero is a keep-alive packet, so just ignore it.
        }
    }
}