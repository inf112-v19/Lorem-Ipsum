package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.Visuals.SpriteType;

public class DoubleLaserBaseTile extends LaserBaseTile {
	public DoubleLaserBaseTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		assignSpriteType();
	}

	private void assignSpriteType() {
		switch (this.direction) {
			case NORTH:
				super.spriteType = SpriteType.DOUBLELASERBASE_NORTH;
				break;
			case SOUTH:
				super.spriteType = SpriteType.DOUBLELASERBASE_SOUTH;
				break;
			case EAST:
				super.spriteType = SpriteType.DOUBLELASERBASE_EAST;
				break;
			case WEST:
				super.spriteType = SpriteType.DOUBLELASERBASE_WEST;
				break;
			default: //will never happen since direction is an enum
		}
	}
}
