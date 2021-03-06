package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Direction;
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
	 * Creates an array of all the players where the index is equal to the player index
	 *
	 * @return
	 */
	Player[] getAllPlayers();

	/**
	 * Method for spawning players on the board - checks if the tile is a SpawnTile and that is does not contain any
	 * players and either places the player on the spawn location or not.
	 *
	 * @param spawnPos
	 * @param player
	 * @return true if the player was placed on the board, or false if not
	 */
	boolean spawnPlayer(Position spawnPos, Player player);

	/**
	 * Tries to move the player in the given direction numberOfMoves times by calling the
	 * movePlayer(Player player, Direction dir) once and then sets the movementCount to numberOfMoves-1,
	 * the curPlayer to player and the moveDir to dir. This will make the doNextAction method handle the potentially
	 * remaining player movements. Method is called from the checkTile methods in the conveyorBelt tiles.
	 *
	 * @param player
	 *            player to be moved
	 * @param dir
	 *            direction to be moved
	 * @param numberOfMoves
	 *            number of times to be tried to move
	 */
	void movePlayer(Player player, Direction dir, int numberOfMoves);

    /**
	 * Tries to move the player once in the given direction, may start recursive call if player collision occurs.
	 * Handles the game logic regarding moving - not able to move through walls etc.
	 *
     * @param player
     *            player to be moved
	 * @param dir
	 *            direction to be moved
     * @return true if movement happened and the player did not fell off the board
     */
    boolean movePlayer(Player player, Direction dir);


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
	void initRound();

	/**
	 * Checks if there is any current card being handled(movementCount>0) and proceed calling the movePlayer accordingly
	 * or tries play the next card.
	 *
	 * @return true if it managed to do an action
	 */
	boolean doNextAction();

	/**
	 * Returns the card that is currently being played
	 *
	 * @return
	 */
	Card getCurCard();

	/**
	 * Returns the player currently being handled - the player which has moves pending
	 *
	 * @return
	 */
	Player getCurPlayer();

	/**
	 * Peeks what the next card in thisRoundsCards is - used for rendering "next card" in PendingCardsGUI
	 *
	 * @return
	 */
	Card peekNextCard();


	/**
	 * Returns the player corresponding to the next card - used used for rendering player corresponding to the
	 * "next card" in PendingCardsGUI
	 *
	 * @return
	 */
	Player getNextPlayer();

	/**
	 * Method for checking if the game has reached an end
	 *
	 * @return true if game is over, else false
	 */
	boolean isGameOver();

	/**
	 * Method for getting the winning player - used to display who has won the game in GameOverState
	 *
	 * @return the player who has won, or null if there are no winners
	 */
	Player getWinningPlayer();
}
