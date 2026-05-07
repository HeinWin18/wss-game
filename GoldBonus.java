public class GoldBonus extends Item {

    private int amount;

    public GoldBonus(int amount) {
        super("Gold Bonus", amount, false);
        this.amount = amount;
    }

    @Override
    public void collect(Player player) {
        player.addGold(amount);
    }
}