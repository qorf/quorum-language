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
id = "org.magnification.DecreaseZoom")
@ActionRegistration(displayName = "#CTL_DecreaseZoom")
@ActionReferences({
    @ActionReference(path = "Menu/Tools/Accessibility", position = 300),
    @ActionReference(path = "Shortcuts", name = "S-BACK_QUOTE")
})
@Messages("CTL_DecreaseZoom=Decrease Zoom")
public final class DecreaseZoom implements ActionListener {
    private MagnifierInterface magnifier = MagnifierFactory.getDefaultMagnifier();
    public void actionPerformed(ActionEvent e) {
        if(magnifier.isStarted()) {
            float zoom = magnifier.getZoom();
            float newZoom = zoom * (1 / MagnificationProperties.ZOOM_INCREASE);
            if(newZoom <= 1) {
                newZoom = 1;
            }
            magnifier.setZoom(newZoom);
            
            // Update settings.
            MagnificationOptions.setZoomLevel(newZoom);
        }
    }
}
