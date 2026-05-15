/**
 * Abstract base class for the player's AI Brain.
 */
public abstract class Brain {
    protected Player player;
    protected Vision vision;

    public Brain() {}

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setVision(Vision vision) {
        this.vision = vision;
    }

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

    // William's Trade Logic (Math Fixed!)
    public Offer makeOffer(Trader trader) {
        Log.methodStart("Brain", "makeOffer", "Trader");

        if (player == null) {
            Log.methodEnd("Brain", "makeOffer", "Offer");
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
        else if (gold <= 3) {
            goldRequest = 1;
            if (food > water && food > 18) {
                foodOffer = 3; // 3 food equals 1 gold
            } else if (water > 18) {
                waterOffer = 3; // 3 water equals 1 gold
            } else if (food > 12) {
                foodOffer = 3;
            } else if (water > 12) {
                waterOffer = 3;
            }
        }
        else if (strength <= 10 && food >= 15 && water >= 15) {
            Log.methodEnd("Brain", "makeOffer", "Offer");
            return new Offer(0, 0, 0, 0, 0, 0);
        }
        else {
            Log.methodEnd("Brain", "makeOffer", "Offer");
            return new Offer(0, 0, 0, 0, 0, 0);
        }

        Offer candidate = new Offer(goldOffer, waterOffer, foodOffer, goldRequest, waterRequest, foodRequest);
        if (candidate.isAcceptable()) {
            Log.methodEnd("Brain", "makeOffer", "Offer");
            return candidate;
        }

        if (goldRequest > 0) {
            if (food > water && food >= 3) {
                Log.methodEnd("Brain", "makeOffer", "Offer");
                return new Offer(0, 0, 3, 1, 0, 0);
            } else if (water >= 3) {
                Log.methodEnd("Brain", "makeOffer", "Offer");
                return new Offer(0, 3, 0, 1, 0, 0);
            }
        }

        if (foodRequest > 0 && gold >= 1) {
            Log.methodEnd("Brain", "makeOffer", "Offer");
            return new Offer(1, 0, 0, 0, 0, 3); // 1 gold for 3 food
        }
        if (waterRequest > 0 && gold >= 1) {
            Log.methodEnd("Brain", "makeOffer", "Offer");
            return new Offer(1, 0, 0, 0, 3, 0); // 1 gold for 3 water
        }

        Log.methodEnd("Brain", "makeOffer", "Offer");
        return new Offer(0, 0, 0, 0, 0, 0);
    }
}

/**
 * AggressiveBrain prioritizes moving East, only diverts when critically low on stats.
 */
class AggressiveBrain extends Brain {

    public AggressiveBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("AggressiveBrain", "makeMove", "none");

        Path path = null;

        if (player == null || player.getMap() == null) return new Move("REST");

        Map map = player.getMap();
        Vision currentVision = (this.vision != null) ? this.vision
                : new CautiousVision(map, player.getX(), player.getY());

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
                Log.info("Strength critically low (<= 2)");
                return new Move("REST");
            }
        } else {
            // No resource crisis — just push East
            Log.info("Resources stable. Moving East.");
            path = new Path();
            path.addMove(new Move("E"));
        }

        Log.methodEnd("AggressiveBrain", "makeMove", "Move");
        return getFirstMove(path, currentVision);
    }
}

/**
 * BalancedBrain reacts earlier (<= 5) and evaluates needs more carefully.
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

        if (player == null || player.getMap() == null) return new Move("REST");

        Map map = player.getMap();
        Vision currentVision = (this.vision != null) ? this.vision
                : new CautiousVision(map, player.getX(), player.getY());

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
                return new Move("REST");
            }
        } else {
            // No resource crisis — just push East
            Log.info("Resources stable. Moving East.");
            path = new Path();
            path.addMove(new Move("E"));
        }

        Log.methodEnd("BalancedBrain", "makeMove", "Move");
        return getFirstMove(path, currentVision);
    }
}
