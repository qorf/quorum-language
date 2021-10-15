package plugins.quorum.Libraries.Interface.Vibration;

import android.util.Log;
import org.robovm.apple.corehaptic.*;
import org.robovm.apple.foundation.*;
import org.robovm.objc.block.VoidBlock1;
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
    CHHapticEventType continuous = CHHapticEventType.HapticContinuous;

    CHHapticEventParameter onIntensity = new CHHapticEventParameter(intensity, 1.0f);
    CHHapticEventParameter offIntensity = new CHHapticEventParameter(intensity, 1.0f);
    CHHapticEventParameter halfSharp = new CHHapticEventParameter(sharpness, 0.5f);
    CHHapticEventParameter sharp = new CHHapticEventParameter(sharpness, 1.0f);
    private static double FREQUENCY_MINIMUM = 0.000001;
    private static double FREQUENCY_MAXIMUM = 500000;
    private static final int FOREVER_SECONDS = 100;

    private void Setup() {
        if (engine != null) {
            return;
        }

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
        QuickPulse(FOREVER_SECONDS, true);
    }

    public void QuickPulse(double seconds, boolean loopForever) {
        Foundation.log("%@", new NSString("Setting up Vibration through Quick Pulse"));
        Setup();
        NSMutableArray<CHHapticEventParameter> onHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        onHalfSharp.add(onIntensity);
        onHalfSharp.add(halfSharp);

        NSMutableArray<CHHapticEventParameter> offHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        offHalfSharp.add(onIntensity);
        offHalfSharp.add(halfSharp);

        CHHapticEvent continuousEvent = new CHHapticEvent(continuous, onHalfSharp, 0.001, 0.075);
        CHHapticEvent continuousEvent2 = new CHHapticEvent(continuous, offHalfSharp, 0.076, 0.025);

        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(continuousEvent);
        events.add(continuousEvent2);

        EventPulse(events, seconds, loopForever);
    }

    private void EventPulse(NSMutableArray<CHHapticEvent> events, double seconds, boolean loopForever) {
        Setup();
        NSMutableArray<CHHapticDynamicParameter> parameters = new NSMutableArray<>();
        try {
            CHHapticPattern pattern = CHHapticPattern.createUsingParameters(events, parameters);

            CHHapticAdvancedPatternPlayer player = engine.createAdvancedPlayer(pattern);
            player.setLoopEnabled(true);


            NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
            player.start(0,ptr);

            long period = (long)(seconds * 1000.0);

            //if the system is telling us to loop forever, we don't need this timer.
            //otherwise the user has requested a stop time.
            if(!loopForever) {
                Timer timer = new Timer(true);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        player.setLoopEnabled(false);
                    }
                }, period);
            }
        } catch (NSErrorException e) {
            String message = e.toString();
            Foundation.log("%@", new NSString(message));
            e.printStackTrace();
        }
    }

    @Override
    public void SlowPulse(double seconds) {
        SlowPulse(seconds, false);
    }

    @Override
    public void SlowPulseForever() {
        SlowPulse(FOREVER_SECONDS, true);
    }

    private void SlowPulse(double seconds, boolean loopForever) {
        NSMutableArray<CHHapticEventParameter> onHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        onHalfSharp.add(onIntensity);
        onHalfSharp.add(halfSharp);

        NSMutableArray<CHHapticEventParameter> offHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        offHalfSharp.add(onIntensity);
        offHalfSharp.add(halfSharp);
        CHHapticEvent continuousEvent = new CHHapticEvent(continuous, onHalfSharp, 0.001, 0.050);
        CHHapticEvent continuousEvent2 = new CHHapticEvent(continuous, offHalfSharp, 0.051, 0.1);

        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(continuousEvent);
        events.add(continuousEvent2);
        EventPulse(events, seconds, loopForever);
    }

    @Override
    public void Rumble(double seconds) {
        Rumble(seconds, false);
    }

    @Override
    public void RumbleForever() {
        Rumble(100, true);
    }

    private void Rumble(double seconds, boolean loopForever) {
        NSMutableArray<CHHapticEventParameter> onHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        onHalfSharp.add(onIntensity);
        onHalfSharp.add(halfSharp);

        NSMutableArray<CHHapticEventParameter> offHalfSharp = new NSMutableArray<CHHapticEventParameter>();
        offHalfSharp.add(onIntensity);
        offHalfSharp.add(halfSharp);

        CHHapticEvent continuousEvent = new CHHapticEvent(continuous, onHalfSharp, 0.001, 0.007);
        CHHapticEvent continuousEvent2 = new CHHapticEvent(continuous, offHalfSharp, 0.008, 0.009);

        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(continuousEvent);
        events.add(continuousEvent2);
        EventPulse(events, seconds, loopForever);
    }

    @Override
    public void Knock(int repetitions) {
        Knock(repetitions, false);
    }

    @Override
    public void KnockOnce() {
        Knock(1, false);
    }

    @Override
    public void KnockForever() {
        Knock(FOREVER_SECONDS, false);
    }

    private void Knock(int repetitions, boolean loopForever) {
        NSMutableArray<CHHapticEventParameter> offSharp = new NSMutableArray<CHHapticEventParameter>();
        offSharp.add(offIntensity);
        offSharp.add(sharp);

        NSMutableArray<CHHapticEventParameter> onSharp = new NSMutableArray<CHHapticEventParameter>();
        onSharp.add(onIntensity);
        onSharp.add(sharp);


        CHHapticEvent event1 = new CHHapticEvent(continuous, offSharp, 0.000, 0.025);
        CHHapticEvent event2 = new CHHapticEvent(continuous, onSharp, 0.025, 0.075);

        CHHapticEvent event3 = new CHHapticEvent(continuous, offSharp, 0.100, 0.025);
        CHHapticEvent event4 = new CHHapticEvent(continuous, onSharp, 0.125, 0.075);

        CHHapticEvent event5 = new CHHapticEvent(continuous, offSharp, 0.200, 0.400);
        CHHapticEvent event6 = new CHHapticEvent(continuous, onSharp, 0.600, 0.050);

        CHHapticEvent event7 = new CHHapticEvent(continuous, offSharp, 0.650, 0.600);

        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);

        RepeatPattern(events,  repetitions, loopForever);
    }

    private void RepeatPattern(NSMutableArray<CHHapticEvent> events, int repetitions, boolean loopForever) {
        Setup();
        NSMutableArray<CHHapticDynamicParameter> parameters = new NSMutableArray<>();
        try {
            CHHapticPattern pattern = CHHapticPattern.createUsingParameters(events, parameters);

            CHHapticAdvancedPatternPlayer player = engine.createAdvancedPlayer(pattern);
            player.setLoopEnabled(true);


            NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
            player.start(0,ptr);

            //instead, calculate repetitions here for the number of knock-knocks.
            //I would prefer not to replicate this logic, but
            //it's just a bit different. The idea is we set off the vibration,
            //then the timer fires at the end, deciding to kill it or
            //to the do another repetition
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                int timesCompleted = 0;

                @Override
                public void run() {
                    timesCompleted++;
                    if(!loopForever && timesCompleted >= repetitions) {
                        player.setLoopEnabled(false);
                        timer.cancel();
                    }

                }
            }, 0, 1250);
        } catch (NSErrorException e) {
            String message = e.toString();
            Foundation.log("%@", new NSString(message));
            e.printStackTrace();
        }
    }
    @Override
    public void Vibrate(double seconds) {
        Vibrate(seconds, 0.5, false);
    }

    @Override
    public void VibrateForever() {
        Vibrate(FOREVER_SECONDS, 0.5, true);
    }

    @Override
    public void Vibrate(double seconds, double intensity) {
        Vibrate(seconds, intensity, false);
    }

    @Override
    public void VibrateForever(double intensity) {
        Vibrate(FOREVER_SECONDS, intensity, true);
    }

    private void Vibrate(double seconds, double intensity, boolean loopForever) {
        CHHapticEventParameter sharpAmount = new CHHapticEventParameter(sharpness, (float) intensity);

        NSMutableArray<CHHapticEventParameter> onSharp = new NSMutableArray<CHHapticEventParameter>();
        onSharp.add(onIntensity);
        onSharp.add(sharpAmount);

        CHHapticEvent continuousEvent = new CHHapticEvent(continuous, onSharp, 0.00, seconds);

        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(continuousEvent);
        EventPulse(events, seconds, loopForever);
    }

    @Override
    public void Vibrate(VibrationArray_ commandArray, int repetitions) {
        Vibrate(new VibrationPattern(commandArray), repetitions, false);
    }

    @Override
    public void VibrateOnce(VibrationArray_ commandArray) {
        Vibrate(new VibrationPattern(commandArray), 1, false);
    }

    @Override
    public void VibrateForever(VibrationArray_ commandArray) {
        Vibrate(new VibrationPattern(commandArray), 1, true);
    }

    public void Vibrate(VibrationPattern commandArray, int repetitions, boolean loopForever) {
        int size = commandArray.getSize();
        if (repetitions < 0) {
            repetitions = 1;
        }

        //On android, we need to require pairs. Is this also true in core haptics or no?
        //We don't check this for now, but it's not clear if we should.
        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        for (int j = 0; j < size; j++) {
            double intensity = commandArray.getIntensity(j);
            if (intensity > 1.0) {
                intensity = 1.0;
            } else if (intensity < 0) {
                intensity = 0;
            }
            double seconds = commandArray.getDuration(j);
            if (seconds < 0.0) {
                seconds = 0.0;
            }

            if (intensity == 0) {
                CHHapticEventParameter sharpAmount = new CHHapticEventParameter(sharpness, (float) intensity);
                NSMutableArray<CHHapticEventParameter> offSharp = new NSMutableArray<CHHapticEventParameter>();
                offSharp.add(offIntensity);
                offSharp.add(sharpAmount);

                CHHapticEvent event = new CHHapticEvent(continuous, offSharp, 0.00, seconds);
                events.add(event);
            } else {
                CHHapticEventParameter sharpAmount = new CHHapticEventParameter(sharpness, (float) intensity);
                NSMutableArray<CHHapticEventParameter> onSharp = new NSMutableArray<CHHapticEventParameter>();
                onSharp.add(onIntensity);
                onSharp.add(sharpAmount);

                CHHapticEvent event = new CHHapticEvent(continuous, onSharp, 0.00, seconds);
                events.add(event);
            }
        }
        RepeatPattern(events,  repetitions, loopForever);
    }

    @Override
    public void VibrateAtFrequency(double seconds, double frequency) {
        VibrateAtFrequency(seconds, frequency, false);
    }

    @Override
    public void VibrateAtFrequencyForever(double frequency) {
        VibrateAtFrequency(FOREVER_SECONDS, frequency, true);
    }

    private void VibrateAtFrequency(double seconds, double frequency, boolean loopForever) {
        NSMutableArray<CHHapticEventParameter> onSharp = new NSMutableArray<CHHapticEventParameter>();
        onSharp.add(onIntensity);
        onSharp.add(sharp);

        NSMutableArray<CHHapticEventParameter> offSharp = new NSMutableArray<CHHapticEventParameter>();
        offSharp.add(offIntensity);
        offSharp.add(sharp);

        if(frequency < FREQUENCY_MINIMUM) {
            frequency = FREQUENCY_MINIMUM;
        } else if(frequency > FREQUENCY_MAXIMUM) {
            frequency = FREQUENCY_MAXIMUM;
        }

        CHHapticEvent event1 = new CHHapticEvent(continuous, onSharp, 0.00, 0.5 / frequency);
        CHHapticEvent event2 = new CHHapticEvent(continuous, offSharp, 0.5 / frequency, 0.5 / frequency);
        NSMutableArray<CHHapticEvent> events = new NSMutableArray<>();
        events.add(event1);
        events.add(event2);
        EventPulse(events, seconds, loopForever);
    }

    @Override
    public void Stop() {
        if (engine != null) {
            engine.stop(new VoidBlock1<NSError>() {
                @Override
                public void invoke(NSError nsError) {
                    //ignore the error? Should we send it up to Quorum or ignore it?
                }
            });
            engine = null;
        }
    }
}
