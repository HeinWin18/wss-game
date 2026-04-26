/**
 * Represents the player navigating the map.
 */
public class Player {

    // Position (Kept public to not break Week 1 GameManager MVP)
    public int x = 0;
    public int y = 0;

    // Stats
    private int food;
    private int water;
    private int strength;
    private int gold; 

    /**
     * Default constructor for Week 1 MVP.
     * Inputs: None
     * Outputs: Player object
     */
    public Player() {
        Log.methodStart("Player", "Constructor", "none");
        this.food = 10;
        this.water = 10;
        this.strength = 10;

        this.brain.setPlayer(this);
        Log.methodEnd("Player", "Constructor", "void");
    }

    /**
     * Constructor with starting coordinates.
     * Inputs: startX (int), startY (int)
     * Outputs: Player object
     */
    public Player(int startX, int startY) {
        Log.methodStart("Player", "Constructor", startX + ", " + startY);
        this.x = startX;
        this.y = startY;
        this.food = 10;
        this.water = 10;
        this.strength = 10;
        Log.methodEnd("Player", "Constructor", "void");
    }

    /**
     * Generates the next move for the player (Week 1 MVP).
     * Inputs: None
     * Outputs: Move object
     */
    public Move makeMove() { 
        Log.methodStart("Player", "makeMove", "none");
        Log.info("TEST MOVE");
        
        Move move = new Move("EAST");
        
        Log.methodEnd("Player", "makeMove", "Move(" + move.getDirection() + ")");
        return move;
    }

    /**
     * Moves the player based on a single Move object.
     * Inputs: m (Move)
     * Outputs: None
     */
    public void move(Move m) {
        Log.methodStart("Player", "move", "Move(" + m.getDirection() + ")");
        String dir = m.getDirection();

        switch (dir) {
            case "N": y++; break;
            case "S": y--; break;
            case "E": 
            case "EAST": // Added to support Week 1 MVP logic
                x++; break;
            case "W": x--; break;
            case "NE": y++; x++; break;
            case "NW": y++; x--; break;
            case "SE": y--; x++; break;
            case "SW": y--; x--; break;
        }
        Log.methodEnd("Player", "move", "void");
    }

    /**
     * Moves the player along a Path.
     * Inputs: path (Path)
     * Outputs: None
     */
    public void move(Path path) {
        Log.methodStart("Player", "move", "Path");
        for (Move m : path.getMoves()) {
            move(m);
        }
        Log.methodEnd("Player", "move", "void");
    }

    /**
     * Checks if the player is still alive based on stats.
     * Inputs: None
     * Outputs: boolean
     */
    public boolean isAlive() {
        Log.methodStart("Player", "isAlive", "none");
        boolean alive = food > 0 && water > 0 && strength > 0;
        Log.methodEnd("Player", "isAlive", String.valueOf(alive));
        return alive;
    }

    /**
     * Checks if the player has reached the eastern edge of the map.
     * Inputs: map (Map)
     * Outputs: boolean
     */
    public boolean hasReachedEast(Map map) {
        Log.methodStart("Player", "hasReachedEast", "Map");
        boolean reached = x >= map.getCols() - 1;
        Log.methodEnd("Player", "hasReachedEast", String.valueOf(reached));
        return reached;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getFood() { return food; }
    public int getWater() { return water; }
    public int getStrength() { return strength; }
    // William
    public int getGold() { return gold; }

    private Brain brain = new AggressiveBrain();
    public Brain getBrain() { return brain; }

    /**
     * Logs the current status of the player.
     * Inputs: None
     * Outputs: None
     */
    public void printStatus() {
        Log.methodStart("Player", "printStatus", "none");
        Log.info("Player Position: (" + x + ", " + y + ")");
        Log.info("Food: " + food + " Water: " + water + " Strength: " + strength);
        Log.methodEnd("Player", "printStatus", "void");
    }
}