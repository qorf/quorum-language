
package plugins.quorum.Libraries.Vibration;


public class VibrationStep {
    public Object me_;
    double duration;
    double intensity;
    
    public VibrationStep() {
        duration = 1;
        intensity = 1.0d;
    }
    
    public VibrationStep(double duration, double intensity) {
        this.duration = duration;
        this.intensity = intensity;
    }
    
    public void setDuration(double duration) {
        this.duration = duration;
    }
    
    public double getDuration() {
        return duration;
    }
            
    public void setIntensity(double intensity) {
        if (0 <= intensity && intensity <= 1.0) {
            this.intensity = intensity;
        }
    }

    public double getIntensity() {
        return this.intensity;
    }
    
}
