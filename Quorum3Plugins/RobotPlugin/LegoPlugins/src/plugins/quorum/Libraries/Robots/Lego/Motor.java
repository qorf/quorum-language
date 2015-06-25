package plugins.quorum.Libraries.Robots.Lego;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import lejos.hardware.motor.BaseRegulatedMotor;

public class Motor {
    public Object me_ = null;
    private final HashMap<String, BaseRegulatedMotor> motors;
    
    public Motor() {
        motors = new HashMap<>();
        motors.put("A", lejos.hardware.motor.Motor.A);
        motors.put("B", lejos.hardware.motor.Motor.B);
        motors.put("C", lejos.hardware.motor.Motor.C);
        motors.put("D", lejos.hardware.motor.Motor.D);
    }
    
    private boolean MotorIsValid(String motorID, BaseRegulatedMotor motor) throws IOException {
        motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return true;
    }
    
    public void SetSpeed(String motorID, int speed) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.setSpeed(speed);
    }
    
    public void MoveForward(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.backward(); //forward is relative to the motors themselves, the EV3 robots seem to go backward to move forward
    }
    
    public void MoveBackward(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.forward();
    }
    
    public void WaitForMotorToFinish(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.waitComplete();
    }
    
    public void RotateByDegrees(String motorID, int degrees) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.rotate(-1 * degrees);
    }
    
    public void RotateToDegree(String motorID, int degreeTarget) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.rotateTo(-1 * degreeTarget);
    }
    
    public int GetRotationTarget(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return motor.getLimitAngle();
    }
    
    public void ResetRotation(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            motor.resetTachoCount();
    }
    
    public int GetRotation(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return motor.getTachoCount() * - 1;
    }
    
    public int GetSpeed(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return motor.getRotationSpeed();
    }
    
    public boolean IsMoving(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return motor.isMoving();
    }
    
    public void Synchronize(String motor1, String motor2) {
        BaseRegulatedMotor motor = motors.get(motor1);
        BaseRegulatedMotor[] motorArray = new BaseRegulatedMotor[1];
        motorArray[0] = motors.get(motor2);
        if (motorArray[0] == null) {
            try {
                throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D.");
            } catch (IOException ex) {
                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            motor.synchronizeWith(motorArray);
            motor.startSynchronization();
        }
    }
    
    public void Synchronize(String leaderMotor, quorum.Libraries.Containers.Array_ followerMotors) throws IOException {
        //the lejOS method accepts duplicate values, so sending 20 "A"s to this functions would generate 20 refernces to the same motor
        //How should this be handled? Throwing out duplicates would guarantee we had at most 4 references to motors,
        //but this would have to potentially search through the whole array
        
        BaseRegulatedMotor motor;
        
        //parse the array
        int motorsIndex = 0;
        BaseRegulatedMotor[] motorsArray = new BaseRegulatedMotor[4];
        String motorID = null;
        
        //verify and store references to any valid motor (can be up to 4)
        for (int i = 0; i < 4; i++) {
            quorum.Libraries.Language.Types.Text_ motorTextObject = (quorum.Libraries.Language.Types.Text_) followerMotors.Get(i);
            if (motorTextObject != null) {
                motorID = motorTextObject.GetValue();
                if (motorID != null) {
                    motor = motors.get(motorID);
                    if (MotorIsValid(motorID, motor))
                        motorsArray[motorsIndex++] = motor;
                }
            }
        }
        
        motor = motors.get(leaderMotor);
        motor.synchronizeWith(motorsArray);
        motor.startSynchronization();
        
        
//        //create an array with only valid motor references
//        //motorsIndex is equal to motorsArray.length
//        if (motorsIndex < 2)
//            throw new IOException("Synchronization failed; at least two motors are required to synchronize");
//        BaseRegulatedMotor[] validMotorsArray = new BaseRegulatedMotor[motorsIndex-1];
//        System.arraycopy(motorsArray, 1, validMotorsArray, 0, motorsIndex-1); //leaving out the first valid motor, as it is used to call the lejOS method
//        //sync
//        motorsArray[0].synchronizeWith(validMotorsArray); //throws an error if a motor tries to synchronize with itself
//        motorsArray[0].startSynchronization();
}
    
    public void EndSynchronization(String motorID) throws IOException {
        //The passed motorID MUST match the first motor passed to Synchronize -- maybe we should make Synchronize take two parameters? (motorID, motorArray)
        
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID, motor))
            motor.endSynchronization();
    }
    
    public void Stop(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            motor.stop(true); //true = "does not wait for the motor to actually stop"
    }
}