/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

import cbccore.create.CreateConnectException;

/**
 *
 * @author sahana
 */
public class Robot {
    public java.lang.Object $me = null;
    
    cbccore.create.Create r;
    //cbccore.create.CreateConnectException e;
    
    public void SetConnection() throws CreateConnectException
    {
        r.connect();
        //throws
        RuntimeException runtimeException = new RuntimeException("Compiled Code");
        r.driveDirect(250, 250);
    }
    
    //public void connect() throws CreateConnectException {
      ///  compiled code
        //throw new RuntimeException("Compiled Code");
    //}
    
    public void Disconnect()
    {
        r.disconnect();
    }
    
}
