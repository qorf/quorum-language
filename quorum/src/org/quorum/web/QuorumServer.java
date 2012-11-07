/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quorum.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Louis Koester
 */
public class QuorumServer {
    private HttpServer server;
    private String address;
    
    public QuorumServer(String add) throws IOException, URISyntaxException {
        address = add;
        server = HttpServer.create(new InetSocketAddress(8000), 1);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        
        while (address.startsWith("/")){
            address = address.substring(1);
        }
        Desktop.getDesktop().browse(new URI("http://localhost:8000/" + address));

    }
    
//    public void start() throws URISyntaxException, IOException{
//        
//        //try {
//           
//            server.start();
//            //Thread.sleep(1000);
//            //Desktop.getDesktop().browse(new URI("http://localhost:8000/" + address));
//        //} catch (InterruptedException ex) {
//        //    Logger.getLogger(QuorumServer.class.getName()).log(Level.SEVERE, null, ex);
//        //}
//    }
    
    private String parsePath(String uriPath){
        
        return uriPath;
    }
    
    private static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "";
            
            Process p = Runtime.getRuntime().exec("java -jar " + t.getRequestURI());
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
