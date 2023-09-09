package plugins.quorum.Libraries.Interface.Accessibility;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.*;
import plugins.quorum.Libraries.Game.IOSApplication;
<<<<<<< HEAD
import plugins.quorum.Libraries.Interface.Accessibility.IOS.ButtonIOS;
import plugins.quorum.Libraries.Interface.Accessibility.IOS.CheckboxIOS;
import plugins.quorum.Libraries.Interface.Accessibility.IOS.ToggleButtonIOS;
import plugins.quorum.Libraries.Interface.Accessibility.IOS.RadioButtonIOS;
import quorum.Libraries.Interface.Controls.*;
=======
import plugins.quorum.Libraries.Interface.Accessibility.IOS.*;
import quorum.Libraries.Interface.Controls.Button_;
import quorum.Libraries.Interface.Controls.Checkbox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Controls.ToggleButton_;
>>>>>>> d441c989a7ac7ee3f61119ea067cc7e7c5577212
import quorum.Libraries.Interface.Events.*;
import quorum.Libraries.Interface.Item_;
import quorum.Libraries.Interface.Selections.TextBoxSelection_;
import quorum.Libraries.Interface.Selections.TextFieldSelection_;

import java.util.HashMap;

public class IOSAccessibility {
    public Object me_ = null;
    public HashMap mapAccessibilityElements = new HashMap<UIAccessibilityElement, Item_>();

    public void  NameChanged(Item_ item) {}

    public void  DescriptionChanged(Item_ item) {}

    public void  BoundsChanged(Item_ item) {}

    public void  TextFieldUpdatePassword(TextField_ field) {}

    public void  Update() {
    }

    public void  ProgressBarValueChanged(ProgressBarValueChangedEvent_ progress) {}

    public void SelectionChanged(SelectionEvent_ event) {}

    public void ButtonActivated(Button_ button) {}

    public void ToggleButtonToggled(ToggleButton_ button) {}

    public void  FocusChanged(FocusEvent_ event) throws Exception {
        Item_ item = event.GetNewFocus();
        System.out.println("NEwFocus Changed to " + item.GetName());
        UIAccessibilityElement element = (UIAccessibilityElement) mapAccessibilityElements.get(item);
        UIAccessibilityGlobals.postNotification(UIAccessibilityNotification.LayoutChangedNotification, element);

    }

    public boolean NativeAdd(Item_ item) {
        boolean debug = false;
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if(item == null) {
            return false;
        }

        UIAccessibilityElement element = new UIAccessibilityElement(IOSApplication.accessibilityContainer);

        //Get the accessibility code and do custom controls.
        //Many of the traits and properties here are incorrect, but the structure is there which can get us started
        int code = item.GetAccessibilityCode();
        if(code != -1) {
            System.out.println("Name: " + item.GetName() + " Code: " + code);
        }

        // Most items only being turned into the basic ItemIOS as a placeholder
        if (code == item.Get_Libraries_Interface_Item__NOT_ACCESSIBLE_() || !item.IsShowing()) {
            return false;
        } else if (code == item.Get_Libraries_Interface_Item__ITEM_()) {
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__CUSTOM_()) {
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__CHECKBOX_()) {
            CheckboxIOS checkbox = new CheckboxIOS(IOSApplication.accessibilityContainer);
            checkbox.Initialize((Checkbox_) item);
            element = checkbox;
        } else if (code == item.Get_Libraries_Interface_Item__RADIO_BUTTON_()) {
<<<<<<< HEAD
            System.out.println("Added a Radio Button");
            element.setAccessibilityTraits(UIAccessibilityTraits.AllowsDirectInteraction);
=======
>>>>>>> d441c989a7ac7ee3f61119ea067cc7e7c5577212
            element.setAccessibilityTraits(UIAccessibilityTraits.Button);
            RadioButtonIOS radioButton = new RadioButtonIOS(IOSApplication.accessibilityContainer);
            radioButton.Initialize((RadioButton_) item);
            element = radioButton;
        } else if (code == item.Get_Libraries_Interface_Item__BUTTON_()) {
            ButtonIOS button = new ButtonIOS(IOSApplication.accessibilityContainer);
            button.Initialize((Button_)item);
            element = button;
        } else if (code == item.Get_Libraries_Interface_Item__TOGGLE_BUTTON_()) {
            System.out.println("Added a Toggle Button");
            element.setAccessibilityTraits(UIAccessibilityTraits.AllowsDirectInteraction);
            element.setAccessibilityTraits(UIAccessibilityTraits.Button);
<<<<<<< HEAD
            ToggleButtonIOS toggleButton = new ToggleButtonIOS(IOSApplication.accessibilityContainer);
            toggleButton.Initialize((ToggleButton_) item);
            element = toggleButton;
=======
        } else if (code == item.Get_Libraries_Interface_Item__TEXTBOX_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__MENU_BAR_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__MENU_ITEM_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__PANE_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_ITEM_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TOOLBAR_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TAB_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TAB_PANE_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TABLE_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__CELL_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TEXT_FIELD_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__LIST_ITEM_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__DIALOG_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__POPUP_MENU_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__PROGRESS_BAR_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__TREE_TABLE_CELL_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__GROUP_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_SECTION_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        } else if (code == item.Get_Libraries_Interface_Item__CHART_ITEM_()){
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
>>>>>>> d441c989a7ac7ee3f61119ea067cc7e7c5577212
        } else if (code == item.Get_Libraries_Interface_Item__LABEL_()) {
            ItemIOS itemIOS = new ItemIOS(IOSApplication.accessibilityContainer);
            itemIOS.Initialize(item);
            element = itemIOS;
        }

        if (item.GetName() != null) {
            element.setAccessibilityIdentifier(item.GetName() + " ID");
            element.setAccessibilityValue(item.GetName() + " Value");
            element.setAccessibilityLabel(item.GetName());
        }
        else {
            element.setAccessibilityLabel("Name");
        }

        if (item.GetDescription() != null) {
            element.setAccessibilityHint(item.GetDescription());
        }
        else {
            element.setAccessibilityHint("Description");
        }

        // for debugging
        for (UIAccessibilityTraits trait : element.getAccessibilityTraits()) {
            System.out.println(trait.toString());
        }

        //accessibilityElement.setAccessibilityValue(item.GetDescription());
        element.setAccessibilityIdentifier("" + item.GetHashCode());
        element.setAccessibilityFrame(new CGRect(x, y, width, height));

        // Add the accessibility element to the list
        IOSApplication.accessibilityContainer.getAccessibilityElements().add(element);
        mapAccessibilityElements.put(item.GetHashCode(), element);

        // Inform iOS that the accessibility elements have changed
        UIAccessibilityGlobals.postNotification(UIAccessibilityNotification.ScreenChangedNotification, element);

        if(debug){
            System.out.println("The bounds are" + IOSApplication.accessibilityContainer.getFrame().getX() + " " + IOSApplication.accessibilityContainer.getFrame().getY() + " " + IOSApplication.accessibilityContainer.getFrame().getWidth() + " " + IOSApplication.accessibilityContainer.getFrame().getHeight());
        }
        return true;
    }

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

    private class HiddenView extends UIView {
        public HiddenView() {

        }
    }

    private class HiddenButton extends UIButton {
        public HiddenButton() {

        }
    }
    private class HiddenTextField extends UITextField {
        public HiddenTextField () {

            setKeyboardType(UIKeyboardType.Default);
            setReturnKeyType(UIReturnKeyType.Done);
            setAutocapitalizationType(UITextAutocapitalizationType.None);
            setAutocorrectionType(UITextAutocorrectionType.No);
            setSpellCheckingType(UITextSpellCheckingType.No);
            setHidden(true);
        }

        @Override
        public void deleteBackward () {
            //app.input.inputProcessor.keyTyped((char)8);
            //super.deleteBackward();
            //Gdx.graphics.requestRendering();
        }
    }

    public boolean NativeRemove(Item_ item) {
        return false;
    }

    public void  MenuChanged(MenuChangeEvent_ event) {}

    public void  TreeChanged(TreeChangeEvent_ event) {}

    public void  TreeTableChanged(TreeTableChangeEvent_ event) {}

    public void  ControlActivated(ControlActivationEvent_ event) {}

    public void  TextChanged(TextChangeEvent_ event) {}

    public void  WindowFocusChanged(WindowFocusEvent_ event) {}

    public void  Notify(Item_ item, String value) {}

    public void  Notify(Item_ item, String value, int notificationType) {}

    public void  Shutdown() {}
}
