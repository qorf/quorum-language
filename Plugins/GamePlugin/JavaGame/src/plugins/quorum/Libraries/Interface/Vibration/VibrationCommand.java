
package plugins.quorum.Libraries.Interface.Vibration;


public class VibrationCommand {
    public Object me_;
    long duration;
    double intensity;
    
    public VibrationCommand() {
        duration = 100;
        intensity = 1.0f;
    }
    
    public VibrationCommand(long duration, double intensity) {
        this.duration = duration;
        this.intensity = intensity;
    }
    
    public void SetDuration(int duration) {
        this.duration = duration;
    }
    
    public int GetDuration() {
        return (int)duration;
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
