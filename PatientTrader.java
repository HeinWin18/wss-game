// A patient trader will make counter offers until a deal is reached
public class PatientTrader extends Trader {
    
    public PatientTrader() {
        super(); // Calls base Trader constructor to generate inventory and logs
        this.name = "Patient Trader";
    }
    
    /** * evaluates if a trade is fair.
     * If it isn't, creates a new offer where isAcceptable() is true.
     */
    public Offer evaluateOffer(Offer offer) {
        Log.methodStart("PatientTrader", "evaluateOffer", "Offer");
        
        if (offer.isAcceptable()) {
            Log.info("Patient Trader accepts the original offer.");
            Log.methodEnd("PatientTrader", "evaluateOffer", "Offer");
            return offer;
        } else {
            Log.info("Patient Trader finds offer unfair. Calculating counter-offer...");
            
            int newGold = offer.getGoldRequest();
            int newWater = offer.getWaterRequest();
            int newFood = offer.getFoodRequest();
            
            int offeredValue = (offer.getGoldOffer() * 3) + offer.getWaterOffer() + offer.getFoodOffer();
            
            // Reduce a player's requested items until the math balances out
            while (offeredValue < ((newGold * 3) + newWater + newFood)) {
                if (newGold > 0) {
                    newGold--;
                } else if (newWater > 0) {
                    newWater--;
                } else if (newFood > 0) {
                    newFood--;
                } else {
                    break; // Safety net to prevent infinite loops
                }
            }
            
            Offer counterOffer = new Offer(offer.getGoldOffer(), offer.getWaterOffer(), offer.getFoodOffer(),
                                        newGold, newWater, newFood);
            
            Log.info("Patient Trader proposes a counter-offer.");
            Log.methodEnd("PatientTrader", "evaluateOffer", "Offer");
            return counterOffer;
        }
    }
}