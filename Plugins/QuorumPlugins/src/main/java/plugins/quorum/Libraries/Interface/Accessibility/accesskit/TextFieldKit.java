package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import java.util.Collections;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;
import quorum.Libraries.Interface.Item_;

public class TextFieldKit extends TextKitBase {
    // What a password field's text run holds instead of its characters.
    private static final char PASSWORD_CHARACTER = '•';

    private boolean textDirty = true;

    public TextFieldKit() {
        SetRole(Role.TEXT_INPUT);
    }

    private NodeId GetLineID() {
        return new NodeId(GetNodeID().value << 32);
    }

    private static boolean IsPassword(Item_ item) {
        return item instanceof TextField_ && ((TextField_) item).IsPassword();
    }

    /*
        A password field is a PASSWORD_INPUT, which VoiceOver treats as a
        secure text field (AccessKit gives it the AXSecureTextField
        subrole and masks its value).
    */
    @Override
    public Role GetRole() {
        if (IsPassword(GetItem())) {
            return Role.PASSWORD_INPUT;
        }
        return super.GetRole();
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            Node builder = new Node(GetRole());
            builder.setBounds(rect);
            builder.setLabel(item.GetName());
            builder.setDescription(item.GetDescription());
            if(item instanceof TextField_) { //technically not compiler guaranteed. You can set the code to anything.
                TextField_ field = (TextField_) item;
                TextFieldSelection_ selection = field.GetSelection();
                int start = selection.GetStartIndex();
                int end = selection.GetEndIndex();
                if(selection.IsEmpty()) {
                    start = field.GetCaretPosition();
                    end = field.GetCaretPosition();
                }
                NodeId lineID = GetLineID();
                TextPosition anchor, focus;
                if (selection.IsEmpty() || selection.IsCaretAtEnd()) {
                    anchor = new TextPosition(lineID, start);
                    focus = new TextPosition(lineID, end);
                } else {
                    anchor = new TextPosition(lineID, end);
                    focus = new TextPosition(lineID, start);
                }
                builder.setTextSelection(new TextSelection(anchor, focus));
            }
            BuildChildren(builder);
            return builder;
        }
        return null;
    }

    /*
        SET_VALUE replaces the text (VoiceOver, dictation, Voice Control);
        not offered for a password field. SET_TEXT_SELECTION moves the
        caret or the selection. AccessKit counts characters (a CRLF is
        one), Quorum counts Java chars, so a selection after an emoji or a
        CRLF is off by those, as before.
    */
    @Override
    protected void AddActions(Node node, Item_ item) {
        super.AddActions(node, item);
        if (item instanceof TextField_) {
            if (!((TextField_) item).IsPassword()) {
                node.addAction(Action.SET_VALUE);
            }
            node.addAction(Action.SET_TEXT_SELECTION);
        }
    }

    @Override
    public void DoAction(ActionRequest request) {
        Item_ item = GetItem();
        if (!(item instanceof TextField_)) {
            super.DoAction(request);
            return;
        }
        TextField_ field = (TextField_) item;
        if (request.action == Action.SET_VALUE && request.value != null && !field.IsPassword()) {
            field.SetText(request.value);
        } else if (request.action == Action.SET_TEXT_SELECTION && request.textSelection != null) {
            int anchor = request.textSelection.anchor.characterIndex;
            int focus = request.textSelection.focus.characterIndex;
            field.Select(Math.min(anchor, focus), Math.max(anchor, focus), focus >= anchor);
        } else {
            super.DoAction(request);
        }
    }

    public void SetTextDirty() {
        textDirty = true;
    }

    @Override
    public Iterable<NodeId> GetInternalChildren() {
        return Collections.singleton(GetLineID());
    }

    @Override
    public Iterable<NodeId> GetDirtyInternalChildren() {
        if (!textDirty) {
            return Collections.emptySet();
        }
        return GetInternalChildren();
    }

    @Override
    public void ClearDirtyInternalChildren() {
        textDirty = false;
    }

    @Override
    public Node BuildInternalChild(NodeId id) {
        if (!id.equals(GetLineID())) {
            throw new IllegalArgumentException("unexpected child ID");
        }
        Item_ item = GetItem();
        if(item != null && item instanceof TextField_) {
            TextField_ field = (TextField_) item;
            String s = field.GetText();
            if (field.IsPassword()) {
                // The password itself never leaves Quorum: one dot for each
                // character, so lengths and the caret still line up.
                s = Mask(s);
            }
            Node builder = BuildLineBase(0, s);
            return builder;
        }
        return null;
    }

    private static String Mask(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            int codePoint = s.codePointAt(i);
            masked.append(PASSWORD_CHARACTER);
            i += Character.charCount(codePoint);
        }
        return masked.toString();
    }

    @Override
    boolean IsBeginningOfToken(int lineIndex, int i) {
        if (lineIndex != 0) {
            throw new IllegalArgumentException("non-zero line index");
        }
        TextField_ field = (TextField_)GetItem();
        if (field.IsPassword()) {
            // No words in a password (the mask is one run of dots).
            return false;
        }
        return field.IsBeginningOfToken(i);
    }
}
