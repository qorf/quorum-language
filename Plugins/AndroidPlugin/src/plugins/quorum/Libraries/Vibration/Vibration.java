package plugins.quorum.Libraries.Vibration;

import android.app.Activity;
//import quorum.Libraries.Vibration.PatternArray_;
import quorum.Libraries.Vibration.VibrationArray_;

public class Vibration extends VibrationManager {
    public Object me_;

    private static final String TAG = "Vibration";

//    public Vibration() {
//        super();
//    }
//
//    public Vibration(int timeslice) {
//        super(timeslice);
//    }
    
    public void QuickPulse(double seconds) {
        quickPulse(seconds);
    }
    
    public void QuickPulseForever() {
        quickPulseForever();
    }
    
    public void SlowPulse(double seconds) {
        slowPulse(seconds);
    }

    public void SlowPulseForever() {
        slowPulseForever();
    }
    
    public void Rumble(double seconds) {
        rumble(seconds);
    }
    
    public void RumbleForever() {
        rumbleForever();
    }
    
    public void Knock(int repetitions) {
        knock(repetitions);
    }   
    
    public void KnockOnce() {
        knockOnce();
    }    
    
    public void KnockForever() {
        knockForever();
    }    
               
    public void Vibrate(double seconds) {
        vibrate(seconds);
    }
    
    public void VibrateForever() {
        vibrateForever();
    }
    
//    public void Vibrate(long[] pattern) {
//        vibrate(pattern);
//    }
//        
//    public void Vibrate(long[] pattern, int repeatAtIndex) {
//        vibrate(pattern, repeatAtIndex);
//    }
    
    public void Vibrate(double seconds, double intensity) {
        vibrate(seconds, intensity);
    }

    public void VibrateForever(double intensity) {
        vibrateForever(intensity);
    }
    
    public void Vibrate(VibrationArray_ commandArray, int repetitions) {
        vibrate(new VibrationPattern(commandArray), repetitions);
    }

    public void VibrateOnce(VibrationArray_ commandArray) {
        vibrateOnce(new VibrationPattern(commandArray));
    }
    
    public void VibrateForever(VibrationArray_ commandArray) {
        vibrateForever(new VibrationPattern(commandArray));
    }
        
    public void VibrateAtFrequency(double seconds, double frequency) {
        vibrateAtFrequency(seconds, frequency);
    }

    public void VibrateAtFrequencyForever(double frequency) {
        vibrateAtFrequencyForever(frequency);
    }

    public void Stop() {
        stop();
    }
    
    public Activity getActivity() {
        Activity activity = plugins.quorum.Libraries.Game.AndroidApplication.GetActivity();
        return activity;
    }

}
