// an impatient trader will only evaluate 1 deal, and accept or reject it
public class ImpatientTrader extends Trader{

	// constructor for Impatient Trader
		public ImpatientTrader() {
			
			// identifies this trader as a default trader
			this.name = "Impatient Trader";
					
			// generate a random inventory, between 0 and 5 of each: gold, food, water
			this.gold = (int)(Math.random() * 6);
			this.water = (int)(Math.random() * 6);
			this.food = (int)(Math.random() * 6);
		}
		
		/** evaluateOffer(Offer: offer): returns an offer object
		* behavior: if the player's offer is fair, accept it
		* otherwise reject it
		* if the trader likes the offer, it will return the offer, letting trade() know to execute said trade
		* if the trader doesn't like the offer, do nothing 
		*/
		public Offer evaluateOffer(Offer offer) {
			
			if (offer.isAcceptable()) {
				return offer;
			}
		}
}
