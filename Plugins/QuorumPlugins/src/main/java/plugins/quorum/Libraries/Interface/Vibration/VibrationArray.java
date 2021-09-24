
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

    public void Add(double duration) {
        quorum.Libraries.Interface.Vibration.VibrationCommand command = new quorum.Libraries.Interface.Vibration.VibrationCommand();
        command.SetDuration(duration);
        command.SetIntensity(1.0);
        content.add(command);
    }    

    public void Add(double duration, double intensity) {
        quorum.Libraries.Interface.Vibration.VibrationCommand command = new quorum.Libraries.Interface.Vibration.VibrationCommand();
        command.SetDuration(duration);
        command.SetIntensity(intensity);
        content.add(command);
    }

    public double GetDuration(int index) {
        return content.get(index).GetDuration();
    }

    public double GetIntensity(int index) {
        return content.get(index).GetIntensity();
    }

    public void Remove(int index) {
        content.remove(index);
    }

    public void RemoveAll() {
        content.clear();
    }

    public int GetSize() {
        return content.size();
    }
 }
