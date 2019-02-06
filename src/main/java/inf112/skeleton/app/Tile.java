package inf112.skeleton.app;

public abstract class Tile implements iTile {


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
