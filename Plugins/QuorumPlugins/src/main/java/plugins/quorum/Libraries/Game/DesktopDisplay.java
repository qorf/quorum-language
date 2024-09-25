/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.nio.ByteBuffer;

import org.lwjgl.system.MemoryUtil;
import quorum.Libraries.Game.ScreenResolution_;
import quorum.Libraries.Containers.Array_;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWCharCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.opengl.GLCapabilities;
import plugins.quorum.Libraries.Interface.Events.KeyboardProcessor;
import plugins.quorum.Libraries.Interface.Events.MouseProcessor;
import plugins.quorum.Libraries.Interface.Events.ResizeProcessor;
import plugins.quorum.Libraries.Interface.Events.TextInputProcessor;
import quorum.Libraries.Game.Graphics.PixelMap_;
import quorum.Libraries.Game.ScreenResolution;
import quorum.Libraries.System.Properties;

/**
 *
 * @author Taylor Bockman, William Allee
 */
public class DesktopDisplay {

    public java.lang.Object me_ = null;
    
    // May only be necessary for development. May be removed later.
    GLFWErrorCallback errorCallback;
    
    // Whether or not the window was resized during the last cycle.
    boolean wasResized = false;
    
    // Used to indicate that the GLFW message pump needs to pause because input was received.
    private boolean pauseEventPolling = false;
    
    /*
    By default, whenever we resize the window, we also call ContinueGame to allow the resize event to be processed.
    However, we sometimes need to prevent this, especially if the resize event would happen before
    we've finished setting up the environment.
    */
    private static boolean continueOnResize = true;
    
    GLFWWindowSizeCallback resizeCallback = new GLFWWindowSizeCallback()
        {
            @Override
            public void invoke(long window, int width, int height)
            {
                wasResized = true;
                ResizeProcessor.AddResizeEvent(window, GetWidth(), GetHeight());
                
                if (continueOnResize)
                {
                    GameStateManager.game.ContinueGame();
                }
                GLFW.glfwSwapBuffers(window);
                
                /* 
                Draw the game to the other buffer as well. This means we're
                technically drawing twice on every resize, but this guarantees
                that if we swap to the other buffer that it won't have junk
                data. Resizing the screen is a rare operation, so this should
                have a minor impact on overall perfo
                */
                UpdateBuffer();
            }
        };
    
    GLFWScrollCallback scrollCallback = new GLFWScrollCallback()
        {
            @Override
            public void invoke(long window, double xOffset, double yOffset)
            {
                // This is stored here to provide a default behavior for the
                // InputMonitor on Desktop, because GLFW doesn't allow polling of the
                // scroll wheel.
                scroll = yOffset;
                MouseProcessor.AddMouseWheelEvent(window, xOffset, yOffset);
            }
        };
    
    GLFWKeyCallback keyboardCallback = new GLFWKeyCallback()
    {
        @Override
        public void invoke(long window, int key, int code, int action, int modifiers)
        {
            KeyboardProcessor.AddKeyboardEvent(window, key, code, action, modifiers);
        }
    };
    
    GLFWCursorPosCallback mouseMovementCallback = new GLFWCursorPosCallback()
    {
        @Override
        public void invoke(long window, double x, double y)
        {
            MouseProcessor.AddMouseMovementEvent(window, x, y);
        }
    };
    
    GLFWMouseButtonCallback mouseCallback = new GLFWMouseButtonCallback()
    {
        @Override
        public void invoke(long window, int button, int action, int modifiers)
        {
            MouseProcessor.AddMouseEvent(window, button, action, modifiers);
        }
    };
    
    GLFWCharCallback textCallback = new GLFWCharCallback()
    {
        @Override
        public void invoke(long window, int codepoint) {
            char[] chars = Character.toChars(codepoint);
            String s = "";
            for (int i = 0; i < chars.length; i++)
                s = s + chars[i];
            TextInputProcessor.AddTextInputEvent(window, codepoint, s);
        }
    };
    
    GLFWWindowFocusCallback windowFocusCallback = new GLFWWindowFocusCallback()
    {
        @Override
        public void invoke(long window, boolean focused) 
        {
            quorum.Libraries.Interface.Events.WindowFocusEvent event = 
                new quorum.Libraries.Interface.Events.WindowFocusEvent();
            
            event.Set(focused);
            
            GameStateManager.input.NotifyWindowFocusListeners(event);
        }
    };
    
    volatile boolean isContinuous = true;
    volatile boolean requestRendering = false;
    float deltaTime = 0;
    double totalTime = 0;
    long frameStart = 0;
    int frames = 0;
    int fps;
    long lastTime = System.nanoTime();
    String extensions = null;
    private boolean initialized = false;
    
    static int major = 4;
    static int minor = 1;
    quorum.Libraries.Game.Graphics.GraphicsManager_ gl20;
    
    // Quorum representation of current resolution.
    ScreenResolution_ resolution;
    // GLFW representation of the original desktop resolution.
    GLFWVidMode desktopResolution;
    
    /*
    The window the game is using. For the initial transition to GLFW, this is
    represents a single static window, which will remain over the entire
    lifespan of the game. In another iteration (i.e. when adding multiple window
    support in the Quorum API) this will need to transition to a more flexible
    system. Even in a multiple window system there will most likely be a primary
    "default" window, but that information would most likely be stored in Quorum
    rather than here in the plugins.
    */ 
    public static long window = 0;
    
    /*
    The DesktopDisplay provides a default scroll wheel listener which is used to
    mark the amount scrolled on each frame. This is used for the InputMonitor on
    desktop platforms in order to provide pollable mouse wheel information. This
    will also need to be reviewed for a multiple window system.
    */
    public static double scroll = 0;
    
    // Describes the features available in the OpenGL context used by the
    // primary window.
    GLCapabilities glCapabilities;
    
    public String GetClipboard() {
        String string = GLFW.glfwGetClipboardString(window);
        return string;
    }

    public void SetClipboard(String string) {
        ByteBuffer buffer = MemoryUtil.memUTF8(string);
        try {
            GLFW.glfwSetClipboardString(window, buffer);
        } finally {
            MemoryUtil.memFree(buffer);
        }
    }
    
    public void SetupDisplay() 
    {
        if (window != 0)
        {
            throw new GameRuntimeError("A display has already been set up. Multiple displays are not supported yet.");
        }
        
        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
        quorum.Libraries.Game.DesktopConfiguration_ config = dis.config;
        
        Initialize();
        // Save the original desktop resolution, if it hasn't already been requested.
        GetOriginalResolution();
        
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, glBoolean(config.Get_Libraries_Game_DesktopConfiguration__resizable_()));
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, glBoolean(config.Get_Libraries_Game_DesktopConfiguration__maximized_()));
        
        // For ease of compatability with the LWJGL 2 release, we request OpenGL 2.1.
        // This may change to a more modern release in the future.
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, major);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, minor);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, 1);
        
        if (GameStateManager.operatingSystem.contains("Windows 10") && config.Get_Libraries_Game_DesktopConfiguration__enableAccessibility_())
        {
            GLFW.glfwWindowHint(GLFW.GLFW_FOCUSED, GLFW.GLFW_FALSE);
            GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        }
        
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
        resolution = config.Get_Libraries_Game_DesktopConfiguration__defaultResolution_();
        
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
            resolution = GetResolutionFromConfiguration();
            
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
        
        SetVSync(config.Get_Libraries_Game_DesktopConfiguration__vSyncEnabled_());
        
        if (!resolution.IsFullscreen() && GetPixelScaleFactor() != 1.0)
            SetScreenResolution(resolution);
        
        GLFW.glfwSetWindowSizeCallback(window, resizeCallback);
        GLFW.glfwSetKeyCallback(window, keyboardCallback);
        GLFW.glfwSetCursorPosCallback(window, mouseMovementCallback);
        GLFW.glfwSetMouseButtonCallback(window, mouseCallback);
        GLFW.glfwSetScrollCallback(window, scrollCallback);
        GLFW.glfwSetCharCallback(window, textCallback);
        GLFW.glfwSetWindowFocusCallback(window, windowFocusCallback);
    }
    
    public void FirstTimeShowWindow()
    {
        ForceWindowVisible();
    }
    
    /*
    This action forces the window to be visible and focused. This is used by the
    Windows accessibility system to "finish" displaying the window once the UIA
    hooks have been set up.
    */
    public static void ForceWindowVisible()
    {
        continueOnResize = false;
        GLFW.glfwShowWindow(window);
        continueOnResize = true;
    }

    public void SetVSync(boolean vsync) 
    {
        // Applies to the current context. This is fine for the current system
        // (one window with one context) but will need to be revised for a
        // multiple window system.
        GLFW.glfwSwapInterval(vsync ? 1 : 0);
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

    int[] x = new int[1], y = new int[1];
    int[] width = new int[1], height = new int[1];
    public int GetDisplayX() {
        GLFW.glfwGetWindowPos(window, x, y);
        return x[0];
    }

    public int GetDisplayY() {
        GLFW.glfwGetWindowPos(window, x, y);
        return y[0];
    }

    public int GetWindowWidth() {
        
        GLFW.glfwGetWindowSize(window, width, height);
        return width[0];
    }

    public int GetWindowHeight() {
        GLFW.glfwGetWindowSize(window, width, height);
        return height[0];
    }
    
    public int GetWidth()
    {
        GLFW.glfwGetFramebufferSize(window, width, height);
        return width[0];
    }
    
    public int GetHeight()
    {
        GLFW.glfwGetFramebufferSize(window, width, height);
        return height[0];
    }
    
    public double GetPixelScaleFactor() 
    {
        return (double)GetWidth() / (double)GetWindowWidth();
    }

    public void ProcessMessages() 
    {
//        Display.processMessages();
    }
    
    public void SetWindowPosition(int x, int y)
    {
        GLFW.glfwSetWindowPos(window, x, y);
    }

    public boolean IsCloseRequested() {
        // This will need to be revised for a multiple-window system.
        if (GLFW.glfwWindowShouldClose(window))
        {
            GLFW.glfwSetWindowShouldClose(window, false);
            return true;
        }
        return false;
    }

    public void Destroy() {
        // This will need to be revisited for a multiple window system.
        GLFW.glfwDestroyWindow(window);
    }

    public boolean IsActive() {
        return GLFW.glfwGetWindowAttrib(window, GLFW.GLFW_VISIBLE) == GLFW.GLFW_TRUE;
    }
    
    public boolean WasResized() {
        boolean resized = wasResized;
        wasResized = false;
        return resized;
    }
    
    public void UpdateTime () 
    {
        long time = System.nanoTime();
        deltaTime = (time - lastTime) / 1000000000.0f;
        lastTime = time;
        totalTime = totalTime + deltaTime;

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
    
    public double GetSecondsSinceStart()
    {
        return totalTime;
    }
    
    public void SetLastTime()
    {
        lastTime = System.nanoTime();
    }
    
    public void UpdateBuffer()
    {
        GameStateManager.game.ClearScreen();
        GameStateManager.game.DrawAll();
    }
    
    public void Update()
    {
        pauseEventPolling = false;
        
        // We always poll for events at least once.
        GLFW.glfwPollEvents();
        
        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
        quorum.Libraries.Game.DesktopConfiguration_ config = dis.config;
        
        double interval = (System.nanoTime() - lastTime) / 1000000000.0;
        double frameTarget = 1.0 / config.Get_Libraries_Game_DesktopConfiguration__targetFramesPerSecond_();
        double minimum = config.Get_Libraries_Game_DesktopConfiguration__minimumFrameDelay_();
        
        if (config.Get_Libraries_Game_DesktopConfiguration__limitFramesPerSecond_())
            while (pauseEventPolling == false && (interval < minimum || interval < frameTarget))
            {
                if (frameTarget > minimum)
                    GLFW.glfwWaitEventsTimeout(frameTarget - interval);
                else
                    GLFW.glfwWaitEventsTimeout(minimum - interval);
                interval = (System.nanoTime() - lastTime) / 1000000000.0;
            }
        
        
        GLFW.glfwSwapBuffers(window);
    }

    public ScreenResolution_ GetDesktopResolution()
    {
        Initialize();
        ScreenResolution_ res = new quorum.Libraries.Game.ScreenResolution();
        GLFWVidMode videoMode = GetOriginalResolution();
        res.Set_Libraries_Game_ScreenResolution__width_(videoMode.width());
        res.Set_Libraries_Game_ScreenResolution__height_(videoMode.height());
        res.Set_Libraries_Game_ScreenResolution__redBits_(videoMode.redBits());
        res.Set_Libraries_Game_ScreenResolution__greenBits_(videoMode.greenBits());
        res.Set_Libraries_Game_ScreenResolution__blueBits_(videoMode.blueBits());
        res.Set_Libraries_Game_ScreenResolution__frequency_(videoMode.refreshRate());
        res.Set_Libraries_Game_ScreenResolution__fullscreen_(true);
        return res;
    }

    /*
    Returns the original desktop resolution of this monitor before the game
    launched a window.
    */
    private GLFWVidMode GetOriginalResolution()
    {
        return desktopResolution != null ? 
            desktopResolution : 
            (desktopResolution = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()));
    }
    
    public void GetAvailableResolutionsNative(Array_ array)
    {
        Initialize();
        GLFWVidMode.Buffer videoModes = GLFW.glfwGetVideoModes(GLFW.glfwGetPrimaryMonitor());
        while (videoModes.hasRemaining())
        {
            GLFWVidMode mode = videoModes.get();
            ScreenResolution_ res = new quorum.Libraries.Game.ScreenResolution();
            res.Set_Libraries_Game_ScreenResolution__width_(mode.width());
            res.Set_Libraries_Game_ScreenResolution__height_(mode.height());
            res.Set_Libraries_Game_ScreenResolution__redBits_(mode.redBits());
            res.Set_Libraries_Game_ScreenResolution__greenBits_(mode.greenBits());
            res.Set_Libraries_Game_ScreenResolution__blueBits_(mode.blueBits());
            res.Set_Libraries_Game_ScreenResolution__frequency_(mode.refreshRate());
            res.Set_Libraries_Game_ScreenResolution__fullscreen_(true);
            array.Add(res);
        }
    };
    
    public void SetScreenResolution(ScreenResolution_ resolution)
    {
        // Currently the bit depths of the resolution are ignored as it isn't
        // possible to change it without destruction and recreation of the
        // window, which requires reinitializing all OpenGL assets - this is a
        // feature that we'll need for Android support later anyway.
        if (resolution.IsFullscreen())
        {
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, resolution.GetWidth(), resolution.GetHeight(), resolution.GetFrequency());
        }
        else
        {
            GLFWVidMode videoMode = GetOriginalResolution();
            int x = videoMode.width() - resolution.GetWidth();
            if (x > 0)
                x = x / 2;
            else
                x = 0;
            
            // Provide minimum space of 20 units for the top bar.
            int y = videoMode.height() - resolution.GetHeight();
            if (y > 40)
                y = y / 2;
            else
                y = 20;
            
            double scaling = GetPixelScaleFactor();
            GLFW.glfwSetWindowMonitor(window, 0, x, y, (int)(resolution.GetWidth() / scaling), (int)(resolution.GetHeight() / scaling), resolution.GetFrequency());
        }
        
        this.resolution = resolution;
    }
    
    public boolean SetDisplayMode(int width, int height, boolean fullscreen)
    {
        try
        {
            ScreenResolution res = new ScreenResolution();
            res.Set_Libraries_Game_ScreenResolution__width_(width);
            res.Set_Libraries_Game_ScreenResolution__height_(height);
            res.Set_Libraries_Game_ScreenResolution__fullscreen_(fullscreen);
            // Use default GLFW values for bits.
            res.Set_Libraries_Game_ScreenResolution__redBits_(8);
            res.Set_Libraries_Game_ScreenResolution__greenBits_(8);
            res.Set_Libraries_Game_ScreenResolution__blueBits_(8);
            // Use the refresh rate of the current desktop resolution (it's most likely the default GLFW will use).
            GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            res.Set_Libraries_Game_ScreenResolution__frequency_(videoMode.refreshRate());
            SetScreenResolution(res);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
    
    public ScreenResolution_ GetScreenResolution()
    {
        return resolution != null ? resolution : GetResolutionFromConfiguration();
    }
    
    public static int glBoolean(boolean value)
    {
        return value ? org.lwjgl.opengl.GL11.GL_TRUE : org.lwjgl.opengl.GL11.GL_FALSE;
    }
    
    // Only works on Windows platforms. This will fail on other platforms.
    public static long GetWindowsHandle()
    {
        return org.lwjgl.glfw.GLFWNativeWin32.glfwGetWin32Window(window);
    }
    
    //only works on Mac. This will fail on other platforms.
    public static long GetCocoaHandle()
    {
        return org.lwjgl.glfw.GLFWNativeCocoa.glfwGetCocoaWindow(window);
    }
    /*
    Used to retrieve or construct a screen resolution from the
    DesktopConfiguration. Used to provide a screen resolution if the display
    doesn't have one itself yet.
    */
    private ScreenResolution_ GetResolutionFromConfiguration()
    {
        Initialize();
        
        quorum.Libraries.Game.DesktopDisplay dis = (quorum.Libraries.Game.DesktopDisplay) me_;
        quorum.Libraries.Game.DesktopConfiguration_ config = dis.config;
        
        if (config.Get_Libraries_Game_DesktopConfiguration__defaultResolution_() != null)
            return config.Get_Libraries_Game_DesktopConfiguration__defaultResolution_();
        
        ScreenResolution res = new ScreenResolution();
        res.Set_Libraries_Game_ScreenResolution__width_(config.Get_Libraries_Game_DesktopConfiguration__width_());
        res.Set_Libraries_Game_ScreenResolution__height_(config.Get_Libraries_Game_DesktopConfiguration__height_());
        res.Set_Libraries_Game_ScreenResolution__fullscreen_(false);
        // Use default GLFW values for bits.
        res.Set_Libraries_Game_ScreenResolution__redBits_(8);
        res.Set_Libraries_Game_ScreenResolution__greenBits_(8);
        res.Set_Libraries_Game_ScreenResolution__blueBits_(8);
        // Use the refresh rate of the current desktop resolution (it's most likely the default GLFW will use).
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        res.Set_Libraries_Game_ScreenResolution__frequency_(videoMode.refreshRate());
        return res;
    }
    
    // Initialize GLFW, if it hasn't been initialized already.
    private void Initialize()
    {
        if (!initialized)
        {
            GLFW.glfwInit();
            initialized = true;
        }
    }
    
    public void FocusWindow()
    {
        GLFW.glfwFocusWindow(window);
    }
    
    public boolean IsWindowFocused()
    {
        return GLFW.glfwGetWindowAttrib(window, GLFW.GLFW_FOCUSED) == GLFW.GLFW_TRUE;
    }
    
    public boolean IsAvailable()
    {
        return GLFW.glfwGetCurrentContext() != 0;
    }
    
    public void MaximizeWindow()
    {
        GLFW.glfwMaximizeWindow(window);
    }
    
    public boolean IsWindowMaximized()
    {
        return GLFW.glfwGetWindowAttrib(window, GLFW.GLFW_MAXIMIZED) == GLFW.GLFW_TRUE;
    }
    
    public void MinimizeWindow()
    {
        GLFW.glfwIconifyWindow(window);
    }
    
    public boolean IsWindowMinimized()
    {
        return GLFW.glfwGetWindowAttrib(window, GLFW.GLFW_ICONIFIED) == GLFW.GLFW_TRUE;
    }
    
    public void RestoreWindow()
    {
        GLFW.glfwRestoreWindow(window);
    }
    
    public void SetWindowIcon(String fileName)
    {
        Properties prop = new Properties();
        if(prop.IsWindows()) {
            int[] channels = new int[1];
            ByteBuffer pixels = org.lwjgl.stb.STBImage.stbi_load(fileName, width, height, channels, 4);

            org.lwjgl.glfw.GLFWImage image = org.lwjgl.glfw.GLFWImage.malloc();
            image.set(width[0], height[0], pixels);

            org.lwjgl.glfw.GLFWImage.Buffer buffer = org.lwjgl.glfw.GLFWImage.malloc(1);
            buffer.put(0, image);

            //Not all platforms support this action. If they don't, just ignore the error.
            GLFW.glfwSetWindowIcon(window, buffer);
        }
    }
    
    /*
    Used to indicate that the Display should stop polling for events, if it is
    currently doing so. Typically used by the accessibility system to ensure the
    system will allow input to be fully resolved in Quorum before processing
    additional messages that could rely on that input's resolution.
    */
    public void PauseEventPolling()
    {
        pauseEventPolling = true;
    }
}
