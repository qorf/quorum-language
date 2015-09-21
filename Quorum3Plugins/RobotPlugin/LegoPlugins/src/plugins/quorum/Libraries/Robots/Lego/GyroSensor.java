package plugins.quorum.Libraries.Robots.Lego;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
    public Object me_ = null;
    private lejos.hardware.sensor.EV3GyroSensor sensor;
    private SampleProvider rotationSP;
    private SampleProvider speedSP;
    private final float[] sample = new float[1];
    
    public double GetRotation() {
        if (rotationSP == null)
            rotationSP = sensor.getAngleMode();
        rotationSP.fetchSample(sample, 0);
        return sample[0];
    }
    
    public double GetSpeed() {
        //WHEN WE GET THE SENSOR...
        //should normalize this to be 1-100 based on value range
        //possibly changing the return type to an integer
        //that way it's consistent with Motor's GetSpeed()
        if (speedSP == null)
            speedSP = sensor.getRateMode();
        speedSP.fetchSample(sample, 0);
        return sample[0];
    }
    
    public void Reset() {
        sensor.reset();
    }
}
