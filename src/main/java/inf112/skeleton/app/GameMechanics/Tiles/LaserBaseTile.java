package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.Visuals.SpriteType;

public class LaserBaseTile extends Tile {

	public LaserBaseTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		this.addGameObject(new Wall(direction.oppositeDirection()));
		this.addGameObject(new Laser(direction));
		assignSpriteType();
	}

	public void toggleLaser (Position pos, Board board, boolean laserStatus){
		Position curPos = pos;
		Position nextPos = pos.getNeighbour(this.direction);
		Tile curTile = this;
		Tile nextTile = board.getTile(nextPos);

		while (true) {
			Player playerOnCurTile = board.posToPlayer(curPos);

			//if curTile has a player blocking the laser - decrease player health if adding lasers and break
			if (playerOnCurTile != null) {
				if (laserStatus) {
					System.out.println("Player: " + playerOnCurTile.getPlayerID() + " took laser damage");
					playerOnCurTile.decreaseHealth();
				}
				break;
			}

			//if curTile has wall blocking the laser - break)
			if (curTile.hasWallInDir(this.direction)){
				break;
			}
			//if nextTile is not null - proceed checking
			else if (nextTile != null) {
				//if the next tile has a wall blocking the laser - break
				if (nextTile.hasWallInDir(this.direction.oppositeDirection())) {
					break;
				}
				else {
					//either adds or removes a laser from the tile according to the laserStatus
					if (laserStatus) {
						nextTile.addGameObject(new Laser(this.direction));
					}
					else {
						nextTile.removeGameObject(new Laser(this.direction));
					}
				}
			}
			//nextTile is null (not on the board) - break
			else {
				break;
			}

			curPos = nextPos;
			nextPos = curPos.getNeighbour(this.direction);
			curTile = nextTile;
			nextTile = board.getTile(nextPos);
		}
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
