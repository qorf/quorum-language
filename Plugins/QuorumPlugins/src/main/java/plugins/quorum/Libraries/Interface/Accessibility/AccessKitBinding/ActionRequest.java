package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/*
    Something VoiceOver asked a node to do (press it, focus it, set its
    value, move its caret, show its menu, open or close it), from
    accesskit_action_request in accesskit.h 0.23.2. AccessKit's macOS
    adapter sends CLICK, FOCUS, INCREMENT, DECREMENT, SET_VALUE,
    SET_TEXT_SELECTION and SCROLL_INTO_VIEW; Mac.so adds SHOW_CONTEXT_MENU,
    EXPAND and COLLAPSE (AccessKitRuntime.m).

    At most one of value, numericValue and textSelection is set, depending
    on the action. action is null for a value this binding doesn't know
    (a newer AccessKit).
*/
public final class ActionRequest {
    public final Action action;
    public final NodeId target;

    // SET_VALUE with text, or REPLACE_SELECTED_TEXT.
    public final String value;

    // SET_VALUE with a number.
    public final boolean hasNumericValue;
    public final double numericValue;

    // SET_TEXT_SELECTION.
    public final TextSelection textSelection;

    public ActionRequest(Action action, NodeId target, String value,
            boolean hasNumericValue, double numericValue, TextSelection textSelection) {
        this.action = action;
        this.target = target;
        this.value = value;
        this.hasNumericValue = hasNumericValue;
        this.numericValue = numericValue;
        this.textSelection = textSelection;
    }
}
