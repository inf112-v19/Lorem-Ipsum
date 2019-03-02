package inf112.skeleton.app.Board;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GameObjects.GameObject;
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
		if (!isValidPos(pos)){
			//TODO - handle invalid position exception
		}

		return tileMap.get(pos).getGameObjects();
	}

	@Override
	public void movePlayer(Player player, Direction dir) {
		if(!playerPositions.containsKey(player)) {
			//TODO - handle player not found exception
		}


	}

	@Override
	public void setGameObject(Position pos, GameObject gameObject) {
		if (!isValidPos(pos)){
			//TODO - handle invalid position exception
		}

		tileMap.get(pos).addGameObject(gameObject);
	}

	@Override
	public void removeObject(Position pos, GameObject gameObject) {
		if (!isValidPos(pos)){
			//TODO - handle invalid position exception
		}
		tileMap.get(pos).removeGameObject(gameObject);
	}

	@Override
	public boolean isValidPos(Position pos) {
		return ((pos.getX()<width && pos.getX()>=0) && (pos.getY()<height && pos.getY()>=0));
	}

	@Override
	public Tile getTile(Position pos) {
		if (!isValidPos(pos)){
			//TODO - handle invalid position exception
		}

		return tileMap.get(pos);
	}
}
