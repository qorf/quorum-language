package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding.*;
import quorum.Libraries.Interface.Controls.ColorWell_;
import quorum.Libraries.Interface.Item_;

/*
    A color well: a button showing a color, which opens a color picker when
    pressed (ClickableKit runs its Activate, which opens or closes the
    picker). Its role is a color well (AXColorWell), and its value is the
    color's words, such as "#4A5A6E, dim gray", so VoiceOver says the name
    and then the color.

    The well also keeps the color's words as its description, for platforms
    with no value; here that would repeat the value as help, so the
    description is left out while it is the same as the color's words.
*/
public class ColorWellKit extends ClickableKit {
    public ColorWellKit() {
        SetRole(Role.COLOR_WELL);
    }

    @Override
    public Node Build() {
        Item_ item = GetItem();
        if (item == null) {
            return null;
        }
        Node builder = new Node(GetRole());
        builder.setBounds(GetBoundingRectangle());
        builder.setLabel(item.GetName());
        String colorText = null;
        if (item instanceof ColorWell_) {
            colorText = ((ColorWell_) item).GetColorText();
            if (colorText != null && !colorText.isEmpty()) {
                builder.setValue(colorText);
            }
        }
        String description = item.GetDescription();
        if (description != null && !description.isEmpty() && !description.equals(colorText)) {
            builder.setDescription(description);
        }
        BuildChildren(builder);
        return builder;
    }
}
