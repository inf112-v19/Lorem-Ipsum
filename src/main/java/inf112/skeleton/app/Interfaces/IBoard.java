package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

/** Interface for board. */

public interface IBoard<T> {

    /** @return The height of the grid. */
    int getHeight();

    /** @return The width of the grid. */
    int getWidth();

	/**
	 * Creates an array of all the players where the index is equal to the playerID
	 * (playerID is set as a counter in the constructor starting from 0)
	 *
	 * @return
	 */
	Player[] getAllPlayers();

    /**
     * Set the Game Object on the tile in the given Position.
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
     * Get the Game Objects on the tile in the given Position.
     *
     * @param pos
     *            position to the objects
     * @return the object that you are getting
     * @throws IndexOutOfBoundsException
     *             if !isValid(x,y)
     */
	GameObject[] getGameObject(Position pos);

	/**
	 * Tries to move the player in the direction the player is facing - potentially starts recursive calling
	 * if player collision occurs. Tries to move the player numberOfMoves times by calling the
	 * movePlayer(Player player, Direction Dir, int numberOfMoves).
	 *
	 * @param player
	 *            player to be moved
	 * @param numberOfMoves
	 *            the number of moves that should happened
	 * @return true if movement happened and the player did not fell off the board
	 */
	boolean movePlayer(Player player, int numberOfMoves) throws PlayerNotFoundException;

	/**
	 * Tries to move the player in the given direction numberOfMoves times by calling the
	 * movePlayer(Player player, Direction dir). Stops when the underlying call returns false meaning
	 * the player was not able to move or potentially fell off the board.
	 *
	 * @param player
	 *            player to be moved
	 * @param dir
	 *            direction to be moved
	 * @param numberOfMoves
	 *            number of times to be tried to move
	 * @return true if movement happened and the player did not fell off the board
	 * @throws PlayerNotFoundException
	 */
	boolean movePlayer(Player player, Direction dir, int numberOfMoves) throws PlayerNotFoundException;

    /**
     * Recursively called in movePlayer - moving in a direction regardless of the direction where the player is facing
     * Tries to move the player once.
	 *
     * @param player
     *            player to be moved
	 * @param dir
	 *            direction to be moved
     * @return true if movement happened and the player did not fell off the board
     */
    boolean movePlayer(Player player, Direction dir) throws PlayerNotFoundException;

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


	/**
	 * Checks for players on a given position
	 *
	 * @param pos
	 * @return player on position, or potentially null if no player is present
	 */
	Player posToPlayer(Position pos);

	/**
	 * Returns the position of the given player
	 *
	 * @param player
	 * @return
	 */
	Position getPlayerPos(Player player);


	/**
	 * Initializes the thisRoundsCards containing all the cards in the correct order based upon the priority.
	 * Works by creating 5 PriorityQueues for each of the cards each player is holding and adding them to the
	 * thisRoundsCards queue containing all the cards. Also initializes the cardToPlayer hashmap mapping each
	 * card to a player.
	 */
	void initPhase();

	/**
	 * Tries to play the next card of the round. Interprets the actions of the card and
	 * calls the movePlayer appropriately.
	 *
	 * @return false if there is cards left to be played in thisRoundsCards or true if it played a card
	 */
	boolean playNextCard();
}
