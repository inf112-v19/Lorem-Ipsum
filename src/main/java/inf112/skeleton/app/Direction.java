package inf112.skeleton.app;

public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST;

	public Direction opositeDirection(Direction dir){
		switch (dir){
			case NORTH:
				return Direction.SOUTH;
			case SOUTH:
				return Direction.NORTH;
			case EAST:
				return Direction.WEST;
			case WEST:
				return Direction.EAST;
			default:
				System.err.println("No oposite direction found");
				return dir;
		}
	}
}


