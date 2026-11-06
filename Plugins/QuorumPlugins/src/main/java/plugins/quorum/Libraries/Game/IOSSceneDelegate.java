package plugins.quorum.Libraries.Game;

import org.robovm.apple.cloudkit.CKShareMetadata;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSSet;
import org.robovm.apple.foundation.NSUserActivity;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationShortcutItem;
import org.robovm.apple.uikit.UICoordinateSpace;
import org.robovm.apple.uikit.UIInterfaceOrientation;
import org.robovm.apple.uikit.UIOpenURLContext;
import org.robovm.apple.uikit.UIResponder;
import org.robovm.apple.uikit.UIScene;
import org.robovm.apple.uikit.UISceneConnectionOptions;
import org.robovm.apple.uikit.UISceneSession;
import org.robovm.apple.uikit.UITraitCollection;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.apple.uikit.UIWindowScene;
import org.robovm.apple.uikit.UIWindowSceneDelegate;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.NotImplemented;
import org.robovm.objc.block.VoidBooleanBlock;

/**
 * The scene delegate for Quorum's iOS window. Since iOS 27, apps built with the
 * latest SDK must use the scene life cycle or they won't launch (Apple's
 * TN3187), so the window is made here, when iOS connects the scene, rather
 * than in IOSDelegate's didFinishLaunching.
 *
 * iOS also sends the active, background and foreground events here instead of
 * to the app delegate, so they are passed on to IOSApplication.
 *
 * Its Objective-C name, QuorumSceneDelegate, is the UISceneDelegateClassName in
 * the app's Info.plist, which Quorum Studio writes.
 */
@CustomClass("QuorumSceneDelegate")
public class IOSSceneDelegate extends UIResponder implements UIWindowSceneDelegate
{
    private UIWindow window;

    @Override
    public void willConnect(UIScene scene, UISceneSession session, UISceneConnectionOptions connectionOptions)
    {
        if (scene instanceof UIWindowScene && IOSDelegate.app != null)
        {
            window = IOSDelegate.app.CreateWindow((UIWindowScene)scene);
        }
    }

    @Override
    public void sceneDidBecomeActive(UIScene scene)
    {
        if (IOSDelegate.app != null)
            IOSDelegate.app.DidBecomeActive(UIApplication.getSharedApplication());
    }

    @Override
    public void sceneWillResignActive(UIScene scene)
    {
        if (IOSDelegate.app != null)
            IOSDelegate.app.WillResignActive(UIApplication.getSharedApplication());
    }

    @Override
    public void sceneWillEnterForeground(UIScene scene)
    {
        if (IOSDelegate.app != null)
            IOSDelegate.app.WillEnterForeground(UIApplication.getSharedApplication());
    }

    @Override
    public UIWindow getWindow()
    {
        return window;
    }

    @Override
    public void setWindow(UIWindow window)
    {
        this.window = window;
    }

    // The rest of the protocol isn't used. NotImplemented keeps RoboVM from
    // telling iOS that this class handles these messages.

    @NotImplemented("sceneDidDisconnect:")
    public void sceneDidDisconnect(UIScene scene) {}

    @NotImplemented("sceneDidEnterBackground:")
    public void sceneDidEnterBackground(UIScene scene) {}

    @NotImplemented("scene:openURLContexts:")
    public void openURLContexts(UIScene scene, NSSet<UIOpenURLContext> contexts) {}

    @NotImplemented("stateRestorationActivityForScene:")
    public NSUserActivity stateRestorationActivityForScene(UIScene scene) { return null; }

    @NotImplemented("scene:restoreInteractionStateWithUserActivity:")
    public void restoreInteractionState(UIScene scene, NSUserActivity activity) {}

    @NotImplemented("scene:willContinueUserActivityWithType:")
    public void willContinueUserActivity(UIScene scene, String userActivityType) {}

    @NotImplemented("scene:continueUserActivity:")
    public void continueUserActivity(UIScene scene, NSUserActivity userActivity) {}

    @NotImplemented("scene:didFailToContinueUserActivityWithType:error:")
    public void didFailToContinueUserActivity(UIScene scene, String userActivityType, NSError error) {}

    @NotImplemented("scene:didUpdateUserActivity:")
    public void didUpdateUserActivity(UIScene scene, NSUserActivity userActivity) {}

    @NotImplemented("windowScene:didUpdateCoordinateSpace:interfaceOrientation:traitCollection:")
    public void didUpdateCoordinateSpace(UIWindowScene windowScene, UICoordinateSpace previousCoordinateSpace, UIInterfaceOrientation previousInterfaceOrientation, UITraitCollection previousTraitCollection) {}

    @NotImplemented("windowScene:performActionForShortcutItem:completionHandler:")
    public void performAction(UIWindowScene windowScene, UIApplicationShortcutItem shortcutItem, VoidBooleanBlock completionHandler) {}

    @NotImplemented("windowScene:userDidAcceptCloudKitShareWithMetadata:")
    public void userDidAcceptCloudKitShare(UIWindowScene windowScene, CKShareMetadata cloudKitShareMetadata) {}
}
