package plugins.quorum.Libraries.Accessibility;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicole Blumhorst
 */
public abstract class AccessibleHandler 
{
    static AccessibilityManager manager;
    
    private static final OperatingSystem os = OperatingSystem.getOS();
    private static boolean isLoaded = false;
    private static final Logger logger =
            Logger.getLogger("plugins.quorum.Libraries.Accessibility.AccessibleHandler");
    
    
    public abstract void Initialize();
    public abstract void Terminate(); 

    static {
        // Load the proper library for this OS.

        if (os == OperatingSystem.MAC_OSX) {
            try {
                loadLibrary("MacAccessibleHandler.dylib");
                isLoaded = true;
            }
            catch (Exception e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                logger.log(Level.SEVERE, "Could not load Mac Accessible library. " +
                    getStackTraceString(stackTrace));
            }
            catch (Error e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                logger.log(Level.SEVERE, "Could not load carbon library. " +
                    getStackTraceString(stackTrace));
            }
        }
        else if(isWindows()) {

            try {
                if (System.getProperty("os.arch").equals("x86"))
                {
                    loadLibrary("WindowsAccessibleHandler32.dll");
                }
                else
                {
                    loadLibrary("WindowsAccessibleHandler64.dll");
                }
                isLoaded = true;
            }
            catch (Exception e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                logger.log(Level.SEVERE, "Could not load windows dll. " +
                    getStackTraceString(stackTrace));
            }
            catch (Error e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
                logger.log(Level.SEVERE, "Could not load windows dll. " +
                    getStackTraceString(stackTrace));
            }
        }

        if(!isLoaded) { //log the problem
            logger.log(Level.SEVERE, "Accessibility libraries could not be loaded.");
        }
    }
    
    private static void loadLibrary(String library) {
        try {
            System.loadLibrary(library);
        }
        catch(Error error) {
            loadLibraryRelative(library);
        }
    }

    /**
     * Loads the specified library using System.loadLibrary() if the library is
     * found in the java.library.path. Otherwise, System.load() is used with
     * JNIPath prefixed.
     *
     * @param lib
     * @throws Exception
     */
    private static void loadLibraryRelative(String lib){
        boolean libFound = false;
        File folder = new File(
            AccessibleHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        folder = folder.getParentFile();
        String JNIRoot = null;
        String JNIPath;
        String libFullName = lib;

        // Make the full library name
        if (isWindows()) {
            libFullName = lib;
            JNIRoot = new File(folder.getAbsolutePath() + "\\libraries").getAbsolutePath();
        }
        else if (os == OperatingSystem.MAC_OSX) {
            libFullName = lib;
            JNIRoot = new File(folder.getAbsolutePath() + "/libraries").getAbsolutePath();
        }
        else if (os == OperatingSystem.LINUX)
        {
            libFullName = "lib";
        }

        // Figure out the proper JNIPath and load the library.
        if (isWindows()) {
            JNIPath = JNIRoot.replaceAll("\\%20", " ") + "\\jni";
            System.load(JNIPath + "\\" + libFullName);
        }
        else {
            JNIPath = JNIRoot.replaceAll("\\%20", " ") + "/jni";
            System.load(JNIPath + "/" + libFullName);
        }
    }
    
    /**
     * Returns whether the system is on windows.
     *
     * @return
     */
    private static boolean isWindows() {
        return (OperatingSystem.WINDOWS7 == os ||
            OperatingSystem.WINDOWS_VISTA == os ||
            OperatingSystem.WINDOWS_XP == os ||
            OperatingSystem.WINDOWS8 == os ||
            OperatingSystem.WINDOWS_OTHER == os);
    }
    
    private static String getStackTraceString(StackTraceElement[] elements) {
        String str = "";
        for(int i = 0; i < elements.length; i++) {
            str += "\n" + elements[i];
        }
        return str;
    }
    
    /**
     * This method essentially serves as the callback function from the specific
     * OS native library.  As of this build, this function simply prints the
     * string that it is passed (for testing purposes).
     * 
     * @param eventInfo 
     */
    public void ReceiveEvent(String eventInfo)
    {
        manager.RecieveEvent(eventInfo);
    }
    
}
