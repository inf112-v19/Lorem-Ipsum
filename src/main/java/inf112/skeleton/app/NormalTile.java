package inf112.skeleton.app;

public class NormalTile extends Tile {

	private final int SPRITE_X = 4;
	private final int SPRITE_Y = 0;

	public NormalTile() {

	}

	@Override
	public int getSpriteX() {
		return SPRITE_X;
	}

	@Override
	public int getSpriteY() {
		return SPRITE_Y;
	}

	@Override
	public Position getPosition() {
		return super.getPosition();
	}

	@Override
	public boolean objectOnTile() {
		return super.objectOnTile();
	}
}
