package plugins.quorum.Libraries.Game;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.IOException;
import java.io.InputStream;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

import quorum.Libraries.Game.AndroidDisplay;
import quorum.Libraries.Game.Game_;
import quorum.Libraries.Game.ApplicationConfiguration_;
import quorum.Libraries.Game.AndroidConfiguration;
import quorum.Libraries.Game.AndroidApplication_;
import quorum.Libraries.Game.AndroidDisplay_;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import quorum.Libraries.Game.AndroidConfiguration_;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.System.File_;

import static android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.*;

/**
 *
 * @author alleew
 */

public class AndroidApplication
{
    public java.lang.Object me_ = null;
    
    static final int MINIMUM_SDK = 21;
    
    public static Activity androidActivity = null;
    private static boolean initialized = false;
    
    public AndroidApplication_ quorumApp;
    
    protected AndroidDisplay_ display;
    //protected AndroidInput input;
    //protected AndroidAudio audio;
    //protected AndroidFiles files;
    //protected AndroidNet net;
    protected Game_ game;
    public Handler handler;
    protected boolean firstResume = true;
    //protected final Array<Runnable> runnables = new Array<Runnable>();
    //protected final Array<Runnable> executedRunnables = new Array<Runnable>();
    //protected final Array<LifecycleListener> lifecycleListeners = new Array<LifecycleListener>();
    //private final Array<AndroidEventListener> androidEventListeners = new Array<AndroidEventListener>();
    //protected int logLevel = LOG_INFO;
    protected boolean useImmersiveMode = false;
    protected boolean hideStatusBar = false;
    private int wasFocusChanged = -1;
    private boolean isWaitingForAudio = false;
    private boolean hasBeenOriented = false;
    public static AccessibilityManager accessibilityManager = null;
    public static AccessibilityEvent accessibilityEvent = null;
    public static AccessibilityNodeProvider accessibilityNodeProvider = null;
    public static View viewRoot = null;
    public static final Map<Integer, Item_> quorumItems = new HashMap<Integer, Item_>();;
    public static int screenHeight;
    public static int screenWidth;

    public void SetupNative(Game_ game, ApplicationConfiguration_ configuration)
    {
        initialized = true;
        
        AndroidConfiguration config = (AndroidConfiguration)configuration;
        /* 
        SetupNative is only called as part of Setup in the Quorum 
        AndroidApplication, which sets itself in the GameStateManager right
        before calling this method. It also sets an AndroidDisplay in the
        GameStateManager.
        */
        quorumApp = (AndroidApplication_)GameStateManager.application;
        display = (AndroidDisplay_)GameStateManager.display;
        accessibilityManager = (AccessibilityManager) GetContext().getSystemService(Context.ACCESSIBILITY_SERVICE);
        accessibilityEvent = AccessibilityEvent.obtain();
        if (GetVersion() < MINIMUM_SDK) 
        {
            throw new GameRuntimeError("Android API level " + GetVersion() + " was detected, but " + MINIMUM_SDK + " or later is required.");
        }
        ((quorum.Libraries.Game.AndroidDisplay)display).plugin_.Initialize(this, config);
        //input = AndroidInputFactory.newAndroidInput(this, this, graphics.view, config);
        //audio = new AndroidAudio(this, config);
        //this.getFilesDir(); // workaround for Android bug #10515463
        //files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
        //net = new AndroidNet(this);
        this.game = game;
        this.handler = new Handler();
        this.useImmersiveMode = config.useImmersiveMode;
        this.hideStatusBar = config.hideStatusBar;

        // Add a specialized audio lifecycle listener
        /*
        addLifecycleListener(new LifecycleListener() {

                @Override
                public void resume () {
                        // No need to resume audio here
                }

                @Override
                public void pause () {
                        audio.pause();
                }

                @Override
                public void dispose () {
                        audio.dispose();
                }
        });*/

        /*
        Gdx.input = this.getInput();
        Gdx.audio = this.getAudio();
        Gdx.files = this.getFiles();
        Gdx.graphics = this.getGraphics();
        Gdx.net = this.getNet();
        */
        
        //if (!isForView) {
        try 
        {
            androidActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception ex) 
        {
            Log.d("AndroidApplication", "Content already displayed, cannot request FEATURE_NO_TITLE", ex);
        }
        androidActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        androidActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //setContentView(graphics.getView(), createLayoutParams());
        androidActivity.setContentView(((AndroidDisplay)GameStateManager.display).plugin_.GetView());
        //}

        CreateWakeLock(config.preventScreenDimming);
        HideStatusBar(this.hideStatusBar);
        UseImmersiveMode(this.useImmersiveMode);

        viewRoot = androidActivity.getWindow().getDecorView();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        androidActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        accessibilityNodeProvider = new AccessibilityNodeProvider() {
            @Override
            public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
                AccessibilityNodeInfo info = null;
                if (virtualViewId == View.NO_ID) {
                    info = AccessibilityNodeInfo.obtain(AndroidApplication.viewRoot);
                    // Add the virtual children of the root View.
                    for (Map.Entry<Integer, Item_> set : AndroidApplication.quorumItems.entrySet())
                    {
                        Item_ item = set.getValue();
                        info.addChild(AndroidApplication.viewRoot, item.GetHashCode());
                    }
                } else {
                    // Find the view that corresponds to the given id.
                    Item_ item = quorumItems.get(virtualViewId);
                    if (item == null) {
                        return null;
                    }
                    // Obtain and initialize an AccessibilityNodeInfo with
                    // information about the virtual view.
                    info = AccessibilityNodeInfo.obtain();
                    info.setPackageName(androidActivity.getPackageName());
                    info.setClassName(item.GetName());
                    info.setSource(viewRoot, virtualViewId);
                    info.setParent(viewRoot);
                    info.setText(item.GetDescription());
                    info.addAction(ACTION_SELECT);
                    info.addAction(ACTION_CLEAR_SELECTION);
                    info.setFocusable(true);
                    info.setVisibleToUser(true);

                    Rect bounds;
                    if (item instanceof Item2D_)
                    {
                        double itemX = ((Item2D_)item).GetScreenX();
                        double itemY = (((Item2D_) item).GetScreenY());

                        if (itemX == Double.NaN)
                            itemX = 0;

                        if (itemY == Double.NaN)
                            itemY = 0;

                        int left = (int)itemX;
                        int top = AndroidApplication.screenHeight - (int)(itemY + ((Item2D_) item).GetHeight());
                        int right = (int) (itemX + ((Item2D_) item).GetWidth());
                        int bottom = AndroidApplication.screenHeight - (int)(itemY);
                        bounds = new Rect(left, top, right, bottom);
                    }
                    else if (item instanceof Item3D_)
                    {
                        // This is only a place holder, to place a small box roughly at the
                        // center of a 3D object in the screen. To calculate this correctly,
                        // check how we calculate mouse input detection for 3D objects.

                        Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();

                        int left = (int)rectangle.GetX();
                        int top = AndroidApplication.screenHeight - (int)(rectangle.GetY() + rectangle.GetHeight());
                        int right = (int) (rectangle.GetX() + rectangle.GetWidth());
                        int bottom = AndroidApplication.screenHeight - (int)(rectangle.GetY());
                        bounds = new Rect(left, top, right, bottom);
                    }
                    else
                    {
                        int left = 0;
                        int top = 0;
                        int right = 0;
                        int bottom = 0;
                        bounds = new Rect(left, top, right, bottom);
                    }


                    info.setBoundsInScreen(bounds);
                }
                return info;
            }
        };

        /*
        if (this.useImmersiveMode && getVersion() >= Build.VERSION_CODES.KITKAT) {
                try {
                        Class<?> vlistener = Class.forName("com.badlogic.gdx.backends.android.AndroidVisibilityListener");
                        Object o = vlistener.newInstance();
                        Method method = vlistener.getDeclaredMethod("createListener", AndroidApplicationBase.class);
                        method.invoke(o, this);
                } catch (Exception e) {
                        log("AndroidApplication", "Failed to create AndroidVisibilityListener", e);
                }
        }
        */

        /*AndroidDisplay displayFullObject = (AndroidDisplay)GameStateManager.display;
        plugins.quorum.Libraries.Game.AndroidDisplay displayPlugin = displayFullObject.plugin_;
        View view = displayPlugin.GetView();*/
    }
    
    public boolean RequiresOrientationChange(AndroidConfiguration_ config)
    {
        boolean required = true;
        
        if (config.Get_Libraries_Game_AndroidConfiguration__defaultOrientation_() == config.Get_Libraries_Game_AndroidConfiguration__LANDSCAPE_())
        {
            if (androidActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                required = false;
            }
        }
        else if (config.Get_Libraries_Game_AndroidConfiguration__defaultOrientation_() == config.Get_Libraries_Game_AndroidConfiguration__PORTRAIT_())
            if (androidActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            {
                required = false;
            }        
        
        /* 
        If the AndroidApplication hasn't been oriented yet, orient it once to 
        prevent it from changing orientation. We only do this if the orientation 
        is currently correct (if it is currently incorrect, the Game will 
        already manually reset the orientation itself).
        */
        if (hasBeenOriented == false && required == false)
        {
            ResetOrientationToDefault(config);
        }
        
        return required;
    }
    
    public void ResetOrientationToDefault(AndroidConfiguration_ config)
    {
        if (config.Get_Libraries_Game_AndroidConfiguration__defaultOrientation_() == config.Get_Libraries_Game_AndroidConfiguration__LANDSCAPE_())
        {
            androidActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else if (config.Get_Libraries_Game_AndroidConfiguration__defaultOrientation_() == config.Get_Libraries_Game_AndroidConfiguration__PORTRAIT_())
        {
            androidActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        
        hasBeenOriented = true;
    }
    
    public void CreateWakeLock(boolean lock)
    {
        if (lock)
        {
            androidActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
    
    public void HideStatusBar(boolean hide)
    {
        if (!hide || GetVersion() < 11)
            return;
        View rootView = androidActivity.getWindow().getDecorView();
        
        try
        {
            Method m = View.class.getMethod("setSystemUiVisibility", int.class);
            if (GetVersion() <= 13)
                m.invoke(rootView, 0x0);
            m.invoke(rootView, 0x1);
        }
        catch (Exception e)
        {
            //log("AndroidApplication", "Can't hide status bar", e);
        }
    }
    
    public void onWindowFocusChanged(boolean hasFocus)
    {
        UseImmersiveMode(this.useImmersiveMode);
        HideStatusBar(this.hideStatusBar);
        if (hasFocus)
        {
            this.wasFocusChanged = 1;
            if (this.isWaitingForAudio)
            {
                //this.audio.resume();
                this.isWaitingForAudio = false;
            }
        }
        else
        {
            this.wasFocusChanged = 0;
        }
    }
    
    public void UseImmersiveMode(boolean mode)
    {
        if (!mode || GetVersion() < Build.VERSION_CODES.KITKAT)
            return;
        
        View view = androidActivity.getWindow().getDecorView();
        try
        {
            Method m = View.class.getMethod("setSystemUiVisibility", int.class);
            int code = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            m.invoke(view, code);
        }
        catch (Exception e)
        {
            //log("AndroidApplication", "Can't set immersive mode", e);
        }
    }
    
    public void onPause()
    {
        //boolean isContinuous = graphics.isContinuousRendering();
        //boolean isContinuousEnforced = AndroidGraphics.enforceContinuousRendering;
        
        // From here, we don't want non-continuous rendering.
        //AndroidGraphics.enforceContinuousRendering = true;
        //graphics.setContinuousRendering(true);
        
        // Calls to setContinuousRendering(false) from other thread (ex: GLThread)
        // will be ignored at this point...
        // graphics.pause();
        
        //input.onPause();
        
        if (androidActivity.isFinishing())
        {
            //graphics.clearManagedCaches();
            //graphics.destroy();
        }
        
        //AndroidGraphics.enforceContinuousRendering = isContinuousEnforced;
        //graphics.setContinuousRendering(isContinuous);
        
        //graphics.onPauseGLSurfaceView();
    }
    
    public void onResume()
    {
        GameStateManager.application = quorumApp;
        //Gdx.input = this.getInput();
        //Gdx.audio = this.getAudio();
        //Gdx.files = this.getFiles();
        //Gdx.graphics = this.getGraphics();
        //Gdx.net = this.getNet();

        //input.onResume();

        //if (graphics != null) {
        //        graphics.onResumeGLSurfaceView();
        //}

        if (!firstResume) 
        {
            //graphics.resume();
        } 
        else
            firstResume = false;

        this.isWaitingForAudio = true;
        if (this.wasFocusChanged == 1 || this.wasFocusChanged == -1) 
        {
            //this.audio.resume();
            this.isWaitingForAudio = false;
        }
    }
    
    public void onDestroy()
    {

    }
    
    public Game_ GetGame()
    {
        return game;
    }
    
    /*
    public Audio getAudio () {
            return audio;
    }

    public Files getFiles () {
            return files;
    }

    public Graphics getGraphics () {
            return graphics;
    }

    public AndroidInput getInput () {
            return input;
    }

    public Net getNet () {
            return net;
    }
    */
    
    public int GetVersion()
    {
        return android.os.Build.VERSION.SDK_INT;
    }
    
    public void Exit()
    {
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                AndroidApplication.androidActivity.finish();
            }
        });
    }
    
    public Context GetContext()
    {
        return androidActivity;
    }
    
    public Handler GetHandler()
    {
        return this.handler;
    }
    
    public static void SetActivity(Activity activity)
    {
        androidActivity = activity;
    }
    
    public static Activity GetActivity()
    {
        return androidActivity;
    }
    
    public static boolean IsInitialized()
    {
        return initialized;
    }
    
    public static byte[] QuorumFileToBytes(File_ quorumFile)
    {
        return AssetAsBytes(quorumFile.GetPath());
    }
    
    public static InputStream QuorumFileToInputStream(File_ quorumFile)
    {
        return AssetAsInputStream(quorumFile.GetPath());
    }
    
    public static AssetFileDescriptor QuorumFileToAssetFileDescriptor(File_ quorumFile)
    {
        return AssetAsAssetFileDescriptor(quorumFile.GetPath());
    }
    
    public static byte[] AssetAsBytes(String assetName)
    {
        try
        {
            InputStream stream = androidActivity.getAssets().open(assetName);
            byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            stream.close();
            return bytes;
        }
        catch (IOException e)
        {
            throw new GameRuntimeError("I couldn't load this file: " + assetName);
        }
    }

    public static InputStream AssetAsInputStream(String assetName)
    {
        try
        {
            return androidActivity.getAssets().open(assetName);
        }
        catch (IOException e)
        {
            throw new GameRuntimeError("I couldn't load this file: " + assetName);
        }
    }
    
    public static AssetFileDescriptor AssetAsAssetFileDescriptor(String assetName)
    {
        try
        {
            return GetActivity().getAssets().openFd(assetName);
        }
        catch (IOException e)
        {
            throw new GameRuntimeError("I couldn't load this file: " + assetName);
        }
    }
    
    public void Log(String header, String text)
    {
        Log.d(header, text);
    }
    
    public void Log(String text)
    {
        Log.d("Quorum Game Application", text);
    }
    
    public static void LogStatic(String header, String text)
    {
        Log.d(header, text);
    }
}
