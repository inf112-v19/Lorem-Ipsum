package inf112.skeleton.app.GameMechanics.Tiles;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.Interfaces.ITile;
import inf112.skeleton.app.GameMechanics.Position;

import java.util.Arrays;
import java.util.Objects;

public abstract class Tile extends Image implements ITile {

	public SpriteType spriteType;
	protected GameObject[] gameObjects;
	protected Direction direction;


	public Tile(GameObject[] gameObjects, Direction direction){
		this.gameObjects = gameObjects;
		this.direction = direction;
	}

	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean hasAnyGameObjects() {
		return gameObjects.length != 0;
	}

	/**
	 * Equals method checks that the objects are of the same type, has the same GameObjects and are the same direction
	 *
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;

		if (!obj.getClass().isInstance(this)) {
			return false;
		}

		Tile tile = (Tile) obj;
		if (!Arrays.equals(tile.getGameObjects(), this.gameObjects)) {
			return false;
		}
		return tile.getDirection().equals(this.direction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(direction, spriteType, gameObjects);
	}

	@Override
	public SpriteType getSpriteType() {
		return spriteType;
	}

	@Override
	public GameObject[] getGameObjects() {
		return gameObjects;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void addGameObject(GameObject gameObject) {
		GameObject[] newGameObjects = new GameObject[gameObjects.length + 1];

		for (int i = 0; i < gameObjects.length; i++) {
			newGameObjects[i] = gameObjects[i];
		}

		newGameObjects[gameObjects.length] = gameObject;
		gameObjects = newGameObjects;
	}

	@Override
	public boolean removeGameObject(GameObject gameObject) {
		int gameObjectIndex = hasGameObject(gameObject);
		if (gameObjectIndex == -1) {
			return false;
		}

		GameObject[] newGameObjects = new GameObject[gameObjects.length - 1];
		int j = 0;
		for (int i = 0; i < gameObjects.length; i++) {
			if (i == gameObjectIndex) {
				continue;
			} else {
				newGameObjects[j] = gameObjects[i];
				j++;
			}
		}
		gameObjects = newGameObjects;
		return true;
	}

	@Override
	public int hasGameObject(GameObject gameObject) {
		for (int i = 0; i < gameObjects.length; i++) {
			if (gameObjects[i].equals(gameObject)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean hasWallInDir(Direction dir) {
		Wall wall = new Wall(dir);
		return (hasGameObject(wall) != -1);
	}

	@Override
	public void checkTile(Board board, Player player) {
	}

	@Override
	public void laserCheck(Player player) {
		for (GameObject gameObject : gameObjects) {
			if (gameObject instanceof Laser) {
				player.increaseDamage();
			}
		}
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	@Override
	public boolean placeFlagOnTile(Flag flag) {
		if (hasGameObject(flag) == -1 && (this instanceof NormalTile)) {
			addGameObject(flag);
			return true;
		}
		return false;
	}

	/*
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for (GameObject gameObject: gameObjects) {
			gameObject.setZIndex(100);
			gameObject.draw(batch, parentAlpha);
		}
	}
	 */


	/**
	 * Overlaying method for togglingLaser - used when the direction is corresponding to the tile direction(LaseBase)
	 * Starts by checking if the current tile contains a player and handles potential damage if laserStatus is true,
	 * otherwise call underlying  toggleLaser method.
	 *
	 * @param pos
	 * @param board
	 * @param laserStatus
	 */
	public void toggleLaser (Position pos, Board board, boolean laserStatus, boolean doubleLaser){
		Player playerOnTile = board.posToPlayer(pos);
		if (playerOnTile != null && laserStatus) {
			System.out.println("Player: " + playerOnTile.getPlayerName() + " took laser damage");
			playerOnTile.increaseDamage();

			if (doubleLaser) {
				playerOnTile.increaseDamage();
			}
		}
		else{
			toggleLaser(pos, board, laserStatus, this.direction, doubleLaser);
		}
	}

	/**
	 * Method for toggling laser - either adds or removes a laser from the current tile and is called recursively as
	 * long as the laser is able to "move" in the given direction. Also deals damage if laser hits a player when adding
	 * laser.
	 *
	 * @param pos
	 * @param board
	 * @param laserStatus true if adding laser, or false if removing
	 * @param dir
	 * @param doubleLaser true if laser is from a DoubleLaserBase (also deals double the damage)
	 */
	public void toggleLaser (Position pos, Board board, boolean laserStatus, Direction dir, boolean doubleLaser){
		Position curPos = pos;
		Position nextPos = pos.getNeighbour(dir);

		//either adds or remove laser from the current tile
		if (laserStatus) {
			this.addGameObject(new Laser(dir, doubleLaser));
		}
		else {
			this.removeGameObject(new Laser(dir, doubleLaser));
		}

		switch (this.isPossibleToMoveDir(curPos, board, dir)) {
			//laser is able to proceed to next tile
			case 0:
				Tile nextTile = board.getTile(nextPos);
				nextTile.toggleLaser(nextPos, board, laserStatus, dir, doubleLaser);
				break;

			//hit player on next tile
			case 2:
				Player playerOnNextTile = board.posToPlayer(nextPos);

				if (laserStatus) {
					System.out.println("Player: " + playerOnNextTile.getPlayerName() + " took laser damage");
					playerOnNextTile.increaseDamage();

					if (doubleLaser) {
						playerOnNextTile.increaseDamage();
					}
				}
				break;
		}
	}


	/**
	 * Method that checks if it is possible to move from the current tile to the tile in the given direction
	 *
	 * @param pos
	 * @param board
	 * @param dir
	 * @return
	 * 		- 0 if it is possible to move in the given direction
	 * 		- 1 if there is a wall blocking either on the current tile or the tile in the given direction
	 * 		- 2 if the tile in the given direction contains a player
	 * 		- 3 if there is no tile in the given direction (outside the board)
	 */
	public int isPossibleToMoveDir(Position pos, Board board, Direction dir) {
		Position nextPos = pos.getNeighbour(dir);
		Tile nextTile = board.getTile(nextPos);

		//if curTile has wall blocking - return false
		if (this.hasWallInDir(dir)) {
			return 1;
		}
		//if nextTile is not null - proceed checking
		else if (nextTile != null) {
			//possible to move if nextTile does not have a wall blocking
			if (nextTile.hasWallInDir(dir.oppositeDirection())) {
				return 1;
			}
			else {
				Player otherPlayer = board.posToPlayer(nextPos);

				//player collision occurred
				if (otherPlayer != null){
					return 2;
				}

				//no player in direction trying to move - moves player to newPos
				else {
					return 0;
				}
			}
		}
		//nextTile is not on the board - return false
		else {
			return 3;
		}
	}


}

