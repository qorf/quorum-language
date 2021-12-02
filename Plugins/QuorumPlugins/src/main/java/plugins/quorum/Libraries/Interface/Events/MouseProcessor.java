/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import org.lwjgl.glfw.GLFW;
import java.util.LinkedList;
import quorum.Libraries.Interface.Events.MouseEvent;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Containers.Array_;

/**
 *
 * @author alleew
 */
public class MouseProcessor 
{
    public java.lang.Object me_ = null;

    /*
    Bitmask of the currently pressed buttons on the mouse. The bitmask is based
    on these constants:
    LEFT = 1
    RIGHT = 2
    MIDDLE = 4
    BACK = 8
    FORWARD = 16
    
    For example, 00001 represents the left button down, all others up.
    01100 represents the middle and back buttons down, all others up.
    */
    public static int pressedButtons = 0;
    
    public static LinkedList<MouseEvent> mouseEvents = new LinkedList<>();
    
    // The position of the cursor during the last added mouse event.
    private static double lastX = 0;
    private static double lastY = 0;
    
    public void Update(Array_ events)
    {
        while (!mouseEvents.isEmpty())
        {
            events.Add(mouseEvents.remove());
        }
    }
    
    /*
    Converts the given GLFW cursor position event information into a Quorum
    MouseEvent and adds it to the processor queue.
    */
    public static void AddMouseMovementEvent(long window, double x, double y)
    {
        // Invert the y-axis (GLFW places 0,0 at top-left) and account for the
        // difference between window coordinates and pixel coordinates.
        double scale = GameStateManager.display.GetPixelScaleFactor();
        x = x * scale;
        y = GameStateManager.display.GetHeight() - y * scale;
        
        MouseEvent event = new MouseEvent();
        event.x = (int)x;
        event.y = (int)y;
        event.movementX = (int)(x - lastX);
        event.movementY = (int)(y - lastY);
        event.mouseButton = pressedButtons;
        if (pressedButtons == 0)
            event.eventType = event.MOVED_MOUSE;
        else
            event.eventType = event.DRAGGED_MOUSE;
        
        // The Quorum MouseEvent requires casting to int for its fields, but we
        // still retain the information in lastX and lastY as doubles so that we
        // maintain sub-pixel precision for future changes.
        lastX = x;
        lastY = y;
        
        mouseEvents.add(event);
    }
    
    /*
    Converts the given GLFW mouse event information into a Quorum MouseEvent and
    adds it to the processor queue.
    */
    public static void AddMouseEvent(long window, int button, int action, int modifiers)
    {
        int quorumButton = 1 << button;

        MouseEvent event = new MouseEvent();
        
        if (action == GLFW.GLFW_PRESS)
        {
            // Set the bit of the button to 1.
            pressedButtons |= quorumButton;
            event.eventType = event.CLICKED_MOUSE;
        }
        else
        {
            // Set the bit of the button to 0.
            pressedButtons &= ~(quorumButton);
            event.eventType = event.RELEASED_MOUSE;
        }
        
        event.x = (int)lastX;
        event.y = (int)lastY;
        event.mouseButton = quorumButton;
        
        mouseEvents.add(event);
    }
    
    /*
    Converts the given GLFW scroll event information into a Quorum MouseEvent 
    and adds it to the processor queue.
    */
    public static void AddMouseWheelEvent(long window, double x, double y)
    {
        MouseEvent event = new MouseEvent();
        event.eventType = event.SCROLLED_MOUSE;
        event.scrollAmount = y;
        event.x = (int)lastX;
        event.y = (int)lastY;
        mouseEvents.add(event);
    }
    
}
