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

    // Extract the first move from a Path, or fallback to the easiest visible path.
    protected Move getFirstMove(Path path, Vision vision) {
        if (path != null && !path.getMoves().isEmpty()) {
            return path.getMoves().get(0);
        }

        if (vision != null) {
            Path fallback = vision.easiestPath();
            if (fallback != null && !fallback.getMoves().isEmpty()) {
                return fallback.getMoves().get(0);
            }
        }

        // If no path is found, rest instead of defaulting to East.
        return new Move("REST");
    }
     
    public abstract Move makeMove();

    public Offer makeOffer(Trader trader) {
        if (player == null) {
            return new Offer(0, 0, 0, 0, 0, 0);
        }

        int food = player.getFood();
        int water = player.getWater();
        int strength = player.getStrength();
        int gold = player.getGold();

        int goldOffer = 0;
        int waterOffer = 0;
        int foodOffer = 0;
        int goldRequest = 0;
        int waterRequest = 0;
        int foodRequest = 0;

        // If the player is low on food or water, prioritize acquiring the scarcest resource.
        if (food <= 15 || water <= 15) {
            if (food < water) {
                foodRequest = 1;
            } else {
                waterRequest = 1;
            }

            if (gold >= 1) {
                goldOffer = 1;
            } else if (food > 20) {
                foodOffer = 1;
            } else if (water > 20) {
                waterOffer = 1;
            }
        }
        // Otherwise, if gold is low, trade surplus food or water for gold.
        else if (gold <= 3) {
            goldRequest = 1;
            if (food > water && food > 18) {
                foodOffer = 2;
            } else if (water > 18) {
                waterOffer = 2;
            } else if (food > 12) {
                foodOffer = 1;
            } else if (water > 12) {
                waterOffer = 1;
            }
        }
        // If strength is low but food and water are stable, don't trade away the other resources.
        else if (strength <= 10 && food >= 15 && water >= 15) {
            // Prefer resting / gathering rather than risky trades.
            return new Offer(0, 0, 0, 0, 0, 0);
        }
        // If everything is healthy, skip trading unless there is a clear surplus.
        else {
            return new Offer(0, 0, 0, 0, 0, 0);
        }

        Offer candidate = new Offer(goldOffer, waterOffer, foodOffer, goldRequest, waterRequest, foodRequest);
        if (candidate.isAcceptable()) {
            return candidate;
        }

        // If the first offer wasn't fair, fall back to a balanced trade with value parity.
        if (goldRequest > 0) {
            if (food > water && food >= 3) {
                return new Offer(0, 0, 3, 1, 0, 0);
            } else if (water >= 3) {
                return new Offer(0, 3, 0, 1, 0, 0);
            }
        }

        if (foodRequest > 0 && gold >= 1) {
            return new Offer(1, 0, 0, 0, 0, 1);
        }
        if (waterRequest > 0 && gold >= 1) {
            return new Offer(1, 0, 0, 0, 1, 0);
        }

        return new Offer(0, 0, 0, 0, 0, 0);
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
            return new Move("REST");
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
            path = null; // no resource-based path found
        }
        
        Log.methodEnd("AggressiveBrain", "makeMove", "Move");
        return getFirstMove(path, currentVision);
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
            return new Move("REST");
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
            path = null; // no resource-based path found
        }
        
        Log.methodEnd("BalancedBrain", "makeMove", "Move");
        return getFirstMove(path, currentVision);
    }
}