package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.Visuals.SpriteType;

public class LaserBaseTile extends Tile {

	public LaserBaseTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		this.addGameObject(new Wall(direction.oppositeDirection()));
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

	@Override
	public void toggleLaser(Position pos, Board board, boolean laserStatus) {
		int possibleToMoveDir = this.isPossibleToMoveDir(pos, board, this.direction);

		//skips adding laser to the base since its already drawn as part of the texture
		if (possibleToMoveDir == 0 || possibleToMoveDir == 2) {
			super.toggleLaser(pos, board, laserStatus);
		}
	}
}
