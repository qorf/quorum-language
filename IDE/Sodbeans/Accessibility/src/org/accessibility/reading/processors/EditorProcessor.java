/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading.processors;

import org.sodbeans.phonemic.AbstractSpeechProcessor;
import org.sodbeans.phonemic.RequestType;
import org.accessibility.sound.SoundUtility;

/**
 * Provides pre and post processing for the text editor reader (EditorReader).
 *
 * @author jeff
 */
public class EditorProcessor extends AbstractSpeechProcessor {

    /**
     * The text that is spoken when the user types or moves across text in a
     * password field.
     */
    private static final String PASSWORD_CHAR = "asterisk";
    private SoundUtility snd = SoundUtility.instance();
    private boolean focusEvent = false;
    private boolean editable = false;
    private String accessibleText = "";
    private String editorText = "";
    private boolean deletedChar = false;
    private boolean startOfDocument = false;
    private boolean endOfDocument = false;
    private boolean codeEditor = false; // set to true if code editor
    private int lineNumber = 0; // <= 0 implies do not read.
    private String braceMatcherText = "";
    private String[][] annotations = null;
    private boolean multiLine = false;
    private boolean passwordField = false;

    public String process() {

        // Is this a focus event? If so, we only care about its accessible
        // information.
        if (focusEvent) {
            return processFocusEvent();
        }

        // If necessary, speak line numbering information.

        if (isStartOfDocument()) {
            snd.playTopOfEditor();
        } else if (endOfDocument) {//we're at the end
            snd.playTopOfEditor();
        }

        if (type == RequestType.TEXT) {
            if (text.matches("(\\s)+")) {
                if (!this.passwordField) {
                    text = "blank";
                } else {
                    text = PASSWORD_CHAR;
                }
            }
            // Code editors get special processing
            if (codeEditor) {
                text = processCode(text);
            }

            text += textPostProcessing();
        } else if (type == RequestType.CHARACTER) {
            if (!this.passwordField) {
                char c = text.charAt(0);

                if (c == '\n') {
                    if (this.isMultiLine()) {
                        text = "new line";
                    } else {
                        text = "enter";
                    }
                } else if (c == ' ') {
                    text = "space";
                } else if (c == '\t') {
                    text = "tab";
                } else {
                    text = processCodeCharacter(c);
                }
            } else {
                text = PASSWORD_CHAR;
                text += charPostProcessing();
            }
        }

        return text;
    }

    /**
     * Focus events are processed much differently than key events. In a focus
     * event, we have our current line of text in `text', our accessible name in
     * `accessibleText', and we use these to form a sane audible focus message.
     *
     * @return
     */
    private String processFocusEvent() {
        String multiLineText = "Text field. ";

        if (this.passwordField) {
            multiLineText = " Password field.";
        } else if (this.multiLine) { // Assuming there's no such thing as a multi-line password field . . . 
            multiLineText = "Multi-line editor.";
        }
        if (accessibleText != null && !accessibleText.equals("N/A")) {
            if (!editable && !this.passwordField) {
                return accessibleText + ". Read only " + multiLineText + " " + text;
            }

            return accessibleText + " gained focus. " + multiLineText;
        } else {
            // No accessible text available. Read what we can.
            // Since read-only fields are less common, I think it is O.K.
            // to read the text in the field last.
            if (!editable) {
                return "Read only " + multiLineText + " " + editorText;
            }

            if (!this.passwordField) {
                return multiLineText + " " + editorText;
            } else {
                return multiLineText;
            }
        }
    }

    /**
     * Add post processing to character requests, such as "deleted" after
     * deleted characters in the editor.
     *
     * @return
     */
    private String charPostProcessing() {
        if (deletedChar && editable) {
            return " deleted";
        }

        return "";
    }

    /**
     * Add post-processing information such as brace matching info or line
     * number information.
     *
     * @param str
     * @return
     */
    private String textPostProcessing() {
        String newStr = "";

        // If available, add brace matcher text.
        newStr += braceMatcherText;

        // Add annotations if available
        newStr += processAnnotations();
        return newStr;
    }

    private String processAnnotations() {
        String processed = "";

        if (annotations != null) {
            for (int i = 0; i < annotations[0].length; i++) {
                processed += ", " + annotations[0][i];
                if (annotations[1][i].contains("Alt-Enter")) {
                    annotations[1][i] = annotations[1][i].replace("'", "");
                    annotations[1][i] = annotations[1][i].replace("-", "");
                    annotations[1][i] = annotations[1][i].replace("(", ",");
                    annotations[1][i] = annotations[1][i].replace(")", "");
                    annotations[1][i] = annotations[1][i].replace("\n", ",");
                    processed += ", " + annotations[1][i];
                }
            }
        }

        return processed;
    }

    /**
     * Processes lines of code and makes them suitable for auditory consumption
     * by converting "(" to left paren, etc.
     *
     * Copied from CodeProcessor.java
     *
     * @param str
     * @return
     */
    private String processCode(String str) {
        String s = "";
        for (int i = 0; i < str.length(); i++) {
            s += processCodeCharacter(str.charAt(i));
        }
        return s;
    }

    /**
     * Processes individual code characters by converting them to more audible
     * forms, such as "left paren" for "(", etc.
     *
     * Copied from CodeCharacterLineProcessor.java
     *
     * @param c
     * @return
     */
    private String processCodeCharacter(char c) {
        /// If it's a password field, reveal no characters.
        if (this.passwordField) {
            return PASSWORD_CHAR;
        }

        String s = "";

        if (c == '@') {
            s += " at ";
        } else if (c == '!') {
            s += " exclamation ";
        } else if (c == '"') {
            s += " double quote ";
        } else if (c == '\'') {
            s += " quote ";
        } else if (c == ':') {
            s += " colon ";
        } else if (c == '?') {
            s += " question ";
        } else if (c == '.') {
            s += " period ";
        } else if (c == '/') {
            s += " slash ";
        } else if (c == '\\') {
            s += " back slash ";
        } else if (c == '*') {
            s += " star ";
        } else if (c == '{') {
            s += " left brace ";
        } else if (c == '}') {
            s += " right brace ";
        } else if (c == '(') {
            s += " left paren ";
        } else if (c == ')') {
            s += " right paren ";
        } else if (c == '[') {
            s += " left bracket ";
        } else if (c == ']') {
            s += " right bracket ";
        } else if (c == '|') {
            s += " bar ";
        } else if (c == ',') {
            s += " comma ";
        } else if (c == ';') {
            s += " semicolon ";
        } else if (c == '=') {
            s += " equals ";
        } else if (c == '<') {
            s += " less than ";
        } else if (c == '>') {
            s += " greater than ";
        } else if (c == '+') {
            s += " plus ";
        } else if (c == '-') {
            s += " minus ";
        } else {
            s += c;
        }

        return s;
    }

    /**
     * @return the braceMatcherText
     */
    public String getBraceMatcherText() {
        return braceMatcherText;
    }

    /**
     * @param braceMatcherText the braceMatcherText to set
     */
    public void setBraceMatcherText(String braceMatcherText) {
        this.braceMatcherText = braceMatcherText;
    }

    /**
     * @return the deletedChar
     */
    public boolean isDeletedChar() {
        return deletedChar;
    }

    /**
     * @param deletedChar the deletedChar to set
     */
    public void setDeletedChar(boolean deletedChar) {
        this.deletedChar = deletedChar;
    }

    /**
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * @return the annotations
     */
    public String[][] getAnnotations() {
        return annotations;
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(String[][] annotations) {
        this.annotations = annotations;
    }

    /**
     * @return the endOfDocument
     */
    public boolean isEndOfDocument() {
        return endOfDocument;
    }

    /**
     * @param endOfDocument the endOfDocument to set
     */
    public void setEndOfDocument(boolean endOfDocument) {
        this.endOfDocument = endOfDocument;
    }

    /**
     * @return the codeEditor
     */
    public boolean isCodeEditor() {
        return codeEditor;
    }

    /**
     * @param codeEditor the codeEditor to set
     */
    public void setCodeEditor(boolean codeEditor) {
        this.codeEditor = codeEditor;
    }

    /**
     * @return the startOfDocument
     */
    public boolean isStartOfDocument() {
        return startOfDocument;
    }

    /**
     * @param startOfDocument the startOfDocument to set
     */
    public void setStartOfDocument(boolean startOfDocument) {
        this.startOfDocument = startOfDocument;
    }

    /**
     * @return the focusEvent
     */
    public boolean isFocusEvent() {
        return focusEvent;
    }

    /**
     * @param focusEvent the focusEvent to set
     */
    public void setFocusEvent(boolean focusEvent) {
        this.focusEvent = focusEvent;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the accessibleText
     */
    public String getAccessibleText() {
        return accessibleText;
    }

    /**
     * @param accessibleText the accessibleText to set
     */
    public void setAccessibleText(String accessibleText) {
        this.accessibleText = accessibleText;
    }

    /**
     * @return the editorText
     */
    public String getEditorText() {
        return editorText;
    }

    /**
     * @param editorText the editorText to set
     */
    public void setEditorText(String editorText) {
        if (!this.passwordField) {
            this.editorText = editorText;
        }
    }

    /**
     * @return the multiLine
     */
    public boolean isMultiLine() {
        return multiLine;
    }

    /**
     * @param multiLine the multiLine to set
     */
    public void setMultiLine(boolean multiLine) {
        this.multiLine = multiLine;
    }

    /**
     * @return the passwordField
     */
    public boolean isPasswordField() {
        return passwordField;
    }

    /**
     * @param passwordField the passwordField to set
     */
    public void setPasswordField(boolean passwordField) {
        this.passwordField = passwordField;
        if (this.passwordField) {
            this.editorText = "";
        }
    }
}
