package plugins.quorum.Libraries.Interface.Accessibility.AccessKitBinding;

/* A rectangle by its edges: x0, y0 the top left, x1, y1 the bottom right. */
public final class Rect {
    public double x0;
    public double y0;
    public double x1;
    public double y1;

    public Rect(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }
}
