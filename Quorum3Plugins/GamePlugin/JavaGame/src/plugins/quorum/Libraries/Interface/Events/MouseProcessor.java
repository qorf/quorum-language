/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 *
 * @author alleew
 */
public class MouseProcessor 
{
    public java.lang.Object me_ = null;
    
    int pressedButtons = 0;
    
    public void GetCurrentEventsNative()
    {
        quorum.Libraries.Interface.Events.MouseProcessor quorumProcessor = (quorum.Libraries.Interface.Events.MouseProcessor)me_;
        
        quorum.Libraries.Containers.List_ events = quorumProcessor.events;
        
        if (Mouse.isCreated())
        {
            
            while (Mouse.next())
            {
                quorum.Libraries.Interface.Events.MouseEvent event = new quorum.Libraries.Interface.Events.MouseEvent();
                
                event.x = (int)(Mouse.getEventX() * Display.getPixelScaleFactor());
                event.y = (int)(Mouse.getEventY() * Display.getPixelScaleFactor());
                
                int button = Mouse.getEventButton();
                
                // Ignore any unknown buttons.
                if (button < -1 || button > 4)
                    continue;
                
                event.mouseButton = button;
                
                // If the button is -1, then no button was pressed. Check for
                // mouse dragged, scrolled, or moved.
                if (button == -1)
                {
                    if (Mouse.getEventDWheel() != 0)
                    {
                        event.eventType = event.SCROLLED_MOUSE;
                        event.scrollAmount = Mouse.getEventDWheel();
                    }
                    else
                    {
                        if (pressedButtons > 0)
                            event.eventType = event.DRAGGED_MOUSE;
                        else
                            event.eventType = event.MOVED_MOUSE;
                        
                        event.movementX = (int)(Mouse.getEventDX() * Display.getPixelScaleFactor());
                        event.movementY = (int)(Mouse.getEventDY() * Display.getPixelScaleFactor());
                    }
                }
                else
                {
                    if (Mouse.getEventButtonState())
                    {   
                        event.eventType = event.CLICKED_MOUSE;
                        pressedButtons++;
                    }
                    else
                    {
                        event.eventType = event.RELEASED_MOUSE;
                        pressedButtons--;
                    }
                }
                /*
				touchEvents.add(event);
				mouseX = event.x;
				mouseY = event.y;
				deltaX = (int)(Mouse.getEventDX() * Display.getPixelScaleFactor());
				deltaY = (int)(Mouse.getEventDY() * Display.getPixelScaleFactor());
                */
                events.Add(event);
            }
        }
    }
    
}
