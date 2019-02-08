package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ITile;

public abstract class Tile implements ITile {


	@Override
	public int[][] getPosition() {
		return new int[0][0];
	}

	@Override
	public boolean objectOnTile() {
		return false;
	}

	@Override
	public Object getObjectOnTile() {
		return null;
	}

	@Override
	public boolean isHostile() {
		return false;
	}
}
