/**
 * Represents the player navigating the map.
 */
public class Player {
    public int x = 0;
    public int y = 0;

    /**
     * Generates the next move for the player.
     * Inputs: None
     * Outputs: Move object
     */
    public Move makeMove() { 
        Log.methodStart("Player", "makeMove", "none");
        Log.info("TEST MOVE");
        
        Move move = new Move("EAST");
        
        Log.methodEnd("Player", "makeMove", "Move(" + move.getDirection() + ")");
        return move;
    }
}