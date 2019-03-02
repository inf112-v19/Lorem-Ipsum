package inf112.skeleton.app.Tiles;

import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.GameObjects.GameObject;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.Interfaces.ITile;
import inf112.skeleton.app.Position;

public abstract class Tile implements ITile {

	public SpriteType spriteType;
	protected IGameObject[] gameObjects;
	protected Direction direction;


	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean hasObjectOnTile() {
		if (gameObjects.length == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getClass() == obj.getClass();
	}

	@Override
	public SpriteType getSpriteType() {
		return spriteType;
	}

	@Override
	public IGameObject[] getGameObjects() {
		return gameObjects;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}
}
