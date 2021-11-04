/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegateAdapter;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;

/**
 *
 * @author alleew
 */
public class IOSDelegate extends UIApplicationDelegateAdapter
{
    // This is static to ensure that only app can exist and all delegates 
    // (if there are multiple) have access to the same one.
    private static IOSApplication app;
    
    public void Begin(IOSApplication application)
    {
        app = application;
        
        // Making the (possibly incorrect) assumption that it is safe to pass an empty array of Strings for args.
        String[] args = new String[0];
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, IOSDelegate.class);
        pool.close();
    }
    
    @Override
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions)
    {
        //application.addStrongRef(this);
        return app.DidFinishLaunching(application, launchOptions);
    }
    
    @Override
    public void didBecomeActive(UIApplication application)
    {
        app.DidBecomeActive(application);
    }
    
    @Override
    public void willEnterForeground(UIApplication application)
    {
        app.WillEnterForeground(application);
    }
    
    @Override
    public void willResignActive(UIApplication application)
    {
        app.WillResignActive(application);
    }
    
    @Override
    public void willTerminate(UIApplication application)
    {
        app.WillTerminate(application);
    }
}
