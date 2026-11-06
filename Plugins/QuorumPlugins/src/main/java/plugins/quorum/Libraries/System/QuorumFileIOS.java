package plugins.quorum.Libraries.System;

import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSFileManager;
import org.robovm.apple.foundation.NSSearchPathDirectory;
import org.robovm.apple.foundation.NSSearchPathDomainMask;
import org.robovm.apple.foundation.NSURL;

/*
    A File on iPhones and iPads. Its working directory is the app bundle
    (IOSApplication.SetupNative), which is read-only, so data the program
    saves goes in the app's Documents folder: kept between runs and backed up.
 */
public class QuorumFileIOS extends QuorumFile {
    @Override
    public String GetStorageDirectoryNative() {
        NSArray<NSURL> urls = NSFileManager.getDefaultManager().getURLsForDirectory(
                NSSearchPathDirectory.DocumentDirectory, NSSearchPathDomainMask.UserDomainMask);
        if (urls == null || urls.isEmpty()) {
            return defaultWorkingDirectory;
        }

        String path = urls.get(0).getPath();
        new java.io.File(path).mkdirs();
        return path;
    }
}
