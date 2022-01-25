/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import java.util.LinkedList;
import quorum.Libraries.Interface.Events.ResizeEvent;
import quorum.Libraries.Containers.Array_;

/**
 *
 * @author alleew
 */
public class ResizeProcessor 
{
    public java.lang.Object me_ = null;
    
    // Events are provided to this queue from the DesktopDisplay. See the
    // GLFWFramebufferSizeCallback to see where these are supplied from.
    public static LinkedList<ResizeEvent> resizeEvents = new LinkedList<>();

    public void Update(Array_ events)
    {
        while (!resizeEvents.isEmpty()) 
        {
            ResizeEvent event = resizeEvents.remove();

            events.Add(event);
        }
    }
    
    public static void AddResizeEvent(long window, int width, int height)
    {
        ResizeEvent event = new ResizeEvent();
        event.SetWidth(width);
        event.SetHeight(height);
        resizeEvents.add(event);
    }
}
