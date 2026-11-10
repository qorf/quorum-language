package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    What native code calls with each action request, taken apart into plain
    values: AccessKitJNI.m's Act for AccessKit's requests, and its
    RuntimeAct for the ones Mac.so adds (show menu, open, close). Builds an
    ActionRequest and gives it to the ActionHandler. Nothing is thrown back
    into native code.
*/
final class NativeActionReceiver {
    // enum accesskit_action_data_Tag in accesskit.h 0.23.2; -1 is no data.
    static final int NO_DATA = -1;
    static final int DATA_VALUE = 1;
    static final int DATA_NUMERIC_VALUE = 2;
    static final int DATA_SET_TEXT_SELECTION = 7;

    private final ActionHandler handler;

    NativeActionReceiver(ActionHandler handler) {
        this.handler = handler;
    }

    /* value is UTF-8, or null. */
    void receive(int action, long target, int dataKind, byte[] value, double number,
            long anchorNode, int anchorIndex, long focusNode, int focusIndex) {
        try {
            String text = null;
            boolean hasNumber = false;
            TextSelection selection = null;
            if (dataKind == DATA_VALUE && value != null) {
                text = new String(value, StandardCharsets.UTF_8);
            } else if (dataKind == DATA_NUMERIC_VALUE) {
                hasNumber = true;
            } else if (dataKind == DATA_SET_TEXT_SELECTION) {
                selection = new TextSelection(
                    new TextPosition(new NodeId(anchorNode), anchorIndex),
                    new TextPosition(new NodeId(focusNode), focusIndex));
            }
            handler.requestAction(new ActionRequest(Action.fromValue(action), new NodeId(target),
                text, hasNumber, number, selection));
        } catch (Throwable error) {
            Logger.getLogger(NativeActionReceiver.class.getName()).log(Level.WARNING,
                "A VoiceOver action request failed.", error);
        }
    }
}
