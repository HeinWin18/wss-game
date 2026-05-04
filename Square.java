package wss;

public class Square {

	private Terrain terrain;
	private Item item;
	private Trader trader;
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

	public Trader getTrader() {
		return trader;
	}

	// Returns true if this square has an item
	public boolean hasItem() {
		return item != null;
	}

	// Returns true if this square has a trader
	public boolean hasTrader() {
		return trader != null;
	}

	// Setter methods - used by Map.populate()
	public void setTerrain(Terrain t) {
		this.terrain = t;
	}

	public void setItem(Item i) {
		this.item = i;
	}

	public void setTrader(Trader t) {
		this.trader = t;
	}

}