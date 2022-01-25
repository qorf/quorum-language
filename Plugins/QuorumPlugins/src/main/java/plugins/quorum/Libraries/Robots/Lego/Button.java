package plugins.quorum.Libraries.Robots.Lego;

import java.util.HashMap;
import lejos.hardware.Key;

public class Button {
    public Object me_ = null;
    private final HashMap<String, Key> keys;
    
    public Button() {
        keys = new HashMap<>();
        keys.put("enter", lejos.hardware.Button.ENTER);
        keys.put("left", lejos.hardware.Button.LEFT);
        keys.put("right", lejos.hardware.Button.RIGHT);
        keys.put("escape", lejos.hardware.Button.ESCAPE);
        keys.put("up", lejos.hardware.Button.UP);
        keys.put("down", lejos.hardware.Button.DOWN);
    }
    
    public boolean IsButtonPressed(String button) {
        Key key = keys.get(button);
        if (key != null)
            return key.isDown();
        else
            return false; //should be an exception--incorrect String passed
    }
    
    public void SetLightPattern(int pattern) {
        lejos.hardware.Button.LEDPattern(pattern);
    }
    
    public void WaitForButtonPress() {
        lejos.hardware.Button.waitForAnyPress();
    }
}
