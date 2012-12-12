/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
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
            //while (jarLocation.startsWith("/")){
            //    jarLocation = jarLocation.substring(1);
           // }
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
            OutputStream os = t.getResponseBody();
            String response = "";
            if(t.getRequestURI().toString().compareTo("/" + jarLocation)==0) {
                response = "<html><body><h2>404 Error</h2>"
                        + "<p>We could not find the page you were looking for.</p></body></html>";
                t.sendResponseHeaders(200, response.length());
                os.write(response.getBytes());
            } else {
                
                URI uri = t.getRequestURI();
                String request = "";
                if(uri != null) {
                    request += uri.toString();
                    if(request != null && !request.isEmpty()) {
                        request = request.substring(1);
                    }
                }
                
                final String URL_PROPERTY_NAME = "quorum.url";
                ProcessBuilder builder = new ProcessBuilder("java", "-Dsodbeans=1", "-jar", jarLocation);
                builder.redirectErrorStream(true);  
                Map<String,String> env = builder.environment(); 
                env.put(URL_PROPERTY_NAME, request);  
                
                Process process = builder.start();  
                t.sendResponseHeaders(200, response.length());
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));  
                
                String line;  
                while ((line = in.readLine()) != null)  
                {  
                    os.write(line.getBytes());
                }
            }
            os.close();
        }
    }
}
