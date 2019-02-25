package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Position;
import inf112.skeleton.app.Tiles.Tile;

/** Interface for board. */

public interface IBoard<T> {

    /** @return The height of the grid. */
    int getHeight();

    /** @return The width of the grid. */
    int getWidth();

    /**
     * Set the contents of the cell in the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param pos
     *            given position you want to set the object
     * @param element
     *            The contents the cell is to have.
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    void setGameObject(Position pos, T element);

    /**
     * Get the contents of the cell in the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param pos
     *            position to the object
     * @return the object that you are getting
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    T getGameObject(Position pos);

    /**
     * Move the current player in the given direction.
     *
     * @param pos
     *            move to this position/coordinates
     */
    void movePlayer(Position pos);

    /**
     * Remove an element from the cell at the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param pos
     *            remove the object in this position
     * @param element
     *            An element to be removed from the cell
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    void removeObject(Position pos, T element);

    /**
     * Check if coordinates are valid.
     *
     * Valid coordinates are 0 <= x < getWidth(), 0 <= y < getHeight().
     *
     * @param pos
     *            is this position/coordinates valid?
     * @return true if the (x,y) position is within the grid
     */
    boolean isValidPos(Position pos);


	/**
	 * Gets the tile on a given position
	 *
	 * @param pos
	 * @return
	 */
	Tile getTile(Position pos);

}
