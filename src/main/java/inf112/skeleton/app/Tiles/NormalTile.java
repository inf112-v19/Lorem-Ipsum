package inf112.skeleton.app.Tiles;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.Interfaces.IGameObject;

public class NormalTile extends Tile {

	private final int SPRITE_X = 4;
	private final int SPRITE_Y = 0;
	private IGameObject[] gameObjects;
	private Direction direction;

	public NormalTile(IGameObject[] gameObjects, Direction direction) {
		this.gameObjects = gameObjects;
		this.direction = direction;
	}

	public int getSpriteX() {
		return SPRITE_X;
	}


	public int getSpriteY() {
		return SPRITE_Y;
	}

}
