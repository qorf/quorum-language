/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import quorum.Libraries.Game.Game_;

import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIDevice;
import org.robovm.apple.uikit.UIUserInterfaceIdiom;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIInterfaceOrientation;

/**
 *
 * @author alleew
 */
public class IOSApplication 
{
    java.lang.Object me_ = null;
    
    // Reference to the game so we can call its actions.
    Game_ game;
    
    UIApplication uiApp;
    UIWindow uiWindow;
    //IOSViewControllerListener viewControllerListener;
    //IOSApplicationConfiguration config;
    //IOSGraphics graphics;
    //IOSAudio audio;
    //IOSFiles files;
    //IOSInput input;
    //IOSNet net;
    //int logLevel = Application.LOG_DEBUG;
    
    // The display scale factor (1.0f for normal, 2.0f to use retina coordinates/dimensions.
    float displayScaleFactor;
    
    // Reference to the delegate -- may not be necessary.
    IOSDelegate delegate;
    
    // Are these necessary?
    //Array<Runnable> runnables = new Array<Runnable>();
    //Array<Runnable> executedRunnables = new Array<Runnable>();
    //Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();
    
    public void SetupNative(Game_ game)
    {
        this.game = game;
        delegate = new IOSDelegate();
        delegate.Begin(this);
    }
    
    final boolean DidFinishLaunching(UIApplication uiApp, UIApplicationLaunchOptions options)
    {
        this.uiApp = uiApp;
        
        // Eventually, this line should use the configuration, as in the commented line below.
        //UIApplication.getSharedApplication().setIdleTimerDisabled(config.preventScreenDimming);
        UIApplication.getSharedApplication().setIdleTimerDisabled(true);
        
        //Gdx.app.debug("IOSApplication", "iOS version: " + UIDevice.getCurrentDevice().getSystemVersion());
        
        // fix the scale factor if we have a retina device (NOTE: iOS screen sizes are in "points" not pixels by default!)
        //Gdx.app.debug("IOSApplication", "Running in " + (Bro.IS_64BIT ? "64-bit" : "32-bit") + " mode");
        
        float scale = (float)(GetIOSVersion() >= 8 ? UIScreen.getMainScreen().getNativeScale() : UIScreen.getMainScreen().getScale());
        
        if (scale >= 2.0f)
        {
            //Gdx.app.debug("IOSApplication", "scale: " + scale);
            if (UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.Pad) 
            {
                // it's an iPad!
                // Below line is dependent on config.
                //displayScaleFactor = config.displayScaleLargeScreenIfRetina * scale;
            }
            else
            {
                // it's an iPod or iPhone
                // Below line is dependent on config.
                //displayScaleFactor = config.displayScaleSmallScreenIfRetina * scale;
            }
        }
        else
        {
            // No retina screen, so no scaling.
            if (UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.Pad) 
            {
                // it's an iPad!
                // Below line is dependent on config.
                //displayScaleFactor = config.displayScaleLargeScreenIfNonRetina;
            }
            else 
            {
                // it's an iPod or iPhone
                // Below line is dependent on config.
                //displayScaleFactor = config.displayScaleSmallScreenIfNonRetina;
            }
        }
        
        //GL20 gl20 = new IOSGLES20();
        //Gdx.gl = gl20;
        //Gdx.gl20 = gl20;

        // setup libgdx
        //this.input = new IOSInput(this);
        //this.graphics = new IOSGraphics(getBounds(null), scale, this, config, input, gl20);
        //this.files = new IOSFiles();
        //this.audio = new IOSAudio(config);
        //this.net = new IOSNet(this);

        //Gdx.files = this.files;
        //Gdx.graphics = this.graphics;
        //Gdx.audio = this.audio;
        //Gdx.input = this.input;
        //Gdx.net = this.net;

        //this.input.setupPeripherals();

        this.uiWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        //this.uiWindow.setRootViewController(this.graphics.viewController);
        this.uiWindow.makeKeyAndVisible();
        //Gdx.app.debug("IOSApplication", "created");
        return true;
    }
    
    private int GetIOSVersion()
    {
        String systemVersion = UIDevice.getCurrentDevice().getSystemVersion();
        int version = Integer.parseInt(systemVersion.split("\\.")[0]);
        return version;
    }
    
    public UIViewController GetUIViewController()
    {
        //return graphics.viewController;
        // TEMP:
        return null;
    }
    
    public UIWindow GetUIWindow()
    {
        return uiWindow;
    }
    
    public CGSize GetBounds(UIViewController viewController)
    {
        // or screen size (always portrait)
        CGSize bounds = UIScreen.getMainScreen().getApplicationFrame().getSize();

        // determine orientation and resulting width + height
        UIInterfaceOrientation orientation;
        if (viewController != null) 
        {
            orientation = viewController.getInterfaceOrientation();
        }
        // FIX ME: Change this when config is done.
        else if (false)//(config.orientationLandscape == config.orientationPortrait) 
        {
            /*
             * if the app has orientation in any side then we can only check status bar orientation
             */
            orientation = uiApp.getStatusBarOrientation();
        }
        // FIX ME: Change this when config is done.
        else if (false)//(config.orientationLandscape) 
        {
            // landscape is true and portrait is false
            orientation = UIInterfaceOrientation.LandscapeRight;
        } else 
        {
            // Portrait is true and landscape is false
            orientation = UIInterfaceOrientation.Portrait;
        }
        int width;
        int height;
        switch (orientation) 
        {
            case LandscapeLeft:
            case LandscapeRight:
                height = (int)bounds.getWidth();
                width = (int)bounds.getHeight();
                if (width < height) 
                {
                        width = (int)bounds.getWidth();
                        height = (int)bounds.getHeight();
                }
                break;
            default:
                // assume portrait
                width = (int)bounds.getWidth();
                height = (int)bounds.getHeight();
        }

        //Gdx.app.debug("IOSApplication", "Unscaled View: " + orientation.toString() + " " + width + "x" + height);

        // update width/height depending on display scaling selected
        width *= displayScaleFactor;
        height *= displayScaleFactor;

        // log screen dimensions
        //Gdx.app.debug("IOSApplication", "View: " + orientation.toString() + " " + width + "x" + height);

        // return resulting view size (based on orientation)
        return new CGSize(width, height);
    }
    
    public void DidBecomeActive(UIApplication uiApp)
    {
        /*
        Gdx.app.debug("IOSApplication", "resumed");
        // workaround for ObjectAL crash problem
        // see: https://groups.google.com/forum/?fromgroups=#!topic/objectal-for-iphone/ubRWltp_i1Q
        OALAudioSession.sharedInstance().forceEndInterruption();
        if (config.allowIpod) {
                OALSimpleAudio.sharedInstance().setUseHardwareIfAvailable(false);
        }
        graphics.makeCurrent();
        graphics.resume();
        */
    }
    
    public void WillEnterForeground (UIApplication uiApp) 
    {
        /*
        // workaround for ObjectAL crash problem
        // see: https://groups.google.com/forum/?fromgroups=#!topic/objectal-for-iphone/ubRWltp_i1Q
        OALAudioSession.sharedInstance().forceEndInterruption(); 
        */    
    }

    public void WillResignActive (UIApplication uiApp) 
    {
        /*
        Gdx.app.debug("IOSApplication", "paused");
        graphics.makeCurrent();
        graphics.pause();
        Gdx.gl.glFlush();
        */
    }

    public void WillTerminate (UIApplication uiApp) 
    {
        /*
        Gdx.app.debug("IOSApplication", "disposed");
        graphics.makeCurrent();
        Array<LifecycleListener> listeners = lifecycleListeners;
        synchronized (listeners) {
                for (LifecycleListener listener : listeners) {
                        listener.pause();
                }
        }
        listener.dispose();
        Gdx.gl.glFlush();
        */
    }
}
