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
            System.load(System.getProperty("user.dir") + "\\resources\\WindowsAccessibleHandler\\WindowsAccessibleHandler32.dll");
        }
        else
        {
            System.load(System.getProperty("user.dir") + "\\resources\\WindowsAccessibleHandler\\WindowsAccessibleHandler64.dll");
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
