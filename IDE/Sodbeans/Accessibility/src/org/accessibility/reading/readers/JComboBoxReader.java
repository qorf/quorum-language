/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.accessibility.reading.readers;

import javax.accessibility.Accessible;
import javax.swing.JComboBox;

/**
 * Reads out text for JComboBox objects.
 * 
 * @author Andreas Stefik
 */import javax.swing.filechooser.FileFilter;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.SourceGroup;
import org.accessibility.reading.processors.ComboBoxProcessor;
import org.accessibility.reading.processors.NullProcessor;
import org.sodbeans.phonemic.SpeechProcessor;
import org.accessibility.options.AccessibilityOptions;




public class JComboBoxReader extends AbstractScreenReader{
    private JComboBox box;

    @Override
    @SuppressWarnings("empty-statement")
    protected SpeechProcessor getKeyEventProcessor() {
        if (box == null)
            return new NullProcessor();

        ComboBoxProcessor proc = new ComboBoxProcessor();
        Object o = box.getSelectedItem();
        String sayString = null;
        Accessible acc = null;

        proc.setKeyEvent(true);

        if(box.getAccessibleContext() != null &&
                box.getAccessibleContext().getAccessibleSelection() != null &&
                box.getAccessibleContext().getAccessibleSelection().getAccessibleSelection(0) != null)
            acc = box.getAccessibleContext().getAccessibleSelection().getAccessibleSelection(0);
        else if(o != null)
            sayString = o.toString();

        if(acc != null) {
             sayString = acc.getAccessibleContext().getAccessibleName();
        }
        else if(o instanceof FileFilter) {
            FileFilter file = (FileFilter) o;
            sayString = file.getDescription();

            //speakString = classReflection(o,"javax.swing.filechooser.FileFilter","getDescription");
        }
        //Our combobox is most likely a Win32ShellFolder, do some
        //Java reflection.
        else if(o instanceof sun.awt.shell.ShellFolder) {
            //preferable as it accounts
            sun.awt.shell.ShellFolder folder = (sun.awt.shell.ShellFolder)o;
            sayString = folder.getDisplayName();

            //just in case, hold on to this
            //speakString = classReflection(o,"sun.awt.shell.Win32ShellFolder2","getDisplayName");
        }
        else if(o instanceof SourceGroup) {
            SourceGroup group = (SourceGroup)o;
            sayString = group.getDisplayName();
        }
        else if(o instanceof Project) {
            Project proj = (Project) o;
            ProjectInformation info = proj.getLookup().lookup(ProjectInformation.class);
            sayString = info.getDisplayName();
        }

        if(sayString != null) {//null values for separators?
             proc.setText(sayString);
        }
        else {
            proc.setSeparator(true);
        }

        return proc;
    }

    @Override
    //Changed this function so it supports Win32ShellFolder2
    @SuppressWarnings("empty-statement")
    protected String getKeyEventString() {
       
        if(box != null ) {
            Object o = box.getSelectedItem();
            String sayString = null;
            Accessible acc = null;

            
            if(box.getAccessibleContext() != null &&
                    box.getAccessibleContext().getAccessibleSelection() != null &&
                    box.getAccessibleContext().getAccessibleSelection().getAccessibleSelection(0) != null)
                acc = box.getAccessibleContext().getAccessibleSelection().getAccessibleSelection(0);
            else if(o != null)
                sayString = o.toString();

            if(acc != null) {
                 sayString = acc.getAccessibleContext().getAccessibleName();
            }
            else if(o instanceof FileFilter) {
                FileFilter file = (FileFilter) o;
                sayString = file.getDescription();
                
                //speakString = classReflection(o,"javax.swing.filechooser.FileFilter","getDescription");
            }
            //Our combobox is most likely a Win32ShellFolder, do some
            //Java reflection.
            else if(o instanceof sun.awt.shell.ShellFolder) {
                //preferable as it accounts
                sun.awt.shell.ShellFolder folder = (sun.awt.shell.ShellFolder)o;
                sayString = folder.getDisplayName();
                
                //just in case, hold on to this
                //speakString = classReflection(o,"sun.awt.shell.Win32ShellFolder2","getDisplayName");
            }
            else if(o instanceof SourceGroup) {
                SourceGroup group = (SourceGroup)o;
                sayString = group.getDisplayName();
            }
            else if(o instanceof Project) {
                Project proj = (Project) o;
                ProjectInformation info = proj.getLookup().lookup(ProjectInformation.class);
                sayString = info.getDisplayName();
            }

            if(sayString != null) {//null values for separators?
                 return sayString;
            }
            else {
                 return "Separator: This item is a visual separator in the combo box. It is not a valid item.";
            }
          }
        return "";
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (box == null)
            return new NullProcessor();

        ComboBoxProcessor proc = new ComboBoxProcessor();
        proc.setKeyEvent(false);
        String accessibleDesc = null;
        String name = null;

        accessibleDesc = box.getAccessibleContext().getAccessibleDescription();
        name = box.getAccessibleContext().getAccessibleName();

        if(accessibleDesc != null)
            proc.setText(accessibleDesc);
        else if (name != null)
            proc.setText(name);
        else if(box.getName() != null)
            proc.setText(box.getName());

        return proc;
    }

    @Override
    protected String getFocusEventString() {

        String sayString = null;
        String name = null;

        if(box != null)
        {
         sayString = box.getAccessibleContext().getAccessibleDescription();
         name = box.getAccessibleContext().getAccessibleName();

          if(sayString != null && !sayString.equals("N/A"))
            if(AccessibilityOptions.isSelfVoicing()) {
                getTextToSpeech().speak(sayString);
            }
          else if (name != null)
              sayString = name + " combo box";
          else if(box.getName() != null) 
              sayString = box.getName();

            //add to it the currently selected item
            //Object o = box.getModel().getSelectedItem();
            //message += ", " + o + " is selected";
            
            return sayString;
        }
        return "";
    }
    
    public void setObject(Object object) {
        box = null;
        box = (JComboBox) object;
    }

}
