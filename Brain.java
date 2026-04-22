public abstract class Brain {
    protected Player player;
    protected Vision vision;
    // NOTE:
        /* assuming that the brain is automatically choosing
        / for the player, not the other way around */
    abstract void makeMove();

    public Brain(){}

    // TO DO:
        // aggressive and balanced brain

        /* using Vision, evaluate needs
        / and adapt from there, asking player
        / if they want an alternative option
        / through the Vision class */
}

class AggressiveBrain extends Brain
{

    @Override
    void makeMove() 
    {
        // aggressive favors east more than resources
        player.move("E");
    }

    void makeMove(String direction)
    {
        player.move(direction);
    }

    public void AggressiveBrain()
    {
        // resources are critically low
        while (player.getFood() >= 2 || player.getWater() >= 2 || player.getStrength() >= 2)
        {
            makeMove();
            if (player.getFood() == 2 || player.getWater() == 2 || player.getStrength() == 2)
            {
                // assuming direction is returned from .evaluateNeeds() function
                String direction = vision.evaluateNeeds();
                makeMove(direction);
                // NOTE:
                    // might need some logic to break out of the while loop
            }
        }
    }
}

class BalancedBrain extends Brain
{
    @Override
    void makeMove()
    {
        String direction = vision.evaluateNeeds();
        player.move(direction);
    }

    public void BalancedBrain
    {
        // will have to see how the vision class is implemented
    }
}



