package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.uikit.UIAccessibilityContainer;
import org.robovm.apple.uikit.UIAccessibilityTraits;
import quorum.Libraries.Interface.Controls.ProgressBar_;
import quorum.Libraries.Interface.Item_;

/*
    A progress bar reads its progress as a percentage, which VoiceOver
    checks again as it changes.
 */
public class ProgressBarIOS extends ItemIOS {
    public ProgressBarIOS(UIAccessibilityContainer container) {
        super(container);
    }

    @Override
    public void Initialize(Item_ item) {
        setAccessibilityTraits(UIAccessibilityTraits.UpdatesFrequently);
        super.Initialize(item);
    }

    @Override
    public String getAccessibilityValue() {
        ProgressBar_ bar = (ProgressBar_) GetItem();
        double range = bar.GetMaximum() - bar.GetMinimum();
        if (range <= 0) {
            return null;
        }
        long percent = Math.round(100 * (bar.GetValue() - bar.GetMinimum()) / range);
        return percent + " percent";
    }
}
