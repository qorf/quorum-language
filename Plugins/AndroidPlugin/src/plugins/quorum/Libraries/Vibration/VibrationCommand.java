
package plugins.quorum.Libraries.Vibration;


public class VibrationCommand {
    public Object me_;
    public double duration;
    public double intensity;
    
    public VibrationCommand() {
        duration = 1;
        intensity = 1.0d;
    }
    
    public VibrationCommand(double duration, double intensity) {
        this.duration = duration;
        this.intensity = intensity;
    }
    
    public void SetDuration(double duration) {
        this.duration = duration;
    }
    
    public double GetDuration() {
        return duration;
    }
            
    public void SetIntensity(double intensity) {
        if (0 <= intensity && intensity <= 1.0) {
            this.intensity = intensity;
        }
    }

    public double GetIntensity() {
        return this.intensity;
    }
}
