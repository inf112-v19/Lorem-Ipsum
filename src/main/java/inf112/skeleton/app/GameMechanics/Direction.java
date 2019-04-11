package inf112.skeleton.app.GameMechanics;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;

	/**
	 * Method that return the opposite direction based on the current direction.
	 * @return opposite direction
	 */
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

	/**
	 * Method to find the needed rotation amount to achieve right direction.
	 * LIBGDX will rotate anticlockwise. Using minus to rotate clockwise.
	 *
	 * @return the degrees to rotate
	 */
	public int directionToDegrees() {
		switch (this) {
			case NORTH:
				return 0;
			case SOUTH:
				return -180;
			case EAST:
				return -90;
			case WEST:
				return -270;
			default:
				System.err.println("direction not valid!");
				return 0;
		}
	}
}


