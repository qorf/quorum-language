package plugins.quorum.Libraries.Robots.Lego;

public class Utility {
    public Object me_ = null;
    
    public void DelayMilliseconds(int milliseconds) {
        lejos.utility.Delay.msDelay(milliseconds);
    }
}
