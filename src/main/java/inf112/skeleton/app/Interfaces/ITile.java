package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Position;

/**
 * Interface for Tiles
 *
 */

public interface ITile {

	/**
	 * Get the x coordinat to sprite in sprite sheet
	 * @return int coordinat to use in a sprite sheet
	 */
	int getSpriteX();

	/**
	 * Get the y coordinat to sprite in sprite sheet
	 * @return int coordinat to use in a sprite sheet
	 */
	int getSpriteY();


	/**
	 * get the Tiles Position
	 *
	 * @return Position object
	 */
	Position getPosition();


	/**
	 * method for checking if an object is on the tile
	 *
	 * @return true is there is an object on tile
	 */
	boolean objectOnTile();

	/**
	 * method that returns any GameObject in the Tile.
	 * Should be null if there is no GameObject on the Tile
	 *
	 * @return GameObject
	 */
	//requires the GameObject class
	//GameObject getObjectOnTile();






}
