package plugins.quorum.Libraries.Interface.Mobile;

public class IOSKeyboard {
    public Object me_ = null;
    /*
        This action creates and displays a default keyboard. The default keyboard
        has the TEXT_NO_CORRECTIONS keyboardType and DONE returnButtonType.

        Attribute: Example
        AndroidKeyboard keyboard
        keyboard:DisplayKeyboard()
    */
    public void DisplayKeyboard() {
    }

    /*
        This action creates and displays a customized keyboard. The type of keyboard
        is set by the keyboardType parameter, using one of the keyboardType constants
        from this class, and the enter key appearance/functionality is set by
        the returnButtonType parameter, using one of the returnButtonType constants
        from this class.

        Attribute: Parameter keyboardType The type of keyboard (such as numeric) to create.

        Attribute: Parameter returnButtonType The enter key appearance/functionality of the keyboard.

        Attribute: Example
        AndroidKeyboard keyboard
        keyboard:DisplayKeyboard(keyboard:PHONE, keyboard:DONE)
    */
    public void DisplayKeyboard(int keyboardType, int returnButtonType) {

    }

    /*
        This action closes the keyboard and removes it from the screen. This is
        called from within DisplayKeyboard() and DisplayKeyboard(integer keyboardType, integer returnButtonType)
        when the return button (except when the returnButtonType is LINEFEED) is
        pressed on the keyboard.

        Attribute: Example
        AndroidKeyboard keyboard
        keyboard:DisplayKeyboard(keyboard:PHONE, keyboard:DONE)
        keyboard:CloseKeyboard()
    */
    public void CloseKeyboard() {

    }

    /*
        This action gets the keyboardType of the keyboard.

        Attribute: Return The keyboardType of the keyboard.

        Attribute: Example
        AndroidKeyboard keyboard
        keyboard:DisplayKeyboard()
        integer keyboardType = keyboard:GetKeyboardType()
    */
    public int GetKeyboardType() {

        return 0;
    }

    /*
        This action gets the returnButtonType of the keyboard.

        Attribute: Return The returnButtonType of the keyboard.

        Attribute: Example
        AndroidKeyboard keyboard
        keyboard:DisplayKeyboard()
        integer returnType = keyboard:GetReturnButtonType()
    */
    public int GetReturnButtonType() {

        return 0;
    }
}
