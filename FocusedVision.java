/**
 * FocusedVision can only see one direction: East.
 */
public class FocusedVision extends Vision {

    public FocusedVision(Map map, int x, int y) {
        super(map, x, y);
        Log.methodStart("FocusedVision", "Constructor", "Map, " + x + ", " + y);
        this.visibleDirections = new String[] { "E" };
        Log.methodEnd("FocusedVision", "Constructor", "void");
    }
}