/**
 * Abstract base class for the player's AI Brain.
 */
public abstract class Brain {
    protected Player player;
    protected Vision vision;

    public Brain() {}

    // Kept your setters so GameManager doesn't break
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setVision(Vision vision) {
        this.vision = vision;
    }

    // William's new method to extract the first move from a Path
    protected Move getFirstMove(Path path) {
        if (path == null || path.getMoves().isEmpty()) {
            return new Move("EAST");
        }
        return path.getMoves().get(0);
    }
     
    public abstract Move makeMove();

    public Offer makeOffer(Trader trader) {
        return new Offer(0, 0, 0, 1, 0, 0); // Default implementation, can be overridden by specific brains
    }
}

/**
 * AggressiveBrain prioritizes moving East, diverts when low on stats.
 */
class AggressiveBrain extends Brain {

    public AggressiveBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("AggressiveBrain", "makeMove", "none");

        Path path = null;
        
        // Null check to prevent crashes
        if (player == null || player.getMap() == null) {
            return new Move("EAST");
        }

        Map map = player.getMap(); 

        // Automated default to CautiousVision (Removed the Scanner that freezes the game)
        Vision currentVision = new CautiousVision(map, player.getX(), player.getY());

        boolean foodCritical = player.getFood() <= 2;
        boolean waterCritical = player.getWater() <= 2;
        boolean strengthCritical = player.getStrength() <= 2;

        if (foodCritical || waterCritical || strengthCritical) {
            Log.info("Resources critical. Relying on Vision.");
            
            if (foodCritical) {
                Log.info("Food critically low (<= 2)");
                path = currentVision.closestFood();
            } else if (waterCritical) {
                Log.info("Water critically low (<= 2)");
                path = currentVision.closestWater();
            } else if (strengthCritical) {
                Log.info("Strength/Gold critically low (<= 2)");
                path = currentVision.closestGold();   
            }
        } else {
            path = null; // fallback to east if no resource path is found
        }
        
        Log.methodEnd("AggressiveBrain", "makeMove", "Move");
        return getFirstMove(path);
    }
}

/**
 * BalancedBrain evaluates needs much earlier (<= 5).
 */
class BalancedBrain extends Brain {

    public BalancedBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("BalancedBrain", "makeMove", "none");
        Log.info("Evaluating needs through Vision to determine best balanced move.");

        Path path = null;
        
        // Null check
        if (player == null || player.getMap() == null) {
            return new Move("EAST");
        }

        Map map = player.getMap(); 

        // Automated default to CautiousVision (Removed the Scanner)
        Vision currentVision = new CautiousVision(map, player.getX(), player.getY());

        boolean foodCritical = player.getFood() <= 5;
        boolean waterCritical = player.getWater() <= 5;
        boolean strengthCritical = player.getStrength() <= 5;

        if (foodCritical || waterCritical || strengthCritical) {
            if (foodCritical) {
                Log.info("Food low (<= 5)");
                path = currentVision.closestFood();
            } else if (waterCritical) {
                Log.info("Water low (<= 5)");
                path = currentVision.closestWater();
            } else if (strengthCritical) {
                Log.info("Strength/Gold low (<= 5)");
                path = currentVision.closestGold();   
            }
        } else {
            path = null; // fallback to east if no better path is found
        }
        
        Log.methodEnd("BalancedBrain", "makeMove", "Move");
        return getFirstMove(path);
    }
}