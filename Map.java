package wss;

import java.util.Random;

public class Map {

	private int rows;
	private int cols;
	private int difficulty;
	private Square[][] squares;

	// Constructor - size depends on difficulty:
	// Easy (1): 10x10, Medium (2): 20x20, Hard (3): 30x30
	public Map(int difficulty) {
		this.difficulty = difficulty;

		if (difficulty == 1) {
			this.rows = 10;
			this.cols = 10;
		} else if (difficulty == 2) {
			this.rows = 20;
			this.cols = 20;
		} else if (difficulty == 3) {
			this.rows = 30;
			this.cols = 30;
		} else {
			this.rows = 20;
			this.cols = 20;
		}

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
		} else if (difficulty == 3) {
			if (roll <= 10)
				return new Plains();
			if (roll <= 30)
				return new Forest();
			if (roll <= 55)
				return new Desert();
			if (roll <= 80)
				return new Swamp();
			return new Mountain();

			// Invalid difficulty
		} else {
			return null;
		}
	}

	// Picks a random Item, or returns null (35% chance of no item).
	// 50/50 chance of FoodBonus or WaterBonus if roll is between 36-95.
	// GoldBonus if roll is above 95.
	private Item getRandomItem(Random rand) {
		int roll = rand.nextInt(100) + 1;

		if (roll <= 35)
			return null;

		if (roll <= 95) {
			// 50/50 chance of FoodBonus or WaterBonus
			if (rand.nextInt(2) == 0)
				return new FoodBonus();
			else
				return new WaterBonus();
		}

		return new GoldBonus();
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
			if (typeRoll <= 70)
				return new PatientTrader();
			else
				return new ImpatientTrader();
		}

		// Medium
		if (difficulty == 2 && spawnRoll <= 15) {
			if (typeRoll <= 50)
				return new PatientTrader();
			else
				return new ImpatientTrader();
		}

		// Hard
		if (difficulty == 3 && spawnRoll <= 10) {
			if (typeRoll <= 40)
				return new PatientTrader();
			else
				return new ImpatientTrader();
		}

		return null;
	}

	// Fills the grid with Square objects, assigns Terrain, Items, and Traders
	// randomly.
	// The starting square (x = 0, y = rows/2) is always Plains with no item or
	// trader.
	public void populate() {
		Random rand = new Random();
		int startY = rows / 2;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				squares[i][j] = new Square(i, j);

				if (i == 0 && j == startY) {
					// Starting square - safe Plains, no item or trader
					squares[i][j].setTerrain(new Plains());
				} else {
					squares[i][j].setTerrain(getRandomTerrain(rand));
					squares[i][j].setItem(getRandomItem(rand));
					squares[i][j].setTrader(getRandomTrader(rand));
				}
			}
		}
	}
}