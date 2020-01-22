//lejOS color map:
//RED = 0;
//GREEN = 1;
//BLUE = 2;
//YELLOW = 3;
//MAGENTA = 4;
//ORANGE = 5;
//WHITE = 6;
//BLACK = 7;
//PINK = 8;
//GRAY = 9;
//LIGHT_GRAY = 10;
//DARK_GRAY = 11;
//CYAN = 12;
//BROWN = 13;
//NONE = -1;

package plugins.quorum.Libraries.Robots.Lego;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {
    
    public Object me_ = null;
    private lejos.hardware.sensor.EV3ColorSensor sensor;
    private SampleProvider colorSP;
    private SampleProvider ambientSP;
    private SampleProvider reflectionSP;
    private SampleProvider rgbSP;
    private final float[] sample = new float[1];    //sample array for GetColor, GetLightLevel, GetReflection
    private final float[] rgbSample = new float[3]; //sample array for GetRGBLevels
    
    public void SetPort(int portNumber) {
        switch (portNumber) {
            case 1: sensor = new EV3ColorSensor(SensorPort.S1); break;
            case 2: sensor = new EV3ColorSensor(SensorPort.S2); break;
            case 3: sensor = new EV3ColorSensor(SensorPort.S3); break;
            case 4: sensor = new EV3ColorSensor(SensorPort.S4); break;
            default:
                //exception goes here
        }
        //the red "floodlight" gets turned on once the sensor is initialized for some reason
        //we can turn it off right away, but the light is still visible for a split second
        //should we just leave the light on?
//        sensor.setFloodlight(-1);
    }
    
    public String GetColor() {
        if (colorSP == null)
            colorSP = sensor.getColorIDMode();
        colorSP.fetchSample(sample, 0);
        switch ((int)sample[0]) {
            case 0: return "red";
            case 1: return "green";
            case 2: return "blue";
            case 3: return "yellow";
            case 6: return "white";
            case 7: return "black";
            case 13: return "brown";
        }
        return "none";
    }
    
    public double GetLightLevel() {
        if (ambientSP == null)
            ambientSP = sensor.getAmbientMode();
        ambientSP.fetchSample(sample, 0);
        return (double)sample[0];
    }
    
    public double GetReflectionLevel() {
        if (reflectionSP == null)
            reflectionSP = sensor.getRedMode();
        reflectionSP.fetchSample(sample, 0);
        return sample[0];
    }
    
    public void SetLightColor(int color) {
        sensor.setFloodlight(color); //will throw an error if not valid
    }
    
    public String GetLightColor() {
        switch (sensor.getFloodlight()) {
            case -1: return "none";
            case 0: return "red";
            case 2: return "blue";
            case 6: return "white";
        }
        return "none";
    }
    
    public boolean IsLightOn() {
        return sensor.isFloodlightOn();
    }
    
    public void SetLightOn(boolean on) {
        sensor.setFloodlight(on);
    }
    
    //GetRGB - returns array of RGB values (0-1)
    //RobotC returns each element through reference parameters
    public void FetchRedGreenBlueSample() {
        rgbSP = sensor.getRGBMode();
        rgbSP.fetchSample(rgbSample, 0); //populate float array
    }
    
    public double GetRedGreenBlueLevel(String color) {
        if (color.equals("r"))
            return (double)rgbSample[0];
        else if (color.equals("g"))
            return (double)rgbSample[1];
        else if (color.equals("b"))
            return (double)rgbSample[2];
        else
            return 0.0;
    }
}
