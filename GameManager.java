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
        
        for (int i = 0; i < 21; i++) { 
            try {
                Move move = player.makeMove();

                if (move == null) {
                    throw new IllegalStateException("Move is null");
                }

                String dir = move.getDirection();
                Log.info("Move: " + dir);

                if (dir.equals("EAST")) {
                    player.x++;
                }

                Log.info("Position: (" + player.x + ", " + player.y + ")");

                map.getSquare(player.x, player.y);

                if (player.x >= map.getCols() - 1) {
                    Log.info("Player has reached EAST. Game Over!");
                    break;
                } 

            } catch (Exception e) {
                Log.error("Error occurred: " + e.getMessage());
                break;
            }
        }
        Log.methodEnd("GameManager", "run", "void");
    }
}