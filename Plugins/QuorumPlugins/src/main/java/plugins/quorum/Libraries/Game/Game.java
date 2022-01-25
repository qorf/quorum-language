/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import android.app.Activity;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import android.util.Log;
//import org.robovm.apple.foundation.Foundation;
//import org.robovm.apple.foundation.NSString;
//import org.robovm.apple.uikit.UIDevice;

/**
*
* @author alleew
*/
public class Game 
{
    public java.lang.Object me_ = null;
    
    static
    {
        try 
        {
            String os = System.getProperty("os.name");
            
            String nativeFile;
            if (os.contains("Mac OS X") || os.contains("Linux"))
            {
                if (os.contains("Linux") && System.getProperty("java.runtime.name").contains("Android Runtime"))
                {
                    Log.d("Quorum Game Application", "Loading C libraries...");
                    Log.d("Quorum Game Application", "Java version is " + System.getProperty("java.version"));
                    nativeFile = null;
                    GameStateManager.operatingSystem = "Android";
                    System.loadLibrary("GameEngineCPlugins");
                    Log.d("Quorum Game Application", "Finished loading C libraries.");
                }
                else
                {
                    java.io.File file = new java.io.File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                    String runLocation = file.getParentFile().getAbsolutePath();
                    String lwjgl = runLocation + "/jni";
                    System.setProperty("org.lwjgl.librarypath", lwjgl);
                
                    nativeFile = runLocation + "/jni/libGameEngineCPlugins.so";
                }
            }
            else if (os.contains("Windows"))
            {
                URI uri = Game.class.getProtectionDomain().getCodeSource().getLocation().toURI();
                String uriPath = uri.getPath();

                if (uri.getAuthority() != null)
                    uriPath = "\\\\" + uri.getAuthority() + uriPath;

                java.io.File file = new java.io.File(uriPath);
                
                String runLocation = file.getParentFile().getAbsolutePath();
                String lwjgl = runLocation + "/jni";
                System.setProperty("org.lwjgl.librarypath", lwjgl);
                
                if (System.getProperty("os.arch").contains("x86"))
                    nativeFile = runLocation + "\\jni\\libGameEngineCPlugins32.dll";
                else
                    nativeFile = runLocation + "\\jni\\libGameEngineCPlugins64.dll";
            }
            else
            {
                IOSApplication.SetOperatingSystem();
                /*
                quorum.Libraries.System.File qFile = new quorum.Libraries.System.File();
                Foundation.log("%@", new NSString("Default directory + path: " + qFile.GetWorkingDirectory() + " + " + qFile.GetPath()));
                
                Foundation.log("%@", new NSString("Version is " + System.getProperty("os.version")));
                Foundation.log("%@", new NSString("Device name is " + UIDevice.getCurrentDevice().getName()));
                if (UIDevice.getCurrentDevice().getName().contains("Simulator"))
                    GameState.SetOperatingSystem("iOS Simulator");
                else
                    GameState.SetOperatingSystem("iOS Device");
                
                Foundation.log("%@", new NSString("Set OS as " + GameState.GetOperatingSystem()));
                */
                nativeFile = null;
            } 
            if (nativeFile != null)
            {
                GameStateManager.nativePath = nativeFile;
                System.load(nativeFile);
                GameStateManager.operatingSystem = System.getProperty("os.name");
            }
        } 
        catch (URISyntaxException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int SelectApplicationTypeNative()
    {
        String os = System.getProperty("os.name");
        String vm = System.getProperty("java.runtime.name");
        if (vm != null && vm.contains("Android Runtime"))
            return 1;
        else if (os.contains("Mac OS X") || os.contains("Windows") || os.contains("Linux"))
            return 2;
        else // If no other type is selected, it's assumed that it's iOS.
            return 3;
    }
    
}
