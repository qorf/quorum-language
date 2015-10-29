/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alleew
 */
public class Game 
{
    static
    {
        try 
        {
            String os = System.getProperty("os.name");
            
            java.io.File file = new java.io.File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String runLocation = file.getParentFile().getAbsolutePath();
            String lwjgl = runLocation + "/jni";
            System.setProperty("org.lwjgl.librarypath", lwjgl);
            
            String nativeFile;
            if (os.contains("Mac OS X") || os.contains("Linux"))
                nativeFile = runLocation + "/jni/libGameEngineCPlugins.so";
            else if (os.contains("Windows"))
                if (System.getProperty("os.arch").contains("x86"))
                    nativeFile = runLocation + "\\jni\\libGameEngineCPlugins32.dll";
                else
                    nativeFile = runLocation + "\\jni\\libGameEngineCPlugins64.dll";
            else
                //throw new GameRuntimeError("This operating system is not supported for games!");
                nativeFile = null;
                
            if (nativeFile != null)
                GameState.SetNativePath(nativeFile);
            GameState.SetOperatingSystem(System.getProperty("os.name"));
        } 
        catch (URISyntaxException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public java.lang.Object me_ = null;
    
    public double GetSecondsBetweenFrames()
    {
        return GameState.GetDisplay().GetSecondsBetweenFrames();
    }
    
    public int SelectApplicationTypeNative()
    {
        String os = System.getProperty("os.name");
        if (os.contains("Mac OS X") || os.contains("Windows") || os.contains("Linux"))
            return 1;
        if (os.contains("iOS"))
            ;//return new iOSApplication();
        
        // Indicate failure to find appropriate application with -1.
        return -1;
    }
    
}
