/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import quorum.Libraries.Interface.Events.TextInputEvent;
import quorum.Libraries.Containers.Array_;
import java.util.LinkedList;

/**
 *
 * @author Bill
 */
public class TextInputProcessor 
{
    public java.lang.Object me_ = null;
    
    // Events are provided to this queue from the DesktopDisplay. See the
    // KeyboardEvent method in plugins.quorum.Libraries.Game.DesktopDisplay
    public static LinkedList<quorum.Libraries.Interface.Events.TextInputEvent> textEvents = new LinkedList<>();

    public void Update(Array_ events)
    {
        while (!textEvents.isEmpty()) 
        {
            quorum.Libraries.Interface.Events.TextInputEvent event = textEvents.remove();

            events.Add(event);
        }
    }
    
    public static void AddTextInputEvent(long window, int codepoint, String text)
    {
        TextInputEvent event = new TextInputEvent();
        event.SetText(text);
        event.SetUnicodeValue(codepoint);
        textEvents.add(event);
    }
}
