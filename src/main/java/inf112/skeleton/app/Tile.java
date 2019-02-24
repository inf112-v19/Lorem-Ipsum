package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ITile;

public abstract class Tile implements ITile {

	private final int DEFAULT_TILE_SPRITE_X = 4;
	private final int DEFAULT_TILE_SPRITE_Y = 0;

	@Override
	public int getSpriteX() {
		return DEFAULT_TILE_SPRITE_X;
	}

	@Override
	public int getSpriteY() {
		return DEFAULT_TILE_SPRITE_Y;
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
