
package plugins.quorum.Libraries.Vibration;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import quorum.Libraries.Vibration.VibrationArray_;
import quorum.Libraries.Vibration.VibrationCommand_;

public class VibrationPattern {
    List<VibrationStep> content;

    public VibrationPattern () {
        this.content = new ArrayList<VibrationStep>();
    }

    public VibrationPattern(List<VibrationStep> content) {
        this.content = content;
    }

    public VibrationPattern (VibrationArray_ vibrations) {
        this.content = new ArrayList<VibrationStep>();
        for (int i = 0; i < vibrations.GetSize(); i++) {
            VibrationStep newCommand = new VibrationStep(vibrations.GetDuration(i), vibrations.GetIntensity(i));
            this.content.add(newCommand);
        }
    }

    public void add(VibrationStep item) {
        content.add(item);
    }
    
    public void add(double seconds) {
        VibrationStep item = new VibrationStep();
        item.setDuration(seconds);
        content.add(item);
    }

    public void add(double seconds, double intensity) {
        VibrationStep item = new VibrationStep();
        item.setDuration(seconds);
        item.setIntensity(intensity);
        content.add(item);
    }

    public void remove(int index) {
        content.remove(index);
    }

//    public VibrationStep get(int index) {
//        return content.get(index);
//    }

    public double getDuration(int index) {
        return content.get(index).getDuration();
    }

    public double getIntensity(int index) {
        return content.get(index).getIntensity();
    }

    public void removeAll() {
        content.clear();
    }

    public int getSize() {
        return content.size();
    }
    
    public VibrationPattern frequencyPattern(double frequency) {
        removeAll();
        // derived from f = 1/T, this is T = 1/f
        // times 1000 because we are dealing in ms
        double overallTime = (1/frequency) * 1000;
        
        // divide the time by 2 and round to the nearest number
        long timeSlot = Math.round(overallTime/2.0);
        
        // create the simple pattern
        // first pause is 0
        // runs for half the time
        // stops for half the time
        VibrationStep step = new VibrationStep(timeSlot, 1.0);
        VibrationStep step2 = new VibrationStep(timeSlot, 0.0);
        content.add(step);
        content.add(step2);
        return this;
    }

    public VibrationPattern simplePattern(double duration, double intensity, int cycleLength) {
        VibrationStep command = new VibrationStep();
        
        double numberCycles = duration/cycleLength;
        
        for (int i = 0; i < numberCycles; i++) {
            command.setDuration(cycleLength/2);
            command.setIntensity(intensity);
            content.add(command);
            command = new VibrationStep();
            command.setDuration(cycleLength/2);
            command.setIntensity(0.0d);
            content.add(command);
            command = new VibrationStep();
        }
        
        return this;
    }

    public void add(int duration, double intensity) {
        VibrationStep command = new VibrationStep();
        command.setDuration(duration);
        command.setIntensity(intensity);
        content.add(command);
    }
}
