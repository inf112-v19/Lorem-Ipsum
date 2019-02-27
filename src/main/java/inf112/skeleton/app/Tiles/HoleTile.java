package inf112.skeleton.app.Tiles;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Interfaces.IGameObject;

public class HoleTile extends Tile {
	private IGameObject[] gameObjects;
	private Direction direction;

	public HoleTile(IGameObject[] gameObjects, Direction direction) {
		this.gameObjects = gameObjects;
		this.direction = direction;
		super.spriteType = SpriteType.HOLE_TILE;
	}

}
