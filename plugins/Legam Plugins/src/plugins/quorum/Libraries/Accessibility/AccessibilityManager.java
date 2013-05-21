package plugins.quorum.Libraries.Accessibility;

import javax.swing.SwingUtilities;

/**
 *
 * @author Nicole Blumhorst
 */
public class AccessibilityManager {
    public Object $me;
    static AccessibleHandler hander;
    
    public AccessibilityManager()
    {
        if ((System.getProperty("os.name")).contains("Windows"))
        {
            hander = new WindowsAccessibleHandler(this);
        }
        else
        {
            hander = new MacAccessibleHandler(this);
        }
    }
    
    public void Start() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                hander.Initialize();
            }
        };
                
        SwingUtilities.invokeLater(runnable);  
        
        //System.out.println("After SwingUtilities.invokeLater");
    }
    
    public void Stop() {
        hander.Terminate();
    } 
    
    public void RecieveEvent(String eventInfo) {
        eventInfo = eventInfo.replace("\t", "");
        eventInfo = eventInfo.replace("\n", " ");
        eventInfo = eventInfo.replace("&", "&amp");
        
        if ($me instanceof quorum.Libraries.Accessibility.AccessibilityManager) {
            quorum.Libraries.Accessibility.AccessibilityManager m = (quorum.Libraries.Accessibility.AccessibilityManager)$me;
            m.GetEvent(eventInfo);
        }
        //System.out.println(eventInfo);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccessibilityManager h = new AccessibilityManager();
        h.Start();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                AccessibilityManager h = new AccessibilityManager();
//                h.Start();
//            }
//        };
//                
//        SwingUtilities.invokeLater(runnable);

    }
}
