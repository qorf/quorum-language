package plugins.quorum.Libraries.System;

import java.io.IOException;

/**
 * The file watcher on platforms without one (iOS). IsSupported is false and
 * Watch fails; everything else does nothing. It is a separate class so these
 * platforms never load java.nio.file's watch service.
 */
final class QuorumFileWatcherUnsupported implements FileWatcherImplementation {
    private int settle = 200;

    @Override
    public boolean IsSupported() {
        return false;
    }

    @Override
    public void Watch(String path, boolean recursive) throws IOException {
        throw new IOException("File watching is not supported on this platform.");
    }

    @Override
    public void Unwatch(String path) {
    }

    @Override
    public void Stop() {
    }

    @Override
    public void ExcludeFolderName(String name) {
    }

    @Override
    public void IgnoreChangesTo(String path, int milliseconds) {
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
        return false;
    }

    @Override
    public String CurrentPath() {
        return "";
    }

    @Override
    public int CurrentKind() {
        return 1; // MODIFIED, the same default as FileChangeEvent
    }

    @Override
    public String CurrentRoot() {
        return "";
    }
}
