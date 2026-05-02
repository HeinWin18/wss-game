<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sequence of moves and the accumulated costs to travel them.
 */
public class Path {

    private List<Move> moves;
=======
public class Path {

    private List<Move> moves;

>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
    private int totalCost;
    private int waterCost;
    private int foodCost;

<<<<<<< HEAD
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
=======
    public Path() {
        moves = new ArrayList<>();
        totalCost = 0;
        waterCost = 0;
        foodCost = 0;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public List<Move> getMoves() {
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
        return moves;
    }

    // Getters
    public int getTotalCost() {
<<<<<<< HEAD
        Log.methodStart("Path", "getTotalCost", "none");
        Log.methodEnd("Path", "getTotalCost", String.valueOf(totalCost));
=======
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
        return totalCost;
    }

    public int getWaterCost() {
<<<<<<< HEAD
        Log.methodStart("Path", "getWaterCost", "none");
        Log.methodEnd("Path", "getWaterCost", String.valueOf(waterCost));
=======
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
        return waterCost;
    }

    public int getFoodCost() {
<<<<<<< HEAD
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
=======
        return foodCost;
    }

    // Console Output
    public void printPath() {
        for (Move m : moves) {
            System.out.print(m.getDirection() + " ");
        }
        System.out.println();
>>>>>>> 0c17719b876b6b06d578a090a4eef566862aecfd
    }
}