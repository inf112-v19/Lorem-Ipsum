package inf112.skeleton.app;

public class Position {
	private final int x;
	private final int y;

	/**
	 *
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 *
	 * @return position coordinates in array form [x, y]
	 */
	public int[] getPosition() {
		return new int[]{this.x, this.y};
	}

	/**
	 *
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 *
	 * @return y coodinate
	 */
	public int getY() {
		return y;
	}
}
