/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.accessibility.magnification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.accessibility.options.MagnificationOptions;
import org.magnify.MagnifierFactory;
import org.magnify.MagnifierInterface;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Accessibility",
id = "org.magnification.ToggleFullScreen")
@ActionRegistration(displayName = "#CTL_ToggleFullScreen")
@ActionReferences({
    @ActionReference(path = "Menu/Tools/Accessibility", position = 25),
    @ActionReference(path = "Shortcuts", name = "O-BACK_QUOTE")
})
@Messages("CTL_ToggleFullScreen=Toggle Full Screen Mode")
public final class ToggleFullScreen implements ActionListener {
    private MagnifierInterface magnifier = MagnifierFactory.getDefaultMagnifier();
    
    public void actionPerformed(ActionEvent e) {
        // Toggle full screen mode.
        if (magnifier.isStarted()) {
            boolean isFullScreen = magnifier.isFullScreen();
            magnifier.setFullScreen(!isFullScreen);
            
            // Update settings.
            MagnificationOptions.setFullScreenEnabled(!isFullScreen);
        }
    }
}
