/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.quorum.debugger;

import javax.swing.JComponent;
import org.netbeans.spi.debugger.ui.BreakpointType;

/**
 *
 * @author Andreas Stefik
 */
public class QuorumLineBreakpointType extends BreakpointType {

    @Override
    public String getCategoryDisplayName() {
        return "Quorum";
    }

    @Override
    public JComponent getCustomizer() {
        return null;
    }

    @Override
    public String getTypeDisplayName() {
        return "QuorumSourceLine";
      }

    @Override
    public boolean isDefault() {
        return true;
    }

}
