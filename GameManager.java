import java.nio.channels.IllegalSelectorException;

/**
 * GameManager controls the main loop and integrates Player and Map.
 */
public class GameManager {
    private Player player;
    private Map map;

    /**
     * Initializes the game map and player.
     * Inputs: None
     * Outputs: None
     */
    public void initialize() {
        Log.methodStart("GameManager", "initialize", "none");
        
        map = new Map(20, 20 , 1);
        player = new Player();
        map.populate();

        // check for errors
        assert map != null : "Map should be initialized";
        assert player != null : "Player should be initialized";
        
        Log.methodEnd("GameManager", "initialize", "void");
    }

    /**
     * Runs the main game loop until the player wins or an error occurs.
     * Inputs: None
     * Outputs: None (prints to console)
     */
    public void run() {
        Log.methodStart("GameManager", "run", "none");
        while(true){
            try {
                // 1. Check lose Condtion
                if(!player.isAlive()) {
                    System.out.println("Player has lost the game.");
                    break;
                }

                // 2. Check win Condition
                if(player.hasReachedEast(map)){
                    System.out.println("Player has won the game!");
                    break;
                }

                //3. Brain selects the next move
                Move move = player.getBrain().makeMove();
                String direction = move.getDirection();

                // Check if the AI decided to rest
                if (direction.equals("REST")) {
                    player.rest();
                    System.out.println("Player skipped movement to rest.");
                    // Do NOT apply terrain damage since they didn't move!
                } 
                else {
                    // Normal movement logic goes here
                    player.move(move);
                    
                    // Process the new square, terrain damage, and items...
                }

                if(move == null) {
                    throw new IllegalStateException("Move is null");
                }

                Log.info("Move chosen: " + move.getDirection());

                //4. Execute move (Player class)
                player.move(move);

                //5. Week 3 Integration logic: Apply terrain costs, item pickups.
                // Get the exact square the player landed on
                Square currentSquare = map.getSquare(player.getX(), player.getY());

                // Apply terrain costs
                Terrain currentTerrain = currentSquare.getTerrain();
                if (currentTerrain != null){
                    currentTerrain.applyEffect(player); // This will modify the player's stats based on the terrain type
                    Log.info("[LOG] Stepped on " + currentTerrain.getClass().getSimpleName() + ", applied effect.");
                }

                //Check for and Collect Items
                if (currentSquare.hasItem()){
                    Item item  = currentSquare.getItem();
                    //need to add collectItem(Item i) to the player class
                    player.collectItem(item);
                    System.out.println("Collected item: " + item.getClass().getSimpleName() + "!");

                    // TODO: Remove item from the square after collection
                     //currentSquare.removeItem();
                }

                // --- WEEK 4 TRADING STUB ---
                // Check if the square has a trader
                if (currentSquare.hasTrader()) {
                    System.out.println("Encountered a Trader on square (" + currentSquare.getX() + ", " + currentSquare.getY() + ")");
                    
                    // TODO (Friday): 
                    // 1. Get the trader: Trader t = currentSquare.getTrader();
                    // 2. Ask Brain for an Offer: Offer o = player.getBrain().makeOffer();
                    // 3. Let Trader evaluate: boolean accepted = t.evaluateOffer(o);
                    // 4. Update player stats if accepted.
                }

                //Get the square to prep for future terrain/trader/item interactions
                map.getSquare(player.getX(), player.getY());

            } catch (Exception e) {
                Log.error("Error occurred: " + e.getMessage());
                break;
            }
        }
        Log.methodEnd("GameManager", "run", "void");
    }
}