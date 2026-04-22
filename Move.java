/**
 * Represents a movement action taken by an entity.
 */
public class Move {
    private String direction;
    
    /**
     * Constructs a Move object with a target direction.
     * Inputs: direction (String)
     * Outputs: Move object
     */
    public Move(String direction) {
        Log.methodStart("Move", "Constructor", direction);
        this.direction = direction;
        Log.methodEnd("Move", "Constructor", "void");
    }
    
    /**
     * Retrieves the direction string.
     * Inputs: None
     * Outputs: direction (String)
     */
    public String getDirection() {
        Log.methodStart("Move", "getDirection", "none");
        Log.methodEnd("Move", "getDirection", direction);
        return direction;
    }
}