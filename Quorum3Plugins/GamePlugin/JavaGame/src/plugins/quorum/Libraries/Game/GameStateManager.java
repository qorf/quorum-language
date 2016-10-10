/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;
import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;
import quorum.Libraries.Game.Graphics.GraphicsManager_;
import quorum.Libraries.Game.Application_;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.GameInput_;
import quorum.Libraries.Game.Graphics.DesktopGraphics;
import quorum.Libraries.Game.Graphics.IOSGraphics;

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
    public static Application_ application;
    public static GameDisplay_ display;
    public static GraphicsManager_ graphics;
    public static GameInput_ input;

    public static String nativePath;
    public static String operatingSystem;
    
    public static GameFileHandler  fileHandler = new LWJGLFileHandler();

    // Having direct access to the Java GraphicsManager makes the Java side much easier.
    public static GraphicsManager nativeGraphics;
    
    static
    {
        String os = System.getProperty("os.name");
        operatingSystem = os;
        
        if (os.contains("Mac OS X") || os.contains("Windows") || os.contains("Linux"))
        {
            graphics = new DesktopGraphics();
            nativeGraphics = ((DesktopGraphics)graphics).plugin_;
        }
        else if (os.contains("iOS"))
        {
            graphics = new IOSGraphics();
            nativeGraphics = ((IOSGraphics)graphics).plugin_;
            plugins.quorum.Libraries.Game.Graphics.IOSGraphics.init();
        }
        else
        {
            System.out.println("Couldn't detect os! OS was " + os);
            nativeGraphics = null;
        }
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
        
        if (operatingSystem.contains("Mac OS X") || operatingSystem.contains("Windows") || operatingSystem.contains("Linux"))
        {
            nativeGraphics = ((DesktopGraphics)graphics).plugin_;
        }
        else if (operatingSystem.contains("iOS"))
        {
            nativeGraphics = ((IOSGraphics)graphics).plugin_;
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
  
}
