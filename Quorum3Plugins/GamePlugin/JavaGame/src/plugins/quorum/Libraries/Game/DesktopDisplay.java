/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.LWJGLException;
import quorum.Libraries.Game.Graphics.Color_;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author Taylor Bockman
 */
public class DesktopDisplay {

    public java.lang.Object me_ = null;
    
    volatile boolean isContinuous = true;
    volatile boolean requestRendering = false;
    float deltaTime = 0;
    long frameStart = 0;
    int frames = 0;
    int fps;
    long lastTime = System.nanoTime();
    String extensions = null;
    
    static int major;
    static int minor;
    quorum.Libraries.Game.Graphics.GraphicsManager_ gl20;
    
    /*
     Right now setupDisplay only works on OpenGL and not software rendering.
     Also, this should eventually throw an exception when it fails.
     */
    public void SetupDisplay() 
    {
        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
            
        dis.SetVSync(dis.config.Get_Libraries_Game_DesktopConfiguration__vSyncEnabled_());
            
        Color_ color = dis.config.Get_Libraries_Game_DesktopConfiguration__initialBackgroundColor_();

        boolean displayCreated = SetDisplayMode(dis.config.Get_Libraries_Game_DesktopConfiguration__width_(),
            dis.config.Get_Libraries_Game_DesktopConfiguration__height_(),
            dis.config.Get_Libraries_Game_DesktopConfiguration__fullScreen_());
            
        if (!displayCreated)
            throw new GameRuntimeError("An error ocurred in SetDisplayMode!");
            
        //Set icons
        Display.setTitle(dis.config.Get_Libraries_Game_DesktopConfiguration__title_());
        Display.setResizable(dis.config.Get_Libraries_Game_DesktopConfiguration__resizable_());

        //Reader beware: Casting double to float here. Since RGB can't be extremely large or
        //               small this shouldn't have any effect on coloring.
        Display.setInitialBackground((float) color.GetRed(), (float) color.GetGreen(), (float) color.GetBlue());
        Display.setLocation(dis.config.Get_Libraries_Game_DesktopConfiguration__x_(),
            dis.config.Get_Libraries_Game_DesktopConfiguration__y_());

        createDisplayPixelFormat(); //Display is actually created in here
        InitiateGLInstances();
    }

    public boolean SetDisplayMode(int width, int height, boolean fullScreen) 
    {
        quorum.Libraries.Game.DesktopDisplay quorumDisplay = (quorum.Libraries.Game.DesktopDisplay) me_;
        
        if (GetWidth() == width && GetHeight() == height && Display.isFullscreen() == fullScreen) 
        {
            return true;
        }

        try 
        {
            org.lwjgl.opengl.DisplayMode targetDisplayMode = null;

            if (fullScreen) 
            {
                org.lwjgl.opengl.DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i = 0; i < modes.length; i++) 
                {
                    org.lwjgl.opengl.DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) 
                    {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) 
                        {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) 
                            {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        // if we've found a match for bpp and frequence against the
                        // original display mode then it's probably best to go for this one
                        // since it's most likely compatible with the monitor
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel())
                            && (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) 
                        {
                            targetDisplayMode = current;
                            break;
                        }
                    }
                }
            }
            else 
            {
                targetDisplayMode = new org.lwjgl.opengl.DisplayMode(width, height);
            }

            if (targetDisplayMode == null) 
            {
                return false;
            }

            boolean resizable = !fullScreen && quorumDisplay.config.Get_Libraries_Game_DesktopConfiguration__resizable_();
	    
            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullScreen);
            // Workaround for bug in LWJGL whereby resizable state is lost on DisplayMode change
            if (resizable == Display.isResizable()) {
                    Display.setResizable(!resizable);
            }
            Display.setResizable(resizable);

            float scaleFactor = Display.getPixelScaleFactor();
            quorumDisplay.config.Set_Libraries_Game_DesktopConfiguration__width_((int)(targetDisplayMode.getWidth() * scaleFactor));
            quorumDisplay.config.Set_Libraries_Game_DesktopConfiguration__height_((int)(targetDisplayMode.getHeight() * scaleFactor));
            
            quorumDisplay.resize = true;
            return true;
        }
        catch (LWJGLException e) 
        {
            System.out.println("Error in SetDisplayMode!");
            return false;
        }
    }
    
    public DisplayMode GetDesktopDisplayMode() 
    {
        return Display.getDesktopDisplayMode();
    }

    public void SetVSync(boolean vsync) {
        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
        dis.Set_Libraries_Game_DesktopDisplay__vsync_(vsync);
        Display.setVSyncEnabled(vsync);
    }

    protected void createDisplayPixelFormat() 
    {
        //Create the display
        try 
        {
            quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;

            if (dis.config.Get_Libraries_Game_DesktopConfiguration__useGL30_()) 
            {
                //Do nothing, we dont support GL30 yet.
                System.out.println("useGL30 was set to true!");
            }
            else 
            {
                Display.create(new PixelFormat(
                    dis.config.Get_Libraries_Game_ApplicationConfiguration__r_()
                    + dis.config.Get_Libraries_Game_ApplicationConfiguration__g_()
                    + dis.config.Get_Libraries_Game_ApplicationConfiguration__b_(),
                    dis.config.Get_Libraries_Game_ApplicationConfiguration__a_(),
                    dis.config.Get_Libraries_Game_ApplicationConfiguration__depth_(),
                    dis.config.Get_Libraries_Game_ApplicationConfiguration__stencil_(),
                    dis.config.Get_Libraries_Game_ApplicationConfiguration__samples_()));
              
            }

        }
        catch (LWJGLException e) 
        {
            e.printStackTrace();
            Display.destroy();
            throw new GameRuntimeError("createDisplayPixelFormat crashed!");

            //Other important clean up stuff
        }
    }
    
    public void InitiateGLInstances()
    {
        String version = org.lwjgl.opengl.GL11.glGetString(GL11.GL_VERSION);
	major = Integer.parseInt("" + version.charAt(0));
	minor = Integer.parseInt("" + version.charAt(2));

	if (major <= 1)
            throw new GameRuntimeError("OpenGL 2.0 or higher with the FBO extension is required. OpenGL version: " + version);
	if (major == 2 || version.contains("2.1")) 
        {
            if (!supportsExtension("GL_EXT_framebuffer_object") && !supportsExtension("GL_ARB_framebuffer_object")) {
		throw new GameRuntimeError("OpenGL 2.0 or higher with the FBO extension is required. OpenGL version: " + version
		+ ", FBO extension: false");
			}
	}

        //GameState.SetGameGraphics(gl20);
        //GameState.SetGameGraphics20Manager(gl20);
    }
    
    public boolean supportsExtension (String extension) 
    {
		if (extensions == null) extensions = GameStateManager.nativeGraphics.glGetString(GraphicsManager.GL_EXTENSIONS);
		return extensions.contains(extension);
    }

    public void RequestRendering() {
        synchronized (this) {
            requestRendering = true;
        }
    }
    
    public boolean ShouldRender () 
    {
        synchronized (this) 
        {
            boolean rq = requestRendering;
            requestRendering = false;
            return rq || isContinuous;
        }
    }

    public int GetDisplayX() {
        return Display.getX();
    }

    public int GetDisplayY() {
        return Display.getY();
    }

    public int GetWidth() {
        return (int)(Display.getWidth() * Display.getPixelScaleFactor());
    }

    public int GetHeight() {
        return (int)(Display.getHeight() * Display.getPixelScaleFactor());
    }
    
    public double GetPixelScaleFactor() {
        return Display.getPixelScaleFactor();
    }

    public void ProcessMessages() {
        Display.processMessages();
    }

    public boolean IsCloseRequested() {
        return Display.isCloseRequested();
    }

    public void Destroy() {
        Display.destroy();
    }

    public boolean IsActive() {
        return Display.isActive();
    }
    
    public boolean WasResized() {
        return Display.wasResized();
    }
    
    public void UpdateTime () 
    {
        long time = System.nanoTime();
        deltaTime = (time - lastTime) / 1000000000.0f;
        lastTime = time;

        if (time - frameStart >= 1000000000) 
        {
            fps = frames;
            frames = 0;
            frameStart = time;
        }
        frames++;
    }
    
    public double GetSecondsBetweenFrames()
    {
        return deltaTime;
    }
    
    public void SetLastTime()
    {
        lastTime = System.nanoTime();
    }
    
    public void Update()
    {
        Display.update(false);
    }
    

}
