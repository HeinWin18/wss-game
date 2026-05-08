// A patient trader will make counter offers until a deal is reached
public class PatientTrader extends Trader {
	
	// constructor for Patient Trader
	public PatientTrader() {
		
		// identifies this trader as a default trader
		this.name = "Patient Trader";
				
		// generate a random inventory, between 0 and 5 of each: gold, food, water
		this.gold = (int)(Math.random() * 6);
		this.water = (int)(Math.random() * 6);
		this.food = (int)(Math.random() * 6);
	}
	
	/** evaluateOffer(Offer: offer): returns an offer object
	* behavior: a patient trader will evaluate if a trade is fair
	* if it is, return the same offer so that trade() can execute
	* if it isn't create a new offer where isAcceptable() is true
	*/
	public Offer evaluateOffer(Offer offer) {
		
		if (offer.isAcceptable) {
			return offer;
		} else {
			
			// potential new player request values
			int newGold;
			int newWater;
			int newFood;
			
			// reduce a player's requested items until an offer is fair i.e. isAcceptable is true
			while (!offer.isAcceptable()) {
				if (offer.getGoldRequest() > 0) {
					newGold = offer.getGoldRequest() - 1;
				} if (offer.getWaterRequest() > 0) {
					newWater = offer.getWaterRequest() - 1;
				} if (offer.getFoodRequest() > 0) {
					newFood = offer.getFoodRequest() - 1;
				}
			}
			
			// create a new Offer object with the same player offer's but new request numbers
			Offer newOffer = new Offer(offer.getGoldOffer(), offer.getWaterOffer(), offer.getFoodOffer(),
										newGold, newWater, newFood);
			
			return newOffer;
		}
	}
}
