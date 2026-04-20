public class Player {
    public Move makeMove() {
        Move move = new Move("EAST");   

        assert move.getDirection() != null : "Move direction should not be null";
        return move;
    }
}