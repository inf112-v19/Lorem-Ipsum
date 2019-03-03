package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GameObjects.GameObject;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.Position;
import inf112.skeleton.app.Tiles.Tile;

/** Interface for board. */

public interface IBoard<T> {

    /** @return The height of the grid. */
    int getHeight();

    /** @return The width of the grid. */
    int getWidth();

    /**
     * Set the Game Object on the tile in the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param pos
     *            given position you want to set the object
     * @param gameObject
     *            the Game Object being placed
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    void setGameObject(Position pos, GameObject gameObject);

    /**
     * Get the Game Objects on the tile in the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param pos
     *            position to the objects
     * @return the object that you are getting
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    T getGameObject(Position pos);

    /**
     *
     *

     */
    /**
     * Tries to move the player in the direction the player is facing - potentially starts recursive calling if player collision occurs
     *
     * @param player
     *            player to be moved
     * @return true if movement happened or false if player did not move
     */
    boolean movePlayer(Player player);

    /**
     * Remove a Game Object from the tile at the given x,y location.
     *
     * y must be greater than or equal to 0 and less than getHeight(). x must be
     * greater than or equal to 0 and less than getWidth().
     *
     * @param pos
     *            remove the object in this position
     * @param gameObject
     *            An element to be removed from the tile
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
    void removeObject(Position pos, GameObject gameObject);

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
