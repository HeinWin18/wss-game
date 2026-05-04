/**
 * Represents a single graphical/logical unit on the Map.
 */
public class Square {

    private Terrain terrain;
    private Item item;
    // private Trader trader;
    private int x;
    private int y;

    /**
     * Constructs a Square at the given coordinates.
     * Inputs: x (int), y (int)
     * Outputs: Square object
     */
    public Square(int x, int y) {
        //Log.methodStart("Square", "Constructor", x + ", " + y);
        this.x = x;
        this.y = y;
        //Log.methodEnd("Square", "Constructor", "void");
    }

    /**
     * Gets the x-coordinate of the Square.
     * Inputs: None
     * Outputs: x (int)
     */
    public int getX() {
        Log.methodStart("Square", "getX", "none");
        Log.methodEnd("Square", "getX", String.valueOf(x));
        return x;
    }

    /**
     * Gets the y-coordinate of the Square.
     * Inputs: None
     * Outputs: y (int)
     */
    public int getY() {
        Log.methodStart("Square", "getY", "none");
        Log.methodEnd("Square", "getY", String.valueOf(y));
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

    // Removes the item from the square after the player collects it
    public void removeItem() {
        this.item = null;
    }

    // Setter methods - used by Map.populate()
    public void setTerrain(Terrain t) {
        this.terrain = t;
    }

    public void setItem(Item i) {
        this.item = i;
    }

    public boolean hasTrader() { 
        return false; // Tells Vision there are no traders yet
    }
    
    // Stubbed - to be implemented when Trader is ready
    // public Trader getTrader() { return trader; }
}