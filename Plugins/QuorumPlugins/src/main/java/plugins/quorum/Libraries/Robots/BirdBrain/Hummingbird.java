package plugins.quorum.Libraries.Robots.BirdBrain;

/**
 * This code is a modified version of code freely available at https://github.com/BirdBrainTechnologies/BirdBrain-Java-Library.
 * Contributed to the Quorum language project under the BSD clause 3 license.
 * 
 * This class extends the Robot class to incorporate functions to control the inputs and outputs
 * of the Hummingbird Bit. It includes methods to set the values of motors and LEDs, as well
 * as methods to read the values of the sensors.
 * 
 * Mike Yuan and Bambi Brewer, BirdBrain Technologies LLC
 * November 2018
 */
public class Hummingbird extends Robot {

	/**
     * Default constructor for the library. Set the default device to be A.
     */
    public Hummingbird() {
          deviceInstance = "A";
          if (!isConnectionValid()) System.exit(0);
          if (!isHummingbird()) System.exit(0);
    }

   
    /**
     * General constructor for the library. Set the device to be "A", "B", or "C".
     *
     * @param device The letter corresponding to the Hummingbird device, which much be "A", "B", or "C". 
     * The letter that identifies the Hummingbird device is assigned by the BlueBird Connector.
     *      */
    public Hummingbird(String device) {
    	if (!((device == "A")||(device == "B")||(device == "C"))) {
        	System.out.println("Error: Device must be A, B, or C.");
        	System.exit(0);
        } else {
        	deviceInstance = device;
        	if (!isConnectionValid()) System.exit(0);
        	if (!isHummingbird()) System.exit(0);
        }
    }
    
    /** 
     * Return true if the device is a Hummingbird.
     */
    private boolean isHummingbird() {
        StringBuilder newURL = new StringBuilder(baseUrl);
        String testURL = (newURL.append("in/isHummingbird/static/")
                .append(deviceInstance)).toString();

        String stringResponse = sendHttpRequest(testURL);
        if (stringResponse.equals("false")) {
            System.out.println("Error: Device " + deviceInstance + " is not a Hummingbird");
            return false;
        } else if (stringResponse.equals("Not Connected")) {
            System.out.println("Error: Device " + deviceInstance + " is not connected.");
            return false;
        } else {
            return true;
        }
    }

    /* This function checks whether a port is within the given bounds. It returns a boolean value 
	   that is either true or false and prints an error if necessary. */
	protected boolean isPortValid(int port, int portMax) {
		if ((port < 1) || (port > portMax)) {
			System.out.println("Error: Please choose a port value between 1 and " + portMax);
			return false;
		}
		else
			return true;	
	}

    /**
     * setPositionServo sets the positionServo at a given port to a specific angle.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port     The port that the position servo is attached to. (Range: 1-4)
     * @param position The angle of the position servo. (Range: 0-180)
     */
    public void SetPositionServo(int port, int angle) {
    	if (!isPortValid(port,4)) {		// Check that port is valid
        	return; 
        } 
        
    	angle = clampParameterToBounds(angle, 0, 180);
        int degrees = (int) (angle * 254.0 / 180.0);
        
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String servoUrl = (resultUrl.append("out/")
                .append("servo/")
                .append(Integer.toString(port) + "/")
                .append(Integer.toString(degrees) + "/")
                .append(deviceInstance)).toString();
           
        httpRequestOut(servoUrl);
    }

    /**
     * setRotationServo sets the rotationServo at a given port to a specific speed.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port  The port that the rotation servo is attached to. (Range: 1-4)
     * @param speed The speed of the rotation servo. (Range: -100-100)
     */
    public void SetRotationServo(int port, int speed) {
    	if (!isPortValid(port,4)) {		// Check that port is valid
        	return; 
        }
    	
    	speed = clampParameterToBounds(speed,-100,100);
    	
    
        if ((speed > -10) && (speed < 10)) {	// dead zone to turn off the motor
            speed = 255;
        } else {	// Scale the speed so that it is semi-linear
            speed = ((speed * 23) / 100) + 122;
        }
            
        // Create the http command
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String rotationUrl = (resultUrl.append("out/")
                .append("rotation/")
                .append(Integer.toString(port) + "/")
                .append(Integer.toString(speed) + "/")
                .append(deviceInstance)).toString();
        
        httpRequestOut(rotationUrl);
            
    }

    /**
     * setLED sets the LED at a given port to a specific light intensity.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port      The port that the LED is attached to. (Range: 1-3)
     * @param intensity The intensity of the LED. (Range: 0-100)
     */
    public void SetLED(int port, int intensity) {
    	if (!isPortValid(port,3)) {		// Check that port is valid
        	return; 
        } 
    	
    	intensity = clampParameterToBounds(intensity,0,100);
    	
    	// Scale and send http request
        intensity = (int) (intensity * 255.0 / 100.0);
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String ledUrl = (resultUrl.append("out/")
                .append("led/")
                .append(Integer.toString(port) + "/")
                .append(Integer.toString(intensity) + "/")
                .append(deviceInstance)).toString();
            
        httpRequestOut(ledUrl);
    }

    /**
     * setTriLED sets the triLED at a given port to a specific color.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port           The port that the LED is attached to. (Range: 1-2)
     * @param redIntensity   The intensity of red light of the triLED. (Range: 0-100)
     * @param greenIntensity The intensity of green light of the triLED. (Range: 0-100)
     * @param blueIntensity  The intensity of blue light of the triLED. (Range: 0-100)
     */
    public void SetTriLED(int port, int redIntensity, int greenIntensity, int blueIntensity) {
    	if (!isPortValid(port,2)) {		// Check that port is valid
        	return; 
        }
    	redIntensity = clampParameterToBounds(redIntensity,0,100);
    	greenIntensity = clampParameterToBounds(greenIntensity,0,100);
    	blueIntensity = clampParameterToBounds(blueIntensity,0,100);
    	
    	// Scale
        redIntensity = (int) (redIntensity * 255.0 / 100.0);
        greenIntensity = (int) (greenIntensity * 255.0 / 100.0);
        blueIntensity = (int) (blueIntensity * 255.0 / 100.0);

        // Build http request
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String triLedUrl = (resultUrl.append("out/")
                .append("triled/")
                .append(Integer.toString(port) + "/")
                .append(Integer.toString(redIntensity) + "/")
                .append(Integer.toString(greenIntensity) + "/")
                .append(Integer.toString(blueIntensity) + "/")
                .append(deviceInstance)).toString();
        httpRequestOut(triLedUrl);
          
    }

    /**
     * getSensorValue returns the raw sensor value at a given port
     *
     * @param port The port that the sensor is attached to. (Range: 1-3)
     */
    private int getSensorValue(int port) {
    	if (!isPortValid(port,3)) {		// Check that port is valid
        	return -1; 
        }
    	StringBuilder resultUrl = new StringBuilder(baseUrl);
        String sensorUrl = (resultUrl.append("in/")
                .append("sensor/")
                .append(Integer.toString(port) + "/")
                .append(deviceInstance)).toString();
        return (int) httpRequestInDouble(sensorUrl); 	
    }

    /**
     * getLight returns light sensor value at a given port after processing the raw sensor value retrieved.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port The port that the light sensor is attached to. (Range: 1-3)
     */
    public int GetLight(int port) {   	
        int sensorResponse = getSensorValue(port);
        return (int) (sensorResponse * 100.0 / 255.0);
    }

    /**
     * getSound returns sound sensor value at a given port after processing the raw sensor value retrieved.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port The port that the sound sensor is attached to. (Range: 1-3)
     */
    public int GetSound(int port) {
    	int sensorResponse = getSensorValue(port);
        return (int) (sensorResponse * 200.0 / 255.0);
    }

    /**
     * getDistance returns distance sensor value at a given port after processing the raw sensor value retrieved.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port The port that the distance sensor is attached to. (Range: 1-3)
     */
    public int GetDistance(int port) {
    	int sensorResponse = getSensorValue(port);
        return (int) (sensorResponse * 117.0 / 100.0);
    }

    /**
     * getDial returns dial value at a given port after processing the raw sensor value retrieved.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port The port that the dial is attached to. (Range: 1-3)
     */
    public int GetDial(int port) {
    	int sensorResponse = getSensorValue(port);
        int processedResponse = (int) (sensorResponse * 100.0 / 230.0);
        return processedResponse >= 100 ? 100 : processedResponse;
    }
    
    /**
     * getVoltage returns voltage value at a given port after processing the raw sensor value retrieved.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param port The port that the dial is attached to. (Range: 1-3)
     */
    public double GetVoltage(int port) {
    	int sensorResponse = getSensorValue(port);
        return (sensorResponse * 3.3 / 255);
    }

}
