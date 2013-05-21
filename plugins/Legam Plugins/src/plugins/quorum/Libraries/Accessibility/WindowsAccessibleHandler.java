package plugins.quorum.Libraries.Accessibility;

/**
 *
 * @author Nicole
 */
public class WindowsAccessibleHandler extends AccessibleHandler {
    
    //public Object $me;
    
    public WindowsAccessibleHandler(AccessibilityManager m) {
        manager = m;
        if (System.getProperty("os.arch").equals("x86"))
        {
            System.load(System.getProperty("user.dir") + "\\resources\\WindowsAccessibleHandler\\x64\\WindowsAccessibleHandler.dll");
        }
        else
        {
            System.load(System.getProperty("user.dir") + "\\resources\\WindowsAccessibleHandler\\x64\\WindowsAccessibleHandler.dll");
        }
    }
    
//    static
//    {
//        if (System.getProperty("os.arch").equals("x86"))
//        {
//            System.load(System.getProperty("user.dir") + "\\resources\\WindowsAccessibleHandler\\WindowsAccessibleHandler.dll");
//        }
//        else
//        {
//            System.load(System.getProperty("user.dir") + "\\resources\\WindowsAccessibleHandler\\WindowsAccessibleHandler.dll");
//        }
//    }
    
    @Override
    public native void Initialize();

    @Override
    public native void Terminate();
}
