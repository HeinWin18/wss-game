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

<<<<<<< HEAD
    /**
     * Default constructor for Week 1 MVP.
     * Inputs: None
     * Outputs: Player object
     */
    public Player() {
        Log.methodStart("Player", "Constructor", "none");
=======
    // References
    private Brain brain;
    private Vision vision;

    // Constructor
    public Player(int startX, int startY, Brain brain, Vision vision) {
        this.x = startX;
        this.y = startY;

        // Temp values (changes with difficulty?)
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
        this.food = 10;
        this.water = 10;
        this.strength = 10;

<<<<<<< HEAD
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
=======
        this.brain = brain;
        this.vision = vision;
    }

    // Player Turn Logic
    public void nextTurn(Map map) {
        Path path = brain.makeMove();

        if (path != null) {
            move(path, map);
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
        }

        //this.food -= 1;
        //this.water -= 1;
        //this.strength -= 1;
        
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
            
            int newX = x;
            int newY = y;
            
            switch (m.getDirection()) {
                case "N":
                    newY++;
                    break;
                case "S":
                    newY--;
                    break;
                case "E":
                    newX++;
                    break;
                case "W":
                    newX--;
                    break;
                case "NE":
                    newY++;
                    newX++;
                    break;
                case "NW":
                    newY++;
                    newX--;
                    break;
                case "SE":
                    newY--;
                    newX++;
                    break;
                case "SW":
                    newY--;
                    newX--;
                    break;
            }

            // Check map bounds. Ignore move if player can't travel there, or cancel entire path? 
            if (newX < 0 || newY < 0 || newX >= map.getCols() || newY >= map.getRows()) {
                continue; 
            }

            // Update player position 
            x = newX;
            y = newY;

            // Update player stats? Or will terrain apply costs? 
                // food--; 
        }
        Log.methodEnd("Player", "move", "void");
    }

    public void reduceStats(int foodCost, int waterCost, int strengthCost) {
        this.food -= foodCost;
        this.water -= waterCost;
        this.strength -= strengthCost;
        Log.info("Player stats reduced by - Food: " + foodCost + ", Water: " + waterCost + ", Strength: " + strengthCost);
    }

    public void collectItem(Item item){
        // Logic will go here after merging Aldo's branch
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

    // Player Stats
    // Terrain will call this method to apply cost of moving
    public void reduceStats(int foodCost, int waterCost, int strengthCost) {
        this.food -= foodCost;
        this.water -= waterCost;
        this.strength -= strengthCost;

        if (food < 0) {
            food = 0;
        }

        if (water < 0) {
            water = 0;
        }
        
        if (strength < 0) {
            strength = 0;
        }
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

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getFood() { return food; }
    public int getWater() { return water; }
    public int getStrength() { return strength; }

    private Brain brain = new AggressiveBrain();
    public Brain getBrain() { return brain; }

<<<<<<< HEAD
    /**
     * Logs the current status of the player.
     * Inputs: None
     * Outputs: None
     */
=======
    // Console output 
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
    public void printStatus() {
        Log.methodStart("Player", "printStatus", "none");
        Log.info("Player Position: (" + x + ", " + y + ")");
        Log.info("Food: " + food + " Water: " + water + " Strength: " + strength);
        Log.methodEnd("Player", "printStatus", "void");
    }
}