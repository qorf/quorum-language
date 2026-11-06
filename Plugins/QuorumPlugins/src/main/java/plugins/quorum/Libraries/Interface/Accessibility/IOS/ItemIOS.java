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
import plugins.quorum.Libraries.Interface.Accessibility.IOSAccessibility;
import quorum.Libraries.Game.Shapes.Rectangle_;
import quorum.Libraries.Interface.Item2D_;
import quorum.Libraries.Interface.Item3D_;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Controls.RadioButton_;

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
        Block1<UIAccessibilityCustomRotorSearchPredicate, UIAccessibilityCustomRotorItemResult> block = uiAccessibilityCustomRotorSearchPredicate -> {
            boolean next = uiAccessibilityCustomRotorSearchPredicate.getSearchDirection() == UIAccessibilityCustomRotorDirection.Next;
            Item_ target = GetFocusNeighbor(item, next);
            if (target == null) {
                return null;
            }
            target.Focus();

            UIAccessibilityElement element = IOSAccessibility.GetVoiceOverTarget(target);
            if (element == null) {
                return null;
            }
            return new UIAccessibilityCustomRotorItemResult(element, null);
        };
        rotor.setItemSearchBlock(block);
        return rotor;
    }

    Item_ item;
    boolean focused = false;

    // The element VoiceOver is on, or null.
    static ItemIOS voiceOverFocus = null;

    public static ItemIOS GetVoiceOverFocus() {
        return voiceOverFocus;
    }

    public static void ClearVoiceOverFocus(ItemIOS element) {
        if (voiceOverFocus == element) {
            voiceOverFocus = null;
        }
    }

    /*
        The next or previous item in Quorum's focus order. Items that aren't
        focusable themselves, such as tree and list items, have none, so their
        parent's (the tree's or list's) is used.
     */
    static Item_ GetFocusNeighbor(Item_ start, boolean next) {
        Item_ current = start;
        while (current != null) {
            Item_ neighbor = next ? current.GetNextFocus() : current.GetPreviousFocus();
            if (neighbor != null) {
                return neighbor;
            }
            current = current instanceof Item2D_ ? ((Item2D_) current).GetParent() : null;
        }
        return null;
    }

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

    /*
        Subclasses set their traits before calling this, so it leaves the
        traits alone (a new element has none).
     */
    public void Initialize(Item_ item) {
        this.item = item;
        customActions = new NSArray<UIAccessibilityCustomAction>();
        assistiveTechnologyFocusedIdentifiers = new HashSet<String>();
    }

    /*
        Sets what VoiceOver reads: the item's name, and its description as a
        hint when it says something the name doesn't. Subclasses read other
        text (a label's text, a list item's text).
     */
    public void RefreshText() {
        String name = item.GetName();
        String description = item.GetDescription();
        setAccessibilityLabel(name);
        setAccessibilityValue(null);
        if (description != null && !description.isEmpty() && !description.equals(name)) {
            setAccessibilityHint(description);
        } else {
            setAccessibilityHint(null);
        }
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
    }

    public void FocusLost() {
    }

    @Override
    public void didBecomeFocused() {
        focused = true;
        voiceOverFocus = this;
        // Where VoiceOver went, to see where it jumps from and to.
        if (IOSAccessibility.debug) {
            System.out.println("VoiceOver moved to " + getClass().getSimpleName() + " \"" + getAccessibilityLabel() + "\"");
        }
        // A radio button takes the focus without being selected: a double
        // tap selects it (RadioButtonIOS.activate).
        if (item instanceof RadioButton_) {
            ((RadioButton_) item).FocusWithoutSelecting();
        } else {
            item.Focus();
        }
    }

    @Override
    public void didLoseFocus() {
        focused = false;
        ClearVoiceOverFocus(this);
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

