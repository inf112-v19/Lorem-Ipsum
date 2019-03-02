package inf112.skeleton.app.Board;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GameObjects.GameObject;
import inf112.skeleton.app.GameObjects.Wall;
import inf112.skeleton.app.Interfaces.IBoard;
import inf112.skeleton.app.Player;
import inf112.skeleton.app.Position;
import inf112.skeleton.app.Tiles.Tile;

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
	public boolean movePlayer(Player player, Direction dir) {
		if(!playerPositions.containsKey(player)) {
			//TODO - handle player not found exception
		}

		Position curPos = playerPositions.get(player);
		Position newPos;

		switch (dir) {
            case NORTH:
                newPos = new Position(curPos.getX(), curPos.getY()-1);
                break;
            case SOUTH:
                newPos = new Position(curPos.getX(), curPos.getY()+1);
                break;
            case EAST:
                newPos = new Position(curPos.getX()+1, curPos.getY());
                break;
            case WEST:
                newPos = new Position(curPos.getX()-1, curPos.getY());
                break;
             default:
                 newPos = curPos;
        }

        if (isValidPos(newPos)) {
            Player otherPlayer = posToPlayer(newPos);
            if (otherPlayer != null){
                if (movePlayer(otherPlayer, dir)){
                	//TODO - handle player collision (recursively call movePlayer until either a movement happens or stand still)
				}
            }
        }
        // potentially walking off the board
        else {
            Tile curTile = tileMap.get(curPos);
            Wall wall = new Wall(dir);
            if (curTile.hasGameObject(wall) != -1){
                //TODO - handle player walking off the board

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

}
