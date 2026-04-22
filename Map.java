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
}