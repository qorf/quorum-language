package plugins.quorum.Libraries.Accessibility;

/**
 *
 * @author Nicole
 */
public class MacAccessibleHandler extends AccessibleHandler {
    
    public MacAccessibleHandler(AccessibilityManager m) {
        manager = m;
        //System.load(System.getProperty("user.dir") + "/resources/MacAccessibleHandler/MacAccessibleHandler.dylib");
    }
    
    @Override
    public native void Initialize();
        
    @Override
    public native void Terminate();
}
