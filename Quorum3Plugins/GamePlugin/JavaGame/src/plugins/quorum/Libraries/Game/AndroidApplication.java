/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import quorum.Libraries.Game.Game_;
import quorum.Libraries.Game.ApplicationConfiguration_;
import quorum.Libraries.Game.AndroidConfiguration;
import quorum.Libraries.Game.AndroidApplication_;
import quorum.Libraries.Game.AndroidDisplay_;

import java.lang.reflect.Method;

/**
 *
 * @author alleew
 */
public class AndroidApplication extends Activity
{
    java.lang.Object me_ = null;
    
    static final int MINIMUM_SDK = 8;
    
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
    
    public void SetupNative(Game_ game, ApplicationConfiguration_ configuration)
    {
        AndroidConfiguration config = (AndroidConfiguration)configuration;
        
        /* 
        SetupNative is only called as part of Setup in the Quorum 
        AndroidApplication, which sets itself in the GameStateManager right
        before calling this method. It also sets an AndroidDisplay in the
        GameStateManager.
        */
        quorumApp = (AndroidApplication_)GameState.GetApp();
        display = (AndroidDisplay_)GameState.GetDisplay();
        
        if (GetVersion() < MINIMUM_SDK) 
        {
            throw new GameRuntimeError("Android API level " + GetVersion() + "was detected, but " + MINIMUM_SDK + " or later is required.");
        }
        
        //graphics = new AndroidGraphics(this, config, config.resolutionStrategy == null ? new FillResolutionStrategy()
        //        : config.resolutionStrategy);
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
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception ex) 
        {
            //log("AndroidApplication", "Content already displayed, cannot request FEATURE_NO_TITLE", ex);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        //setContentView(graphics.getView(), createLayoutParams());
        //}

        CreateWakeLock(config.preventScreenDimming);
        HideStatusBar(this.hideStatusBar);
        UseImmersiveMode(this.useImmersiveMode);
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
    }
    
    public void CreateWakeLock(boolean lock)
    {
        if (lock)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
    
    public void HideStatusBar(boolean hide)
    {
        if (!hide || GetVersion() < 11)
            return;
        View rootView = getWindow().getDecorView();
        
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
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
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
    
    @TargetApi(19)
    public void UseImmersiveMode(boolean mode)
    {
        if (!mode || GetVersion() < Build.VERSION_CODES.KITKAT)
            return;
        
        View view = getWindow().getDecorView();
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
    
    @Override
    protected void onPause()
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
        
        if (isFinishing())
        {
            //graphics.clearManagedCaches();
            //graphics.destroy();
        }
        
        //AndroidGraphics.enforceContinuousRendering = isContinuousEnforced;
        //graphics.setContinuousRendering(isContinuous);
        
        //graphics.onPauseGLSurfaceView();
        
        super.onPause();
    }
    
    @Override
    protected void onResume()
    {
        GameState.SetApp(quorumApp);
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
        super.onResume();
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
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
                AndroidApplication.this.finish();
            }
        });
    }
    
    public Context GetContext()
    {
        return this;
    }
    
    public Handler GetHandler()
    {
        return this.handler;
    }
}
