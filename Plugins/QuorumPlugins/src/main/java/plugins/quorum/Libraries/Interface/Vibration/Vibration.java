package plugins.quorum.Libraries.Interface.Vibration;

import android.app.Activity;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSString;
import plugins.quorum.Libraries.Game.IOSApplication;
import quorum.Libraries.Interface.Vibration.VibrationArray_;

public class Vibration {
    public Object me_;

    private static final String TAG = "Vibration";

    private static VibrationStrategy vibrate = null;

    static
    {
        String os = System.getProperty("os.name");
        String property = System.getProperty("java.runtime.name");

        if (os.contains("Mac OS X") || os.contains("Linux"))
        {
            if (os.contains("Linux") && System.getProperty("java.runtime.name").contains("Android Runtime"))
            {

            } else {
                vibrate = new NullVibrationStrategy();
            }
        }
        else if (os.contains("Windows"))
        {
            vibrate = new NullVibrationStrategy();
        }
        else
        {
            Foundation.log("%@", new NSString("OS: " + os));
            Foundation.log("%@", new NSString("Runtime: " + property));
            Foundation.log("%@", new NSString("Device: " + IOSApplication.IsDevice()));
            if (IOSApplication.IsDevice()) {
                vibrate = new VibrationIOS();
            } else { //it's on the simulator, so ignore it.
                vibrate = new NullVibrationStrategy();
            }
        }
    }

    public void QuickPulse(double seconds) {
        vibrate.QuickPulse(seconds);
    }
    
    public void QuickPulseForever() {
        vibrate.QuickPulseForever();
    }
    
    public void SlowPulse(double seconds) {
        vibrate.SlowPulse(seconds);
    }

    public void SlowPulseForever() {
        vibrate.SlowPulseForever();
    }
    
    public void Rumble(double seconds) {
        vibrate.Rumble(seconds);
    }
    
    public void RumbleForever() {
        vibrate.RumbleForever();
    }
    
    public void Knock(int repetitions) {
        vibrate.Knock(repetitions);
    }   
    
    public void KnockOnce() {
        vibrate.KnockOnce();
    }    
    
    public void KnockForever() {
        vibrate.KnockForever();
    }    
               
    public void Vibrate(double seconds) {
        vibrate.Vibrate(seconds);
    }
    
    public void VibrateForever() {
        vibrate.VibrateForever();
    }
        
    public void Vibrate(double seconds, double intensity) {
        vibrate.Vibrate(seconds, intensity);
    }

    public void VibrateForever(double intensity) {
        vibrate.VibrateForever(intensity);
    }
    
    public void Vibrate(VibrationArray_ commandArray, int repetitions) {
        vibrate.Vibrate(commandArray, repetitions);
    }

    public void VibrateOnce(VibrationArray_ commandArray) {
        vibrate.VibrateOnce(commandArray);
    }
    
    public void VibrateForever(VibrationArray_ commandArray) {
        vibrate.VibrateForever(commandArray);
    }
        
    public void VibrateAtFrequency(double seconds, double frequency) {
        vibrate.VibrateAtFrequency(seconds, frequency);
    }

    public void VibrateAtFrequencyForever(double frequency) {
        vibrate.VibrateAtFrequencyForever(frequency);
    }

    public void Stop() {
        vibrate.Stop();
    }
    
    public Activity getActivity() {
        Activity activity = plugins.quorum.Libraries.Game.AndroidApplication.GetActivity();
        return activity;
    }

}
