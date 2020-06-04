package plugins.quorum.Libraries.Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import quorum.Libraries.Language.Errors.InputOutputError;
import quorum.Libraries.Network.NetworkRequest_;
import quorum.Libraries.Network.NetworkResponseEvent_;
import quorum.Libraries.System.File_;

/**
 *
 * @author Patrick Daleiden
 */
public class NetworkConnection {

    public java.lang.Object me_ = null;
    private quorum.Libraries.Network.NetworkConnection connection = null; 

    public void Request() throws InputOutputError {
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
    
    private void Post(NetworkRequest_ request) throws InputOutputError {
        try {
            URL Url = new URL(request.GetWebAddress());
            HttpURLConnection conn;
            
            URLConnection urlConnection = Url.openConnection();
            if (urlConnection instanceof HttpsURLConnection) {
                HttpsURLConnection https = (HttpsURLConnection) urlConnection;
                System.setProperty("jsse.enableSNIExtension", String.valueOf(connection.GetServerNameIdentification()));
                conn = (HttpURLConnection) https;
            } else {
                conn = (HttpURLConnection) urlConnection;
            }
            conn.setRequestMethod(request.GetRequestType());
            conn.setReadTimeout(request.GetReadTimeout());
            String body = request.GetBody();
            if (request.IsFixedLengthStreamingMode()) {
                conn.setFixedLengthStreamingMode(body.getBytes(StandardCharsets.UTF_8).length);
            } else {
            // Note: the HttpURLConnection object handles the mode. Removed for now, but kept here for future reference.
            //     conn.setChunkedStreamingMode(connection.GetChunkLength());
            }
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
            
            if (request.GetDownloadFile() != null){
                InputStream in = conn.getInputStream();
                File_ quorumFile = request.GetDownloadFile();
                File file = new File(quorumFile.GetAbsolutePath());
                FileOutputStream out = new FileOutputStream(file);
 
                int bytesRead = -1;
                byte[] buffer = new byte[4096];
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                SetResponses(conn, request.GetDownloadFile() + " downloaded.");
                in.close();
                out.close();
            } else {
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
                SetResponses(conn, responseText.toString());
                in.close();
            }
            conn.disconnect();
        } catch (IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    private void Get(NetworkRequest_ request) throws InputOutputError {
        // Note: Get Request does not send body. Parameters are attached to the URL.
        try {
            String url = request.GetWebAddress() + "?" + request.GetParameters();
            URL Url = new URL(url);
            HttpURLConnection conn;
            
            URLConnection urlConnection = Url.openConnection();
            if (urlConnection instanceof HttpsURLConnection) {
                HttpsURLConnection https = (HttpsURLConnection) urlConnection;
                System.setProperty("jsse.enableSNIExtension", String.valueOf(connection.GetServerNameIdentification()));
                conn = (HttpURLConnection) https;
            } else {
                conn = (HttpURLConnection) urlConnection;
            }
            conn.setRequestMethod(request.GetRequestType());
            conn.setReadTimeout(request.GetReadTimeout());
            request.ResetHeaderIterator();
            while (request.HasNextHeader()) {
                String key = request.GetNextHeaderKey();
                String value = request.GetHeaderValue(key);
                conn.setRequestProperty(key, value);
            }
            conn.setDoOutput(request.GetDoOutput());
            conn.connect();
            
            if (request.GetDownloadFile() != null){
                InputStream in = conn.getInputStream();
                File file = new File(request.GetDownloadFile().GetAbsolutePath());
                FileOutputStream out = new FileOutputStream(file);
 
                int bytesRead = -1;
                byte[] buffer = new byte[4096];
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                SetResponses(conn, request.GetDownloadFile() + " downloaded.");
                in.close();
                out.close();
            } else {
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
                SetResponses(conn, responseText.toString());
                in.close();
            }
            conn.disconnect();
        } catch (IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
    private void Head(NetworkRequest_ request) {}
    
    private void Patch(NetworkRequest_ request) {}
    
    private void Delete(NetworkRequest_ request) {}
    
    private void Put(NetworkRequest_ request) {}
    
    private void SetResponses(HttpURLConnection conn, String responseText) throws InputOutputError {
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
        } catch (IOException ex)
        {
            InputOutputError e = new InputOutputError();
            if (ex.getMessage() != null)
                e.SetErrorMessage(ex.getMessage());
            throw e;
        }
    }
    
}
