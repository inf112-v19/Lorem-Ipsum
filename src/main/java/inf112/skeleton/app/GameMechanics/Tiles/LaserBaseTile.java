package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.Visuals.SpriteType;

public class LaserBaseTile extends Tile {

	public LaserBaseTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		this.addGameObject(new Wall(direction.oppositeDirection()));
		this.addGameObject(new Laser(direction));
		assignSpriteType();
	}

	private void assignSpriteType() {
		switch (this.direction) {
			case NORTH:
				super.spriteType = SpriteType.LASERBASE_NORTH;
				break;
			case SOUTH:
				super.spriteType = SpriteType.LASERBASE_SOUTH;
				break;
			case EAST:
				super.spriteType = SpriteType.LASERBASE_EAST;
				break;
			case WEST:
				super.spriteType = SpriteType.LASERBASE_WEST;
				break;
			default: //will never happen since direction is an enum
		}
	}
}
