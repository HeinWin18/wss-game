public class Path {

    private List<Move> moves;

    private int totalCost;
    private int waterCost;
    private int foodCost;

    public Path() {
        moves = new ArrayList<>();
        totalCost = 0;
        waterCost = 0;
        foodCost = 0;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public List<Move> getMoves() {
        return moves;
    }

    // Getters
    public int getTotalCost() {
        return totalCost;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    // Console Output
    public void printPath() {
        for (Move m : moves) {
            System.out.print(m.getDirection() + " ");
        }
        System.out.println();
    }
}