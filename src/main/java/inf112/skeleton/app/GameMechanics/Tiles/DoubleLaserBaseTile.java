package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.Visuals.SpriteType;

public class DoubleLaserBaseTile extends LaserBaseTile {
	public DoubleLaserBaseTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		this.addGameObject(new Laser(direction));
		assignSpriteType();
	}

	/**
	 * Call the toggleLaser method twice since there there will be 2 lasers on each tile
	 *
	 * @param pos
	 * @param board
	 * @param laserStatus
	 */
	@Override
	public void toggleLaser(Position pos, Board board, boolean laserStatus) {
		super.toggleLaser(pos, board, laserStatus);
		super.toggleLaser(pos, board, laserStatus);
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
