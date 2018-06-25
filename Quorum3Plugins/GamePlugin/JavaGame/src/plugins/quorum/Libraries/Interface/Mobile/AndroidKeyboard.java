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
    static boolean created = false;         // tracks if keyboard is already created or not
    static int layoutID;                    // to keep track of the layout
    static int textID;                      // to keep track of the textfield
    public int keyboardType = -1;           
    public int returnButtonType = -1;
    public GameStateManager manager = new GameStateManager();   // needed to manually flush the keyboard events sometimes
    public static AndroidInput in = new AndroidInput();         // needed to manually flush the keyboard events sometimes
    
    // Allows users to display a keyboard without specifying parameters.
    // Creates one based on default parameters.
    public void DisplayKeyboard()
    {
        DisplayKeyboard(TEXT_NO_CORRECTIONS, DONE);
    }
    
    // Main function for creating and displaying the soft keyboard.
    // keyboardType should be one of the constants from above to set the type
    // of keyboard to create, and returnButtonType should do the same to set
    // the type of return button.
    public void DisplayKeyboard(int keyboardType, int returnButtonType)
    {   
        if(created) {
            // if a keyboard has already been created, close this one and exit function
            CloseKeyboard();
            return;
        }
        
        this.keyboardType = keyboardType;
        this.returnButtonType = returnButtonType;
        if(manager.GetInput() instanceof AndroidInput)
            in = (AndroidInput)manager.GetInput();
        
        // Programmatically creating a new layout and textview.
        // Layout parameters must be set to be added to the UI correctly.
        LinearLayout layout = new LinearLayout(getContext());
        layoutID = LinearLayout.generateViewId();
        layout.setId(layoutID);
        
        EditText textField = new EditText(getContext());
        textID = EditText.generateViewId();
        textField.setId(textID);
        
        LinearLayout.LayoutParams textFieldParams = new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //textFieldParams.height = 0;   // this line needs to be readded to hide the textfield
        textField.setLayoutParams(textFieldParams);
        
        layout.addView(textField);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
              
        // InputMethodManager deals with handling different types of input
        // (touch, keyboard, etc.). We have to get the system service that
        // lets us enter text on the keyboard.
        InputMethodManager methodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        SetKeyboardType(keyboardType, textField);
        SetKeyboardReturnButton(returnButtonType, textField);
        
        // Anything updating the UI has to be run on the UI thread.
        // Will crash otherwise. Adds the layout to the OpenGL surface.
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                getActivity().addContentView(layout, layoutParams);
                // Using the method manager from before, we simply toggle
                // display of the keyboard (on if off, and vice versa). 
                methodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                textField.requestFocus();   // automatically put cursor on edit text box
            }
        });
       
        created = true;
        
        // TextWatcher looks for changes to the text field and calls these functions
        // when it changes. 
        textField.addTextChangedListener(new TextWatcher() {
            String before = "";
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) {
                before = textField.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {   
            }

            @Override
            public void afterTextChanged(Editable editable) {
                FixInput(before, textField.getText().toString());
            }   
            
            // Kind of a hack and not the best solution (although Android mostly
            // does this delete and replace solution as well). We need to make sure
            // that previous text input events have been processed before we send in
            // more backspace events. Otherwise, since text is always handled after
            // keyboard events by the Quorum game update, we will process all the
            // backspaces at once without any text in the textbox, and then we add
            // in all the various text strings, causing bad input. This is primarily
            // needed due to midline spaces, which splits strings up into three
            // parts (first half, second half, and the new string) but processes
            // them in reverse order (second half, first half, new string).
            private void FixInput(String before, String after)
            {
                in.HandleTextInputEvents();
                for(int i = 0; i < before.length(); i++)
                    KeyboardProcessor.AddKeyboardEvent(0, GLFW_BACKSPACE, QUORUM_BACKSPACE, GLFW_PRESS, 0);
                TextInputProcessor.AddTextInputEvent(0, 0, after); 
            }
        });
        
        // Listens for the return/enter key being pressed.
        textField.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                CloseKeyboard();
                return true;       
            }
        });
    }   
    
    // This function handles closing all of the UI elements that pop up
    // once opening the keyboard. Hides the keyboard and removes the text input
    // field from the screen. Then sets created to false so that the DisplayKeyboard
    // function knows to display all of this again the next time it is called. 
    public void CloseKeyboard() {
        if(created) {
            Activity activity = getActivity();
            ViewGroup parentView = (ViewGroup) activity.findViewById(layoutID);
            InputMethodManager methodManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            // All UI changes must be run on the UI thread to avoid crashes
            activity.runOnUiThread(new Runnable(){
                @Override
                public void run(){
                parentView.removeView(activity.findViewById(layoutID));
                parentView.removeView(activity.findViewById(textID));
                methodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            });
            created = false;
        }
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
    private void SetKeyboardReturnButton(int returnButton, EditText textField)
    {
        switch (returnButton) {
            case SEARCH:
                textField.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
                break;
            case NEXT:
                textField.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                break;
            case GO:
                textField.setImeOptions(EditorInfo.IME_ACTION_GO);
                break;
            case PREVIOUS:
                textField.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
                break;
            case SEND:
                textField.setImeOptions(EditorInfo.IME_ACTION_SEND);
                break;
            case LINEFEED:
                textField.setInputType(textField.getInputType() | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                textField.setImeOptions(EditorInfo.IME_ACTION_UNSPECIFIED);
                break;
            case DONE:
            default:
                textField.setImeOptions(EditorInfo.IME_ACTION_DONE);
                break;
        }
    }
    
    // Function to set the type of keyboard to make, as well as enable/disable
    // any options for it. See the related Quorum class for a description of
    // what each of these constants does. 
    private void SetKeyboardType(int keyboardType, EditText textField)
    {
        switch (keyboardType) {
            case DATE_AND_TIME:
                textField.setInputType(InputType.TYPE_CLASS_DATETIME);
                break;
            case DATE:
                textField.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
                break;
            case TIME:
                textField.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME);
                break;
            case NUMERIC_NO_PUNCTUATION:
                textField.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case NUMERIC_WITH_DECIMAL:
                textField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case NUMERIC_SIGNED:
                textField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                break;
            case NUMERIC_SIGNED_DECIMAL:
                textField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED 
                    | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case PHONE:
                textField.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case EMAIL_ADDRESS:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case TEXT_AUTO_CAPITALIZE_SENTENCES_AUTO_CORRECT:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE 
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                break;
            case TEXT_AUTO_CAPITALIZE_WORDS_AUTO_CORRECT:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE 
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                break;
            case TEXT_AUTO_CAPITALIZE_LETTERS_AUTO_CORRECT:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE 
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                break;
            case TEXT_AUTO_CAPITALIZE_SENTENCES_NO_SUGGESTIONS:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                    | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                break;
            case TEXT_AUTO_CAPITALIZE_WORDS_NO_SUGGESTIONS:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS 
                    | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                break;
            case TEXT_AUTO_CAPITALIZE_LETTERS_NO_SUGGESTIONS:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS 
                    | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                break;
            case TEXT_NO_CORRECTIONS:    
            default:
                textField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
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