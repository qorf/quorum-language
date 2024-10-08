package plugins.quorum.Libraries.Interface.Vibration;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import quorum.Libraries.Interface.Vibration.VibrationArray_;

public class AndroidVibrationStrategy implements VibrationStrategy {
    
    private long endTimeLastRun;
    private Vibrator vibrationUnit;
    private Activity activity;

    private static final String TAG = "VibrationManager";

    public Activity getActivity() {
		if (activity == null) {
			setActivity(plugins.quorum.Libraries.Game.AndroidApplication.GetActivity());
		}
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    
    private Vibrator getVibrationUnit() {
        return (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }
        
    public void initialize() {
        this.vibrationUnit = getVibrationUnit();
    }
        
    public void QuickPulse(double seconds) {
        QuickPulse(seconds, -1);
    }
    
    public void QuickPulseForever() {
        QuickPulse(5, 0);
    }
    
    private void QuickPulse(double seconds, int repeatAtIndex) {
        initializeIfNecessary();

        if (vibrationUnit.hasVibrator()) {
            // converts seconds to milliseconds
            seconds = seconds * 1000;

            // preset time (from 25 ms wait, 75 ms run)
            double patternTime = 100;

            // determine the number of timeSlots required for full pattern
            int timeSlots = (int) Math.round(seconds/patternTime) * 2;

            if (timeSlots > 2500)
                Log.w(TAG, "Long duration patterns may cause delay before vibration. Consider reducing duration.");

            if (timeSlots == 0)
                timeSlots++;    // plays at least once (timeSlots = 0 due to rounding in the case of extremely short vibrations)

            if (timeSlots % 2 == 1)
                timeSlots++;    // ensures even number of timeSlots (1 to pause, 1 to vibrate)

            long [] fullPattern = new long[timeSlots+2];
            fullPattern[0] = 0;                 // removes initial pause
            fullPattern[timeSlots+1] = 0;       // removes ending run (required for repeating)

            // sets pattern for (approximately) the given duration
            for (int i = 1; i < timeSlots; i = i + 2) {
                fullPattern[i] = 75;
                fullPattern[i+1] = 25;
            }

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    public void SlowPulse(double seconds) {
        SlowPulse(seconds, -1);
    }

    public void SlowPulseForever() {
        SlowPulse(5, 0);
    }
    
    private void SlowPulse(double seconds, int repeatAtIndex) {
        initializeIfNecessary();
        
        if (vibrationUnit.hasVibrator()) {
            // converts seconds to milliseconds
            seconds = seconds * 1000;

            // preset time (from 100 ms wait, 50 ms run)
            double patternTime = 150;

            // determine the number of timeSlots required for full pattern
            int timeSlots = (int) Math.round(seconds/patternTime) * 2;

            if (timeSlots > 2500)
                Log.w(TAG, "Long duration patterns may cause delay before vibration. Consider reducing duration.");

            if (timeSlots == 0)
                timeSlots++;    // plays at least once (timeSlots = 0 due to rounding in the case of extremely short vibrations)

            if (timeSlots % 2 == 1)
                timeSlots++;    // ensures even number of timeSlots (1 to pause, 1 to vibrate)

            long [] fullPattern = new long[timeSlots+2];
            fullPattern[0] = 0;                 // removes initial pause
            fullPattern[timeSlots+1] = 0;       // removes ending run (required for repeating)

            // sets pattern for (approximately) the given duration
            for (int i = 1; i < timeSlots; i = i + 2) {
                fullPattern[i] = 50;
                fullPattern[i+1] = 100;
            }

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    public void Rumble(double seconds) {
        Rumble(seconds, -1);
    }

    public void RumbleForever() {
        Rumble(5, 0);
    }    

    private void Rumble(double seconds, int repeatAtIndex) {
        initializeIfNecessary();

        if (vibrationUnit.hasVibrator()) {
            // converts seconds to milliseconds
            seconds = seconds * 1000;

            // preset time (from 9 ms wait, 7 ms run)
            double patternTime = 16;

            // determine the number of timeSlots required for full pattern
            int timeSlots = (int) Math.round(seconds/patternTime) * 2;

            if (timeSlots > 2500)
                Log.w(TAG, "Long duration patterns may cause delay before vibration. Consider reducing duration.");

            if (timeSlots == 0)
                timeSlots++;    // plays at least once (timeSlots = 0 due to rounding in the case of extremely short vibrations)

            if (timeSlots % 2 == 1)
                timeSlots++;    // ensures even number of timeSlots (1 to pause, 1 to vibrate)

            long [] fullPattern = new long[timeSlots+2];
            fullPattern[0] = 0;                 // removes initial pause
            fullPattern[timeSlots+1] = 0;       // removes ending run (required for repeating)

            // sets pattern for (approximately) the given duration
            for (int i = 1; i < timeSlots; i = i + 2) {
                fullPattern[i] = 7;
                fullPattern[i+1] = 9;
            }

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }
    
    public void Knock(int repetitions) {
        Knock(repetitions, -1);
    }

    public void KnockOnce() {
        Knock(1, -1);
    }

    public void KnockForever() {
        Knock(5, 0);
    }    
    
    private void Knock(int repetitions, int repeatAtIndex) {
        long[] fullPattern = {};
        int size = 8;   // preset (from {25, 75, 25, 75, 400, 50, 600, 0})

        if (vibrationUnit.hasVibrator()) {
            for (int i = 0; i < repetitions; i++) {
                long[] pattern = new long[]{25, 75, 25, 75, 400, 50, 600, 0};
                logVibrationPattern(pattern);
                if (pattern.length % 2 != 0) {
                    Log.e("CycleCount", "Half a cycle found!");
                }

                fullPattern = concat(fullPattern, pattern);
            }

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    public void Vibrate(VibrationArray_ commandArray, int repetitions) {
        Vibrate(new VibrationPattern(commandArray), repetitions);
    }

    public void VibrateOnce(VibrationArray_ commandArray) {
        VibrateOnce(new VibrationPattern(commandArray));
    }

    public void VibrateForever(VibrationArray_ commandArray) {
        VibrateForever(new VibrationPattern(commandArray));
    }

    public void Vibrate(double seconds) {
        Vibrate(seconds, -1);
    }
    
    public void VibrateForever() {
        Vibrate(5, 0);
    }
    
    private void Vibrate(double seconds, int repeatAtIndex) {
        initializeIfNecessary();
        
        if (vibrationUnit.hasVibrator()) {
            // converts seconds to milliseconds
            seconds = seconds * 1000;
            
            long[] fullPattern = new long[]{0, (long)seconds};

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    public void Vibrate(VibrationPattern commandArray, int repetitions) {
        initializeIfNecessary();
        long [] fullPattern = {};
        int size = commandArray.getSize();

        int repeatAtIndex = -1;

        if (repetitions < 0) {
            repeatAtIndex = 0;
            repetitions = 1;
        }

        if (vibrationUnit.hasVibrator()) {
            for (int i = 0; i < repetitions; i++) {
                for (int j = 0; j < size; j++) {
                    double intensity = commandArray.getIntensity(j);
                    double duration = commandArray.getDuration(j) * 1000;   // converts from seconds to ms
                    long[] pattern = generateVibrationPattern(intensity, duration);
                    logVibrationPattern(pattern);
                    if (pattern.length % 2 != 0) {
                        Log.e("CycleCount", "Half a cycle found!");
                    }

                    fullPattern = concat(fullPattern, pattern);
                }
            }

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    public void VibrateOnce(VibrationPattern commandArray) {
        Vibrate(commandArray, 1);
    }

    public void VibrateForever(VibrationPattern commandArray) {
        Vibrate(commandArray, -1);
    }

    public void Stop() {
        endTimeLastRun = 0;
        if (vibrationUnit != null) {
            vibrationUnit.cancel();
        }
    }

    public void initializeIfNecessary() {
        if (vibrationUnit == null) {
            Log.v(TAG, "Initializing");
            initialize();
        }
    }

    public void VibrateForever(double intensity) {
        Vibrate(5, intensity, 0);
    }

    public void Vibrate(double seconds, double intensity) {
        Vibrate(seconds, intensity, -1);
    }

    private void Vibrate(double seconds, double intensity, int repeatAtIndex) {
        initializeIfNecessary();

        // converts seconds to milliseconds
        seconds = seconds * 1000;

        long[] pattern = generateVibrationPattern(intensity, seconds);

        if (vibrationUnit.hasVibrator()) {
            logVibrationPattern(pattern);
            vibrationUnit.vibrate(pattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }
    
    public void VibrateAtFrequencyForever(double frequency) {
        VibrateAtFrequency(5, frequency, 0);
    }
    
    public void VibrateAtFrequency(double seconds, double frequency) {
        VibrateAtFrequency(seconds, frequency, -1);
    }
        
    private void VibrateAtFrequency(double seconds, double frequency, int repeatAtIndex) {
        initializeIfNecessary();

        if (vibrationUnit.hasVibrator()) {
            // converts seconds to milliseconds
            seconds = seconds * 1000;

            if (frequency > 50)
                Log.w(TAG, "Frequencies above 50 may have inconsistent vibration timing. Consider reducing frequency.");

            // derived from f = 1/T, this is T = 1/f
            // times 1000 because we are dealing in milliseconds
            double waveTime = (1/frequency) * 1000;

            // divide the wave time by 2 and round to the nearest number
            long timeSlot = Math.round(waveTime/2.0);

            // calculates the number of timeSlots needed to fit into the duration
            int numberRepeats = Math.round((long)seconds/timeSlot);

            if (numberRepeats > 2500)
                Log.w(TAG, "Large vibration patterns may cause delay before vibration. Consider reducing frequency or duration.");

            if (numberRepeats == 0)
                numberRepeats++;    // plays at least once (numberRepeats = 0 due to rounding in the case of extremely short vibrations)

            if (numberRepeats % 2 == 1)
                numberRepeats++;    // ensures even number of time slots (1 to pause, 1 to vibrate)

            long [] fullPattern = new long[numberRepeats+2];
            fullPattern[0] = 0;                     // removes initial pause
            fullPattern[numberRepeats] = timeSlot;  // adds an ending pause (required for repeating)
            fullPattern[numberRepeats+1] = 0;       // removes ending run (required for repeating)

            // sets pattern for (approximately) the given duration
            for (int i = 1; i < numberRepeats; i++) {
                fullPattern[i] = timeSlot;
            }

            logVibrationPattern(fullPattern);
            vibrationUnit.vibrate(fullPattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    private void Vibrate(long[] pattern) {
        initializeIfNecessary();
        
        if (vibrationUnit.hasVibrator()) {
            logVibrationPattern(pattern);
            vibrationUnit.vibrate(pattern, -1);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }

    private void Vibrate(long[] pattern, int repeatAtIndex) {
        initializeIfNecessary();

        if (vibrationUnit.hasVibrator()) {
            logVibrationPattern(pattern);
            vibrationUnit.vibrate(pattern, repeatAtIndex);
        } else {
            Log.v(TAG, "No Vibration Device! Did you initialize?");
        }
    }
    
    private double secondElement(double ratio, double first) {
        return ratio*first;
    }

    private double firstElement(long duration, int numberOfCycles, double ratio) {
        return duration/(numberOfCycles*(1+ratio));
    }

    private double linearRatio(double intensity) {
        return intensity;
    }

    private double upCurveRatio(double intensity) {
        return 20 * intensity / (1 + intensity);
    }

    private double initialRatio(double intensity) {
        return 1 / (1.000001 - intensity) - 1;
    }

    private int numberOfCycles(double intensity, long duration) {
        return (int) (duration/2 - (duration-2)* Math.abs(intensity - 0.5));
    }

    /**************************************************************************************
     * Function Calculation
     **************************************************************************************/

    double pulse(double time, long duration) {
        final double pi = 3.14;
        final double frequency = 1d/duration; // Frequency in Hz
        return 1*(1 + Math.sin(2 * pi * frequency * time));
    }

    double line (double time, long duration) {
        return time/duration;
    }

    double exponential(double time, long duration) {
        double result = (Math.pow(Math.E, 3*(time/duration))-1)/20;
        return result;
    }

    /**************************************************************************************
     * Helpers
     **************************************************************************************/

    private long biasedRound(double number) {
        return Math.round(number);
    }

    private long[] concat(long[] a, long[] b) {
        int aLen = a.length;
        int bLen = b.length;
        long[] c= new long[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    private void logVibrationPattern(long[] pattern) {
        String t = "";
        for (int i = 0; i < pattern.length; i++) {
            if (i < pattern.length - 1) {
                t += pattern[i] + ", ";
            } else {
                t += pattern[i];
            }
        }
        Log.v(TAG, "Vibrating Pattern: " + t);
    }

    private void logVariables(int timeslice, double intensity, long duration, long i) {
        Log.v(TAG, "timeslice: " + timeslice + ", intensity: " + intensity + ", duration: " + duration + ", i: " + i);
    }

    /*
    This seems to make the changes in intensity easier to distinguish
    */
    private long[] addPause(long[] pattern) {
        return concat(pattern, new long[]{10l, 0l});
    }

    private long calculateTime(long[] fullPattern) {
        long total = 0l;
        for (int i = 0; i < fullPattern.length; i++) {
            long tmp = fullPattern[i];
            total += tmp;
        }
        return total;
    }

    private long[] generateVibrationPattern(double intensity, double duration) {
        return generateVibrationPattern(intensity, duration, 0);
    }

    private long[] generateVibrationPattern(double intensity, double duration, int ratiocalc) {

        // edge cases and wrong intensities
        if (intensity >= 1.0d){
            long [] pattern = new long[2];
            pattern[0] = 0;
            pattern[1] = (long)duration;    //had "duration-1" before... unsure if necessary
            return pattern;
        } else if (intensity <= 0.0d) {
            long [] pattern = new long[2];
            pattern[0] = (long)duration;
            pattern[1] = 0;
            return pattern;
        }

        double ratio = 0;
        int numberOfCycles = numberOfCycles(intensity, (long)duration);
        if (ratiocalc == 0) {
            ratio = linearRatio(intensity);
        } else if (ratiocalc == 1) {
            ratio = initialRatio(intensity);
        } else if (ratiocalc == 2) {
            ratio = upCurveRatio(intensity);
        }

        double first = firstElement((long)duration, numberOfCycles, ratio);
        double second = secondElement(ratio, first);
        long[] pattern = new long[ numberOfCycles*2 ];
        for (int i = 0; i < numberOfCycles*2; i = i +2) {

            pattern[i] = biasedRound(first);
            pattern[i+1] = biasedRound(second);
        }

        return pattern;
    }
}
