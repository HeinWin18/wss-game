public class Player {

    // Position
    private int x;
    private int y;

    // Stats
    private int food;
    private int water;
    private int strength;
    private int gold; 

    // Constructor
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        // Temp values (changes with difficulty?)
        this.food = 10;
        this.water = 10;
        this.strength = 10;
    }

    // Single move 
    public void move(Move m) {
        String dir = m.getDirection();

        switch (dir) {
            case "N":
                y++;
                break;
            case "S":
                y--;
                break;
            case "E":
                x++;
                break;
            case "W":
                x--;
                break;
            case "NE":
                y++;
                x++;
                break;
            case "NW":
                y++;
                x--;
                break;
            case "SE":
                y--;
                x++;
                break;
            case "SW":
                y--;
                x--;
                break;
        }
    }

    // Path move
    public void move(Path path) {
        for (Move m : path.getMoves()) {
            move(m);
        }
    }

    // Player State
    public boolean isAlive() {
        return food > 0 && water > 0 && strength > 0; // Only food and water or include strength too? 
    }

    public boolean hasReachedEast(Map map) {
        return x == map.getCols() - 1;
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

    // Temp Console Output (Game Manager will handle this later?)
    public void printStatus() {
        System.out.println("Player Position: (" + x + ", " + y + ")");
        System.out.println("Food: " + food + " Water: " + water + " Strength: " + strength);
    }
}