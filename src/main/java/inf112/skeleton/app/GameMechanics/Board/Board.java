package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardType;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.Tiles.HoleTile;
import inf112.skeleton.app.Interfaces.IBoard;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

import java.util.*;

public class Board implements IBoard {
	private HashMap<Position, Tile> tileMap;
	private HashMap<Player, Position> playerPositions = new HashMap<>();
	private HashMap<Card, Player> cardToPlayer = new HashMap<>();
	private Queue<Card> thisRoundsCards = new LinkedList<>();

	private Card curCard;
	private int movementCount = 0;
	private Player curPlayer;
	private Direction moveDir;

	private int height;
	private int width;

	public Board(String filename) {
		BoardBuilder builder = new BoardBuilder();
		tileMap = builder.buildBoard(filename);
		height = builder.getHeight();
		width = builder.getWidth();

		//creates two players and places them on the board and sets their backup - mostly for testing purposes
		Player player1 = new Player(0,"Player 1", Direction.EAST);
		Player player2 = new Player(1,"Player 2", Direction.EAST);
		player1.setBackup(new Position(1, 4));
		player2.setBackup(new Position(1, 11));
		playerPositions.put(player1, new Position(1, 4));
		playerPositions.put(player2, new Position(1, 11));


	}

	/**
	 * Method used to initialize players on the board - places players on the given position.
	 * Also used for testing purposes (in setup and teardown methods).
	 *
	 * @param player
	 * @param pos
	 */
	public void placePlayerOnPos(Player player, Position pos) {
		if (!isValidPos(pos)) {
			//TODO - handle invalid position exception
		}

		playerPositions.put(player, pos);
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
	public boolean movePlayer(Player player, Direction dir, int numberOfMoves) {
		movementCount = numberOfMoves-1;
		curPlayer = player;
		moveDir = dir;

		return movePlayer(player, dir);
	}

	@Override
	public boolean movePlayer(Player player, Direction dir) {
		System.out.println("Tried to move player" + player.getPlayerID() + " " + dir);
		if(!playerPositions.containsKey(player)) {
			// TODO - Handle custom PLayerNotFoundException
			//throw new PlayerNotFoundException("Tried to move player that was not found in playerPositions");
		}

		//if the player has fallen off the board no movement happens(edge case, should not happen)
		if (!player.onBoardCheck()) {
			return false;
		}

		Position curPos = playerPositions.get(player);
		Position newPos = curPos.getNeighbour(dir);
		Tile curTile = tileMap.get(curPos);

		//if tile currently standing on has no wall blocking the player - proceed
        if (!curTile.hasWallInDir(dir)){
            if (isValidPos(newPos)) {
                Tile newTile = tileMap.get(newPos);
                //if tile walking on to has no wall blocking the player - proceed
                if (!newTile.hasWallInDir(dir.oppositeDirection())) {
                    Player otherPlayer = posToPlayer(newPos);

                    //player collision occurred
                    if (otherPlayer != null){
                        //proceed moving if the colliding player moved or stand still if no movement happened
                        if (movePlayer(otherPlayer, dir)){
							checkForHole(player, newPos);
                        }
                    }

                    //no player in direction trying to move - moves player to newPos
                    else {
						checkForHole(player, newPos);
                    }
                }
            }
            //player walks off the board
            else {
				playerFellOffTheBoard(player, newPos);
            }
        }

        //returns true if the player position has changed and the player did not fell off the board
        return !curPos.equals(playerPositions.get(player)) && !player.onBoardCheck();
	}


	@Override
	public boolean isValidPos(Position pos) {
		return ((pos.getX()<width && pos.getX()>=0) && (pos.getY()<height && pos.getY()>=0));
	}

	@Override
	public Tile getTile(Position pos) {
		if (!isValidPos(pos)) {
			//TODO - handle invalid position exception
		}

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
		PriorityQueue<Card>[] phaseQueues = new PriorityQueue[5];
		//init phaseQueues
		for (int i = 0; i < 5; i++) {
			phaseQueues[i] = new PriorityQueue<>();
		}

		for (Player player : playerPositions.keySet()) {
			if (player.isDead()) {
				continue;
			}

			Card[] playerCards = player.getCardSequence();

			for (int i = 0; i < 5; i++) {
				Card curCard = playerCards[i];
				cardToPlayer.put(curCard, player);
				phaseQueues[i].add(curCard);
			}
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
			movementCount--;
			movePlayer(curPlayer, moveDir);
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

	/**
	 * Tries to play the next card of the round. Interprets the actions of the card and
	 * calls the movePlayer appropriately and increases the movementCount if the card contains multiple moves
	 *
	 * @return false if there is cards left to be played in thisRoundsCards or true if it played a card
	 */
	private boolean playNextCard() {
		if (thisRoundsCards.isEmpty()) {
			return endRound();
		}

		curCard = thisRoundsCards.poll();
		curPlayer = cardToPlayer.get(curCard);

		//if player has fallen off the board - skip the card
		if (!curPlayer.onBoardCheck()){
			return playNextCard();
		}

		CardType curCardType = curCard.getCardType();
		int numRotation = curCardType.getRotation();
		//if card is rotate card do the rotation and return
		if (numRotation != 0){
			curPlayer.turnPlayer(numRotation);
			System.out.println("Rotated player" + curPlayer.getPlayerID() + " " + numRotation + " times");
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
	 * Resets the round by setting every players state to not ready, respawn players who has fallen off the board,
	 * and also calls the checkTile method for the players still on the board - checkTile method will execute the
	 * correct action according to the tile-type and gameObjects on the tile(movePlayer, set backup).
	 * Finally every player standing on a tile with lasers at the end of the round take damage.
	 *
	 * @return true if checkTile increased the movementCount from 0 - if moves are pending
	 */
	private boolean endRound() {
		if (doTileActions() == false) {
			return true;
		}

		turnOnLasers();
		respawnPlayers();
		checkForGameOver();

		//round is over
		return false;
	}

	/**
	 * Method for checking if the game is over after the round has finished - checks if any player has collected all
	 * the flags, or if there is less than 2 players alive
	 */
	private void checkForGameOver() {
		int alivePlayers = 0;

		for (Player player : playerPositions.keySet()) {
			if (player.numberOfFlagsCollected() == playerPositions.size()) {
				//TODO - handle player winning the game
			}
			if (!player.isDead()) {
				alivePlayers++;
			}
		}

		if (alivePlayers<2) {
			//TODO - handle game over
		}
	}

	/**
	 * Calls the checkTile method on all tiles players are standing on
	 *
	 * @return true if it managed to do all tile actions, or false if there are moves pending
	 */
	private boolean doTileActions() {
		for (Map.Entry<Player,Position> playerPositionEntry : playerPositions.entrySet()) {
			Player player = playerPositionEntry.getKey();
			Position playerPos = playerPositionEntry.getValue();

			if (player.isReady() && player.onBoardCheck()) {
				Tile playerTile = tileMap.get(playerPos);
				playerTile.checkTile(this, player);
			}
			player.setNotReady();

			//if the current player has movement pending - return false
			if (movementCount>0){
				return false;
			}
		}

		//no movement pending - return true
		return true;
	}

	/**
	 * Respawn all the players who has fallen off the board and puts them on their backup
	 */
	private void respawnPlayers() {
		for (Player player : playerPositions.keySet()) {
			if (!player.onBoardCheck() && !player.isDead()){
				System.out.println("Player" + player.getPlayerID() + " respawned");
				playerPositions.put(player, player.getBackup());
				player.setOnTheBoard(true);
			}
		}
	}

	/**
	 * Deals damage to all players standing on tiles containing lasers
	 */
	private void turnOnLasers() {
		for (Map.Entry<Player,Position> playerPositionEntry : playerPositions.entrySet()) {
			Player curPlayer = playerPositionEntry.getKey();
			Position curPlayerPos = playerPositionEntry.getValue();

			if (curPlayer.onBoardCheck()) {
				Tile playerTile = tileMap.get(curPlayerPos);
				playerTile.laserCheck(curPlayer);
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

    	//skips any remaining moves for the current card
		movementCount = 0;
		playerPositions.put(player, newPos);
		player.setOnTheBoard(false);

    	if (!player.isDead()) {
			System.out.println("Player" + player.getPlayerID() + " fell off the board");
		}
    	else {
			System.out.println("Player" + player.getPlayerID() + " fell off the board and died");
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


	public Player getCurPlayer() {
		return curPlayer;
	}

	public Card peekNextCard() {
		return thisRoundsCards.peek();
	}

	public Player getNextPlayer() {
		return cardToPlayer.get(thisRoundsCards.peek());
	}
}
