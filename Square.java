/**
 * Represents a single graphical/logical unit on the Map.
 */
public class Square {

    // private Terrain terrain;
    // private Item item;
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

    // Stubbed methods - to be implemented later

    
     // --- Paste this at the bottom of your Square class ---

    public Terrain getTerrain() { 
        return new Terrain(); // Safe dummy terrain
    }
    
    public Item getItem() { 
        return null; // No items exist yet
    }
    
    public boolean hasItem() { 
        return false; // Tells Vision there are no items to see yet
    }
    
    public boolean hasTrader() { 
        return false; // Tells Vision there are no traders yet
    }
    
    // We will leave Trader commented out until you actually need it
    // public Trader getTrader() { return trader; }
     

}