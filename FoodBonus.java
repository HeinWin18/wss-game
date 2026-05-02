public class FoodBonus extends Item {

    private int hungerRestore;

    public FoodBonus(int hungerRestore) {
        // Square will contain an item with an amount (ex: new FoodBonus(5))
        // Still unsure if repeating is necessary? 
        super("Food Bonus", 0, false);
        this.hungerRestore = hungerRestore;
    }

    @Override
    public void collect(Player player) {
        player.addFood(hungerRestore);
    }
}