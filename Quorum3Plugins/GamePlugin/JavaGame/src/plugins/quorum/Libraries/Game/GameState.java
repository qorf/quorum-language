/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Game;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Util;
import quorum.Libraries.Game.Graphics.GraphicsManager_;
import quorum.Libraries.Game.Application_;
import quorum.Libraries.Game.GameDisplay_;
import quorum.Libraries.Game.InputMonitor_;

import plugins.quorum.Libraries.Game.Graphics.GraphicsManager;

/**
 *
 * @author Taylor Bockman
 * 
 * Modeled after the GDX class of Libgdx, this class maintains the current
 * state of the game in terms of Application, input, etc instances.
 * 
 * The way this class works is it stores the Quorum instances for us on the
 * Java side for convenient referencing.
 * 
 * This class is only touched through quorum by GameStateManager
 */
public class GameState {
    public java.lang.Object me_ = null;
    private static Application_ app;
    private static GameDisplay_ display;
    private static GraphicsManager_ GameGraphics;
    private static GraphicsManager_ GameGraphics20Manager;

    private static String nativePath;
    private static String operatingSystem;

    // Having direct access to the Java GraphicsManager makes the Java side much easier.
    public static final GraphicsManager nativeGraphics = new GraphicsManager();

    // This is never accessed directly through Quorum. It is only accessed through
    // Java. Thus it is place here instead of in GameStateManager.
    // As a temporary hack, this is initialized in GameStateManager:SetApplication.
    public static GameFileHandler fileHandler;

    //Getters/setters so GameState can be used properly from Quorum.

    /**
     * @return the app
     */
    public static Application_ GetApp() {
      return app;
    }

    /**
     * @param aApp the app to set
     */
    public static void SetApp(Application_ aApp) {
      app = aApp;
    }

    /**
     * @return the display
     */
    public static GameDisplay_ GetDisplay() {
      return display;
    }

    /**
     * @param aDisplay the display to set
     */
    public static void SetDisplay(GameDisplay_ aDisplay) {
      display = aDisplay;
    }

    /**
     * @return the GameGraphics
     */
    public static GraphicsManager_ GetGameGraphics() {
      return GameGraphics;
    }

    /**
     * @param aGameGraphics the GameGraphics to set
     */
    public static void SetGameGraphics(GraphicsManager_ aGameGraphics) {
      GameGraphics = aGameGraphics;
    }

    /**
     * @return the GameGraphics20Manager
     */
    public static GraphicsManager_ GetGameGraphics20Manager() {
      return GameGraphics20Manager;
    }

    /**
     * @param aGameGraphics20Manager the GameGraphics20Manager to set
     */
    public static void SetGameGraphics20Manager(GraphicsManager_ aGameGraphics20Manager) {
      GameGraphics20Manager = aGameGraphics20Manager;
    }
  
    public static void SetNativePath(String path)
    {
        nativePath = path;
        System.load(GameState.GetNativePath());
    }
    
    public static String GetNativePath()
    {
        return nativePath;
    }
    
    public static void SetOperatingSystem(String os)
    {
        operatingSystem = os;
    }
    
    public static String GetOperatingSystem()
    {
        return operatingSystem;
    }
  
    // FOR DEBUGGING PURPOSES ONLY
    public static void CheckForErrors()
    {
        int x;
        x = GL11.glGetError();
        System.out.println(Util.translateGLErrorString(x));
    }
  
    // FOR DEBUGGING PURPOSES ONLY
    public static void CheckForErrors(String text)
    {
        int x;
        x = GL11.glGetError();
        System.out.println(text + Util.translateGLErrorString(x));
    }
}
