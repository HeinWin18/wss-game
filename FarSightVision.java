/**
 * FarSightVision can see up to two squares away in North, South, and East directions.
 */
public class FarSightVision extends Vision {

    private int range = 2;

    public FarSightVision(Map map, int x, int y) {
        super(map, x, y);
        Log.methodStart("FarSightVision", "Constructor", "Map, " + x + ", " + y);
        this.visibleDirections = new String[] { "N", "S", "E" };
        Log.methodEnd("FarSightVision", "Constructor", "void");
    }

    private Square getFarNeighbor(String dir) {
        Log.methodStart("FarSightVision", "getFarNeighbor", dir);
        int newX = x;
        int newY = y;

        switch (dir) {
            case "N": newY = y - range; break;
            case "S": newY = y + range; break;
            case "E": newX = x + range; break;
        }

        Square sq = map.getSquare(newX, newY);
        Log.methodEnd("FarSightVision", "getFarNeighbor", sq == null ? "null" : "Square");
        return sq;
    }

    @Override
    public Path closestFood() {
        Log.methodStart("FarSightVision", "closestFood", "none");
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasItem() && near.getItem() instanceof FoodBonus) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestFood", "Path(near)");
                return p;
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasItem() && far.getItem() instanceof FoodBonus) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestFood", "Path(far)");
                return p;
            }
        }
        Log.methodEnd("FarSightVision", "closestFood", "null");
        return null;
    }

    @Override
    public Path secondClosestFood() {
        Log.methodStart("FarSightVision", "secondClosestFood", "none");
        boolean foundFirst = false;
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasItem() && near.getItem() instanceof FoodBonus) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestFood", "Path(near)");
                    return p;
                }
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasItem() && far.getItem() instanceof FoodBonus) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestFood", "Path(far)");
                    return p;
                }
            }
        }
        Log.methodEnd("FarSightVision", "secondClosestFood", "null");
        return null;
    }

    @Override
    public Path closestWater() {
        Log.methodStart("FarSightVision", "closestWater", "none");
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasItem() && near.getItem() instanceof WaterBonus) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestWater", "Path(near)");
                return p;
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasItem() && far.getItem() instanceof WaterBonus) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestWater", "Path(far)");
                return p;
            }
        }
        Log.methodEnd("FarSightVision", "closestWater", "null");
        return null;
    }

    @Override
    public Path secondClosestWater() {
        Log.methodStart("FarSightVision", "secondClosestWater", "none");
        boolean foundFirst = false;
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasItem() && near.getItem() instanceof WaterBonus) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestWater", "Path(near)");
                    return p;
                }
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasItem() && far.getItem() instanceof WaterBonus) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestWater", "Path(far)");
                    return p;
                }
            }
        }
        Log.methodEnd("FarSightVision", "secondClosestWater", "null");
        return null;
    }

    @Override
    public Path closestGold() {
        Log.methodStart("FarSightVision", "closestGold", "none");
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasItem() && near.getItem() instanceof GoldBonus) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestGold", "Path(near)");
                return p;
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasItem() && far.getItem() instanceof GoldBonus) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestGold", "Path(far)");
                return p;
            }
        }
        Log.methodEnd("FarSightVision", "closestGold", "null");
        return null;
    }

    @Override
    public Path secondClosestGold() {
        Log.methodStart("FarSightVision", "secondClosestGold", "none");
        boolean foundFirst = false;
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasItem() && near.getItem() instanceof GoldBonus) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestGold", "Path(near)");
                    return p;
                }
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasItem() && far.getItem() instanceof GoldBonus) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestGold", "Path(far)");
                    return p;
                }
            }
        }
        Log.methodEnd("FarSightVision", "secondClosestGold", "null");
        return null;
    }

    @Override
    public Path closestTrader() {
        Log.methodStart("FarSightVision", "closestTrader", "none");
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasTrader()) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestTrader", "Path(near)");
                return p;
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasTrader()) {
                Path p = new Path();
                p.addMove(new Move(dir));
                Log.methodEnd("FarSightVision", "closestTrader", "Path(far)");
                return p;
            }
        }
        Log.methodEnd("FarSightVision", "closestTrader", "null");
        return null;
    }

    @Override
    public Path secondClosestTrader() {
        Log.methodStart("FarSightVision", "secondClosestTrader", "none");
        boolean foundFirst = false;
        for (String dir : visibleDirections) {
            Square near = getNeighbor(dir);
            if (near != null && near.hasTrader()) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestTrader", "Path(near)");
                    return p;
                }
            }
            Square far = getFarNeighbor(dir);
            if (far != null && far.hasTrader()) {
                if (!foundFirst) foundFirst = true;
                else {
                    Path p = new Path();
                    p.addMove(new Move(dir));
                    Log.methodEnd("FarSightVision", "secondClosestTrader", "Path(far)");
                    return p;
                }
            }
        }
        Log.methodEnd("FarSightVision", "secondClosestTrader", "null");
        return null;
    }

    @Override
    public Path easiestPath() {
        Log.methodStart("FarSightVision", "easiestPath", "none");
        String easiestDir = null;
        int lowestCost = Integer.MAX_VALUE;

        for (String dir : visibleDirections) {
            int totalCost = 0;

            Square near = getNeighbor(dir);
            if (near != null) {
                totalCost += near.getTerrain().getStrengthCost();
            }

            Square far = getFarNeighbor(dir);
            if (far != null) {
                totalCost += far.getTerrain().getStrengthCost();
            }

            if (near != null && totalCost < lowestCost) {
                lowestCost = totalCost;
                easiestDir = dir;
            }
        }

        if (easiestDir != null) {
            Path p = new Path();
            p.addMove(new Move(easiestDir));
            Log.methodEnd("FarSightVision", "easiestPath", "Path(" + easiestDir + ")");
            return p;
        }
        
        Log.methodEnd("FarSightVision", "easiestPath", "null");
        return null;
    }
}