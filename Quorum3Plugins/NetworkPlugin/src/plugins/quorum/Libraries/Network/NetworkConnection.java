package plugins.quorum.Libraries.Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
            Map<String, List<String>> map = http.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                response.AddHeader(entry.getKey(), entry.getValue());
            }
            connection.SetResponse(response);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.out.println(ex.getMessage());
        }

    }
    
    private void Get(NetworkRequest_ request) {}
    
    private void Head(NetworkRequest_ request) {}
    
    private void Patch(NetworkRequest_ request) {}
    
    private void Delete(NetworkRequest_ request) {}
    
    private void Put(NetworkRequest_ request) {}
    
}
