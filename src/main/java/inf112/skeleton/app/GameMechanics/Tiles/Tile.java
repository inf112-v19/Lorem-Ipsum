package inf112.skeleton.app.GameMechanics.Tiles;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GUI.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.Interfaces.ITile;
import inf112.skeleton.app.GameMechanics.Position;

public abstract class Tile implements ITile {

	public SpriteType spriteType;
	protected GameObject[] gameObjects;
	protected Direction direction;


	@Override
	public Position getPosition() {
		return null;
	}

	@Override
	public boolean hasAnyGameObjects() {
		if (gameObjects.length == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getClass() == obj.getClass();
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
		GameObject[] newGameObjects = new GameObject[gameObjects.length+1];

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

		GameObject[] newGameObjects = new GameObject[gameObjects.length-1];
		int j = 0;
		for (int i = 0; i < gameObjects.length; i++) {
			if (i == gameObjectIndex){
				continue;
			}else {
				newGameObjects[j] = gameObjects[i];
				j++;
			}
		}
		gameObjects = newGameObjects;
		return true;
	}

	@Override
	public int hasGameObject(GameObject gameObject){
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

}
