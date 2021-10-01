package plugins.quorum.Libraries.Interface.Vibration;

import org.robovm.apple.corehaptic.*;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSErrorException;

/*
    Vibration manager ported largely from https://github.com/chromelab/iosVibrationManager
 */
public class VibrationIOS {
    CHHapticEngine engine = null;
    CHHapticEventParameterID intensity = CHHapticEventParameterID.HapticIntensity;
    CHHapticEventParameterID sharpness = CHHapticEventParameterID.HapticSharpness;

    CHHapticEventParameter onIntensity = new CHHapticEventParameter(intensity, 1.0f);
    CHHapticEventParameter offIntensity = new CHHapticEventParameter(intensity, 1.0f);
    CHHapticEventParameter halfSharp = new CHHapticEventParameter(sharpness, 0.5f);

    private void Setup() {
        try {
            engine = new CHHapticEngine();
        } catch(Exception e) {
            engine = null; //if it's off, it's off.
        }
    }

    public void QuickPulse(double seconds) {
        QuickPulse(seconds, false);
    }

    public void QuickPulseForever() {
        QuickPulse(100, true);
    }

    public void QuickPulse(double seconds, boolean loopForever) {
        Setup();

        NSArray<CHHapticEventParameter> onHalfSharp = new NSArray<>();
        onHalfSharp.add(onIntensity);
        onHalfSharp.add(halfSharp);

        NSArray<CHHapticEventParameter> offHalfSharp = new NSArray<>();
        offHalfSharp.add(onIntensity);
        offHalfSharp.add(halfSharp);

        CHHapticEventType type = CHHapticEventType.HapticContinuous;
        CHHapticEvent continuousEvent = new CHHapticEvent(type, onHalfSharp, 0.001, 0.075);
        CHHapticEvent continuousEvent2 = new CHHapticEvent(type, offHalfSharp, 0.076, 0.025);

        NSArray<CHHapticEvent> events = new NSArray<>();
        events.add(continuousEvent);
        events.add(continuousEvent2);

        NSArray<CHHapticDynamicParameter> parameters = new NSArray<>();
        try {
            CHHapticPattern pattern = CHHapticPattern.createUsingParameters(events, parameters);
        } catch (NSErrorException e) {
            e.printStackTrace();
        }
        //array.add()
        //CHHapticEvent event = new CHHapticEvent();
    }
}
