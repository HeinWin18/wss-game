package wss;

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

	public Square getSquare(int x, int y) {
		return squares[x][y];
	}

	// Fills the grid with Square objects
	public void populate(int difficulty) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				squares[i][j] = new Square(i, j);
			}
		}
	}
}
