public abstract class Item {

    protected String name;
    protected int value;
    protected boolean repeating;

    public Item(String name, int value, boolean repeating) {
        this.name = name;
        this.value = value;
        this.repeating = repeating;
    }

    public abstract void collect(Player player);

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public boolean isRepeating() {
        return repeating;
    }
}