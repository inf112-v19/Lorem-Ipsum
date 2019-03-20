package inf112.skeleton.app.GameMechanics;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;

	public Direction oppositeDirection() {
		switch (this) {
			case NORTH:
				return Direction.SOUTH;
			case SOUTH:
				return Direction.NORTH;
			case EAST:
				return Direction.WEST;
			case WEST:
				return Direction.EAST;
			default:
				System.err.println("No opposite direction found");
				return this;
		}
	}

	public int directionToDegrees() {
		switch (this) {
			case NORTH:
				return 0;
			case SOUTH:
				return 180;
			case EAST:
				return 90;
			case WEST:
				return -90;
			default:
				System.err.println("direction not valid!");
				return 0;
		}
	}
}


