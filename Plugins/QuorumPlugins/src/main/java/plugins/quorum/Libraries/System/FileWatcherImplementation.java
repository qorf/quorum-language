package plugins.quorum.Libraries.System;

import java.io.IOException;

/**
 * What a file watcher does on one platform. FileWatcher (the plugin class)
 * picks one: QuorumFileWatcher on the desktop and Android, or
 * QuorumFileWatcherUnsupported on iOS.
 *
 * Every method is called from the Quorum program's thread only.
 */
interface FileWatcherImplementation {
    boolean IsSupported();

    /**
     * Starts watching a folder. Throws java.io.FileNotFoundException or
     * java.nio.file.NoSuchFileException if the folder does not exist, and
     * another IOException for other problems.
     */
    void Watch(String path, boolean recursive) throws IOException;

    void Unwatch(String path);

    void Stop();

    void ExcludeFolderName(String name);

    void IgnoreChangesTo(String path, int milliseconds);

    void SetSettleTime(int milliseconds);

    int GetSettleTime();

    /**
     * Takes the next waiting change and holds it as the current change.
     * Waits up to milliseconds for one (0 or less: does not wait). Returns
     * false if there was none.
     */
    boolean Next(int milliseconds);

    String CurrentPath();

    int CurrentKind();

    String CurrentRoot();
}
