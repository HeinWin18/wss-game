public class Player {

    // Position
    private int x;
    private int y;

    // Stats
    private int food;
    private int water;
    private int strength;
    private int gold; 

    // References
    private Brain brain;
    private Vision vision;

    // Constructor
    public Player(int startX, int startY, Brain brain, Vision vision) {
        this.x = startX;
        this.y = startY;

        // Temp values (changes with difficulty?)
        this.food = 10;
        this.water = 10;
        this.strength = 10;

        this.brain = brain;
        this.vision = vision;
    }

    // Player Turn Logic
    public void nextTurn(Map map) {
        Path path = brain.makeMove();

        if (path != null) {
            move(path, map);
        }
    }

    // Path move
    public void move(Path path) {
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
    }

    // Player State
    public boolean isAlive() {
        return food > 0 && water > 0 && strength > 0; // Only food and water or include strength too? 
    }

    public boolean hasReachedEast(Map map) {
        return x == map.getCols() - 1;
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
    public int getX() {
        return x; 
    }
    public int getY() {
        return y; 
    }

    public int getFood() {
        return food; 
    }
    public int getWater() {
        return water; 
    }
    public int getStrength() {
        return strength; 
    }

    // Console output 
    public void printStatus() {
        System.out.println("Player Position: (" + x + ", " + y + ")");
        System.out.println("Food: " + food + " Water: " + water + " Strength: " + strength);
    }
}