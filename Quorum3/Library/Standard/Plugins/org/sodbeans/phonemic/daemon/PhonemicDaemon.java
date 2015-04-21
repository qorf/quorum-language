/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sodbeans.phonemic.daemon;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sodbeans.phonemic.tts.TextToSpeech;

/**
 * A phonemic daemon that listens on the specified address and port.
 * 
 * @author jeff
 */
public class PhonemicDaemon extends Thread {
    /**
     * The server socket.
     */
    protected ServerSocket serverSocket = null;
    
    /**
     * The port we are listening on.
     */
    private int port = 0;
    
    /**
     * The address we are bound to.
     */
    private InetAddress address = null;
    
    /**
     * The connection queue size of this server. The default is 100.
     */
    private int backlog = 100;
    
    /**
     * The text to speech engine we wish to use.
     */
    private TextToSpeech textToSpeech = null;
    
    /**
     * Create a new Phonemic daemon that listens on the specified port, and is
     * bound to the specified address.
     * 
     * @param port
     * @param address 
     */
    public PhonemicDaemon(TextToSpeech textToSpeech, int port, InetAddress address) {
        this.port = port;
        this.address = address;
        this.textToSpeech = textToSpeech;
        this.setDaemon(true);
        this.setName("Phonemic Daemon thread");
    }
    
    /**
     * Bind to the server's host and port. MUST be called before start()!
     */
    public void bind() throws IOException {
        this.serverSocket = new ServerSocket(this.getPort(), this.getBacklog(), this.getAddress());
    }
    
    /**
     * The server's main loop.
     */
    @Override
    public void run() {
        if (this.serverSocket == null) {
            System.err.println("bind() must be called before start().");
            return;
        }
        
        /**
         * Accept connections, pass them off to a ClientHandler.
         */
        while (true) {
            Socket client = null;
            
            try {
                client = this.serverSocket.accept();
                ClientHandler ch = new ClientHandler(this.textToSpeech, client);
                ch.start();
            } catch (IOException ex) {
                Logger.getLogger(PhonemicDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Stop the daemon.
     */
    public void stopDaemon() {
        // TODO
    }
    
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * @return the backlog
     */
    public int getBacklog() {
        return backlog;
    }

    /**
     * @param backlog the backlog to set
     */
    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }
}
