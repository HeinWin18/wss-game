// Trader superclass: represents a merchant in a square that the player can trade with
public class Trader {
	
	// Trader attributes
	protected String name;  // the trader's identifier
	protected int gold;     // how much gold the trader has available
	protected int water;    // how much water the trader has available
	protected int food;     // how much food the trader has available
	
	// constructor for a default trader who either accepts or rejects the first offer from the player
	public Trader() {
		
		// identifies this trader as a default trader
		this.name = "Default Trader";
		
		// generate a random inventory, between 0 and 5 of each: gold, food, water
		this.gold = (int)(Math.random() * 6);
		this.water = (int)(Math.random() * 6);
		this.food = (int)(Math.random() * 6);
	}
	
	// Methods
	// hasItems(): returns true if the trader has items to trade
	public boolean hasItems() {
		if (this.gold > 0 || this.water > 0 || this.food > 0) {
			return true;
		} else {
			return false;
		}
	}
		
	// getInventory: returns a summary of the trader's current resources
	public String getInventory() {
		
		String summary = name + " has " + this.gold + " gold, "
				+ this.food + " food, and "
				+ this.water + " water.";
		
		return summary;
	}
	
	// trade(Player: player, Offer: offer): evaluates offers and makes a decision on what to do
	// if the offer is fair, execute the offer
	// otherwise do nothing
	public void trade(Player player, Offer offer) {
		
		// if a trader likes an offer, i.e. evaluateOffer returns an offer object that is exactly the same
		// execute the trade
		if (evaluateOffer(offer) == offer) {
			
			// take requested items away from trader
			this.gold -= offer.getGoldRequest();
			this.water -= offer.getWaterRequest();
			this.food -= offer.getFoodRequest();
			
			// add player's offered items to trader
			this.gold += offer.getGoldOffer();
			this.water += offer.getWaterOffer();
			this.food += offer.getFoodOffer();
			
			// calculate a negative or positive number for player's recieveTrade() function
			// a positive number means the player is receiving, a negative number means the player is giving
			int playerFood = offer.getFoodRequest() - offer.getFoodOffer();
			int playerWater = offer.getWaterRequest() - offer.getWaterOffer();
			int playerGold = offer.getGoldRequest() - offer.getGoldOffer();
		
			// add or subtract items from a player's inventory
			player.recieveTrade(playerFood, playerWater, playerGold);
		}
	}
}
