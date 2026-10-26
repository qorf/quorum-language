package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.accessibility.AXChart;
import org.robovm.apple.accessibility.AXChartDescriptor;
import org.robovm.apple.uikit.UIAccessibilityContainer;
import quorum.Libraries.Interface.Controls.Charts.ChartItem_;
import quorum.Libraries.Interface.Controls.Charts.Chart_;

/*
    The first element of a chart: its title, then its summary. It also gives
    VoiceOver the chart's data (AXChart, iOS 15), which adds "Audio Graph" to
    the rotor: the data played as tones, and a data explorer.
 */
public class ChartSummaryIOS extends ItemIOS implements AXChart {
    private AXChartDescriptor descriptor = null;

    public ChartSummaryIOS(UIAccessibilityContainer container) {
        super(container);
    }

    Chart_ GetChart() {
        return (Chart_) GetItem();
    }

    @Override
    public void RefreshText() {
        String title = GetChart().GetTitle();
        if (title == null || title.isEmpty()) {
            title = GetChart().GetName();
        }
        setAccessibilityLabel(title);
        setAccessibilityValue(GetChart().GetDisplayName());
        setAccessibilityHint(null);
        descriptor = null;
    }

    /*
        Double tap: into the chart, at its top level (the chart area, axes and
        legend), when VoiceOver isn't in it already.
     */
    @Override
    public boolean activate() {
        Chart_ chart = GetChart();
        ChartItem_ selected = chart.GetSelection() == null ? null : chart.GetSelection().GetChartItem();
        if ((selected == null || selected == chart) && chart.GetChartArea() != null) {
            chart.Select(chart.GetChartArea());
            return true;
        }
        return false;
    }

    // Made when VoiceOver first asks, and again after the text changes.
    @Override
    public AXChartDescriptor getAccessibilityChartDescriptor() {
        if (descriptor == null) {
            descriptor = ChartDescriptors.Describe(GetChart());
        }
        return descriptor;
    }

    @Override
    public void setAccessibilityChartDescriptor(AXChartDescriptor v) {
        descriptor = v;
    }
}
