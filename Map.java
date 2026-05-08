import java.util.Random;

/**
 * Represents the 2D grid world of the game.
 */
public class Map {
    private int rows;
    private int cols;
    private int difficulty;
    private Square[][] grid;

    /**
     * Constructs the Map and populates it with Squares.
     */
    public Map(int rows, int cols, int difficulty) {
        Log.methodStart("Map", "Constructor", rows + ", " + cols + ", " + difficulty);
        
        if (rows <= 0 || cols <= 0) {
            Log.error("Invalid map size created.");
            throw new IllegalArgumentException("Invalid map size");
        }

        this.rows = rows;
        this.cols = cols;
        this.difficulty = difficulty;

        grid = new Square[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Square(i, j);
            }
        }
        Log.methodEnd("Map", "Constructor", "void");
    }

    public int getRows() {
        Log.methodStart("Map", "getRows", "none");
        Log.methodEnd("Map", "getRows", String.valueOf(rows));
        return rows;
    }

    public int getCols() {
        Log.methodStart("Map", "getCols", "none");
        Log.methodEnd("Map", "getCols", String.valueOf(cols));
        return cols;
    }

    /**
     * Retrieves the Square object at the requested coordinates.
     * Includes Chris's bounds-checking to prevent crashes.
     */
    public Square getSquare(int x, int y) {
        Log.methodStart("Map", "getSquare", x + ", " + y);
        
        // Bounds check to prevent ArrayIndexOutOfBoundsException
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            Log.methodEnd("Map", "getSquare", "null (Out of bounds)");
            return null;
        }
        
        Square sq = grid[x][y];
        Log.methodEnd("Map", "getSquare", "Square(" + sq.getX() + ", " + sq.getY() + ")");
        return sq;
    }

    // Picks a random Terrain based on difficulty level
    private Terrain getRandomTerrain(Random rand) {
        int roll = rand.nextInt(100) + 1;

        if (difficulty == 1) { // Easy
            if (roll <= 50) return new Plains();
            if (roll <= 75) return new Forest();
            if (roll <= 85) return new Desert();
            if (roll <= 95) return new Swamp();
            return new Mountain();
        } else if (difficulty == 2) { // Medium
            if (roll <= 30) return new Plains();
            if (roll <= 55) return new Forest();
            if (roll <= 75) return new Desert();
            if (roll <= 90) return new Swamp();
            return new Mountain();
        } else { // Hard
            if (roll <= 10) return new Plains();
            if (roll <= 30) return new Forest();
            if (roll <= 55) return new Desert();
            if (roll <= 80) return new Swamp();
            return new Mountain();
        }
    }

    // Picks a random Item, or returns null (35% chance of no item).
    // 50/50 chance of FoodBonus or WaterBonus if roll is between 36-95.
    // GoldBonus if roll is above 95.
    private Item getRandomItem(Random rand) {
        int roll = rand.nextInt(100) + 1;

        if (roll <= 35) return null;

        if (roll <= 95) {
            // 50/50 chance of FoodBonus or WaterBonus
            if (rand.nextInt(2) == 0) return new FoodBonus(5);
            else return new WaterBonus(5);
        }

        return new GoldBonus(5);
    }

    // Randomly places a Trader on a square based on difficulty.
    // Easy: 20% chance, Medium: 15% chance, Hard: 10% chance.
    // Trader type split by difficulty:
    // Easy: 70% PatientTrader, 30% ImpatientTrader
    // Medium: 50% PatientTrader, 50% ImpatientTrader
    // Hard: 40% PatientTrader, 60% ImpatientTrader
    private Trader getRandomTrader(Random rand) {
        int spawnRoll = rand.nextInt(100) + 1;
        int typeRoll = rand.nextInt(100) + 1;

        // Easy
        if (difficulty == 1 && spawnRoll <= 20) {
            if (typeRoll <= 70) return new PatientTrader();
            else return new ImpatientTrader();
        }

        // Medium
        if (difficulty == 2 && spawnRoll <= 15) {
            if (typeRoll <= 50) return new PatientTrader();
            else return new ImpatientTrader();
        }

        // Hard
        if (difficulty == 3 && spawnRoll <= 10) {
            if (typeRoll <= 40) return new PatientTrader();
            else return new ImpatientTrader();
        }

        return null;
    }

    /**
     * Populates the map with terrain, items, and traders.
     */
    public void populate() {
        Log.methodStart("Map", "populate", "none");
        
        Random rand = new Random();
        int startY = cols / 2;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
                // Starting square - safe Plains, no item or trader
                if (i == 0 && j == startY) {
                    grid[i][j].setTerrain(new Plains());
                } else {
                    grid[i][j].setTerrain(getRandomTerrain(rand));
                    grid[i][j].setItem(getRandomItem(rand));
                    grid[i][j].setTrader(getRandomTrader(rand));
                }
            }
        }
        
        Log.methodEnd("Map", "populate", "void");
    }
}