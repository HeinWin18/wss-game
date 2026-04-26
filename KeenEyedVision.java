/**
 * KeenEyedVision can see five directions: North, South, East, NorthEast, and SouthEast.
 */
public class KeenEyedVision extends Vision {

    public KeenEyedVision(Map map, int x, int y) {
        super(map, x, y);
        Log.methodStart("KeenEyedVision", "Constructor", "Map, " + x + ", " + y);
        this.visibleDirections = new String[] { "N", "S", "E", "NE", "SE" };
        Log.methodEnd("KeenEyedVision", "Constructor", "void");
    }
}