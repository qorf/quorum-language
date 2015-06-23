package plugins.quorum.Libraries.Robots.Lego;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class InfraredSensor {
    public Object me_ = null;
    private lejos.hardware.sensor.EV3IRSensor sensor;   //the IR sensor object
    private SampleProvider distanceSampleProvider;
    private float[] distanceModeSample;
    private SampleProvider seekSampleProvider;
    private float[] seekModeSample;                     //array contains 4 pairs of values for each channel: [direction][distance]
    
    //essentially what the constructor would be
    public void SetPort(int portNumber) {
        switch (portNumber) {
            case 1:
                sensor = new EV3IRSensor(SensorPort.S1);
                break;
            case 2:
                sensor = new EV3IRSensor(SensorPort.S2);
                break;
            case 3:
                sensor = new EV3IRSensor(SensorPort.S3);
                break;
            case 4:
                sensor = new EV3IRSensor(SensorPort.S4);
                break;
            default:
                //exception goes here
        }
    }
    
    public int GetDistance() {
        distanceSampleProvider = sensor.getDistanceMode();                      //used to detect distance from object
        distanceModeSample = new float[distanceSampleProvider.sampleSize()];    //float array with size based on mode type
        distanceSampleProvider.fetchSample(distanceModeSample, 0);              //get the sample
        return (int)distanceModeSample[0];
    }
    
    public int GetRemoteDirection(int channel) {
        seekSampleProvider = sensor.getSeekMode();                      //seek mode is for finding "beacons" aka remotes
        seekModeSample = new float[seekSampleProvider.sampleSize()];
        seekSampleProvider.fetchSample(seekModeSample, 0);
        float lejosNumber = seekModeSample[2 * channel - 2];                    //value will be -25 to 25, where 0 indicates the remote is straight ahead of the sensor
        return (int)(lejosNumber * 7.2);                                        //this converts the number to degrees ranging from -180 to 180
    }
    
    public int GetRemoteDistance(int channel) {
        seekSampleProvider = sensor.getSeekMode();
        seekModeSample = new float[seekSampleProvider.sampleSize()];
        seekSampleProvider.fetchSample(seekModeSample, 0);
        return (int)seekModeSample[2 * channel - 2 + 1];                //value will be 1 to 100
    }
    
    public int GetRemoteCommand(int channel) {
        return sensor.getRemoteCommand(channel-1);  //-1 because they use 0-3 to correspond to 1-4 on the remote
    }
}