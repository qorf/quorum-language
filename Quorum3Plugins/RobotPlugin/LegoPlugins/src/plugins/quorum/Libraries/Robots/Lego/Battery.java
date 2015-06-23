package plugins.quorum.Libraries.Robots.Lego;

public class Battery {
    public Object me_ = null;
    
    public double GetVoltage() {
        return (double)lejos.hardware.Battery.getVoltage();
    }
    
    public double GetCurrent() {
        return (double)lejos.hardware.Battery.getBatteryCurrent();
    }
}
