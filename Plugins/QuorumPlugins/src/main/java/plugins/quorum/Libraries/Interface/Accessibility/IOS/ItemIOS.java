package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSMutableArray;
import org.robovm.apple.uikit.*;
import org.robovm.objc.Selector;
import org.robovm.objc.block.Block1;
import plugins.quorum.Libraries.Game.GameStateManager;
import plugins.quorum.Libraries.Game.IOSApplication;
import plugins.quorum.Libraries.Game.IOSInput;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;

import java.util.HashSet;
import java.util.Set;

public class ItemIOS extends UIAccessibilityElement implements UIAccessibilityAction, UIAccessibilityFocus {
    @Override
    public boolean isAccessibilityElement() {
        return true;
    }

    public ItemIOS(UIAccessibilityContainer container)
    {
        super(container);
        accessibilityCustomRotors = new NSArray<UIAccessibilityCustomRotor>(buildRotor());
//        customActions = new NSArray<>();
//        customActions.add(MakeNextFocusCustomAction());
//        customActions.add(MakePreviousFocusCustomAction());
    }

    private NSArray<UIAccessibilityCustomAction> customActions;
    private Set<String> assistiveTechnologyFocusedIdentifiers;
    public NSArray<UIAccessibilityCustomRotor> accessibilityCustomRotors;

    private UIAccessibilityCustomRotor buildRotor()
    {
        UIAccessibilityCustomRotor rotor = new UIAccessibilityCustomRotor();
        rotor.setName("Quorum Navigation");
        UIAccessibilityElement target = this;
        Block1<UIAccessibilityCustomRotorSearchPredicate, UIAccessibilityCustomRotorItemResult> block = uiAccessibilityCustomRotorSearchPredicate -> {
            if (uiAccessibilityCustomRotorSearchPredicate.getSearchDirection() == UIAccessibilityCustomRotorDirection.Next) {
                item.GetNextFocus().Focus();
                System.out.println("Rotor Forward");
            } else {
                item.GetPreviousFocus().Focus();
                System.out.println("Rotor Backward");
            }
            return new UIAccessibilityCustomRotorItemResult(target, null);
        };
        rotor.setItemSearchBlock(block);
        return rotor;
    }

    Item_ item;
    boolean focused = false;

    public Item_ GetItem() {
        return this.item;
    }
    public void SetItem(Item_ item)
    {
        this.item = item;
    }

    public void setAccessibilityCustomRotors(NSArray<UIAccessibilityCustomRotor> accessibilityCustomRotors) {
        this.accessibilityCustomRotors = accessibilityCustomRotors;
    }

    public NSArray<UIAccessibilityCustomRotor> getAccessibilityCustomRotors() {
        return accessibilityCustomRotors;
    }

    public void Initialize(Item_ item) {
        this.item = item;
        UIAccessibilityTraits traits = UIAccessibilityTraits.None;
        this.setAccessibilityTraits(traits);
        customActions = new NSArray<UIAccessibilityCustomAction>();
        assistiveTechnologyFocusedIdentifiers = new HashSet<String>();
    }

    @Override
    public CGRect getAccessibilityFrame() {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (item instanceof Item2D_)
        {
            double itemX = ((Item2D_)item).GetScreenX();
            double itemY = (((Item2D_)item).GetScreenY());
            if (Double.isNaN(itemX)) {
                itemX = 0;
            }

            if (Double.isNaN(itemY)) {
                itemY = 0;
            }

            width = (int) (((Item2D_) item).GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int) (((Item2D_) item).GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int)(itemX / IOSApplication.containerScaleFactorWidth);
            y = (int)(IOSApplication.accessibilityContainerBounds.getHeight() - ( height + (itemY / IOSApplication.containerScaleFactorHeight)));
        }
        else if (item instanceof Item3D_) {
            // This is only a place holder, to place a small box roughly at the
            // center of a 3D object in the screen. To calculate this correctly,
            // check how we calculate mouse input detection for 3D objects.
            Rectangle_ rectangle = ((Item3D_) item).GetScreenBounds();

            width = (int) (rectangle.GetWidth() / IOSApplication.containerScaleFactorWidth);
            height = (int) (rectangle.GetY() + rectangle.GetHeight() / IOSApplication.containerScaleFactorHeight);
            x = (int) (rectangle.GetX() / IOSApplication.containerScaleFactorWidth);
            y = (int) (IOSApplication.accessibilityContainerBounds.getHeight() - (height + (rectangle.GetY() / IOSApplication.containerScaleFactorHeight)));
        }

        return new CGRect(x, y, width, height);
    }
    private UIAccessibilityCustomAction MakeNextFocusCustomAction() {
        UIAccessibilityCustomAction action = new UIAccessibilityCustomAction();
        action.setName("Next Focus");
        action.setTarget(this);
        action.setSelector(Selector.register("GetNextFocus"));
        return action;
    }

    private  UIAccessibilityCustomAction MakePreviousFocusCustomAction() {
        UIAccessibilityCustomAction action = new UIAccessibilityCustomAction();
        action.setName("Previous Focus");
        action.setTarget(this);
        action.setSelector(Selector.register("GetPreviousFocus"));
        return action;
    }

    // These will tell Quorum to focus on the next item but voiceover will not be notified as they are now
    public void GetNextFocus() {
        item.GetNextFocus().Focus();
    }

    public void GetPreviousFocus() {
        item.GetPreviousFocus().Focus();
    }

    @Override
    public NSArray<UIAccessibilityCustomAction> getAccessibilityCustomActions() {
        return customActions;
    }

    @Override
    public void setAccessibilityCustomActions(NSArray<UIAccessibilityCustomAction> v) {
        customActions = v;
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void increment() {

    }

    @Override
    public void decrement() {

    }

    @Override
    public boolean scroll(UIAccessibilityScrollDirection direction) {
        return false;
    }

    @Override
    public boolean performEscape() {
        return false;
    }

    @Override
    public boolean performMagicTap() {
        return false;
    }

    /*
        VoiceOver's zoom action (iOS 17 and later). VoiceOver takes over the
        pinch gesture, so an item that listens for gestures says it supports
        zoom, and each zoom in or out reaches it as one pinch step (see
        IOSInput.AddAccessibilityPinchEvents). 1.5 matches the pinch size
        Android marks with isPinch.
     */
    static final double ZOOM_STEP = 1.5;

    public boolean HasGestureListeners() {
        return item != null && item.GetGestureListeners().HasNext();
    }

    @Override
    public UIAccessibilityTraits getAccessibilityTraits() {
        UIAccessibilityTraits traits = super.getAccessibilityTraits();
        if (HasGestureListeners()) {
            return new UIAccessibilityTraits(traits.value() | UIAccessibilityTraits.SupportsZoomValue.value());
        }
        return traits;
    }

    @Override
    public boolean zoomInAtPoint(CGPoint point) {
        return Zoom(point, ZOOM_STEP);
    }

    @Override
    public boolean zoomOutAtPoint(CGPoint point) {
        return Zoom(point, 1.0 / ZOOM_STEP);
    }

    /*
        The pinch goes to whatever is under the point, so a point outside this
        item (VoiceOver may give the element's center, or nothing) is moved to
        the item's center. The game handles the pinch in its next frame, so
        true means the item listens for it, not that it zoomed.
     */
    private boolean Zoom(CGPoint point, double scale) {
        if (!HasGestureListeners()) {
            return false;
        }

        CGRect frame = getAccessibilityFrame();
        if (point == null || !frame.contains(point)) {
            point = new CGPoint(frame.getMidX(), frame.getMidY());
        }

        IOSInput input = ((quorum.Libraries.Game.IOSInput) GameStateManager.input).plugin_;
        input.AddAccessibilityPinchEvents(point, scale);
        return true;
    }

    /*
        This function tells the item that it has the focus. By default, this does nothing. Subclasses may need
        to trigger iOS specific things when this happens.
     */
    public void Focus() {
        System.out.println("Focus from ItemIOS to " + item.GetName());
    }

    public void FocusLost() {
        System.out.println("Lost focus from " + item.GetName());
    }

    @Override
    public void didBecomeFocused() {
        System.out.println(item.GetName() + " gained focus through IOS");
        item.Focus();
        focused = true;
    }

    @Override
    public void didLoseFocus() {
        System.out.println(item.GetName() + " lost focus from IOS");
        focused = false;
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    @Override
    public Set<String> getAssistiveTechnologyFocusedIdentifiers() {
        return assistiveTechnologyFocusedIdentifiers;
    }
}

