package plugins.quorum.Libraries.Robots.Lego;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltrasonicSensor {
    public Object me_ = null;
    private lejos.hardware.sensor.EV3UltrasonicSensor sensor;
    private SampleProvider sampleProvider;
    private float[] sample;
    
    public void SetPort(int portNumber) {
        switch (portNumber) {
            case 1:
                sensor = new EV3UltrasonicSensor(SensorPort.S1);
                break;
            case 2:
                sensor = new EV3UltrasonicSensor(SensorPort.S2);
                break;
            case 3:
                sensor = new EV3UltrasonicSensor(SensorPort.S3);
                break;
            case 4:
                sensor = new EV3UltrasonicSensor(SensorPort.S4);
                break;
            default:
                //exception goes here
        }
        sensor.enable();
        sampleProvider = sensor.getDistanceMode();
        sample = new float[sensor.sampleSize()];
    }
    
    public double GetDistance() {
        sampleProvider.fetchSample(sample, 0);
        return (double)sample[0];
    }
}
