/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * This class primarily manages taking care of receiving input.
 * 
 * 
 * @author Taylor Bockman, William Allee
 */
public class InputMonitor {
    public java.lang.Object me_ = null;
    boolean keyJustPressed = false;
    boolean[] justPressedKeys = new boolean[256];
    int pressedKeys = 0;
  
    public final static int ANY_KEY = -1;
    public final static int NUM_0 = 7;
    public final static int NUM_1 = 8;
    public final static int NUM_2 = 9;
    public final static int NUM_3 = 10;
    public final static int NUM_4 = 11;
    public final static int NUM_5 = 12;
    public final static int NUM_6 = 13;
    public final static int NUM_7 = 14;
    public final static int NUM_8 = 15;
    public final static int NUM_9 = 16;
    public final static int A = 29;
    public final static int ALT_LEFT = 57;
    public final static int ALT_RIGHT = 58;
    public final static int APOSTROPHE = 75;
    public final static int B = 30;
    public final static int BACKSLASH = 73;
    public final static int C = 31;
    public final static int CAPS_LOCK = 77;
    public final static int CLEAR = 28;
    public final static int COMMA = 55;
    public final static int D = 32;
    public final static int BACKSPACE = 67;
    public final static int FORWARD_DELETE = 112;
    public final static int DOWN = 20;
    public final static int LEFT = 21;
    public final static int RIGHT = 22;
    public final static int UP = 19;
    public final static int E = 33;
    public final static int ENTER = 66;
    public final static int EQUALS = 70;
    public final static int F = 34;
    public final static int G = 35;
    public final static int GRAVE = 68;
    public final static int H = 36;
    public final static int HOME = 3;
    public final static int I = 37;
    public final static int J = 38;
    public final static int K = 39;
    public final static int L = 40;
    public final static int LEFT_BRACKET = 71;
    public final static int M = 41;
    public final static int META_LEFT = 63;
    public final static int META_RIGHT = 64;
    public final static int MINUS = 69;
    public final static int N = 42;
    public final static int NUM_LOCK = 78;
    public final static int O = 43;
    public final static int P = 44;
    public final static int PAUSE = 197;
    public final static int PERIOD = 56;
    public final static int POWER = 26;
    public final static int PRINT_SCREEN = 183;
    public final static int Q = 45;
    public final static int R = 46;
    public final static int RIGHT_BRACKET = 72;
    public final static int S = 47;
    public final static int SCROLL_LOCK = 80;
    public final static int SEMICOLON = 74;
    public final static int SHIFT_LEFT = 59;
    public final static int SHIFT_RIGHT = 60;
    public final static int SLASH = 76;
    public final static int SPACE = 62;
    public final static int T = 48;
    public final static int TAB = 61;
    public final static int U = 49;
    public final static int UNKNOWN = 0;
    public final static int V = 50;
    public final static int W = 51;
    public final static int X = 52;
    public final static int Y = 53;
    public final static int Z = 54;
    public final static int CONTROL_LEFT = 129;
    public final static int CONTROL_RIGHT = 130;
    public final static int ESCAPE = 131;
    public final static int END = 132;
    public final static int INSERT = 133;
    public final static int PAGE_UP = 92;
    public final static int PAGE_DOWN = 93;
    public final static int NUMPAD_0 = 144;
    public final static int NUMPAD_1 = 145;
    public final static int NUMPAD_2 = 146;
    public final static int NUMPAD_3 = 147;
    public final static int NUMPAD_4 = 148;
    public final static int NUMPAD_5 = 149;
    public final static int NUMPAD_6 = 150;
    public final static int NUMPAD_7 = 151;
    public final static int NUMPAD_8 = 152;
    public final static int NUMPAD_9 = 153;
    public final static int NUMPAD_DECIMAL = 83;
    public final static int NUMPAD_ENTER = 156;
    public final static int NUMPAD_MINUS = 82;
    public final static int NUMPAD_PLUS = 81;
    public final static int NUMPAD_SLASH = 181;
    public final static int NUMPAD_STAR = 17;
    public final static int NUMPAD_EQUALS = 84;
    public final static int COLON = 243;
    public final static int F1 = 244;
    public final static int F2 = 245;
    public final static int F3 = 246;
    public final static int F4 = 247;
    public final static int F5 = 248;
    public final static int F6 = 249;
    public final static int F7 = 250;
    public final static int F8 = 251;
    public final static int F9 = 252;
    public final static int F10 = 253;
    public final static int F11 = 254;
    public final static int F12 = 255;
  
    public boolean IsKeyPressed(int key)
    {
        if(!Keyboard.isCreated()) 
            return false;

        if(key == ANY_KEY)
        {
            return pressedKeys > 0;
        }
        else
        {
            return Keyboard.isKeyDown(gdxToLwjglKeyCode(key));
        }
    }
  
  
    public static int gdxToLwjglKeyCode(int code)
    {
        switch(code)
        {
            case APOSTROPHE:
                return Keyboard.KEY_APOSTROPHE;
            case LEFT_BRACKET:
                return Keyboard.KEY_LBRACKET;
            case RIGHT_BRACKET:
                return Keyboard.KEY_RBRACKET;
            case GRAVE:
                return Keyboard.KEY_GRAVE;
            case NUM_LOCK:
                return Keyboard.KEY_NUMLOCK;
            case CAPS_LOCK:
                return Keyboard.KEY_CAPITAL;
            case SCROLL_LOCK:
                return Keyboard.KEY_SCROLL;
            case EQUALS:
                return Keyboard.KEY_EQUALS;
            case META_LEFT:
                return Keyboard.KEY_LMETA;
            case META_RIGHT:
                return Keyboard.KEY_RMETA;
            case NUM_0:
                return Keyboard.KEY_0;
            case NUM_1:
                return Keyboard.KEY_1;
            case NUM_2:
                return Keyboard.KEY_2;
            case NUM_3:
                return Keyboard.KEY_3;
            case NUM_4:
                return Keyboard.KEY_4;
            case NUM_5:
                return Keyboard.KEY_5;
            case NUM_6:
                return Keyboard.KEY_6;
            case NUM_7:
                return Keyboard.KEY_7;
            case NUM_8:
                return Keyboard.KEY_8;
            case NUM_9:
                return Keyboard.KEY_9;
            case A:
                return Keyboard.KEY_A;
            case B:
                return Keyboard.KEY_B;
            case C:
                return Keyboard.KEY_C;
            case D:
                return Keyboard.KEY_D;
            case E:
                return Keyboard.KEY_E;
            case F:
                return Keyboard.KEY_F;
            case G:
                return Keyboard.KEY_G;
            case H:
                return Keyboard.KEY_H;
            case I:
                return Keyboard.KEY_I;
            case J:
                return Keyboard.KEY_J;
            case K:
                return Keyboard.KEY_K;
            case L:
                return Keyboard.KEY_L;
            case M:
                return Keyboard.KEY_M;
            case N:
                return Keyboard.KEY_N;
            case O:
                return Keyboard.KEY_O;
            case P:
                return Keyboard.KEY_P;
            case Q:
                return Keyboard.KEY_Q;
            case R:
                return Keyboard.KEY_R;
            case S:
                return Keyboard.KEY_S;
            case T:
                return Keyboard.KEY_T;
            case U:
                return Keyboard.KEY_U;
            case V:
                return Keyboard.KEY_V;
            case W:
                return Keyboard.KEY_W;
            case X:
                return Keyboard.KEY_X;
            case Y:
                return Keyboard.KEY_Y;
            case Z:
                return Keyboard.KEY_Z;
            case ALT_LEFT:
                return Keyboard.KEY_LMENU;
            case ALT_RIGHT:
                return Keyboard.KEY_RMENU;
            case BACKSLASH:
                return Keyboard.KEY_BACKSLASH;
            case COMMA:
                return Keyboard.KEY_COMMA;
            case FORWARD_DELETE:
                return Keyboard.KEY_DELETE;
            case ENTER:
                return Keyboard.KEY_RETURN;
            case HOME:
                return Keyboard.KEY_HOME;
            case END:
                return Keyboard.KEY_END;
            case PAGE_DOWN:
                return Keyboard.KEY_NEXT;
            case PAGE_UP:
                return Keyboard.KEY_PRIOR;
            case INSERT:
                return Keyboard.KEY_INSERT;
            case MINUS:
                return Keyboard.KEY_MINUS;
            case POWER:
                return Keyboard.KEY_POWER;
            case PERIOD:
                return Keyboard.KEY_PERIOD;
            case NUMPAD_PLUS:
                return Keyboard.KEY_ADD;
            case NUMPAD_MINUS:
                return Keyboard.KEY_SUBTRACT;
            case NUMPAD_ENTER:
                return Keyboard.KEY_NUMPADENTER;
            case NUMPAD_SLASH:
                return Keyboard.KEY_DIVIDE;
            case NUMPAD_STAR:
                return Keyboard.KEY_MULTIPLY;
            case NUMPAD_DECIMAL:
                return Keyboard.KEY_DECIMAL;
            case NUMPAD_EQUALS:
                return Keyboard.KEY_NUMPADEQUALS;
            case SEMICOLON:
                return Keyboard.KEY_SEMICOLON;
            case SHIFT_LEFT:
                return Keyboard.KEY_LSHIFT;
            case SHIFT_RIGHT:
                return Keyboard.KEY_RSHIFT;
            case SLASH:
                return Keyboard.KEY_SLASH;
            case SPACE:
                return Keyboard.KEY_SPACE;
            case TAB:
                return Keyboard.KEY_TAB;
            case BACKSPACE:
                return Keyboard.KEY_BACK;
            case CONTROL_LEFT:
                return Keyboard.KEY_LCONTROL;
            case CONTROL_RIGHT:
                return Keyboard.KEY_RCONTROL;
            case ESCAPE:
                return Keyboard.KEY_ESCAPE;
            case F1:
                return Keyboard.KEY_F1;
            case F2:
                return Keyboard.KEY_F2;
            case F3:
                return Keyboard.KEY_F3;
            case F4:
                return Keyboard.KEY_F4;
            case F5:
                return Keyboard.KEY_F5;
            case F6:
                return Keyboard.KEY_F6;
            case F7:
                return Keyboard.KEY_F7;
            case F8:
                return Keyboard.KEY_F8;
            case F9:
                return Keyboard.KEY_F9;
            case F10:
                return Keyboard.KEY_F10;
            case F11:
                return Keyboard.KEY_F11;
            case F12:
                return Keyboard.KEY_F12;
            case COLON:
                return Keyboard.KEY_COLON;
            case NUMPAD_0:
                return Keyboard.KEY_NUMPAD0;
            case NUMPAD_1:
                return Keyboard.KEY_NUMPAD1;
            case NUMPAD_2:
                return Keyboard.KEY_NUMPAD2;
            case NUMPAD_3:
                return Keyboard.KEY_NUMPAD3;
            case NUMPAD_4:
                return Keyboard.KEY_NUMPAD4;
            case NUMPAD_5:
                return Keyboard.KEY_NUMPAD5;
            case NUMPAD_6:
                return Keyboard.KEY_NUMPAD6;
            case NUMPAD_7:
                return Keyboard.KEY_NUMPAD7;
            case NUMPAD_8:
                return Keyboard.KEY_NUMPAD8;
            case NUMPAD_9:
                return Keyboard.KEY_NUMPAD9;
            case PAUSE:
                return Keyboard.KEY_PAUSE;
            case PRINT_SCREEN:
                return Keyboard.KEY_SYSRQ;
            default:
                return Keyboard.KEY_NONE;
        }
    }
  
    public int GetMouseX () 
    {
	return (int)(Mouse.getX() * GameStateManager.display.GetPixelScaleFactor());
    }

    public int GetMouseY () 
    {
	return (int)(Mouse.getY() * GameStateManager.display.GetPixelScaleFactor());
    }
  
    public boolean IsMouseButtonPressed(int button)
    {
        return Mouse.isButtonDown(button);
    }
    
    public boolean IsClicked()
    {
        return Mouse.isButtonDown(0) || Mouse.isButtonDown(1) || Mouse.isButtonDown(2);
    }
    
    public int IsScrolled()
    {
        return Mouse.getDWheel();
    }
    
}
