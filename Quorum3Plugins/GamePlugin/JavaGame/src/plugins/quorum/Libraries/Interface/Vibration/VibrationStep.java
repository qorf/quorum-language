
package plugins.quorum.Libraries.Interface.Vibration;


public class VibrationStep {
    public Object me_;
    long duration;
    double intensity;
    
    public VibrationStep() {
        duration = 100;
        intensity = 1.0f;
    }
    
    public VibrationStep(long duration, double intensity) {
        this.duration = duration;
        this.intensity = intensity;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public int getDuration() {
        return (int)duration;
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
