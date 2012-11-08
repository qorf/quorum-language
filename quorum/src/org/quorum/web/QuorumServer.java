/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A default web server for handling requests. 
 * 
 * @author Louis Koester
 */
public class QuorumServer {
    private HttpServer server;
    private String jarLocation = null;
    
    public QuorumServer() {
    } 
    
    /**
     * Starts an http server using the current jar location. If the jar location
     * is null, this method does nothing.
     * 
     */
    public void start() {
        try {
            if(jarLocation == null) {
                return;
            }
            server = HttpServer.create(new InetSocketAddress(8000), 1);
            server.createContext("/", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            while (jarLocation.startsWith("/")){
                jarLocation = jarLocation.substring(1);
            }
            Desktop.getDesktop().browse(new URI("http://localhost:8000/"));
        } catch (URISyntaxException ex) {
            Logger.getLogger(QuorumServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuorumServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the jarLocation
     */
    public String getJarLocation() {
        return jarLocation;
    }

    /**
     * @param jarLocation the jarLocation to set
     */
    public void setJarLocation(String jarLocation) {
        this.jarLocation = jarLocation;
    }
    
    private class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "";
            Process p = Runtime.getRuntime().exec("java -jar " + jarLocation + " " + t.getRequestURI());
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            Integer input = p.getInputStream().read();
            while (input != -1) {
                os.write(input.byteValue());
                input = p.getInputStream().read();
            }
            os.close();
        }
    }
    
}
