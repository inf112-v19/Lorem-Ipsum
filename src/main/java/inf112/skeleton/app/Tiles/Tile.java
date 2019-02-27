package inf112.skeleton.app.Tiles;

import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.Interfaces.ITile;
import inf112.skeleton.app.Position;

public abstract class Tile implements ITile {

	public SpriteType spriteType;

	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean objectOnTile() {
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getClass() == obj.getClass();
	}

	@Override
	public SpriteType getSpriteType() {
		return spriteType;
	}
}
