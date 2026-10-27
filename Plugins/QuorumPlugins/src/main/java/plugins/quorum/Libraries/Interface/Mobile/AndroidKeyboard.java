package plugins.quorum.Libraries.Interface.Mobile;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.util.List;
import plugins.quorum.Libraries.Interface.Events.KeyboardProcessor;
import plugins.quorum.Libraries.Interface.Events.TextInputProcessor;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Game.AndroidInput;

public class AndroidKeyboard {
    // Keyboard event constants
    public static final int GLFW_BACKSPACE = 259;
    public static final int GLFW_RELEASE = 0;
    public static final int GLFW_PRESS = 1;
    public static final int QUORUM_BACKSPACE = 67;
    
    // Keyboard type constants, used in SetKeyboardType
    public static final int DATE_AND_TIME = 0;
    public static final int DATE = 1;
    public static final int TIME = 2;
    public static final int NUMERIC_NO_PUNCTUATION = 3;
    public static final int NUMERIC_WITH_DECIMAL = 4;
    public static final int NUMERIC_SIGNED = 5;
    public static final int NUMERIC_SIGNED_DECIMAL = 6;
    public static final int PHONE = 7;
    public static final int EMAIL_ADDRESS = 8;
    public static final int TEXT_AUTO_CAPITALIZE_SENTENCES_AUTO_CORRECT = 9;
    public static final int TEXT_AUTO_CAPITALIZE_WORDS_AUTO_CORRECT = 10;
    public static final int TEXT_AUTO_CAPITALIZE_LETTERS_AUTO_CORRECT = 11;
    public static final int TEXT_AUTO_CAPITALIZE_SENTENCES_NO_SUGGESTIONS = 12;
    public static final int TEXT_AUTO_CAPITALIZE_WORDS_NO_SUGGESTIONS = 13;
    public static final int TEXT_AUTO_CAPITALIZE_LETTERS_NO_SUGGESTIONS = 14;
    public static final int TEXT_NO_CORRECTIONS = 15;

    // Keyboard action button constants, used in SetKeyboardReturnButton
    public static final int SEARCH = 0;
    public static final int NEXT = 1;
    public static final int GO = 2;
    public static final int PREVIOUS = 3;
    public static final int SEND = 4;
    public static final int LINEFEED = 5;
    public static final int DONE = 6;
  
    public Object me_ = null;
    public int keyboardType = -1;           
    public int returnButtonType = -1;
    
    // Allows users to display a keyboard without specifying parameters.
    // Creates one based on default parameters.
    public void DisplayKeyboard()
    {
        DisplayKeyboard(TEXT_NO_CORRECTIONS, DONE);
    }
    
    // Shows the soft keyboard for the focused text field or box, with a
    // keyboard type and a return button from the constants above. The
    // keyboard types into the game's own view (AndroidTextInput), as on iOS.
    public void DisplayKeyboard(int keyboardType, int returnButtonType)
    {
        this.keyboardType = keyboardType;
        this.returnButtonType = returnButtonType;
        int inputType = InputTypeFor(keyboardType);
        boolean lines = returnButtonType == LINEFEED;
        if (lines)
            inputType = inputType | InputType.TYPE_TEXT_FLAG_MULTI_LINE;
        AndroidTextInput.Show(inputType, ImeOptionsFor(returnButtonType), lines);
    }

    // Hides the soft keyboard.
    public void CloseKeyboard()
    {
        AndroidTextInput.Hide();
    }

    public int GetKeyboardType()
    {
        return keyboardType;
    }
    
    public int GetReturnButtonType()
    {
        return returnButtonType;
    }
    
    // This function sets the action button on the keyboard based on the second
    // parameter sent to DisplayKeyboard function. Mostly a cosmetic change,
    // although linefeed does have special functionality to allow for multi-line
    // input. Appearance of each button is keyboard specific, but should be similar
    // within applications on the same device (one messaging app send button
    // looks the same as another if using the same keyboard). See constants in
    // the Quorum class for a descripton of what each looks like.
    private int ImeOptionsFor(int returnButton)
    {
        switch (returnButton) {
            case SEARCH:
                return EditorInfo.IME_ACTION_SEARCH;
            case NEXT:
                return EditorInfo.IME_ACTION_NEXT;
            case GO:
                return EditorInfo.IME_ACTION_GO;
            case PREVIOUS:
                return EditorInfo.IME_ACTION_PREVIOUS;
            case SEND:
                return EditorInfo.IME_ACTION_SEND;
            case LINEFEED:
                // The multi-line flag is added in DisplayKeyboard.
                return EditorInfo.IME_ACTION_UNSPECIFIED;
            case DONE:
            default:
                return EditorInfo.IME_ACTION_DONE;
        }
    }
    
    // Function to set the type of keyboard to make, as well as enable/disable
    // any options for it. See the related Quorum class for a description of
    // what each of these constants does. 
    private int InputTypeFor(int keyboardType)
    {
        switch (keyboardType) {
            case DATE_AND_TIME:
                return InputType.TYPE_CLASS_DATETIME;
            case DATE:
                return InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE;
            case TIME:
                return InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME;
            case NUMERIC_NO_PUNCTUATION:
                return InputType.TYPE_CLASS_NUMBER;
            case NUMERIC_WITH_DECIMAL:
                return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
            case NUMERIC_SIGNED:
                return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED;
            case NUMERIC_SIGNED_DECIMAL:
                return InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED 
                    | InputType.TYPE_NUMBER_FLAG_DECIMAL;
            case PHONE:
                return InputType.TYPE_CLASS_PHONE;
            case EMAIL_ADDRESS:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
            case TEXT_AUTO_CAPITALIZE_SENTENCES_AUTO_CORRECT:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE 
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
            case TEXT_AUTO_CAPITALIZE_WORDS_AUTO_CORRECT:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE 
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_WORDS;
            case TEXT_AUTO_CAPITALIZE_LETTERS_AUTO_CORRECT:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE 
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
            case TEXT_AUTO_CAPITALIZE_SENTENCES_NO_SUGGESTIONS:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                    | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES;
            case TEXT_AUTO_CAPITALIZE_WORDS_NO_SUGGESTIONS:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS 
                    | InputType.TYPE_TEXT_FLAG_CAP_WORDS;
            case TEXT_AUTO_CAPITALIZE_LETTERS_NO_SUGGESTIONS:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS 
                    | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;
            case TEXT_NO_CORRECTIONS:    
            default:
                return InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
        }
    }
    
    private Activity getActivity() {
        Activity activity = plugins.quorum.Libraries.Game.AndroidApplication.GetActivity();
        return activity;
    }
    
    private Context getContext(){
        Context context = getActivity().getApplicationContext();
        return context;
    }
    
}