/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Network;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 *
 * @author stefika
 */
public class NetworkExchange {
    public java.lang.Object me_ = null;
    
    private HttpExchange exchange;
    private URI uri;
        
    public void SendResponseHeaders(int code, int size) throws IOException {
        exchange.sendResponseHeaders(code, size);
    }
    
    public void SendResponse(String response) throws IOException {
        OutputStream body = exchange.getResponseBody();
        body.write(response.getBytes());
        body.close();
    }
    
    public String GetQuery() {
        return uri.getQuery();
    }

    public String GetPath() {
        return uri.getPath();
    }
    
    public String GetHost() {
        return uri.getHost();
    }
    
    public int GetPort() {
        return uri.getPort();
    }
    
    public String GetAuthority() {
        return uri.getAuthority();
    }
    
    /**
     * @return the exchange
     */
    public HttpExchange getExchange() {
        return exchange;
    }

    /**
     * @param exchange the exchange to set
     */
    public void setExchange(HttpExchange exchange) {
        this.exchange = exchange;
        uri = exchange.getRequestURI();
    }
}
