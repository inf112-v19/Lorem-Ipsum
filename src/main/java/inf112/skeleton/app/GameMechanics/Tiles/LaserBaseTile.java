package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Player;
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

	/**
	 * Skips adding laser to the base tile since the laser is already drawn as part of the tile texture
	 *
	 * @param pos
	 * @param board
	 * @param laserStatus
	 */
	@Override
	public void toggleLaser(Position pos, Board board, boolean laserStatus, boolean doubleLaser) {
		Player playerOnTile = board.posToPlayer(pos);
		int possibleToMoveDir = this.isPossibleToMoveDir(pos, board, this.direction);

		if (playerOnTile != null) {
			if (laserStatus) {
				System.out.println("Player: " + playerOnTile.getPlayerID() + " took laser damage");
				playerOnTile.increaseDamage();

				if (doubleLaser) {
					playerOnTile.increaseDamage();
				}
			}
		}
		else if (possibleToMoveDir == 0) {
			Tile nextTile = board.getTile(pos.getNeighbour(this.direction));
			nextTile.toggleLaser(pos, board, laserStatus, this.direction, doubleLaser);
		}
		else if (possibleToMoveDir == 2) {
			if (laserStatus) {
				Player playerOnNextTile = board.posToPlayer(pos.getNeighbour(this.direction));
				System.out.println("Player: " + playerOnNextTile.getPlayerID() + " took laser damage");
				playerOnNextTile.increaseDamage();
			}
		}
	}
}
