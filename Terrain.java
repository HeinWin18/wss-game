public class Terrain {
	
	// Terrain attributes
	protected int strengthCost;
	protected int waterCost;
	protected int foodCost;

	// Methods
	// Getters
	// getStrengthCost() : int - returns the terrain’s strength cost
    public int getStrengthCost(){
        return this.strengthCost;
    }
    
    // getWaterCost() : int - returns the terrain’s water cost
    public int getWaterCost() {
    	return this.waterCost;
    }
    
    // getFoodCost() : int - returns the terrain’s food cost
    public int getFoodCost() {
    	return this.foodCost;
    }
    
    // getType() : String - returns the terrain type
    public String getType() {
    	return this.getClass().getSimpleName();
    }
    
    // applyEffect(player : Player) - deducts strengthCost, waterCost, and foodCost from the player’s stats when the player enters this square
    public void applyEffect(Player p) {
    	p.reduceStats(this.foodCost, this.waterCost, this.strengthCost)
    }
}
