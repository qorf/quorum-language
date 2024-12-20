/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Game;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import org.robovm.apple.audiotoolbox.AudioServices;
import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.foundation.NSExtensions;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSRange;
import org.robovm.apple.foundation.NSString;
import org.robovm.apple.uikit.*;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.VM;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.Pointer;

import plugins.quorum.Libraries.Game.libGDX.Array;
import plugins.quorum.Libraries.Game.libGDX.Pool;

import plugins.quorum.Libraries.Interface.Accessibility.IOS.TextAdjust;
import plugins.quorum.Libraries.Interface.Accessibility.IOS.TextFieldIOS;
import quorum.Libraries.Game.IOSConfiguration_;
import quorum.Libraries.Game.IOSConfiguration;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Events.GestureEvent;
import quorum.Libraries.Interface.Events.TouchEvent;
import quorum.Libraries.Containers.List_;
import quorum.Libraries.Containers.List;
import quorum.Libraries.Interface.Item_;

/**
 *
 * @author alleew
 */
public class IOSInput 
{
    public java.lang.Object me_ = null;
    
    static final int MAX_TOUCHES = 20;

    public TextAdjust getFocusedTextAdjust() {
        return focusedTextAdjust;
    }

    public void setFocusedTextAdjust(TextAdjust focusedTextField) {
        this.focusedTextAdjust = focusedTextField;
    }

    private static class NSObjectWrapper<T extends NSObject> 
    {
        private static final long HANDLE_OFFSET;
        static 
        {
            try 
            {
                HANDLE_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(NativeObject.class.getDeclaredField("handle")));
            }
            catch (Throwable t) 
            {
                throw new Error(t);
            }
        }

        private final T instance;

        public NSObjectWrapper (Class<T> cls) 
        {
            instance = VM.allocateObject(cls);
        }

        public T wrap (long handle) 
        {
            VM.setLong(VM.getObjectAddress(instance) + HANDLE_OFFSET, handle);
            return instance;
        }
    }

    private static final NSObjectWrapper<UITouch> UI_TOUCH_WRAPPER = new NSObjectWrapper<UITouch>(UITouch.class);
    //static final NSObjectWrapper<UIAcceleration> UI_ACCELERATION_WRAPPER = new NSObjectWrapper<UIAcceleration>(UIAcceleration.class);
    
    IOSApplication app;
    IOSConfiguration_ config;
    int[] deltaX = new int[MAX_TOUCHES];
    int[] deltaY = new int[MAX_TOUCHES];
    int[] touchX = new int[MAX_TOUCHES];
    int[] touchY = new int[MAX_TOUCHES];
    // we store the pointer to the UITouch struct here, or 0
    long[] touchDown = new long[MAX_TOUCHES];
    int numTouched = 0;
    boolean justTouched = false;
    
    List_ touchEventList;
    List_ gestureEventList;
    
    Pool<TouchEvent> touchEventPool = new Pool<TouchEvent>() 
        {
            @Override
            protected TouchEvent newObject () 
            {
                return new TouchEvent();
            }
        };
    Array<TouchEvent> touchEvents = new Array<TouchEvent>();
    //TouchEvent currentEvent = null;
    
    float[] acceleration = new float[3];
    float[] rotation = new float[3];
    float[] R = new float[9];
    //InputProcessor inputProcessor = null;

    boolean hasVibrator;
    //CMMotionManager motionManager;
    //UIAccelerometerDelegate accelerometerDelegate;
    boolean compassSupported;
    boolean keyboardCloseOnReturn;

    public void Initialize(IOSApplication app) 
    {
        this.app = app;
        this.config = app.config;
        this.keyboardCloseOnReturn = ((IOSConfiguration)app.config).keyboardCloseOnReturn;
    }

    void SetUpPeripherals () 
    {
        //motionManager = new CMMotionManager();
        SetUpAccelerometer();
        SetUpCompass();
        UIDevice device = UIDevice.getCurrentDevice();

        if (device.getModel().equalsIgnoreCase("iphone"))
            hasVibrator = true;
    }

    private void SetUpCompass () 
    {
        if (((IOSConfiguration)config).useCompass) 
        {
            //setupMagnetometer();
        }
    }

    private void SetUpAccelerometer () 
    {
        /* NOTE: Accelerometer is not implemented in first pass of iOS input.
        
        if (config.useAccelerometer) 
        {
            accelerometerDelegate = new UIAccelerometerDelegateAdapter() 
            {
                
                @Method(selector = "accelerometer:didAccelerate:")
                public void didAccelerate (UIAccelerometer accelerometer, @Pointer long valuesPtr) 
                {
                    UIAcceleration values = UI_ACCELERATION_WRAPPER.wrap(valuesPtr);
                    float x = (float)values.getX() * 10;
                    float y = (float)values.getY() * 10;
                    float z = (float)values.getZ() * 10;

                    acceleration[0] = -x;
                    acceleration[1] = -y;
                    acceleration[2] = -z;
                }
            };
            UIAccelerometer.getSharedAccelerometer().setDelegate(accelerometerDelegate);
            UIAccelerometer.getSharedAccelerometer().setUpdateInterval(config.accelerometerUpdate);
        }
        
        */
    }

    /*
    public float getAccelerometerX () {
            return acceleration[0];
    }

    public float getAccelerometerY () {
            return acceleration[1];
    }

    public float getAccelerometerZ () {
            return acceleration[2];
    }


    public float getAzimuth () {
            if (!compassSupported) return 0;
            return rotation[0];
    }

    public float getPitch () {
            if (!compassSupported) return 0;
            return rotation[1];
    }

    public float getRoll () {
            if (!compassSupported) return 0;
            return rotation[2];
    }

    public void getRotationMatrix (float[] matrix) {
            if (matrix.length != 9) return;
            //TODO implement when azimuth is fixed
    }
    */

    public int GetX() 
    {
        return touchX[0];
    }

    public int GetX(int pointer) 
    {
        return touchX[pointer];
    }

    public int GetDeltaX() 
    {
        return deltaX[0];
    }

    public int GetDeltaX(int pointer) 
    {
        return deltaX[pointer];
    }

    public int GetY() 
    {
        return touchY[0];
    }

    public int GetY(int pointer) 
    {
        return touchY[pointer];
    }

    public int GetDeltaY () 
    {
        return deltaY[0];
    }

    public int GetDeltaY(int pointer) 
    {
        return deltaY[pointer];
    }

    public boolean IsTouched () 
    {
        for (int pointer = 0; pointer < MAX_TOUCHES; pointer++) 
        {
            if (touchDown[pointer] != 0) 
            {
                return true;
            }
        }
        return false;
    }

    public boolean JustTouched () 
    {
        return justTouched;
    }

    public boolean IsTouched(int pointer)
    {
        return touchDown[pointer] != 0;
    }

//    public void getTextInput(TextInputListener listener, String title, String text, String hint) {
//            buildUIAlertView(listener, title, text, hint).show();
//    }

    // hack for software keyboard support
    // uses a hidden textfield to capture input
    // see: http://www.badlogicgames.com/forum/viewtopic.php?f=17&t=11788

    private class HiddenTextField extends UITextField {
            public HiddenTextField (CGRect frame) {
                    super(frame);

                    setKeyboardType(UIKeyboardType.Default);
                    setReturnKeyType(UIReturnKeyType.Done);
                    setAutocapitalizationType(UITextAutocapitalizationType.None);
                    setAutocorrectionType(UITextAutocorrectionType.No);
                    setSpellCheckingType(UITextSpellCheckingType.No);
                    setHidden(true);
            }

            @Override
            public void deleteBackward () {
//                    app.input.inputProcessor.keyTyped((char)8);
                    super.deleteBackward();
//                    Gdx.graphics.requestRendering();
            }
    }

    private TextAdjust focusedTextAdjust = null;


    private UITextField textfield = null;
    private final UITextFieldDelegate textDelegate = new UITextFieldDelegateAdapter() {
            @Override
            public boolean shouldChangeCharacters (UITextField textField, NSRange range, String string) {
                if(focusedTextAdjust == null) {
                    return false;
                }

                //the user clicked delete, I "think"
                if(string == null || string.isEmpty()) {
                    focusedTextAdjust.DeleteBackward();
                } else {
                    char[] chars = new char[string.length()];
                    string.getChars(0, string.length(), chars, 0);
                    for (int i = 0; i < chars.length; i++) {
                        focusedTextAdjust.Insert(chars[i] + "");
                    }
                }

                focusedTextAdjust.setAccessibilityValue(focusedTextAdjust.GetText());
                return true;
            }

            @Override
            public boolean shouldEndEditing (UITextField textField) {
                System.out.println("Triggered should end editing");
                    // Text field needs to have at least one symbol - so we can use backspace
                textField.setText("x");
//                    Gdx.graphics.requestRendering();

                    return true;
            }

        public boolean shouldClear(UITextField textField) {
            System.out.println("Should clear");
            return true;
        }

        public void textFieldDidChangeSelection(UITextField textField) {
            UITextRange range = textField.getSelectedTextRange();
        }

        @Override
        public boolean shouldReturn (UITextField textField) {
            System.out.println("Triggered should return");
                if (keyboardCloseOnReturn) setOnscreenKeyboardVisible(false);
//                    app.input.inputProcessor.keyDown(Keys.ENTER);
//                    app.input.inputProcessor.keyTyped((char)13);
//                    Gdx.graphics.requestRendering();
                return false;
            }
    };

    public void setOnscreenKeyboardVisible (boolean visible) {
        System.out.println("Set on screen visible: " + visible);
        if (textfield == null) createDefaultTextField();
        if (visible) {
            System.out.println("Visible");
            textfield.becomeFirstResponder();
            textfield.setDelegate(textDelegate);
        } else {
            System.out.println("Not visible");
            textfield.resignFirstResponder();
        }
    }
    
    /**
     * Set the keyboard to close when the UITextField return key is pressed
     * @param shouldClose Whether or not the keyboard should clsoe on return key press
     */
    public void setKeyboardCloseOnReturnKey (boolean shouldClose) {
            keyboardCloseOnReturn = shouldClose;
    }

    public UITextField getKeyboardTextField () {
            if (textfield == null) createDefaultTextField();
            return textfield;
    }

    private void createDefaultTextField () {
            textfield = new UITextField(new CGRect(10, 10, 100, 50));
            //Parameters
            // Setting parameters
            textfield.setKeyboardType(UIKeyboardType.Default);
            textfield.setReturnKeyType(UIReturnKeyType.Done);
            textfield.setAutocapitalizationType(UITextAutocapitalizationType.None);
            textfield.setAutocorrectionType(UITextAutocorrectionType.No);
            textfield.setSpellCheckingType(UITextSpellCheckingType.No);
            textfield.setHidden(true);
            // Text field needs to have at least one symbol - so we can use backspace
            textfield.setText("x");
//            app.getUIViewController().getView().addSubview(textfield);
    }
    
    // Issue 773 indicates this may solve a premature GC issue
    UIAlertViewDelegate delegate;

//    /** Builds an {@link UIAlertView} with an added {@link UITextField} for inputting text.
//     * @param listener Text input listener
//     * @param title Dialog title
//     * @param text Text for text field
//     * @return UiAlertView */
//    private UIAlertView buildUIAlertView (final TextInputListener listener, String title, String text, String placeholder) {
//            delegate = new UIAlertViewDelegateAdapter() {
//                    @Override
//                    public void clicked (UIAlertView view, long clicked) {
//                            if (clicked == 0) {
//                                    // user clicked "Cancel" button
//                                    listener.canceled();
//                            } else if (clicked == 1) {
//                                    // user clicked "Ok" button
//                                    UITextField textField = view.getTextField(0);
//                                    listener.input(textField.getText());
//                            }
//                            delegate = null;
//                    }
//
//                    @Override
//                    public void cancel (UIAlertView view) {
//                            listener.canceled();
//                            delegate = null;
//                    }
//            };
//
//            // build the view
//            final UIAlertView uiAlertView = new UIAlertView();
//            uiAlertView.setTitle(title);
//            uiAlertView.addButton("Cancel");
//            uiAlertView.addButton("Ok");
//            uiAlertView.setAlertViewStyle(UIAlertViewStyle.PlainTextInput);
//            uiAlertView.setDelegate(delegate);
//
//            UITextField textField = uiAlertView.getTextField(0);
//            textField.setPlaceholder(placeholder);
//            textField.setText(text);
//
//            return uiAlertView;
//    }

    public void Vibrate (int milliseconds) 
    {
        AudioServices.playSystemSound(4095);
    }

    public void vibrate (long[] pattern, int repeat) {
            // FIXME implement this
    }

    public void cancelVibrate () {
            // FIXME implement this
    }

    /*
    public long getCurrentEventTime () 
    {
            return currentEvent.timestamp;
    }
    

    public void setCatchBackKey (boolean catchBack) {
    }

    public boolean isCatchBackKey () {
            return false;
    }

    public void setCatchMenuKey (boolean catchMenu) {
    }

    public boolean isCatchMenuKey() {
            return false;
    }
    

    public void setInputProcessor (InputProcessor processor) {
            this.inputProcessor = processor;
    }

    public InputProcessor getInputProcessor () {
            return inputProcessor;
    }

    public boolean isPeripheralAvailable (Peripheral peripheral) {
            if (peripheral == Peripheral.Accelerometer && config.useAccelerometer) return true;
            if (peripheral == Peripheral.MultitouchScreen) return true;
            if (peripheral == Peripheral.Vibrator) return hasVibrator;
            if (peripheral == Peripheral.Compass) return compassSupported;
            // if(peripheral == Peripheral.OnscreenKeyboard) return true;
            return false;
    }
    */
    
    public int GetRotation() 
    {
            // we measure orientation counter clockwise, just like on Android
            switch (app.uiApp.getStatusBarOrientation()) 
            {
                case LandscapeLeft:
                    return 270;
                case PortraitUpsideDown:
                    return 180;
                case LandscapeRight:
                    return 90;
                case Portrait:
                default:
                    return 0;
            }
    }

    /*
    public Orientation getNativeOrientation () {
            switch (app.uiApp.getStatusBarOrientation()) {
            case LandscapeLeft:
            case LandscapeRight:
                    return Orientation.Landscape;
            default:
                    return Orientation.Portrait;
            }
    }

    public void setCursorCatched (boolean catched) {
    }

    public boolean isCursorCatched () {
            return false;
    }

    public void setCursorPosition (int x, int y) {
    }
    */

    protected void OnTouch (long touches) 
    {
        ToTouchEvents(touches);
        app.display.RequestRendering();
    }

    void ProcessEvents () 
    {
        synchronized (touchEvents) 
        {
            justTouched = false;
            for (TouchEvent event : touchEvents) 
            {
                /*
                    The following values are used from TouchEvent.quorum:
                    BEGAN = 0
                    MOVED = 1
                    STATIONARY = 2
                    ENDED = 3
                    CANCELLED = 4
                */
                
                //currentEvent = event;
                if (event.eventType == 0 && numTouched == 1)
                    justTouched = true;
                
                touchEventList.Add(event);
            }
            touchEventPool.freeAll(touchEvents);
            touchEvents.clear();
        }
    }

    private int GetFreePointer() 
    {
        for (int i = 0; i < touchDown.length; i++) 
        {
                if (touchDown[i] == 0)
                    return i;
        }
        // If there's no free pointers, offer up the first one.
        return 0;
    }

    private int FindPointer(UITouch touch) 
    {
        long ptr = touch.getHandle();
        for (int i = 0; i < touchDown.length; i++) 
        {
            if (touchDown[i] == ptr)
                return i;
        }
        return -1;
    }

    private static class NSSetExtensions extends NSExtensions 
    {
        @Method(selector = "allObjects")
        public static native @Pointer long allObjects (@Pointer long thiz);
    }

    private static class NSArrayExtensions extends NSExtensions 
    {
        @Method(selector = "objectAtIndex:")
        public static native @Pointer long objectAtIndex$ (@Pointer long thiz, @MachineSizedUInt long index);

        @Method(selector = "count")
        public static native @MachineSizedUInt long count (@Pointer long thiz);
    }

    private void ToTouchEvents (long touches) 
    {
        long array = NSSetExtensions.allObjects(touches);
        int length = (int)NSArrayExtensions.count(array);
        for (int i = 0; i < length; i++) 
        {
            long touchHandle = NSArrayExtensions.objectAtIndex$(array, i);
            UITouch touch = UI_TOUCH_WRAPPER.wrap(touchHandle);
            final int locX, locY;
            // Get and map the location to our drawing space
            {
                CGPoint loc = touch.getLocationInView(touch.getWindow());
                final CGRect bounds = app.GetCachedBounds();
                
                locX = (int)(loc.getX() * app.displayScaleFactor - bounds.getMinX());

                // We need to invert the y-axis to match the Quorum engine's y-axis.
                locY = (int)(bounds.getMaxY() - (loc.getY() * app.displayScaleFactor));
            }

            synchronized (touchEvents) 
            {
                UITouchPhase phase = touch.getPhase();
                TouchEvent event = touchEventPool.obtain();
                event.x = locX;
                event.y = locY;
                event.eventHandled = false;

                /*
                    The following values are used from TouchEvent.quorum:
                    BEGAN = 0
                    MOVED = 1
                    STATIONARY = 2
                    ENDED = 3
                    CANCELLED = 4

                    phaseType begins with value of CANCELLED before
                    the if statements.
                */
                int phaseType = 4;
                if (phase == UITouchPhase.Began)
                    phaseType = 0;
                else if (phase == UITouchPhase.Moved)
                    phaseType = 1;
                else if (phase == UITouchPhase.Stationary)
                    phaseType = 2;
                else if (phase == UITouchPhase.Ended)
                    phaseType = 3;

                event.eventType = phaseType;
                //event.timestamp = (long)(touch.getTimestamp() * 1000000000);

                if (phase == UITouchPhase.Began) 
                {
                    event.fingerID = GetFreePointer();
                    touchDown[event.fingerID] = touch.getHandle();
                    touchX[event.fingerID] = event.x;
                    touchY[event.fingerID] = event.y;
                    deltaX[event.fingerID] = 0;
                    deltaY[event.fingerID] = 0;
                    numTouched++;
                }

                if (phase == UITouchPhase.Moved || phase == UITouchPhase.Stationary) 
                {
                    event.fingerID = FindPointer(touch);
                    deltaX[event.fingerID] = event.x - touchX[event.fingerID];
                    deltaY[event.fingerID] = event.y - touchY[event.fingerID];
                    touchX[event.fingerID] = event.x;
                    touchY[event.fingerID] = event.y;
                }

                if (phase == UITouchPhase.Cancelled || phase == UITouchPhase.Ended) 
                {
                    event.fingerID = FindPointer(touch);

                    /*
                    There may not be a relevant finger ID if the event was cancelled immediately on touch down due to
                    gesture recognition. In this case, give it a free digit to represent the new finger touching the
                    screen (but don't adjust the number of touches -- a new finger touched the screen and was
                    immediately disregarded, so there's no net change in the number of touches).
                    */
                    if (event.fingerID == -1)
                    {
                        event.fingerID = GetFreePointer();
                    }
                    else
                        numTouched--;

                    touchDown[event.fingerID] = 0;
                    touchX[event.fingerID] = event.x;
                    touchY[event.fingerID] = event.y;
                    deltaX[event.fingerID] = 0;
                    deltaY[event.fingerID] = 0;
                }

                event.movementX = deltaX[event.fingerID];
                event.movementY = deltaY[event.fingerID];
                touchEvents.add(event);
            }
        }
    }

    /*
    static class TouchEvent {
            UITouchPhase phase;
            long timestamp;
            int x, y;
            int pointer;
    }

    public float getGyroscopeX() {
            // TODO Auto-generated method stub
            return 0;
    }

    public float getGyroscopeY() {
            // TODO Auto-generated method stub
            return 0;
    }

    public float getGyroscopeZ() {
            // TODO Auto-generated method stub
            return 0;
    }
    */

    private void InitializeValues(UIGestureRecognizer recognizer, GestureEvent quorumEvent)
    {
        CGPoint point = recognizer.getLocationInView(recognizer.getView());
        final CGRect bounds = app.GetCachedBounds();

        int x = (int)(point.getX() * app.displayScaleFactor - bounds.getMinX());

        // We need to invert the y-axis to match the Quorum engine's y-axis.
        int y = (int)(bounds.getMaxY() - (point.getY() * app.displayScaleFactor));

        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__x_(x);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__y_(y);
    }

    public void AddSingleTapEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.SINGLE_TAP);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddDoubleTapEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.DOUBLE_TAP);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddSwipeEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.SWIPE);

        UISwipeGestureRecognizer swipeRecognizer = (UISwipeGestureRecognizer)recognizer;
        UISwipeGestureRecognizerDirection direction = swipeRecognizer.getDirection();

        long directionValue = direction.value();
        if (directionValue == UISwipeGestureRecognizerDirection.Right.value())
            quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.RIGHT);
        else if (directionValue == UISwipeGestureRecognizerDirection.Left.value())
            quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.LEFT);
        else if (directionValue == UISwipeGestureRecognizerDirection.Up.value())
            quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.UP);
        else if (directionValue == UISwipeGestureRecognizerDirection.Down.value())
            quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.DOWN);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddLongPressEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.LONG_PRESS);
        if (recognizer.getState().value() == UIGestureRecognizerState.Ended.value())
            quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.FINISH);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddPanEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PAN);

        UIPanGestureRecognizer panRecognizer = (UIPanGestureRecognizer)recognizer;
        CGPoint translation = panRecognizer.getTranslation(recognizer.getView());

        int x = (int)(translation.getX() * app.displayScaleFactor);
        int y = (int)(-translation.getY() * app.displayScaleFactor);

        panRecognizer.setTranslation(new CGPoint(0, 0), recognizer.getView());

        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__panDistanceX_(x);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__panDistanceY_(y);

        if (Math.abs(x) > Math.abs(y))
        {
            if (x < 0)
                quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.LEFT);
            else
                quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.RIGHT);
        }
        else
        {
            if (y < 0)
                quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.DOWN);
            else
                quorumEvent.Set_Libraries_Interface_Events_GestureEvent__direction_(quorumEvent.UP);
        }

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddPinchBeginEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PINCH);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.BEGIN);

        UIPinchGestureRecognizer pinchRecognizer = (UIPinchGestureRecognizer)recognizer;
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__scaleFactor_(pinchRecognizer.getScale());
        // To maintain consistent behavior between IOS and Android, reset the recognizer's scale value to 1.0
        // This will make the recognizer give us "per event" scaling, instead of overall scaling
        pinchRecognizer.setScale(1.0);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddPinchContinueEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PINCH);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.CONTINUE);

        UIPinchGestureRecognizer pinchRecognizer = (UIPinchGestureRecognizer)recognizer;
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__scaleFactor_(pinchRecognizer.getScale());
        // To maintain consistent behavior between IOS and Android, reset the recognizer's scale value to 1.0
        // This will make the recognizer give us "per event" scaling, instead of overall scaling
        pinchRecognizer.setScale(1.0);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void AddPinchEndEvent(UIGestureRecognizer recognizer)
    {
        GestureEvent quorumEvent = new GestureEvent();
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__eventType_(quorumEvent.PINCH);
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__timingCode_(quorumEvent.FINISH);

        UIPinchGestureRecognizer pinchRecognizer = (UIPinchGestureRecognizer)recognizer;
        quorumEvent.Set_Libraries_Interface_Events_GestureEvent__scaleFactor_(pinchRecognizer.getScale());
        // To maintain consistent behavior between IOS and Android, reset the recognizer's scale value to 1.0
        // This will make the recognizer give us "per event" scaling, instead of overall scaling
        pinchRecognizer.setScale(1.0);

        InitializeValues(recognizer, quorumEvent);

        gestureEventList.Add(quorumEvent);
    }

    public void InitializePlugin(List_ list, List_ gestureList)
    {
        touchEventList = list;
        gestureEventList = gestureList;
    }
}
