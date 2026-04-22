package wss;

public class FocusedVision extends Vision {

	// Constructor
	// FocusedVision can only see one direction: East
	public FocusedVision(Map map, int x, int y) {
		super(map, x, y);
		visibleDirections = new String[] { "E" };
	}

}