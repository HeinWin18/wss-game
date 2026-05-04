<<<<<<< HEAD
/**
 * Represents the 2D grid world of the game.
 */
=======
package wss;

import java.util.Random;

>>>>>>> 533d2bfe47ca99a47efb9bac30f81544ea2b821f
public class Map {
    private int rows;
    private int cols;
    private int difficulty;
    private Square[][] grid;

    /**
     * Constructs the Map and populates it with Squares.
     * Inputs: rows (int), cols (int), difficulty (int)
     * Outputs: Map object
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

<<<<<<< HEAD
    /**
     * Gets the number of rows.
     * Inputs: None
     * Outputs: rows (int)
     */
    public int getRows() {
        Log.methodStart("Map", "getRows", "none");
        Log.methodEnd("Map", "getRows", String.valueOf(rows));
        return rows;
    }

    /**
     * Gets the number of columns.
     * Inputs: None
     * Outputs: cols (int)
     */
    public int getCols() {
        Log.methodStart("Map", "getCols", "none");
        Log.methodEnd("Map", "getCols", String.valueOf(cols));
        return cols;
    }

    /**
     * Retrieves the Square object at the requested coordinates.
     * Inputs: x (int), y (int)
     * Outputs: Square object
     */
    public Square getSquare(int x, int y) {
        Log.methodStart("Map", "getSquare", x + ", " + y);
        Square sq = grid[x][y];
        Log.methodEnd("Map", "getSquare", "Square(" + sq.getX() + ", " + sq.getY() + ")");
        return sq;
    }

    /**
     * Populates the map with terrain, items, and traders.
     * Inputs: None
     * Outputs: None
     */
    public void populate() {
        Log.methodStart("Map", "populate", "none");
        
        Log.info("Map populate placeholder called. (Waiting on Map team for real item generation)");
        // Chris will put his real generation logic here later!
        
        Log.methodEnd("Map", "populate", "void");
    }
=======
	// Prevents an ArrayIndexOutOfBoundsException when Vision looks
	// past the edge of the map. Returns null instead of crashing.
	public Square getSquare(int x, int y) {
		if (x < 0 || x >= cols || y < 0 || y >= rows) {
			return null;
		}
		return squares[x][y];
	}

	// Picks a random Terrain based on difficulty level
	private Terrain getRandomTerrain(Random rand) {
		int roll = rand.nextInt(100) + 1;

		// Easy
		if (difficulty == 1) {
			if (roll <= 50)
				return new Plains();
			if (roll <= 75)
				return new Forest();
			if (roll <= 85)
				return new Desert();
			if (roll <= 95)
				return new Swamp();
			return new Mountain();

			// Medium
		} else if (difficulty == 2) {
			if (roll <= 30)
				return new Plains();
			if (roll <= 55)
				return new Forest();
			if (roll <= 75)
				return new Desert();
			if (roll <= 90)
				return new Swamp();
			return new Mountain();

			// Hard
		} else {
			if (roll <= 10)
				return new Plains();
			if (roll <= 30)
				return new Forest();
			if (roll <= 55)
				return new Desert();
			if (roll <= 80)
				return new Swamp();
			return new Mountain();
		}
	}

	// Picks a random Item, or returns null (70% chance of no item)
	private Item getRandomItem(Random rand) {
		int roll = rand.nextInt(100) + 1;

		if (roll <= 70)
			return null;

		if (roll <= 85)
			return new FoodBonus();

		if (roll <= 95)
			return new WaterBonus();

		return new GoldBonus();
	}

	// Fills the grid with Square objects, assigns Terrain and Items randomly.
	// The starting square (x = 0, y = rows/2) is always Plains with no item.
	public void populate() {
		Random rand = new Random();
		int startY = rows / 2;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				squares[i][j] = new Square(i, j);

				if (i == 0 && j == startY) {
					// Starting square - safe Plains, no item
					squares[i][j].setTerrain(new Plains());
				} else {
					squares[i][j].setTerrain(getRandomTerrain(rand));
					squares[i][j].setItem(getRandomItem(rand));
				}
			}
		}
	}
>>>>>>> 533d2bfe47ca99a47efb9bac30f81544ea2b821f
}