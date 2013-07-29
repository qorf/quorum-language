package plugins.quorum.Libraries.Accessibility;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nicole Blumhorst
 */
public class AccessibilityManager {
    public Object $me;
    static AccessibleHandler handler;
    
    public AccessibilityManager()
    {
        if ((System.getProperty("os.name")).contains("Windows")) {
            handler = new WindowsAccessibleHandler(this);
        } else {
            handler = new MacAccessibleHandler(this);
        }
    }
    
    public void Start() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.Initialize();
            }
        };
                
        SwingUtilities.invokeLater(runnable);  
        try {
            Object o = new Object();
            synchronized(o) {
                o.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(AccessibilityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Stop() {
        handler.Terminate();
    } 
    
    public void RecieveEvent(String eventInfo) {
        
        eventInfo = eventInfo.replace("\t", "");
        eventInfo = eventInfo.replace("\n", " ");
        eventInfo = eventInfo.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<?xml version=\"1.0\"?> <!DOCTYPE doc_type [<!ENTITY lt \"&#60;\"> <!ENTITY amp \"&#38;\"> <!ENTITY gt \"&#62;\"> <!ENTITY quot \"&#34;\"> <!ENTITY apos \"&#39;\">]>");
        //System.out.println(eventInfo);   
        
        if ( eventInfo.endsWith("</AccessibleEvent>") || eventInfo.endsWith("</AccessibleEvent> ") )
            eventInfo = eventInfo + "";
        else
        {
            int index = eventInfo.lastIndexOf(">");    
            eventInfo = eventInfo.substring(0, index+1);
            eventInfo += "</AccessibleEvent>";
        }
        if ($me instanceof quorum.Libraries.Accessibility.AccessibilityManager) {
            quorum.Libraries.Accessibility.AccessibilityManager m = (quorum.Libraries.Accessibility.AccessibilityManager)$me;
            m.GetEvent(eventInfo);
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccessibilityManager h = new AccessibilityManager();
        h.Start();
    }
}
