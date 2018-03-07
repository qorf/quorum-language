package plugins.quorum.Libraries.Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import quorum.Libraries.Network.NetworkRequest_;
import quorum.Libraries.Network.NetworkResponseEvent_;

/**
 *
 * @author Patrick Daleiden
 */
public class NetworkConnection {

    public java.lang.Object me_ = null;
    private quorum.Libraries.Network.NetworkConnection connection = null; 

    public void Request() {
        connection = (quorum.Libraries.Network.NetworkConnection) me_;
        NetworkRequest_ request = connection.GetNetworkRequest();
        if (request.GetRequestType().equals("POST")) {
            Post(request);
        } else if (request.GetRequestType().equals("GET")) {
            Get(request);
        } else if (request.GetRequestType().equals("HEAD")) {
            Head(request);
        } else if (request.GetRequestType().equals("PATCH")) {
            Patch(request);
        } else if (request.GetRequestType().equals("DELETE")) {
            Delete(request);
        } else if (request.GetRequestType().equals("PUT")) {
            Put(request);
        }
    }
    
    private void Post(NetworkRequest_ request) {
        try {
            URL Url = new URL(request.GetWebAddress());
            HttpURLConnection http = (HttpURLConnection) Url.openConnection();
            http.setRequestMethod(request.GetRequestType());
            http.setReadTimeout(request.GetReadTimeout());
            String body = request.GetBody();
            http.setFixedLengthStreamingMode(body.getBytes(StandardCharsets.UTF_8).length);
            request.ResetHeaderIterator();
            while (request.HasNextHeader()) {
                String key = request.GetNextHeaderKey();
                String value = request.GetHeaderValue(key);
                http.setRequestProperty(key, value);
            }
            http.setDoInput(request.GetDoInput());
            http.setDoOutput(request.GetDoOutput());
            http.connect();
            try(DataOutputStream os = new DataOutputStream(http.getOutputStream())) {
                os.writeBytes(body);
                os.flush();
                os.close();
            }
            DataInputStream is = new DataInputStream(http.getInputStream());
            BufferedReader in = null;
            if ("gzip".equals(http.getContentEncoding())) {
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(http.getInputStream())));
            } else {
                in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            }
            String line;
            StringBuffer responseText = new StringBuffer();
            while ((line = in.readLine()) != null) {
                responseText.append(line + "\r\n");
            }
            in.close();
            SetResponses(http, responseText.toString());
            http.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
        
    }
    
    private void SetResponses(HttpURLConnection http, String responseText) {
        try {
            NetworkResponseEvent_ response = connection.GetNewResponseEvent();
            response.SetWebAddress(http.getURL().toString());
            response.SetStatusCode(http.getResponseCode());
            response.SetStatusText(http.getResponseMessage());
            if (http.getContentEncoding() != null) {
                response.SetEncoding(http.getContentEncoding());
            } else {
                response.SetEncoding("NULL");
            }
            if (http.getContentType() != null) {
                response.SetContentType(http.getContentType());
            } else {
                response.SetContentType("NULL");
            }
            response.SetResponseText(responseText);
            response.SetResponseMessage(http.getResponseMessage());
            connection.SetResponse(response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }

    }
    
    private void PostFails(NetworkRequest_ request) {
        try {
            URL Url = new URL(request.GetWebAddress());
            HttpURLConnection http = (HttpURLConnection) Url.openConnection();
            System.out.println(http.getURL().toString());
            http.setRequestMethod(request.GetRequestType());
            System.out.println(http.getRequestMethod());
            http.setReadTimeout(request.GetReadTimeout());
            System.out.println(http.getReadTimeout());
//            http.setConnectTimeout(request.GetConnectTimeout());
            String body = request.GetBody();
            System.out.println(body);
            if (request.IsFixedLengthStreamingMode()) {
                http.setFixedLengthStreamingMode(body.getBytes(StandardCharsets.UTF_8).length);
                System.out.println("Fixed Length Mode");
                System.out.println(body.getBytes(StandardCharsets.UTF_8).length);
            } else {
                http.setChunkedStreamingMode(request.GetChunkLength());
                System.out.println("Should not be here.");
            }
            request.ResetHeaderIterator();
            while (request.HasNextHeader()) {
                String key = request.GetNextHeaderKey();
                String value = request.GetHeaderValue(key);
                http.setRequestProperty(key, value);
            }
            http.setRequestProperty("Connection", "keep-alive");
            http.setRequestProperty("Accept-Encoding", "gzip, deflate");
            http.setRequestProperty("Accept", "*/*");
            http.setRequestProperty("User-Agent", "Java client");
            http.setRequestProperty("Authentication-Token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MTY3MzQ3MjcsInR5cGUiOiJhY2Nlc3MiLCJpZGVudGl0eSI6Im1vcmRyaWsiLCJleHAiOjE1MTY4MjExMjd9.q3F8aO4QZ0Wt1dhDAIxCHXB-tpdgCnD0s5pDGyJM2e0");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            System.out.println(http.getRequestProperties());
            Map<String, List<String>> map = http.getRequestProperties();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String k = entry.getKey();
                List<String> v = entry.getValue();
                System.out.println(k + " => " + v);
            }
            http.setDoInput(request.GetDoInput());
            System.out.println(http.getDoInput());
            http.setDoOutput(request.GetDoOutput());
            System.out.println(http.getDoOutput());
            System.out.println("Ready to connect");
            http.connect();
            System.out.println(http.getContentLength());
            System.out.println(http.getResponseCode());
            System.out.println(http.getResponseMessage());
            System.out.println("Connection should have happened");
            DataOutputStream os = new DataOutputStream(http.getOutputStream());
            os.writeBytes(body);
            os.flush();
            os.close();
            if (HttpURLConnection.HTTP_OK == http.getResponseCode()) System.out.println("connected"); else System.out.println("Not connected");
            DataInputStream is = new DataInputStream(http.getInputStream());
            BufferedReader in = null;
            if ("gzip".equals(http.getContentEncoding())) {
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(http.getInputStream())));
            } else {
                in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            }
            String line;
            StringBuilder responseText = new StringBuilder();
            while ((line = in.readLine()) != null) {
                responseText.append(line + "\r\n");
            }
            System.out.println(responseText.toString());
            in.close();
            NetworkResponseEvent_ response = connection.GetNewResponseEvent();
            response.SetWebAddress(http.getURL().toString());
            response.SetStatusCode(http.getResponseCode());
            response.SetStatusText(http.getResponseMessage());
            response.SetEncoding(http.getContentEncoding());
            response.SetResponseText(responseText.toString());
            response.SetResponseMessage(http.getResponseMessage());
            connection.SetResponse(response);
            http.disconnect();
        } catch (MalformedURLException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(NetworkConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            String x = "name=2018+CY1%26raHours=11%3A12%3A20.1%26decDegs=-04%3A18%3A57%26maxSun=-14.0%26minEl=30.0%26mode=0.0%26timeAccountId=33546.0%26priority=1.0%26teleOwnerId=6.0%26isToo=false";
            System.out.println(x.getBytes().length);
            System.out.println(x);
        }
        
    }
    
    private void Get(NetworkRequest_ request) {}
    
    private void Head(NetworkRequest_ request) {}
    
    private void Patch(NetworkRequest_ request) {}
    
    private void Delete(NetworkRequest_ request) {}
    
    private void Put(NetworkRequest_ request) {}
    
}
