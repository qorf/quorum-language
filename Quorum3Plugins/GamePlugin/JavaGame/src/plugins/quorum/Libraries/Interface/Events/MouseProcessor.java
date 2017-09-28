/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import quorum.Libraries.Interface.Events.MouseEvent;

/**
 *
 * @author alleew
 */
public class MouseProcessor 
{
    public java.lang.Object me_ = null;

    /*
    Bitmask of the currently pressed buttons on the mouse. The nth bit
    represents the nth mouse button, using the following constants from Quorum:
    LEFT = 0
    RIGHT = 1
    MIDDLE = 2
    BACK = 3
    FORWARD = 4
    
    For example, 00001 represents the left button down, all others up.
    01100 represents the middle and back buttons down, all others up.
    */
    public static int pressedButtons = 0;
    
    // The position of the cursor during the last added mouse event.
    private static double lastX = 0;
    private static double lastY = 0;
    
    public void GetCurrentEventsNative()
    {
        quorum.Libraries.Interface.Events.MouseProcessor quorumProcessor = (quorum.Libraries.Interface.Events.MouseProcessor)me_;
        
        quorum.Libraries.Containers.List_ events = quorumProcessor.events;
        
//        if (Mouse.isCreated())
//        {
//            
//            while (Mouse.next())
//            {
//                quorum.Libraries.Interface.Events.MouseEvent event = new quorum.Libraries.Interface.Events.MouseEvent();
//                
//                event.x = (int)(Mouse.getEventX() * Display.getPixelScaleFactor());
//                event.y = (int)(Mouse.getEventY() * Display.getPixelScaleFactor());
//                
//                int button = Mouse.getEventButton();
//                
//                // Ignore any unknown buttons.
//                if (button < -1 || button > 4)
//                    continue;
//                
//                event.mouseButton = button;
//                
//                // If the button is -1, then no button was pressed. Check for
//                // mouse dragged, scrolled, or moved.
//                if (button == -1)
//                {
//                    if (Mouse.getEventDWheel() != 0)
//                    {
//                        event.eventType = event.SCROLLED_MOUSE;
//                        event.scrollAmount = Mouse.getEventDWheel();
//                    }
//                    else
//                    {
//                        if (pressedButtons > 0)
//                            event.eventType = event.DRAGGED_MOUSE;
//                        else
//                            event.eventType = event.MOVED_MOUSE;
//                        
//                        event.movementX = (int)(Mouse.getEventDX() * Display.getPixelScaleFactor());
//                        event.movementY = (int)(Mouse.getEventDY() * Display.getPixelScaleFactor());
//                    }
//                }
//                else
//                {
//                    if (Mouse.getEventButtonState())
//                    {   
//                        event.eventType = event.CLICKED_MOUSE;
//                        pressedButtons++;
//                    }
//                    else
//                    {
//                        event.eventType = event.RELEASED_MOUSE;
//                        pressedButtons--;
//                    }
//                }
//                /*
//				touchEvents.add(event);
//				mouseX = event.x;
//				mouseY = event.y;
//				deltaX = (int)(Mouse.getEventDX() * Display.getPixelScaleFactor());
//				deltaY = (int)(Mouse.getEventDY() * Display.getPixelScaleFactor());
//                */
//                events.Add(event);
//            }
//        }
    }
    
    /*
    Converts the given GLFW cursor position event information into a Quorum
    MouseEvent and adds it to the processor queue.
    */
    public static void AddMouseMovementEvent(long window, double x, double y)
    {
        if (pressedButtons == 0)
        {
            MouseEvent event = new MouseEvent();
            event.x = (int)x;
            event.y = (int)y;
            event.movementX = (int)(x - lastX);
            event.movementY = (int)(y - lastY);
            event.eventType = event.MOVED_MOUSE;
        }
        else
        {
            // Handle mouse drag event.
        }
        
        // The Quorum MouseEvent requires casting to int for its fields, but we
        // still retain the information in lastX and lastY as doubles so that we
        // maintain sub-pixel precision for future changes.
        lastX = x;
        lastY = y;
    }
    
    /*
    Converts the given GLFW mouse event information into a Quorum MouseEvent and
    adds it to the processor queue.
    */
    public static void AddMouseEvent(long window, int button, int action, int modifiers)
    {
        
    }
    
}
