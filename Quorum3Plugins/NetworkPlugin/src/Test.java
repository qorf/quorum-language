/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import plugins.quorum.Libraries.Network.*;

/*
 * @author patrick
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String url = "http://kitana.ddns.net/GETtest.php";
        String paramsJSON = "{\"name\":\"Fred Flintstone\",\"city\":\"Bedrock\"}";
        String paramsGET = "name=Fred%20Flintstone&city=Bedrock";
        NetworkRequest nr = new NetworkRequest();
        nr.Get(url);
        nr.Get(url, paramsGET);
    }
    
}
