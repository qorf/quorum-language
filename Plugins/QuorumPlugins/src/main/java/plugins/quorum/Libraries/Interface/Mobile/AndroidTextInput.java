package plugins.quorum.Libraries.Interface.Mobile;

import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import plugins.quorum.Libraries.Game.AndroidDisplay;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Libraries.Interface.Controls.TextBox_;
import quorum.Libraries.Interface.Controls.TextField_;
import quorum.Libraries.Interface.Item_;

/*
    Typing into Quorum's text fields and boxes on Android, as on iOS
    (TextFieldIOS): while a text control has the keyboard, the game's view is
    the keyboard's target, and what the keyboard sends goes to the focused
    control on the game's thread, through its Insert and DeleteBackward.
    AndroidKeyboard shows and hides it; the views ask for the connection.
 */
public class AndroidTextInput {
    private static View view = null;
    private static volatile boolean active = false;
    private static int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
    private static int imeOptions = EditorInfo.IME_ACTION_DONE;
    private static boolean multipleLines = false;

    // The game's view, from AndroidDisplay when it makes it.
    public static void SetView(View gameView) {
        view = gameView;
    }

    public static boolean IsActive() {
        return active;
    }

    // Shows the keyboard for the focused text control.
    public static void Show(final int type, final int options, final boolean lines) {
        final View target = view;
        if (target == null) {
            return;
        }
        target.post(new Runnable() {
            public void run() {
                inputType = type;
                imeOptions = options;
                multipleLines = lines;
                active = true;
                android.util.Log.d("QuorumAccessibility", "Keyboard shown");
                target.setFocusable(true);
                target.setFocusableInTouchMode(true);
                target.requestFocus();
                InputMethodManager manager = (InputMethodManager) target.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.restartInput(target);
                manager.showSoftInput(target, 0);
            }
        });
    }

    // Hides the keyboard, when the focus leaves the text control.
    public static void Hide() {
        final View target = view;
        if (target == null) {
            return;
        }
        target.post(new Runnable() {
            public void run() {
                if (!active) {
                    return;
                }
                active = false;
                android.util.Log.d("QuorumAccessibility", "Keyboard hidden");
                InputMethodManager manager = (InputMethodManager) target.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(target.getWindowToken(), 0);
                manager.restartInput(target);
            }
        });
    }

    /*
        The view's connection to the keyboard while a text control has it, or
        null. Words a keyboard is still guessing (composing) are typed and
        then replaced as the guess changes.
     */
    public static InputConnection CreateConnection(View target, EditorInfo info) {
        if (!active) {
            return null;
        }
        info.inputType = inputType;
        info.imeOptions = imeOptions | EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN;
        return new BaseInputConnection(target, false) {
            private int composing = 0;

            @Override
            public boolean commitText(CharSequence text, int newCursorPosition) {
                Delete(composing);
                composing = 0;
                Insert(text.toString());
                return true;
            }

            @Override
            public boolean setComposingText(CharSequence text, int newCursorPosition) {
                Delete(composing);
                composing = text.length();
                Insert(text.toString());
                return true;
            }

            @Override
            public boolean finishComposingText() {
                composing = 0;
                return true;
            }

            @Override
            public boolean deleteSurroundingText(int beforeLength, int afterLength) {
                Delete(beforeLength);
                return true;
            }

            @Override
            public boolean sendKeyEvent(KeyEvent event) {
                if (event.getAction() != KeyEvent.ACTION_DOWN) {
                    return true;
                }
                if (event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    Delete(1);
                } else if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    Enter();
                } else if (event.getUnicodeChar() != 0) {
                    Insert(String.valueOf((char) event.getUnicodeChar()));
                }
                return true;
            }

            @Override
            public boolean performEditorAction(int editorAction) {
                Enter();
                return true;
            }
        };
    }

    // Return: a new line in a text box, or done (the keyboard goes away).
    private static void Enter() {
        if (multipleLines) {
            Insert("\n");
        } else {
            Hide();
        }
    }

    private static void Insert(final String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                Item_ focus = GameStateManager.game == null ? null : GameStateManager.game.GetFocus();
                if (focus instanceof TextField_) {
                    ((TextField_) focus).Insert(text);
                } else if (focus instanceof TextBox_) {
                    ((TextBox_) focus).Insert(text);
                }
            }
        });
    }

    private static void Delete(final int count) {
        if (count <= 0) {
            return;
        }
        AndroidDisplay.RunOnGameThread(new Runnable() {
            public void run() {
                Item_ focus = GameStateManager.game == null ? null : GameStateManager.game.GetFocus();
                for (int i = 0; i < count; i++) {
                    if (focus instanceof TextField_) {
                        ((TextField_) focus).DeleteBackward();
                    } else if (focus instanceof TextBox_) {
                        ((TextBox_) focus).DeleteBackward();
                    }
                }
            }
        });
    }
}
