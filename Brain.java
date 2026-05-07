/**
 * Abstract base class for the player's AI Brain.
 */
public abstract class Brain {
    protected Player player;
    protected Vision vision;

    public Brain() {}

    // ADDED: Setters to safely connect these after the objects are created
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setVision(Vision vision) {
        this.vision = vision;
    }

    /**
     * Determines the best move for the current turn.
     * @return A Move object containing the chosen direction.
     */
    public abstract Move makeMove();
}

/**
 * AggressiveBrain prioritizes moving East.
 */
class AggressiveBrain extends Brain {

    public AggressiveBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("AggressiveBrain", "makeMove", "none");
        String direction = "EAST"; // Safe default

        // ADDED: Null checks to prevent crashes if player or vision aren't fully wired yet
        if (player != null && (player.getFood() <= 2 || player.getWater() <= 2 || player.getStrength() <= 2)) {
            Log.info("Resources critical (<= 2). Asking Vision for alternative direction.");
            if (vision != null) {
                direction = vision.evaluateNeeds(); 
            }
        } else {
            Log.info("Resources stable. Aggressively pushing East.");
            direction = "EAST";
        }

        Log.methodEnd("AggressiveBrain", "makeMove", "Move(" + direction + ")");
        return new Move(direction);
    }
}

/**
 * BalancedBrain relies entirely on the Vision class.
 */
class BalancedBrain extends Brain {

    public BalancedBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("BalancedBrain", "makeMove", "none");
        String direction = "EAST"; // Safe default

        Log.info("Evaluating needs through Vision to determine best balanced move.");
        if (vision != null) {
            direction = vision.evaluateNeeds();
        }
        
        Log.methodEnd("BalancedBrain", "makeMove", "Move(" + direction + ")");
        return new Move(direction);
    }
}