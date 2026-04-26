public abstract class Vision {

	protected Map map; // the game map Vision uses to look up squares
	protected String[] visibleDirections; // the directions this Vision type can see
	protected int x; // the player's current x position on the map
	protected int y; // the player's current y position on the map

	// Constructor
	public Vision(Map map, int x, int y) {
		this.map = map;
		this.x = x;
		this.y = y;
	}

	// Calculates the neighboring square's coordinates based on direction,
	// and returns that Square from the map. Returns null if out of bounds.
	protected Square getNeighbor(String dir) {
		int newX = x;
		int newY = y;

		switch (dir) {
		case "N":
			newY = y - 1;
			break;
		case "S":
			newY = y + 1;
			break;
		case "E":
			newX = x + 1;
			break;
		case "NE":
			newX = x + 1;
			newY = y - 1;
			break;
		case "SE":
			newX = x + 1;
			newY = y + 1;
			break;
		}

		return map.getSquare(newX, newY);
	}

	// Loops through all visible directions, finds the first square with a
	// FoodBonus,
	// and returns a Path to it. Returns null if none found.
	public Path closestFood() {
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasItem() && s.getItem() instanceof FoodBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Loops through all visible directions, skips the first FoodBonus it finds,
	// and returns a Path to the second one. Returns null if fewer than two found.
	public Path secondClosestFood() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasItem() && s.getItem() instanceof FoodBonus) {
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

	// Loops through all visible directions, finds the first square with a
	// WaterBonus,
	// and returns a Path to it. Returns null if none found.
	public Path closestWater() {
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasItem() && s.getItem() instanceof WaterBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Loops through all visible directions, skips the first WaterBonus it finds,
	// and returns a Path to the second one. Returns null if fewer than two found.
	public Path secondClosestWater() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasItem() && s.getItem() instanceof WaterBonus) {
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

	// Loops through all visible directions, finds the first square with a
	// GoldBonus,
	// and returns a Path to it. Returns null if none found.
	public Path closestGold() {
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasItem() && s.getItem() instanceof GoldBonus) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Loops through all visible directions, skips the first GoldBonus it finds,
	// and returns a Path to the second one. Returns null if fewer than two found.
	public Path secondClosestGold() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasItem() && s.getItem() instanceof GoldBonus) {
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

	// Loops through all visible directions, finds the first square with a Trader,
	// and returns a Path to it. Returns null if none found.
	public Path closestTrader() {
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasTrader()) {
				Path p = new Path();
				p.addMove(new Move(dir));
				return p;
			}
		}
		return null;
	}

	// Loops through all visible directions, skips the first Trader it finds,
	// and returns a Path to the second one. Returns null if fewer than two found.
	public Path secondClosestTrader() {
		boolean foundFirst = false;
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.hasTrader()) {
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

	// Loops through all visible directions, compares terrain strength costs,
	// and returns a Path to the square with the lowest movement cost.
	public Path easiestPath() {
		Square easiest = null;
		String easiestDir = null;
		int lowestCost = Integer.MAX_VALUE;
		for (String dir : visibleDirections) {
			Square s = getNeighbor(dir);
			if (s != null && s.getTerrain().getStrengthCost() < lowestCost) {
				lowestCost = s.getTerrain().getStrengthCost();
				easiest = s;
				easiestDir = dir;
			}
		}
		if (easiest != null) {
			Path p = new Path();
			p.addMove(new Move(easiestDir));
			return p;
		}
		return null;
	}

}