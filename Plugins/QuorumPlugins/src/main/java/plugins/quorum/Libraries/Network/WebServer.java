/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Network;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import quorum.Libraries.Network.NetworkExchangeListener_;

/**
 * This is really inflexible, but may be sufficient for small applications. 
 * 
 * @author stefika
 */
public class WebServer {
    public java.lang.Object me_ = null;
    
    HttpServer server = null;
    public void Create(int port) throws IOException {
        if(server != null) {
            server.stop(0);
            server = null;
        }

        server = HttpServer.create(new InetSocketAddress(port), 0);
    }
    
    public void Start() {
        server.start();
    }
    
    public void Add(String context, NetworkExchangeListener_ listener) {
        HttpHandlerWrapper wrapper = new HttpHandlerWrapper();
        wrapper.setListener(listener);
        server.createContext(context, wrapper);
    }
    
    public void Stop(int delay) {
        if(server != null) {
            server.stop(delay);
            server = null;
        }
    }
}
