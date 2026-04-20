public class GameManager {
    private Player player;
    private Map map;
    public void initialize() {
        map = new Map(20, 20);
        player = new Player();

        assert map != null : "Map should be initialized";
        assert player != null : "Player should be initialized";
    }
    public void run() {
        while(true) {
            try{
                Move move = player.makeMove();
                if (move == null) {
                    throw new IllegalStateException("Move is null");
                }
                System.out.println("Player made a move in direction: " + move.getDirection());
                // Process the move (not implemented)
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
                break;
            }
        }
    }
}