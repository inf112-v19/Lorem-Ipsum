package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Position;

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
	boolean hasAnyGameObjects();

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

	/**
	 * method that places a GameObject to the tile
	 *
	 * @param gameObject
	 */
	void addGameObject(GameObject gameObject);

	/**
	 * method that removes a specified GameObject from the tile if present
	 *
	 * @param gameObject
	 */
	boolean removeGameObject(GameObject gameObject);

	/**
	 * method that checks if a given GameObject is present on the tile
	 *
	 * @param gameObject
	 * @return int of the index position of the GameObject, or possibly -1 if not present
	 */
	int hasGameObject(GameObject gameObject);

	/**
	 * Checks if tile has a wall in the given direction
	 *
	 * @param dir
	 * @return true if wall is present or false if not
	 */
	boolean hasWallInDir(Direction dir);

	/**
	 * Tells the player to do the action specified by the tile type
	 * @param board current game board
	 * @param player the Player to preform the action
	 */
	void checkTile(Board board, Player player);


	/**
	 * Checks if the tile contains any lasers and gives damage to the player accordingly
	 * @param player
	 */
	void laserCheck(Player player);


}
