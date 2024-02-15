package plugins.quorum.Libraries.Interface.Accessibility;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import plugins.quorum.Libraries.Game.AndroidApplication;
import quorum.Libraries.Interface.Accessibility;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.ListItem_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;


/**
 *
 * @author andreasstefik
 */
public class AndroidAccessibility{
    public Object me_ = null;
    public static Item_ lastSpokenChild;
    public static Item_ lastHoveredChild;

    
    public static void sendAccessibilityEventForVirtualView(Item_ item, int eventType) {
        if(item == null)
            return;
        if (AndroidApplication.accessibilityManager.isTouchExplorationEnabled()) {
            if (lastSpokenChild == null)
                lastSpokenChild = item;
            else if(lastSpokenChild == item)
                return;
            else
                lastSpokenChild = item;
            AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
            event.setPackageName(AndroidApplication.androidActivity.getPackageName());
            event.setClassName(item.GetName());
            event.setSource(AndroidApplication.viewRoot, item.GetHashCode());
            String text = "";
            if (!item.GetName().trim().isEmpty()) {
                text += item.GetName() + " ";
            }
            if (!item.GetDescription().trim().isEmpty()){
                text += item.GetDescription() + " ";
            } else {
                text += item.GetAccessibilityType();
            }
            if (item.GetAccessibilityCode() == item.Get_Libraries_Interface_Item__LIST_ITEM_()){
                ((ListItem_) item).Select();
                text += " " + ((ListItem_) item).GetText();
            }

            event.getText().add(text);

            //AndroidApplication.accessibilityManager.interrupt();
            //AndroidApplication.accessibilityManager.sendAccessibilityEvent(event);
        }
    }

    public static void sendAccessibilityEventForVirtualView(String text) {
        AccessibilityEvent event = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_ANNOUNCEMENT);
        event.setSource(AndroidApplication.viewRoot, lastSpokenChild.GetHashCode());
        event.getText().add(text);

        //AndroidApplication.accessibilityManager.interrupt();
        //AndroidApplication.accessibilityManager.sendAccessibilityEvent(event);
    }

    public void  NameChanged(Item_ item) {}

    public void  DescriptionChanged(Item_ item) {}

    public void  BoundsChanged(Item_ item) {}

    public void  TextFieldUpdatePassword(TextField_ field) {}

    public void  Update() {}

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {}

    public void  SelectionChanged(SelectionEvent_ event) {}
    
    
    public void ButtonActivated(Button_ button) {
        //sendAccessibilityEventForVirtualView(button, AccessibilityEvent.TYPE_ANNOUNCEMENT);
        //android.util.Log.e("Quorum", "ButtonActivated: ");
    }

    public void  ToggleButtonToggled(ToggleButton_ button) {}
    
    
    public void  FocusChanged(FocusEvent_ event) throws Exception {
    	//Log.d("myapp", Log.getStackTraceString(new Exception()));
    	
        AccessibilityManager accessibilityManager = AndroidApplication.accessibilityManager;
        if(accessibilityManager == null) {
            return;
        }

        Item_ item = event.Get_Libraries_Interface_Events_FocusEvent__gainedFocus_();
        if(item == null) {
            return;
        }

        int id = item.GetHashCode();
        Item_ temp = AndroidApplication.quorumItems.get(id);
        if(temp == null)
            return;
        //Log.e("Quorum", "FocusChanged: " + temp.GetDescription() + temp.GetName());
        
        AccessibilityEvent accessibilityEvent = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_ANNOUNCEMENT);
        accessibilityEvent.setPackageName(AndroidApplication.androidActivity.getPackageName());
        accessibilityEvent.setClassName(item.GetName());
        accessibilityEvent.setSource(AndroidApplication.viewRoot, item.GetHashCode());
        String text = "";
        if (!item.GetName().trim().isEmpty()) {
            text += item.GetName() + " ";
        }
        if (!item.GetDescription().trim().isEmpty()){
            text += item.GetDescription() + " ";
        } else {
            text += item.GetAccessibilityType();
        }
        if (item.GetAccessibilityCode() == item.Get_Libraries_Interface_Item__LIST_ITEM_()){
            ((ListItem_) item).Select();
            text += " " + ((ListItem_) item).GetText();
        }

        accessibilityEvent.getText().add(text);

    	//Log.d("AndroidAccessibility", "FocusChanged(FocusEvent_ event) - INTERRUPT ON 2");
		
        AndroidApplication.accessibilityManager.interrupt();
        AndroidApplication.accessibilityManager.sendAccessibilityEvent(accessibilityEvent);
    }

    public boolean NativeAdd(Item_ item) throws Exception {

        AccessibilityManager accessibilityManager = AndroidApplication.accessibilityManager;
        if(accessibilityManager == null) {
            return false;
        }
        View view = AndroidApplication.viewRoot;
        if(view == null) {
            return false;
        }
        AccessibilityNodeProvider accessibilityNodeProvider = AndroidApplication.accessibilityNodeProvider;
        if(accessibilityNodeProvider == null) {
            return false;
        }

        if(item.GetAccessibilityType().equals("NOT_ACCESSIBLE"))
            return false;

        int id = item.GetHashCode();

        AndroidApplication.quorumItems.put(id,item);
        AndroidApplication.accessibilityNodeProvider.createAccessibilityNodeInfo(id);

        return true;
    }

    public boolean NativeRemove(Item_ item) {
        if(item == null) {
            return false;
        }

        int id = item.GetHashCode();
        Item_ temp = AndroidApplication.quorumItems.get(id);
        if(temp == null)
            return false;
        else
            return (AndroidApplication.quorumItems.remove(id) != null);
    }

    public void  MenuChanged(MenuChangeEvent_ event) {}

    public void  TreeChanged(TreeChangeEvent_ event) {}

    public void  TreeTableChanged(TreeTableChangeEvent_ event) {}

    public void  ControlActivated(ControlActivationEvent_ event) {}

    public void  TextChanged(TextChangeEvent_ event) {}

    public void TextSelectionChanged(TextBoxSelection_ selection)
    {

    }

    public void TextSelectionChanged(TextFieldSelection_ selection)
    {

    }

    public boolean Select(Item_ item)
    {
        return false;
    }

    public void  WindowFocusChanged(WindowFocusEvent_ event) {}

    public void  Notify(Item_ item, String value) {}

    public void  Notify(Item_ item, String value, int notificationType) {}

    public void  Shutdown() {}
}
