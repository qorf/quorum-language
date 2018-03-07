/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Network;

import java.net.*;
import java.io.*;


/**
 *
 * @author Patrick Daleiden
 */

public class NetworkRequest {
    public java.lang.Object me_ = null;

    public void Get(String url) throws Exception {
        final quorum.Libraries.Network.NetworkRequest quorumNetworkRequest = (quorum.Libraries.Network.NetworkRequest) me_;

        URL Url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        String response = "";
        while ((line = in.readLine()) != null) {
            response += line + '\n';
        }
        in.close();
//        quorumNetworkRequest.SetResponse(response);
    }
    
    public void Get(String url, String params) throws Exception {
        String formattedURL = url + "?" + params;
        System.out.println(formattedURL);
        Get(formattedURL);
    }
}
