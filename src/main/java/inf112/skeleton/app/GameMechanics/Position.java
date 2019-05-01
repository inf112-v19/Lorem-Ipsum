package inf112.skeleton.app.GameMechanics;

import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.BoardGUI;

import java.util.Objects;

public class Position {
	private final int x;
	private final int y;

	/**
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param tile on stage
	 * @param boardGUI
	 */
	public Position(Tile tile, BoardGUI boardGUI){
		float x = (tile.getX() - boardGUI.getxOffset()) / (tile.getWidth());
		float y = (tile.getY() - boardGUI.getyOffset()) / (tile.getHeight());
		this.x = (int)x;
		this.y = (int)y;
	}

	public Position(String positionToString){
		String xandY = positionToString.substring(9);
		String xs = xandY.split(", ")[0].split("=")[1];
		String ys = xandY.split(", ")[1].split("=")[1].split("}")[0];
		this.x = Integer.parseInt(xs);
		this.y = Integer.parseInt(ys);

	}

	/**
	 * @return position coordinates in array form [x, y]
	 */
	public int[] getPosition() {
		return new int[]{this.x, this.y};
	}

	/**
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return y coodinate
	 */
	public int getY() {
		return this.y;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return x == position.x &&
				y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}


	/**
	 * Calculates the position in the given direction for the current position
	 *
	 * @param dir
	 * @return
	 */
	public Position getNeighbour(Direction dir) {
		switch (dir) {
			case NORTH:
				return new Position(this.x, this.y + 1);
			case SOUTH:
				return new Position(this.x, this.y - 1);
			case EAST:
				return new Position(this.x + 1, this.y);
			case WEST:
				return new Position(this.x - 1, this.y);
			default:
				return this;
		}
	}


	@Override
	public String toString() {
		return "Position{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
