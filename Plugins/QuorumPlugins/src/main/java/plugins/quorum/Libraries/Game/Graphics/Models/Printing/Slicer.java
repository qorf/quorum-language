package plugins.quorum.Libraries.Game.Graphics.Models.Printing;

import quorum.Libraries.Containers.Number32BitArray_;

public class Slicer {
    public java.lang.Object me_ = null;

    //Modify this to load in a C++ file.
//    static
//    {
//        try
//        {
//            String os = System.getProperty("os.name");
//
//            String nativeFile;
//            if (os.contains("Mac OS X") || os.contains("Linux"))
//            {
//                if (os.contains("Linux") && System.getProperty("java.runtime.name").contains("Android Runtime"))
//                {
//                    Log.d("Quorum Game Application", "Loading C libraries...");
//                    Log.d("Quorum Game Application", "Java version is " + System.getProperty("java.version"));
//                    nativeFile = null;
//                    GameStateManager.operatingSystem = "Android";
//                    System.loadLibrary("GameEngineCPlugins");
//                    Log.d("Quorum Game Application", "Finished loading C libraries.");
//                }
//                else
//                {
//                    java.io.File file = new java.io.File(Game.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
//                    String runLocation = file.getParentFile().getAbsolutePath();
//                    String lwjgl = runLocation + "/jni";
//                    System.setProperty("org.lwjgl.librarypath", lwjgl);
//
//                    if (System.getProperty("os.arch").contains("aarch64")) {
//                        nativeFile = runLocation + "/jni/libGameEngineCPluginsArm.so";
//                    } else {
//                        nativeFile = runLocation + "/jni/libGameEngineCPlugins.so";
//                    }
//                }
//            }
//            else if (os.contains("Windows"))
//            {
//                URI uri = Game.class.getProtectionDomain().getCodeSource().getLocation().toURI();
//                String uriPath = uri.getPath();
//
//                if (uri.getAuthority() != null)
//                    uriPath = "\\\\" + uri.getAuthority() + uriPath;
//
//                java.io.File file = new java.io.File(uriPath);
//
//                String runLocation = file.getParentFile().getAbsolutePath();
//                String lwjgl = runLocation + "/jni";
//                System.setProperty("org.lwjgl.librarypath", lwjgl);
//
//                if (System.getProperty("os.arch").contains("x86"))
//                    nativeFile = runLocation + "\\jni\\libGameEngineCPlugins32.dll";
//                else
//                    nativeFile = runLocation + "\\jni\\libGameEngineCPlugins64.dll";
//            }
//            else
//            {
//                IOSApplication.SetOperatingSystem();
//                nativeFile = null;
//            }
//            if (nativeFile != null)
//            {
//                GameStateManager.nativePath = nativeFile;
//                System.load(nativeFile);
//                GameStateManager.operatingSystem = System.getProperty("os.name");
//            }
//        }
//        catch (URISyntaxException ex)
//        {
//            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void Slice(Number32BitArray_ array) {
        for(int i = 0; i < array.GetSize(); i++) {
            System.out.println(array.Get(i));
        }
    }
}
