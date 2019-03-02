package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Position;

/**
 * Interface for Tiles
 *
 */

public interface ITile {

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
	boolean hasObjectOnTile();

	/**
	 * method that returns any GameObject in the Tile.
	 * Should be null if there is no GameObject on the Tile
	 *
	 * @return GameObject
	 */
	//requires the GameObject class
	//GameObject getObjectOnTile();


	/**
	 * @return SpritType of the Tile
	 */
	SpriteType getSpriteType();


	/**
	 * method that returns GameObjects on the Tile
	 * @return GameObject[]
	 */
	IGameObject[] getGameObjects();


	Direction getDirection();




}
