package plugins.quorum.Libraries.Interface.Accessibility;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.TreeUpdate;
import plugins.quorum.Libraries.Language.SharedClass;
import quorum.Libraries.System.Logging.SharedLogger_;

/*
    Checks each TreeUpdate against the rules AccessKit's tree enforces
    (accesskit_consumer 0.39, tree.rs), just before it is handed over. When
    AccessKit finds a rule broken it stops the app, and its message is lost
    when Studio wasn't started from a terminal. So a broken rule is written
    to the shared logger first, which Studio's FileLogger pushes to disk at
    once. The update is still sent: the app stops as before, and the log
    can be matched with the crash report.

    The rules:
    1. No child is named twice in one update.
    2. Every node in the update is in the tree, is named as a child by a
       node in the update, or is the root.
    3. Every child a node names is in the tree or in the update.
    4. After the update, the focus and the root are in the tree.

    5. No node is removed twice by one update.

    "The tree" is a copy kept here of the nodes AccessKit holds: each
    node's children, changed the way AccessKit changes its own (Apply),
    and the last node that named each one, for the log.

    Nothing is written unless a rule is broken. After one entry the checks
    stop for the rest of the run: either the app stops, or the copy here
    was wrong, which the next update says (NoteStillRunning).
*/
final class UpdateCheck {
    // Descriptions for the log, from MacAccessibility, asked for only
    // when an entry is written.
    interface Describer {
        String Describe(long id);
        String DescribeFocus();
    }

    private static final int HISTORY_SIZE = 20;
    private static final int UPDATE_HISTORY_SIZE = 5;
    private static final int MAX_LISTED_NODES = 20;

    private final HashMap<Long, long[]> children = new HashMap<Long, long[]>();
    private final HashMap<Long, Long> parents = new HashMap<Long, Long>();
    private long root = 0;
    private boolean hasRoot = false;

    // Off once an entry is written: the copy can't be trusted after that.
    private boolean checking = true;
    private boolean reported = false;
    private boolean notedStillRunning = false;

    // What happened lately, kept in memory and written only with an entry.
    private final ArrayDeque<String> history = new ArrayDeque<String>();
    private final ArrayDeque<String> updates = new ArrayDeque<String>();

    // AccessKit starts a new tree (its first look): the copy starts over.
    void Reset() {
        children.clear();
        parents.clear();
        hasRoot = false;
        root = 0;
    }

    /*
        Adds a line to the history: an item added or removed, or the focus
        moving. Called only on those events, never once a frame.
    */
    void Remember(String line) {
        if (!checking) {
            return;
        }
        if (history.size() == HISTORY_SIZE) {
            history.removeFirst();
        }
        history.addLast(System.currentTimeMillis() + " " + line);
    }

    /*
        Checks an update about to be handed to AccessKit and changes the copy
        to match it. A broken rule is logged with the update, the nodes
        involved, the focus and the recent history.
    */
    void Check(TreeUpdate update, boolean fullTree, Describer describer) {
        if (!checking) {
            return;
        }
        String problem = null;
        long problemNode = 0;
        try {
            int count = update.getNodeCount();
            HashSet<Long> inUpdate = new HashSet<Long>();
            HashSet<Long> named = new HashSet<Long>();
            for (int i = 0; i < count; i++) {
                inUpdate.add(update.getNodeId(i));
            }
            long newRoot = update.hasTree() ? update.getTreeRoot() : root;
            boolean rootKnown = update.hasTree() || hasRoot;

            // Rule 1.
            for (int i = 0; i < count && problem == null; i++) {
                for (long child : update.getNodeChildren(i)) {
                    if (!named.add(child)) {
                        problem = "Rule 1: the update names child " + child + " twice"
                            + " (AccessKit: \"TreeUpdate includes duplicate child\").";
                        problemNode = child;
                        break;
                    }
                }
            }
            // Rule 2.
            for (int i = 0; i < count && problem == null; i++) {
                long id = update.getNodeId(i);
                boolean isRoot = rootKnown && id == newRoot;
                if (!children.containsKey(id) && !named.contains(id) && !isRoot) {
                    problem = "Rule 2: node " + id + " is in the update but is neither in the tree"
                        + " nor named as a child by a node in the update (AccessKit: \"TreeUpdate"
                        + " includes nodes which are neither in the current tree nor a child of"
                        + " another node from the update\").";
                    problemNode = id;
                }
            }
            // Rule 3.
            for (int i = 0; i < count && problem == null; i++) {
                for (long child : update.getNodeChildren(i)) {
                    if (!children.containsKey(child) && !inUpdate.contains(child)) {
                        problem = "Rule 3: node " + update.getNodeId(i) + " names child " + child
                            + ", which is neither in the tree nor in the update (AccessKit:"
                            + " \"TreeUpdate's nodes include children ids which are neither in the"
                            + " current tree nor the ID of another node from the update\").";
                        problemNode = child;
                        break;
                    }
                }
            }

            String removal = Apply(update, named);
            if (problem == null && removal != null) {
                problem = removal;
                problemNode = removedTwice;
            }

            // Rule 4.
            if (problem == null && !children.containsKey(update.getFocus())) {
                problem = "Rule 4: the focus, node " + update.getFocus() + ", is not in the tree"
                    + " after the update (AccessKit: \"Focused ID is not in the node list\").";
                problemNode = update.getFocus();
            }
            if (problem == null && (!hasRoot || !children.containsKey(root))) {
                problem = "Rule 4: the root, node " + root + ", is not in the tree after the"
                    + " update (AccessKit: \"Root ID is not in the node list\").";
                problemNode = root;
            }

            String summary = (fullTree ? "whole tree" : "changed nodes") + ", " + count
                + " nodes, focus " + update.getFocus();
            if (updates.size() == UPDATE_HISTORY_SIZE) {
                updates.removeFirst();
            }
            updates.addLast(System.currentTimeMillis() + " " + summary);

            if (problem != null) {
                Report(problem, problemNode, update, fullTree, describer);
            }
        } catch (RuntimeException error) {
            // The check must never be what stops the app.
            checking = false;
            Logger.getLogger(UpdateCheck.class.getName()).log(Level.WARNING,
                "Checking an accessibility update failed; checks are off for this run.", error);
        }
    }

    /*
        The next update after an entry: the app is still running, so
        AccessKit took the update the check called broken, and the copy here
        was out of step. Said once.
    */
    void NoteStillRunning() {
        if (!reported || notedStillRunning) {
            return;
        }
        notedStillRunning = true;
        Log("Accessibility update check: the app kept running after the update logged above,"
            + " so AccessKit accepted it and the check's copy of the tree was out of step."
            + " Checks are off for the rest of this run.");
    }

    // Logs once, the first time the focus is found on a node cut off from the tree.
    private boolean notedDetachedFocus = false;

    void NoteDetachedFocus(String text) {
        if (notedDetachedFocus) {
            return;
        }
        notedDetachedFocus = true;
        StringBuilder entry = new StringBuilder();
        entry.append("Accessibility: the focus was on a node whose parent was removed, so it went to")
            .append(" the root instead (MacAccessibility.DropDetachedFocus). Logged once a run.\n");
        entry.append("Focused node: ").append(text).append('\n');
        AppendHistory(entry);
        Log(entry.toString());
    }

    /*
        Changes the copy as AccessKit changes its tree for this update, step
        for step (TreeState::update): each node in the update replaces its
        children; a child that a node in the update stopped naming, and that
        no node later names (or is itself in the update after that), is
        removed with everything under it that the update doesn't name. A
        node removed twice in one update stops AccessKit (an unwrap of a
        node that is already gone), which is returned as rule 5.
    */
    // The node rule 5 found, for the log.
    private long removedTwice = 0;

    private String Apply(TreeUpdate update, HashSet<Long> named) {
        HashSet<Long> unreachable = new HashSet<Long>();
        HashSet<Long> seen = new HashSet<Long>();
        Long newRoot = null;
        if (update.hasTree()) {
            newRoot = update.getTreeRoot();
            if (hasRoot && root != newRoot) {
                unreachable.add(root);
            }
            root = newRoot;
            hasRoot = true;
        }
        int count = update.getNodeCount();
        for (int i = 0; i < count; i++) {
            long id = update.getNodeId(i);
            unreachable.remove(id);
            long[] newChildren = update.getNodeChildren(i);
            for (long child : newChildren) {
                seen.add(child);
                unreachable.remove(child);
            }
            long[] oldChildren = children.get(id);
            if (oldChildren != null) {
                for (long child : oldChildren) {
                    if (!seen.contains(child)) {
                        unreachable.add(child);
                    }
                }
            }
            children.put(id, newChildren);
            for (long child : newChildren) {
                parents.put(child, id);
            }
        }
        if (hasRoot) {
            parents.remove(root);
        }
        for (long top : unreachable) {
            ArrayDeque<Long> stack = new ArrayDeque<Long>();
            stack.push(top);
            while (!stack.isEmpty()) {
                long id = stack.pop();
                long[] kids = children.remove(id);
                if (kids == null) {
                    removedTwice = id;
                    return "Rule 5: node " + id + " is removed twice by this update (a node no"
                        + " longer named under one removed node is also under another), which"
                        + " AccessKit doesn't allow (an unwrap on a node that is already gone).";
                }
                parents.remove(id);
                for (long child : kids) {
                    if (!named.contains(child) && (newRoot == null || child != newRoot)) {
                        stack.push(child);
                    }
                }
            }
        }
        return null;
    }

    private void Report(String problem, long node, TreeUpdate update, boolean fullTree,
            Describer describer) {
        checking = false;
        reported = true;
        StringBuilder entry = new StringBuilder();
        entry.append("Accessibility update check: this update breaks a rule of AccessKit's tree,")
            .append(" which stops the app. It is sent anyway, so a crash in")
            .append(" accesskit_consumer TreeState::update should follow.\n");
        entry.append(problem).append('\n');
        entry.append("Node ").append(node).append(": ").append(describer.Describe(node)).append('\n');
        Long parent = parents.get(node);
        if (parent != null) {
            entry.append("Its parent in AccessKit's tree, node ").append(parent).append(": ")
                .append(describer.Describe(parent)).append('\n');
        }
        entry.append("Focus: ").append(describer.DescribeFocus()).append('\n');
        int count = update.getNodeCount();
        entry.append("The update: ").append(fullTree ? "the whole tree" : "changed nodes")
            .append(", ").append(count).append(" nodes, focus ").append(update.getFocus()).append('\n');
        for (int i = 0; i < count && i < MAX_LISTED_NODES; i++) {
            long id = update.getNodeId(i);
            entry.append("  ").append(id).append(" (").append(update.getNodeChildren(i).length)
                .append(" children): ").append(describer.Describe(id)).append('\n');
        }
        if (count > MAX_LISTED_NODES) {
            entry.append("  and ").append(count - MAX_LISTED_NODES).append(" more\n");
        }
        entry.append("Recent updates (time in ms):\n");
        for (String line : updates) {
            entry.append("  ").append(line).append('\n');
        }
        AppendHistory(entry);
        Log(entry.toString());
    }

    private void AppendHistory(StringBuilder entry) {
        entry.append("Recent changes (time in ms):\n");
        if (history.isEmpty()) {
            entry.append("  none\n");
        }
        for (String line : history) {
            entry.append("  ").append(line).append('\n');
        }
    }

    /*
        Writes to the shared logger that Studio sets up (Options.quorum, a
        FileLogger, which pushes each entry to disk), or to Java's logger
        when no program set one.
    */
    private static void Log(String text) {
        Object shared = SharedClass.GetStaticClass("Libraries.System.Logging.SharedLogger");
        if (shared instanceof SharedLogger_) {
            SharedLogger_ logger = (SharedLogger_) shared;
            if (logger.GetLogger() != null) {
                logger.Log(text);
                return;
            }
        }
        Logger.getLogger(UpdateCheck.class.getName()).log(Level.WARNING, text);
    }
}
