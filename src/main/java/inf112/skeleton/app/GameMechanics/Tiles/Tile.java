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
				player.decreaseHealth();
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
	 * Overlaying method for togglingLaser - used when the direction is corresponding to the tile direction
	 *
	 * @param pos
	 * @param board
	 * @param laserStatus
	 */
	public void toggleLaser (Position pos, Board board, boolean laserStatus){
		toggleLaser(pos, board, laserStatus, this.direction);
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
	 */
	public void toggleLaser (Position pos, Board board, boolean laserStatus, Direction dir){
		Position curPos = pos;
		Position nextPos = pos.getNeighbour(dir);
		Player playerOnCurTile = board.posToPlayer(curPos);

		//if curTile has a player blocking the laser - decrease player health if adding lasers and break
		if (playerOnCurTile != null) {
			if (laserStatus) {
				System.out.println("Player: " + playerOnCurTile.getPlayerID() + " took laser damage");
				playerOnCurTile.decreaseHealth();
			}
		}
		else {
			if (laserStatus) {
				this.addGameObject(new Laser(dir));
			}
			else {
				this.removeGameObject(new Laser(dir));
			}

			if (this.isPossibleToMoveDir(curPos, board, dir)) {
				toggleLaser(nextPos, board, laserStatus, dir);
			}
		}
	}


	/**
	 * Method that checks if it is possible to move from the current tile to the tile in the given direction
	 *
	 * @param pos
	 * @param board
	 * @param dir
	 * @return true if there are no walls blocking, or false if there are any walls blocking or if there is no tile in
	 * the given direction (position is outside the board)
	 */
	public boolean isPossibleToMoveDir(Position pos, Board board, Direction dir) {
		Tile nextTile = board.getTile(pos.getNeighbour(dir));

		//if curTile has wall blocking - return false
		if (this.hasWallInDir(dir)) {
			return false;
		}
		//if nextTile is not null - proceed checking
		else if (nextTile != null) {
			//possible to move if nextTile does not have a wall blocking
			return !nextTile.hasWallInDir(dir.oppositeDirection());
		}
		//nextTile is not on the board - return false
		else {
			return false;
		}
	}


}

