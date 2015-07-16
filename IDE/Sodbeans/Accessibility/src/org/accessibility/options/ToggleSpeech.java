/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Accessibility",
        id = "org.accessibility.options.ToggleSpeech"
)
@ActionRegistration(
        displayName = "#CTL_ToggleSpeech"
)
@ActionReferences({
    @ActionReference(path = "Menu/Tools/Accessibility", position = -200),
    @ActionReference(path = "Shortcuts", name = "SM-T")
})
@Messages("CTL_ToggleSpeech=Speech Toggle")
public final class ToggleSpeech implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        AccessibilityOptions.setSelfVoicing(!AccessibilityOptions.isSelfVoicing());
    }
}
