package plugins.quorum.Libraries.Accessibility;

/**
 *
 * @author Nicole
 */
public class WindowsAccessibleHandler extends AccessibleHandler {
    public WindowsAccessibleHandler(AccessibilityManager m) {
        manager = m;
    }
    
    @Override
    public native void Initialize();

    @Override
    public native void Terminate();
}
