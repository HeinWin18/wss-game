package wss;

public class CautiousVision extends Vision {

	// Constructor
	// CautiousVision can see three directions: North, South, and East
	public CautiousVision(Map map, int x, int y) {
		super(map, x, y);
		visibleDirections = new String[] { "N", "S", "E" };
	}

}