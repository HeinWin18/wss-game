// An impatient trader will only evaluate 1 deal, and accept or reject it
public class ImpatientTrader extends Trader {

    public ImpatientTrader() {
        super(); // Calls base Trader constructor to generate inventory and logs
        this.name = "Impatient Trader";
    }
        
    /** * If the player's offer is fair, accept it.
     * Otherwise reject it completely.
     */
    public Offer evaluateOffer(Offer offer) {
        Log.methodStart("ImpatientTrader", "evaluateOffer", "Offer");
        
        if (offer.isAcceptable()) {
            Log.info("Impatient Trader accepts the offer.");
            Log.methodEnd("ImpatientTrader", "evaluateOffer", "Offer");
            return offer;
        } else {
            Log.info("Impatient Trader rejects the offer and refuses to negotiate.");
            Log.methodEnd("ImpatientTrader", "evaluateOffer", "null");
            return null; // Must return null if rejected so the compiler doesn't throw an error
        }
    }
}