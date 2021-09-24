/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import org.lwjgl.glfw.GLFW;
import java.util.LinkedList;
import plugins.quorum.Libraries.Game.InputMonitor;

/**
 *
 * @author alleew
 */
public class KeyboardProcessor {

    public java.lang.Object me_ = null;
    
    // Events are provided to this queue from the DesktopDisplay. See the
    // KeyboardEvent method in plugins.quorum.Libraries.Game.DesktopDisplay
    public static LinkedList<quorum.Libraries.Interface.Events.KeyboardEvent> keyboardEvents = new LinkedList<>();

    public void Update() 
    {
        quorum.Libraries.Interface.Events.KeyboardProcessor quorumProcessor = (quorum.Libraries.Interface.Events.KeyboardProcessor) me_;
        quorum.Libraries.Containers.List_ events = quorumProcessor.events;

        while (!keyboardEvents.isEmpty()) 
        {
            quorum.Libraries.Interface.Events.KeyboardEvent event = keyboardEvents.remove();

            // Check if the key was pressed down and update the InputMonitor.
            if (event.eventType == event.PRESSED_KEY)
            {
                InputMonitor.pressedKeys++;
            }
            // Otherwise, a key has just been released.
            else if (event.eventType == event.RELEASED_KEY)
            {
                InputMonitor.pressedKeys--;
            }

            events.Add(event);
        }
    }

    public static int GetGameKeyCode(int glfwKeyCode) 
    {
        switch (glfwKeyCode)
        {
            case GLFW.GLFW_KEY_LEFT_BRACKET:
                return plugins.quorum.Libraries.Game.InputMonitor.LEFT_BRACKET;
            case GLFW.GLFW_KEY_RIGHT_BRACKET:
                return plugins.quorum.Libraries.Game.InputMonitor.RIGHT_BRACKET;
            case GLFW.GLFW_KEY_GRAVE_ACCENT:
                return plugins.quorum.Libraries.Game.InputMonitor.GRAVE;
            case GLFW.GLFW_KEY_NUM_LOCK:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_LOCK;
            case GLFW.GLFW_KEY_SCROLL_LOCK:
                return plugins.quorum.Libraries.Game.InputMonitor.SCROLL_LOCK;
            case GLFW.GLFW_KEY_CAPS_LOCK:
                return plugins.quorum.Libraries.Game.InputMonitor.CAPS_LOCK;
            case GLFW.GLFW_KEY_PAUSE:
                return plugins.quorum.Libraries.Game.InputMonitor.PAUSE;
            case GLFW.GLFW_KEY_PRINT_SCREEN:
                return plugins.quorum.Libraries.Game.InputMonitor.PRINT_SCREEN;
            case GLFW.GLFW_KEY_LEFT_SUPER:
                return plugins.quorum.Libraries.Game.InputMonitor.META_LEFT;
            case GLFW.GLFW_KEY_RIGHT_SUPER:
                return plugins.quorum.Libraries.Game.InputMonitor.META_RIGHT;
            case GLFW.GLFW_KEY_EQUAL:
                return plugins.quorum.Libraries.Game.InputMonitor.EQUALS;
            case GLFW.GLFW_KEY_0:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_0;
            case GLFW.GLFW_KEY_1:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_1;
            case GLFW.GLFW_KEY_2:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_2;
            case GLFW.GLFW_KEY_3:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_3;
            case GLFW.GLFW_KEY_4:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_4;
            case GLFW.GLFW_KEY_5:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_5;
            case GLFW.GLFW_KEY_6:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_6;
            case GLFW.GLFW_KEY_7:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_7;
            case GLFW.GLFW_KEY_8:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_8;
            case GLFW.GLFW_KEY_9:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_9;
            case GLFW.GLFW_KEY_A:
                return plugins.quorum.Libraries.Game.InputMonitor.A;
            case GLFW.GLFW_KEY_B:
                return plugins.quorum.Libraries.Game.InputMonitor.B;
            case GLFW.GLFW_KEY_C:
                return plugins.quorum.Libraries.Game.InputMonitor.C;
            case GLFW.GLFW_KEY_D:
                return plugins.quorum.Libraries.Game.InputMonitor.D;
            case GLFW.GLFW_KEY_E:
                return plugins.quorum.Libraries.Game.InputMonitor.E;
            case GLFW.GLFW_KEY_F:
                return plugins.quorum.Libraries.Game.InputMonitor.F;
            case GLFW.GLFW_KEY_G:
                return plugins.quorum.Libraries.Game.InputMonitor.G;
            case GLFW.GLFW_KEY_H:
                return plugins.quorum.Libraries.Game.InputMonitor.H;
            case GLFW.GLFW_KEY_I:
                return plugins.quorum.Libraries.Game.InputMonitor.I;
            case GLFW.GLFW_KEY_J:
                return plugins.quorum.Libraries.Game.InputMonitor.J;
            case GLFW.GLFW_KEY_K:
                return plugins.quorum.Libraries.Game.InputMonitor.K;
            case GLFW.GLFW_KEY_L:
                return plugins.quorum.Libraries.Game.InputMonitor.L;
            case GLFW.GLFW_KEY_M:
                return plugins.quorum.Libraries.Game.InputMonitor.M;
            case GLFW.GLFW_KEY_N:
                return plugins.quorum.Libraries.Game.InputMonitor.N;
            case GLFW.GLFW_KEY_O:
                return plugins.quorum.Libraries.Game.InputMonitor.O;
            case GLFW.GLFW_KEY_P:
                return plugins.quorum.Libraries.Game.InputMonitor.P;
            case GLFW.GLFW_KEY_Q:
                return plugins.quorum.Libraries.Game.InputMonitor.Q;
            case GLFW.GLFW_KEY_R:
                return plugins.quorum.Libraries.Game.InputMonitor.R;
            case GLFW.GLFW_KEY_S:
                return plugins.quorum.Libraries.Game.InputMonitor.S;
            case GLFW.GLFW_KEY_T:
                return plugins.quorum.Libraries.Game.InputMonitor.T;
            case GLFW.GLFW_KEY_U:
                return plugins.quorum.Libraries.Game.InputMonitor.U;
            case GLFW.GLFW_KEY_V:
                return plugins.quorum.Libraries.Game.InputMonitor.V;
            case GLFW.GLFW_KEY_W:
                return plugins.quorum.Libraries.Game.InputMonitor.W;
            case GLFW.GLFW_KEY_X:
                return plugins.quorum.Libraries.Game.InputMonitor.X;
            case GLFW.GLFW_KEY_Y:
                return plugins.quorum.Libraries.Game.InputMonitor.Y;
            case GLFW.GLFW_KEY_Z:
                return plugins.quorum.Libraries.Game.InputMonitor.Z;
            case GLFW.GLFW_KEY_LEFT_ALT:
                return plugins.quorum.Libraries.Game.InputMonitor.ALT_LEFT;
            case GLFW.GLFW_KEY_RIGHT_ALT:
                return plugins.quorum.Libraries.Game.InputMonitor.ALT_RIGHT;
            case GLFW.GLFW_KEY_BACKSLASH:
                return plugins.quorum.Libraries.Game.InputMonitor.BACKSLASH;
            case GLFW.GLFW_KEY_COMMA:
                return plugins.quorum.Libraries.Game.InputMonitor.COMMA;
            case GLFW.GLFW_KEY_DELETE:
                return plugins.quorum.Libraries.Game.InputMonitor.FORWARD_DELETE;
            case GLFW.GLFW_KEY_LEFT:
                return plugins.quorum.Libraries.Game.InputMonitor.LEFT;
            case GLFW.GLFW_KEY_RIGHT:
                return plugins.quorum.Libraries.Game.InputMonitor.RIGHT;
            case GLFW.GLFW_KEY_UP:
                return plugins.quorum.Libraries.Game.InputMonitor.UP;
            case GLFW.GLFW_KEY_DOWN:
                return plugins.quorum.Libraries.Game.InputMonitor.DOWN;
            case GLFW.GLFW_KEY_ENTER:
                return plugins.quorum.Libraries.Game.InputMonitor.ENTER;
            case GLFW.GLFW_KEY_HOME:
                return plugins.quorum.Libraries.Game.InputMonitor.HOME;
            case GLFW.GLFW_KEY_MINUS:
                return plugins.quorum.Libraries.Game.InputMonitor.MINUS;
            case GLFW.GLFW_KEY_PERIOD:
                return plugins.quorum.Libraries.Game.InputMonitor.PERIOD;
            case GLFW.GLFW_KEY_SEMICOLON:
                return plugins.quorum.Libraries.Game.InputMonitor.SEMICOLON;
            case GLFW.GLFW_KEY_LEFT_SHIFT:
                return plugins.quorum.Libraries.Game.InputMonitor.SHIFT_LEFT;
            case GLFW.GLFW_KEY_RIGHT_SHIFT:
                return plugins.quorum.Libraries.Game.InputMonitor.SHIFT_RIGHT;
            case GLFW.GLFW_KEY_SLASH:
                return plugins.quorum.Libraries.Game.InputMonitor.SLASH;
            case GLFW.GLFW_KEY_SPACE:
                return plugins.quorum.Libraries.Game.InputMonitor.SPACE;
            case GLFW.GLFW_KEY_TAB:
                return plugins.quorum.Libraries.Game.InputMonitor.TAB;
            case GLFW.GLFW_KEY_LEFT_CONTROL:
                return plugins.quorum.Libraries.Game.InputMonitor.CONTROL_LEFT;
            case GLFW.GLFW_KEY_RIGHT_CONTROL:
                return plugins.quorum.Libraries.Game.InputMonitor.CONTROL_RIGHT;
            case GLFW.GLFW_KEY_PAGE_DOWN:
                return plugins.quorum.Libraries.Game.InputMonitor.PAGE_DOWN;
            case GLFW.GLFW_KEY_PAGE_UP:
                return plugins.quorum.Libraries.Game.InputMonitor.PAGE_UP;
            case GLFW.GLFW_KEY_ESCAPE:
                return plugins.quorum.Libraries.Game.InputMonitor.ESCAPE;
            case GLFW.GLFW_KEY_END:
                return plugins.quorum.Libraries.Game.InputMonitor.END;
            case GLFW.GLFW_KEY_INSERT:
                return plugins.quorum.Libraries.Game.InputMonitor.INSERT;
            case GLFW.GLFW_KEY_BACKSPACE:
                return plugins.quorum.Libraries.Game.InputMonitor.BACKSPACE;
            case GLFW.GLFW_KEY_APOSTROPHE:
                return plugins.quorum.Libraries.Game.InputMonitor.APOSTROPHE;
            case GLFW.GLFW_KEY_F1:
                return plugins.quorum.Libraries.Game.InputMonitor.F1;
            case GLFW.GLFW_KEY_F2:
                return plugins.quorum.Libraries.Game.InputMonitor.F2;
            case GLFW.GLFW_KEY_F3:
                return plugins.quorum.Libraries.Game.InputMonitor.F3;
            case GLFW.GLFW_KEY_F4:
                return plugins.quorum.Libraries.Game.InputMonitor.F4;
            case GLFW.GLFW_KEY_F5:
                return plugins.quorum.Libraries.Game.InputMonitor.F5;
            case GLFW.GLFW_KEY_F6:
                return plugins.quorum.Libraries.Game.InputMonitor.F6;
            case GLFW.GLFW_KEY_F7:
                return plugins.quorum.Libraries.Game.InputMonitor.F7;
            case GLFW.GLFW_KEY_F8:
                return plugins.quorum.Libraries.Game.InputMonitor.F8;
            case GLFW.GLFW_KEY_F9:
                return plugins.quorum.Libraries.Game.InputMonitor.F9;
            case GLFW.GLFW_KEY_F10:
                return plugins.quorum.Libraries.Game.InputMonitor.F10;
            case GLFW.GLFW_KEY_F11:
                return plugins.quorum.Libraries.Game.InputMonitor.F11;
            case GLFW.GLFW_KEY_F12:
                return plugins.quorum.Libraries.Game.InputMonitor.F12;
            case GLFW.GLFW_KEY_KP_0:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_0;
            case GLFW.GLFW_KEY_KP_1:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_1;
            case GLFW.GLFW_KEY_KP_2:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_2;
            case GLFW.GLFW_KEY_KP_3:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_3;
            case GLFW.GLFW_KEY_KP_4:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_4;
            case GLFW.GLFW_KEY_KP_5:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_5;
            case GLFW.GLFW_KEY_KP_6:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_6;
            case GLFW.GLFW_KEY_KP_7:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_7;
            case GLFW.GLFW_KEY_KP_8:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_8;
            case GLFW.GLFW_KEY_KP_9:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_9;
            case GLFW.GLFW_KEY_KP_ADD:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_PLUS;
            case GLFW.GLFW_KEY_KP_DECIMAL:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_DECIMAL;
            case GLFW.GLFW_KEY_KP_DIVIDE:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_SLASH;
            case GLFW.GLFW_KEY_KP_MULTIPLY:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_STAR;
            case GLFW.GLFW_KEY_KP_ENTER:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_ENTER;
            case GLFW.GLFW_KEY_KP_EQUAL:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_EQUALS;
            case GLFW.GLFW_KEY_KP_SUBTRACT:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_MINUS;
            default:
                return plugins.quorum.Libraries.Game.InputMonitor.UNKNOWN;
        }
    }
    
    /*
    Converts the given GLFW keyboard event information into a Quorum
    KeyboardEvent and adds it to the processor queue.
    */
    public static void AddKeyboardEvent(long window, int key, int code, int action, int modifiers)
    {
        quorum.Libraries.Interface.Events.KeyboardEvent event = new quorum.Libraries.Interface.Events.KeyboardEvent();
        event.keyCode = GetGameKeyCode(key);
        
        switch (action)
        {
            case GLFW.GLFW_PRESS:
                event.eventType = event.PRESSED_KEY;
                break;
            case GLFW.GLFW_RELEASE:
                event.eventType = event.RELEASED_KEY;
                break;
            default:
                return;
        }

        keyboardEvents.add(event);
    }

    /*
    Returns true if there are keyboard events which are waiting to be processed
    by the Quorum game engine, and false otherwise.
    */
    public static boolean EventsArePending()
    {
        return keyboardEvents.isEmpty() == false;
    }
}
