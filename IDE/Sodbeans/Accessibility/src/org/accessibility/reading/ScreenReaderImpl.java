/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading;

import java.awt.Component;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JRootPane;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import org.openide.awt.MenuBar;
import org.openide.windows.TopComponent;
import org.openide.windows.TopComponent.Registry;
import org.openide.windows.WindowManager;
import org.accessibility.ScreenReader;
import org.accessibility.reading.processors.EditorProcessor;
import org.accessibility.reading.readers.ReadType;
import org.accessibility.reading.readers.ComponentScreenReader;
import org.sodbeans.phonemic.SpeechPriority;
import org.sodbeans.phonemic.SpeechProcessor;
import org.sodbeans.phonemic.TextToSpeechFactory;
import org.sodbeans.phonemic.tts.TextToSpeech;
import org.accessibility.options.AccessibilityOptions;

/**
 * This object grabs control of various API's for listening to events
 * and forwards them to a set of objects that, collectively, read the screen
 * and allow the user to understand what is going on through audio.
 *
 * @author Andreas Stefik
 */
public class ScreenReaderImpl implements ScreenReader, KeyEventPostProcessor, PropertyChangeListener {

    private KeyboardFocusManager manager;
    private ScreenReaderFactory readers = new ScreenReaderFactory();
    private UberEvent event = new UberEvent();
    private WindowManager windows;
    private static TopComponent lc = null;
    private long previousDeleteTime = 0;
    private TextToSpeech speech = TextToSpeechFactory.getDefaultTextToSpeech();
    
    public void gainControl() {
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(this);
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {

            public boolean dispatchKeyEvent(KeyEvent e) {
                long time = e.getWhen();
                if (e.getKeyCode() == KeyEvent.VK_DELETE
                        || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                        || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    long delta = time - previousDeleteTime;
                    if (delta < 150) {//prevent netbeans from strangely sending
                        //duplicate preprocess events.
                        return false;
                    }
                    previousDeleteTime = time;
                    Object object = e.getComponent();
                    if (object != null) {
                        event.reset();
                        event.key = e;
                        event.readType = ReadType.KEYBOARD;
                        event.object = object;
                        event.preprocess = true;
                        ComponentScreenReader reader = readers.instance(event);
                        if(AccessibilityOptions.isSelfVoicing()) {
                            reader.read();
                        }
                    }
                }
                return false;//important, do not change
                //prevents key from going to editor, if true
            }
        });
        manager.addPropertyChangeListener(this);

        MenuSelectionManager menuManager = MenuSelectionManager.defaultManager();
        menuManager.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                Object o = e.getSource();
                if (o instanceof MenuSelectionManager) {
                    MenuSelectionManager manager = (MenuSelectionManager) o;
                    MenuElement[] elements = manager.getSelectedPath();
                    if (elements.length != 0) {
                        MenuElement element = elements[elements.length - 1];
                        event.reset();
                        event.object = element;
                        event.readType = ReadType.MENU;
                        ComponentScreenReader reader = readers.instance(event);
                        if(AccessibilityOptions.isSelfVoicing()) {
                            reader.read();
                        }
                        reader.magnify();
                    }
                }
            }
        });

        runInEventDispatchThread(new Runnable() {

            public void run() {
                windows = WindowManager.getDefault();
                Registry reg = windows.getRegistry();
                JFrame frame = (JFrame) windows.getMainWindow();
                JRootPane pane = frame.getRootPane();
                JLayeredPane layered = pane.getLayeredPane();
                Object[] comps = layered.getComponents();
                for (int i = 0; i < comps.length; i++) {
                    if (comps[i] instanceof MenuBar) {
                        MenuBar bar = (MenuBar) comps[i];
                        bar.waitFinished();
                        int num = bar.getMenuCount();
                        for (int j = 0; j < num; j++) {
                            Object o = bar.getMenu(j);
                            if (o != null) {
                                JMenu menu = (JMenu) o;
                                menu.addMenuListener(new MenuListener() {

                                    public void menuSelected(MenuEvent e) {
                                        if (e.getSource() instanceof JMenu) {
                                            event.reset();
                                            event.object = e.getSource();
                                            event.readType = ReadType.MENU;
                                            ComponentScreenReader reader = readers.instance(event);
                                            if(AccessibilityOptions.isSelfVoicing()) {
                                                reader.read();
                                            }
                                            reader.magnify();
                                        }
                                    }

                                    public void menuDeselected(MenuEvent e) {
                                    }

                                    public void menuCanceled(MenuEvent e) {
                                    }
                                });
                            }
                        }
                    }
                }

            }
        });
    }

    public void loseControl() {
        manager.removeKeyEventPostProcessor(this);
        manager = null;
    }

    private void runInEventDispatchThread(Runnable runnable) {
        if (javax.swing.SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            javax.swing.SwingUtilities.invokeLater(runnable);
        }
    }

    public boolean postProcessKeyEvent(KeyEvent e) {

        Object object = e.getComponent();

        if (object != null && e.getID() == KeyEvent.KEY_PRESSED) {
                event.reset();
                event.key = e;
                event.readType = ReadType.KEYBOARD;
                event.object = object;
                ComponentScreenReader reader = readers.instance(event);
                if(AccessibilityOptions.isSelfVoicing()) {
                    reader.read();
                }
                reader.magnify();
                return true;
        }
        return false;
    }

    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (("focusOwner".equals(prop))) {
            Object object = e.getNewValue();

            if (object != null) {
                event.reset();
                event.object = object;
                event.readType = ReadType.FOCUS;
                ComponentScreenReader reader = readers.instance(event);
                if(AccessibilityOptions.isSelfVoicing()) {
                    reader.read();
                }
                reader.magnify();
            }
        }
    }

    public void magicKeyLeft(Object object) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
    }

    public void magicKeyRight(Object object) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
    }

    /**
     * In this implementation, up and down keys do nothing.
     * @param object
     */
    public void magicKeyUp(Object object) {
//        TopComponent top = TopComponent.getRegistry().getActivated();
//        java.util.Iterator<TopComponent> comps = TopComponent.getRegistry().getOpened().iterator();
//        TopComponent firstTop = null;
//        boolean first = true;
//        boolean halt = false;
//        while(comps.hasNext() && !halt) {
//            TopComponent top2 = comps.next();
//            if(first == true) {
//                firstTop = top2;
//                first = false;
//            }
//            if(top2 == top) {
//                if(comps.hasNext()) {
//                    top2 = comps.next();
//                  //  top2.requestActive();
//                  //  top2.requestFocus(true);
//                }
//                else {
//                   // firstTop.requestActive();
//                   // firstTop.requestFocus(true);
//                }
//                halt = true;
//            }
//        }
    }

    public void magicKeyDown(Object object) {
//        TopComponent top = TopComponent.getRegistry().getActivated();
//        java.util.Iterator<TopComponent> comps = TopComponent.getRegistry().getOpened().iterator();
//        boolean halt = false;
//        TopComponent previous = null;
//        while(comps.hasNext() && !halt) {
//            TopComponent top2 = comps.next();
//            if(top2 == top) {
//                if(previous == null) {
//                    //find the last one
//                    if(!comps.hasNext()) { //last == first, so return
//                        return;
//                    }
//
//                    while(comps.hasNext()) {
//                        previous = comps.next();
//                    }
//                }
//            //    previous.requestActive();
//            //    previous.requestFocus(true);
//                halt = true;
//            }
//        }
    }

    public void speakLocation() {
        String componentName = "";
        String windowName = "";
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        this.event.object = component;
        this.event.readType = ReadType.FOCUS;
        ComponentScreenReader reader = readers.instance(event);
        SpeechProcessor read = reader.getRead();
        componentName = read.process();

        // Supplement "gained focus" with "has focus."
        if (componentName.contains("gained focus")) {
            componentName = componentName.substring(0, componentName.lastIndexOf("gained focus"));
        }
        
        // Read the line number in the editor, if applicable.
        if (read instanceof EditorProcessor) {
            EditorProcessor e = (EditorProcessor)read;
            int line = e.getLineNumber();
            
            if (e.isMultiLine() && line >= 0)
                componentName = "Line " + (line + 1) + " in " + componentName;
        }
        // TODO: Editor stuff
        
        Window window = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        
        if(window.getAccessibleContext() != null && window.getAccessibleContext().getAccessibleName() != null &&
                !window.getAccessibleContext().getAccessibleName().isEmpty()) {
            windowName = window.getAccessibleContext().getAccessibleName();
        } else if(window.getAccessibleContext() != null && window.getAccessibleContext().getAccessibleDescription() != null &&
                !window.getAccessibleContext().getAccessibleDescription().isEmpty()) {
            windowName = window.getAccessibleContext().getAccessibleDescription();
        } else if (window instanceof Frame) {
            Frame f = (Frame)window;
            
            if (f.getTitle() != null && !f.getTitle().isEmpty())
                windowName = f.getTitle();
        }
        
        String readString = componentName;
        if (!windowName.isEmpty())
            readString += ", in window " + windowName + ", has focus.";
        
        speech.speak(readString, SpeechPriority.HIGHEST);
    }
}
