// Trader superclass: represents a merchant in a square that the player can trade with
public class Trader {
    
    protected String name; 
    protected int gold;     
    protected int water;    
    protected int food;     
    
    public Trader() {
        Log.methodStart("Trader", "Constructor", "none");
        this.name = "Default Trader";
        
        // generate a random inventory, between 0 and 5 of each
        this.gold = (int)(Math.random() * 6);
        this.water = (int)(Math.random() * 6);
        this.food = (int)(Math.random() * 6);
        
        Log.info(name + " spawned with - Gold: " + gold + ", Water: " + water + ", Food: " + food);
        Log.methodEnd("Trader", "Constructor", "void");
    }
    
    public boolean hasItems() {
        return (this.gold > 0 || this.water > 0 || this.food > 0);
    }
        
    public String getInventory() {
        return name + " has " + this.gold + " gold, " + this.food + " food, and " + this.water + " water.";
    }

    /**
     * Default evaluation: Accept if fair, reject if not.
     * (Patient and Impatient traders will override this with their own logic)
     */
    public Offer evaluateOffer(Offer offer) {
        Log.methodStart("Trader", "evaluateOffer", "Offer");
        
        if (offer.isAcceptable()) {
            Log.info("Default Trader accepts the offer.");
            Log.methodEnd("Trader", "evaluateOffer", "Offer");
            return offer; // Deal is good!
        } else {
            Log.info("Default Trader rejects the offer.");
            Log.methodEnd("Trader", "evaluateOffer", "null");
            return null;  // Deal is bad!
        }
    }
    
    // Evaluates offers and executes trade if fair and inventory allows
    public void trade(Player player, Offer offer) {
        Log.methodStart("Trader", "trade", "Player, Offer");
        
        // FIX: Check if Aldo's offer is fair AND if the Trader actually has the items to give
        boolean hasEnoughInventory = (this.gold >= offer.getGoldRequest() && 
                                      this.water >= offer.getWaterRequest() && 
                                      this.food >= offer.getFoodRequest());

        if (offer.isAcceptable() && hasEnoughInventory) {
            Log.info("Trader accepted the offer!");
            
            // take requested items away from trader
            this.gold -= offer.getGoldRequest();
            this.water -= offer.getWaterRequest();
            this.food -= offer.getFoodRequest();
            
            // add player's offered items to trader
            this.gold += offer.getGoldOffer();
            this.water += offer.getWaterOffer();
            this.food += offer.getFoodOffer();
            
            int playerFood = offer.getFoodRequest() - offer.getFoodOffer();
            int playerWater = offer.getWaterRequest() - offer.getWaterOffer();
            int playerGold = offer.getGoldRequest() - offer.getGoldOffer();
        
            // Note: Keeping Parker's spelling of 'recieveTrade' assuming it matches your Player.java
            player.recieveTrade(playerFood, playerWater, playerGold);
            
        } else {
            Log.info("Trader rejected the offer. (Either unfair deal or not enough inventory).");
        }
        
        Log.methodEnd("Trader", "trade", "void");
    }
}