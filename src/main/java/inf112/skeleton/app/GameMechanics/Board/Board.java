package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardType;
import inf112.skeleton.app.GameMechanics.Direction;
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
	private Direction playerDir;

	private int height;
	private int width;

	public Board(String filename) {
		BoardBuilder builder = new BoardBuilder();
		tileMap = builder.buildBoard(filename);
		height = builder.getHeight();
		width = builder.getWidth();

		//creates two players and places them on the board and sets their backup - mostly for testing purposes
		Player player1 = new Player("0", Direction.EAST);
		Player player2 = new Player("1", Direction.EAST);
		player1.setBackup(new Position(1, 4));
		player2.setBackup(new Position(1, 11));
		playerPositions.put(player1, new Position(1, 4));
		playerPositions.put(player2, new Position(1, 11));

	}

	public Board(String filename, int numberOfPlayers) {
		this(filename);
		for (int i = 0; i < numberOfPlayers; i++) {
			playerPositions.put(new Player(Integer.toString(i), Direction.NORTH), new Position(-1, -1));
		}
	}

	/**
	 * Method used for testing purposes - maybe not needed for finished program
	 * (ignores all exceptions and relies on correct usage)
	 *
	 * @param player
	 * @param pos
	 */
	public void placePlayerOnPos(Player player, Position pos) {
		playerPositions.put(player, pos);
	}

	@Override
	public Player[] getAllPlayers() {
		Player[] players = new Player[playerPositions.size()];

		for (Player p : playerPositions.keySet()) {
			int indexOfP = Integer.parseInt(p.getPlayerID());
			players[indexOfP] = p;
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
	public GameObject[] getGameObject(Position pos) {
		if (!isValidPos(pos)) {
			//TODO - handle invalid position exception
		}

		return tileMap.get(pos).getGameObjects();
	}

	@Override
	public boolean movePlayer(Player player, int numberOfMoves) {
		return movePlayer(player, player.getDirection(), numberOfMoves);
	}

	@Override
	public boolean movePlayer(Player player, Direction dir, int numberOfMoves) {
		for (int i = 0; i < numberOfMoves; i++) {
			if (!movePlayer(player, dir)) {
				return false;
			}
		}

		// if this happens it means all previous calls of movePlayer returned true
		return true;
	}

	@Override
	public boolean movePlayer(Player player, Direction dir) {
		if(!playerPositions.containsKey(player)) {
			// TODO - Handle custom PLayerNotFoundException
			//throw new PlayerNotFoundException("Tried to move player that was not found in playerPositions");
		}

		//if the player has fallen off the board no movement happens
		if (!playerIsOnTheBoard(player)) {
			return false;
		}

		Position curPos = playerPositions.get(player);
		Position newPos = curPos.getNeighbour(dir);
		Tile curTile = tileMap.get(curPos);
		boolean fellOffTheBoard = false;

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
							fellOffTheBoard = checkForHole(player, newPos);
                        }
                    }

                    //no player in direction trying to move - moves player to newPos
                    else {
						fellOffTheBoard = checkForHole(player, newPos);
                    }
                }
            }
            //player walks off the board
            else {
				playerFellOffTheBoard(player);
				fellOffTheBoard = true;
            }
        }

        //returns true if the player position has changed and the player did not fell off the board
        return !curPos.equals(playerPositions.get(player)) && !fellOffTheBoard;
	}

	@Override
	public void setGameObject(Position pos, GameObject gameObject) {
		if (!isValidPos(pos)) {
			//TODO - handle invalid position exception
		}

		tileMap.get(pos).addGameObject(gameObject);
	}

	@Override
	public void removeObject(Position pos, GameObject gameObject) {
		if (!isValidPos(pos)) {
			//TODO - handle invalid position exception
		}
		if (!tileMap.get(pos).removeGameObject(gameObject)) {
		    //TODO - handle case where given GameObject was not present on tile
        }

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
        for (Player player : playerPositions.keySet()) {
            if (playerPositions.get(player).equals(pos)){
            	return player;
            }
        }
        return null;
    }

    @Override
	public Position getPlayerPos(Player player) {
		return playerPositions.get(player);
	}

	@Override
	public void initPhase() {
		PriorityQueue<Card>[] phaseQueues = new PriorityQueue[5];
		//init phaseQueues
		for (int i = 0; i < 5; i++) {
			phaseQueues[i] = new PriorityQueue<>();
		}

		for (Player player : playerPositions.keySet()) {
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
			movePlayer(curPlayer, playerDir);
			return true;
		}
		else {
			return playNextCard();
		}
	}

	/**
	 * Tries to play the next card of the round. Interprets the actions of the card and
	 * calls the movePlayer appropriately and increases the movementCount if the card contains multiple moves
	 *
	 * @return false if there is cards left to be played in thisRoundsCards or true if it played a card
	 */
	private boolean playNextCard() {
		if (thisRoundsCards.isEmpty()) {
			resetRound();
			return false;
		}

		curCard = thisRoundsCards.poll();
		curPlayer = cardToPlayer.get(curCard);

		//if player has fallen off the board - skip the card
		if (!playerIsOnTheBoard(curPlayer)){
			playNextCard();
		}

		CardType curCardType = curCard.getCardType();
		int numRotation = curCardType.getRotation();
		//if card is rotate card do the rotation and return
		if (numRotation != 0){
			curPlayer.turnPlayer(numRotation);
			return true;
		}

		int movement = curCardType.getMovement();
		playerDir = curPlayer.getDirection();

		switch (movement) {
			case -1:
				movePlayer(curPlayer, playerDir.oppositeDirection());
				break;
			case 1:
				movePlayer(curPlayer, playerDir);
				break;
			case 2:
				movePlayer(curPlayer, playerDir);
				movementCount = 1;
				break;
			case 3:
				movePlayer(curPlayer, playerDir);
				movementCount = 2;
				break;
		}

		return true;
	}

	/**
	 * Resets the round by setting every players state to not ready, respawn players who has fallen off the board,
	 * and also calls the checkTile method for the players still on the board - checkTile method will execute the
	 * correct action according to the tile-type and gameObjects on the tile(movePlayer, set backup, take damage etc.)
	 */
	private void resetRound() {
		for (Player player : playerPositions.keySet()) {
			player.setNotReady();

			//if player is not on the board - respawn at backup
			if (!playerIsOnTheBoard(player)){
				playerPositions.put(player, player.getBackup());
			}
			else{
				Position playerPos = playerPositions.get(player);
				Tile playerTile = tileMap.get(playerPos);
				playerTile.checkTile(this, player);
			}
		}
	}

	/**
	 * Handles a player walking off the board - temporally places player on (-1, -1)
	 *
	 * @param player
	 */
	private void playerFellOffTheBoard(Player player) {
    	player.destroyPlayer();

    	//skips any remaining moves for the current card
		movementCount = 0;

    	if (player.getLives()>0) {
    		playerPositions.put(player, new Position(-1,-1));
		}
    	else {
			//TODO - handle dead player
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
	private boolean checkForHole(Player player, Position newPos) {
		Tile newTile = tileMap.get(newPos);

		if (newTile instanceof HoleTile) {
			playerFellOffTheBoard(player);
			return true;
		}else{
			playerPositions.put(player, newPos);
			return false;
		}
	}

	/**
	 * Method for checking if the player has fallen off the board - player position equals (-1,-1)
	 *
	 * @param player
	 * @return true if player has not fallen off the board
	 */
	private boolean playerIsOnTheBoard(Player player) {
		return !playerPositions.get(player).equals(new Position(-1,-1));
	}

}
