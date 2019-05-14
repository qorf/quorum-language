
package plugins.quorum.Libraries.Interface.Vibration;

import java.util.ArrayList;
import java.util.List;
import quorum.Libraries.Interface.Vibration.VibrationArray_;
import quorum.Libraries.Interface.Vibration.VibrationCommand_;



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
            VibrationCommand_ command = vibrations.Get(i);
            VibrationStep newCommand = new VibrationStep(command.GetDuration(), command.GetIntensity());
            this.content.add(newCommand);
        }
    }
    
    public VibrationPattern LongWeak() {
        this.add(1000, 0.01);
        return this;
    }
    
    public VibrationPattern LongMedium() {
        this.add(1000, 0.5);
        return this;
    }
    
    public VibrationPattern LongStrong() {
        this.add(1000, 1.0);
        return this;
    }
    
    public VibrationPattern ShortMedium() {
        this.add(100, 0.5);
        return this;
    }
    
    public VibrationPattern ShortWeak() {
        this.add(100, 0.01);
        return this;
    }
    
    public VibrationPattern ShortStrong() {
        this.add(100, 0.8);
        return this;
    }
    
    public VibrationPattern ShortSharp() {
        this.add(50, 1.0);
        return this;
    }
    
    public VibrationPattern DoublePulse() {
        this.add(25, 0.8);
        this.add(50, 0.0);
        this.add(25, 0.8);
        return this;
    }
      
    public VibrationPattern DoubleClick() {
        this.add(25,  1.0);
        this.add(100, 0.0);
        this.add(25,  1.0);
        return this;
    }  
    
    public void add(VibrationStep item) {
        content.add(item);
    }
    
    public void remove(int index) {
        content.remove(index);
    }
    
    public VibrationStep get(int index) {
        return content.get(index);
    }
    
    public void removeAll() {
        content.clear();
    }

    public int getSize() {
        return content.size();
    }
    
    public VibrationPattern simplePattern(int duration, double intensity, int cycleLength) {
        VibrationStep command = new VibrationStep();
        
        int numberCycles = duration/cycleLength;
        
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
