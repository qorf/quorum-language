package plugins.quorum.Libraries.Robots.BirdBrain;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This code is a modified version of code freely available at https://github.com/BirdBrainTechnologies/BirdBrain-Java-Library.
 * Contributed to the Quorum language project under the BSD clause 3 license.
 * 
 * This is an abstract class that is inherited by Microbit.java, Hummingbird.java and Finch.java.
 * It includes methods to print on the micro:bit LED array or set those LEDs individually. It also
 * contains methods to read the values of the micro:bit accelerometer and magnetometer.
 * 
 * Mike Yuan and Bambi Breewer, BirdBrain Technologies LLC
 * November 2018
 */
abstract class Robot {

    //Quorum variable
    public java.lang.Object me_ = null;


	// Variables used to make http request to control the micro:bit (and Hummingbird Bit)
    protected HttpURLConnection connection = null;
    protected static String baseUrl = "http://127.0.0.1:30061/hummingbird/";
    protected URL requestUrl;
    
    protected String deviceInstance;		// A, B, or C
    
    // String variables used to return the orientation of the micro:bit
    private static final String SCREEN_UP = "Screen%20Up";
    private static final String SCREEN_DOWN = "Screen%20Down";
    private static final String TILT_LEFT = "Tilt%20Left";
    private static final String TILT_RIGHT = "Tilt%20Right";
    private static final String LOGO_UP = "Logo%20Up";
    private static final String LOGO_DOWN = "Logo%20Down";
    private static final String SHAKE = "Shake";

    private String outputError = "Error: Could not set output on the device ";
	private String inputError = "Error: Could not read sensor on the device ";
	
	protected boolean[] displayStatus = new boolean[25];

	protected String magRequest = "Magnetometer";
	protected String accelRequest = "Accelerometer";
	protected String compassRequest = "Compass";

    
    /* This function tests a connection by attempting to read whether or not the micro:bit is shaking. 
     * Return true if the connection is good and false otherwise. 
     */
    protected boolean isConnectionValid() {

         StringBuilder newURL = new StringBuilder(baseUrl);
         String testURL = (newURL.append("in/")
                 .append("orientation/")
                 .append(SHAKE + "/")
                 .append(deviceInstance)).toString();

        String stringResponse = sendHttpRequest(testURL);
        if (stringResponse.equals("Not Connected")) {
            return false;
        } else {
            return true;
        }
    }
    
    /* This function checks whether an input parameter is within the given bounds. If not, it prints
	   a warning and returns a value of the input parameter that is within the required range.
	   Otherwise, it just returns the initial value. */
    protected int clampParameterToBounds(int parameter, int inputMin, int inputMax) {
    	if ((parameter < inputMin) || (parameter > inputMax)) {
    		System.out.println("Warning: Please choose a parameter between " + inputMin + " and " + inputMax);
    		return Math.max(inputMin, Math.min(inputMax,  parameter));
    	} else
    		return parameter;
    }
    
    /* This function checks whether an input parameter is within the given bounds. If not, it prints
	   a warning and returns a value of the input parameter that is within the required range.
	   Otherwise, it just returns the initial value. */
	protected double clampParameterToBounds(double parameter, double inputMin, double inputMax) {
	 	if ((parameter < inputMin) || (parameter > inputMax)) {
	 		System.out.println("Warning: Please choose a parameter between " + inputMin + " and " + inputMax);
	 		return Math.max(inputMin, Math.min(inputMax,  parameter));
	 	} else
	 		return parameter;
	}

    /**
     * Create a url string given a list of arguments to include
     * @param args
     * @return
     */
    protected String getUrl(String[] args) {
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        for (String arg : args) {
            resultUrl.append(arg + "/");
        }
        String url = resultUrl.toString();
        return url.substring(0, url.length() - 1); //remove the trailing '/'
    }

    /**
     * General function for sending an http request and returning the response
     * @param URLRequest
     * @return String response
     */
	protected String sendHttpRequest(String URLRequest) {
        long requestStartTime = System.currentTimeMillis();
	    String responseString = "Not Connected";
        try {
            requestUrl = new URL(URLRequest);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            //connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                if (!response.toString().contains("Not Connected")) {
                    responseString = response.toString();
                }
            } else {
                System.out.println(inputError);
            }

        } catch (IOException e) {
            System.out.println("Error sending http request: " + e.getMessage());
        } finally {
            if (responseString.equals("Not Connected")) {
                System.out.println("Error: Device " + deviceInstance + " is not connected");
            }
            Disconnect();
        }
        //If too many requests get sent too quickly, macOS gets overwhelmed and starts to insert pauses.
        while(System.currentTimeMillis() < requestStartTime + 5) {}

        return responseString;

	}

	/* This function sends http requests that set outputs (lights, motors, buzzer, 
	 * etc.) on the micro:bit and Hummingbird. */
    protected void httpRequestOut(String URLRequest) {
        String response = sendHttpRequest(URLRequest);
    }
    
    /* This function sends http requests that return a double response from a sensor. */
    protected double httpRequestInDouble(String URLRequest) {
        String stringResponse = sendHttpRequest(URLRequest);
        try {
            double value = Double.parseDouble(stringResponse);
            return value;
        } catch(Exception e) {
            System.out.println("Error: " + stringResponse);
            return -1;
        }
    }
    
    /* This function sends http requests that return a boolean response from a sensor. */
    protected boolean httpRequestInBoolean(String URLRequest) {
        String stringResponse = sendHttpRequest(URLRequest);
        if (!stringResponse.equalsIgnoreCase("true")
                && !stringResponse.equalsIgnoreCase("false")) {
            System.out.println("Error: " + stringResponse);
            return false;
        } else {
            return (stringResponse.equalsIgnoreCase("true"));
        }
    }

    
    /**
     * Print() lets the LED Array display a given message.
     *
     * @param message The message that will be displayed on the LED Array.
     */
    public void Print(String message) {
       
        // Warn the user if there are any special characters. Note that we don't use isCharacterOrDigit() because we can only display English characters
        char letter;
        for (int i = 0; i < message.length(); i++) {
        	letter = message.charAt(i);
        	if (!(((letter >= 'a') && (letter <= 'z')) || ((letter >= 'A') && (letter <= 'Z')) || ((letter >= '0') && (letter <= '9')) || (letter == ' '))) {
        		System.out.println("Warning: Many special characters cannot be printed on the LED display");
        	}
        }
    	for (int i = 0; i < displayStatus.length; i++) displayStatus[i] = false;
 			
    	// Get rid of spaces
    	message = message.replace(" ", "%20");
    	
    	// Build http request
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String printUrl = (resultUrl.append("out/")
                    .append("print/")
                    .append(message + "/")
                    .append(deviceInstance)).toString();
         httpRequestOut(printUrl);
            
    }

    /**
     * Output() - Same as Print(). Added for Quorum compatibility. 
     *
     * @param message The message that will be displayed on the LED Array.
     */
    public void Output(String message) {
        this.Print(message);
    }

    /**
     * setDisplay lets the LED Array display a pattern based on an array of 1s and 0s.
     *
     * @param ledValues The list of integers that the function takes in to set the LED Array.
     *                1 means on and 0 means off.
     */
    private void setDisplay(int[] ledValues) {
    	StringBuilder resultUrl = new StringBuilder(baseUrl);
        int ledLen = ledValues.length;
        
        for (int i = 0; i < ledLen; i++){
        	ledValues[i] = clampParameterToBounds(ledValues[i],0,1);
        }
        
        if (ledLen != 25) {
        	System.out.println("Error: setDisplay() requires a int array of length 25");
        	return;
        }         
        
        /* For the http request, we need to convert the 0s and 1s to boolean values. We can do this while
         * also ensuring that the user only used 0 and 1.
         */
    	for (int i = 0; i < ledLen; i++){
    		if (ledValues[i] == 1)
                displayStatus[i] = true;
            else 
                displayStatus[i] = false;
                
        }      
    		
        resultUrl = resultUrl.append("out/")
                    .append("symbol/").append(deviceInstance.toString()+"/");
          
        for (int i = 0; i < ledLen; i++) {
            resultUrl = resultUrl.append(String.valueOf(displayStatus[i]) + "/");
        }
           
        String symbolUrl = resultUrl.append(deviceInstance).toString();
        symbolUrl = symbolUrl.substring(0, symbolUrl.length() - 1);
        
        httpRequestOut(symbolUrl);
    }

    /**
     * Since quorum doesn't have primitive arrays, we will specify the 
     * led values as a string
     */
    public void SetDisplay(String ledValues) {
        if (ledValues.length() != 25) {
            System.out.println("Error: setDisplay() requires text with 25 characters");
            return;
        }
        int[] array = new int[25];
        for (int i = 0; i < 25; i++) {
            char c = ledValues.charAt(i);
            int x = Character.getNumericValue(c);
            if (!Character.isDigit(c) || !(x == 0 || x == 1)) {
                System.out.println("Error: setDisplay() - every character must be a 1 (led on) or 0 (led off)");
                return;
            }
            array[i] = x;
        }
        setDisplay(array);
    }
    
    /* This function turns on or off a single LED on the micro:bit LED array. 
     * 
     * @param x The column of the LED (1-5)
     * @param y The row of the LED (1-5)
     * @param value The value of the LED (0 for off, 1 for on)
     * */
    public void SetPoint(int row, int column, int value) {
    	
    	StringBuilder resultUrl = new StringBuilder(baseUrl);
    	
    	row = clampParameterToBounds(row, 1, 5);
    	column = clampParameterToBounds(column, 1, 5);
    	value = clampParameterToBounds(value, 0, 1);
    		
    	// Find the position of this led in displayStatus
		int position = (row - 1)*5 + (column-1);
		/* For the http request, we need to convert the 0 or 1 to a boolean. We can do this warning if the user didn't use 0 or 1 for the value
         */
		if (value == 1)
			displayStatus[position] = true;
		else 
			displayStatus[position] = false;
		
        resultUrl = resultUrl.append("out/")
                .append("symbol/").append(deviceInstance.toString()+"/");
      
        for (int i = 0; i < displayStatus.length; i++) {
            resultUrl = resultUrl.append(String.valueOf(displayStatus[i]) + "/");
        }
       
        String symbolUrl = resultUrl.append(deviceInstance).toString();
        symbolUrl = symbolUrl.substring(0, symbolUrl.length() - 1);

        httpRequestOut(symbolUrl);
    }

    /**
     * Set the buzzer to play the given note for the given duration
     * @param note - midi note number to play (Range: 32 to 135)
     * @param beats - duration in beats (Range: 0 to 16); each beat is one second
     */
    public void PlayNote(int note, double beats) {
        note = clampParameterToBounds(note, 32, 135);
        beats = clampParameterToBounds(beats,0,16);
        beats = beats * 1000;

        String [] urlArgs = {"out", "playnote", Integer.toString(note), Integer.toString((int)beats), deviceInstance};
        String url = getUrl(urlArgs);
        httpRequestOut(url);
    }
   
    /**
     * getAccelerationInDirs returns acceleration value in a specified direction.
     *
     * @param dir The direction of which the acceleration will be returned.
     */
    private double getAccelerationInDirs(String dir) {
        
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String acclUrl = (resultUrl.append("in/")
                .append(accelRequest + "/")
                .append(dir + "/")
                .append(deviceInstance)).toString();

        return httpRequestInDouble(acclUrl);     
    }

    /**
     * getMagnetometerValInDirs returns magnetometer value in a specified direction.
     *
     * @param dir The direction of which the magnetometer value will be returned.
     */
    private double getMagnetometerValInDirs(String dir) {
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String magUrl = (resultUrl.append("in/")
                .append(magRequest + "/")
                .append(dir + "/")
                .append(deviceInstance)).toString();

        return httpRequestInDouble(magUrl);
    }

    /**
     * getAcceleration returns accelerations in 3 directions (X,Y,Z) in m/s^2.
     *
     * @return the accelerations in 3 directions (X,Y,Z) in m/s^2.
     */
    public double[] getAcceleration() {
        double[] accelerations = new double[3];
        double resX = getAccelerationInDirs("X");
        double resY = getAccelerationInDirs("Y");
        double resZ = getAccelerationInDirs("Z");
        accelerations[0] = resX;
        accelerations[1] = resY;
        accelerations[2] = resZ;
        return accelerations;
    }

    /**
     * getMagnetometer returns magnetometer values in 3 directions (X,Y,Z) in microT.
     *
     * @return the magnetometer values in 3 directions (X,Y,Z) in microT.
     */
    public int[] getMagnetometer() {
        int[] magnetometerVals = new int[3];
        double resX = getMagnetometerValInDirs("X");
        double resY = getMagnetometerValInDirs("Y");
        double resZ = getMagnetometerValInDirs("Z");
        magnetometerVals[0] = (int)Math.round(resX);
        magnetometerVals[1] = (int)Math.round(resY);
        magnetometerVals[2] = (int)Math.round(resZ);
        return magnetometerVals;
    }

    /**
     * Since quorum doesn't have primitive arrays, we will return just
     * one axis instead of all 3
     */
    public double GetAcceleration(String axis) {
        return getAccelerationInDirs(axis.toUpperCase());
    }
    public int GetMagnetometer(String axis) {
        return (int)Math.round(getMagnetometerValInDirs(axis.toUpperCase()));
    }

    /**
     * GetCompass returns the direction in degrees from north.
     *
     * @return the direction in degrees. (Range: 0-360)
     */
    public int GetCompass() {
        
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String compassUrl = (resultUrl.append("in/")
                .append(compassRequest + "/")
                .append(deviceInstance)).toString();

        return (int) httpRequestInDouble(compassUrl);
       
    }

    /**
     * GetButton() takes in a button and checks whether it is pressed.
     * The function shows a warning dialog if the inputs are not in the specified range.
     *
     * @param button the button that will be checked whether is it pressed or not. (Range: "A", "B")
     * @return true if the button is pressed and false otherwise.
     */
    public boolean GetButton(String button) {
        button = button.toUpperCase();
        if (!(button.equals("A") || button.equals("B") || button.equals("LOGO"))) {
            System.out.println("Error: Please choose button A, B, or Logo");
            return false;
        }
        
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String buttonUrl = (resultUrl.append("in/")
                .append("button/")
                .append(button + "/")
                .append(deviceInstance)).toString();
        
        return httpRequestInBoolean(buttonUrl);
          
    }

    /**
     * GetSound() returns the current sound level from the micro:bit sound sensor
     * @return sound level
     */
    public int GetSound() {
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String soundUrl = (resultUrl.append("in/")
                .append("V2sensor/Sound/")
                .append(deviceInstance)).toString();

        return (int) Math.round(httpRequestInDouble(soundUrl));
    }

    /**
     * GetTemperature() returns the current temperature in degrees Celsius from the micro:bit temperature sensor
     * @return temperature in degrees Celsius
     */
    public int GetTemperature() {
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String tempUrl = (resultUrl.append("in/")
                .append("V2sensor/Temperature/")
                .append(deviceInstance)).toString();

        return (int) Math.round(httpRequestInDouble(tempUrl));
    }

    /**
     * getOrientationBoolean checks whether the device currently being held to a specific orientation or shaken.
     *
     * @param orientation The orientation that will be checked.
     * @return "true" if the device is held to the orientation and false otherwise.
     */
    private boolean getOrientationBoolean(String orientation) {
    	
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String orientationUrl = (resultUrl.append("in/")
                .append("orientation/")
                .append(orientation + "/")
                .append(deviceInstance)).toString();
      
        return httpRequestInBoolean(orientationUrl);  
        
    }

    /* isShaking() tells you whether the micro:bit is being shaken. 
     * 
     * @return a boolean value telling you the shake state
     * */
    public boolean IsShaking() {
    	return getOrientationBoolean(SHAKE);
    }
    /**
     * getOrientation() provides information about the device's current orientation.
     *
     * @return the orientation of the device. (Range: Screen up, Screen down, Tilt left, Tilt right, Logo up, Logo down)
     */
    public String GetOrientation() {
        boolean screenUp = getOrientationBoolean(SCREEN_UP);
        boolean screenDown = getOrientationBoolean(SCREEN_DOWN);
        boolean tiltLeft = getOrientationBoolean(TILT_LEFT);
        boolean tiltRight = getOrientationBoolean(TILT_RIGHT);
        boolean logoUp = getOrientationBoolean(LOGO_UP);
        boolean logoDown = getOrientationBoolean(LOGO_DOWN);
        
        if (screenUp) return "Screen up";
        else if (screenDown) return "Screen down";
        else if (tiltLeft) return "Tilt left";
        else if (tiltRight) return "Tilt right";
        else if (logoUp) return "Logo up";
        else if (logoDown) return "Logo down";
        return "In between";
    }

    /* Pauses the program for a time in seconds. */
    public void Pause(double numSeconds) {
    	
    	double milliSeconds = 1000*numSeconds;
    	try {
            Thread.sleep(Math.round(milliSeconds));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * disconnect closes the http connection to save memory
     */
    public void Disconnect() {
        if (connection != null) {
            connection.disconnect();
            connection = null;
        }
    }
    /* stopAll() turns off all the outputs. */
    public void StopAll() {
    
    	Pause(0.1);         // Hack to give stopAll() time to act before the end of a program
	
		// Build http request to turn off all the outputs
        StringBuilder resultUrl = new StringBuilder(baseUrl);
        String stopUrl = (resultUrl.append("out/")
                .append("stopall/")
                .append(deviceInstance)).toString();
        
        httpRequestOut(stopUrl);

        //Set the current display status to all off
        for (int i = 0; i < displayStatus.length; i++) displayStatus[i] = false;
    }
}
