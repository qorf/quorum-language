package plugins.quorum.Libraries.System;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * The file watcher for the desktop (and Android): one java.nio.file
 * WatchService and one daemon thread, made on the first Watch.
 *
 * Threads. The watcher thread never calls Quorum code and never touches a
 * Quorum object. It puts finished changes in a LinkedBlockingQueue ("ready").
 * The Quorum program's thread takes them out with Next. Everything shared
 * between the two threads is a concurrent collection or a volatile field;
 * registering and cancelling keys is also guarded by the session's lock.
 *
 * Merging. Each change waits in a per-folder "pending" map until its path
 * has been quiet for the settle time. Changes to the same path in that time
 * are merged (see Combine). Only the watcher thread uses the pending maps.
 *
 * Uses only Java 7 APIs, so it builds with the plugins' Java 8 toolchain.
 * No com.sun.nio.file modifiers (sensitivity or FILE_TREE) are used.
 */
final class QuorumFileWatcher implements FileWatcherImplementation {
    // The same values as the constants in FileChangeEvent.quorum.
    static final int CREATED = 0;
    static final int MODIFIED = 1;
    static final int DELETED = 2;
    static final int OVERFLOW = 3;

    // Merging a created file with its deletion: report nothing.
    private static final int NOTHING = -1;

    private static final long JOIN_MILLISECONDS = 1000;

    private final Object lock = new Object();
    private Session session = null; // guarded by lock

    private volatile int settle = 200;
    private final Set<String> excluded = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
    private final Map<String, Long> ignoreUntil = new ConcurrentHashMap<String, Long>();
    private final LinkedBlockingQueue<Change> ready = new LinkedBlockingQueue<Change>();

    // Quorum thread only.
    private Change current = null;

    @Override
    public boolean IsSupported() {
        return true;
    }

    @Override
    public void Watch(String text, boolean recursive) throws IOException {
        Path folder = Normalize(text);
        if (!Files.exists(folder)) {
            throw new FileNotFoundException(text);
        }
        if (!Files.isDirectory(folder)) {
            throw new IOException("FileWatcher can only watch a folder, and this is not one: " + text);
        }

        synchronized (lock) {
            if (session == null) {
                session = new Session();
            }
            Session s = session;
            Root old = s.roots.get(folder);
            if (old != null) {
                s.RemoveRoot(old);
                RemoveReady(old);
            }

            Root root = new Root(folder, text, recursive);
            s.roots.put(folder, root);
            try {
                // The first level is registered here, so its errors reach the caller.
                s.Register(folder, root);
            } catch (IOException ex) {
                s.RemoveRoot(root);
                throw ex;
            }
            if (recursive) {
                // The folders inside are registered on the watcher thread.
                s.walks.add(root);
            }
        }
    }

    @Override
    public void Unwatch(String text) {
        Path folder = Normalize(text);
        synchronized (lock) {
            if (session == null) {
                return;
            }
            Root root = session.roots.get(folder);
            if (root != null) {
                session.RemoveRoot(root);
                RemoveReady(root);
            }
        }
    }

    @Override
    public void Stop() {
        Session s;
        synchronized (lock) {
            s = session;
            session = null;
        }
        if (s != null) {
            s.Close();
        }
        ready.clear();
        ignoreUntil.clear();
        current = null;
    }

    @Override
    public void ExcludeFolderName(String name) {
        if (name != null && !name.isEmpty()) {
            excluded.add(name);
        }
    }

    @Override
    public void IgnoreChangesTo(String path, int milliseconds) {
        if (path == null) {
            return;
        }
        long until = System.currentTimeMillis() + Math.max(0, milliseconds);
        ignoreUntil.put(Normalize(path).toString(), until);
    }

    @Override
    public void SetSettleTime(int milliseconds) {
        settle = Math.max(0, milliseconds);
    }

    @Override
    public int GetSettleTime() {
        return settle;
    }

    @Override
    public boolean Next(int milliseconds) {
        try {
            if (milliseconds <= 0) {
                current = ready.poll();
            } else {
                current = ready.poll(milliseconds, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            current = null;
        }
        return current != null;
    }

    @Override
    public String CurrentPath() {
        return current == null ? "" : current.path;
    }

    @Override
    public int CurrentKind() {
        return current == null ? MODIFIED : current.kind;
    }

    @Override
    public String CurrentRoot() {
        return current == null ? "" : current.root.text;
    }

    private static Path Normalize(String text) {
        return Paths.get(text).toAbsolutePath().normalize();
    }

    /** Drops waiting changes of a folder that is no longer watched. */
    private void RemoveReady(Root root) {
        Iterator<Change> iterator = ready.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().root == root) {
                iterator.remove();
            }
        }
    }

    private boolean IsExcluded(Path path) {
        Path name = path.getFileName();
        return name != null && excluded.contains(name.toString());
    }

    private boolean IsIgnored(Path path, long time) {
        Long until = ignoreUntil.get(path.toString());
        return until != null && time <= until.longValue();
    }

    /**
     * Merges a new change into the one already waiting for the same path.
     * Returns the change to report, or NOTHING.
     */
    private static int Combine(int first, int then) {
        if (then == CREATED) {
            // Deleted and made again (an editor's save): a change.
            return first == DELETED ? MODIFIED : first;
        } else if (then == MODIFIED) {
            // Created and then written: still new.
            return first == CREATED ? CREATED : MODIFIED;
        } else if (then == DELETED) {
            // Created and deleted again before it settled: nothing happened.
            return first == CREATED ? NOTHING : DELETED;
        }
        return then;
    }

    /** One finished change, waiting in the ready queue. */
    private static final class Change {
        final String path;
        final int kind;
        final Root root;

        Change(String path, int kind, Root root) {
            this.path = path;
            this.kind = kind;
            this.root = root;
        }
    }

    /** A change not yet reported. Watcher thread only. */
    private static final class Pending {
        int kind;
        long time;

        Pending(int kind, long time) {
            this.kind = kind;
            this.time = time;
        }
    }

    /** A folder passed to Watch. */
    private static final class Root {
        final Path path;
        final String text; // exactly as passed to Watch, sent back as the watched folder
        final boolean recursive;
        volatile boolean active = true;

        // Watcher thread only.
        final Map<Path, Pending> pending = new LinkedHashMap<Path, Pending>();
        long overflowTime = -1; // when an OVERFLOW started waiting, or -1
        boolean rewalk = false; // walk the tree again to register missed folders

        Root(Path path, String text, boolean recursive) {
            this.path = path;
            this.text = text;
            this.recursive = recursive;
        }
    }

    /**
     * One WatchService and its thread. Stop throws the whole session away,
     * so a thread that is slow to end never touches the next session.
     */
    private final class Session implements Runnable {
        final WatchService service;
        final Thread thread;
        volatile boolean running = true;

        final ConcurrentMap<Path, Root> roots = new ConcurrentHashMap<Path, Root>();
        final Map<WatchKey, Path> folders = new ConcurrentHashMap<WatchKey, Path>();
        // One folder can belong to more than one watched folder when they overlap.
        final Map<WatchKey, List<Root>> keyRoots = new ConcurrentHashMap<WatchKey, List<Root>>();
        final Queue<Root> walks = new ConcurrentLinkedQueue<Root>();

        Session() throws IOException {
            service = FileSystems.getDefault().newWatchService();
            thread = new Thread(this, "Quorum File Watcher");
            thread.setDaemon(true);
            thread.start();
        }

        /** Registers one folder for one root. Either thread. */
        void Register(Path folder, Root root) throws IOException {
            synchronized (this) {
                if (!root.active || !running) {
                    return;
                }
                WatchKey key = folder.register(service,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY);
                folders.put(key, folder);
                List<Root> list = keyRoots.get(key);
                if (list == null) {
                    list = new CopyOnWriteArrayList<Root>();
                    keyRoots.put(key, list);
                }
                if (!list.contains(root)) {
                    list.add(root);
                }
            }
        }

        /** Stops watching one root. Keys shared with another root stay. Either thread. */
        void RemoveRoot(Root root) {
            synchronized (this) {
                root.active = false;
                roots.remove(root.path, root);
                Iterator<Map.Entry<WatchKey, List<Root>>> iterator = keyRoots.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<WatchKey, List<Root>> entry = iterator.next();
                    List<Root> list = entry.getValue();
                    list.remove(root);
                    if (list.isEmpty()) {
                        WatchKey key = entry.getKey();
                        key.cancel();
                        iterator.remove();
                        folders.remove(key);
                    }
                }
            }
        }

        /** Called from Stop on the Quorum thread. */
        void Close() {
            synchronized (this) {
                running = false;
                for (Root root : roots.values()) {
                    root.active = false;
                }
            }
            try {
                service.close();
            } catch (IOException ex) {
                // Nothing more can be done; the thread ends below anyway.
            }
            thread.interrupt();
            if (Thread.currentThread() != thread) {
                try {
                    thread.join(JOIN_MILLISECONDS);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            roots.clear();
            folders.clear();
            keyRoots.clear();
            walks.clear();
        }

        @Override
        public void run() {
            try {
                while (running) {
                    Root walk = walks.poll();
                    while (walk != null) {
                        if (walk.active) {
                            RegisterTree(walk.path, walk, false);
                        }
                        walk = walks.poll();
                    }

                    WatchKey key = service.poll(Tick(), TimeUnit.MILLISECONDS);
                    while (key != null && running) {
                        Process(key);
                        key = service.poll();
                    }

                    for (Root root : roots.values()) {
                        if (root.active && root.rewalk) {
                            root.rewalk = false;
                            RegisterTree(root.path, root, false);
                        }
                    }

                    Flush();
                }
            } catch (InterruptedException ex) {
                // Stop interrupts the thread: end.
            } catch (ClosedWatchServiceException ex) {
                // Stop closes the service: end.
            }
        }

        /** How long one wait for events lasts, so merged changes are flushed on time. */
        private long Tick() {
            int value = settle / 2;
            if (value < 10) {
                return 10;
            }
            if (value > 250) {
                return 250;
            }
            return value;
        }

        private void Process(WatchKey key) {
            Path folder = folders.get(key);
            List<Root> list = keyRoots.get(key);
            if (folder == null || list == null) {
                // Cancelled by Unwatch: throw its events away.
                key.pollEvents();
                return;
            }

            try {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        for (Root root : list) {
                            if (root.active) {
                                Overflow(root);
                                if (root.recursive) {
                                    root.rewalk = true;
                                }
                            }
                        }
                        continue;
                    }

                    Object context = event.context();
                    if (!(context instanceof Path)) {
                        continue;
                    }
                    Path child = folder.resolve((Path) context);
                    for (Root root : list) {
                        if (root.active) {
                            Handle(root, child, kind);
                        }
                    }
                }
            } catch (ClosedWatchServiceException ex) {
                throw ex;
            } catch (RuntimeException ex) {
                // Unexpected. Library code must not print, and this thread
                // must not call Quorum's logger, so tell the program to look again.
                for (Root root : list) {
                    if (root.active) {
                        Overflow(root);
                    }
                }
            }

            if (!key.reset()) {
                Forget(key, folder, list);
            }
        }

        private void Handle(Root root, Path child, WatchEvent.Kind<?> kind) {
            if (IsExcluded(child)) {
                return;
            }

            boolean isFolder = Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS);
            if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                // A folder's entries changed: its children report that themselves.
                if (!isFolder) {
                    Merge(root, child, MODIFIED);
                }
            } else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                Merge(root, child, CREATED);
                if (isFolder && root.recursive) {
                    // Files may already be in it: report those as created too.
                    RegisterTree(child, root, true);
                }
            } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                Merge(root, child, DELETED);
            }
        }

        /** A key that can't be reset: its folder is gone. */
        private void Forget(WatchKey key, Path folder, List<Root> list) {
            synchronized (this) {
                folders.remove(key);
                keyRoots.remove(key);
            }
            for (Root root : list) {
                if (root.active && root.path.equals(folder)) {
                    // The watched folder itself was deleted. Its parent isn't
                    // watched, so nothing else will say so.
                    Merge(root, folder, DELETED);
                }
            }
        }

        /**
         * Registers a folder and every folder inside it, skipping excluded
         * names. With report true (a folder that just appeared), every file
         * and folder found inside is reported as CREATED.
         */
        private void RegisterTree(final Path start, final Root root, final boolean report) {
            try {
                Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path folder, BasicFileAttributes attributes) {
                        if (!root.active || !running) {
                            return FileVisitResult.TERMINATE;
                        }
                        if (!folder.equals(start) && IsExcluded(folder)) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        try {
                            Register(folder, root);
                        } catch (NoSuchFileException ex) {
                            // Deleted while walking: its DELETED comes from its parent.
                            return FileVisitResult.SKIP_SUBTREE;
                        } catch (IOException ex) {
                            // For example, Linux's limit of watches was reached.
                            Overflow(root);
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        if (report && !folder.equals(start)) {
                            Merge(root, folder, CREATED);
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                        if (report && !IsExcluded(file)) {
                            Merge(root, file, CREATED);
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException ex) {
                        // Gone or unreadable: nothing to register.
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (NoSuchFileException ex) {
                // The folder is already gone.
            } catch (IOException ex) {
                Overflow(root);
            }
        }

        /** Records a change for a path, merged with one already waiting. */
        private void Merge(Root root, Path path, int kind) {
            if (root.overflowTime >= 0) {
                // The program will look at the whole folder again anyway.
                return;
            }
            long now = System.currentTimeMillis();
            Pending waiting = root.pending.get(path);
            if (waiting == null) {
                root.pending.put(path, new Pending(kind, now));
                return;
            }
            int merged = Combine(waiting.kind, kind);
            if (merged == NOTHING) {
                root.pending.remove(path);
            } else {
                waiting.kind = merged;
                waiting.time = now;
            }
        }

        /** Changes were lost: drop the waiting ones and report one OVERFLOW. */
        private void Overflow(Root root) {
            root.pending.clear();
            root.overflowTime = System.currentTimeMillis();
        }

        /** Moves changes quiet for the settle time to the ready queue. */
        private void Flush() {
            long now = System.currentTimeMillis();
            int wait = settle;
            for (Root root : roots.values()) {
                if (!root.active) {
                    continue;
                }
                if (root.overflowTime >= 0) {
                    if (now - root.overflowTime >= wait) {
                        root.overflowTime = -1;
                        Offer(root.path, OVERFLOW, root);
                    }
                    continue;
                }
                Iterator<Map.Entry<Path, Pending>> iterator = root.pending.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Path, Pending> entry = iterator.next();
                    Pending waiting = entry.getValue();
                    if (now - waiting.time >= wait) {
                        iterator.remove();
                        if (!IsIgnored(entry.getKey(), waiting.time)) {
                            Offer(entry.getKey(), waiting.kind, root);
                        }
                    }
                }
            }

            Iterator<Map.Entry<String, Long>> ignored = ignoreUntil.entrySet().iterator();
            while (ignored.hasNext()) {
                if (ignored.next().getValue().longValue() < now) {
                    ignored.remove();
                }
            }
        }

        private void Offer(Path path, int kind, Root root) {
            if (running && root.active) {
                ready.offer(new Change(path.toString(), kind, root));
            }
        }
    }
}
