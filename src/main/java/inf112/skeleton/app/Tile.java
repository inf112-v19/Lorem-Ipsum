package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ITile;

public abstract class Tile implements ITile {
	private int spriteX;
	private int spriteY;

	public int getSpriteX() {
		return spriteX;
	}

	public int getSpriteY() {
		return spriteY;
	}

	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean objectOnTile() {
		return false;
	}
}
