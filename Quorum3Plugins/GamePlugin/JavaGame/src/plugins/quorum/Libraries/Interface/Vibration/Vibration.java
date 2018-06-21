package plugins.quorum.Libraries.Interface.Vibration;

import android.app.Activity;

import quorum.Libraries.Interface.Vibration.PatternArray_;
import quorum.Libraries.Interface.Vibration.VibrationArray_;

public class Vibration extends VibrationManager {
    public Object me_;

    private static final String TAG = "Vibration";

    public Vibration() {
        super();
    }

    public Vibration(int timeslice) {
        super(timeslice);
    }
    
    public Activity getActivity() {
        Activity activity = plugins.quorum.Libraries.Game.AndroidApplication.GetActivity();
        return activity;
    }
     
    private long[] arrayConversion(PatternArray_ array) {
        long[] result = new long[array.GetSize()];
        for (int i = 0; i < result.length; i++) {
            result[i] = array.Get(i);
        }
        return result;
    }

    public void Stop() {
        stop();
    }
    
    public void SetTimeslice(int timeslice) {
        setTimeslice(timeslice);
    }
    
    public int GetTimeslice() {
        return getTimeslice();
    }
    
    public void VibratePattern(PatternArray_ pattern) {
        long[] patternarray = arrayConversion(pattern);
        vibratePattern(patternarray);
    }

    public void VibratePattern(PatternArray_ pattern, int repeat){
        long[] longpattern = arrayConversion(pattern);
        vibratePattern(longpattern, repeat);
    }
        
    public void VibrateGeneratedPattern(float intensity, int duration) {
        VibrateGeneratedPattern(intensity, duration, -1);
    }

    public void VibrateGeneratedPattern(float intensity, int duration, int repeat) {
        vibrateGeneratedPattern(intensity, duration, repeat);
    }
    
    public void VibratePattern(VibrationArray_ commandArray) {
        vibratePattern(new VibrationPattern(commandArray));
    }

    /**************************************************************************************
     * Linear Pattern
     **************************************************************************************/

    public void VibrateLinearPattern(int duration) {
       vibrateLinearPattern(duration, getTimeslice());
    }

    public void VibrateLinearPattern(int duration, int timeslice) {
       vibrateLinearPattern(duration, timeslice);
    }

    public void VibrateLinearPattern(int duration, int timeslice, int ratiocalc) {
       vibrateLinearPattern(duration, timeslice, ratiocalc);
    }

    /**************************************************************************************
     * Wave Pattern
     **************************************************************************************/

    public void VibrateWavePattern(int duration) {
        vibrateWavePattern(duration, getTimeslice());
    }


    public void VibrateWavePattern(int duration, int timeslice) {
        vibrateWavePattern(duration, timeslice);
    }

    /**************************************************************************************
    * Exponential Pattern
    **************************************************************************************/
    public void VibrateExponentialPattern(int duration) {
       vibrateExponentialPattern(duration, getTimeslice());
    }


    public void VibrateExponentialPattern(int duration, int timeslice) {
       vibrateExponentialPattern(duration, timeslice);
    }

}
