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
id = "org.magnification.IncreaseZoom")
@ActionRegistration(displayName = "#CTL_IncreaseZoom")
@ActionReferences({
    @ActionReference(path = "Menu/Tools/Magnification", position = 200, separatorBefore = 150),
    @ActionReference(path = "Shortcuts", name = "D-BACK_QUOTE")
})
@Messages("CTL_IncreaseZoom=Increase Zoom")
public final class IncreaseZoom implements ActionListener {
    private MagnifierInterface magnifier = MagnifierFactory.getDefaultMagnifier();
    public void actionPerformed(ActionEvent e) {
        if(magnifier.isStarted()) {
            float zoom = magnifier.getZoom();
            float newZoom = zoom * MagnificationProperties.ZOOM_INCREASE;
            magnifier.setZoom(newZoom);
            
            // Update settings.
            MagnificationOptions.setZoomLevel(newZoom);
        }
    }
}
