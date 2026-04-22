package wss;

public class FarSightVision extends Vision {

	// FarSightVision has an extended range compared to other Vision types
	private int range = 2;

	// Constructor
	// FarSightVision can see up to two squares away in North, South, and East
	// directions
	public FarSightVision(Map map, int x, int y) {
		super(map, x, y);
		visibleDirections = new String[] { "N", "S", "E" };
	}

	// Returns the square 2 steps away in the given direction
	private Square getFarNeighbor(String dir) {
		int newX = x;
		int newY = y;

		switch (dir) {
		case "N":
			newY = y - range;
			break;
		case "S":
			newY = y + range;
			break;
		case "E":
			newX = x + range;
			break;
		}

		return map.getSquare(newX, newY);
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the first square with a FoodBonus. Returns null if none
	// found.
	@Override
	public Path closestFood() {
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasItem() && near.getItem() instanceof FoodBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasItem() && far.getItem() instanceof FoodBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the second square with a FoodBonus. Returns null if fewer
	// than two found.
	@Override
	public Path secondClosestFood() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasItem() && near.getItem() instanceof FoodBonus) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasItem() && far.getItem() instanceof FoodBonus) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the first square with a WaterBonus. Returns null if none
	// found.
	@Override
	public Path closestWater() {
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasItem() && near.getItem() instanceof WaterBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasItem() && far.getItem() instanceof WaterBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the second square with a WaterBonus. Returns null if fewer
	// than two found.
	@Override
	public Path secondClosestWater() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasItem() && near.getItem() instanceof WaterBonus) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasItem() && far.getItem() instanceof WaterBonus) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the first square with a GoldBonus. Returns null if none
	// found.
	@Override
	public Path closestGold() {
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasItem() && near.getItem() instanceof GoldBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasItem() && far.getItem() instanceof GoldBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the second square with a GoldBonus. Returns null if fewer
	// than two found.
	@Override
	public Path secondClosestGold() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasItem() && near.getItem() instanceof GoldBonus) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasItem() && far.getItem() instanceof GoldBonus) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the first square with a Trader. Returns null if none found.
	@Override
	public Path closestTrader() {
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasTrader()) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasTrader()) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Checks square 1 out first, then square 2 out.
	// Returns a Path to the second square with a Trader. Returns null if fewer than
	// two found.
	@Override
	public Path secondClosestTrader() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square near = getNeighbor(dir);
			if (near != null && near.hasTrader()) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
			Square far = getFarNeighbor(dir);
			if (far != null && far.hasTrader()) {
				if (!foundFirst) {
					foundFirst = true;
				} else {
					Path p = new Path();
					p.addMove(new Move(dir));
					return p;
				}
			}
		}
		return null;
	}

	// Checks both squares in each direction, compares cumulative movement cost,
	// and returns a Path to the direction with the lowest total cost.
	@Override
	public Path easiestPath() {
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
			return p;
		}
		return null;
	}

}