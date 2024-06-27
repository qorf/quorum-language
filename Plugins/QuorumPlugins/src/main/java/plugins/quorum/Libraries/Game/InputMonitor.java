/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;

import org.lwjgl.glfw.GLFW;
import plugins.quorum.Libraries.Interface.Events.MouseProcessor;
//import org.lwjgl.input.Mouse;

/**
 * This class primarily manages taking care of receiving input.
 * 
 * 
 * @author Taylor Bockman, William Allee
 */
public class InputMonitor {
    public java.lang.Object me_ = null;
    public static int pressedKeys = 0;
  
    public final static int ANY_KEY = 1;
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
        if(key == ANY_KEY)
        {
            return pressedKeys > 0;
        }
        else
        {
            return GLFW.glfwGetKey(DesktopDisplay.window, QuorumToGLFWKeyCode(key)) == GLFW.GLFW_PRESS;
        }
    }
  
  
    public static int QuorumToGLFWKeyCode(int code)
    {
        switch(code)
        {
            case UP:
                return GLFW.GLFW_KEY_UP;
            case DOWN:
                return GLFW.GLFW_KEY_DOWN;
            case LEFT:
                return GLFW.GLFW_KEY_LEFT;
            case RIGHT:
                return GLFW.GLFW_KEY_RIGHT;
            case CLEAR:
                // No corresponding GLFW constant. This may be removed later.
                return GLFW.GLFW_KEY_UNKNOWN;
            case APOSTROPHE:
                return GLFW.GLFW_KEY_APOSTROPHE;
            case LEFT_BRACKET:
                return GLFW.GLFW_KEY_LEFT_BRACKET;
            case RIGHT_BRACKET:
                return GLFW.GLFW_KEY_RIGHT_BRACKET;
            case GRAVE:
                return GLFW.GLFW_KEY_GRAVE_ACCENT;
            case NUM_LOCK:
                return GLFW.GLFW_KEY_NUM_LOCK;
            case CAPS_LOCK:
                return GLFW.GLFW_KEY_CAPS_LOCK;
            case SCROLL_LOCK:
                return GLFW.GLFW_KEY_SCROLL_LOCK;
            case EQUALS:
                return GLFW.GLFW_KEY_EQUAL;
            case META_LEFT:
                return GLFW.GLFW_KEY_LEFT_SUPER;
            case META_RIGHT:
                return GLFW.GLFW_KEY_RIGHT_SUPER;
            case NUM_0:
                return GLFW.GLFW_KEY_0;
            case NUM_1:
                return GLFW.GLFW_KEY_1;
            case NUM_2:
                return GLFW.GLFW_KEY_2;
            case NUM_3:
                return GLFW.GLFW_KEY_3;
            case NUM_4:
                return GLFW.GLFW_KEY_4;
            case NUM_5:
                return GLFW.GLFW_KEY_5;
            case NUM_6:
                return GLFW.GLFW_KEY_6;
            case NUM_7:
                return GLFW.GLFW_KEY_7;
            case NUM_8:
                return GLFW.GLFW_KEY_8;
            case NUM_9:
                return GLFW.GLFW_KEY_9;
            case A:
                return GLFW.GLFW_KEY_A;
            case B:
                return GLFW.GLFW_KEY_B;
            case C:
                return GLFW.GLFW_KEY_C;
            case D:
                return GLFW.GLFW_KEY_D;
            case E:
                return GLFW.GLFW_KEY_E;
            case F:
                return GLFW.GLFW_KEY_F;
            case G:
                return GLFW.GLFW_KEY_G;
            case H:
                return GLFW.GLFW_KEY_H;
            case I:
                return GLFW.GLFW_KEY_I;
            case J:
                return GLFW.GLFW_KEY_J;
            case K:
                return GLFW.GLFW_KEY_K;
            case L:
                return GLFW.GLFW_KEY_L;
            case M:
                return GLFW.GLFW_KEY_M;
            case N:
                return GLFW.GLFW_KEY_N;
            case O:
                return GLFW.GLFW_KEY_O;
            case P:
                return GLFW.GLFW_KEY_P;
            case Q:
                return GLFW.GLFW_KEY_Q;
            case R:
                return GLFW.GLFW_KEY_R;
            case S:
                return GLFW.GLFW_KEY_S;
            case T:
                return GLFW.GLFW_KEY_T;
            case U:
                return GLFW.GLFW_KEY_U;
            case V:
                return GLFW.GLFW_KEY_V;
            case W:
                return GLFW.GLFW_KEY_W;
            case X:
                return GLFW.GLFW_KEY_X;
            case Y:
                return GLFW.GLFW_KEY_Y;
            case Z:
                return GLFW.GLFW_KEY_Z;
            case ALT_LEFT:
                return GLFW.GLFW_KEY_LEFT_ALT;
            case ALT_RIGHT:
                return GLFW.GLFW_KEY_RIGHT_ALT;
            case BACKSLASH:
                return GLFW.GLFW_KEY_BACKSLASH;
            case COMMA:
                return GLFW.GLFW_KEY_COMMA;
            case FORWARD_DELETE:
                return GLFW.GLFW_KEY_DELETE;
            case ENTER:
                return GLFW.GLFW_KEY_ENTER;
            case HOME:
                return GLFW.GLFW_KEY_HOME;
            case END:
                return GLFW.GLFW_KEY_END;
            case PAGE_DOWN:
                return GLFW.GLFW_KEY_PAGE_DOWN;
            case PAGE_UP:
                return GLFW.GLFW_KEY_PAGE_UP;
            case INSERT:
                return GLFW.GLFW_KEY_INSERT;
            case MINUS:
                return GLFW.GLFW_KEY_MINUS;
            case POWER:
                // No corresponding GLFW constant. This may be removed later.
                return GLFW.GLFW_KEY_UNKNOWN;
            case PERIOD:
                return GLFW.GLFW_KEY_PERIOD;
            case NUMPAD_PLUS:
                return GLFW.GLFW_KEY_KP_ADD;
            case NUMPAD_MINUS:
                return GLFW.GLFW_KEY_KP_SUBTRACT;
            case NUMPAD_ENTER:
                return GLFW.GLFW_KEY_KP_ENTER;
            case NUMPAD_SLASH:
                return GLFW.GLFW_KEY_KP_DIVIDE;
            case NUMPAD_STAR:
                return GLFW.GLFW_KEY_KP_MULTIPLY;
            case NUMPAD_DECIMAL:
                return GLFW.GLFW_KEY_KP_DECIMAL;
            case NUMPAD_EQUALS:
                return GLFW.GLFW_KEY_KP_EQUAL;
            case SEMICOLON:
                return GLFW.GLFW_KEY_SEMICOLON;
            case SHIFT_LEFT:
                return GLFW.GLFW_KEY_LEFT_SHIFT;
            case SHIFT_RIGHT:
                return GLFW.GLFW_KEY_RIGHT_SHIFT;
            case SLASH:
                return GLFW.GLFW_KEY_SLASH;
            case SPACE:
                return GLFW.GLFW_KEY_SPACE;
            case TAB:
                return GLFW.GLFW_KEY_TAB;
            case BACKSPACE:
                return GLFW.GLFW_KEY_BACKSPACE;
            case CONTROL_LEFT:
                return GLFW.GLFW_KEY_LEFT_CONTROL;
            case CONTROL_RIGHT:
                return GLFW.GLFW_KEY_RIGHT_CONTROL;
            case ESCAPE:
                return GLFW.GLFW_KEY_ESCAPE;
            case F1:
                return GLFW.GLFW_KEY_F1;
            case F2:
                return GLFW.GLFW_KEY_F2;
            case F3:
                return GLFW.GLFW_KEY_F3;
            case F4:
                return GLFW.GLFW_KEY_F4;
            case F5:
                return GLFW.GLFW_KEY_F5;
            case F6:
                return GLFW.GLFW_KEY_F6;
            case F7:
                return GLFW.GLFW_KEY_F7;
            case F8:
                return GLFW.GLFW_KEY_F8;
            case F9:
                return GLFW.GLFW_KEY_F9;
            case F10:
                return GLFW.GLFW_KEY_F10;
            case F11:
                return GLFW.GLFW_KEY_F11;
            case F12:
                return GLFW.GLFW_KEY_F12;
            case COLON:
                // No corresponding GLFW constant. This may be removed later.
                return GLFW.GLFW_KEY_UNKNOWN;
            case NUMPAD_0:
                return GLFW.GLFW_KEY_KP_0;
            case NUMPAD_1:
                return GLFW.GLFW_KEY_KP_1;
            case NUMPAD_2:
                return GLFW.GLFW_KEY_KP_2;
            case NUMPAD_3:
                return GLFW.GLFW_KEY_KP_3;
            case NUMPAD_4:
                return GLFW.GLFW_KEY_KP_4;
            case NUMPAD_5:
                return GLFW.GLFW_KEY_KP_5;
            case NUMPAD_6:
                return GLFW.GLFW_KEY_KP_6;
            case NUMPAD_7:
                return GLFW.GLFW_KEY_KP_7;
            case NUMPAD_8:
                return GLFW.GLFW_KEY_KP_8;
            case NUMPAD_9:
                return GLFW.GLFW_KEY_KP_9;
            case PAUSE:
                return GLFW.GLFW_KEY_PAUSE;
            case PRINT_SCREEN:
                return GLFW.GLFW_KEY_PRINT_SCREEN;
            default:
                return GLFW.GLFW_KEY_UNKNOWN;
        }
    }
  
    public int GetMouseX () 
    {
        double[] x = new double[1], y = new double[1];
        GLFW.glfwGetCursorPos(DesktopDisplay.window, x, y);
        return (int)(x[0] * GameStateManager.display.GetPixelScaleFactor());
    }

    public int GetMouseY () 
    {
	    double[] x = new double[1], y = new double[1];
        GLFW.glfwGetCursorPos(DesktopDisplay.window, x, y);
        // Subtract from the display height, because GLFW reports y-values inverted from our convention.
        // GLFW assumes y=0 is the top, but we treat y=0 as the bottom.
        return (int)(GameStateManager.display.GetHeight() - y[0] * GameStateManager.display.GetPixelScaleFactor());
    }
  
    public boolean IsMouseButtonPressed(int button)
    {
        return (MouseProcessor.pressedButtons & button) != 0;
    }
    
    public boolean IsClicked()
    {
        return MouseProcessor.pressedButtons != 0;
    }
    
    public int IsScrolled()
    {
        int scroll = (int)DesktopDisplay.scroll;
        DesktopDisplay.scroll = 0;
        return scroll;
    }
    
    public int NumberOfKeysPressed()
    {
        return pressedKeys;
    }
    
}
