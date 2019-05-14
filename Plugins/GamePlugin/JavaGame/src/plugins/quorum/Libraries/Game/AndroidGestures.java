package plugins.quorum.Libraries.Game;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.GestureDetector;
import quorum.Libraries.Interface.Events.TouchEvent_;

public class AndroidGestures extends Activity{
    public Object me_;
    private final String DEBUG_TAG = "AndroidGestures";
    private final GestureDetector detector = new GestureDetector(getActivity(), new MyGestureListener());
    
    
//    public void HelloWorld(){
//        Log.d(DEBUG_TAG, "Hello world!");
//    }
    
//    public void TestGesture(TouchEvent_ e){ 
//        /* Testing that the event passed in has the proper values for 
//        appropriate touch events
//        int action = e.Get_Libraries_Interface_Events_TouchEvent__eventType_();
//        switch(action){
//            case (0):
//                Log.d(DEBUG_TAG, "Finger pressed down");
//                break;
//            case (1):
//                Log.d(DEBUG_TAG, "Finger moving around");
//                break;
//            case (2):
//                Log.d(DEBUG_TAG, "Finger is stationary");
//                break;
//            case (3):
//                Log.d(DEBUG_TAG, "Finger lifted up");
//                break;
//            default:
//                Log.d(DEBUG_TAG, "Unexplained behavior");
//                break;
//        }
//        */
//    }
    
//    public void PrintStuff(String input){
//        Log.d(DEBUG_TAG, input);
//    }
    
    private Activity getActivity(){
        Activity activity = plugins.quorum.Libraries.Game.AndroidApplication.GetActivity();
        return activity;
    }
    
    private Context getContext(){
        Context context = getActivity().getApplicationContext();
        return context;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d(DEBUG_TAG, "Inside onTouchEvent");
        if(detector.onTouchEvent(event)) {
            Log.d(DEBUG_TAG, "Gesture recognized");
            return true;
        }
        Log.d(DEBUG_TAG, "Gesture not recognized");
        return false;
        // return super.onTouchEvent(event);
        }
    
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent event){
            Log.d(DEBUG_TAG, "Made it into onDown recognition");
            return true;
        }
        
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            Log.d(DEBUG_TAG, "Made it into onFling recognition");
            return true;
        }
        
        @Override
        public boolean onSingleTapUp(MotionEvent event){
            Log.d(DEBUG_TAG, "Made it into onSingleTapUp recognition");
            return true;
        }
        
        @Override
        public void onLongPress(MotionEvent event){
            Log.d(DEBUG_TAG, "Made it into onLongPress recognition"); 
        }
        
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY){
            Log.d(DEBUG_TAG, "Made it into onScroll recognition");
            return true;
        }
        
        @Override
        public boolean onDoubleTap(MotionEvent event){
            Log.d(DEBUG_TAG, "Made it into onDoubleTap recognition");
            return true;
        }
    }
}