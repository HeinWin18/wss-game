import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sequence of moves and the accumulated costs to travel them.
 */
public class Path {

    private List<Move> moves;
    private int totalCost;
    private int waterCost;
    private int foodCost;

    /**
     * Basic constructor.
     * Inputs: None
     * Outputs: Path object
     */
    public Path() {
        Log.methodStart("Path", "Constructor", "none");
        this.moves = new ArrayList<>();
        this.totalCost = 0;
        this.waterCost = 0;
        this.foodCost = 0;
        Log.methodEnd("Path", "Constructor", "void");
    }

    /**
     * Adds a move to the path.
     * Inputs: move (Move)
     * Outputs: None
     */
    public void addMove(Move move) {
        Log.methodStart("Path", "addMove", "Move(" + move.getDirection() + ")");
        this.moves.add(move);
        Log.methodEnd("Path", "addMove", "void");
    }

    /**
     * Returns the sequence of moves.
     * Inputs: None
     * Outputs: List of Move objects
     */
    public List<Move> getMoves() {
        Log.methodStart("Path", "getMoves", "none");
        Log.methodEnd("Path", "getMoves", "List<Move> size: " + moves.size());
        return moves;
    }

    // Getters
    public int getTotalCost() {
        Log.methodStart("Path", "getTotalCost", "none");
        Log.methodEnd("Path", "getTotalCost", String.valueOf(totalCost));
        return totalCost;
    }

    public int getWaterCost() {
        Log.methodStart("Path", "getWaterCost", "none");
        Log.methodEnd("Path", "getWaterCost", String.valueOf(waterCost));
        return waterCost;
    }

    public int getFoodCost() {
        Log.methodStart("Path", "getFoodCost", "none");
        Log.methodEnd("Path", "getFoodCost", String.valueOf(foodCost));
        return foodCost;
    }

    /**
     * Logs the sequence of moves in the path.
     * Inputs: None
     * Outputs: None
     */
    public void printPath() {
        Log.methodStart("Path", "printPath", "none");
        
        StringBuilder pathString = new StringBuilder("Path Moves: ");
        for (Move m : moves) {
            pathString.append(m.getDirection()).append(" ");
        }
        Log.info(pathString.toString().trim());
        
        Log.methodEnd("Path", "printPath", "void");
    }
}