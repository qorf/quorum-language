/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import plugins.quorum.Libraries.Game.GameState;

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
  
                public static final int ANY_KEY = -1;
		public static final int NUM_0 = 7;
		public static final int NUM_1 = 8;
		public static final int NUM_2 = 9;
		public static final int NUM_3 = 10;
		public static final int NUM_4 = 11;
		public static final int NUM_5 = 12;
		public static final int NUM_6 = 13;
		public static final int NUM_7 = 14;
		public static final int NUM_8 = 15;
		public static final int NUM_9 = 16;
		public static final int A = 29;
		public static final int ALT_LEFT = 57;
		public static final int ALT_RIGHT = 58;
		public static final int APOSTROPHE = 75;
		public static final int AT = 77;
		public static final int B = 30;
		public static final int BACK = 4;
		public static final int BACKSLASH = 73;
		public static final int C = 31;
		public static final int CALL = 5;
		public static final int CAMERA = 27;
		public static final int CLEAR = 28;
		public static final int COMMA = 55;
		public static final int D = 32;
		public static final int DEL = 67;
		public static final int BACKSPACE = 67;
		public static final int FORWARD_DEL = 112;
		public static final int DPAD_CENTER = 23;
		public static final int DPAD_DOWN = 20;
		public static final int DPAD_LEFT = 21;
		public static final int DPAD_RIGHT = 22;
		public static final int DPAD_UP = 19;
		public static final int CENTER = 23;
		public static final int DOWN = 20;
		public static final int LEFT = 21;
		public static final int RIGHT = 22;
		public static final int UP = 19;
		public static final int E = 33;
		public static final int ENDCALL = 6;
		public static final int ENTER = 66;
		public static final int ENVELOPE = 65;
		public static final int EQUALS = 70;
		public static final int EXPLORER = 64;
		public static final int F = 34;
		public static final int FOCUS = 80;
		public static final int G = 35;
		public static final int GRAVE = 68;
		public static final int H = 36;
		public static final int HEADSETHOOK = 79;
		public static final int HOME = 3;
		public static final int I = 37;
		public static final int J = 38;
		public static final int K = 39;
		public static final int L = 40;
		public static final int LEFT_BRACKET = 71;
		public static final int M = 41;
		public static final int MEDIA_FAST_FORWARD = 90;
		public static final int MEDIA_NEXT = 87;
		public static final int MEDIA_PLAY_PAUSE = 85;
		public static final int MEDIA_PREVIOUS = 88;
		public static final int MEDIA_REWIND = 89;
		public static final int MEDIA_STOP = 86;
		public static final int MENU = 82;
		public static final int MINUS = 69;
		public static final int MUTE = 91;
		public static final int N = 42;
		public static final int NOTIFICATION = 83;
		public static final int NUM = 78;
		public static final int O = 43;
		public static final int P = 44;
		public static final int PERIOD = 56;
		public static final int PLUS = 81;
		public static final int POUND = 18;
		public static final int POWER = 26;
		public static final int Q = 45;
		public static final int R = 46;
		public static final int RIGHT_BRACKET = 72;
		public static final int S = 47;
		public static final int SEARCH = 84;
		public static final int SEMICOLON = 74;
		public static final int SHIFT_LEFT = 59;
		public static final int SHIFT_RIGHT = 60;
		public static final int SLASH = 76;
		public static final int SOFT_LEFT = 1;
		public static final int SOFT_RIGHT = 2;
		public static final int SPACE = 62;
		public static final int STAR = 17;
		public static final int SYM = 63;
		public static final int T = 48;
		public static final int TAB = 61;
		public static final int U = 49;
		public static final int UNKNOWN = 0;
		public static final int V = 50;
		public static final int VOLUME_DOWN = 25;
		public static final int VOLUME_UP = 24;
		public static final int W = 51;
		public static final int X = 52;
		public static final int Y = 53;
		public static final int Z = 54;
		public static final int META_ALT_LEFT_ON = 16;
		public static final int META_ALT_ON = 2;
		public static final int META_ALT_RIGHT_ON = 32;
		public static final int META_SHIFT_LEFT_ON = 64;
		public static final int META_SHIFT_ON = 1;
		public static final int META_SHIFT_RIGHT_ON = 128;
		public static final int META_SYM_ON = 4;
		public static final int CONTROL_LEFT = 129;
		public static final int CONTROL_RIGHT = 130;
		public static final int ESCAPE = 131;
		public static final int END = 132;
		public static final int INSERT = 133;
		public static final int PAGE_UP = 92;
		public static final int PAGE_DOWN = 93;
		public static final int PICTSYMBOLS = 94;
		public static final int SWITCH_CHARSET = 95;
		public static final int BUTTON_CIRCLE = 255;
		public static final int BUTTON_A = 96;
		public static final int BUTTON_B = 97;
		public static final int BUTTON_C = 98;
		public static final int BUTTON_X = 99;
		public static final int BUTTON_Y = 100;
		public static final int BUTTON_Z = 101;
		public static final int BUTTON_L1 = 102;
		public static final int BUTTON_R1 = 103;
		public static final int BUTTON_L2 = 104;
		public static final int BUTTON_R2 = 105;
		public static final int BUTTON_THUMBL = 106;
		public static final int BUTTON_THUMBR = 107;
		public static final int BUTTON_START = 108;
		public static final int BUTTON_SELECT = 109;
		public static final int BUTTON_MODE = 110;

		public static final int NUMPAD_0 = 144;
		public static final int NUMPAD_1 = 145;
		public static final int NUMPAD_2 = 146;
		public static final int NUMPAD_3 = 147;
		public static final int NUMPAD_4 = 148;
		public static final int NUMPAD_5 = 149;
		public static final int NUMPAD_6 = 150;
		public static final int NUMPAD_7 = 151;
		public static final int NUMPAD_8 = 152;
		public static final int NUMPAD_9 = 153;

// public static final int BACKTICK = 0;
// public static final int TILDE = 0;
// public static final int UNDERSCORE = 0;
// public static final int DOT = 0;
// public static final int BREAK = 0;
// public static final int PIPE = 0;
// public static final int EXCLAMATION = 0;
// public static final int QUESTIONMARK = 0;

// ` | VK_BACKTICK
// ~ | VK_TILDE
// : | VK_COLON
// _ | VK_UNDERSCORE
// . | VK_DOT
// (break) | VK_BREAK
// | | VK_PIPE
// ! | VK_EXCLAMATION
// ? | VK_QUESTION
		public static final int COLON = 243;
		public static final int F1 = 244;
		public static final int F2 = 245;
		public static final int F3 = 246;
		public static final int F4 = 247;
		public static final int F5 = 248;
		public static final int F6 = 249;
		public static final int F7 = 250;
		public static final int F8 = 251;
		public static final int F9 = 252;
		public static final int F10 = 253;
		public static final int F11 = 254;
		public static final int F12 = 255;
  
  
  
  
  public boolean IsKeyPressed(int key){    
    if(!Keyboard.isCreated()) return false;
    
    if(key == ANY_KEY){
      return pressedKeys > 0;
    } else {
      return Keyboard.isKeyDown(gdxToLwjglKeyCode(key));
    }
  }
  
  
  public static int gdxToLwjglKeyCode(int code){
    switch(code){
      case APOSTROPHE:
			return Keyboard.KEY_APOSTROPHE;
		case LEFT_BRACKET:
			return Keyboard.KEY_LBRACKET;
		case RIGHT_BRACKET:
			return Keyboard.KEY_RBRACKET;
		case GRAVE:
			return Keyboard.KEY_GRAVE;
		case STAR:
			return Keyboard.KEY_MULTIPLY;
		case NUM:
			return Keyboard.KEY_NUMLOCK;
		case AT:
			return Keyboard.KEY_AT;
		case EQUALS:
			return Keyboard.KEY_EQUALS;
		case SYM:
			return Keyboard.KEY_LMETA;
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
		case FORWARD_DEL:
			return Keyboard.KEY_DELETE;
		case DPAD_LEFT:
			return Keyboard.KEY_LEFT;
		case DPAD_RIGHT:
			return Keyboard.KEY_RIGHT;
		case DPAD_UP:
			return Keyboard.KEY_UP;
		case DPAD_DOWN:
			return Keyboard.KEY_DOWN;
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
		case PERIOD:
			return Keyboard.KEY_PERIOD;
		case PLUS:
			return Keyboard.KEY_ADD;
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
		case DEL:
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
      default:
        return Keyboard.KEY_NONE;
    }
  }
  
    public int GetMouseX () 
    {
	return (int)(Mouse.getX() * GameState.GetDisplay().GetPixelScaleFactor());
    }

    public int GetMouseY () 
    {
	return (int)(Mouse.getY() * GameState.GetDisplay().GetPixelScaleFactor());
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
