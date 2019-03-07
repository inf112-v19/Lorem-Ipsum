package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Cards.Card;
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

	private int height;
	private int width;

	public Board(String filename) {
		BoardBuilder builder = new BoardBuilder();
		tileMap = builder.buildBoard(filename);
		height = builder.getHeight();
		width = builder.getWidth();

		//creates two players and places them on the board - mostly for testing purposes
		Player player1 = new Player("0", Direction.EAST);
		Player player2 = new Player("1", Direction.EAST);
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
	public boolean movePlayer(Player player) throws PlayerNotFoundException {
		return movePlayer(player, player.getDirection());
	}

	@Override
	public boolean movePlayer(Player player, Direction dir) throws PlayerNotFoundException {
		if(!playerPositions.containsKey(player)) {
			throw new PlayerNotFoundException("Tried to move player that was not found in playerPositions");
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
				playerFellOffTheBoard(player);
            }
        }

        //returns true if the player position has changed
        return !curPos.equals(playerPositions.get(player));
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
	public boolean initPhase() {
		PriorityQueue<Card>[] phaseQueues = new PriorityQueue[5];

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

		return true;
	}

	public boolean playNextCard(){
		if (thisRoundsCards.isEmpty()) {
			return false;
		}
		//TODO - handle playing the card
		return true;
	}
    

	/**
	 * Handles a player walking off the board
	 *
	 * @param player
	 */
	private void playerFellOffTheBoard(Player player) {
    	player.destroyPlayer();

    	if (player.getLives()>0) {
    		playerPositions.put(player, player.getBackup());
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
	 */
	private void checkForHole(Player player, Position newPos) {
		Tile newTile = tileMap.get(newPos);

		if (newTile instanceof HoleTile) {
			playerFellOffTheBoard(player);
		}else{
			playerPositions.put(player, newPos);
		}
	}

	/**
	 * Iterates over every player and calls the checkTile method for the tile they are standing on -
	 * checkTile method will execute the correct action according to the tile-type(movePlayer etc.)
	 */
	private void checkAllPlayers() throws PlayerNotFoundException {
		for (Player player : playerPositions.keySet()) {
			Position playerPos = playerPositions.get(player);
			Tile playerTile = tileMap.get(playerPos);
			playerTile.checkTile(this, player);
		}
	}

}
