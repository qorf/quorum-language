package plugins.quorum.Libraries.Robots.Lego;

import java.io.IOException;
import java.util.HashMap;
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
    
    private boolean MotorIsValid(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return true;
    }
    
    public void SetSpeed(String motorID, int speed) throws IOException {
        if (speed > 100)
            speed = 100;
        else if (speed < 0)
            speed = 0;
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID))
            motor.setSpeed((int)(speed * 0.01 * 720));  //speed is a percentage of the 720, which is the "max" speed
    }
    
    public void RotateForward(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID))
            motor.forward(); //forward is relative to the motors themselves
    }
    
    public void RotateBackward(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID))
            motor.backward();
    }
    
    public void Wait(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (MotorIsValid(motorID))
            motor.waitComplete();
    }
    
    public void RotateByDegrees(String motorID, double degrees) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor != null) {
            BaseRegulatedMotor[] motorArray = new BaseRegulatedMotor[1];
            if (motorID.equals("A"))
                motorArray[0] = motors.get("B");
            else
                motorArray[0] = motors.get("A");
            motor.synchronizeWith(motorArray);
            motor.startSynchronization();
            motor.rotate((int)Math.round(degrees));      //all of the synchronization stuff is to make this not block
            motor.endSynchronization();
        }
    }
    
    public void RotateToDegree(String motorID, double degreeTarget) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor != null) {
            BaseRegulatedMotor[] motorArray = new BaseRegulatedMotor[1];
            if (motorID.equals("A"))
                motorArray[0] = motors.get("B");    //can't synchronize with self
            else
                motorArray[0] = motors.get("A");
            motor.synchronizeWith(motorArray);
            motor.startSynchronization();
            motor.rotateTo((int)Math.round(degreeTarget));      //all of the synchronization stuff is to make this not block
            motor.endSynchronization();
        }
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
            return motor.getTachoCount();
    }
    
    public int GetSpeed(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return (int)(motor.getRotationSpeed() / 720 * 100);
    }
    
    public boolean IsMoving(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            return motor.isMoving();
    }
    
//    public void Synchronize(String motor1, String motor2) {
//        BaseRegulatedMotor motor = motors.get(motor1);
//        BaseRegulatedMotor[] motorArray = new BaseRegulatedMotor[1];
//        motorArray[0] = motors.get(motor2);
//        if (motorArray[0] == null) {
//            try {
//                throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D.");
//            } catch (IOException ex) {
//                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        else {
//            motor.synchronizeWith(motorArray);
//            motor.startSynchronization();
//        }
//    }
//    
//    public void Synchronize(String leaderMotor, quorum.Libraries.Containers.Array_ followerMotors) throws IOException {
//        //the lejOS method accepts duplicate values, so sending 20 "A"s to this functions would generate 20 refernces to the same motor
//        //How should this be handled? Throwing out duplicates would guarantee we had at most 4 references to motors,
//        //but this would have to potentially search through the whole array
//        
//        BaseRegulatedMotor motor;
//        
//        //parse the array
//        int motorsIndex = 0;
//        BaseRegulatedMotor[] motorsArray = new BaseRegulatedMotor[4];
//        String motorID = null;
//        
//        //verify and store references to any valid motor
//        for (int i = 0; i < followerMotors.GetSize(); i++) {
//            quorum.Libraries.Language.Types.Text_ motorTextObject = (quorum.Libraries.Language.Types.Text_) followerMotors.Get(i);
//            if (motorTextObject != null) {
//                motorID = motorTextObject.GetValue();   //pull out the motor name
//                if (motorID != null) {  
//                    if (MotorIsValid(motorID)) {
//                        motor = motors.get(motorID);        //get the BaseRegulatedMotor
//                        motorsArray[motorsIndex++] = motor;
//                    }
//                }
//            }
//        }
//        
//        if (MotorIsValid(leaderMotor)) {
//            motor = motors.get(leaderMotor);
//            motor.synchronizeWith(motorsArray);
//            motor.startSynchronization();
//        }
//}
//    
//    public void EndSynchronization(String motorID) throws IOException {
//        //The passed motorID MUST match the first motor passed to Synchronize -- maybe we should make Synchronize take two parameters? (motorID, motorArray)
//        
//        BaseRegulatedMotor motor = motors.get(motorID);
//        if (MotorIsValid(motorID))
//            motor.endSynchronization();
//    }
    
    public void Stop(String motorID) throws IOException {
        BaseRegulatedMotor motor = motors.get(motorID);
        if (motor == null)
            throw new IOException("Invalid motor ID specified. Valid options are: A, B, C, or D, but " + motorID + " was given.");
        else
            motor.stop(true); //true = "does not wait for the motor to actually stop"
    }
}