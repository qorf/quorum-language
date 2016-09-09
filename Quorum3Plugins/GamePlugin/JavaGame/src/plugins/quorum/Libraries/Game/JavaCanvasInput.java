/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import quorum.Libraries.Interface.Events.KeyboardEvent;
import plugins.quorum.Libraries.Game.libGDX.Pool;

/**
 *
 * @author alleew
 */
public class JavaCanvasInput implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener
{
    public java.lang.Object me_ = null;
    
    Pool<KeyboardEvent> usedKeyEvents = new Pool<KeyboardEvent>(16, 1000) 
    {
        protected KeyboardEvent newObject () 
        {
            return new KeyboardEvent();
        }
    };

    Pool<quorum.Libraries.Interface.Events.MouseEvent> usedMouseEvents = new Pool<quorum.Libraries.Interface.Events.MouseEvent>(16, 1000) 
    {
        protected quorum.Libraries.Interface.Events.MouseEvent newObject () 
        {
            return new quorum.Libraries.Interface.Events.MouseEvent();
        }
    };
    
    private JavaCanvasApplication canvasApplication;
    List<KeyboardEvent> keyEvents = new ArrayList<KeyboardEvent>();
    List<quorum.Libraries.Interface.Events.MouseEvent> mouseEvents = new ArrayList<quorum.Libraries.Interface.Events.MouseEvent>();
    int mouseX = 0;
    int mouseY = 0;
    int deltaX = 0;
    int deltaY = 0;
    boolean mouseClicked = false;
    boolean justClicked = false;
    int keyCount = 0;
    boolean[] keys = new boolean[256];
    boolean keyJustPressed = false;
    boolean[] justPressedKeys = new boolean[256];
    HashSet pressedButtons = new HashSet();
//    InputProcessor processor;
    Canvas canvas;
    boolean catched = false;
    Robot robot = null;
    long currentEventTimeStamp;
    
    private static final KeyboardEvent keyConstants = new KeyboardEvent();
    private static final quorum.Libraries.Interface.Events.MouseEvent mouseConstants = new quorum.Libraries.Interface.Events.MouseEvent();
    
    public void Initialize(quorum.Libraries.Game.JavaCanvasApplication_ quorumApp)
    {
        canvasApplication = ((quorum.Libraries.Game.JavaCanvasApplication)quorumApp).plugin_;
        SetListeners(canvasApplication.GetCanvas());
        try
        {
            robot = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
        }
        catch(HeadlessException | AWTException e)
        {
            // Do nothing.
        }
    }
    
    public void SetListeners (Canvas canvas) 
    {
        if (this.canvas != null) 
        {
            canvas.removeMouseListener(this);
            canvas.removeMouseMotionListener(this);
            canvas.removeMouseWheelListener(this);
            canvas.removeKeyListener(this);
        }
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
        canvas.addKeyListener(this);
        canvas.setFocusTraversalKeysEnabled(false);
        this.canvas = canvas;
    }
    
    public int GetX()
    {
        return mouseX;
    }
    
    public int GetY()
    {
        return mouseY;
    }
    
    public synchronized boolean IsKeyPressed(int key)
    {
        if (key == keyConstants.ANY_KEY)
            return keyCount > 0;
        
        if (key < 0 || key > 255)
            return false;
        
        return keys[key];
    }
    
    public boolean IsClicked()
    {
        return mouseClicked;
    }
    
    public void ProcessInputEvents()
    {
        synchronized(this)
        {
            justClicked = false;
            if (keyJustPressed)
            {
                keyJustPressed = false;
                for (int i = 0; i < justPressedKeys.length; i++)
                    justPressedKeys[i] = false;
            }
            
            quorum.Libraries.Game.JavaCanvasInput quorumInput = ((quorum.Libraries.Game.JavaCanvasInput)me_);
            
            int length = keyEvents.size();
            for (int i = 0; i < length; i++)
            {
                KeyboardEvent e = keyEvents.get(i);
                if (e.eventType == e.PRESSED_KEY)
                {
                    keyJustPressed = true;
                    justPressedKeys[e.keyCode] = true;
                }
                
                quorumInput.ProcessKeyboardEvent(e);
                usedKeyEvents.free(e);
            }
            
            length = mouseEvents.size();
            for (int i = 0; i < length; i++)
            {
                quorum.Libraries.Interface.Events.MouseEvent e = mouseEvents.get(i);
                
                if (e.eventType == e.CLICKED_MOUSE)
                    justClicked = true;
                
                quorumInput.ProcessMouseEvent(e);
                usedMouseEvents.free(e);
            }
            
            if (mouseEvents.size() == 0)
            {
                deltaX = 0;
                deltaY = 0;
            }
            
            keyEvents.clear();
            mouseEvents.clear();
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
        synchronized (this)
        {
            quorum.Libraries.Interface.Events.MouseEvent event = usedMouseEvents.obtain();
            event.x = e.getX();
            event.y = e.getY();
            event.eventType = event.DRAGGED_MOUSE;
            
            deltaX = event.x - mouseX;
            deltaY = event.y - mouseY;
            event.movementX = deltaX;
            event.movementY = deltaY;
            mouseEvents.add(event);
            
            mouseX = event.x;
            mouseY = event.y;
            CheckCatched(e);
            canvasApplication.display.RequestRendering();
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        quorum.Libraries.Interface.Events.MouseEvent event = usedMouseEvents.obtain();
        event.x = e.getX();
        event.y = e.getY();
        event.eventType = event.MOVED_MOUSE;
        
        deltaX = event.x - mouseX;
        deltaY = event.y - mouseY;
        event.movementX = deltaX;
        event.movementY = deltaY;
        mouseEvents.add(event);
        
        mouseX = event.x;
        mouseY = event.y;
        CheckCatched(e);
        canvasApplication.display.RequestRendering();
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        // Do nothing.
    }
    
    @Override
    public void mouseEntered(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        CheckCatched(e);
        canvasApplication.display.RequestRendering();
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
        CheckCatched(e);
        canvasApplication.display.RequestRendering();
    }
    
    private void CheckCatched(MouseEvent e)
    {
        if (catched && robot != null && canvas.isShowing())
        {
            
            if (e.getX() < 0 || e.getX() >= canvas.getWidth() || e.getY() < 0 || e.getY() >= canvas.getHeight())
            {
                int x = Math.max(0, Math.min(e.getX(), canvas.getWidth()) - 1) + canvas.getLocationOnScreen().x;
                int y = Math.max(0, Math.min(e.getY(), canvas.getHeight()) - 1) + canvas.getLocationOnScreen().y;
                
                robot.mouseMove(x, y);
            }
        }
    }
    
    private int ConvertMouseButton (int canvasButton) 
    {
        if (canvasButton == MouseEvent.BUTTON1)
            return mouseConstants.LEFT;
        if (canvasButton == MouseEvent.BUTTON2)
            return mouseConstants.MIDDLE;
        if (canvasButton == MouseEvent.BUTTON3)
            return mouseConstants.RIGHT;
        
        return mouseConstants.LEFT;
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        synchronized(this)
        {
            quorum.Libraries.Interface.Events.MouseEvent event = usedMouseEvents.obtain();
            event.x = e.getX();
            event.y = e.getY();
            event.eventType = event.CLICKED_MOUSE;
            event.mouseButton = ConvertMouseButton(e.getButton());
            
            deltaX = event.x - mouseX;
            deltaY = event.y - mouseY;
            event.movementX = deltaX;
            event.movementY = deltaY;
            mouseEvents.add(event);
            
            mouseX = event.x;
            mouseY = event.y;
            mouseClicked = true;
            pressedButtons.add(event.mouseButton);
            canvasApplication.display.RequestRendering();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        synchronized(this)
        {
            quorum.Libraries.Interface.Events.MouseEvent event = usedMouseEvents.obtain();
            event.x = e.getX();
            event.y = e.getY();
            event.mouseButton = ConvertMouseButton(e.getButton());
            event.eventType = event.RELEASED_MOUSE;
            
            deltaX = event.x - mouseX;
            deltaY = event.y - mouseY;
            event.movementX = deltaX;
            event.movementY = deltaY;
            mouseEvents.add(event);
            
            mouseX = event.x;
            mouseY = event.y;
            pressedButtons.remove(event.mouseButton);
            if (pressedButtons.isEmpty())
                mouseClicked = false;
            canvasApplication.display.RequestRendering();
        }
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        synchronized(this)
        {
            quorum.Libraries.Interface.Events.MouseEvent event = usedMouseEvents.obtain();
            event.eventType = event.SCROLLED_MOUSE;
            event.scrollAmount = e.getWheelRotation();
            mouseEvents.add(event);
            canvasApplication.display.RequestRendering();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        synchronized(this)
        {
            KeyboardEvent event = usedKeyEvents.obtain();
            event.keyCode = TranslateKeyCode(e.getKeyCode());
            event.eventType = event.PRESSED_KEY;
            keyEvents.add(event);
            if (!keys[event.keyCode])
            {
                keyCount++;
                keys[event.keyCode] = true;
            }
            canvasApplication.display.RequestRendering();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        synchronized(this)
        {
            KeyboardEvent event = usedKeyEvents.obtain();
            event.keyCode = TranslateKeyCode(e.getKeyCode());
            event.eventType = event.RELEASED_KEY;
            keyEvents.add(event);
            if (keys[event.keyCode])
            {
                keyCount--;
                keys[event.keyCode] = false;
            }
            canvasApplication.display.RequestRendering();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        // Since we don't have a "key typed" event, we send this as a key press.
        keyPressed(e);
    }
    
    protected static int TranslateKeyCode(int keyCode) 
    {
        switch (keyCode) 
        {
            case java.awt.event.KeyEvent.VK_ADD:
                return keyConstants.PLUS;
            case java.awt.event.KeyEvent.VK_SUBTRACT:
                return keyConstants.MINUS;
            case java.awt.event.KeyEvent.VK_0:
                return keyConstants.NUM_0;
            case java.awt.event.KeyEvent.VK_1:
                return keyConstants.NUM_1;
            case java.awt.event.KeyEvent.VK_2:
                return keyConstants.NUM_2;
            case java.awt.event.KeyEvent.VK_3:
                return keyConstants.NUM_3;
            case java.awt.event.KeyEvent.VK_4:
                return keyConstants.NUM_4;
            case java.awt.event.KeyEvent.VK_5:
                return keyConstants.NUM_5;
            case java.awt.event.KeyEvent.VK_6:
                return keyConstants.NUM_6;
            case java.awt.event.KeyEvent.VK_7:
                return keyConstants.NUM_7;
            case java.awt.event.KeyEvent.VK_8:
                return keyConstants.NUM_8;
            case java.awt.event.KeyEvent.VK_9:
                return keyConstants.NUM_9;
            case java.awt.event.KeyEvent.VK_A:
                return keyConstants.A;
            case java.awt.event.KeyEvent.VK_B:
                return keyConstants.B;
            case java.awt.event.KeyEvent.VK_C:
                return keyConstants.C;
            case java.awt.event.KeyEvent.VK_D:
                return keyConstants.D;
            case java.awt.event.KeyEvent.VK_E:
                return keyConstants.E;
            case java.awt.event.KeyEvent.VK_F:
                return keyConstants.F;
            case java.awt.event.KeyEvent.VK_G:
                return keyConstants.G;
            case java.awt.event.KeyEvent.VK_H:
                return keyConstants.H;
            case java.awt.event.KeyEvent.VK_I:
                return keyConstants.I;
            case java.awt.event.KeyEvent.VK_J:
                return keyConstants.J;
            case java.awt.event.KeyEvent.VK_K:
                return keyConstants.K;
            case java.awt.event.KeyEvent.VK_L:
                return keyConstants.L;
            case java.awt.event.KeyEvent.VK_M:
                return keyConstants.M;
            case java.awt.event.KeyEvent.VK_N:
                return keyConstants.N;
            case java.awt.event.KeyEvent.VK_O:
                return keyConstants.O;
            case java.awt.event.KeyEvent.VK_P:
                return keyConstants.P;
            case java.awt.event.KeyEvent.VK_Q:
                return keyConstants.Q;
            case java.awt.event.KeyEvent.VK_R:
                return keyConstants.R;
            case java.awt.event.KeyEvent.VK_S:
                return keyConstants.S;
            case java.awt.event.KeyEvent.VK_T:
                return keyConstants.T;
            case java.awt.event.KeyEvent.VK_U:
                return keyConstants.U;
            case java.awt.event.KeyEvent.VK_V:
                return keyConstants.V;
            case java.awt.event.KeyEvent.VK_W:
                return keyConstants.W;
            case java.awt.event.KeyEvent.VK_X:
                return keyConstants.X;
            case java.awt.event.KeyEvent.VK_Y:
                return keyConstants.Y;
            case java.awt.event.KeyEvent.VK_Z:
                return keyConstants.Z;
            case java.awt.event.KeyEvent.VK_ALT:
                return keyConstants.ALT_LEFT;
            case java.awt.event.KeyEvent.VK_ALT_GRAPH:
                return keyConstants.ALT_RIGHT;
            case java.awt.event.KeyEvent.VK_BACK_SLASH:
                return keyConstants.BACKSLASH;
            case java.awt.event.KeyEvent.VK_COMMA:
                return keyConstants.COMMA;
            case java.awt.event.KeyEvent.VK_DELETE:
                return keyConstants.FORWARD_DEL;
            case java.awt.event.KeyEvent.VK_LEFT:
                return keyConstants.DPAD_LEFT;
            case java.awt.event.KeyEvent.VK_RIGHT:
                return keyConstants.DPAD_RIGHT;
            case java.awt.event.KeyEvent.VK_UP:
                return keyConstants.DPAD_UP;
            case java.awt.event.KeyEvent.VK_DOWN:
                return keyConstants.DPAD_DOWN;
            case java.awt.event.KeyEvent.VK_ENTER:
                return keyConstants.ENTER;
            case java.awt.event.KeyEvent.VK_HOME:
                return keyConstants.HOME;
            case java.awt.event.KeyEvent.VK_MINUS:
                return keyConstants.MINUS;
            case java.awt.event.KeyEvent.VK_PERIOD:
                return keyConstants.PERIOD;
            case java.awt.event.KeyEvent.VK_PLUS:
                return keyConstants.PLUS;
            case java.awt.event.KeyEvent.VK_SEMICOLON:
                return keyConstants.SEMICOLON;
            case java.awt.event.KeyEvent.VK_SHIFT:
                return keyConstants.SHIFT_LEFT;
            case java.awt.event.KeyEvent.VK_SLASH:
                return keyConstants.SLASH;
            case java.awt.event.KeyEvent.VK_SPACE:
                return keyConstants.SPACE;
            case java.awt.event.KeyEvent.VK_TAB:
                return keyConstants.TAB;
            case java.awt.event.KeyEvent.VK_BACK_SPACE:
                return keyConstants.DEL;
            case java.awt.event.KeyEvent.VK_CONTROL:
                return keyConstants.CONTROL_LEFT;
            case java.awt.event.KeyEvent.VK_ESCAPE:
                return keyConstants.ESCAPE;
            case java.awt.event.KeyEvent.VK_END:
                return keyConstants.END;
            case java.awt.event.KeyEvent.VK_INSERT:
                return keyConstants.INSERT;
            case java.awt.event.KeyEvent.VK_PAGE_UP:
                return keyConstants.PAGE_UP;
            case java.awt.event.KeyEvent.VK_PAGE_DOWN:
                return keyConstants.PAGE_DOWN;
            case java.awt.event.KeyEvent.VK_F1:
                return keyConstants.F1;
            case java.awt.event.KeyEvent.VK_F2:
                return keyConstants.F2;
            case java.awt.event.KeyEvent.VK_F3:
                return keyConstants.F3;
            case java.awt.event.KeyEvent.VK_F4:
                return keyConstants.F4;
            case java.awt.event.KeyEvent.VK_F5:
                return keyConstants.F5;
            case java.awt.event.KeyEvent.VK_F6:
                return keyConstants.F6;
            case java.awt.event.KeyEvent.VK_F7:
                return keyConstants.F7;
            case java.awt.event.KeyEvent.VK_F8:
                return keyConstants.F8;
            case java.awt.event.KeyEvent.VK_F9:
                return keyConstants.F9;
            case java.awt.event.KeyEvent.VK_F10:
                return keyConstants.F10;
            case java.awt.event.KeyEvent.VK_F11:
                return keyConstants.F11;
            case java.awt.event.KeyEvent.VK_F12:
                return keyConstants.F12;
            case java.awt.event.KeyEvent.VK_COLON:
                return keyConstants.COLON;
            case java.awt.event.KeyEvent.VK_NUMPAD0:
                return keyConstants.NUM_0;
            case java.awt.event.KeyEvent.VK_NUMPAD1:
                return keyConstants.NUM_1;
            case java.awt.event.KeyEvent.VK_NUMPAD2:
                return keyConstants.NUM_2;
            case java.awt.event.KeyEvent.VK_NUMPAD3:
                return keyConstants.NUM_3;
            case java.awt.event.KeyEvent.VK_NUMPAD4:
                return keyConstants.NUM_4;
            case java.awt.event.KeyEvent.VK_NUMPAD5:
                return keyConstants.NUM_5;
            case java.awt.event.KeyEvent.VK_NUMPAD6:
                return keyConstants.NUM_6;
            case java.awt.event.KeyEvent.VK_NUMPAD7:
                return keyConstants.NUM_7;
            case java.awt.event.KeyEvent.VK_NUMPAD8:
                return keyConstants.NUM_8;
            case java.awt.event.KeyEvent.VK_NUMPAD9:
                return keyConstants.NUM_9;
        }
        return keyConstants.UNKNOWN;
    }
    
    public boolean JustClicked()
    {
        return justClicked;
    }
    
    public boolean IsButtonPressed(int button)
    {
        return pressedButtons.contains(button);
    }
    
    public int GetMovementX()
    {
        return deltaX;
    }
    
    public int GetMovementY()
    {
        return deltaY;
    }
}
