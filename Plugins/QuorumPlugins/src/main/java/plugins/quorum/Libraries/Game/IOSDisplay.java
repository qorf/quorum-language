/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.glkit.GLKView;
import org.robovm.apple.glkit.GLKViewDelegate;
import org.robovm.apple.glkit.GLKViewControllerDelegate;
import org.robovm.apple.glkit.GLKViewController;
import org.robovm.apple.glkit.GLKViewDrawableColorFormat;
import org.robovm.apple.glkit.GLKViewDrawableDepthFormat;
import org.robovm.apple.glkit.GLKViewDrawableStencilFormat;
import org.robovm.apple.glkit.GLKViewDrawableMultisample;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.apple.opengles.EAGLContext;
import org.robovm.apple.opengles.EAGLRenderingAPI;
import plugins.quorum.Libraries.Game.libGDX.HWMachine;
import plugins.quorum.Libraries.Game.libGDX.IOSDevice;
import plugins.quorum.Libraries.Game.Graphics.OpenGL.IOSOpenGL;
import quorum.Libraries.Game.IOSConfiguration_;

/**
 *
 * @author alleew
 */
public class IOSDisplay extends NSObject implements GLKViewDelegate, GLKViewControllerDelegate, UIGestureRecognizerDelegate
{
    public java.lang.Object me_ = null;
    public static IOSUIViewController theViewController = null;
    // Is this necessary? Original libgdx code below:
    // private static final String tag = "IOSGraphics";
    private static final String tag = "IOSDisplay";

    /*
    Method used to determine if a recognizer is allowed to transition to "begin" state.
    When this is called, the system has already recognized the gesture -- it's only asking for permission.
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/1624213-gesturerecognizershouldbegin
    */
    @Override
    public boolean shouldBegin(UIGestureRecognizer uiGestureRecognizer)
    {
        return true;
    }

    /*
    Method used to determine if two gesture recognizers are allowed to trigger off the same inputs.
    By default this is false, but we want to capture all gestures and send them up to Quorum, so we always return true.
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/1624208-gesturerecognizer
    */
    @Override
    public boolean shouldRecognizeSimultaneously(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1)
    {
        return true;
    }

    /*
    A method to restrict gestures so they only trigger if a different gesture fails to validate. The only case of this
    we care about (related to single tap and double tap) is handled elsewhere, so we always return false (the default).
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/1624229-gesturerecognizer
    */
    @Override
    public boolean shouldRequireFailure(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1)
    {
        return false;
    }

    /*
    Another method to relate the success of a gesture to the failure of another. We return the default value (false).
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/1624222-gesturerecognizer
    */
    @Override
    public boolean shouldBeRequiredToFail(UIGestureRecognizer uiGestureRecognizer, UIGestureRecognizer uiGestureRecognizer1)
    {
        return false;
    }

    /*
    Used to check if the gesture recognizer has permission to process this input. We always allow processing.
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/1624214-gesturerecognizer
    */
    @Override
    public boolean shouldReceiveTouch(UIGestureRecognizer uiGestureRecognizer, UITouch uiTouch)
    {
        return true;
    }

    /*
    Used to check if the gesture recognizer has permission to process this input. We always allow processing.
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/1624216-gesturerecognizer
    */
    @Override
    public boolean shouldReceivePress(UIGestureRecognizer uiGestureRecognizer, UIPress uiPress)
    {
        return true;
    }

    /*
    Used to check if the gesture recognizer has permission to process this input. We always allow processing.
    See: https://developer.apple.com/documentation/uikit/uigesturerecognizerdelegate/3538976-gesturerecognizer
    */
    @Override
    public boolean shouldReceiveEvent(UIGestureRecognizer uiGestureRecognizer, UIEvent uiEvent)
    {
        return true;
    }

    static class IOSUIViewController extends GLKViewController
    {

        final IOSApplication app;
        final IOSDisplay display;
        boolean created = false;
        
        IOSUIViewController(IOSApplication app, IOSDisplay display)
        {
            this.app = app;
            this.display = display;
            theViewController = this;
        }
        
        @Override
        public void viewWillAppear(boolean arg0)
        {
            super.viewWillAppear(arg0);
            // start GLKViewController even though we may only draw a single frame
            // (we may be in non-continuous mode)
            setPaused(false);
        }
        
        @Override
        public void viewDidAppear(boolean animated)
        {
            // Dependent on the existence of IOSViewControllerListener, which does not seem mandatory.
            //if (app.viewControllerListener != null) app.viewControllerListener.viewDidAppear(animated);
        }
        
        /*
        @Override
        public void didRotate(UIInterfaceOrientation orientation)
        {
            super.didRotate(orientation);
            
            CGSize bounds = app.GetBounds(this);
            display.width = (int)bounds.getWidth();
            display.height = (int)bounds.getHeight();
            display.MakeCurrent();
            //app.listener.resize(display.width, display.height);
        }
        */
        
        @Override
        public UIInterfaceOrientationMask getSupportedInterfaceOrientations()
        {
            long mask = 0;
            if (app.config.Get_Libraries_Game_IOSConfiguration__landscapeSupported_())
            {
                mask |= ((1 << UIInterfaceOrientation.LandscapeLeft.value()) | (1 << UIInterfaceOrientation.LandscapeRight.value()));
            }
            if (app.config.Get_Libraries_Game_IOSConfiguration__portraitSupported_())
            {
                mask |= ((1 << UIInterfaceOrientation.Portrait.value()) | (1 << UIInterfaceOrientation.PortraitUpsideDown.value()));
            }
            return new UIInterfaceOrientationMask(mask);
        }
        
        @Override
        public boolean shouldAutorotate()
        {
            return true;
        }
        
        public boolean shouldAutorotateToInterfaceOrientation(UIInterfaceOrientation orientation)
        {
            // Returns "true" if the orientation is supported.
            switch (orientation)
            {
                case LandscapeLeft:
                case LandscapeRight:
                    return app.config.Get_Libraries_Game_IOSConfiguration__landscapeSupported_();
                default:
                    // In this case we assume it's portrait.
                    return app.config.Get_Libraries_Game_IOSConfiguration__portraitSupported_();
            }
        }
        
        @Override
        public void viewDidLayoutSubviews () {
            super.viewDidLayoutSubviews();
            // get the view size and update graphics
            CGRect bounds = app.GetBounds();
            display.width = (int) bounds.getWidth();
            display.height = (int) bounds.getHeight();
            display.MakeCurrent();
        }
    }
    
    static class IOSUIView extends GLKView
    {
        public IOSUIView(CGRect frame, EAGLContext context)
        {
            super(frame, context);
        }
    }
    
    IOSApplication app;
    IOSInput input;
    IOSOpenGL graphics;
    int width;
    int height;
    long lastFrameTime;
    float deltaTime;
    double totalTime = 0;
    long framesStart;
    int frames;
    int fps;
    //BufferFormat bufferFormat;
    String extensions;
    
    private float ppiX = 0;
    private float ppiY = 0;
    private float ppcX = 0;
    private float ppcY = 0;
    private float density = 1;
    
    volatile boolean appPaused;
    private long frameID = -1;
    private boolean isContinuous = true;
    private boolean isFrameRequested = true;
    
    IOSConfiguration_ config;
    EAGLContext context;
    GLKView view;
    IOSUIViewController viewController;
    
    boolean created = false;
    
    public void Initialize(float scale, IOSApplication app, IOSConfiguration_ config, IOSInput input, IOSOpenGL graphics)
    {
        this.config = config;
        
        final CGRect bounds = app.GetBounds();
        
        width = (int)bounds.getWidth();
        height = (int)bounds.getHeight();
        
        this.graphics = graphics;
        
        context = new EAGLContext(EAGLRenderingAPI.OpenGLES3);
        if(context == null) {
            Foundation.log("%@", new NSString("Could not create OpenGL context."));
        }

        /*
        Creation of an anonymous inner class using GLKView which will call to
        the proper input handler (when it is implemented) and will call upon
        this class to draw itself.
        */
        view = new GLKView(new CGRect(0, 0, bounds.getWidth(), bounds.getHeight()), context)
        {
            @Method(selector = "touchesBegan:withEvent:")
            public void touchesBegan(@Pointer long touches, UIEvent event)
            {
                IOSDisplay.this.input.OnTouch(touches);
            }
            
            @Method(selector = "touchesCancelled:withEvent:")
            public void touchesCancelled(@Pointer long touches, UIEvent event)
            {
                IOSDisplay.this.input.OnTouch(touches);
            }
            
            @Method(selector = "touchesEnded:withEvent:")
            public void touchesEnded(@Pointer long touches, UIEvent event)
            {
                IOSDisplay.this.input.OnTouch(touches);
            }
            
            @Method(selector = "touchesMoved:withEvent:")
            public void touchesMoved(@Pointer long touches, UIEvent event)
            {
                IOSDisplay.this.input.OnTouch(touches);
            }
            
            @Override
            public void draw(CGRect rect)
            {
                IOSDisplay.this.draw(this, rect);
            }
        };
        
        view.setDelegate(this);
        
        // Set the color format.
        switch(config.Get_Libraries_Game_IOSConfiguration__colorFormat_())
        {
            // Value of RGBA8888 in IOSConfiguration:
            case 1:
                view.setDrawableColorFormat(GLKViewDrawableColorFormat.RGBA8888);
                break;
            // Value of RGB565:
            case 2:
                view.setDrawableColorFormat(GLKViewDrawableColorFormat.RGB565);
                break;
            // Value of SRGBA8888:
            case 3:
                view.setDrawableColorFormat(GLKViewDrawableColorFormat.SRGBA8888);
        }
        
        // Set the depth format.
        switch(config.Get_Libraries_Game_IOSConfiguration__depthFormat_())
        {
            // Value of DEPTH_16 in IOSConfiguration:
            case 16:
                view.setDrawableDepthFormat(GLKViewDrawableDepthFormat._16);
                break;
            // Value of DEPTH_24 in IOSConfiguration:
            case 24:
                view.setDrawableDepthFormat(GLKViewDrawableDepthFormat._24);
                break;
            // Value of NONE in IOSConfiguration:
            case 0:
                view.setDrawableDepthFormat(GLKViewDrawableDepthFormat.None);         
        }
        
        // Set the stencil format.
        switch(config.Get_Libraries_Game_IOSConfiguration__stencilFormat_())
        {
            // Value of STENCIL_8 in IOSConfiguration:
            case 8:
                view.setDrawableStencilFormat(GLKViewDrawableStencilFormat._8);
                break;
            // Value of NONE in IOSConfiguration:
            case 0:
                view.setDrawableStencilFormat(GLKViewDrawableStencilFormat.None);
        }
        
        // Set the multisample format.
        switch(config.Get_Libraries_Game_IOSConfiguration__multisampleFormat_())
        {
            // Value of SAMPLE_4X in IOSConfiguration:
            case 4:
                view.setDrawableMultisample(GLKViewDrawableMultisample._4X);
                break;
            // Value of NONE in IOSConfiguration:
            case 0:
                view.setDrawableMultisample(GLKViewDrawableMultisample.None);
        }
        
        view.setMultipleTouchEnabled(true);

        // Set up gesture recognition and input callbacks.
        InitializeGestures(view);

        // Create the view controller.
        viewController = new IOSUIViewController(app, this);
        viewController.setView(view);
        viewController.setDelegate(this);
        viewController.setPreferredFramesPerSecond(config.Get_Libraries_Game_IOSConfiguration__preferredFramesPerSecond_());

        this.app = app;
        this.input = input;
        
        int r = 0, g = 0, b = 0, a = 0, depth = 0, stencil = 0, samples = 0;
        if (config.Get_Libraries_Game_IOSConfiguration__colorFormat_() == config.Get_Libraries_Game_IOSConfiguration__RGB565_())
        {
            r = 5;
            g = 6;
            b = 5;
            a = 0;
        }
        else
        {
            r = g = b = a = 8;
        }
        
        if (config.Get_Libraries_Game_IOSConfiguration__depthFormat_() == config.Get_Libraries_Game_IOSConfiguration__DEPTH_16_())
        {
            depth = 16;
        }
        else if (config.Get_Libraries_Game_IOSConfiguration__depthFormat_() == config.Get_Libraries_Game_IOSConfiguration__DEPTH_24_())
        {
            depth = 24;
        }
        else
        {
            depth = 0;
        }
        
        if (config.Get_Libraries_Game_IOSConfiguration__stencilFormat_() == config.Get_Libraries_Game_IOSConfiguration__STENCIL_8_())
        {
            stencil = 8;
        }
        if (config.Get_Libraries_Game_IOSConfiguration__multisampleFormat_() == config.Get_Libraries_Game_IOSConfiguration__SAMPLE_4X_())
        {
            samples = 4;
        }
        
        /*
        In libGDX, this information is used to create a buffer format. We 
        instead set this data in the ApplicationConfiguration class data (the
        parent class of IOSConfiguration).
        */
        config.Set_Libraries_Game_ApplicationConfiguration__r_(r);
        config.Set_Libraries_Game_ApplicationConfiguration__g_(g);
        config.Set_Libraries_Game_ApplicationConfiguration__b_(b);
        config.Set_Libraries_Game_ApplicationConfiguration__a_(a);
        config.Set_Libraries_Game_ApplicationConfiguration__depth_(depth);
        config.Set_Libraries_Game_ApplicationConfiguration__stencil_(stencil);
        config.Set_Libraries_Game_ApplicationConfiguration__samples_(samples);
        
        // this.graphics = graphics;
        
        String machineString = HWMachine.getMachineString();
        IOSDevice device = IOSDevice.getDevice(machineString);
        if (device == null)
            Foundation.log("%@", new NSString("[error] " + tag + ": " + "Machine ID: " + machineString + " was not found!"));
        
        int ppi = device != null ? device.ppi : 163;
        density = device != null ? device.ppi/160f : scale;
        ppiX = ppi;
        ppiY = ppi;
        ppcX = ppiX / 2.54f;
        ppcY = ppiY / 2.54f;
        //app.debug(tag, "Display: ppi = " + ppi + ", density = " + density);
        
        // Start time and FPS.
        lastFrameTime = System.nanoTime();
        framesStart = lastFrameTime;
        
        appPaused = false;
    }

    private void InitializeGestures(GLKView view)
    {
        // Set up single and double tap recognizers.
        UITapGestureRecognizer doubleTapRecognizer = new UITapGestureRecognizer();
        doubleTapRecognizer.setNumberOfTapsRequired(2);
        doubleTapRecognizer.addListener(new OnDoubleTapListener());
        view.addGestureRecognizer(doubleTapRecognizer);

        UITapGestureRecognizer tapRecognizer = new UITapGestureRecognizer();
        tapRecognizer.setNumberOfTapsRequired(1);
        tapRecognizer.requireGestureRecognizerToFail(doubleTapRecognizer);
        tapRecognizer.addListener(new OnTapListener());
        view.addGestureRecognizer(tapRecognizer);

        // Swipe and pan recognizers.
        UISwipeGestureRecognizer rightSwipeRecognizer = new UISwipeGestureRecognizer();
        rightSwipeRecognizer.addListener(new OnSwipeListener());
        rightSwipeRecognizer.setDelegate(this);
        rightSwipeRecognizer.setDirection(new UISwipeGestureRecognizerDirection(UISwipeGestureRecognizerDirection.Right.value()));
        view.addGestureRecognizer(rightSwipeRecognizer);

        UISwipeGestureRecognizer leftSwipeRecognizer = new UISwipeGestureRecognizer();
        leftSwipeRecognizer.addListener(new OnSwipeListener());
        leftSwipeRecognizer.setDelegate(this);
        leftSwipeRecognizer.setDirection(new UISwipeGestureRecognizerDirection(UISwipeGestureRecognizerDirection.Left.value()));
        view.addGestureRecognizer(leftSwipeRecognizer);

        UISwipeGestureRecognizer downSwipeRecognizer = new UISwipeGestureRecognizer();
        downSwipeRecognizer.addListener(new OnSwipeListener());
        downSwipeRecognizer.setDelegate(this);
        downSwipeRecognizer.setDirection(new UISwipeGestureRecognizerDirection(UISwipeGestureRecognizerDirection.Down.value()));
        view.addGestureRecognizer(downSwipeRecognizer);

        UISwipeGestureRecognizer upSwipeRecognizer = new UISwipeGestureRecognizer();
        upSwipeRecognizer.addListener(new OnSwipeListener());
        upSwipeRecognizer.setDelegate(this);
        upSwipeRecognizer.setDirection(new UISwipeGestureRecognizerDirection(UISwipeGestureRecognizerDirection.Up.value()));
        view.addGestureRecognizer(upSwipeRecognizer);

        // ADD SWIPES FOR EACH DIRECTION HERE

        UIPanGestureRecognizer panRecognizer = new UIPanGestureRecognizer();
        panRecognizer.addListener(new OnPanListener());
        panRecognizer.setDelegate(this);
        view.addGestureRecognizer(panRecognizer);

        // Long press recognizer.
        UILongPressGestureRecognizer pressRecognizer = new UILongPressGestureRecognizer();
        pressRecognizer.addListener(new OnLongPressListener());
        view.addGestureRecognizer(pressRecognizer);

        // Pinch recognizer.
        UIPinchGestureRecognizer pinchRecognizer = new UIPinchGestureRecognizer();
        pinchRecognizer.addListener(new OnPinchListener());
        view.addGestureRecognizer(pinchRecognizer);
    }
    
    public void Resume()
    {
        if (!appPaused)
            return;
        appPaused = false;
    }
    
    public void Pause()
    {
        if (appPaused)
            return;
        appPaused = true;
    }
    
    @Override
    public void draw(GLKView view, CGRect rect)
    {
        MakeCurrent();
        /*
        GLKView resets the viewport on each draw call, so we have to set it back.
        To deal with the issue, IOSGraphics stores the last known data of
        the viewport so we can reset it.
        */
        graphics.glViewport(IOSOpenGL.x, IOSOpenGL.y, IOSOpenGL.width, IOSOpenGL.height);
        
        if (!created)
        {
            graphics.glViewport(0, 0, width, height);
            // Create the game's Painter2D.
            //Painter2D painter = new Painter2D();
            ((quorum.Libraries.Game.IOSApplication)GameStateManager.application).plugin_.game.InitializeLayers();

            GameStateManager.nativeGraphics.SetClearScreenColor(0.85f, 0.85f, 0.85f, 1.0f);

            app.game.CreateGame();
            //app.game.Resize(width, height);
            created = true;
        }
        if (appPaused)
        {
            return;
        }
        
        long time = System.nanoTime();
        deltaTime = (time - lastFrameTime) / 1000000000.0f;
        lastFrameTime = time;
        totalTime = totalTime + deltaTime;
        
        frames++;
        if (time - framesStart >= 1000000000l) 
        {
            framesStart = time;
            fps = frames;
            frames = 0;
        }
        
        // NOTE: ProcessEvents sets up the List of events for the input. Actual
        // input handling is processed during ContinueGame() in Game.quorum.
        input.ProcessEvents();
        frameID++;
        app.game.ContinueGame();
    }
    
    void MakeCurrent()
    {
        EAGLContext.setCurrentContext(context);
    }
    
    @Override
    public void update(GLKViewController controller)
    {
        MakeCurrent();
        //app.processRunnables();
        
        /*
        Pause the GLKViewController render loop if we are no longer continuous
        and if we haven't requested a frame in the last loop iteration.
        */
        if (!isContinuous && !isFrameRequested)
        {
            viewController.setPaused(true);
        }
        isFrameRequested = false;
    }
    
    @Override
    public void willPause(GLKViewController controller, boolean pause)
    {
        
    }
    
    /* In libGDX this exists due to extension of the Graphics interface, but
    this plugin has no such inheritance.
    @Override
    public GL20 getGL20()
    {
        return gl20;
    }
    */
    
    public int GetWidth()
    {
        return width;
    }
    
    public int GetHeight()
    {
        return height;
    }
    
    public double GetSecondsBetweenFrames()
    {
        return deltaTime;
    }
    
    public double GetSecondsSinceStart()
    {
        return totalTime;
    }
    
    public int GetFramesPerSecond()
    {
        return fps;
    }
    
    public double GetHorizontalPixelsPerInch()
    {
        return ppiX;
    }
    
    public double GetVerticalPixelsPerInch()
    {
        return ppiY;
    }
    
    public double GetHorizontalPixelsPerCentimeter()
    {
        return ppcX;
    }
    
    public double GetVerticalPixelsPerCentimeter()
    {
        return ppcY;
    }
    
    public double GetPixelDensity()
    {
        return density;
    }
    
    public boolean GetResize()
    {
        return false;
    }
    
    public boolean SetDisplayMode(int width, int height, boolean fullscreen)
    {
        return false;
    }
    
    public void SetVSync(boolean vsync)
    {
        // Do nothing.
    }
    
    public void SetContinuousRendering(boolean isContinuous)
    {
        if (isContinuous != this.isContinuous)
        {
            this.isContinuous = isContinuous;
            
            // If we were not continuous, but now we are, start the GLKViewController.
            if (isContinuous)
                viewController.setPaused(false);
        }
    }
    
    public boolean IsContinuouslyRendering()
    {
        return isContinuous;
    }
    
    public void RequestRendering()
    {
        isFrameRequested = true;
        // Start the GLKViewController if we are in non-continuous mode.
        if (!isContinuous)
            viewController.setPaused(false);
    }
    
    public boolean IsFullscreen()
    {
        return true;
    }

    private class OnTapListener implements UIGestureRecognizer.OnGestureListener
    {
        @Override
        public void onGesture(UIGestureRecognizer gestureRecognizer)
        {
            input.AddSingleTapEvent(gestureRecognizer);
        }
    }

    private class OnDoubleTapListener implements UIGestureRecognizer.OnGestureListener
    {
        @Override
        public void onGesture(UIGestureRecognizer gestureRecognizer)
        {
            input.AddDoubleTapEvent(gestureRecognizer);
        }
    }

    private class OnSwipeListener implements UIGestureRecognizer.OnGestureListener
    {
        @Override
        public void onGesture(UIGestureRecognizer gestureRecognizer)
        {
            input.AddSwipeEvent(gestureRecognizer);
        }
    }

    private class OnPanListener implements UIGestureRecognizer.OnGestureListener
    {
        @Override
        public void onGesture(UIGestureRecognizer gestureRecognizer)
        {
            input.AddPanEvent(gestureRecognizer);
        }
    }

    private class OnLongPressListener implements UIGestureRecognizer.OnGestureListener
    {
        @Override
        public void onGesture(UIGestureRecognizer gestureRecognizer)
        {
            input.AddLongPressEvent(gestureRecognizer);
        }
    }

    private class OnPinchListener implements UIGestureRecognizer.OnGestureListener
    {
        @Override
        public void onGesture(UIGestureRecognizer gestureRecognizer)
        {
            UIGestureRecognizerState state = gestureRecognizer.getState();

            if (state.value() == 1)
                input.AddPinchBeginEvent(gestureRecognizer);
            else if (state.value() == 2)
                input.AddPinchContinueEvent(gestureRecognizer);
            else if (state.value() == 3)
                input.AddPinchEndEvent(gestureRecognizer);
            else
                IOSApplication.GlobalLog("WARNING: Couldn't identify pinch, state = " + state.value() + " or " + state.name());
        }
    }

}
