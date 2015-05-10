/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading;

import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;
import org.accessibility.reading.readers.*;

/**
 * This class takes an object and checks to see if it can be read by a screen
 * reader. If it can, it attempts to connect to that object and do something
 * sensible with it. This particular factory only deals with visual components.
 * Exactly which components it can read and understand is determined by
 * its enum ReaderType, which is public.
 * 
 * @author Andreas Stefik
 */
public class ScreenReaderFactory {

    private HashMap<ReaderType, ComponentScreenReader> readers;

    public enum ReaderType{
        DEFAULT (new DefaultReader()), 
        EDITOR (new EditorReader()),
        JTREE (new JTreeReader()),
        JBUTTON (new JButtonReader()),
        JLIST (new JListReader()),
        JCOMBO_BOX (new JComboBoxReader()),
        JRADIO_BUTTON (new JRadioButtonReader()),
        JMENU_ITEM (new JMenuItemReader()),
        JCHECK_BOX (new JCheckBoxReader()),
        JTABBED_PANE (new JTabbedPaneReader()),
        JLABEL (new JLabelReader()),
        JTOGGLE_BUTTON (new JToggleButtonReader()),
        JPANEL (new JPanelReader()),
        JTABLE (new JTableReader()),
        JSLIDER (new JSliderReader()),
        TOP_COMPONENT(new TopComponentReader()),
        ;

        private final ComponentScreenReader reader;
        ReaderType(ComponentScreenReader reader) {
            this.reader = reader;
        }
    }

    public ScreenReaderFactory() {
        readers = new HashMap<ReaderType, ComponentScreenReader>();
        init();
    }

    private void init() {
        for(ReaderType reader: ReaderType.values()) {
            readers.put(reader, reader.reader);
        }
    }

    public ComponentScreenReader instance(UberEvent event) {
        ComponentScreenReader reader = readers.get(ReaderType.DEFAULT);
        Object object = event.object;

        if (object instanceof JTextComponent) {
            reader = readers.get(ReaderType.EDITOR);
        }
        else if (object instanceof JTree) {
            reader = readers.get(ReaderType.JTREE);
        }
        else if (object instanceof JButton) {
            reader = readers.get(ReaderType.JBUTTON);
        }
        else if (object instanceof JList) {
            reader = readers.get(ReaderType.JLIST);
        }
        else if (object instanceof JComboBox) {
            reader = readers.get(ReaderType.JCOMBO_BOX);
        }
        else if (object instanceof JRadioButton) {
            reader = readers.get(ReaderType.JRADIO_BUTTON);
        }
        else if (object instanceof JMenuItem) {
            reader = readers.get(ReaderType.JMENU_ITEM);
        }
        else if (object instanceof JCheckBox) {
            reader = readers.get(ReaderType.JCHECK_BOX);
        }
        else if(object instanceof JTabbedPane) {
            reader = readers.get(ReaderType.JTABBED_PANE);
        }
        else if(object instanceof JLabel) {
            reader = readers.get(ReaderType.JLABEL);
        }
        else if (object instanceof JPanel) {
            reader = readers.get(ReaderType.JPANEL);
        }
        else if(object instanceof JToggleButton) {
            reader = readers.get(ReaderType.JTOGGLE_BUTTON);
        }
        else if(object instanceof JTable){
            reader = readers.get(ReaderType.JTABLE);
        }
        else if(object instanceof JScrollPane){
            reader = readers.get(ReaderType.JTABBED_PANE);
        }
        else if(object instanceof JSlider) {
            reader = readers.get(ReaderType.JSLIDER);
        }
        else {
            int a = 0;//if it isn't read, should we log it?
        }
        reader.setUberEvent(event);
        return reader;
    }
}
