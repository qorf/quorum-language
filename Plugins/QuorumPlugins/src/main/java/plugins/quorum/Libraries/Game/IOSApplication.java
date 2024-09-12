/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.Graphics.OpenGL.IOSOpenGL;
import quorum.Libraries.Game.Game_;
import quorum.Libraries.Game.IOSConfiguration_;
import quorum.Libraries.Game.IOSDisplay_;
import quorum.Libraries.Game.IOSDisplay;
import quorum.Libraries.Game.Graphics.IOSGraphics;

import org.robovm.apple.coregraphics.CGRect;

/**
 *
 * @author alleew
 */


public class IOSApplication
{
    public class IOSAccessibilityContainer extends UIView implements UIAccessibilityContainer
    {
        private NSMutableArray<UIAccessibilityElement> accessibilityElements = new NSMutableArray<>();
        private UIAccessibilityContainerType accessibilityContainerType = UIAccessibilityContainerType.SemanticGroup;

        @Override
        public NSMutableArray<UIAccessibilityElement> getAccessibilityElements() {
            return accessibilityElements;
        }

        @Override
        //WARNING: Do not actually set to NSArray<UIAccessibilityElement> as it will crash the program.
        // it has to be NSMutableArray<UIAccessibilityElement>
        public void setAccessibilityElements(NSArray<UIAccessibilityElement> v) {
            accessibilityElements = (NSMutableArray<UIAccessibilityElement>) v;
        }

        @Override
        public UIAccessibilityContainerType getAccessibilityContainerType() {
            return accessibilityContainerType;
        }

        @Override
        public void setAccessibilityContainerType(UIAccessibilityContainerType v) {
            accessibilityContainerType = v;
        }

        @Override
        public long getAccessibilityElementCount() {
            return accessibilityElements.size();
        }

        @Override
        public UIAccessibilityElement getAccessibilityElement(long index) {
            return accessibilityElements.get((int)index);
        }

        @Override
        public long indexOfAccessibilityElement(UIAccessibilityElement element) {
            return accessibilityElements.indexOf(element);
        }
    }
    static final String SIMULATOR_SEARCH = "CoreSimulator";
    static final String SIMULATOR_PROPERTY = "IOS-Simulator";
    static {
        String property = System.getProperty("user.dir");
        if(property != null && property.contains(SIMULATOR_SEARCH)) {
            System.setProperty(SIMULATOR_PROPERTY, "true");
        } else {
            System.setProperty(SIMULATOR_PROPERTY, "false");
        }
    }
    public java.lang.Object me_ = null;

    // Reference to the game so we can call its actions.
    public Game_ game;
    public static String name = "";

    UIApplication uiApp;
    UIWindow uiWindow;
    //IOSViewControllerListener viewControllerListener;
    IOSConfiguration_ config;
    IOSDisplay_ display;
    public static IOSAccessibilityContainer accessibilityContainer;
    //IOSFiles files;
    //IOSInput input;
    //IOSNet net;
    //int logLevel = Application.LOG_DEBUG;

    // The display scale factor (1.0f for normal, 2.0f to use retina coordinates/dimensions.
    float displayScaleFactor;
    public CGRect screenBounds;
    public static CGRect accessibilityContainerBounds;
    public static int containerScaleFactorWidth;
    public static int containerScaleFactorHeight;

    // Reference to the delegate -- may not be necessary.
    IOSDelegate delegate;

    private CGRect lastScreenBounds = null;

    // Are these necessary?
    //Array<Runnable> runnables = new Array<Runnable>();
    //Array<Runnable> executedRunnables = new Array<Runnable>();
    //Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();

    public static void SetOperatingSystem()
    {
        if (IsSimulator()) {
            GameStateManager.operatingSystem = "iOS Simulator";
        }
        else {
            GameStateManager.operatingSystem = "iOS Device";
        }
    }

    public static boolean IsSimulator() {
        String s = System.getProperty(SIMULATOR_PROPERTY);
        if (s != null && s.contains("true")) {
            return true;
        }
        return false;
    }

    public static boolean IsDevice() {
        return !IsSimulator();
    }

    public void SetupNative(Game_ game)
    {
        this.game = game;
        name = game.GetGameName();

        // Make the default working directory more useful if we're not on a simulator.
        if (IsSimulator())
        {
            plugins.quorum.Libraries.System.QuorumFile qFile = new plugins.quorum.Libraries.System.QuorumFile();
            qFile.defaultWorkingDirectory = NSBundle.getMainBundle().getBundlePath();
        }
        System.out.println("IOSApplication: SetupNative: NSBundle.getMainBundle().getBundlePath() = " + NSBundle.getMainBundle().getBundlePath());

        delegate = new IOSDelegate();
        delegate.Begin(this);
        System.out.println("IOSApplication: SetupNative: delegate.Begin(this) called.");
    }

    final boolean DidFinishLaunching(UIApplication uiApp, UIApplicationLaunchOptions options)
    {
        display = (IOSDisplay_)GameStateManager.display;
        config = (IOSConfiguration_)display.GetConfiguration();

        this.uiApp = uiApp;

        UIApplication.getSharedApplication().setIdleTimerDisabled(config.Get_Libraries_Game_IOSConfiguration__preventScreenDimming_());

        float scale = (float)(GetIOSVersion() >= 8 ? UIScreen.getMainScreen().getNativeScale() : UIScreen.getMainScreen().getScale());
        
        /*
        Scaling values are used as divisors of 1 in order to "flip" the scaling
        value about 1. The "NOTE ABOUT SCALING" comment in
        IOSConfiguration.quorum has been copied here for convenience:
        
        NOTE ON SCALING:
        libGDX goes the opposite way with scale, where a smaller scale value will
        make objects larger on the screen (the logic being you are scaling down the
        screen size). In our code, we make a larger scaling value cause items to be
        drawn larger on the screen, thus reducing the effective screen size, in
        order to maintain consistency with how scaling works with Drawables.
        */

        if (scale >= 2.0f)
        {
            if (UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.Pad)
            {
                // it's an iPad!
                displayScaleFactor = (float)(1 / config.Get_Libraries_Game_IOSConfiguration__largeRetinaDisplayScale_()) * scale;
            }
            else
            {
                // it's an iPod or iPhone
                displayScaleFactor = (float)(1 / config.Get_Libraries_Game_IOSConfiguration__smallRetinaDisplayScale_()) * scale;
            }
        }
        else
        {
            // No retina screen, so no scaling.
            if (UIDevice.getCurrentDevice().getUserInterfaceIdiom() == UIUserInterfaceIdiom.Pad)
            {
                // it's an iPad!
                displayScaleFactor = (float)(1 / config.Get_Libraries_Game_IOSConfiguration__largeNonRetinaDisplayScale_());
            }
            else
            {
                // it's an iPod or iPhone
                displayScaleFactor = (float)(1 / config.Get_Libraries_Game_IOSConfiguration__smallNonRetinaDisplayScale_());
            }
        }

        IOSOpenGL.init();

        IOSInput input = ((quorum.Libraries.Game.IOSInput)GameStateManager.input).plugin_;
        input.Initialize(this);
        ((IOSDisplay)display).plugin_.Initialize(scale, this, config, input, ((IOSGraphics)GameStateManager.graphics).plugin_);

        this.uiWindow = new UIWindow(UIScreen.getMainScreen().getBounds());
        this.uiWindow.setRootViewController(((IOSDisplay)display).plugin_.viewController);
        this.uiWindow.makeKeyAndVisible();
        accessibilityContainer = new IOSAccessibilityContainer();
        accessibilityContainerBounds = UIScreen.getMainScreen().getBounds();
        screenBounds = GetBounds();
        containerScaleFactorWidth = (int)(screenBounds.getWidth() / accessibilityContainerBounds.getWidth());
        containerScaleFactorHeight = (int)(screenBounds.getHeight() / accessibilityContainerBounds.getHeight());
        accessibilityContainer.setFrame(UIScreen.getMainScreen().getBounds());
        accessibilityContainer.setHidden(false);

        accessibilityContainer.setAccessibilityIdentifier(name);

        this.uiWindow.getRootViewController().getView().addSubview(accessibilityContainer);

        //hidden text field for the keyboard
        UITextField field = input.getKeyboardTextField();
        this.uiWindow.getRootViewController().getView().addSubview(field);

        System.out.println("IOSApplication: DidFinishLaunching: this.uiWindow.makeKeyAndVisible() called.32323");
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
        UIWindow ui = GetUIWindow();
        return ui.getRootViewController();
    }

    public UIWindow GetUIWindow()
    {
        return uiWindow;
    }

    public CGRect GetBounds()
    {
        // or screen size (always portrait)
        final CGRect screenBounds = UIScreen.getMainScreen().getBounds();
        final CGRect statusBarFrame = uiApp.getStatusBarFrame();
        final UIInterfaceOrientation statusBarOrientation = uiApp.getStatusBarOrientation();

        double statusBarHeight = Math.min(statusBarFrame.getWidth(), statusBarFrame.getHeight());

        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Make sure that the orientation is consistent with ratios. Should be, but may not be on older iOS versions
        switch (statusBarOrientation)
        {
            case LandscapeLeft:
            case LandscapeRight:
                if (screenHeight > screenWidth)
                {
                    // Swap the reported screen height and screen width.
                    double tmp = screenHeight;
                    screenHeight = screenWidth;
                    screenWidth = tmp;
                }
        }

        // update width/height depending on display scaling selected
        screenWidth *= displayScaleFactor;
        screenHeight *= displayScaleFactor;

        if (statusBarHeight != 0.0)
        {
            //debug("IOSApplication", "Status bar is visible (height = " + statusBarHeight + ")");
            statusBarHeight *= displayScaleFactor;
            screenHeight -= statusBarHeight;
        }
        else
        {
            //debug("IOSApplication", "Status bar is not visible");
        }

        return lastScreenBounds = new CGRect(0.0, statusBarHeight, screenWidth, screenHeight);
    }

    public CGRect GetCachedBounds()
    {
        if(lastScreenBounds == null)
            return GetBounds();
        else
            return lastScreenBounds;
    }

    public void DidBecomeActive(UIApplication uiApp)
    {
        ((IOSDisplay)display).plugin_.MakeCurrent();
        ((IOSDisplay)display).plugin_.Resume();
    }

    public void WillEnterForeground (UIApplication uiApp)
    {
    }

    public void WillResignActive (UIApplication uiApp)
    {
        ((IOSDisplay)display).plugin_.MakeCurrent();
        ((IOSDisplay)display).plugin_.Pause();
        ((IOSGraphics)GameStateManager.graphics).plugin_.glFlush();
    }

    public void WillTerminate (UIApplication uiApp)
    {
        ((IOSDisplay)display).plugin_.MakeCurrent();
        ((IOSGraphics)GameStateManager.graphics).plugin_.glFlush();
    }

    /*
    This action returns the location of the application bundle on the iOS
    device. If the program is currently running on the simulator, this will
    return the default working directory of a Quorum File instead.
    */
    public String GetApplicationLocation()
    {
        if (!IsRunningOnSimulator())
        {
            return NSBundle.getMainBundle().getBundlePath();
        }
        else
        {
            return System.getProperty("user.dir");
        }
    }

    /*
    This action will return whether or not the device is currently running on an
    iOS simulator.
    */
    public boolean IsRunningOnSimulator()
    {
        if (IsSimulator())
            return true;
        else
            return false;
    }

    /*
    This action will log the given line of text in this device's logs.
    */
    public void Log(String info)
    {
        GlobalLog(info);
    }

    public static void GlobalLog(String info)
    {
        Foundation.log("%@", new NSString(info));
    }
}
