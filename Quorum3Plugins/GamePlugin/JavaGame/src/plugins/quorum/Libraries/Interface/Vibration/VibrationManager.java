package plugins.quorum.Libraries.Interface.Vibration;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

public class VibrationManager {
    
    private long endTimeLastRun;
    private Vibrator vibrationUnit;
    private int timeslice;
    private Activity activity;

    private static final String TAG = "VibrationManager";

    public Activity getActivity() {
        return activity;
    }
    
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    
    private Vibrator getVibrationUnit() {
        Log.v(TAG, "Got vibration unit");
        return (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }
    
    public int getTimeSlice() {
        return this.timeslice;
    }
    
    public void setTimeSlice(int timeslice) {
        this.timeslice = timeslice;
    }
    
    public void initialize() {
        this.vibrationUnit = getVibrationUnit();
    }
    
    public VibrationManager() {
        timeslice = 20;
    }

    public VibrationManager(int timeslice) {
        this.timeslice = timeslice;
    }
    
    public void setTimeslice(int timeslice) {
        this.timeslice = timeslice;
    }
    
    public int getTimeslice() {
        return this.timeslice;
    }
    
    private long[] arrayConversion(PatternArray array) {
        long[] result = new long[array.GetSize()];
        for (int i = 0; i < result.length; i++) {
            result[i] = array.Get(i);
        }
        return result;
    }

    public void vibratePattern(PatternArray pattern) {
        long[] patternarray = arrayConversion(pattern);
        vibratePattern(patternarray);
    }
    
    public void vibratePattern(long[] patternarray) {
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {
                vibrationUnit.vibrate(patternarray, -1);
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(patternarray);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }
    
    public void stop() {
        endTimeLastRun = 0;
        if (vibrationUnit != null) {
            vibrationUnit.cancel();
        }
    }
    
    
    public void vibratePattern(long[] longpattern, int repeat){
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        logVibrationPattern(longpattern);
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {
                vibrationUnit.vibrate(longpattern, repeat);
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(longpattern);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }

    public void vibratePattern(PatternArray pattern, int repeat){
        long[] longpattern = arrayConversion(pattern);
        vibratePattern(longpattern, repeat);
    }
    
    
    public void initializeIfNecessary() {
        if (vibrationUnit == null) {
            Log.v(TAG, "Initializing");
            initialize();
        }
    }
    
    public void vibrateGeneratedPattern(double intensity, int duration) {
        this.vibrateGeneratedPattern(intensity, duration, -1);
    }

    public void vibrateGeneratedPattern(double intensity, int duration, int repeat) {
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        long[] pattern = this.generateVibrationPattern(intensity, duration);
        logVibrationPattern(pattern);
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {
                vibrationUnit.vibrate(pattern, repeat);
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(pattern);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }
    
    public void vibratePattern(VibrationPattern commandArray) {
        
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        long [] fullPattern = {};
        int size = commandArray.getSize();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {
                for (int i = 0; i < size; i++) {
                    //VibrationCommand command = commandArray.Get(i);
                    VibrationStep command = commandArray.get(i);
                    int duration = command.getDuration();
                    double intensity = command.getIntensity();
                    long[] pattern = generateVibrationPattern(intensity, duration);

                    logVibrationPattern(pattern);
                    if (pattern.length % 2 != 0) {
                        Log.e("CycleCount", "Half a cycle found!");
                    }

                    fullPattern = concat(fullPattern, pattern);
                    fullPattern = addPause(fullPattern);

                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(fullPattern);
                logVibrationPattern(fullPattern);
                vibrationUnit.vibrate(fullPattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }

    /**************************************************************************************
     * Linear Pattern
     **************************************************************************************/

    public void vibrateLinearPattern(int duration) {
        vibrateLinearPattern(duration, timeslice);
    }

    public void vibrateLinearPattern(int duration, int timeslice) {
        initializeIfNecessary();
        long [] fullPattern = {};
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {

                for (long i = 0; i <= duration / timeslice; i++) {

                    double intensity = line(i * timeslice, duration);
                    long[] pattern = generateVibrationPattern(intensity, timeslice);

                    logVibrationPattern(pattern);
                    if (pattern.length % 2 != 0) {
                        Log.e("CycleCount", "Half a cycle found!");
                    }

                    fullPattern = concat(fullPattern, pattern);
                    fullPattern = addPause(fullPattern);
                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(fullPattern);
                logVibrationPattern(fullPattern);
                vibrationUnit.vibrate(fullPattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }

    public void vibrateLinearPattern(int duration, int timeslice, int ratiocalc) {
        initializeIfNecessary();
        
        long[] fullPattern = {};
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun){
            if (vibrationUnit.hasVibrator()) {

                for (long i = 0; i <= duration / timeslice; i++) {

                    double intensity = line(i * timeslice, duration);
                    long[] pattern = generateVibrationPattern(intensity, timeslice, ratiocalc);

                    logVibrationPattern(pattern);
                    if (pattern.length % 2 != 0) {
                        Log.e("CycleCount", "Half a cycle found!");
                    }

                    fullPattern = concat(fullPattern, pattern);
                    fullPattern = addPause(fullPattern);
                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(fullPattern);
                logVibrationPattern(fullPattern);
                vibrationUnit.vibrate(fullPattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }


    public void vibrateLinearPatternStep(int i, int duration) {
        vibrateLinearPatternStep(i, duration, timeslice);
    }

    public void vibrateLinearPatternStep(int i, int duration, int timeslice) {
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {

                double intensity = line(i*timeslice, duration);
                long[] pattern = generateVibrationPattern(intensity, timeslice);

                logVibrationPattern(pattern);
                if (pattern.length % 2 != 0) {
                    Log.e("CycleCount", "Half a cycle found!");
                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(pattern);
                vibrationUnit.vibrate(pattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }




    /**************************************************************************************
     * Wave Pattern
     **************************************************************************************/

    public void vibrateWavePattern(int duration) {
        vibrateWavePattern(duration, timeslice);
    }


    public void vibrateWavePattern(int duration, int timeslice) {
        initializeIfNecessary();
        long [] fullPattern = {};
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {
                for (long i = 0; i <= duration/timeslice; i++) {

                    double intensity = pulse( i * timeslice, duration/1000);
                    long[] pattern = generateVibrationPattern(intensity, timeslice);

                    logVibrationPattern(pattern);
                    if (pattern.length % 2 != 0) {
                        Log.e("CycleCount", "Half a cycle found!");
                    }

                    fullPattern = concat(fullPattern, pattern);
                    fullPattern = concat(fullPattern, new long[] {15l, 0l});
                }
                logVibrationPattern(fullPattern);
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(fullPattern);
                vibrationUnit.vibrate(fullPattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }

    public void vibrateWavePatternStep(int i, int duration) {
        vibrateWavePatternStep(i, duration, timeslice);
    }

    public void vibrateWavePatternStep(int i, int duration, int timeslice) {
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator()) {

                double intensity = pulse(i*timeslice, duration/1000);
                long[] pattern = generateVibrationPattern(intensity, timeslice);

                logVibrationPattern(pattern);
                if (pattern.length % 2 != 0) {
                    Log.e("CycleCount", "Half a cycle found!");
                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(pattern);
                vibrationUnit.vibrate(pattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }

    /**************************************************************************************
     * Exponential Pattern
     **************************************************************************************/
    public void vibrateExponentialPattern(int duration) {
        vibrateExponentialPattern(duration, timeslice);
    }


    public void vibrateExponentialPattern(int duration, int timeslice) {
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        long [] fullPattern = {};
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator())  {
                for (long i = 0; i <= duration/timeslice; i++) {

                    double intensity = exponential( i * timeslice, duration);
                    long[] pattern = generateVibrationPattern(intensity, timeslice);

                    logVibrationPattern(pattern);
                    if (pattern.length % 2 != 0) {
                        Log.e("CycleCount", "Half a cycle found!");
                    }

                    fullPattern = concat(fullPattern, pattern);
                    fullPattern = concat(fullPattern, new long[] {15l, 0l});
                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(fullPattern);
                logVibrationPattern(fullPattern);
                vibrationUnit.vibrate(fullPattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }

    public void vibrateExponentialPatternStep(int i, int duration) {
        VibrationManager.this.vibrateExponentialPatternStep(i, duration, timeslice);
    }

    public void vibrateExponentialPatternStep(int i, int duration, int timeslice) {
        initializeIfNecessary();
        long now = System.currentTimeMillis();
        if (now >= endTimeLastRun) {
            if (vibrationUnit.hasVibrator() ) {

                double intensity = exponential(i*timeslice, duration);
                long[] pattern = generateVibrationPattern(intensity, timeslice);

                logVibrationPattern(pattern);
                if (pattern.length % 2 != 0) {
                    Log.e("CycleCount", "Half a cycle found!");
                }
                now = System.currentTimeMillis();
                endTimeLastRun = now + calculateTime(pattern);
                vibrationUnit.vibrate(pattern, -1);
            } else {
                Log.v(TAG, "No Vibration Device! Did you initialize?");
            }
        }
    }


    public long[] generateVibrationPattern(double intensity, int duration) {
        return generateVibrationPattern(intensity, duration, 0);
    }


    public long[] generateVibrationPattern(double intensity, int duration, int ratiocalc) {

        // edge cases and wrong intensities
        if (intensity >= 1.0f){
            long [] pattern = new long[2];
            pattern[0] = 0;
            pattern[1] = duration-1;
            return pattern;
        } else if (intensity <= 0.0f) {
            long [] pattern = new long[2];
            pattern[0] = duration;
            pattern[1] = 0;
            return pattern;
        }

        double ratio = 0;
        int numberOfCycles = numberOfCycles(intensity, duration);
        if (ratiocalc == 0) {
            ratio = linearRatio(intensity);
        } else if (ratiocalc == 1) {
            ratio = initialRatio(intensity);
        } else if (ratiocalc == 2) {
            ratio = upCurveRatio(intensity);
        }

        double first = firstElement(duration, numberOfCycles, ratio);
        double second = secondElement(ratio, first);
        //Log.v("Vibration Function" , "" + first + ", " + second + "; ");
        long[] pattern = new long[ numberOfCycles*2 ];
        for (int i = 0; i < numberOfCycles*2; i = i +2) {

            pattern[i] = BiasedRound(first);
            pattern[i+1] = BiasedRound(second);
        }

        return pattern;
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
        // Log.v("Moep", "Res: " + result + " time/duration: " + time/duration);
        return result;
    }

    /**************************************************************************************
     * Helpers
     **************************************************************************************/

    private long BiasedRound(double number) {
        return Math.round(number);
    }

    public long[] concat(long[] a, long[] b) {
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

}
