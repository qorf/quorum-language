package plugins.quorum.Libraries.System;

/**
 * FSEvents, macOS's own file-change notifications, for QuorumFileWatcher
 * (Studio plan FileWatching.md, 2026-11-06). The C side is
 * quorum-natives/src/mac/FileWatcherMac.c, built into Mac.so.
 *
 * One queue per watcher session; one stream per watched folder (and all
 * folders inside it) feeds it. The watcher thread takes events out with
 * Take, which waits up to a time limit. C never calls into Java.
 *
 * Thread rule: Take, RemoveStream and DestroyQueue run only on the
 * session's watcher thread. AddStream may run on any thread.
 */
final class MacFileEvents {
    // FSEvents' event flags (FSEventStreamEventFlags), as Take returns them.
    static final int MUST_SCAN_SUBFOLDERS = 0x00000001;
    static final int USER_DROPPED = 0x00000002;
    static final int KERNEL_DROPPED = 0x00000004;
    static final int ROOT_CHANGED = 0x00000020;
    static final int ITEM_CREATED = 0x00000100;
    static final int ITEM_REMOVED = 0x00000200;
    static final int ITEM_INODE_META_MOD = 0x00000400;
    static final int ITEM_RENAMED = 0x00000800;
    static final int ITEM_MODIFIED = 0x00001000;
    static final int ITEM_FINDER_INFO_MOD = 0x00002000;
    static final int ITEM_CHANGE_OWNER = 0x00004000;
    static final int ITEM_XATTR_MOD = 0x00008000;
    static final int ITEM_IS_FILE = 0x00010000;
    static final int ITEM_IS_DIR = 0x00020000;

    // The version of FileWatcherMac.c this class was written for.
    private static final int VERSION = 1;

    private static final Object availableLock = new Object();
    private static Boolean available = null; // guarded by availableLock

    private MacFileEvents() {
    }

    /**
     * True on macOS when Mac.so is loaded and has these functions. A program
     * that isn't a Game doesn't load Mac.so, and an older Mac.so doesn't have
     * them; the watcher then uses the JDK's WatchService. Only a yes is
     * kept, since Mac.so may be loaded later (when a Game starts).
     */
    static boolean IsAvailable() {
        synchronized (availableLock) {
            if (available != null) {
                return true;
            }
            String system = System.getProperty("os.name", "");
            if (!system.toLowerCase().startsWith("mac")) {
                return false;
            }
            try {
                if (Version() == VERSION) {
                    available = Boolean.TRUE;
                    return true;
                }
            } catch (UnsatisfiedLinkError error) {
                // Not loaded yet, or an older Mac.so.
            }
            return false;
        }
    }

    static native int Version();

    /** Makes an event queue. Returns 0 if it couldn't. */
    static native long CreateQueue();

    /**
     * Starts watching a folder and everything in it, feeding the queue with
     * events marked with root. The latency, in seconds, is how long FSEvents
     * gathers events before passing them on. Returns 0 if it couldn't.
     */
    static native long AddStream(long queue, String folder, double latency, int root);

    /** Stops a stream. Its events already queued stay in the queue. */
    static native void RemoveStream(long stream);

    /**
     * Waits up to milliseconds for events, then fills the arrays (as many as
     * paths can hold). Returns how many, or -1 if events were lost (all
     * waiting ones are then dropped too, so the caller rescans).
     */
    static native int Take(long queue, int milliseconds, String[] paths, int[] flags, int[] roots);

    /** Frees a queue. All its streams must be removed first. */
    static native void DestroyQueue(long queue);
}
