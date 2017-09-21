/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import quorum.Libraries.Game.Graphics.Color_;
import quorum.Libraries.Game.ScreenResolution_;
import quorum.Libraries.Containers.Array_;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GLCapabilities;

/**
 *
 * @author Taylor Bockman
 */
public class DesktopDisplay {

    public java.lang.Object me_ = null;
    
    // May only be necessary for development. May be removed later.
    GLFWErrorCallback errorCallback;
    // Used to capture resize events. Currently does nothing.
    GLFWFramebufferSizeCallback resizeCallback = new GLFWFramebufferSizeCallback()
            {
                @Override
                public void invoke(long window, int width, int height)
                {
                    ResizeEvent(window, width, height);
                }
            };
    
    volatile boolean isContinuous = true;
    volatile boolean requestRendering = false;
    float deltaTime = 0;
    long frameStart = 0;
    int frames = 0;
    int fps;
    long lastTime = System.nanoTime();
    String extensions = null;
    
    static int major = 2;
    static int minor = 1;
    quorum.Libraries.Game.Graphics.GraphicsManager_ gl20;
    
    /*
    The window the game is using. For the initial transition to GLFW, this is
    represents a single static window, which will remain over the entire
    lifespan of the game. In another iteration (i.e. when adding multiple window
    support in the Quorum API) this will need to transition to a more flexible
    system. Even in a multiple window system there will most likely be a primary
    "default" window, but that information would most likely be stored in Quorum
    rather than here in the plugins.
    */ 
    private long window;
    
    // Describes the features available in the OpenGL context used by the
    // primary window.
    GLCapabilities glCapabilities;
    
    public void SetupDisplay() 
    {
        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
        quorum.Libraries.Game.DesktopConfiguration_ config = dis.config;
        
        GLFW.glfwInit();
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, glBoolean(config.Get_Libraries_Game_DesktopConfiguration__resizable_()));
        // For ease of compatability with the LWJGL 2 release, we request OpenGL 2.1.
        // This may change to a more modern release in the future.
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, major);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, minor);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_ANY_PROFILE); 
        
        /*
        Handle for a GLFW monitor. If it's 0, GLFW treats it as null. If it's
        not "null", the application is made fullscreen on that monitor.
        Otherwise, the application is windowed.
        */
        long monitor;
        
        // Width and height of the window, or resolution to use for fullscreen.
        int width;
        int height;
        
        String name = config.Get_Libraries_Game_DesktopConfiguration__title_();
        
        ScreenResolution_ resolution = config.Get_Libraries_Game_DesktopConfiguration__defaultResolution_();
        
        if (resolution != null)
        {
            // Use the given resolution.
            width = resolution.GetWidth();
            height = resolution.GetHeight();
            
            // This currently defaults to the primary monitor, but will most
            // likely need to change with a multiple window system.
            monitor = GLFW.glfwGetPrimaryMonitor();
        }
        else
        {
            // Use the width and height of the config.
            width = config.Get_Libraries_Game_DesktopConfiguration__width_();
            height = config.Get_Libraries_Game_DesktopConfiguration__height_();
            
            monitor = 0;
        }
        
        window = GLFW.glfwCreateWindow(width, height, name, monitor, 0);
        
        if (window == 0)
        {
            throw new GameRuntimeError("The Game display couldn't be initialized.");
        }
        
        GLFW.glfwMakeContextCurrent(window);
        
        glCapabilities = GL.createCapabilities();
        GLFW.glfwShowWindow(window);
    }

    public void SetVSync(boolean vsync) 
    {
//        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
//        dis.Set_Libraries_Game_DesktopDisplay__vsync_(vsync);
//        Display.setVSyncEnabled(vsync);
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
        int[] x = new int[1], y = new int[1];
        GLFW.glfwGetFramebufferSize(window, x, y);
        return x[0];
    }

    public int GetDisplayY() {
        int[] x = new int[1], y = new int[1];
        GLFW.glfwGetFramebufferSize(window, x, y);
        return y[0];
    }

    public int GetWidth() {
        int[] width = new int[1], height = new int[1];
        GLFW.glfwGetFramebufferSize(window, width, height);
        return width[0];
    }

    public int GetHeight() {
        int[] width = new int[1], height = new int[1];
        GLFW.glfwGetFramebufferSize(window, width, height);
        return height[0];
    }
    
    public double GetPixelScaleFactor() {
//        return Display.getPixelScaleFactor();
        return 0;
    }

    public void ProcessMessages() {
//        Display.processMessages();
    }

    public boolean IsCloseRequested() {
//        return Display.isCloseRequested();
        return false;
    }

    public void Destroy() {
        // This will need to be revisited for a multiple window system.
        GLFW.glfwDestroyWindow(window);
    }

    public boolean IsActive() {
//        return Display.isActive();
        return false;
    }
    
    public boolean WasResized() {
//        return Display.wasResized();
        return false;
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
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(window);
    }
   
    public ScreenResolution_ GetDesktopResolution()
    {
//        ScreenResolution_ resolution = new quorum.Libraries.Game.ScreenResolution();
//        org.lwjgl.opengl.DisplayMode mode = Display.getDesktopDisplayMode();
//        resolution.Set_Libraries_Game_ScreenResolution__width_(mode.getWidth());
//        resolution.Set_Libraries_Game_ScreenResolution__height_(mode.getHeight());
//        resolution.Set_Libraries_Game_ScreenResolution__bitsPerPixel_(mode.getBitsPerPixel());
//        resolution.Set_Libraries_Game_ScreenResolution__frequency_(mode.getFrequency());
//        resolution.Set_Libraries_Game_ScreenResolution__fullscreen_(mode.isFullscreenCapable());
//        return resolution;
        return null;
    }
    
    public void GetAvailableResolutionsNative(Array_ array)
    {
//        try
//        {
//            org.lwjgl.opengl.DisplayMode[] modes = Display.getAvailableDisplayModes();
//            
//            for (int i = 0; i < modes.length; i++)
//            {
//                ScreenResolution_ resolution = new quorum.Libraries.Game.ScreenResolution();
//                org.lwjgl.opengl.DisplayMode mode = modes[i];
//                resolution.Set_Libraries_Game_ScreenResolution__width_(mode.getWidth());
//                resolution.Set_Libraries_Game_ScreenResolution__height_(mode.getHeight());
//                resolution.Set_Libraries_Game_ScreenResolution__bitsPerPixel_(mode.getBitsPerPixel());
//                resolution.Set_Libraries_Game_ScreenResolution__frequency_(mode.getFrequency());
//                resolution.Set_Libraries_Game_ScreenResolution__fullscreen_(mode.isFullscreenCapable());
//                array.Add(resolution);
//            }
//        }
//        catch(Exception ex)
//        {
//            throw new GameRuntimeError("An error occurred while retrieving available screen resolutions: " + ex.getMessage());
//        }
    };
    
    public void SetScreenResolution(ScreenResolution_ resolution)
    {
//        try
//        {
//            org.lwjgl.opengl.DisplayMode[] modes = Display.getAvailableDisplayModes();
//            org.lwjgl.opengl.DisplayMode targetDisplayMode = null;
//            for (int i = 0; i < modes.length; i++)
//            {
//                org.lwjgl.opengl.DisplayMode mode = modes[i];
//                if (resolution.GetWidth() == mode.getWidth() && resolution.GetHeight() == mode.getHeight()
//                        && resolution.GetBitsPerPixel() == mode.getBitsPerPixel() 
//                        && resolution.GetFrequency() == mode.getFrequency()
//                        && resolution.IsFullscreen() == mode.isFullscreenCapable())
//                {
//                    targetDisplayMode = mode;
//                    break;
//                }
//            }
//            if (targetDisplayMode == null)
//                targetDisplayMode = new org.lwjgl.opengl.DisplayMode(resolution.GetWidth(), resolution.GetHeight());
//
//            Display.setDisplayMode(targetDisplayMode);
//            Display.setFullscreen(resolution.IsFullscreen());
//        }
//        catch(Exception ex)
//        {
//            throw new GameRuntimeError("An error occurred while setting the screen resolution: " + ex.getMessage());
//        }
    }
    
    public ScreenResolution_ GetScreenResolution()
    {
//        org.lwjgl.opengl.DisplayMode mode = Display.getDisplayMode();
//        ScreenResolution_ resolution = new quorum.Libraries.Game.ScreenResolution();
//        resolution.Set_Libraries_Game_ScreenResolution__width_(mode.getWidth());
//        resolution.Set_Libraries_Game_ScreenResolution__height_(mode.getHeight());
//        resolution.Set_Libraries_Game_ScreenResolution__frequency_(mode.getFrequency());
//        resolution.Set_Libraries_Game_ScreenResolution__bitsPerPixel_(mode.getBitsPerPixel());
//        resolution.Set_Libraries_Game_ScreenResolution__fullscreen_(mode.isFullscreenCapable());
//        return resolution;
        return null;
    }
    
    public int glBoolean(boolean value)
    {
        return value ? org.lwjgl.opengl.GL11.GL_TRUE : org.lwjgl.opengl.GL11.GL_FALSE;
    }
    
    public void ResizeEvent(long window, int width, int height)
    {
        // Handle resizing in a sensible manner.
        // When the Quorum resizing API is implemented, inform listeners.
    }
}
