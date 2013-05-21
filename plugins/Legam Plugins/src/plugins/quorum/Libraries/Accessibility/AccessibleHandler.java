package plugins.quorum.Libraries.Accessibility;

/**
 *
 * @author Nicole Blumhorst
 */
public abstract class AccessibleHandler 
{
    static AccessibilityManager manager;
    
    public abstract void Initialize();
    public abstract void Terminate(); 
    
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
