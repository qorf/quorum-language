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


public class AnalogSensor {
    public java.lang.Object $me = null;
    cbccore.low.Sensor s = null;
    private int p=0;
    
    public void SetPort(int port)
    {
        s = new cbccore.low.Sensor();
        p=port;
    }
   
    public int GetPort()
    {
        return p;
    }
    
    public int GetValue()
    {
        return Device.getLowSensorController().analog(p);
    }
    
    public int Get10BitValue()
    {
        return Device.getLowSensorController().analog10(p);
    }
    
    public void Reset()
    {
        if(p==0)
         Device.getLowSensorController().set_each_analog_state(1,0,0,0,0,0,0,0);
        if(p==1)
         Device.getLowSensorController().set_each_analog_state(0,1,0,0,0,0,0,0);
        if(p==2)
         Device.getLowSensorController().set_each_analog_state(0,0,1,0,0,0,0,0);
        if(p==3)
         Device.getLowSensorController().set_each_analog_state(0,0,0,1,0,0,0,0);
        if(p==4)
         Device.getLowSensorController().set_each_analog_state(0,0,0,0,1,0,0,0);
        if(p==5)
         Device.getLowSensorController().set_each_analog_state(0,0,0,0,0,1,0,0);
        if(p==6)
         Device.getLowSensorController().set_each_analog_state(0,0,0,0,0,0,1,0);
        if(p==7)
         Device.getLowSensorController().set_each_analog_state(0,0,0,0,0,0,0,1);
    }  
    
    public int GetDistance()
    {
        return Device.getLowSensorController().sonar(p);
    }
    
}

