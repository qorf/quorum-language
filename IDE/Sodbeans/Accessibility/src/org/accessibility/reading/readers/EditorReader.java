package org.accessibility.reading.readers;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Element;
import org.magnify.MagnifierFactory;
import org.magnify.MagnifierInterface;
import org.netbeans.editor.AnnotationDesc;
import org.netbeans.editor.Annotations;
import org.netbeans.editor.BaseDocument;
import org.netbeans.spi.editor.bracesmatching.BracesMatcher;
import org.netbeans.spi.editor.bracesmatching.BracesMatcherFactory;
import org.openide.util.Exceptions;
import org.accessibility.reading.SappyMasterMatcher;
import org.accessibility.reading.text.CharacterProcessor;
import org.accessibility.reading.text.CodeProcessor;
import org.accessibility.reading.text.LiteralCharacterProcessor;
import org.accessibility.reading.text.TextProcessor;
import org.accessibility.reading.processors.EditorProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.TutorialTextProcessor;
import org.sodbeans.phonemic.SpeechProcessor;
import org.sodbeans.phonemic.RequestType;
import org.accessibility.sound.SoundUtility;

/**
 * This class reads text from any text capable component, like text boxes
 * or text areas. It is hooked into key events to indicate what should be read
 * at what time and under what context, but is not hooked into the specifics
 * of each kind of text. For example, certain programming languages might
 * read poorly and certain kinds of punctuation might be skipped. Ultimately,
 * we'll want some way to determine the kind of language and pass that reading
 * on to that reading interpreter.
 *
 * @author Andreas Stefik
 * @author Andrew Hauck
 * @author Susanna Siebert
 * @author Jeff Wilson
 */
public class EditorReader extends AbstractScreenReader {
    private final String TUTORIAL_TEXT_AREA_NAME = "__sodbeans_tutorial_instructionArea__";
    private HashMap<String, String> annotations;
    private Collection<? extends BracesMatcherFactory> factories;
    private BracesMatcher [] matcher;
    private SoundUtility snd = SoundUtility.instance();
    public EditorReader() {
        annotations = getAnnotationDescription();
        matcher = new BracesMatcher[1];
    }

    private JTextComponent text;
    private TextProcessor textProcessor = new CodeProcessor();
    private CharacterProcessor characterProcessor = new LiteralCharacterProcessor();
    private MagnifierInterface magnifier = MagnifierFactory.getDefaultMagnifier();
    
    private String evalCurrentChar() {
        String currentChar = getCurrentCharacterAsString();
        if (currentChar != null && currentChar.compareTo(" ") == 0) {
            currentChar = "space";
        }
        return currentChar;
    }

    private String evalNextChar() {
        String nextChar = getNextCharacterAsString();
        if (nextChar != null && nextChar.compareTo(" ") == 0) {
            nextChar = "space";
        }

        return nextChar;
    }

    @Override
    public void setObject(Object object) {
        text = null;
        text = (JTextComponent) object;
    }

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
        // If this is the tutorial text area, don't speak anything! It
        // gets in the way.
        String accessibleName = text.getAccessibleContext().getAccessibleName();
        if (accessibleName != null && accessibleName.equals(TUTORIAL_TEXT_AREA_NAME)) {
            NullProcessor proc = new NullProcessor();
            return proc;
        }
        
        //if the screen magnification is on, tell it what's going on
        if(magnifier.isStarted()) {
            try {
                Rectangle x = text.modelToView(text.getCaretPosition());
                
                // Focus at the point in the editor.
                magnifier.setFocusCenter(x.x + text.getLocationOnScreen().x, x.y + 
                        text.getLocationOnScreen().y);
                
                // Move the magnifier as well (if windowed).
                if (!magnifier.isFullScreen()) {
                    magnifier.setLocation(x.x + text.getLocationOnScreen().x - (magnifier.getWidth()/2), x.y + 
                        text.getLocationOnScreen().y - (magnifier.getHeight()/2));
                }
            } catch (BadLocationException ex) { //ignore the call or log it?
                Logger.getLogger(EditorReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Master processor used for the editor.
        // Allows us to do things like append brace matcher text.
        EditorProcessor proc = new EditorProcessor();
        proc.setCodeEditor(true); // assume it is a code editor for now
        proc.setPasswordField(text instanceof JPasswordField);
        
        String message = null;
        boolean addAnnotation = false;
        boolean addMatcherText = false;

        char code = getUberEvent().key.getKeyChar();
        int keyCode = getUberEvent().key.getKeyCode();

        //save whether the shift key is held
        boolean isShiftDown = getUberEvent().key.isShiftDown();
        //save whether the alt key is held
        boolean isAltDown = getUberEvent().key.isAltDown();
        //save whether the meta/altGr key is held
        boolean isMetaDown = getUberEvent().key.isMetaDown();
        //save whether the control key is held
        boolean isControlDown = getUberEvent().key.isControlDown();

        //get operating system
        String os = System.getProperty("os.name");

        //get type of textbox
        String editorType = text.getClass().getName();


        boolean process = false;
        //variable for whether the line information should be spoken
        boolean lineInfo = false;

        // Determine whether or not editing is allowed.
        proc.setEditable(text.isEditable());
        proc.setMultiLine(text instanceof JEditorPane);

        switch (keyCode) {
            //right key is pressed
            case KeyEvent.VK_RIGHT:
                //While the alt key is held on Mac OS - jumps over next word
                if (isAltDown && !getUberEvent().preprocess && os.startsWith("Mac")) {
                    //after event, read previous word
                    proc.setText(getPreviousWord());
                    process = true;
                }
                //while the control key is held on Windows - jumps over next word
                else if (isControlDown && !getUberEvent().preprocess && os.startsWith("Win")){
                    //after event, read previous word
                    proc.setText(getPreviousWord());
                    process = true;
                }
                //while the meta key is held on Mac OS - jumps to end of line
                else if (isMetaDown && !getUberEvent().preprocess && os.startsWith("Mac")) {
                    //after event, read previous word - last word on line
                    proc.setText(getPreviousWord());
                    process = true;
                }
                //otherwise read character that was stepped over
                else {
                    // Don't read newlines here.
                    String c = getCurrentCharacterAsString();
                    if (!c.equals("\n") && !c.equals("\r")) {
                        proc.setText(getCurrentCharacterAsString());
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
  
                    //if you are not using any special keys and you are in the editor, also do auditory brace matching
                    /*if (!isControlDown && !isShiftDown && !isAltDown && !isMetaDown && editorType.equals("org.openide.text.QuietEditorPane")) {
                        addMatcherText = true;
                    }*/
                    process = true;
                }
                break;

            //delete key is pressed
            case KeyEvent.VK_DELETE:
                if (getUberEvent().preprocess) {
                    String word;
                    word = Character.toString(getNextCharacter());
                    //followed by "deleted"
                    proc.setText(word);
                    proc.setRequestType(RequestType.CHARACTER);
                    proc.setDeletedChar(true);
                    process = true;
                }
                break;

            //backspace key is pressed
            case KeyEvent.VK_BACK_SPACE:
                if (getUberEvent().preprocess) {
                    String word;
                    // say character was deleted.
                    proc.setText(Character.toString(getPreviousCharacter()));
                    proc.setRequestType(RequestType.CHARACTER);
                    proc.setDeletedChar(true);
                    process = true;
                }
                break;

            //left key is pressed
            case KeyEvent.VK_LEFT:
                //While the alt key is held on Mac OS - jumps over previous word
                if (isAltDown && !getUberEvent().preprocess && os.startsWith("Mac")) {
                    //after event, read next word
                    proc.setText(getNextWord());
                    process = true;
                }
                //while the control key is held on Windows - jumps over pevious word
                else if (isControlDown && !getUberEvent().preprocess && os.startsWith("Win")){
                    //after event, read next word
                    proc.setText(getNextWord());
                    process = true;
                }
                //while the meta key is held on Mac OS - jumps to beginning of line
                else if (isMetaDown && !getUberEvent().preprocess && os.startsWith("Mac")) {
                    //after event, read next word - first word on line
                    proc.setText(getNextWord());
                    process = true;
                }
                //otherwise read character that was stepped over
                else {
                    // Don't read newlines here.
                    String c = getCurrentCharacterAsString();
                    if (!c.equals("\n") && !c.equals("\r")) {
                        proc.setText(getCurrentCharacterAsString());
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }

                    //if you are not using any special keys and you are in the editor, also do auditory brace matching
                    /*if (!isControlDown && !isShiftDown && !isAltDown && !isMetaDown && editorType.equals("org.openide.text.QuietEditorPane")) {
                        // TODO: Modify handling of matcher text
                        addMatcherText = true;
                    }*/
                    process = true;
                }

                break;

            //up or down arrow key is pressed
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                //read current line
                proc.setText(getCurrentLineOfText());
                process = true;
                lineInfo = true;
                //if you are not using any special keys and you are in the editor, also do auditory brace matching and add annotations
                if (!isControlDown && !isShiftDown && !isAltDown && !isMetaDown && editorType.equals("org.openide.text.QuietEditorPane")) {
                    addMatcherText = true;
                    addAnnotation = true;
                }
                break;

            //end button is pressed
            case KeyEvent.VK_END:
                //read previous word - last word on line
                proc.setText(getPreviousWord());
                process = true;
                break;

            //home button is pressed
            case KeyEvent.VK_HOME:
                //read next word - first word on line
                proc.setText(getNextWord());
                process = true;
                break;

            //0 is pressed
            case KeyEvent.VK_0:
                // If control is down, don't speak "0".
                if (isControlDown || isMetaDown)
                {
                    // Fix to focus bug on Windows
                    proc.setText("");
                    process = false;
                }
                else
                {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        process = true;
                    }
                    //}
                    proc.setRequestType(RequestType.CHARACTER);  
                    }
                break;
                
            //1 is pressed
            case KeyEvent.VK_1:
                //if shift is pressed - exclamation mark is written - read "exclamation"
                /*if (isShiftDown && !getUberEvent().preprocess) {
                    //read "exclamation"
                    proc.setText("!");
                    process = true;
                }
                //otherwise read normal character
                else {*/
                if (Character.isDefined(code) && keyCode != 0) {
                    proc.setText(Character.toString(code));
                    process = true;
                }
                //}
                proc.setRequestType(RequestType.CHARACTER);
                break;

            //C is pressed
            case KeyEvent.VK_C:
                //if control is pressed on Windows, read "copy"
                if (os.startsWith("Win") && isControlDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("copy");
                    process = true;
                }
                //if command (meta-key) is pressed on Mac, read "copy"
                if (os.startsWith("Mac") && isMetaDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("copy");
                    process = true;
                }
                //otherwise read normal character
                else {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
                }
                break;

            //L is pressed
            case KeyEvent.VK_L:
                //if control is pressed
                if (isControlDown && !getUberEvent().preprocess) {
                    //read line number
                    int line = getCurrentLineNumber();
                    proc.setLineNumber(line);
                    process = true;
                }
                //otherwise read normal character
                else {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
                }
                break;

            //V is pressed
            case KeyEvent.VK_V:
                //if control is pressed on Windows, read "copy"
                if (os.startsWith("Win") && isControlDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("paste");
                    process = true;
                }
                //if command (meta-key) is pressed on Mac, read "copy"
                if (os.startsWith("Mac") && isMetaDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("paste");
                    process = true;
                }
                //otherwise read normal character
                else {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
                }
                break;

            //X is pressed
            case KeyEvent.VK_X:
                //if control is pressed on Windows, read "copy"
                if (os.startsWith("Win") && isControlDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("cut");
                    process = true;
                }
                //if command (meta-key) is pressed on Mac, read "copy"
                if (os.startsWith("Mac") && isMetaDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("cut");
                    process = true;
                }
                //otherwise read normal character
                else {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
                }
                break;

            //Y is pressed
            case KeyEvent.VK_Y:
                //if control is pressed on Windows, read "copy"
                if (os.startsWith("Win") && isControlDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("redo");
                    process = true;
                }
                //if command (meta-key) is pressed on Mac, read "copy"
                if (os.startsWith("Mac") && isMetaDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("redo");
                    process = true;
                }
                //otherwise read normal character
                else {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
                }
                break;

            //Z is pressed
            case KeyEvent.VK_Z:
                //if control is pressed on Windows, read "copy"
                if (os.startsWith("Win") && isControlDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("undo");
                    process = true;
                }
                //if command (meta-key) is pressed on Mac, read "copy"
                if (os.startsWith("Mac") && isMetaDown && !getUberEvent().preprocess) {
                    //read line number
                    proc.setText("undo");
                    process = true;
                }
                //otherwise read normal character
                else {
                    if (Character.isDefined(code) && keyCode != 0) {
                        proc.setText(Character.toString(code));
                        proc.setRequestType(RequestType.CHARACTER);
                        process = true;
                    }
                }
                break;

            default:
                if (Character.isDefined(code) && keyCode != 0) {
                    proc.setText(Character.toString(code));
                    proc.setRequestType(RequestType.CHARACTER);
                    process = true;
                }
        }

        if (process) {
            String processed = textProcessor.process("");
            if (lineInfo && proc.isMultiLine()) {
                proc.setLineNumber(getCurrentLineNumber());
                if (getCurrentLineNumber() == 1)
                    proc.setStartOfDocument(true);
                else if(getLastLineNumber() == getCurrentLineNumber())
                    proc.setEndOfDocument(true);
            }
            
            //add the end matcher text to the end of the processed line message
            if (addMatcherText) {
                proc.setBraceMatcherText(getMatchingMessage());
            }
            //ad the line annotation descriptions to the end of the processed line message
            if (addAnnotation) {
                proc.setAnnotations(getAnnotation());
            }
        }

        return proc;
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        // The tutorial text area gets different processing.
        String accessibleName = text.getAccessibleContext().getAccessibleName();
        
        if (accessibleName!= null && accessibleName.equals(TUTORIAL_TEXT_AREA_NAME))
        {
            TutorialTextProcessor proc = new TutorialTextProcessor();
            proc.setText(text.getText());
            return proc;
        }
        
        EditorProcessor proc = new EditorProcessor();
        proc.setPasswordField(text instanceof JPasswordField);
        proc.setFocusEvent(true);
        proc.setEditable(text.isEditable());
        proc.setText(getCurrentLineOfText());
        if (this.text.getClass().getName().equals("org.openide.text.QuietEditorPane") &&
                accessibleName.matches("Editor for .*?")) {
            String[] matches = accessibleName.split("Editor for .*?");
            if (matches.length == 2) {
                accessibleName = matches[1] + " editor";
                accessibleName = accessibleName.replaceAll("\\.", " dot ");
            }
        }
        
        proc.setAccessibleText(accessibleName);
        
        // If this editor is part of the Help Contents window, strip all the HTML.
        if (text.getClass().getName().startsWith("javax.help.")) {
            String newText = text.getText().replaceAll("\\<.*?>","");
            proc.setEditorText(newText);
        } else { 
            proc.setEditorText(text.getText());
        }
        proc.setMultiLine(text instanceof JEditorPane);
        return proc;
    }

    /**
     * Tries to return the next character (character to the right of the caret) as String
     *
     * @return
     */
    private String getNextCharacterAsString() {

        if (text != null) {
            try {
                return text.getText(text.getCaretPosition(), 1);
            } catch (BadLocationException ex) {
                return "";
            }
        }
        return "";
    }

    /**
     * Tries to return the current character (character to the left of the caret) as String
     *
     * @return
     */
    private String getCurrentCharacterAsString() {

        if (text != null) {
            try {
                return text.getText(text.getCaretPosition(), 1);
            } catch (BadLocationException ex) {
                return "";
            }
        }

        return "";
    }

    /**
     * Tries to return the previous character (character to the left of the caret) as char
     *
     * @return
     */
    private char getPreviousCharacter() {

        if (text != null) {
            try {
                char[] chars = text.getText(text.getCaretPosition() - 1, 1).toCharArray();
                if (chars.length > 0) {
                    return chars[0];
                } else {
                    return '\0';
                }
            } catch (BadLocationException ex) {
                return '\0';
            }
        }

        return '\0';
    }

    /**
     * Tries to return the next character (character to the right of the caret) as char
     *
     * @return
     */
    private char getNextCharacter() {

        if (text != null) {
            try {
                char[] chars = text.getText(text.getCaretPosition(), 1).toCharArray();
                if (chars.length > 0) {
                    return chars[0];
                } else {
                    return '\0';
                }
            } catch (BadLocationException ex) {
                return '\0';
            }
        }

        return '\0';
    }

    /**
     * Tries to return the next word (word or word part to the right of the caret) as String
     *
     * @return
     */
    private String getNextWord(){
        if (text != null) {
            try {
                int caret = text.getCaretPosition();
                Element rootElement = text.getDocument().getDefaultRootElement();
                int lineNumber = rootElement.getElementIndex(caret);
                Element lineElement = rootElement.getElement(lineNumber);
                int endIndex = lineElement.getEndOffset();

                String line = text.getDocument().getText(caret, endIndex - caret);
                if (line != null) {
                    String word  = "";
                    for (int i=0; i < line.length() -1 && line.charAt(i)!= ' '; i++) {
                        word = word + line.charAt(i);
                    }
                    return word;
                }
            } catch (BadLocationException ex) {
                return "";
            }
        }
        return "";
    }

    /**
     * Tries to return the previous word (word or word part to the left of the caret) as String
     *
     * @return
     */
    private String getPreviousWord() {
        if (text != null) {
            try {
                int caret = text.getCaretPosition();
                Element rootElement = text.getDocument().getDefaultRootElement();
                int lineNumber = rootElement.getElementIndex(caret);
                Element lineElement = rootElement.getElement(lineNumber);
                int endIndex = lineElement.getEndOffset();

                String line = text.getDocument().getText(caret, endIndex - caret);
                if (line != null && !line.contentEquals("")) {
                    //if the last character in the extracted line is a blank, delete it
                    if (line.charAt(line.length() - 1) == ' '){
                        line = line.substring(0, line.length()-1);
                    }
                    String word  = "";
                    for(int i = 0; (i <= line.length()-1) && (line.charAt(i)!= ' '); i++) {
                        word = word + line.charAt(i) ;
                    }
                    return word;
                }
            } catch (BadLocationException ex) {
                return "";
            }
        }
        return "";
    }

    /**
     * Returns the line of text where the cursor is currently on.
     *
     * @return
     */
    private String getCurrentLineOfText() {
        if (text != null) {
            try {
                int caret = text.getCaretPosition();
                Element rootElement = text.getDocument().getDefaultRootElement();
                int lineNumber = rootElement.getElementIndex(caret);
                Element lineElement = rootElement.getElement(lineNumber);
                int beginIndex = lineElement.getStartOffset();
                int endIndex = lineElement.getEndOffset();

                String line = text.getDocument().getText(beginIndex, endIndex - beginIndex);
                if (line != null) {
                    return line;
                }

            } catch (BadLocationException ex) {
                return "";
            }
        }

        return "";
    }


    /**
     * Tries to determine the current line number that the caret is on.
     * 
     * @return
     */
    private int getCurrentLineNumber() {
        if (text != null) {
            int caret = text.getCaretPosition();
            Element rootElement = text.getDocument().getDefaultRootElement();
            int lineNumber = rootElement.getElementIndex(caret);
            return lineNumber + 1;
        }
        return -1;
    }

    /**
     * Tries to determine the last line number in the file.
     * 
     * @return
     */
    private int getLastLineNumber() {
        if (text != null) {
            int end = text.getText().length();
            Element rootElement = text.getDocument().getDefaultRootElement();
            int lineNumber = rootElement.getElementIndex(end);
            return lineNumber + 1;
        }
        return -1;
    }

    /**
     * returns a String array with all the annotations on the current line
     *
     * @return
     */
    private String[][] getAnnotation(){
        BaseDocument document = (BaseDocument)text.getDocument();
        int lineNumber = getCurrentLineNumber() - 1;
        
        if (document != null && lineNumber != -1) {
            //get a list if all annotations in the document
            Annotations docAnnotations = document.getAnnotations();
            int count = docAnnotations.getNumberOfAnnotations(lineNumber);
            if (count == 0) {
                return null;
            }
            else {
                //get the active Annotation
                AnnotationDesc active = docAnnotations.getActiveAnnotation(lineNumber);
                String[][] list = new String[2][count];
                list[0][0] = annotations.get(active.getAnnotationType());
                list[1][0] = active.getShortDescription();
                if (list[0][0].equals("Compiler Error")) {
                    snd.playCompilerError();
                }
                if (count > 1){
                    //get the inactive Annotation(s)
                    AnnotationDesc[] pasive = docAnnotations.getPasiveAnnotations(lineNumber);
                    for (int i = 1; i < count; i++){
                        list[0][i] = annotations.get(pasive[i-1].getAnnotationType());
                        list[1][i] = pasive[i-1].getShortDescription();
                        
                        if (list[0][i].equals("Compiler Error")) {
                            snd.playCompilerError();
                        }
                    }
                }
                return list;
            }
        }
        return null;
    }

    /**
     * returns a hash map of all line annotations with a custom annotation description
     * line annotations can be found in the xml files under Editors/AnnotationTypes
     *
     * @return
     */
    private HashMap<String, String> getAnnotationDescription(){
        HashMap<String, String> hm = new HashMap<String, String>();

        //red square
        //Breakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/Breakpoint.png'
        hm.put("Breakpoint", "Breakpoint");

        //torn red square
        //Breakpoint_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/Breakpoint_broken.png'
        hm.put("Breakpoint_broken", "Invalid Breakpoint");
        
        //BreakpointableLine.xml
        //no glyph
        hm.put("BreakpointableLine", "Breakpointable Line");

        //purple triangle
        //CallSite.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/CallSite.png'
        hm.put("CallSite", "Call Site");

        //red triangle
        //ClassBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/breakpointsView/NonLineBreakpoint.gif'
        hm.put("ClassBreakpoint", "Class Breakpoint");

        //red sideways L
        //CondBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/ConditionalBreakpoint.png'
        hm.put("CondBreakpoint", "Conditional Breakpoint");

        //red sideways L with torn corner
        //CondBreakpoint_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/ConditionalBreakpoint_broken.png'
        hm.put("CondBreakpoint_broken", "Invalid Conditional Breakpoint");
        
        //CurrentExpression.xml
        //no glyph
        hm.put("CurrentExpression", "Current Expression");

        //green arrow
        //CurrentExpressionLine.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/PC.gif'
        hm.put("CurrentExpressionLine", "Current Expression Line");
        
        //red square with green arrow pointing right
        //CurrentExpressionLine_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/Breakpoint+PC.png'
        hm.put("CurrentExpressionLine_BP", "Stopped at Breakpoint");

        //red sideways L with green arrow pointing right
        //CurrentExpressionLine_CBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/ConditionalBreakpoint+PC.png'
        hm.put("CurrentExpressionLine_CBP", "Stopped at Conditional Breakpoint");

        //grey square with green arrow pointing right
        //CurrentExpressionLine_DBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledBreakpoint+PC.png'
        hm.put("CurrentExpressionLine_DBP", "Disabled Breakpoint at Current Execution");

        //grey sideways L with green arrow pointing right
        //CurrentExpressionLine_DCBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledConditionalBreakpoint+PC.png'
        hm.put("CurrentExpressionLine_DCBP", "Disabled Conditional Breakpoint at Current Execution");

        //green arrow pointing right
        //CurrentPC
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/PC.gif'
        hm.put("CurrentPC", "Current Execution");
        
        //CurrentPC2.xml
        //no glyph
        hm.put("CurrentPC2", "Current Execution");
        
        //CurrentPC2LinePart.xml
        //no glyph
        hm.put("CurrentPC2LinePart", "Current Execution");

        //red square
        //CurrentPC2_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/Breakpoint.png'
        hm.put("CurrentPC2_BP", "Breakpoint");

        //grey square
        //CurrentPC2_DBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledBreakpoint.png'
        hm.put("CurrentPC2_DBP", "Disabled Breakpoint");

        //green arrow pointing right
        //CurrentPCLinePart.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/PC.gif'
        hm.put("CurrentPCLinePart", "Current Execution");

        //yellow square
        //Deprecation.xml
        //glyph="nbresloc:/org/netbeans/modules/languages/resources/warning-glyph.gif"
        hm.put("Deprecation", "Deprecated Expression");

        //yellow square
        //Deprecation2.xml
        //glyph="nbresloc:/org/netbeans/modules/languages/resources/warning-glyph.gif"
        hm.put("Deprecation2", "Deprecated Expression");

        //grey square
        //DisabledBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledBreakpoint.png'
        hm.put("DisabledBreakpoint", "Disabled Breakpoint");

        //grey triangle
        //DisabledClassBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/breakpointsView/DisabledNonLineBreakpoint.gif'
        hm.put("DisabledClassBreakpoint", "Disabled Class Breakpoint");

        //grey sideways L
        //DisabledCondBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledConditionalBreakpoint.png'
        hm.put("DisabledCondBreakpoint", "Disabled Conditional Breakpoint");

        //grey triangle
        //DisabledFieldBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/breakpointsView/DisabledNonLineBreakpoint.gif'
        hm.put("DisabledFieldBreakpoint", "Disabled Field Breakpoint");

        //grey triangle
        //DiabledMethodBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/breakpointsView/DisabledNonLineBreakpoint.gif'
        hm.put("DisabledMethodBreakpoint", "Disabled Method Breakpoint");

        //red square with x
        //Error.xml
        //glyph="nbresloc:/org/netbeans/modules/languages/resources/error-glyph.gif"
        hm.put("Error", "Error");

        //red triangle
        //FieldBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/breakpointsView/NonLineBreakpoint.gif'
        hm.put("FieldBreakpoint", "Field Breakpoint");

        //red triangle
        //MethodBreakpoint.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/breakpointsView/NonLineBreakpoint.gif'
        hm.put("MethodBreakpoint", "Method Breakpoint");

        //yellow gear
        //OtherThread.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_suspended.png'
        hm.put("OtherThread", "Suspended Other Thread");

        //yellow gear and red square
        //OtherThread_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint.png'
        hm.put("OtherThread_BP", "Suspended Other Thread with Breakpoint");

        //yello gear and red square
        //OtherThread_BP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint.png'
        hm.put("OtherThread_BP_broken", "Suspended Other Thread with Invalid Breakpoint");

        //yello gear and read square
        //OtherThread_DBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint.png'
        hm.put("OtherThread_DBP", "Suspended Other Thread with Disabled Breakpoint");

        //yellow gear and green arrow pointing right
        //OtherThread_PC.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_arrow.png'
        hm.put("OtherThread_PC", "Suspended Other Thread at Current Execution");

        //yellow gear, red square and green arrow pointing right
        //OtherThread_PC_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint_arrow.png'
        hm.put("OtherThread_PC_BP", "Stopped at Breakpoint for Suspended Other Thread");

        //yellow gear
        //OtherThreads.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_suspended.png'
        hm.put("OtherThreads", "Suspended Other Threads");

        //yellow gear and red square
        //OtherThreads_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint.png'
        hm.put("OtherThreads_BP", "Suspended Other Threads with Breakpoint");

        //yellow gear and red square
        //OtherThreads_BP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint.png'
        hm.put("OtherThreads_BP_broken", "Suspended Other Threads with Invalid Breakpoint");

        //yellow gear and red square
        //OtherThreads_DBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint.png'
        hm.put("OtherThreads_DBP", "Suspended Other Threads with Disabled Breakpoint");

        //yellow gear, red square and green arrow pointing right
        //OtherThreads_PC_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/other_threads_breakpoint_arrow.png'
        hm.put("OtherThreads_PC_BP", "Stopped at Breakpoint for Suspended Other Threads");

        //red square and arrow pointing right
        //PC_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/Breakpoint+PC.png'
        hm.put("debugger-PC_BP", "Stopped at breakpoint");

        //torn red square and green arrow pointing right
        //PC_BP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/Breakpoint+PC_broken.png'
        hm.put("debugger-PC_BP_broken", "Invalid Breakpoint at Current Execution");

        //red sideways L and green arrow pointing right
        //PC_CBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/ConditionalBreakpoint+PC.png'
        hm.put("debugger-PC_CBP", "Stopped at Conditional Breakpoint");

        //red sideways L with jagged corner and green arrow pointing right
        //PC_CBP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/ConditionalBreakpoint+PC_broken.png'
        hm.put("debugger-PC_CBP_broken", "Invalid Conditional Breakpoint at Current Execution");

        //grey square with green arrow pointing right
        //PC_DBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledBreakpoint+PC.png'
        hm.put("debugger-PC_DBP", "Disabled Breakpoint at Current Execution");

        //grey sideways L with green arrow pointing right
        //PC_DCBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/DisabledConditionalBreakpoint+PC.png'
        hm.put("debugger-PC_DCBP", "Disabled Conditional Breakpoint at Current Execution");

        //two red overlapping squares and green arrow pointing right
        //PC_mixedBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint+PC.png'
        hm.put("debugger-PC_mixedBP", "Stopped at Mixed Breakpoints");

        //two red overlapping squares, one with jagged edges and green arrow pointing right
        //PC_mixedBP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint+PC_broken.png'
        hm.put("debugger-PC_mixedBP_broken", "Mixed Invalid Breakpoints at Current Execution");

        //two red overlapping squares and green arrow pointing right
        //PC_multi_BPCBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint+PC.png'
        hm.put("debugger-PC_multi_BPCBP", "Stopped at Multiple Breakpoints");

        //two red overlapping squares, one with jagged edges and green arrow pointing right
        //PC_multi_BPCBP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint+PC_broken.png'
        hm.put("debugger-PC_multi_BPCBP_broken", "Multiple Invalid Breakpoints at Current Execution");

        //two grey overlapping squares and green arrow pointing right
        //PC_multi_DBPCBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiDisabledBreakpoint+PC.png'
        hm.put("debugger-PC_multi_DBPCBP", "Multiple Disabled Breakpoints at Current Execution");

        //red square with x
        //SyntaxError.xml
        //glyph="nbresloc:/org/netbeans/modules/languages/resources/error-glyph.gif"
        hm.put("SyntaxError", "Syntax Error");
        
        //Usage.xml
        //no glyph
        hm.put("Usage", "Usage");

        //yellow rectangle
        //Warning
        //glyph="nbresloc:/org/netbeans/modules/languages/resources/warning-glyph.gif"
        hm.put("Warning", "Warning");
        
        //_mixed_BPCBP.xml
        //no glyph
        hm.put("debugger-mixed_BPCBP", "");
        
        //_mixed_BPCBP_broken.xml
        //no glyph
        hm.put("debugger-mixed_BPCBP_broken", "");
        
        //_mixed_BPD.xml
        //no glyph
        hm.put("debugger-mixed_BPD", "");
        
        //_mixed_BPD_broken.xml
        //no glyph
        hm.put("debugger-mixed_BPD_broken", "");
        
        //_mixed_CBPD.xml
        //no glyph
        hm.put("debugger-mixed_CBPD", "");
        
        //_mixed_CBPD_broken.xml
        //no glyph
        hm.put("debugger-mixed_CBPD_broken", "");
        
        //_mixed_DBP.xml
        //no glyph
        hm.put("debugger-mixed_DBP", "");
        
        //_mixed_DBPCBP.xml
        //no glyph
        hm.put("debugger-mixed_DBPCBP", "");
        
        //_mixed_DBP_broken.xml
        //no glyph
        hm.put("debugger-mixed_DBP_broken", "");
        
        //_mixed_DCBP.xml
        //no glyph
        hm.put("debugger-mixed_DCBP", "");

        //_mixed_DCBP_broken.xml
        //no glyph
        hm.put("debugger-mixed_DCBP_broken", "");
        
        //_multi_BP.xml
        //no glyph
        hm.put("debugger-multi_BP", "");
        
        //_multi_BP_broken.xml
        ///no glyph
        hm.put("debugger-multi_BP_broken", "");
        
        //_multi_CBP.xml
        //no glyph
        hm.put("debugger-multi_CBP", "");
        
        //_multi_CBP_broken.xml
        //no glyph
        hm.put("debugger-multi_CBP_broken", "");
        
        //_multi_DBP.xml
        //no glyph
        hm.put("debugger-multi_DBP", "");
        
        //_multi_DCBP.xml
        //no glyph
        hm.put("debugger-multi_DCBP", "");

        //blue squared arrow pointing right
        //bookmark.xml
        //glyph='nbresloc:/org/netbeans/modules/editor/bookmarks/resources/bookmark_16.png'
        hm.put("editor-bookmark", "Bookmark");

        //grey circle
        //implements-has-implementations-combined.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/override-is-overridden-combined.png"
        hm.put("org-netbeans-modules-editor-annotations-implements-has-implementations-combined", "Combined Method Implements and "
                + "Has Implementations");

        //grey circle
        //implements-is-overridden-combined.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/override-is-overridden-combined.png"
        hm.put("org-netbeans-modules-editor-annotations-implements-is-overridden-combined", "Combined Method Implements and Is "
                + "Overridden");

        //two red overlapping squares
        //mixed_BP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint.png'
        hm.put("debugger-mixed_BP", "Multiple Breakpoints");

        //two red overlapping squares, one with jagged edges
        //mixed_BP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint_broken.png'
        hm.put("debugger-mixed_BP_broken", "Multiple Invalid Breakpoints");

        //two red overlapping squares
        //multi_BPCBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint.png'
        hm.put("debugger-multi_BPCBP", "Mutliple Breakpoints");

        //two red overlapping squares, one with jagged edges
        //multi_BPCBP_broken.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiBreakpoint_broken.png'
        hm.put("debugger-multi_BPCBP_broken", "Multiple Invalid Breakpoints");

        //two grey overlapping squares
        //multi_DBPCBP.xml
        //glyph='nbresloc:/org/netbeans/modules/debugger/resources/editor/MultiDisabledBreakpoint.png'
        hm.put("debugger-multi_DBPCBP", "Multiple Diabled Breakpoints");

        //grey circle with I
        //org-netbeans-modules-editor-annotations-has_implementations.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/has-implementations.png"
        hm.put("org-netbeans-modules-editor-annotations-has_implementations", "Method Has Implementations");

        //green circle with I
        //org-netbeans-modules-editor-annotations-implements.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/implements.png"
        hm.put("org-netbeans-modules-editor-annotations-implements", "Method Implements");

        //grey circle with circle
        //org-netbeans-modules-editor-annotations-is_overridden.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/is-overridden.png"
        hm.put("org-netbeans-modules-editor-annotations-is_overridden", "Method Is Overridden");

        //green circle with circle
        //org-netbeans-modules-editor-annotations-overrides.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/overrides.png"
        hm.put("org-netbeans-modules-editor-annotations-overrides", "Method Ovberrides");

        //org-netbeans-modules-mercurial-Annotation.xml
        //no glyph
        hm.put("org-netbeans-modules-mercurial-Annotation", "");

        //org-netbeans-modules-subversion-Annotation.xml
        //no glyph
        hm.put("org-netbeans-modules-subversion-Annotation", "");

        //org-netbeans-modules-versioning-annotate-Annotation.xml
        //no glyph
        hm.put("org-netbeans-modules-versioning-annotate-Annotation", "");

        //org-netbeans-modules-versioning-system-cvss-Annotation.xml
        //no glyph
        hm.put("org-netbeans-modules-versioning-system-cvss-Annotation", "");

        //red square with x
        //org-netbeans-modules-xml-error.xml
        //glyph="nbresloc:/org/netbeans/modules/xml/resources/error-glyph.gif"
        hm.put("org-netbeans-modules-xml-error", "XML Error");

        //red circle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_err.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/error-glyph.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_err", "Compiler Error");

        //light bulb with little red cicle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_err_fixable.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/error-sg.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_err_fixable", "Compiler Error with Suggestions");

        //yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_hint.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/warning-glyph.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_hint", "Hint");

        //light bulb
        //org-netbeans-spi-java-parser_annotation_hint_fixable.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/suggestion.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_hint_fixable", "Hint with Suggestions");

        //yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_todo.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/warning-glyph.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_todo", "To Do");

        //light bulb with litte yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_todo_fixable.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/warning-sg.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_todo_fixable", "To Do with Suggestions");

        //yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_verifier.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/verifier-glyph.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_verifier", "Verifyer Warning");

        //light bulb with litte yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_verifier_fixable.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/verifier-sg.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_verifier_fixable", "Verifyer Warning with Suggestions");

        //yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_warn.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/warning-glyph.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_warn", "Compiler Warning");

        //light bulb with litte yellow triangle with exclamation mark
        //org-netbeans-spi-java-parser_annotation_warn_fixable.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/hints/resources/warning-sg.gif"
        hm.put("org-netbeans-spi-editor-hints-parser_annotation_warn_fixable", "Compiler Warning with Suggestions");

        //grey circle
        //override-is-overridden-combined.xml
        //glyph="nbresloc:/org/netbeans/modules/editor/resources/override/override-is-overridden-combined.png"
        hm.put("org-netbeans-modules-editor-annotations-override-is-overridden-combined", "Combined Method Override and Is Overridden");

        return hm;
    }

    /**
     * aural end matcher. Returns a String of the text that is to be spoken.
     *
     * @return
     */
    private String getMatchingMessage(){
        String matchingMessage = "";

        int caret = text.getCaretPosition();
        Document document = text.getDocument();

        factories = SappyMasterMatcher.findFactories(document, caret, true);

        if (factories != null) {
            matcher = SappyMasterMatcher.findMatcher(matcher, factories, document, caret, true, 1);

            if (matcher[0] != null) {
                SappyMasterMatcher mastermatcher = new SappyMasterMatcher(text);
                SappyMasterMatcher.Result result = mastermatcher.new Result(document, caret, "backward", 0, 1, 1);

                int [] origin = null;
                int [] match = null;

                try {
                    origin = result.findOrigin(true, matcher);
                    if (origin != null) {
                        match = matcher[0].findMatches();
                    }
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                }

                if (origin != null) {
                    try {
                        String originText = text.getText(origin[0], origin[1] - origin[0]);
                        if (match != null) {
                            try {
                                int matchingLineNumber = text.getDocument().getDefaultRootElement().getElementIndex(match[0]) + 1;
                                String matchText = text.getText(match[0], match[1] - match[0]);
                                matchingMessage = ", matches " + matchText + " at line " + matchingLineNumber;
                            } catch (BadLocationException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        } else {
                            matchingMessage = ", no match ";
                        }
                    } catch (BadLocationException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
        }
        return matchingMessage;
    }
}
