/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.Renderer;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Choreographer;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import plugins.quorum.Libraries.Game.Graphics.Texture;
import plugins.quorum.Libraries.Game.libGDX.GdxEglConfigChooser;

import plugins.quorum.Libraries.Game.libGDX.WindowedMean;
import quorum.Libraries.Game.AndroidConfiguration;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanGraphics;
import quorum.Libraries.Game.Graphics.Vulkan.VulkanGraphics_;
import plugins.quorum.Libraries.Interface.Events.ResizeProcessor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author alleew
 */
public class AndroidDisplay implements Renderer
{
    public java.lang.Object me_ = null;
    
    /** When {@link AndroidFragmentApplication#onPause()} or {@link AndroidApplication#onPause()} call
        * {@link AndroidGraphics#pause()} they <b>MUST</b> enforce continuous rendering. If not, {@link #onDrawFrame(GL10)} will not
        * be called in the GLThread while {@link #pause()} is sleeping in the Android UI Thread which will cause the
        * {@link AndroidGraphics#pause} variable never be set to false. As a result, the {@link AndroidGraphics#pause()} method will
        * kill the current process to avoid ANR */
       static volatile boolean enforceContinuousRendering = false;

       protected View view;
       protected int width;
       protected int height;
       AndroidApplication app;
       //GL20 gl20;
       //GL30 gl30;
       EGLContext eglContext;
       String extensions;

       protected long lastFrameTime = System.nanoTime();
       protected float deltaTime = 0;
       private double totalTime = 0;
       protected long frameStart = System.nanoTime();
       protected long frameId = -1;
       protected int frames = 0;
       protected int fps;
       protected WindowedMean mean = new WindowedMean(5);

       volatile boolean created = false;
       volatile boolean running = false;
       volatile boolean pause = false;
       volatile boolean resume = false;
       volatile boolean destroy = false;

       private float ppiX = 0;
       private float ppiY = 0;
       private float ppcX = 0;
       private float ppcY = 0;
       private float density = 1;

       protected AndroidConfiguration config;
       //private BufferFormat bufferFormat = new BufferFormat(5, 6, 5, 0, 16, 0, 0, false);
       private boolean isContinuous = true;

       private int[] value = new int[1];
       private Object synch = new Object();

       /*
       With Vulkan (AndroidConfiguration's useVulkan): a SurfaceView, whose
       Surface Vulkan draws on, and a thread that runs the game once per screen
       refresh (Choreographer), as GLSurfaceView's thread does with OpenGL.
       */
       private volatile Surface vulkanSurface = null;
       private HandlerThread vulkanThread = null;
       private Handler vulkanHandler = null;
       // The display, so input and accessibility can reach the game's thread
       // (RunOnGameThread).
       private static AndroidDisplay current = null;
       private Choreographer choreographer = null;
       // Touched only on the Vulkan thread.
       private volatile boolean vulkanRunning = false;
       private boolean frameRequested = false;
       private final Choreographer.FrameCallback vulkanFrame = new Choreographer.FrameCallback()
       {
           @Override
           public void doFrame(long frameTimeNanos)
           {
               frameRequested = false;
               if (!vulkanRunning)
                   return;
               RunVulkanFrame();
               RequestVulkanFrame();
           }
       };
    
    public void Initialize(AndroidApplication application, AndroidConfiguration configuration)
    {
        config = configuration;
        app = application;
        if (GameStateManager.graphics instanceof VulkanGraphics)
            view = CreateVulkanSurfaceView(application);
        else
            view = CreateGLSurfaceView(application);
        
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }

    /*
    The view for Vulkan, and the thread the game runs on. Android gives the
    view a Surface when it's shown, and takes it away when the app goes to the
    background: frames run only while there is one.
    */
    protected View CreateVulkanSurfaceView(AndroidApplication application)
    {
        vulkanThread = new HandlerThread("Quorum Vulkan");
        vulkanThread.start();
        vulkanHandler = new Handler(vulkanThread.getLooper());
        current = this;

        SurfaceView surfaceView = new VulkanSurfaceView(application.GetContext());
        plugins.quorum.Libraries.Interface.Mobile.AndroidTextInput.SetView(surfaceView);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, final int newWidth, final int newHeight)
            {
                // A new size on a surface Vulkan already draws on (the device
                // turned): the game lays out again and the swapchain is made
                // again, as on iOS. Otherwise, frames start on the new surface.
                if (vulkanRunning && holder.getSurface() == vulkanSurface)
                {
                    width = newWidth;
                    height = newHeight;
                    vulkanHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ResizeProcessor.AddResizeEvent(0, newWidth, newHeight);
                            ((VulkanGraphics_)GameStateManager.graphics).RequestSwapchainRegeneration();
                        }
                    });
                    return;
                }
                width = newWidth;
                height = newHeight;
                StartVulkanFrames(holder.getSurface());
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                StopVulkanFrames();
            }
        });
        return surfaceView;
    }

    // The Surface Vulkan draws on, for NativeVulkanSurface.
    public Surface GetVulkanSurface()
    {
        return vulkanSurface;
    }

    /*
    Starts the frames on the Vulkan thread with the new Surface. When the app
    comes back from the background, Vulkan's surface and swapchain are made
    again on it first (VulkanGraphics:RestoreSurface).
    */
    private void StartVulkanFrames(final Surface surface)
    {
        vulkanSurface = surface;
        vulkanHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (choreographer == null)
                    choreographer = Choreographer.getInstance();
                if (created)
                    ((VulkanGraphics_)GameStateManager.graphics).RestoreSurface();
                vulkanRunning = true;
                RequestVulkanFrame();
            }
        });
    }

    /*
    Stops the frames when Android takes the Surface away, and frees what
    Vulkan made on it (VulkanGraphics:LoseSurface). Android needs that done
    before this returns, so the Android thread waits for the Vulkan thread.
    */
    private void StopVulkanFrames()
    {
        final CountDownLatch done = new CountDownLatch(1);
        vulkanHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                vulkanRunning = false;
                if (choreographer != null)
                    choreographer.removeFrameCallback(vulkanFrame);
                frameRequested = false;
                if (created)
                    ((VulkanGraphics_)GameStateManager.graphics).LoseSurface();
                done.countDown();
            }
        });
        try
        {
            done.await(4, TimeUnit.SECONDS);
        }
        catch (InterruptedException ignored)
        {
        }
        vulkanSurface = null;
    }

    private void RequestVulkanFrame()
    {
        if (!frameRequested)
        {
            frameRequested = true;
            choreographer.postFrameCallback(vulkanFrame);
        }
    }

    /*
    Runs work on the thread the game runs on. With Vulkan that's the "Quorum
    Vulkan" thread, while touches and TalkBack's requests come on Android's
    main thread, so the work is posted there and Quorum's lists and items
    are only changed by one thread. With OpenGL, it is queued on GLSurfaceView's thread.
    */
    public static void RunOnGameThread(Runnable work)
    {
        AndroidDisplay display = current;
        if (display != null && display.vulkanHandler != null)
            display.vulkanHandler.post(work);
        else if (display != null && display.view instanceof GLSurfaceView)
        {
            // With OpenGL the game runs on GLSurfaceView's thread.
            GLSurfaceView glView = (GLSurfaceView)display.view;
            glView.queueEvent(work);
            glView.requestRender();
        }
        else
            work.run();
    }

    /*
    One frame with Vulkan, on the Vulkan thread. The first one starts Vulkan
    (Game:InitializeLayers), now that there's a Surface, and makes the game.
    */
    private void RunVulkanFrame()
    {
        if (!created)
        {
            UpdatePPI();
            app.GetGame().InitializeLayers();
            app.GetGame().CreateGame();
            created = true;
            synchronized (this)
            {
                running = true;
            }
        }

        long time = System.nanoTime();
        deltaTime = (time - lastFrameTime) / 1000000000.0f;
        lastFrameTime = time;
        totalTime = totalTime + deltaTime;
        mean.addValue(deltaTime);

        frameId++;
        app.GetGame().ContinueGame();

        if (time - frameStart > 1000000000)
        {
            fps = frames;
            frames = 0;
            frameStart = time;
        }
        frames++;
    }
    
    protected void PreserveEGLContextOnPause ()
    {
        if (view instanceof GLSurfaceView20)
            ((GLSurfaceView20)view).setPreserveEGLContextOnPause(true);
    }


    protected View CreateGLSurfaceView (AndroidApplication application)
    {
        if (!CheckGL20())
            throw new GameRuntimeError("This application requires OpenGL ES 2.0 or higher.");

        Log.d("Android Application - Android Display", "Found support for OpenGL ES 2.0 or higher.");

        EGLConfigChooser configChooser = getEglConfigChooser();
        GLSurfaceView20 view = new GLSurfaceView20(application.GetContext(), config);
        current = this;
        plugins.quorum.Libraries.Interface.Mobile.AndroidTextInput.SetView(view);
        view.setEGLConfigChooser(configChooser);
        view.setRenderer(this);
        return view;
    }
    
    
    public void OnPauseGLSurfaceView () 
    {
        if (view != null) 
        {
        //    if (view instanceof GLSurfaceViewAPI18) ((GLSurfaceViewAPI18)view).onPause();
            if (view instanceof GLSurfaceView)
                ((GLSurfaceView)view).onPause();
        }
    }

    public void OnResumeGLSurfaceView () 
    {
        if (view != null) 
        {
        //    if (view instanceof GLSurfaceViewAPI18) ((GLSurfaceViewAPI18)view).onResume();
            if (view instanceof GLSurfaceView)
                ((GLSurfaceView)view).onResume();
        }
    }
    
    protected EGLConfigChooser getEglConfigChooser () 
    {
        return new GdxEglConfigChooser(config.Get_Libraries_Game_ApplicationConfiguration__r_(), config.Get_Libraries_Game_ApplicationConfiguration__g_(),
            config.Get_Libraries_Game_ApplicationConfiguration__b_(), config.Get_Libraries_Game_ApplicationConfiguration__a_(),
            config.Get_Libraries_Game_ApplicationConfiguration__depth_(), config.Get_Libraries_Game_ApplicationConfiguration__stencil_(),
            config.Get_Libraries_Game_ApplicationConfiguration__samples_());
    }

    private void UpdatePPI()
    {
        DisplayMetrics metrics = app.GetActivity().getResources().getDisplayMetrics();

        ppiX = metrics.xdpi;
        ppiY = metrics.ydpi;
        ppcX = metrics.xdpi / 2.54f;
        ppcY = metrics.ydpi / 2.54f;
        density = metrics.density;
    }

    protected boolean CheckGL20() 
    {
        Log.d("Android Application - Android Display", "Checking GL20...");
        EGL10 egl = (EGL10)EGLContext.getEGL();
        EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

        int[] version = new int[2];
        egl.eglInitialize(display, version);
        Log.d("Android Display", "Initialized EGL in AndroidDisplay.");

        int EGL_OPENGL_ES2_BIT = 4;
        int[] configAttribs = {EGL10.EGL_RED_SIZE, 4, EGL10.EGL_GREEN_SIZE, 4, EGL10.EGL_BLUE_SIZE, 4, EGL10.EGL_RENDERABLE_TYPE,
                EGL_OPENGL_ES2_BIT, EGL10.EGL_NONE};

        EGLConfig[] configs = new EGLConfig[10];
        int[] num_config = new int[1];
        egl.eglChooseConfig(display, configAttribs, configs, 10, num_config);
        egl.eglTerminate(display);
        return num_config[0] > 0;
    }
    
    public int GetHeight() 
    {
        return height;
    }

    public int GetWidth() 
    {
        return width;
    }

    private void SetupGL (javax.microedition.khronos.opengles.GL10 gl) {
        /*
        if (gl20 != null)
            return;

        gl20 = new AndroidGL20();

        Gdx.gl = gl20;
        Gdx.gl20 = gl20;

        Gdx.app.log(LOG_TAG, "OGL renderer: " + gl.glGetString(GL10.GL_RENDERER));
        Gdx.app.log(LOG_TAG, "OGL vendor: " + gl.glGetString(GL10.GL_VENDOR));
        Gdx.app.log(LOG_TAG, "OGL version: " + gl.glGetString(GL10.GL_VERSION));
        Gdx.app.log(LOG_TAG, "OGL extensions: " + gl.glGetString(GL10.GL_EXTENSIONS));
       */
    }

    @Override
    public void onSurfaceChanged (javax.microedition.khronos.opengles.GL10 gl, int width, int height) 
    {
        Log.d("Android Application - Android Display", "Beginning onSurfaceChanged");
        // A new size after the game started (the device turned): the game
        // lays out again, as on iOS and the desktop.
        boolean resized = created && (width != this.width || height != this.height);
        this.width = width;
        this.height = height;
        if (resized)
            ResizeProcessor.AddResizeEvent(0, width, height);
        UpdatePPI();
        gl.glViewport(0, 0, this.width, this.height);
        if (created == false) 
        {
            GameStateManager.nativeGraphics.SetClearScreenColor(0.85, 0.85, 0.85, 1);
            app.GetGame().InitializeLayers();
            app.GetGame().CreateGame();
            created = true;
            synchronized (this) 
            {
                running = true;
            }
        }
        //app.getApplicationListener().resize(width, height);
        Log.d("Android Application - Android Display", "Finishing onSurfaceChanged");
    }
    
    @Override
    public void onSurfaceCreated (javax.microedition.khronos.opengles.GL10 gl, EGLConfig config) 
    {
        Log.d("Android Application - Android Display", "Beginning onSurfaceCreated");
        eglContext = ((EGL10)EGLContext.getEGL()).eglGetCurrentContext();
        SetupGL(gl);
        //logConfig(config);
        UpdatePPI();

        quorum.Libraries.Game.AndroidDisplay quorumDisplay = (quorum.Libraries.Game.AndroidDisplay)me_;
        quorumDisplay.ReloadResources();
        Texture.ReloadTextures();
        
        /*
        Mesh.invalidateAllMeshes(app);
        Texture.invalidateAllTextures(app);
        Cubemap.invalidateAllCubemaps(app);
        ShaderProgram.invalidateAllShaderPrograms(app);
        FrameBuffer.invalidateAllFrameBuffers(app);

        logManagedCachesStatus();
        */
        
        Rect bounds = app.GetActivity().getWindowManager().getCurrentWindowMetrics().getBounds();
        this.width = bounds.width();
        this.height = bounds.height();
        this.mean = new WindowedMean(5);
        this.lastFrameTime = System.nanoTime();

        gl.glViewport(0, 0, this.width, this.height);
        Log.d("Android Application - Android Display", "Finishing onSurfaceCreated");
    }

    private void logConfig (EGLConfig config) 
    {
        EGL10 egl = (EGL10)EGLContext.getEGL();
        EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        int r = GetAttribute(egl, display, config, EGL10.EGL_RED_SIZE, 0);
        int g = GetAttribute(egl, display, config, EGL10.EGL_GREEN_SIZE, 0);
        int b = GetAttribute(egl, display, config, EGL10.EGL_BLUE_SIZE, 0);
        int a = GetAttribute(egl, display, config, EGL10.EGL_ALPHA_SIZE, 0);
        int d = GetAttribute(egl, display, config, EGL10.EGL_DEPTH_SIZE, 0);
        int s = GetAttribute(egl, display, config, EGL10.EGL_STENCIL_SIZE, 0);
        /*
        int samples = Math.max(getAttrib(egl, display, config, EGL10.EGL_SAMPLES, 0),
                getAttrib(egl, display, config, GdxEglConfigChooser.EGL_COVERAGE_SAMPLES_NV, 0));
        boolean coverageSample = getAttrib(egl, display, config, GdxEglConfigChooser.EGL_COVERAGE_SAMPLES_NV, 0) != 0;

        Gdx.app.log(LOG_TAG, "framebuffer: (" + r + ", " + g + ", " + b + ", " + a + ")");
        Gdx.app.log(LOG_TAG, "depthbuffer: (" + d + ")");
        Gdx.app.log(LOG_TAG, "stencilbuffer: (" + s + ")");
        Gdx.app.log(LOG_TAG, "samples: (" + samples + ")");
        Gdx.app.log(LOG_TAG, "coverage sampling: (" + coverageSample + ")");

        bufferFormat = new BufferFormat(r, g, b, a, d, s, samples, coverageSample);
        */
    }

    private int GetAttribute(EGL10 egl, EGLDisplay display, EGLConfig config, int attrib, int defValue) 
    {
        if (egl.eglGetConfigAttrib(display, config, attrib, value)) 
        {
            return value[0];
        }
        return defValue;
    }
    
    void Resume() 
    {
        synchronized (synch) {
            running = true;
            resume = true;
        }
    }

    void Pause()
    {
        synchronized (synch) 
        {
            if (!running)
                return;
            running = false;
            pause = true;
            while (pause) 
            {
                try 
                {
                    // TODO: fix deadlock race condition with quick resume/pause.
                    // Temporary workaround:
                    // Android ANR time is 5 seconds, so wait up to 4 seconds before assuming
                    // deadlock and killing process. This can easily be triggered by opening the
                    // Recent Apps list and then double-tapping the Recent Apps button with
                    // ~500ms between taps.
                    synch.wait(4000);
                    if (pause) 
                    {
                        // pause will never go false if onDrawFrame is never called by the GLThread
                        // when entering this method, we MUST enforce continuous rendering
                        //Gdx.app.error(LOG_TAG, "waiting for pause synchronization took too long; assuming deadlock and killing");
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }
                catch (InterruptedException ignored) 
                {
                    //Gdx.app.log(LOG_TAG, "waiting for pause synchronization failed!");
                }
            }
        }
    }

    void Destroy() 
    {
        synchronized (synch) 
        {
            running = false;
            destroy = true;

            while (destroy) 
            {
                try 
                {
                    synch.wait();
                }
                catch (InterruptedException ex) 
                {
                    //Gdx.app.log(LOG_TAG, "waiting for destroy synchronization failed!");
                }
            }
        }
    }
    
    @Override
    public void onDrawFrame(javax.microedition.khronos.opengles.GL10 gl)
    {
        long time = System.nanoTime();
        deltaTime = (time - lastFrameTime) / 1000000000.0f;
        lastFrameTime = time;
        totalTime = totalTime + deltaTime;
        
        /* After pause deltaTime can have a somewhat huge value that 
        destabilizes the mean, so we ignore it. */
        if (!resume)
            mean.addValue(deltaTime);
        else
            deltaTime = 0;
        
        boolean lrunning = false;
        boolean lpause = false;
        boolean ldestroy = false;
        boolean lresume = false;
        
        synchronized(synch)
        {
            lrunning = running;
            lpause = pause;
            ldestroy = destroy;
            lresume = resume;
            
            if (resume)
                resume = false;
            
            if (pause)
            {
                pause = false;
                synch.notifyAll();
            }
            
            if (destroy)
            {
                destroy = false;
                synch.notifyAll();
            }
        }
        
        if (lresume)
        {
            /*
            Array<LifecycleListener> listeners = app.getLifecycleListeners();
            synchronized (listeners) {
                    for (LifecycleListener listener : listeners) {
                            listener.resume();
                    }
            }
            app.getApplicationListener().resume();
            Gdx.app.log(LOG_TAG, "resumed");
            */
        }
        
        if (lrunning)
        {
            /*
            synchronized (app.getRunnables()) 
            {
                app.getExecutedRunnables().clear();
                app.getExecutedRunnables().addAll(app.getRunnables());
                app.getRunnables().clear();
            }

            for (int i = 0; i < app.getExecutedRunnables().size; i++) 
            {
                try 
                {
                    app.getExecutedRunnables().get(i).run();
                }
                catch (Throwable t) 
                {
                    t.printStackTrace();
                }
            }
            */
            //app.getInput().processEvents();
            frameId++;
            app.GetGame().ContinueGame();
        }
        
        if (lpause)
        {
            /*
            Array<LifecycleListener> listeners = app.getLifecycleListeners();
            synchronized (listeners) {
                    for (LifecycleListener listener : listeners) {
                            listener.pause();
                    }
            }
            app.getApplicationListener().pause();
            Gdx.app.log(LOG_TAG, "paused");
            */
        }
        
        if (ldestroy)
        {
            /*
            Array<LifecycleListener> listeners = app.getLifecycleListeners();
            synchronized (listeners) {
                    for (LifecycleListener listener : listeners) {
                            listener.dispose();
                    }
            }
            app.getApplicationListener().dispose();
            Gdx.app.log(LOG_TAG, "destroyed");
            */
        }
        
        if (time - frameStart > 1000000000) 
        {
            fps = frames;
            frames = 0;
            frameStart = time;
        }
        frames++;
    }
    
    public long GetFrameID()
    {
            return frameId;
    }

    public double GetSecondsBetweenFrames() 
    {
        return mean.getMean() == 0 ? deltaTime : mean.getMean();
    }
    
    public double GetSecondsSinceStart()
    {
        return totalTime;
    }

    public float GetRawDeltaTime() 
    {
        return deltaTime;
    }

    public int GetFramesPerSecond() 
    {
        return fps;
    }

    /*
    public void ClearManagedCaches () 
    {
            Mesh.clearAllMeshes(app);
            Texture.clearAllTextures(app);
            Cubemap.clearAllCubemaps(app);
            ShaderProgram.clearAllShaderPrograms(app);
            FrameBuffer.clearAllFrameBuffers(app);

            logManagedCachesStatus();
    }

    protected void logManagedCachesStatus () {
            Gdx.app.log(LOG_TAG, Mesh.getManagedStatus());
            Gdx.app.log(LOG_TAG, Texture.getManagedStatus());
            Gdx.app.log(LOG_TAG, Cubemap.getManagedStatus());
            Gdx.app.log(LOG_TAG, ShaderProgram.getManagedStatus());
            Gdx.app.log(LOG_TAG, FrameBuffer.getManagedStatus());
    }
    */

    public View GetView() 
    {
        return view;
    }

    public float GetPPIX() 
    {
        return ppiX;
    }

    public float GetPPIY() 
    {
        return ppiY;
    }
    
    public float GetPPCX() 
    {
        return ppcX;
    }

    public float GetPPCY() 
    {
        return ppcY;
    }

    public float GetDensity() 
    {
        return density;
    }

    public boolean SupportsDisplayModeChange() 
    {
        return false;
    }

    /*
    public BufferFormat getBufferFormat () {
            return bufferFormat;
    }
    */

    public boolean SupportsExtension(String extension) 
    {
        if (extensions == null)
            ;//extensions = Gdx.gl.glGetString(GL10.GL_EXTENSIONS);
        return extensions.contains(extension);
    }

    public void SetContinuousRendering(boolean isContinuous) 
    {
        if (view != null) 
        {
            // ignore setContinuousRendering(false) while pausing
            this.isContinuous = enforceContinuousRendering || isContinuous;
            int renderMode = this.isContinuous ? GLSurfaceView.RENDERMODE_CONTINUOUSLY : GLSurfaceView.RENDERMODE_WHEN_DIRTY;
            /*
            if (view instanceof GLSurfaceViewAPI18)
                ((GLSurfaceViewAPI18)view).setRenderMode(renderMode);
            if (view instanceof GLSurfaceView)
                ((GLSurfaceView)view).setRenderMode(renderMode);
            */
            mean.clear();
        }
    }

    public boolean IsContinuousRendering() 
    {
        return isContinuous;
    }

    public void RequestRendering() 
    {
        if (view != null) 
        {
            /*
            if (view instanceof GLSurfaceViewAPI18)
                ((GLSurfaceViewAPI18)view).requestRender();
            if (view instanceof GLSurfaceView)
                ((GLSurfaceView)view).requestRender();
            */
        }
    }

    public boolean IsFullscreen() 
    {
        return true;
    }

    public boolean IsAvailable()
    {
        // This check needs to be made more thorough. This satisfies the simple
        // case of making sure the display was initialized at some point, but
        // doesn't necessarily guarantee that an OpenGL context exists, which is
        // the more important question to answer. With Vulkan, it's whether
        // there's a Surface to draw on.
        return eglContext != null || vulkanSurface != null;
    }
}
