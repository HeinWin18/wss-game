package wss;

public class KeenEyedVision extends Vision {

	// Constructor
	// KeenEyedVision can see five directions: North, South, East, NorthEast, and
	// SouthEast
	public KeenEyedVision(Map map, int x, int y) {
		super(map, x, y);
		visibleDirections = new String[] { "N", "S", "E", "NE", "SE" };
	}

}