package wss;

public class Map {

    private int rows;
    private int cols;
    private int difficulty;
    private Square[][] grid;

    public Map(int rows, int cols, int difficulty) {
        if (rows <= 0 || cols <= 0) {
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
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Square getSquare(int x, int y) {
        return grid[x][y];
    }
}