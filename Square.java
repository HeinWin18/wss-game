package wss;

public class Square {

	private Terrain terrain;
	private Item item;
	// private Trader trader;
	private int x;
	private int y;

	// Constructor
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Getter methods
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public Item getItem() {
		return item;
	}

	// Returns true if this square has an item
	public boolean hasItem() {
		return item != null;
	}

	// Setter methods - used by Map.populate()
	public void setTerrain(Terrain t) {
		this.terrain = t;
	}

	public void setItem(Item i) {
		this.item = i;
	}

	// Stubbed - to be implemented when Trader is ready
	// public Trader getTrader() { return trader; }
	// public boolean hasTrader() { return false; }

}