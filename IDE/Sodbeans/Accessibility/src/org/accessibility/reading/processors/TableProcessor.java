package org.accessibility.reading.processors;

import java.awt.image.BufferedImage;
import java.beans.FeatureDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.openide.nodes.PropertySupport;
import org.openide.util.Exceptions;
import org.sodbeans.phonemic.AbstractSpeechProcessor;
import org.accessibility.sound.SoundUtility;

/**
 * A processor for JTable objects.
 *
 * @author jeff
 */
public class TableProcessor extends AbstractSpeechProcessor {
    private SoundUtility snd = SoundUtility.instance();
    private JTable table;
    private Pattern pattern = Pattern.compile("([\\w]+)<html><[^<>]+>([^<>]+)</[^<>]+></html>");
    private boolean keyEvent = false;
    private boolean endOfTable = false;
    private int readColumns = 0;
    
    public String process() {
        text = "";
        Object o = null;

        if (getTable().getSelectedRow() > -1 && getTable().getSelectedColumn() > -1) {
            o = getTable().getValueAt(getTable().getSelectedRow(), getTable().getSelectedColumn());
        }

        if (o instanceof FeatureDescriptor && o != null) {
            FeatureDescriptor fd = (FeatureDescriptor) o;

            if (fd.getShortDescription() != null) {
                text = getTable().getColumnName(getTable().getSelectedColumn()) + fd.getShortDescription();

                if (text != null) {
                    Matcher matcher = pattern.matcher(text);

                    if (matcher.find()) {
                        text = matcher.group(1) + " " + matcher.group(2);
                    } else {
                        // So that there is at least some kind of representation.
                        Object obj = getTable().getValueAt(getTable().getSelectedRow(), getTable().getSelectedColumn());
                        
                        if (obj instanceof PropertySupport) {
                            PropertySupport prop = (PropertySupport) o;
                            String name = prop.getDisplayName();
                            try {
                                Object value = prop.getValue();
                                text = value + ", " + name;
                                readColumns = 1;
                            } catch (IllegalAccessException ex) {
                                Exceptions.printStackTrace(ex);
                            } catch (InvocationTargetException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        }
                        else
                        {
                            text = getTable().getColumnName(getTable().getSelectedColumn()) + ", " + obj.toString();
                            readColumns = 1;
                        }
                    }
                }
            }
        }
        else if (o != null) {
            //text = o.toString() + " " + getTable().getColumnName(getTable().getSelectedColumn()) + " selected.";
            for (int i = 0; i < getTable().getColumnCount(); i++) {
                readColumns++;
                o = getTable().getValueAt(getTable().getSelectedRow(), i);
                TableCellRenderer renderer = getTable().getCellRenderer(getTable().getSelectedRow(), i);
                
                if(o instanceof PropertySupport) {
                    PropertySupport prop = (PropertySupport) o;
                    String name = prop.getDisplayName();
                    try {
                        Object value = prop.getValue();
                        text += ", " + value + ", " + name;
                    } catch (IllegalAccessException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (InvocationTargetException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                else {
                    // Determine the text to speak based on the renderer.
                    
                    if (renderer instanceof JCheckBox && o instanceof Boolean) {
                        Boolean checked = (Boolean)o;
                        if (checked)
                            text = text + ", Checked checkbox";
                        else
                            text = text + ", Unchecked checkbox";
                        text += ", " + getTable().getColumnName(i);
                    } else if (getTable().getColumnName(i) != null && o != null) { // last resort option
                        // Fix for plugins window
                        if (o instanceof BufferedImage)
                            text = text + ", image, " + getTable().getColumnName(i);
                        else
                            text = text + ", " + o.toString() + ", " + getTable().getColumnName(i);
                    }
                }
            }
        }

        if (!isKeyEvent() && !text.isEmpty()) {
            String name = this.table.getAccessibleContext().getAccessibleName();
            String desc = this.table.getAccessibleContext().getAccessibleDescription();
            
            String tableNotice = "";
            if (desc != null && !desc.isEmpty() && !desc.equals("N/A"))
                tableNotice = desc + " " + tableNotice;
            
            if (name != null && !name.isEmpty() && !name.equals("N/A"))
                tableNotice = name + " " + tableNotice;
            
            if (readColumns == 1)
                text = text + ", in table: " + tableNotice;
            else
                text = tableNotice + " table. " + text;
        }
        
        if (isKeyEvent() && isEndOfTable()) {
            snd.playTopOfTable();
        }
        
        
        return text;
    }

    /**
     * @return the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(JTable table) {
        this.table = table;
    }

    /**
     * @return the keyEvent
     */
    public boolean isKeyEvent() {
        return keyEvent;
    }

    /**
     * @param keyEvent the keyEvent to set
     */
    public void setKeyEvent(boolean keyEvent) {
        this.keyEvent = keyEvent;
    }

    /**
     * @return the endOfTable
     */
    public boolean isEndOfTable() {
        return endOfTable;
    }

    /**
     * @param endOfTable the endOfTable to set
     */
    public void setEndOfTable(boolean endOfTable) {
        this.endOfTable = endOfTable;
    }
}