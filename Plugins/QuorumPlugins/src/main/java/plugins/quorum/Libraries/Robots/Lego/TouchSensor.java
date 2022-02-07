package plugins.quorum.Libraries.Robots.Lego;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class TouchSensor {
    public Object me_ = null;
    private lejos.hardware.sensor.EV3TouchSensor sensor;
    private SampleProvider sp;
    private float[] sample;
    
    public void SetPort(int portNumber) {
        switch (portNumber) {
            case 1:
                sensor = new EV3TouchSensor(SensorPort.S1);
                break;
            case 2:
                sensor = new EV3TouchSensor(SensorPort.S2);
                break;
            case 3:
                sensor = new EV3TouchSensor(SensorPort.S3);
                break;
            case 4:
                sensor = new EV3TouchSensor(SensorPort.S4);
                break;
            default:
                //exception goes here
        }
        sp = sensor.getTouchMode();
        sample = new float[sensor.sampleSize()];
    }
    
    public boolean IsPressed() {
        sp.fetchSample(sample, 0);
        return sample[0] != 0; //sample[0] == 0 means not pressed, return false
    }
}
