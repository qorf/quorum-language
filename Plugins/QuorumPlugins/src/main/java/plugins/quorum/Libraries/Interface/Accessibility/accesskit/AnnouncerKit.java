package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;

/*
    A live region under the root, for MacAccessibility.Notify. When its text
    changes, AccessKit's macOS adapter asks VoiceOver to announce it
    (NSAccessibilityAnnouncementRequestedNotification); focus doesn't move.
    AccessKit announces only a change, so MacAccessibility alternates two of
    these and empties the one it isn't using: the same message twice in a
    row is still spoken.

    It has no item and no size. It can't be hidden: AccessKit skips hidden
    nodes, and announces nothing for them.
*/
public class AnnouncerKit extends ItemKit {
    private final NodeId id;
    private String text = null;
    private Live live = Live.POLITE;

    public AnnouncerKit(long id) {
        this.id = new NodeId(id);
        SetRole(Role.STATUS);
    }

    @Override
    public NodeId GetNodeID() {
        return id;
    }

    @Override
    public Node Build() {
        Node builder = new Node(GetRole());
        builder.setLive(live);
        if (text != null) {
            builder.setLabel(text);
        }
        return builder;
    }

    /* null empties it. */
    public void SetText(String text, Live live) {
        this.text = text;
        this.live = live;
    }
}
