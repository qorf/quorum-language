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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import plugins.quorum.Libraries.Game.Graphics.Mesh;
import plugins.quorum.Libraries.Game.Graphics.ShaderProgram;
import plugins.quorum.Libraries.Game.Graphics.Texture;
import plugins.quorum.Libraries.Game.libGDX.GdxEglConfigChooser;

import plugins.quorum.Libraries.Game.libGDX.WindowedMean;
import quorum.Libraries.Game.AndroidConfiguration;

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
    
    public void Initialize(AndroidApplication application, AndroidConfiguration configuration)
    {
        config = configuration;
        app = application;
        view = CreateGLSurfaceView(application);
        
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }
    
    protected void PreserveEGLContextOnPause () 
    {
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        if ((sdkVersion >= 11 && view instanceof GLSurfaceView20)/* || view instanceof GLSurfaceView20API18*/) 
        {
            try 
            {
                view.getClass().getMethod("setPreserveEGLContextOnPause", boolean.class).invoke(view, true);
            } 
            catch (Exception e) 
            {
                //Gdx.app.log(LOG_TAG, "Method GLSurfaceView.setPreserveEGLContextOnPause not found");
            }
        }
    }
    
    
    protected View CreateGLSurfaceView (AndroidApplication application) 
    {
        if (!CheckGL20())
            throw new GameRuntimeError("This application requires OpenGL ES 2.0 or higher.");

        Log.d("Android Application - Android Display", "Found support for OpenGL ES 2.0 or higher.");
        
        EGLConfigChooser configChooser = getEglConfigChooser();
        int sdkVersion = android.os.Build.VERSION.SDK_INT;
        /*
        if (sdkVersion <= 10 && config.useGLSurfaceView20API18) 
        {
            //GLSurfaceView20API18 view = new GLSurfaceView20API18(application.getContext(), resolutionStrategy);
            //if (configChooser != null)
                ;//view.setEGLConfigChooser(configChooser);
            //else
                ;//view.setEGLConfigChooser(config.r, config.g, config.b, config.a, config.depth, config.stencil);
            //view.setRenderer(this);
            return view;
        }
        */
        //else ...
        {
            boolean nullContext = application.GetContext() == null;
            Log.d("Android Display", "Application context null? " + nullContext);
            Log.d("Android Display", "Attempting to get resources...");
            application.GetActivity().getResources();
            Log.d("Android Display", "Got resources.");
            GLSurfaceView20 view = new GLSurfaceView20(application.GetContext(), config);

            view.setEGLConfigChooser(configChooser);
            view.setRenderer(this);
            return view;
        }
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
        DisplayMetrics metrics = new DisplayMetrics();
        app.GetActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

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
        this.width = width;
        this.height = height;
        UpdatePPI();
        gl.glViewport(0, 0, this.width, this.height);
        if (created == false) 
        {
            GameStateManager.nativeGraphics.ClearScreenColor(0.85, 0.85, 0.85, 1);
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

        Mesh.ReloadMeshes();
        Texture.ReloadTextures();
        ShaderProgram.ReloadShaders();
        
        /*
        Mesh.invalidateAllMeshes(app);
        Texture.invalidateAllTextures(app);
        Cubemap.invalidateAllCubemaps(app);
        ShaderProgram.invalidateAllShaderPrograms(app);
        FrameBuffer.invalidateAllFrameBuffers(app);

        logManagedCachesStatus();
        */
        
        Display display = app.GetActivity().getWindowManager().getDefaultDisplay();
        this.width = display.getWidth();
        this.height = display.getHeight();
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
        // the more important question to answer.
        return eglContext != null;
    }
}
