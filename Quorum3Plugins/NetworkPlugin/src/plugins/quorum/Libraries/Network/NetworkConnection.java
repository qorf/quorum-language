package plugins.quorum.Libraries.Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
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
            HttpURLConnection conn;
            if (request.GetWebAddress().contains("https://")){
                conn = (HttpsURLConnection) Url.openConnection();
            } else {
                conn = (HttpURLConnection)  Url.openConnection();
            }
            conn.setRequestMethod(request.GetRequestType());
            conn.setReadTimeout(request.GetReadTimeout());
            String body = request.GetBody();
            conn.setFixedLengthStreamingMode(body.getBytes(StandardCharsets.UTF_8).length);
            request.ResetHeaderIterator();
            while (request.HasNextHeader()) {
                String key = request.GetNextHeaderKey();
                String value = request.GetHeaderValue(key);
                conn.setRequestProperty(key, value);
            }
            conn.setDoInput(request.GetDoInput());
            conn.setDoOutput(request.GetDoOutput());
            conn.connect();
            try(DataOutputStream os = new DataOutputStream(conn.getOutputStream())) {
                os.writeBytes(body);
                os.flush();
                os.close();
            }
            DataInputStream is = new DataInputStream(conn.getInputStream());
            BufferedReader in = null;
            if ("gzip".equals(conn.getContentEncoding())) {
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String line;
            StringBuffer responseText = new StringBuffer();
            while ((line = in.readLine()) != null) {
                responseText.append(line + "\r\n");
            }
            in.close();
            SetResponses(conn, responseText.toString());
            conn.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
    
    private void SetResponses(HttpURLConnection conn, String responseText) {
        try {
            NetworkResponseEvent_ response = connection.GetNewResponseEvent();
            response.SetWebAddress(conn.getURL().toString());
            response.SetStatusCode(conn.getResponseCode());
            response.SetStatusText(conn.getResponseMessage());
            if (conn.getContentEncoding() != null) {
                response.SetEncoding(conn.getContentEncoding());
            } else {
                response.SetEncoding("NULL");
            }
            if (conn.getContentType() != null) {
                response.SetContentType(conn.getContentType());
            } else {
                response.SetContentType("NULL");
            }
            Map<String, List<String>> map = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                if (entry.getKey() == null) {
                    response.AddHeader("Null", String.join(" ", entry.getValue()));
                } else {
                    response.AddHeader(entry.getKey(), String.join(" ", entry.getValue()));
                }
            }
            response.SetResponseText(responseText);
            connection.SetResponse(response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }

    }
    
    private void Get(NetworkRequest_ request) {
        try {
            URL Url = new URL(request.GetWebAddress());
            HttpURLConnection conn;
            if (request.GetWebAddress().contains("https://")){
                conn = (HttpsURLConnection) Url.openConnection();
            } else {
                conn = (HttpURLConnection)  Url.openConnection();
            }
            conn.setRequestMethod(request.GetRequestType());
            conn.setReadTimeout(request.GetReadTimeout());
            String body = request.GetBody();
            conn.setFixedLengthStreamingMode(body.getBytes(StandardCharsets.UTF_8).length);
            request.ResetHeaderIterator();
            while (request.HasNextHeader()) {
                String key = request.GetNextHeaderKey();
                String value = request.GetHeaderValue(key);
                conn.setRequestProperty(key, value);
            }
            conn.setDoInput(request.GetDoInput());
            conn.setDoOutput(request.GetDoOutput());
            conn.connect();
            try(DataOutputStream os = new DataOutputStream(conn.getOutputStream())) {
                os.writeBytes(body);
                os.flush();
                os.close();
            }
            DataInputStream is = new DataInputStream(conn.getInputStream());
            BufferedReader in = null;
            if ("gzip".equals(conn.getContentEncoding())) {
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String line;
            StringBuffer responseText = new StringBuffer();
            while ((line = in.readLine()) != null) {
                responseText.append(line + "\r\n");
            }
            in.close();
            SetResponses(conn, responseText.toString());
            conn.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }
    }
    
    private void Head(NetworkRequest_ request) {}
    
    private void Patch(NetworkRequest_ request) {}
    
    private void Delete(NetworkRequest_ request) {}
    
    private void Put(NetworkRequest_ request) {}
    
}
