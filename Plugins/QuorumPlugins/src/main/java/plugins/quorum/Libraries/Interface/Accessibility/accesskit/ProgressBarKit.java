package plugins.quorum.Libraries.Interface.Accessibility.accesskit;

import dev.accesskit.Node;
import dev.accesskit.NodeBuilder;
import dev.accesskit.Rect;
import dev.accesskit.Role;
import quorum.Libraries.Interface.Controls.ProgressBar_;
import quorum.Libraries.Interface.Item_;

public class ProgressBarKit extends ItemKit{
    public ProgressBarKit() {
        SetRole(Role.PROGRESS_INDICATOR);
    }

    public Node Build() {
        Item_ item = GetItem();
        if(item != null) {
            Rect rect = GetBoundingRectangle();
            NodeBuilder builder = new NodeBuilder(GetRole());
            builder.setBounds(rect);
            builder.setName(item.GetName());
            if(item instanceof ProgressBar_) { //technically not compiler guaranteed. You can set the code to anything.
                ProgressBar_ bar = (ProgressBar_) item;
                double max = bar.GetMaximum();
                double min = bar.GetMinimum();
                double value = bar.GetValue();
                builder.setMaxNumericValue(max);
                builder.setMinNumericValue(min);
                builder.setNumericValue(value);
            }
            return builder.build();
        }
        return null;
    }
}
