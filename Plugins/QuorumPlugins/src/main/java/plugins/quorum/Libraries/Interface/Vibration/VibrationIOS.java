package plugins.quorum.Libraries.Interface.Vibration;

import org.robovm.apple.corehaptic.*;
import org.robovm.apple.foundation.*;
import quorum.Libraries.Interface.Vibration.VibrationArray_;

import java.util.Timer;
import java.util.TimerTask;

/*
    Vibration manager ported largely from https://github.com/chromelab/iosVibrationManager
 */
public class VibrationIOS implements VibrationStrategy {
    CHHapticEngine engine = null;
    CHHapticEventParameterID intensity = CHHapticEventParameterID.HapticIntensity;
    CHHapticEventParameterID sharpness = CHHapticEventParameterID.HapticSharpness;

    CHHapticEventParameter onIntensity = new CHHapticEventParameter(intensity, 1.0f);
    CHHapticEventParameter offIntensity = new CHHapticEventParameter(intensity, 1.0f);
    CHHapticEventParameter halfSharp = new CHHapticEventParameter(sharpness, 0.5f);

    private void Setup() {
        try {
            engine = new CHHapticEngine();
            engine.setResetHandler(new Runnable() {

                @Override
                public void run() {
                    Foundation.log("%@", new NSString("Core Haptics Crashed. Attempting Restart."));
                    NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
                    engine.startAndReturnError(ptr);
                    Foundation.log("%@", new NSString("Error: " + ptr.toString()));

                }
            });

            NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
            engine.startAndReturnError(ptr);
            Foundation.log("%@", ptr.get());
        } catch(Exception e) {
            engine = null; //if it's off, it's off.
        }
    }

    @Override
    public void QuickPulse(double seconds) {
        QuickPulse(seconds, false);
    }

    @Override
    public void QuickPulseForever() {
        QuickPulse(100, true);
    }

    public void QuickPulse(double seconds, boolean loopForever) {
        Foundation.log("%@", new NSString("Setting up Vibration through Quick Pulse"));
        Setup();
        Foundation.log("%@", new NSString("Setup Complete"));
        NSMutableArray<CHHapticEventParameter> onHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        onHalfSharp.add(onIntensity);
        onHalfSharp.add(halfSharp);

        NSMutableArray<CHHapticEventParameter> offHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        offHalfSharp.add(onIntensity);
        offHalfSharp.add(halfSharp);
        Foundation.log("%@", new NSString("Parameters Created"));
        CHHapticEventType type = CHHapticEventType.HapticContinuous;
        CHHapticEvent continuousEvent = new CHHapticEvent(type, onHalfSharp, 0.001, 0.075);
        CHHapticEvent continuousEvent2 = new CHHapticEvent(type, offHalfSharp, 0.076, 0.025);

        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(continuousEvent);
        events.add(continuousEvent2);

        Foundation.log("%@", new NSString("Events Created"));
        NSMutableArray<CHHapticDynamicParameter> parameters = new NSMutableArray<>();
        try {
            Foundation.log("%@", new NSString("Pattern Created"));
            CHHapticPattern pattern = CHHapticPattern.createUsingParameters(events, parameters);

            Foundation.log("%@", new NSString("Player Created"));
            CHHapticAdvancedPatternPlayer player = engine.createAdvancedPlayer(pattern);
            player.setLoopEnabled(true);


            NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
            Foundation.log("%@", new NSString("Starting vibration player."));
            player.start(0,ptr);

            long period = (long)(seconds * 1000.0);
            Foundation.log("%@", new NSString("Milliseconds: " + period));

            //if the system is telling us to loop forever, we don't need this timer.
            //otherwise the user has requested a stop time.
            if(!loopForever) {
                Timer timer = new Timer(true);
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        Foundation.log("%@", new NSString("Ending Vibration"));
                        player.setLoopEnabled(false);
                    }
                }, period);
                //NSError error = new NSError();
                Foundation.log("%@", new NSString("Setting off timer"));
            }


            //we need to handle whether or not we loop forever, but first we need to see if we can't get it to fire off
            //anything in Core Haptics.
        } catch (NSErrorException e) {
            e.printStackTrace();
        }
        //array.add()
        //CHHapticEvent event = new CHHapticEvent();
    }

    @Override
    public void SlowPulse(double seconds) {

    }

    @Override
    public void SlowPulseForever() {

    }

    @Override
    public void Rumble(double seconds) {

    }

    @Override
    public void RumbleForever() {

    }

    @Override
    public void Knock(int repetitions) {

    }

    @Override
    public void KnockOnce() {

    }

    @Override
    public void KnockForever() {

    }

    @Override
    public void Vibrate(double seconds) {

    }

    @Override
    public void VibrateForever() {

    }

    @Override
    public void Vibrate(double seconds, double intensity) {

    }

    @Override
    public void VibrateForever(double intensity) {

    }

    @Override
    public void Vibrate(VibrationArray_ commandArray, int repetitions) {

    }

    @Override
    public void VibrateOnce(VibrationArray_ commandArray) {

    }

    @Override
    public void VibrateForever(VibrationArray_ commandArray) {

    }

    @Override
    public void VibrateAtFrequency(double seconds, double frequency) {

    }

    @Override
    public void VibrateAtFrequencyForever(double frequency) {

    }

    @Override
    public void Stop() {

    }
}
