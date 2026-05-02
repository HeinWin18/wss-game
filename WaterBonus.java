public class WaterBonus extends Item {

    private int thirstRestore;

    public WaterBonus(int thirstRestore) {
        super("Water Bonus", 0, false);
        this.thirstRestore = thirstRestore;
    }

    @Override
    public void collect(Player player) {
        player.addWater(thirstRestore);
    }
}