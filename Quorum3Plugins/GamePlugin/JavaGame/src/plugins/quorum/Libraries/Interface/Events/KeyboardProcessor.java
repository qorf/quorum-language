/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Interface.Events;

import org.lwjgl.input.Keyboard;

import plugins.quorum.Libraries.Game.InputMonitor;

/**
 *
 * @author alleew
 */
public class KeyboardProcessor {

    public java.lang.Object me_ = null;

    public void Update() 
    {
        quorum.Libraries.Interface.Events.KeyboardProcessor quorumProcessor = (quorum.Libraries.Interface.Events.KeyboardProcessor) me_;

        quorum.Libraries.Containers.List_ events = quorumProcessor.events;

        if (Keyboard.isCreated()) 
        {
            while (Keyboard.next()) 
            {
                int keyCode = GetGameKeyCode(Keyboard.getEventKey());

                quorum.Libraries.Interface.Events.KeyboardEvent event = new quorum.Libraries.Interface.Events.KeyboardEvent();
                event.keyCode = keyCode;

                // Check if the key was pressed down.
                if (Keyboard.getEventKeyState())
                    event.eventType = event.PRESSED_KEY;
                // Otherwise, a key has just been released.
                else
                    event.eventType = event.RELEASED_KEY;

                events.Add(event);
            }
            
        }
    }

    public static int GetGameKeyCode(int lwjglKeyCode) 
    {
        switch (lwjglKeyCode) 
        {
            case Keyboard.KEY_LBRACKET:
                return plugins.quorum.Libraries.Game.InputMonitor.LEFT_BRACKET;
            case Keyboard.KEY_RBRACKET:
                return plugins.quorum.Libraries.Game.InputMonitor.RIGHT_BRACKET;
            case Keyboard.KEY_GRAVE:
                return plugins.quorum.Libraries.Game.InputMonitor.GRAVE;
            case Keyboard.KEY_MULTIPLY:
                return plugins.quorum.Libraries.Game.InputMonitor.STAR;
            case Keyboard.KEY_NUMLOCK:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM;
            case Keyboard.KEY_DECIMAL:
                return plugins.quorum.Libraries.Game.InputMonitor.PERIOD;
            case Keyboard.KEY_DIVIDE:
                return plugins.quorum.Libraries.Game.InputMonitor.SLASH;
            case Keyboard.KEY_LMETA:
                return plugins.quorum.Libraries.Game.InputMonitor.SYM;
            case Keyboard.KEY_RMETA:
                return plugins.quorum.Libraries.Game.InputMonitor.SYM;
            case Keyboard.KEY_NUMPADEQUALS:
                return plugins.quorum.Libraries.Game.InputMonitor.EQUALS;
            case Keyboard.KEY_AT:
                return plugins.quorum.Libraries.Game.InputMonitor.AT;
            case Keyboard.KEY_EQUALS:
                return plugins.quorum.Libraries.Game.InputMonitor.EQUALS;
            case Keyboard.KEY_NUMPADCOMMA:
                return plugins.quorum.Libraries.Game.InputMonitor.COMMA;
            case Keyboard.KEY_NUMPADENTER:
                return plugins.quorum.Libraries.Game.InputMonitor.ENTER;
            case Keyboard.KEY_0:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_0;
            case Keyboard.KEY_1:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_1;
            case Keyboard.KEY_2:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_2;
            case Keyboard.KEY_3:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_3;
            case Keyboard.KEY_4:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_4;
            case Keyboard.KEY_5:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_5;
            case Keyboard.KEY_6:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_6;
            case Keyboard.KEY_7:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_7;
            case Keyboard.KEY_8:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_8;
            case Keyboard.KEY_9:
                return plugins.quorum.Libraries.Game.InputMonitor.NUM_9;
            case Keyboard.KEY_A:
                return plugins.quorum.Libraries.Game.InputMonitor.A;
            case Keyboard.KEY_B:
                return plugins.quorum.Libraries.Game.InputMonitor.B;
            case Keyboard.KEY_C:
                return plugins.quorum.Libraries.Game.InputMonitor.C;
            case Keyboard.KEY_D:
                return plugins.quorum.Libraries.Game.InputMonitor.D;
            case Keyboard.KEY_E:
                return plugins.quorum.Libraries.Game.InputMonitor.E;
            case Keyboard.KEY_F:
                return plugins.quorum.Libraries.Game.InputMonitor.F;
            case Keyboard.KEY_G:
                return plugins.quorum.Libraries.Game.InputMonitor.G;
            case Keyboard.KEY_H:
                return plugins.quorum.Libraries.Game.InputMonitor.H;
            case Keyboard.KEY_I:
                return plugins.quorum.Libraries.Game.InputMonitor.I;
            case Keyboard.KEY_J:
                return plugins.quorum.Libraries.Game.InputMonitor.J;
            case Keyboard.KEY_K:
                return plugins.quorum.Libraries.Game.InputMonitor.K;
            case Keyboard.KEY_L:
                return plugins.quorum.Libraries.Game.InputMonitor.L;
            case Keyboard.KEY_M:
                return plugins.quorum.Libraries.Game.InputMonitor.M;
            case Keyboard.KEY_N:
                return plugins.quorum.Libraries.Game.InputMonitor.N;
            case Keyboard.KEY_O:
                return plugins.quorum.Libraries.Game.InputMonitor.O;
            case Keyboard.KEY_P:
                return plugins.quorum.Libraries.Game.InputMonitor.P;
            case Keyboard.KEY_Q:
                return plugins.quorum.Libraries.Game.InputMonitor.Q;
            case Keyboard.KEY_R:
                return plugins.quorum.Libraries.Game.InputMonitor.R;
            case Keyboard.KEY_S:
                return plugins.quorum.Libraries.Game.InputMonitor.S;
            case Keyboard.KEY_T:
                return plugins.quorum.Libraries.Game.InputMonitor.T;
            case Keyboard.KEY_U:
                return plugins.quorum.Libraries.Game.InputMonitor.U;
            case Keyboard.KEY_V:
                return plugins.quorum.Libraries.Game.InputMonitor.V;
            case Keyboard.KEY_W:
                return plugins.quorum.Libraries.Game.InputMonitor.W;
            case Keyboard.KEY_X:
                return plugins.quorum.Libraries.Game.InputMonitor.X;
            case Keyboard.KEY_Y:
                return plugins.quorum.Libraries.Game.InputMonitor.Y;
            case Keyboard.KEY_Z:
                return plugins.quorum.Libraries.Game.InputMonitor.Z;
            case Keyboard.KEY_LMENU:
                return plugins.quorum.Libraries.Game.InputMonitor.ALT_LEFT;
            case Keyboard.KEY_RMENU:
                return plugins.quorum.Libraries.Game.InputMonitor.ALT_RIGHT;
            case Keyboard.KEY_BACKSLASH:
                return plugins.quorum.Libraries.Game.InputMonitor.BACKSLASH;
            case Keyboard.KEY_COMMA:
                return plugins.quorum.Libraries.Game.InputMonitor.COMMA;
            case Keyboard.KEY_DELETE:
                return plugins.quorum.Libraries.Game.InputMonitor.FORWARD_DEL;
            case Keyboard.KEY_LEFT:
                return plugins.quorum.Libraries.Game.InputMonitor.DPAD_LEFT;
            case Keyboard.KEY_RIGHT:
                return plugins.quorum.Libraries.Game.InputMonitor.DPAD_RIGHT;
            case Keyboard.KEY_UP:
                return plugins.quorum.Libraries.Game.InputMonitor.DPAD_UP;
            case Keyboard.KEY_DOWN:
                return plugins.quorum.Libraries.Game.InputMonitor.DPAD_DOWN;
            case Keyboard.KEY_RETURN:
                return plugins.quorum.Libraries.Game.InputMonitor.ENTER;
            case Keyboard.KEY_HOME:
                return plugins.quorum.Libraries.Game.InputMonitor.HOME;
            case Keyboard.KEY_MINUS:
                return plugins.quorum.Libraries.Game.InputMonitor.MINUS;
            case Keyboard.KEY_PERIOD:
                return plugins.quorum.Libraries.Game.InputMonitor.PERIOD;
            case Keyboard.KEY_ADD:
                return plugins.quorum.Libraries.Game.InputMonitor.PLUS;
            case Keyboard.KEY_SEMICOLON:
                return plugins.quorum.Libraries.Game.InputMonitor.SEMICOLON;
            case Keyboard.KEY_LSHIFT:
                return plugins.quorum.Libraries.Game.InputMonitor.SHIFT_LEFT;
            case Keyboard.KEY_RSHIFT:
                return plugins.quorum.Libraries.Game.InputMonitor.SHIFT_RIGHT;
            case Keyboard.KEY_SLASH:
                return plugins.quorum.Libraries.Game.InputMonitor.SLASH;
            case Keyboard.KEY_SPACE:
                return plugins.quorum.Libraries.Game.InputMonitor.SPACE;
            case Keyboard.KEY_TAB:
                return plugins.quorum.Libraries.Game.InputMonitor.TAB;
            case Keyboard.KEY_LCONTROL:
                return plugins.quorum.Libraries.Game.InputMonitor.CONTROL_LEFT;
            case Keyboard.KEY_RCONTROL:
                return plugins.quorum.Libraries.Game.InputMonitor.CONTROL_RIGHT;
            case Keyboard.KEY_NEXT:
                return plugins.quorum.Libraries.Game.InputMonitor.PAGE_DOWN;
            case Keyboard.KEY_PRIOR:
                return plugins.quorum.Libraries.Game.InputMonitor.PAGE_UP;
            case Keyboard.KEY_ESCAPE:
                return plugins.quorum.Libraries.Game.InputMonitor.ESCAPE;
            case Keyboard.KEY_END:
                return plugins.quorum.Libraries.Game.InputMonitor.END;
            case Keyboard.KEY_INSERT:
                return plugins.quorum.Libraries.Game.InputMonitor.INSERT;
            case Keyboard.KEY_BACK:
                return plugins.quorum.Libraries.Game.InputMonitor.DEL;
            case Keyboard.KEY_SUBTRACT:
                return plugins.quorum.Libraries.Game.InputMonitor.MINUS;
            case Keyboard.KEY_APOSTROPHE:
                return plugins.quorum.Libraries.Game.InputMonitor.APOSTROPHE;
            case Keyboard.KEY_F1:
                return plugins.quorum.Libraries.Game.InputMonitor.F1;
            case Keyboard.KEY_F2:
                return plugins.quorum.Libraries.Game.InputMonitor.F2;
            case Keyboard.KEY_F3:
                return plugins.quorum.Libraries.Game.InputMonitor.F3;
            case Keyboard.KEY_F4:
                return plugins.quorum.Libraries.Game.InputMonitor.F4;
            case Keyboard.KEY_F5:
                return plugins.quorum.Libraries.Game.InputMonitor.F5;
            case Keyboard.KEY_F6:
                return plugins.quorum.Libraries.Game.InputMonitor.F6;
            case Keyboard.KEY_F7:
                return plugins.quorum.Libraries.Game.InputMonitor.F7;
            case Keyboard.KEY_F8:
                return plugins.quorum.Libraries.Game.InputMonitor.F8;
            case Keyboard.KEY_F9:
                return plugins.quorum.Libraries.Game.InputMonitor.F9;
            case Keyboard.KEY_F10:
                return plugins.quorum.Libraries.Game.InputMonitor.F10;
            case Keyboard.KEY_F11:
                return plugins.quorum.Libraries.Game.InputMonitor.F11;
            case Keyboard.KEY_F12:
                return plugins.quorum.Libraries.Game.InputMonitor.F12;
            case Keyboard.KEY_COLON:
                return plugins.quorum.Libraries.Game.InputMonitor.COLON;
            case Keyboard.KEY_NUMPAD0:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_0;
            case Keyboard.KEY_NUMPAD1:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_1;
            case Keyboard.KEY_NUMPAD2:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_2;
            case Keyboard.KEY_NUMPAD3:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_3;
            case Keyboard.KEY_NUMPAD4:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_4;
            case Keyboard.KEY_NUMPAD5:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_5;
            case Keyboard.KEY_NUMPAD6:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_6;
            case Keyboard.KEY_NUMPAD7:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_7;
            case Keyboard.KEY_NUMPAD8:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_8;
            case Keyboard.KEY_NUMPAD9:
                return plugins.quorum.Libraries.Game.InputMonitor.NUMPAD_9;
            default:
                return plugins.quorum.Libraries.Game.InputMonitor.UNKNOWN;
        }
    }

}
