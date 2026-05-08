public class Offer {

    // Player Offer
    public int goldOffer;
    public int waterOffer;
    public int foodOffer;

    // Player Request
    public int goldRequest;
    public int waterRequest;
    public int foodRequest;

    public Offer(int goldOffer, int waterOffer, int foodOffer,
                 int goldRequest, int waterRequest, int foodRequest) {
        Log.methodStart("Offer", "Constructor", "multiple ints");

        this.goldOffer = goldOffer;
        this.waterOffer = waterOffer;
        this.foodOffer = foodOffer;

        this.goldRequest = goldRequest;
        this.waterRequest = waterRequest;
        this.foodRequest = foodRequest;
        
        Log.methodEnd("Offer", "Constructor", "void");
    }

    // Check to see if value of offer is greater than request to ensure fairness
    public boolean isAcceptable() {
        Log.methodStart("Offer", "isAcceptable", "none");

        int offeredValue = (goldOffer * 3) + waterOffer + foodOffer;
        int requestedValue = (goldRequest * 3) + waterRequest + foodRequest;

        boolean isFair = offeredValue >= requestedValue;
        
        Log.info("Evaluating Trade Math: Offered Value (" + offeredValue + ") vs Requested Value (" + requestedValue + ")");
        Log.methodEnd("Offer", "isAcceptable", String.valueOf(isFair));
        
        return isFair;
    }

    // Getters
    public int getGoldOffer() { return goldOffer; }
    public int getWaterOffer() { return waterOffer; }
    public int getFoodOffer() { return foodOffer; }

    public int getGoldRequest() { return goldRequest; }
    public int getWaterRequest() { return waterRequest; }
    public int getFoodRequest() { return foodRequest; }

    public void printOffer() {
        Log.info("Offer: Give [" + goldOffer + " Gold, " + waterOffer + " Water, " + foodOffer + " Food]");
        Log.info("       Get  [" + goldRequest + " Gold, " + waterRequest + " Water, " + foodRequest + " Food]");
    }
}