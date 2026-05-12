/**
 * CautiousVision can see three directions: North, South, and East.
 */
public class CautiousVision extends Vision {

    public CautiousVision(Map map, int x, int y) {
        super(map, x, y);
        Log.methodStart("CautiousVision", "Constructor", "Map, " + x + ", " + y);
        this.visibleDirections = new String[] { "N", "S", "E", "NE", "SE" };
        Log.methodEnd("CautiousVision", "Constructor", "void");
    }
}