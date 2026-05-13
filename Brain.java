import java.util.Scanner;

// Abstract base class for the player's AI Brain.
// Determines the path the player will move each turn.*/
// NOTE:
    /* I have yet to implement the other vision classes, as I need to allow the user to select
    / which vision they would want. Therefore, I am only using the default Vision class, as of now. */
public abstract class Brain {
    protected Player player;
    protected Vision vision;

    public Brain() {}

    protected Move getFirstMove(Path path) {
        if (path == null || path.getMoves().isEmpty()) {
            return new Move("EAST");
        }
        return path.getMoves().get(0);
    }
     
    // Determines the best move for the current turn.
    // @return A Move object containing the chosen path.*/
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

        Path path = null;
        Map map = player.getMap(); // NOTE: assuming this is how we access the map from the player, may need to adjust based on actual implementation

        // setting CautiousVision as default, since it is the most comprehensive and will allow the player to find resources when in critical condition.
        Vision vision = new CautiousVision(map, player.getX(), player.getY());

        boolean foodCritical = player.getFood() <= 2;
        boolean waterCritical = player.getWater() <= 2;
        boolean strengthCritical = player.getStrength() <= 2;

        int criticalCount = 0;
        if (foodCritical) { criticalCount++; }
        if (waterCritical) { criticalCount++; }
        if (strengthCritical) {criticalCount++; }

        // If resources are critically low, rely on vision to find supplies
                // whichever resource is first critically low, Brain will find the closest resource
                // if both or more resources are critically low, ask user which resource they would
                // want to prioritize.
        if (criticalCount > 1)
        {
            Log.info("Multiple Resources Critical");
            Scanner scanner = new Scanner(System.in);
            Log.question("Multiple resources are critically low, choose vision:\n1.) Cautious Vision\n2.) Focused Vision\n3.) Far Sight Vision\n4.) Keen Eyed Vision");
            int choice = scanner.nextInt();

            switch (choice)
            {
                case 2:
                    Log.info("Focused Vision selected.");
                    vision = new FocusedVision(map, player.getX(), player.getY());
                    break;
                case 3:
                    Log.info("Far Sight Vision selected.");
                    vision = new FarSightVision(map, player.getX(), player.getY());
                    break;
                case 4:
                    Log.info("Keen Eyed Vision selected.");
                    vision = new KeenEyedVision(map, player.getX(), player.getY());
                    break;
                default:
                    Log.info("Using default vision.");
                    vision = new CautiousVision(map, player.getX(), player.getY());
            }

            if (foodCritical)
            {
                Log.info("Food critically low (<= 2)");
            }
            if (waterCritical)
            {
                Log.info("Water critically low (<= 2)");
            }
            if (strengthCritical)
            {
                Log.info("Gold critically low (<= 2)");
            }

            // later implement which resource to prioritize
            // for now, just pick default priority
            if (foodCritical)
            {
                path = vision.closestFood();
            } else if (waterCritical)
            {
                path = vision.closestWater();
            } else if (strengthCritical)
            {
                path = vision.closestGold();
            }
        } else if (foodCritical)
        {
            Log.info("Food critically low (<= 2)");
            path = vision.closestFood();
        } else if (waterCritical)
        {
            Log.info("Water critically low (<= 2)");
            path = vision.closestWater();
        } else if (strengthCritical)
        {
            Log.info("Gold critically low (<= 2)");
            path = vision.closestGold();   
        } else {
            path = null; // fallback to east if no resource path is found
        }
        Log.methodEnd("AggressiveBrain", "makeMove", "Move");
        return getFirstMove(path);
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

        Path path = null;

        Map map = player.getMap(); // NOTE: assuming this is how we access the map from the player, may need to adjust based on actual implementation

        // setting CautiousVision as default, since it is the most comprehensive and will allow the player to find resources when in critical condition.
        Vision vision = new CautiousVision(map, player.getX(), player.getY());


        boolean foodCritical = player.getFood() <= 5;
        boolean waterCritical = player.getWater() <= 5;
        boolean strengthCritical = player.getStrength() <= 5;

        int criticalCount = 0;
        if (foodCritical) { criticalCount++; }
        if (waterCritical) { criticalCount++; }
        if (strengthCritical) {criticalCount++; }

        if (criticalCount > 1)
        {
            Scanner scanner = new Scanner(System.in);
            Log.question("Multiple resources are critically low, choose vision:\n1.) Cautious Vision\n2.) Focused Vision\n3.) Far Sight Vision\n4.) Keen Eyed Vision");
            int choice = scanner.nextInt();

            switch (choice)
            {
                case 2:
                    Log.info("Focused Vision selected.");
                    vision = new FocusedVision(map, player.getX(), player.getY());
                    break;
                case 3:
                    Log.info("Far Sight Vision selected.");
                    vision = new FarSightVision(map, player.getX(), player.getY());
                    break;
                case 4:
                    Log.info("Keen Eyed Vision selected.");
                    vision = new KeenEyedVision(map, player.getX(), player.getY());
                    break;
                default:
                    Log.info("Using default vision.");
                    vision = new CautiousVision(map, player.getX(), player.getY());
            }

            Log.info("Multiple Resources Critical");
            if (foodCritical)
            {
                Log.info("Food critically low (<= 5)");
            }
            if (waterCritical)
            {
                Log.info("Water critically low (<= 5)");
            }
            if (strengthCritical)
            {
                Log.info("Gold critically low (<= 5)");
            }

            // later implement which resource to prioritize
            // for now, just pick default priority
            if (foodCritical)
            {
                path = vision.closestFood();
            } else if (waterCritical)
            {
                path = vision.closestWater();
            } else if (strengthCritical)
            {
                path = vision.closestGold();
            }
        } else if (foodCritical)
        {
            Log.info("Food critically low (<= 5)");
            path = vision.closestFood();
        } else if (waterCritical)
        {
            Log.info("Water critically low (<= 5)");
            path = vision.closestWater();
        } else if (strengthCritical)
        {
            Log.info("Gold critically low (<= 5)");
            path = vision.closestGold();   
        } else {
            path = null; // fallback to east if no better path is found
        }
        Log.methodEnd("BalancedBrain", "makeMove", "Move");
        return getFirstMove(path);
    }
}