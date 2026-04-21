public class Move 
{
    private String direction;

    public Move(String direction) 
    {
        if (isValidDirection(direction))
             {
            this.direction = direction.toUpperCase();
        } else {
            throw new IllegalArgumentException("Invalid move direction: " + direction);
        }
    }

    public String getDirection() 
    {
        return direction;
    }

    public void setDirection(String direction) 
    {
        if (isValidDirection(direction)) {
            this.direction = direction.toUpperCase();
        } else {
            throw new IllegalArgumentException("Invalid move direction: " + direction);
        }
    }

    private boolean isValidDirection(String direction) 
    {
        if (direction == null) return false;

        direction = direction.toUpperCase();

        return direction.equals("N") ||
               direction.equals("S") ||
               direction.equals("E") ||
               direction.equals("W") ||
               direction.equals("NE") ||
               direction.equals("NW") ||
               direction.equals("SE") ||
               direction.equals("SW") ||
               direction.equals("REST");
    }

    @Override
    public String toString()
     {
        return "Move Direction: " + direction;
    }
}
