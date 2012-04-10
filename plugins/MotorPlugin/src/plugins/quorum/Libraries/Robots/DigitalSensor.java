/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Robots;

/**
 *
 * @author sahana
 */

import cbccore.Device;

public class DigitalSensor {
    public java.lang.Object $me = null;
    cbccore.low.Sensor s = null;
    private int p;
    
    public void SetPort(int port)
    {
        s = new cbccore.low.Sensor();
        p=port;
    }
    
    public int GetValue()
    {
        return Device.getLowSensorController().digital(p);
    }
   
    public void SetOutput(int value)
    {
        Device.getLowSensorController().set_digital_output_value(p, value);
    }
}
