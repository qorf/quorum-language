/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.reading.readers;

import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.Field;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.nodes.Node;
import org.accessibility.AccessibleNode;
import org.accessibility.reading.processors.NullProcessor;
import org.accessibility.reading.processors.TreeProcessor;
import org.sodbeans.phonemic.SpeechProcessor;

/**
 * Reads out text for JTree objects.
 * 
 * @author Andreas Stefik
 * @author Andrew Hauck
 */
public class JTreeReader extends AbstractScreenReader {

    private JTree tree;

    public void setObject(Object object) {
        tree = null;
        tree = (JTree) object;
    }

    @Override
    protected SpeechProcessor getKeyEventProcessor() {
        int keyCode = getUberEvent().key.getKeyCode();
        
        // We only want to respond to up, down, left and right keys.
        if (keyCode != KeyEvent.VK_UP && keyCode != KeyEvent.VK_DOWN &&
                keyCode != KeyEvent.VK_LEFT && keyCode != KeyEvent.VK_RIGHT)
            return new NullProcessor();
        
        // If there's no tree, we can't do anything either.
        if (tree == null)
            return new NullProcessor();
        
        TreeProcessor proc = new TreeProcessor();
        proc.setKeyEvent(true);
        proc.setKeyCode(keyCode);
        proc.setTree(tree);

        return proc;
    }

    @Override
    protected SpeechProcessor getFocusEventProcessor() {
        if (tree == null)
            return new NullProcessor();
        TreeProcessor proc = new TreeProcessor();
        proc.setText("n"); // TODO: phonemic bug needs fixing
        proc.setTree(tree);

        return proc;
    }


}
