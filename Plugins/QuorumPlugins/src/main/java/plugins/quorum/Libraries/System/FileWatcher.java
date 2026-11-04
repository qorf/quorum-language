package plugins.quorum.Libraries.System;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import quorum.Libraries.Language.Errors.FileNotFoundError;
import quorum.Libraries.Language.Errors.InputOutputError;

/**
 * Java bytecode plugin for the "FileWatcher" class. It picks an
 * implementation per platform, the way File.java does: iOS gets one that
 * reports "not supported" (so it never loads the watch service code), every
 * other platform gets QuorumFileWatcher.
 *
 * The watcher's own thread never calls Quorum code. It fills a queue, and
 * the Quorum program takes changes from it with NextChangeNative or
 * WaitForChangeNative, on its own thread.
 */
public class FileWatcher {
    public java.lang.Object me_ = null;
    private final FileWatcherImplementation inst;

    public FileWatcher() {
        if (File.isIOS) {
            inst = new QuorumFileWatcherUnsupported();
        } else {
            inst = new QuorumFileWatcher();
        }
    }

    public boolean IsSupportedNative() {
        return inst.IsSupported();
    }

    public void WatchNative(String path, boolean recursive) throws FileNotFoundError, InputOutputError {
        try {
            inst.Watch(path, recursive);
        } catch (java.io.FileNotFoundException ex) {
            FileNotFoundError error = new FileNotFoundError();
            error.SetPath(path);
            throw error;
        } catch (NoSuchFileException ex) {
            FileNotFoundError error = new FileNotFoundError();
            error.SetPath(path);
            throw error;
        } catch (IOException ex) {
            InputOutputError error = new InputOutputError();
            if (ex.getMessage() != null) {
                error.SetErrorMessage(ex.getMessage());
            }
            throw error;
        }
    }

    public void UnwatchNative(String path) {
        inst.Unwatch(path);
    }

    public void StopNative() {
        inst.Stop();
    }

    public void ExcludeFolderNameNative(String name) {
        inst.ExcludeFolderName(name);
    }

    public void IgnoreChangesToNative(String path, int milliseconds) {
        inst.IgnoreChangesTo(path, milliseconds);
    }

    public void SetSettleTimeNative(int milliseconds) {
        inst.SetSettleTime(milliseconds);
    }

    public int GetSettleTimeNative() {
        return inst.GetSettleTime();
    }

    public boolean NextChangeNative() {
        return inst.Next(0);
    }

    public boolean WaitForChangeNative(int milliseconds) {
        return inst.Next(milliseconds);
    }

    public String GetChangePathNative() {
        return inst.CurrentPath();
    }

    public int GetChangeKindNative() {
        return inst.CurrentKind();
    }

    public String GetChangeRootNative() {
        return inst.CurrentRoot();
    }
}
