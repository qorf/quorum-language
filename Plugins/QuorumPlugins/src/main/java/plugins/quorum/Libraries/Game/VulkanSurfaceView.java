package plugins.quorum.Libraries.Game;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import plugins.quorum.Libraries.Interface.Mobile.AndroidTextInput;
import plugins.quorum.Libraries.Interface.Accessibility.AndroidAccessibilityProvider;

/*
    The view Vulkan draws on (AndroidConfiguration's useVulkan). A plain
    SurfaceView takes no touches, so this one passes them to Quorum the way
    the OpenGL view (GLSurfaceView20) does: each touch to AndroidInput, and
    through a gesture detector for taps, swipes, long presses and pans.
 */
public class VulkanSurfaceView extends SurfaceView
{
    private final GestureDetector gestureDetector;

    public VulkanSurfaceView(Context context)
    {
        super(context);
        gestureDetector = new GestureDetector(context, new GLSurfaceView20.QuorumGestureTranslator());
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    // True, so the rest of the touch (moves, the lift) comes here too.
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        quorum.Libraries.Game.AndroidInput input = (quorum.Libraries.Game.AndroidInput)GameStateManager.input;
        if (input == null)
            return false;
        input.plugin_.AddEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    // The keyboard types into Quorum's focused text control (AndroidTextInput).
    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs)
    {
        return AndroidTextInput.CreateConnection(this, outAttrs);
    }

    @Override
    public boolean onCheckIsTextEditor()
    {
        return AndroidTextInput.IsActive();
    }

    // TalkBack's tree of Quorum's items (AndroidAccessibilityProvider).
    @Override
    public AccessibilityNodeProvider getAccessibilityNodeProvider()
    {
        return AndroidAccessibilityProvider.Get(this);
    }

    // Explore by touch: TalkBack reads what is under the finger.
    @Override
    public boolean dispatchHoverEvent(MotionEvent event)
    {
        if (AndroidAccessibilityProvider.Get(this).OnHover(event))
            return true;
        return super.dispatchHoverEvent(event);
    }
}
