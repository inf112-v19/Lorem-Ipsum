package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ITile;

public abstract class Tile implements ITile {

	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean objectOnTile() {
		return false;
	}
}
