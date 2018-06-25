
package plugins.quorum.Libraries.Interface.Vibration;

import java.util.ArrayList;
import java.util.List;

import quorum.Libraries.Interface.Vibration.VibrationCommand_;
import quorum.Libraries.Interface.Vibration.VibrationArray_;

public class VibrationArray {
    public Object me_;
    List<VibrationCommand_> content;
    
    public VibrationArray () {
        this.content = new ArrayList<VibrationCommand_>();
    }
    
    public VibrationArray(List<VibrationCommand_> content) {
        this.content = content;
    }
    
    
    public void Add(VibrationCommand_ item) {
        content.add(item);
    }
    
    public void Remove(int index) {
        content.remove(index);
    }
    
    public VibrationCommand_ Get(int index) {
        return content.get(index);
    }
    
    public void RemoveAll() {
        content.clear();
    }

    public int GetSize() {
        return content.size();
    }
    
    public VibrationArray_ SimplePattern(int duration, double intensity, int cycleLength) {
        quorum.Libraries.Interface.Vibration.VibrationCommand command = new quorum.Libraries.Interface.Vibration.VibrationCommand();
        
        int numberCycles = duration/cycleLength;
        
        for (int i = 0; i < numberCycles; i++) {
            command.SetDuration(cycleLength/2);
            command.SetIntensity(intensity);
            content.add(command);
            command = new quorum.Libraries.Interface.Vibration.VibrationCommand();
            command.SetDuration(cycleLength/2);
            command.SetIntensity(0.0d);
            content.add(command);
            command = new quorum.Libraries.Interface.Vibration.VibrationCommand();
        }
        
        return (quorum.Libraries.Interface.Vibration.VibrationArray_) me_;
    }
    
    public void Add(int duration, double intensity) {
        quorum.Libraries.Interface.Vibration.VibrationCommand command = new quorum.Libraries.Interface.Vibration.VibrationCommand();
        command.SetDuration(duration);
        command.SetIntensity(intensity);
        content.add(command);
    }
        
}
