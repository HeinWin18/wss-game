public class Offer {

    // Player Offer
    public int goldOffer;
    public int waterOffer;
    public int foodOffer;

    // Player Request
    public int goldRequest;
    public int waterRequest;
    public int foodRequest;

    // Constructor
    public Offer(int goldOffer, int waterOffer, int foodOffer,
                 int goldRequest, int waterRequest, int foodRequest) {

        this.goldOffer = goldOffer;
        this.waterOffer = waterOffer;
        this.foodOffer = foodOffer;

        this.goldRequest = goldRequest;
        this.waterRequest = waterRequest;
        this.foodRequest = foodRequest;
    }

    // Check to see if value of offer is greater than request to ensure fairness
    // Gold is more valueable so it has a determined weighted value 
    public boolean isAcceptable() {

        int offeredValue =
                (goldOffer * 3) +
                waterOffer +
                foodOffer;

        int requestedValue =
                (goldRequest * 3) +
                waterRequest +
                foodRequest;

        // Ex: Player cannot offer 1 food for 1 gold. 

        return offeredValue >= requestedValue;
    }

    // Getters
    public int getGoldOffer() { 
        return goldOffer; 
    }
    public int getWaterOffer() { 
        return waterOffer; 
    }
    public int getFoodOffer() { 
        return foodOffer; 
    }

    public int getGoldRequest() { 
        return goldRequest; 
    }
    public int getWaterRequest() { 
        return waterRequest; 
    }
    public int getFoodRequest() { 
        return foodRequest; 
    }

    public void printOffer() {
        System.out.println("Offer: Give [" + goldOffer + " Gold, " +
                waterOffer + " Water, " +
                foodOffer + " Food]");

        System.out.println("       Get  [" + goldRequest + " Gold, " +
                waterRequest + " Water, " +
                foodRequest + " Food]");
    }
}