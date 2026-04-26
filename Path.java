import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder Path class for Week 1 compilation.
 * Aldo and Parker will fully implement this later.
 */

public class Path {
    private List<Move> moves;

    /**
     * Basic constructor.
     * Inputs: None
     * Outputs: Path object
     */
    public Path(){
        Log.methodStart("Path", "Constructor", "none");
        this.moves = new ArrayList<>();
        Log.methodEnd("Path", "Constructor", "void");
    }

    /**
     * Returns the sequence of moves.
     * Inputs: None
     * Outputs: List of Move objects
     */
    public List<Move> getMoves() {
        Log.methodStart("Path", "getMoves", "none");
        Log.methodEnd("Path", "getMoves", "List of " + moves.size() + " moves");
        return moves;
    }
}