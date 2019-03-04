package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.Interfaces.IBoard;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;

import java.util.HashMap;

public class Board implements IBoard {
	private HashMap<Position, Tile> tileMap;
	private HashMap<Player, Position> playerPositions = new HashMap<>();
	private int height;
	private int width;

	public Board(String filename) {
		BoardBuilder builder = new BoardBuilder();
		tileMap = builder.buildBoard(filename);
		height = builder.getHeight();
		width = builder.getWidth();
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
	public boolean movePlayer(Player player) {
		return movePlayer(player, player.getDirection());
	}

	@Override
	public boolean movePlayer(Player player, Direction dir) {
		if(!playerPositions.containsKey(player)) {
			//TODO - handle player not found exception
		}

		Position curPos = playerPositions.get(player);
		Position newPos = calcNewPos(curPos, dir);
		Tile curTile = tileMap.get(curPos);

		//if tile currently standing on has no wall blocking the player - proceed
        if (!curTile.hasWallInDir(dir)){
            if (isValidPos(newPos)) {
                Tile newTile = tileMap.get(newPos);

                //if tile walking on to has no wall blocking the player - proceed
                if (!newTile.hasWallInDir(dir.oppositeDirection())) {
                    Player otherPlayer = posToPlayer(newPos);

                    if (otherPlayer != null){
                        //proceed moving if the colliding player moved or stand still if no movement happened
                        if (movePlayer(otherPlayer, dir)){
							//TODO - handle walking on a hole tile
							playerPositions.put(player, newPos);
                        }
                    }

                    //no player in direction trying to move - moves player to newPos
                    else {
						//TODO - handle walking on a hole tile
						playerPositions.put(player, newPos);
                    }
                }
            }
            //player walks off the board
            else {
            	//TODO - handle player loosing life when falling off the board
				playerPositions.put(player, player.getBackup());
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

    /**
     * Checks for players on a given position
     *
     * @param pos
     * @return player on position, or potentially null if no player is present
     */
	private Player posToPlayer(Position pos) {
        for (Player player : playerPositions.keySet()) {
            if (playerPositions.get(player).equals(pos)){
                return player;
            }
        }
        return null;
    }

    /**
     * Calculates the position in the given direction for a current position
     *
     * @param curPos
     * @param dir
     * @return
     */
    private Position calcNewPos(Position curPos, Direction dir) {
        switch (dir) {
            case NORTH:
                return new Position(curPos.getX(), curPos.getY()-1);
            case SOUTH:
                return  new Position(curPos.getX(), curPos.getY()+1);
            case EAST:
                return  new Position(curPos.getX()+1, curPos.getY());
            case WEST:
                return new Position(curPos.getX()-1, curPos.getY());
            default:
                return curPos;
        }
    }

}