/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Network;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.URI;
import quorum.Libraries.Network.NetworkExchangeListener_;

/**
 *
 * @author stefika
 */
public class HttpHandlerWrapper implements HttpHandler{
    private NetworkExchangeListener_ listener;
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        quorum.Libraries.Network.NetworkExchange ne = new quorum.Libraries.Network.NetworkExchange();
        ne.plugin_.setExchange(exchange);
        listener.ResponseReceived(ne);
    }

    /**
     * @return the listener
     */
    public NetworkExchangeListener_ getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(NetworkExchangeListener_ listener) {
        this.listener = listener;
    }
    
}
