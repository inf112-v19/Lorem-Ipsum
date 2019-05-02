package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardType;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.Tiles.*;
import inf112.skeleton.app.Interfaces.IBoard;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;

import java.util.*;

public class Board implements IBoard {
	private HashMap<Position, Tile> tileMap;
	private HashMap<Player, Position> playerPositions = new HashMap<>();
	private HashMap<Card, Player> cardToPlayer = new HashMap<>();
	private Queue<Card> thisRoundsCards = new LinkedList<>();

	private int phaseCardCount = 0;
	private int phasePlayerCount = 0;

	private Card curCard;
	private int movementCount = 0;
	private Player curPlayer;
	private Direction moveDir;

	private Player winningPlayer = null;
	private boolean gameOver = false;

	private int height;
	private int width;

	private boolean laserStatus = false;

	public Board(String filename) {
		BoardBuilder builder = new BoardBuilder();
		tileMap = builder.buildBoard(filename);
		height = builder.getHeight();
		width = builder.getWidth();
	}

	/**
	 * Method used to initialize players on the board - places players on the given position.
	 * Only used for testing purposes (in setup and teardown methods).
	 *
	 * @param player
	 * @param pos
	 */
	public void placePlayerOnPos(Player player, Position pos) {
		playerPositions.put(player, pos);
	}

	@Override
	public boolean spawnPlayer(Position spawnPos, Player player) {
		Tile tile = tileMap.get(spawnPos);
		boolean posContainsPlayer = (posToPlayer(spawnPos) != null);

		if ((tile instanceof SpawnTile) && !posContainsPlayer) {
			playerPositions.put(player, spawnPos);
			player.setBackup(spawnPos);
			System.out.println(player.getIndex() + " spawned on " + spawnPos);
			return true;
		}

		return false;
	}

	@Override
	public Player[] getAllPlayers() {
		Player[] players = new Player[playerPositions.size()];

		for (Player p : playerPositions.keySet()) {
			players[p.getIndex()] = p;
		}
		return players;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void movePlayer(Player player, Direction dir, int numberOfMoves) {
		movementCount = numberOfMoves-1;
		curPlayer = player;
		moveDir = dir;

		movePlayer(player, dir);

		//if the player fell off the board - skip remaining moves on the card
		if (!player.onBoardCheck()) {
			movementCount = 0;
		}
	}

	@Override
	public boolean movePlayer(Player player, Direction dir) {
		System.out.println("Tried to move player" + player.getPlayerName() + " " + dir);

		//if the player has fallen off the board no movement happens - should not happen
		if (!player.onBoardCheck()) {
			return false;
		}

		Position curPos = playerPositions.get(player);
		Position newPos = curPos.getNeighbour(dir);
		Tile curTile = tileMap.get(curPos);

		switch (curTile.isPossibleToMoveDir(curPos, this, dir)){
			//nothing obstructing the move - proceed
			case 0:
				checkForHole(player, newPos);
				break;

			//player collision occurred - start recursive calling
			case 2:
				Player otherPlayer = posToPlayer(newPos);
				//proceed moving if the colliding player moved or stand still if no movement happened
				if (movePlayer(otherPlayer, dir)){
					checkForHole(player, newPos);
				}
				break;

			//player fell off the board
			case 3:
				playerFellOffTheBoard(player, newPos);
				break;
		}

		//returns true if the player position has changed
		return !curPos.equals(playerPositions.get(player));
	}


	@Override
	public boolean isValidPos(Position pos) {
		return ((pos.getX()<width && pos.getX()>=0) && (pos.getY()<height && pos.getY()>=0));
	}

	@Override
	public Tile getTile(Position pos) {
		return tileMap.get(pos);
	}

	@Override
	public Player posToPlayer(Position pos) {
        for (Map.Entry<Player,Position> playerPositionEntry : playerPositions.entrySet()) {
            if (playerPositionEntry.getValue().equals(pos)){
            	return playerPositionEntry.getKey();
            }
        }
        return null;
    }

    @Override
	public Position getPlayerPos(Player player) {
		return playerPositions.get(player);
	}

	@Override
	public void initRound() {
		//init phaseQueues and reset phasePlayerCount(number of players which play cards this round)
		phasePlayerCount = 0;
		PriorityQueue<Card>[] phaseQueues = new PriorityQueue[5];
		for (int i = 0; i < 5; i++) {
			phaseQueues[i] = new PriorityQueue<>();
		}

		for (Player player : playerPositions.keySet()) {

			//players in power down gets health reset and skips cards
			if (player.getPowerDown() == 1) {
				player.resetDamage();
				continue;
			}

			//skips dead players
			if (player.isDead()) {
				continue;
			}

			Card[] playerCards = player.getCardSequence();

			for (int i = 0; i < 5; i++) {
				Card curCard = playerCards[i];
				cardToPlayer.put(curCard, player);
				phaseQueues[i].add(curCard);
			}
			phasePlayerCount++;
		}

		for (int i = 0; i < 5; i++) {
			for (Card card : phaseQueues[i]) {
				thisRoundsCards.add(card);
			}
		}
	}

	@Override
	public boolean doNextAction() {
		if (movementCount>0) {
			movePlayer(curPlayer, moveDir, movementCount);
			return true;
		}
		else {
			return playNextCard();
		}
	}

	@Override
	public Card getCurCard(){
		return curCard;
	}

	@Override
	public Player getCurPlayer() {
		return curPlayer;
	}

	@Override
	public Card peekNextCard() {
		return thisRoundsCards.peek();
	}

	@Override
	public Player getNextPlayer() {
		return cardToPlayer.get(thisRoundsCards.peek());
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	@Override
	public Player getWinningPlayer() {
		return winningPlayer;
	}

	/**
	 * Tries to play the next card of the round. Interprets the actions of the card and
	 * calls the movePlayer appropriately and increases the movementCount if the card contains multiple moves.
	 * Also checks whether all the cards of this phase has been played or if all the cards of the round has been played
	 * and calls either the endRound or endPhase method accordingly.
	 *
	 * @return false if there is cards left to be played in thisRoundsCards or true if it played a card
	 */
	private boolean playNextCard() {
		//all cards of the round has been played - round is over, endRound is called
		if (thisRoundsCards.isEmpty()) {
			return endRound();
		}

		//all cards of the phase has been played - do endPhase method
		else if (phaseCardCount == phasePlayerCount){
			endPhase();
			return true;
		}

		curCard = thisRoundsCards.poll();
		curPlayer = cardToPlayer.get(curCard);
		phaseCardCount++;

		//if player has fallen off the board - skip the card
		if (!curPlayer.onBoardCheck()){
			return playNextCard();
		}

		CardType curCardType = curCard.getCardType();
		int numRotation = curCardType.getRotation();
		//if card is rotate card do the rotation and return
		if (numRotation != 0){
			curPlayer.turnPlayer(numRotation);
			System.out.println("Rotated player " + curPlayer.getPlayerName() + " " + numRotation + " times");
			return true;
		}

		int movement = curCardType.getMovement();
		moveDir = curPlayer.getDirection();

		switch (movement) {
			case -1:
				movePlayer(curPlayer, moveDir.oppositeDirection());
				break;
			case 1:
				movePlayer(curPlayer, moveDir);
				break;
			case 2:
				movePlayer(curPlayer, moveDir, 2);
				break;
			case 3:
				movePlayer(curPlayer, moveDir, 3);
				break;
			default:
				//should not happen considering earlier checking if card was a rotation card
		}

		//returns true since we were able to play a card
		return true;
	}

	/**
	 * Handles the final parts of the phase:
	 * - does all the tile actions(movement and rotation of the players)
	 * - toggles lasers on and then off after 1 update (both laser bases and players shooting)
	 * - updates gameOver boolean
	 * - resets phaseCardCount so the cards for the next phase can be played next update (set to 0)
	 *
	 * @return false if the phase is not yet over (moves pending or laser need to stay on 1 update before toggling off),
	 * else true if all the actions in the endPhase has happened and the phase is over
	 */
	private boolean endPhase() {
		//do tile actions only if the laser has not yet been toggled - return if false if there are moves pending
		if (!laserStatus && !doTileActions()) {
			return false;
		}

		//turns on lasers if off
		if (!laserStatus) {
			toggleLasers();
			return false;
		}

		//toggles off the lasers after 1 update
		toggleLasers();

		gameOver = checkForGameOver();
		phaseCardCount = 0;

		//phase is over
		return true;
	}

	/**
	 * Handles the end of the round actions:
	 * - final calls of the endPhase (2 times since toggleLasers takes 2 updates)
	 * - respawn players who fell off the board during the round
	 * - updating checkpoints and collections of flags
	 * - power down, sets destroyed players to powerDown = 3, resets power down for players currently in
	 * 	 power down and sets powerDown = 1 for players pending power down(powerDown currently equals 2)
	 * - sets all players to not ready
	 *
	 * @return false if the round is over, or true if the the endPhase still needs to be called once more
	 */
	private boolean endRound() {
		if (!endPhase()) {
			return true;
		}

		for (Map.Entry<Player,Position> playerPositionEntry : playerPositions.entrySet()) {
			Player player = playerPositionEntry.getKey();
			Position playerPos = playerPositionEntry.getValue();

			//respawn dead players
			if (!player.onBoardCheck() && !player.isDead()){
				System.out.println("Player " + player.getPlayerName() + " respawned");
				playerPositions.put(player, player.getBackup());
				player.setOnTheBoard(true);

				//if power down was requested for next round and player was destroyed - set power down to 3(cancel option)
				if (player.getPowerDown() == 2) {
					player.setPowerDown(3);
				}
			}

			//handle checkpoints for player on the board
			else {
				Tile playerTile = tileMap.get(playerPos);

				if (playerTile instanceof NormalTile ||
					playerTile instanceof OptionsTile ||
					playerTile instanceof RepairTile) {

					playerTile.checkTile(this, player);
				}
			}


			//players currently in power down is reset
			if (player.getPowerDown() == 1) {
				player.setPowerDown(0);
			}
			//players currently pending power down is set to power down
			else if (player.getPowerDown() == 2) {
				player.setPowerDown(1);
			}

			//finally set player to not ready - boolean is used in the card state (players choosing cards)
			player.setNotReady();
		}

		//round is over
		return false;
	}


	/**
	 * Method for checking if the game is over after the round has finished - checks if any player has collected all
	 * the flags, or if there is less than 2 players alive
	 *
	 * @return true if game is over or false if not
	 */
	private boolean checkForGameOver() {
		int alivePlayers = 0;

		for (Player player : playerPositions.keySet()) {

			//player has collected all the flags - game over, player has won
			if (player.numberOfFlagsCollected() == playerPositions.size()) {
				System.out.println(player.getPlayerName() + " has won the game");
				winningPlayer = player;
				return true;
			}
			if (!player.isDead()) {
				alivePlayers++;
			}
		}

		//if no players is alive - game over, else game is still in progress
		return alivePlayers == 0;
	}

	/**
	 * Calls the checkTile method on all tiles players are standing on regarding movement and rotation
	 *
	 * @return true if it managed to do all tile actions, or false if there are moves pending
	 */
	private boolean doTileActions() {
		for (Map.Entry<Player,Position> playerPositionEntry : playerPositions.entrySet()) {
			Player player = playerPositionEntry.getKey();
			Position playerPos = playerPositionEntry.getValue();

			if (player.isReady() && player.onBoardCheck()) {
				Tile playerTile = tileMap.get(playerPos);

				//only do tile action for movement and rotation
				if (playerTile instanceof ConveyorBeltTile ||
					playerTile instanceof DoubleConveyorBeltTile ||
					playerTile instanceof RotationRightTile ||
					playerTile instanceof RotationLeftTile) {

					playerTile.checkTile(this, player);
				}
			}
			player.setNotReady();

			//if the current player has movement pending - return false
			if (movementCount>0){
				return false;
			}
		}

		//since the player ready boolean is used to keep track of which players has been handled it is
		//reset for next phase - all players are set to ready once more
		for (Player player : playerPositions.keySet()) {
			player.setReady();
		}

		//no movement pending - return true
		return true;
	}

	/**
	 * Toggle all the lasers on the board and deals damage to players hit by laser if toggled on or removes the lasers
	 * from the board if toggled off also handles players toggling lasers(shooting or removing shot laser)
	 */
	private void toggleLasers() {
		laserStatus = !laserStatus;

		for (Map.Entry<Position, Tile> tileMapEntry : tileMap.entrySet()) {
			Tile tile = tileMapEntry.getValue();
			Position tilePos = tileMapEntry.getKey();

			if (tile instanceof DoubleLaserBaseTile) {
				tile.toggleLaser(tilePos, this, laserStatus, true);
			}
			else if (tile instanceof LaserBaseTile) {
				tile.toggleLaser(tilePos, this, laserStatus, false);
			}
		}

		for (Map.Entry<Player, Position> playerPositionEntry : playerPositions.entrySet()) {
			Player player = playerPositionEntry.getKey();
			Position playerPos = playerPositionEntry.getValue();

			//only calls the toggleLaser if the player is alive or if toggling off the laser
			if (!player.isDead() || !laserStatus){
				player.toggleLaser(playerPos, this, laserStatus);
			}
		}
	}

	/**
	 * Handles a player walking off the board - calls players setOnTheBoard(false)
	 *
	 * @param player
	 */
	private void playerFellOffTheBoard(Player player, Position newPos) {
    	player.destroyPlayer();

		playerPositions.put(player, newPos);
		player.setOnTheBoard(false);

    	if (!player.isDead()) {
			System.out.println("Player" + player.getPlayerName() + " fell off the board");
		}
    	else {
			System.out.println("Player" + player.getPlayerName() + " fell off the board and died");
		}
	}

	/**
	 * Checks if tile player is moving to is a HoleTile and potentially moves player to it if not,
	 * else calls playerFellOffTheBoard
	 *
	 * @param player
	 * @param newPos
	 * @return returns true if the tile is a hole
	 */
	private void checkForHole(Player player, Position newPos) {
		Tile newTile = tileMap.get(newPos);

		if (newTile instanceof HoleTile) {
			playerFellOffTheBoard(player, newPos);
		}else{
			playerPositions.put(player, newPos);
		}
	}

	/**
	 * @return returns the boards tilemap
	 */
	public HashMap<Position,Tile> getTileMap(){
		return tileMap;
	}

}
