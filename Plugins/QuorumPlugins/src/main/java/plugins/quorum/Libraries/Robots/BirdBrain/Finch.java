package plugins.quorum.Libraries.Robots.BirdBrain;

/**
 * This code is a modified version of code freely available at https://github.com/BirdBrainTechnologies/BirdBrain-Java-Library.
 * Contributed to the Quorum language project under the BSD clause 3 license.
 * 
 * This class extends the Robot class to incorporate functions to control the inputs and outputs
 * of the Finch. It includes methods to set the values of motors and LEDs, as well
 * as methods to read the values of the sensors.
 *
 * Krissie Lauwers, BirdBrain Technologies LLC
 * October 2019
 */
public class Finch extends Robot {

    // String variables used to return the orientation of the finch
    private static final String BEAK_UP = "Beak%20Up";
    private static final String BEAK_DOWN = "Beak%20Down";
    private static final String TILT_LEFT = "Tilt%20Left";
    private static final String TILT_RIGHT = "Tilt%20Right";
    private static final String LEVEL = "Level";
    private static final String UPSIDE_DOWN = "Upside%20Down";

    /**
     * Default constructor for the library. Set the default device to be A.
     */
    public Finch() {
        this("A");
    }

    /**
     * General constructor for the library. Set the device to be "A", "B", or "C".
     *
     * @param device The letter corresponding to the Hummingbird device, which much be "A", "B", or "C".
     * The letter that identifies the Hummingbird device is assigned by the BlueBird Connector.
     */
    public Finch(String device) {
        if (!((device == "A")||(device == "B")||(device == "C"))) {
            System.out.println("Error: Device must be A, B, or C.");
            System.exit(0);
        } else {
            deviceInstance = device;
            if (!isConnectionValid()) System.exit(0);
            if (!isFinch()) System.exit(0);

            //The finch has separate requests for these so that the results returned
            // are in the finch reference frame.
            magRequest = "finchMag";
            accelRequest = "finchAccel";
            compassRequest = "finchCompass/static";
        }
    }

    /**
     * This function sends a request to BlueBird Connector to determine whether or not
     * the device is a Finch.
     */
    private boolean isFinch() {
        StringBuilder newURL = new StringBuilder(baseUrl);
        String testURL = (newURL.append("in/isFinch/static/")
                .append(deviceInstance)).toString();

        String stringResponse = sendHttpRequest(testURL);
        if (stringResponse.equals("false")) {
            System.out.println("Error: Device " + deviceInstance + " is not a Finch");
            return false;
        } else if (stringResponse.equals("Not Connected")) {
            System.out.println("Error: Device " + deviceInstance + " is not connected.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Formats the direction string for sending to the bluebird connector.
     * If the selection made is not acceptable, returns 'Neither'.
     * @param direction
     * @return
     */
    private String formatForwardBackward(String direction) {
        switch (direction) {
            case "F":
            case "f":
            case "Forward":
            case "forward":
                return "Forward";
            case "B":
            case "b":
            case "Backward":
            case "backward":
                return "Backward";
            default:
                System.out.println("Error: Please specify direction as F or B.");
                return "Neither";
        }
    }

    /**
     * Formats the direction string for sending to the bluebird connector.
     * If the selection made is not acceptable, returns 'Neither'.
     * @param direction
     * @return
     */
    private String formatRightLeft(String direction) {
        switch (direction) {
            case "R":
            case "r":
            case "Right":
            case "right":
                return "Right";
            case "L":
            case "l":
            case "Left":
            case "left":
                return "Left";
            default:
                System.out.println("Error: Please specify direction as R or L.");
                return "Neither";
        }
    }

    /**
     * Send a command to move the finch and wait until the finch has finished
     * its motion to return. Used by setMove and setTurn.
     * @param motion - Move or turn
     * @param direction - forward, backward, right or left
     * @param length - Length of travel (distance or angle)
     * @param speed - Speed as a percent (Range: 0 to 100)
     */
    private void moveFinchAndWait(String motion, String direction, double length, double speed){
        String [] imUrlArgs = {"in", "finchIsMoving", "static", deviceInstance};
        String isMovingUrl = getUrl(imUrlArgs);
        boolean isMoving = httpRequestInBoolean(isMovingUrl);
        boolean wasMoving = isMoving;
        long commandSendTime = System.currentTimeMillis();

        String [] urlArgs = {"out", motion, deviceInstance, direction, Double.toString(length), Double.toString(speed)};
        String url = getUrl(urlArgs);
        httpRequestOut(url);

        while (!((System.currentTimeMillis() > commandSendTime + 500 || wasMoving) && !isMoving)){
            wasMoving = isMoving;
            Pause(0.01);
            isMoving = httpRequestInBoolean(isMovingUrl);
        }
    }

    /**
     * Sends a request for the finch to move forward or backward a given distance
     * at a given speed. Direction should be specified as "F" or "B".
     * @param direction - F or B for forward or backward
     * @param distance - Distance to travel in cm. (Range: 0 to 500)
     * @param speed - Speed as a percent (Range: 0 to 100)
     */
    public void SetMove(String direction, double distance, double speed) {
        String dir = formatForwardBackward(direction);
        if (dir.equals("Neither")) { return; }

        distance = clampParameterToBounds(distance, -10000, 10000);
        speed = clampParameterToBounds(speed, 0, 100);

        moveFinchAndWait("move", dir, distance, speed);
    }

    /**
     * Sends a request for the finch to turn right or left to the given angle
     * at the given speed.
     * @param direction - R or L for right or left
     * @param angle - Angle of the turn in degrees (Range: 0 to 360)
     * @param speed - Speed of the turn as a percent (Range: 0 to 100)
     */
    public void SetTurn(String direction, double angle, double speed) {
        String dir = formatRightLeft(direction);
        if (dir.equals("Neither")) { return; }

        angle = clampParameterToBounds(angle, -360000, 360000);
        speed = clampParameterToBounds(speed, 0, 100);

        moveFinchAndWait("turn", dir, angle, speed);
    }

    /**
     * Set the right and left motors of the finch to the speeds given.
     * @param leftSpeed - Speed as a percent (Range: -100 to 100)
     * @param rightSpeed - Speed as a percent (Range: -100 to 100)
     */
    public void SetMotors(double leftSpeed, double rightSpeed) {
        leftSpeed = clampParameterToBounds(leftSpeed, -100, 100);
        rightSpeed = clampParameterToBounds(rightSpeed, -100, 100);

        String [] urlArgs = {"out", "wheels", deviceInstance, Double.toString(leftSpeed), Double.toString(rightSpeed)};
        String url = getUrl(urlArgs);
        httpRequestOut(url);
    }

    /**
     * Stop the finch motors
     */
    public void Stop() {
        String [] urlArgs = {"out", "stopFinch", deviceInstance};
        String url = getUrl(urlArgs);
        httpRequestOut(url);
    }

    /**
     * Private method to set led intensity. Used to set beak and tail leds.
     * @param port - led to set. 1 is beak. 2-5 are tail. 6 sets entire tail. (Range: 1 to 6)
     * @param redIntensity - red intensity (Range: 0 to 100)
     * @param greenIntensity - green intensity (Range: 0 to 100)
     * @param blueIntensity - blue intensity (Range: 0 to 100)
     */
    private void setTriLED(int port, int redIntensity, int greenIntensity, int blueIntensity) {
        if ((port < 1) || (port > 6)) {		// Check that port is valid
            return;
        }
        redIntensity = clampParameterToBounds(redIntensity,0,100);
        greenIntensity = clampParameterToBounds(greenIntensity,0,100);
        blueIntensity = clampParameterToBounds(blueIntensity,0,100);

        // Scale
        redIntensity = (int) (redIntensity * 255.0 / 100.0);
        greenIntensity = (int) (greenIntensity * 255.0 / 100.0);
        blueIntensity = (int) (blueIntensity * 255.0 / 100.0);

        String portString = Integer.toString(port);
        if (port == 6) { portString = "all"; }

        String [] urlArgs = {"out", "triled", portString, Integer.toString(redIntensity), Integer.toString(greenIntensity), Integer.toString(blueIntensity), deviceInstance};
        String url = getUrl(urlArgs);
        httpRequestOut(url);
    }

    /**
     * Set the finch beak to the given rgb color.
     * @param red - red intensity (Range: 0 to 100)
     * @param green - green intensity (Range: 0 to 100)
     * @param blue - blue intensity (Range: 0 to 100)
     */
    public void SetBeak(int red, int green, int blue) {
        setTriLED(1, red, green, blue);
    }

    /**
     * Set the specified tail led to the specified rgb color.
     * @param ledNum - led to set (Range: 1 to 4)
     * @param red - red intensity (Range: 0 to 100)
     * @param green - green intensity (Range: 0 to 100)
     * @param blue - blue intensity (Range: 0 to 100)
     */
    public void SetTail(int ledNum, int red, int green, int blue) {
        ledNum = clampParameterToBounds(ledNum, 1, 4);
        setTriLED(ledNum + 1, red, green, blue);
    }

    /**
     * Set all tail leds to the specified rgb color.
     * @param ledNum - String which must be specified as 'all'
     * @param red - red intensity (Range: 0 to 100)
     * @param green - green intensity (Range: 0 to 100)
     * @param blue - blue intensity (Range: 0 to 100)
     */
    public void SetTail(String ledNum, int red, int green, int blue) {
        if (!ledNum.equals("all") && !ledNum.equals("All") && !ledNum.equals("ALL")) {
            System.out.println("Error: Please specify tail led number or 'all'");
            return;
        }

        setTriLED(6, red, green, blue);
    }

    /**
     * Reset the finch encoder values to 0.
     */
    public void ResetEncoders() {
        String [] urlArgs = {"out", "resetEncoders", deviceInstance};
        String url = getUrl(urlArgs);
        httpRequestOut(url);
        Pause(0.2); //Give the finch a chance to reset before moving on
    }

    /**
     * Private function to get the value of a sensor
     * @param sensor - Light, Distance, Line, or Encoder
     * @param port - Right, Left, or static
     * @return - sensor value returned by bluebird connector or -1 in the case of a problem.
     */
    private double getSensor(String sensor, String port) {
        if (!port.equals("static")) {
            port = formatRightLeft(port);
        }
        if (port.equals("Neither")) { return -1; }
        String [] urlArgs = {"in", sensor, port, deviceInstance};
        String url = getUrl(urlArgs);
        return httpRequestInDouble(url);
    }

    /**
     * Get the current value of the right or left encoder
     * @param direction - R or L to specify right or left
     * @return - encoder value in rotations
     */
    public double GetEncoder(String direction) {
        double value = getSensor("Encoder", direction);
        value = Math.round(value * 100.0)/100.0;
        return value;
    }

    /**
     * Get the current value of the finch distance sensor
     * @return - the distance to the closest obstacle in cm
     */
    public int GetDistance() {
        double value = getSensor("Distance", "static");
        return (int) Math.round(value);
    }

    /**
     * Get the current value of the specified finch light sensor
     * @param direction - R or L to specify right or left
     * @return - brightness as a value 0-100
     */
    public int GetLight(String direction) {
        double value = getSensor("Light", direction);
        return (int) Math.round(value);
    }

    /**
     * Get the current value of the specified finch line sensor.
     * Return value is inverted (100 - value) so that more reflected
     * light = bigger number
     * @param direction - R or L to specify right or left
     * @return - brightness as a value 0-100
     */
    public int GetLine(String direction) {
        double value = getSensor("Line", direction);
        return (int)value;
    }

    /**
     * Checks whether or not the finch is in the given orientation.
     *
     * @param orientation - Orientation to check
     * @return - True if the finch is in the given orientation
     */
    private boolean getOrientationBoolean(String orientation) {
        String [] urlArgs = {"in", "finchOrientation", orientation, deviceInstance};
        String url = getUrl(urlArgs);
        return httpRequestInBoolean(url);
    }

    /**
     * getOrientation() provides information about the finch's current orientation.
     * This function overrides the function in the Robot class so that results are
     * in the finch reference frame.
     *
     * @return the orientation of the finch. (Range: Beak up, Beak down, Tilt left, Tilt right, Level, Upside down)
     */
    public String GetOrientation() {
        boolean beakUp = getOrientationBoolean(BEAK_UP);
        boolean beakDown = getOrientationBoolean(BEAK_DOWN);
        boolean tiltLeft = getOrientationBoolean(TILT_LEFT);
        boolean tiltRight = getOrientationBoolean(TILT_RIGHT);
        boolean level = getOrientationBoolean(LEVEL);
        boolean upsideDown = getOrientationBoolean(UPSIDE_DOWN);

        if (beakUp) return "Beak up";
        else if (beakDown) return "Beak down";
        else if (tiltLeft) return "Tilt left";
        else if (tiltRight) return "Tilt right";
        else if (level) return "Level";
        else if (upsideDown) return "Upside down";
        return "In between";
    }
}
