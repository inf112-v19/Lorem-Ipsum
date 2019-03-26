package inf112.skeleton.app.GameMechanics.Tiles;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.Interfaces.ITile;
import inf112.skeleton.app.GameMechanics.Position;

import java.util.Arrays;

public abstract class Tile implements ITile {

	public SpriteType spriteType;
	protected GameObject[] gameObjects;
	protected Direction direction;

	public Tile(GameObject[] gameObjects, Direction direction){
		super();
		this.gameObjects = gameObjects;
		this.direction = direction;
	}

	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean hasAnyGameObjects() {
		if (gameObjects.length == 0) {
			return false;
		}
		return true;
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
}

