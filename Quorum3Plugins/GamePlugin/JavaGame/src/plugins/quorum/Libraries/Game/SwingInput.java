/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import quorum.Libraries.Interface.Events.KeyboardEvent;
import plugins.quorum.Libraries.Game.libGDX.Pool;

/**
 *
 * @author alleew
 */
public abstract class SwingInput implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener
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
    
    private SwingApplication swingApplication;
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
//    IntSet pressedButtons = new IntSet();
//    InputProcessor processor;
    Canvas canvas;
    boolean catched = false;
    Robot robot = null;
    long currentEventTimeStamp;
    
    private static final KeyboardEvent keyConstants = new KeyboardEvent();
    private static final quorum.Libraries.Interface.Events.MouseEvent mouseConstants = new quorum.Libraries.Interface.Events.MouseEvent();
    
    public void Initialize(quorum.Libraries.Game.SwingApplication_ quorumApp)
    {
        swingApplication = ((quorum.Libraries.Game.SwingApplication)quorumApp).plugin_;
        SetListeners(swingApplication.GetCanvas());
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
            swingApplication.display.RequestRendering();
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
        swingApplication.display.RequestRendering();
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
        swingApplication.display.RequestRendering();
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
        CheckCatched(e);
        swingApplication.display.RequestRendering();
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
    
    private int ConvertMouseButton (int swingButton) 
    {
        if (swingButton == MouseEvent.BUTTON1)
            return mouseConstants.LEFT;
        if (swingButton == MouseEvent.BUTTON2)
            return mouseConstants.MIDDLE;
        if (swingButton == MouseEvent.BUTTON3)
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
            //pressedButtons.Add(event.mouseButton);
            swingApplication.display.RequestRendering();
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
            //pressedButtons.remove(event.mouseButton);
            //if (pressedButtons.size == 0) touchDown = false;
            swingApplication.display.RequestRendering();
        }
    }
}
