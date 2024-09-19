/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;
import plugins.quorum.Libraries.Game.Graphics.OpenGL.OpenGLManager;
import quorum.Libraries.Game.Graphics.GraphicsManager_;
import quorum.Libraries.Game.Application_;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.GameInput_;
import quorum.Libraries.Game.Game_;
import quorum.Libraries.Game.Graphics.OpenGL.DesktopOpenGL;
import quorum.Libraries.Game.Graphics.OpenGL.IOSOpenGL;
import quorum.Libraries.Game.Graphics.OpenGL.AndroidOpenGL;
import quorum.Libraries.Game.Graphics.Fonts.FontManager;
import quorum.Libraries.Game.Graphics.Fonts.FontManager_;

/**
 * A helper class that contains static references to individual components that
 * should remain unique, such as the Application class or the Display. These
 * static variables can be referenced directly (as they are public) or using the
 * non-static setters and getters. The setters and getters must not be static
 * in order to be called correctly from the system actions of the Quorum
 * GameStateManager class.
 * 
 * 
 * @author Taylor Bockman, William Allee
 */
public class GameStateManager 
{
    public java.lang.Object me_ = null;
    public static Game_ game;
    public static Application_ application;
    public static GameDisplay_ display;
    public static GraphicsManager_ graphics;
    public static FontManager_ fonts = new FontManager();
    public static GameInput_ input;

    public static String nativePath;
    public static String operatingSystem;
    
    public static GameFileHandler  fileHandler = new LWJGLFileHandler();

    // Having direct access to the Java GraphicsManager makes the Java side much easier.
    public static OpenGLManager nativeGraphics;

    /*
    An ID for the main thread. Certain actions can only be done on the main
    thread, especially anything which directly touches OpenGL. Aside from the
    default value here, this value will be set by the Game directly (which will
    theoretically be the same value, but this offers a degree of protection if
    the GameStateManager was somehow initialized in the wrong thread first).
    */
    public static String mainThreadID = "" + Thread.currentThread().getId();
    
    static
    {
//        String os = System.getProperty("os.name");
//        operatingSystem = os;
//
//        if (os.contains("Mac OS X") || os.contains("Windows") || os.contains("Linux"))
//        {
//            if (os.contains("Linux") && System.getProperty("java.runtime.name").contains("Android Runtime"))
//            {
//                if (IsVulkanRendering())
//                {
//                    throw new RuntimeException("NYI");
//                }
//                else
//                {
//                    graphics = new AndroidOpenGL();
//                    nativeGraphics = ((AndroidOpenGL)graphics).plugin_;
//                    operatingSystem = "Linux (Android) : TEST-CODE-MCX";
//                }
//            }
//            else
//            {
//                if (IsVulkanRendering())
//                {
//                    throw new RuntimeException("NYI");
//                }
//                else
//                {
//                    graphics = new DesktopOpenGL();
//                    nativeGraphics = ((DesktopOpenGL) graphics).plugin_;
//                }
//            }
//        }
//        else if (os.contains("iOS"))
//        {
//            if (IsVulkanRendering())
//            {
//                throw new RuntimeException("NYI");
//            }
//            else
//            {
//                graphics = new IOSOpenGL();
//                nativeGraphics = ((IOSOpenGL) graphics).plugin_;
//                plugins.quorum.Libraries.Game.Graphics.OpenGL.IOSOpenGL.init();
//            }
//        }
//        else
//        {
//            System.out.println("Couldn't detect os! OS was " + os);
//            nativeGraphics = null;
//        }
    }

    public static boolean IsVulkanRendering()
    {
        // Temporary placeholder way for the GameStateManager plugin to decide whether to use OpenGL or Vulkan stuff.
        return false;
    }
    
    public Game_ GetGame()
    {
        return game;
    }
    
    public void SetGame(Game_ g)
    {
        game = g;
    }
    
    public Application_ GetApplication() 
    {
        return application;
    }

    public void SetApplication(Application_ app) 
    {
        application = app;
    }

    public GameDisplay_ GetGameDisplay() 
    {
        return display;
    }

    public void SetGameDisplay(GameDisplay_ aDisplay) 
    {
        display = aDisplay;
    }

    public GraphicsManager_ GetGameGraphics() 
    {
        return graphics;
    }

    public void SetGameGraphics(GraphicsManager_ aGameGraphics) 
    {
        graphics = aGameGraphics;
        if (IsVulkanRendering())
        {
            throw new RuntimeException("NYI");
        }
        else
        {
            if (operatingSystem.contains("Android"))
            {
                nativeGraphics = ((AndroidOpenGL) graphics).plugin_;
            }
            else if (operatingSystem.contains("Mac OS X") || operatingSystem.contains("Windows") || operatingSystem.contains("Linux"))
            {
                nativeGraphics = ((DesktopOpenGL) graphics).plugin_;
            }
            else if (operatingSystem.contains("iOS"))
            {
                nativeGraphics = ((IOSOpenGL) graphics).plugin_;
            }
        }
    }

    public void SetInput(GameInput_ gameInput)
    {
        input = gameInput;
    }
    
    public GameInput_ GetInput()
    {
        return input;
    }
    
    public void SetFontManager(FontManager_ fontManager)
    {
        fonts = fontManager;
    }
    
    public FontManager_ GetFontManager()
    {
        return fonts;
    }
    
    public void SetNativePath(String path)
    {
        nativePath = path;
        System.load(nativePath);
    }
    
    public String GetNativePath()
    {
        return nativePath;
    }
    
    public void SetOperatingSystem(String os)
    {
        operatingSystem = os;
    }
    
    public String GetOperatingSystem()
    {
        return operatingSystem;
    }
    
    public void SetMainThreadName(String name)
    {
        mainThreadID = name;
    }
    
    public String GetMainThreadName()
    {
        return mainThreadID;
    }
}
