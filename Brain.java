// Abstract base class for the player's AI Brain.
// Determines the direction the player will move each turn.*/
// NOTE:
    /* I have yet to implement the other vision classes, as I need to allow the user to select
    / which vision they would want. Therefore, I am only using the default Vision class, as of now. */
public abstract class Brain {
    protected Player player;
    protected Vision vision;

    public Brain() {}
     
// Determines the best move for the current turn.
// @return A Move object containing the chosen direction.*/
public abstract Move makeMove();
}

// AggressiveBrain prioritizes moving East to reach the goal quickly.
// It only diverts to look for resources when stats become critically low.*/
class AggressiveBrain extends Brain {

    public AggressiveBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("AggressiveBrain", "makeMove", "none");

        Path direction;

        boolean foodCritical = player.getFood() <= 2;
        boolean waterCritical = player.getWater() <= 2;
        boolean goldCritical = player.getGold() <= 2;

        int criticalCount = 0;
        if (foodCritical) { criticalCount++; }
        if (waterCritical) { criticalCount++; }
        if (goldCritical) {criticalCount++; }

        // If resources are critically low, rely on vision to find supplies
                // whichever resource is first critically low, Brain will find the closest resource
                // if both or more resources are critically low, ask user which resource they would
                // want to prioritize.
        if (criticalCount > 1)
        {
            Log.info("Multiple Resources Critical");
            if (foodCritical)
            {
                Log.info("Food critically low (<= 2)");
            }
            if (waterCritical)
            {
                Log.info("Water critically low (<= 2)");
            }
            if (goldCritical)
            {
                Log.info("Gold critically low (<= 2)");
            }

            // later implement which resource to prioritize
            // for now, just pick default priority
            if (foodCritical)
            {
                direction = vision.closestFood();
            } else if (waterCritical)
            {
                direction = vision.closestWater();
            } else if (goldCritical)
            {
                direction = vision.closestGold();
            }
        } else if (foodCritical)
        {
            Log.info("Food critically low (<= 2)");
            direction = vision.closestFood();
        } else if (waterCritical)
        {
            Log.info("Water critically low (<= 2)");
            direction = vision.closestWater();
        } else if (goldCritical)
        {
            Log.info("Gold critically low (<= 2)");
            direction = vision.closestGold();   
        } else
        {
            /* pseuedo, for now */
            direction = path.EAST;
        }
        Log.methodEnd("AggressiveBrain", "makeMove", "Move");
        /* Direction has to be changed or converted to Path class in order to return new Move */
        return new Move(direction);
    }
}


// BalancedBrain relies entirely on the Vision class to evaluate
// the current needs and map surroundings before every single move.*/
class BalancedBrain extends Brain {

    public BalancedBrain() {
        super();
    }

    @Override
    public Move makeMove() {
        Log.methodStart("BalancedBrain", "makeMove", "none");

        Log.info("Evaluating needs through Vision to determine best balanced move.");

        Path direction;

        boolean foodCritical = player.getFood() <= 5;
        boolean waterCritical = player.getWater() <= 5;
        boolean goldCritical = player.getGold() <= 5;

        int criticalCount = 0;
        if (foodCritical) { criticalCount++; }
        if (waterCritical) { criticalCount++; }
        if (goldCritical) {criticalCount++; }

        if (criticalCount > 1)
        {
            Log.info("Multiple Resources Critical");
            if (foodCritical)
            {
                Log.info("Food critically low (<= 5)");
            }
            if (waterCritical)
            {
                Log.info("Water critically low (<= 5)");
            }
            if (goldCritical)
            {
                Log.info("Gold critically low (<= 5)");
            }

            // later implement which resource to prioritize
            // for now, just pick default priority
            if (foodCritical)
            {
                direction = vision.closestFood();
            } else if (waterCritical)
            {
                direction = vision.closestWater();
            } else if (goldCritical)
            {
                direction = vision.closestGold();
            }
        } else if (foodCritical)
        {
            Log.info("Food critically low (<= 5)");
            direction = vision.closestFood();
        } else if (waterCritical)
        {
            Log.info("Water critically low (<= 5)");
            direction = vision.closestWater();
        } else if (goldCritical)
        {
            Log.info("Gold critically low (<= 5)");
            direction = vision.closestGold();   
        }else
        {
            /* pseuedo, for now */
            direction = Path.EAST;
        }
        Log.methodEnd("BalancedBrain", "makeMove", "Move");
        /* direction needs to be converted to Path */
        return new Move(direction);
    }
}