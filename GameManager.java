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
    public void initialize(int width, int height, int difficulty, int visionChoice, int brainChoice) {
        Log.methodStart("GameManager", "initialize", "none");

        map = new Map(width, height, difficulty);
        player = new Player(visionChoice, brainChoice);
        player.setMap(map); // Give the player access to the map for Vision and movement
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

                // 3 & 4. Brain selects the next move + Check if AI rest + Execute move (Player class)
                Move move = player.getBrain().makeMove();

                // Prevent a crash if the brain fails to return a move
                if(move == null) {
                    throw new IllegalStateException("Move is null");
                }

                // Check if the AI decided to rest
                if (move.getDirection().equals("REST")) {
                    player.rest();
                    System.out.println("Player skipped movement to rest.");
                    // Do NOT apply terrain damage since they didn't move!
                } 
                else {
                    // Normal movement logic goes here
                    player.move(move);
                    Log.info("Move chosen: " + move.getDirection());
                    
                    // 5. Week 3 Integration logic: Apply terrain costs, item pickups.
                    // Get the exact square the player landed on
                    Square currentSquare = map.getSquare(player.getX(), player.getY());

                    if (currentSquare == null) {
                        Log.info("[WARN] Player moved out of bounds, resetting position.");
                        break;
                    }
                    // Apply terrain costs
                    Terrain currentTerrain = currentSquare.getTerrain();
                    if (currentTerrain != null){
                        currentTerrain.applyEffect(player); // This will modify the player's stats based on the terrain type
                        Log.info("[LOG] Stepped on " + currentTerrain.getClass().getSimpleName() + ", applied effect.");
                    }

                    // Check for and Collect Items
                    if (currentSquare.hasItem()){
                        Item item  = currentSquare.getItem();
                        player.collectItem(item);
                        System.out.println("Collected item: " + item.getClass().getSimpleName() + "!");

                        // TODO: Remove item from the square after collection
                        // currentSquare.removeItem();
                    }

                    // --- WEEK 4 TRADING LOOP ---
                    if (currentSquare.hasTrader()) {
                        Trader trader = currentSquare.getTrader();
                        System.out.println("Player encountered a " + trader.getClass().getSimpleName() + " on square (" + currentSquare.getX() + ", " + currentSquare.getY() + ")");
                        
                        // 1. The AI Brain creates an Offer based on its needs
                        Offer playerOffer = player.getBrain().makeOffer(trader);
                        
                        if (playerOffer != null) {
                            System.out.println("Player proposes a trade.");
                            playerOffer.printOffer();
                            
                            // 2. Trader evaluates. (PatientTraders might counter-offer, ImpatientTraders return null if they hate it)
                            Offer finalOffer = trader.evaluateOffer(playerOffer);
                            
                            if (finalOffer != null) {
                                // 3 & 4. Execute the trade and update stats automatically!
                                System.out.println("Final negotiated trade:");
                                finalOffer.printOffer(); // <-- ADD THIS!
                                
                                trader.trade(player, finalOffer);
                                System.out.println("Trade successfully executed!");
                            } else {
                                System.out.println("Trade negotiations failed. Trader rejected the offer.");
                            }
                        } else {
                            System.out.println("Player decided not to trade.");
                        }
                    }

                    // Get the square to prep for future terrain/trader/item interactions
                    map.getSquare(player.getX(), player.getY());
                }

            } catch (Exception e) {
                Log.error("Error occurred: " + e.getMessage());
                break;
            }
        }
        Log.methodEnd("GameManager", "run", "void");
    }
}