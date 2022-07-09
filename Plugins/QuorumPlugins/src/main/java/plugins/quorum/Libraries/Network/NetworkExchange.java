/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Network;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.io.ByteArrayInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import quorum.Libraries.Containers.HashTable;
import quorum.Libraries.Containers.HashTable_;
import quorum.Libraries.Containers.List_;
import quorum.Libraries.Language.Types.Text;
import quorum.Libraries.Language.Types.Text_;

/**
 *
 * @author stefika
 */
public class NetworkExchange {
    public java.lang.Object me_ = null;
    private String ENCODING = "UTF-8";
    private HttpExchange exchange;
    private URI uri;
        
    public void SendResponseHeaders(int code, String response) throws IOException {
        String resp2 = new String(response.getBytes(), ENCODING);
        exchange.sendResponseHeaders(code, resp2.getBytes().length);
    }
    
    public void SendResponse(String response) throws IOException {
        response = new String(response.getBytes(), ENCODING);
        OutputStream body = exchange.getResponseBody();
        BufferedOutputStream out = new BufferedOutputStream(body);
        if(response == null) {
            response = "";
        }
        ByteArrayInputStream byteResponseStream = new ByteArrayInputStream(response.getBytes(ENCODING));

        byte [] buffer = new byte [1024];
        int count = 0;
        while ((count = byteResponseStream.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        out.close();
    }

    public String HashPassword(String password) {
        String hash = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(10, password.toCharArray());
        return hash;
    }

    public boolean VerifyPassword(String password, String hash) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hash);
        return result.verified;
    }
    public HashTable_ GetHeaders() {
        HashTable table = new HashTable();

        Headers headers = exchange.getRequestHeaders();
        for (String key : headers.keySet()) {
            List<String> strings = headers.get(key);
            Iterator<String> iterator = strings.iterator();
            quorum.Libraries.Containers.List list = new quorum.Libraries.Containers.List();
            while(iterator.hasNext()) {
                String next = iterator.next();
                Text text = new Text();
                text.SetValue(next);
                list.Add(text);
            }

            Text_ quorumKey = new Text();
            quorumKey.SetValue(key);
            table.Add(quorumKey, list);
        }
        return table;
    }

    public HashTable_ GetParameters() throws UnsupportedEncodingException, IOException {
        HashTable table = new HashTable();
        boolean urlencdoded = false;
        List<String> list = exchange.getRequestHeaders().get("Content-type");
        if(list != null && list.size() > 0) {
            String type = list.get(0);
            if(type.compareTo("application/x-www-form-urlencoded") == 0)
            {
                urlencdoded = true;
            }
        }
        String rawQuery = "";
        if(urlencdoded) {
            InputStream requestBody = exchange.getRequestBody();
            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
              (requestBody, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            
            requestBody.close();
            rawQuery = textBuilder.toString();
        } else {
            rawQuery = uri.getRawQuery();
        }
        
        if(rawQuery == null) {
            return table;
        }
        String[] params = rawQuery.split("&");

        for(int i = 0; i < params.length; i++) {
            String[] split = params[i].split("=");
            Text left = new Text();
            Text right = new Text();

            left.SetValue(java.net.URLDecoder.decode(split[0], ENCODING));
            if(split.length > 1) {
                right.SetValue(java.net.URLDecoder.decode(split[1], ENCODING));
            }
            table.Add(left, right);
        }
        return table;
    }
    
    public String GetQuery() {
        return uri.getQuery();
    }

    public String GetPath() {
        return uri.getPath();
    }
    
    public String GetHost() {
        return exchange.getRemoteAddress().toString();
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
