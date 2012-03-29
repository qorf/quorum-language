/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;
import cbccore.Botball;
import cbccore.Device;

/**
 *
 * @author sahana
 */
public class CBC {
    public java.lang.Object $me = null;
    cbccore.Botball cbc = new cbccore.Botball();
    cbccore.sensors.analog.Analog s = null;     
    
    public void ShutDownIn(double seconds)//tested and works 
    {
        cbc.shutDownIn(seconds);
    }
    
    public void Sleep(double s) //tested and works //throws InterruptedException
    {
        try{
            int seconds = (int) s;
            Thread.currentThread().sleep(seconds*1000);}
        catch(InterruptedException ie){}
    }
    
    //public void RunFor(double seconds)
    //{
      //  Device.
                //LowCreateController().get_create_lbump(lag1);
   // }
    
    public void IsLightinPort(int port) //tested and works 
    {
        s = new cbccore.sensors.analog.Analog(port);
        cbc.waitForLight(s);
    }
}
