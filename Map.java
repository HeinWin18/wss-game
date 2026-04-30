package wss;

import java.util.Random;

public class Map {

	private int rows;
	private int cols;
	private int difficulty;
	private Square[][] squares;

	// Constructor (20x20 size matrix for now)
	public Map(int difficulty) {
		this.rows = 20;
		this.cols = 20;
		this.difficulty = difficulty;
		this.squares = new Square[rows][cols];
	}

	// Getter methods
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

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
}