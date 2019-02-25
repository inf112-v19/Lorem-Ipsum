package inf112.skeleton.app.GUI;

import inf112.skeleton.app.Interfaces.IBoard;
import inf112.skeleton.app.Position;
import inf112.skeleton.app.Tile;

import java.util.HashMap;

public class Board implements IBoard {
	private HashMap<Position, Tile> tileMap;
	//private HashMap<Player, Position> playerPositions = new HashMap<>();
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
	public void setGameObject(Position pos, Object element) {

	}

	@Override
	public Object getGameObject(Position pos) {
		return null;
	}

	@Override
	public void movePlayer(Position pos) {

	}

	@Override
	public void removeObject(Position pos, Object element) {

	}

	@Override
	public boolean isValidPos(Position pos) {
		return ((pos.getX()<width && pos.getX()>=0) && (pos.getY()<height && pos.getY()>=0));
	}

	@Override
	public Tile getTile(Position pos) {
		if (!isValidPos(pos)){
			System.err.println("Invalid position");
			System.exit(1);
		}

		return tileMap.get(pos);
	}
}
