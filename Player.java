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

    // References
    private Brain brain = new AggressiveBrain(); // Default to AggressiveBrain for Week 1 MVP
    private Vision vision;

    /**
     * Default constructor for Week 1 MVP.
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
     * Constructor with starting coordinates (Week 1 MVP).
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
     * Constructor for Week 3 integration.
     */
    public Player(int startX, int startY, Brain brain, Vision vision) {
        Log.methodStart("Player", "Constructor", "Week 3 Full");
        this.x = startX;
        this.y = startY;

        this.food = 10;
        this.water = 10;
        this.strength = 10;
        this.gold = 0;

        this.brain = brain;
        this.vision = vision;
        Log.methodEnd("Player", "Constructor", "void");
    }

    /**
     * Generates the next move for the player (Week 1 MVP).
     */
    public Move makeMove() { 
        Log.methodStart("Player", "makeMove", "none");
        Log.info("TEST MOVE");
        
        Move move = new Move("EAST");
        
        Log.methodEnd("Player", "makeMove", "Move(" + move.getDirection() + ")");
        return move;
    }

    // Player Turn Logic (We will uncomment this after merging William's AI branch)
    // public void nextTurn(Map map) {
    //     Path path = brain.makeMove();

    //     if (path != null) {
    //         move(path); 
    //     }
    // }

    /**
     * Moves the player based on a single Move object.
     */
    public void move(Move m) {
        Log.methodStart("Player", "move", "Move(" + m.getDirection() + ")");
        String dir = m.getDirection();

        switch (dir) {
            case "N": y++; break;
            case "S": y--; break;
            case "E": 
            case "EAST": 
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
     */
    public void move(Path path) {
        Log.methodStart("Player", "move", "Path");
        for (Move m : path.getMoves()) {
            
            int newX = x;
            int newY = y;
            
            switch (m.getDirection()) {
                case "N": newY++; break;
                case "S": newY--; break;
                case "E": newX++; break;
                case "W": newX--; break;
                case "NE": newY++; newX++; break;
                case "NW": newY++; newX--; break;
                case "SE": newY--; newX++; break;
                case "SW": newY--; newX--; break;
            }

            // Update player position 
            x = newX;
            y = newY;
        }
        Log.methodEnd("Player", "move", "void");
    }

    /**
     * Terrain will call this method to apply cost of moving.
     * Merged local and incoming logic to prevent negative stats.
     */
    public void reduceStats(int foodCost, int waterCost, int strengthCost) {
        this.food -= foodCost;
        this.water -= waterCost;
        this.strength -= strengthCost;

        if (food < 0) { food = 0; }
        if (water < 0) { water = 0; }
        if (strength < 0) { strength = 0; }

        Log.info("Player stats reduced by - Food: " + foodCost + ", Water: " + waterCost + ", Strength: " + strengthCost);
    }

    public void collectItem(Item item){
        item.collect(this);
    }

    public void addFood(int amount) {
        this.food += amount;
    }

    public void addWater(int amount) {
        this.water += amount;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    /**
     * Checks if the player is still alive based on stats.
     */
    public boolean isAlive() {
        Log.methodStart("Player", "isAlive", "none");
        boolean alive = food > 0 && water > 0 && strength > 0;
        Log.methodEnd("Player", "isAlive", String.valueOf(alive));
        return alive;
    }

    /**
     * Checks if the player has reached the eastern edge of the map.
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
    public Brain getBrain() { return brain; }

    /**
     * Logs the current status of the player.
     */
    public void printStatus() {
        Log.methodStart("Player", "printStatus", "none");
        Log.info("Player Position: (" + x + ", " + y + ")");
        Log.info("Food: " + food + " Water: " + water + " Strength: " + strength);
        Log.methodEnd("Player", "printStatus", "void");
    }
}