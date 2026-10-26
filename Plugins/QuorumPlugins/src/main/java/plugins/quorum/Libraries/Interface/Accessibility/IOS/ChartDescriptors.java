package plugins.quorum.Libraries.Interface.Accessibility.IOS;

import org.robovm.apple.accessibility.AXCategoricalDataAxisDescriptor;
import org.robovm.apple.accessibility.AXChartDescriptor;
import org.robovm.apple.accessibility.AXDataPoint;
import org.robovm.apple.accessibility.AXDataPointValue;
import org.robovm.apple.accessibility.AXDataSeriesDescriptor;
import org.robovm.apple.accessibility.AXNumericDataAxisDescriptor;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSNumber;
import org.robovm.apple.foundation.NSString;
import quorum.Libraries.Interface.Controls.Charts.ChartItem_;
import quorum.Libraries.Interface.Controls.Charts.Chart_;
import quorum.Libraries.Interface.Controls.Charts.Graphics.BarGroup_;
import quorum.Libraries.Interface.Controls.Charts.Graphics.Bar_;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    Reads a Quorum chart for VoiceOver: the order its parts are read in, and
    its data for Apple's Audio Graph.
 */
public class ChartDescriptors {
    /*
        The chart's parts in reading order, from the navigation graph the chart
        builds for keyboard users: starting at the chart area, each part, then
        (depth first) the part it enters, then its right neighbor. The top-level
        parts form a ring (chart area, axes, legend) and bars may too, so a part
        already visited ends that walk.
     */
    public static List<ChartItem_> GetReadingOrder(Chart_ chart) {
        List<ChartItem_> order = new ArrayList<>();
        ChartItem_ start = chart.GetChartArea();
        if (start == null) {
            start = chart.GetEnterItem();
        }
        Visit(start, order, new HashSet<ChartItem_>());
        return order;
    }

    /*
        The parts at the same level as this one, in order: the ring (or line)
        its left and right neighbors form, starting from its leftmost part. On a
        bar chart, the top level is the chart area, axes and legend; inside the
        chart area, the bars.
     */
    public static List<ChartItem_> GetRing(ChartItem_ part) {
        // Back to the start of a line, or once around a ring.
        ChartItem_ start = part;
        Set<ChartItem_> seen = new HashSet<>();
        seen.add(start);
        ChartItem_ left = start.GetLeftItem();
        while (left != null && !seen.contains(left)) {
            seen.add(left);
            start = left;
            left = left.GetLeftItem();
        }
        // In a ring the walk stops back at the part, so start there.
        if (left == part) {
            start = part;
        }

        List<ChartItem_> ring = new ArrayList<>();
        Set<ChartItem_> visited = new HashSet<>();
        ChartItem_ next = start;
        while (next != null && !visited.contains(next)) {
            visited.add(next);
            ring.add(next);
            next = next.GetRightItem();
        }
        return ring;
    }

    private static void Visit(ChartItem_ start, List<ChartItem_> order, Set<ChartItem_> visited) {
        ChartItem_ part = start;
        while (part != null && !visited.contains(part)) {
            visited.add(part);
            order.add(part);
            Visit(part.GetEnterItem(), order, visited);
            part = part.GetRightItem();
        }
    }

    /*
        The chart's data for VoiceOver's Audio Graph: for a bar chart, each bar's
        category (its group's name) and value, in reading order. Other kinds of
        chart have no Audio Graph yet (undefined).
     */
    public static AXChartDescriptor Describe(Chart_ chart) {
        List<String> categories = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for (ChartItem_ part : GetReadingOrder(chart)) {
            if (part instanceof Bar_) {
                Bar_ bar = (Bar_) part;
                categories.add(GetCategory(bar));
                values.add(bar.GetValue());
            }
        }
        if (categories.isEmpty()) {
            return null;
        }

        double low = 0;
        double high = 0;
        List<AXDataPoint> points = new ArrayList<>();
        List<NSString> categoryOrder = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            double value = values.get(i);
            low = Math.min(low, value);
            high = Math.max(high, value);
            categoryOrder.add(new NSString(categories.get(i)));
            points.add(new AXDataPoint(new AXDataPointValue(categories.get(i)), new AXDataPointValue(value)));
        }
        if (high == low) {
            high = low + 1;
        }

        String xTitle = Text(chart.GetXAxisTitle(), "Category");
        String yTitle = Text(chart.GetYAxisTitle(), "Value");
        AXCategoricalDataAxisDescriptor xAxis = new AXCategoricalDataAxisDescriptor(xTitle, new NSArray<NSString>(categoryOrder));
        AXNumericDataAxisDescriptor yAxis = new AXNumericDataAxisDescriptor(yTitle, low, high,
                new NSArray<NSNumber>(), (value) -> FormatNumber(value));
        AXDataSeriesDescriptor series = new AXDataSeriesDescriptor(yTitle, false, new NSArray<AXDataPoint>(points));

        String title = Text(chart.GetTitle(), chart.GetName());
        return new AXChartDescriptor(title, chart.GetDisplayName(), xAxis, yAxis, new NSArray<AXDataSeriesDescriptor>(series));
    }

    // A bar's category: its group's name, or its own name without " bar".
    private static String GetCategory(Bar_ bar) {
        BarGroup_ group = bar.GetBarGroup();
        if (group != null && group.GetName() != null && !group.GetName().isEmpty()) {
            return group.GetName();
        }
        String name = ChartItemIOS.Trim(bar.GetName());
        return name.endsWith(" bar") ? name.substring(0, name.length() - 4) : name;
    }

    private static String Text(String value, String fallback) {
        return value == null || value.isEmpty() ? fallback : value;
    }

    // Whole numbers without a decimal point, as the chart's own text shows them.
    static String FormatNumber(double value) {
        if (value == Math.rint(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(Math.round(value * 100) / 100.0);
    }
}
