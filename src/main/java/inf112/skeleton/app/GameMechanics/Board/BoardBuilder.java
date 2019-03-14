package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BoardBuilder {
	private static int height;
	private static int width;

	/**
	 * Creates a hashmap of the board based on an input text file
	 * Format:
	 * H
	 * W
	 * TRNGD ... ... ...
	 * ...   ... ... ...
	 * ...   ... ... ...
	 *
	 * Where H is height, W is width, T is tile-type, R is tile-rotation, N is number of GameObjects on tile,
	 * G is the GameObject-type and D is the direction. The last two letters are repeated for the number of
	 * GameObjects on the tile.
	 *
	 *
	 *
	 * @param fileName
	 * @return Hashmap containing all possible positions with corresponding tiles
	 */
	public static HashMap<Position, Tile> buildBoard(String fileName)  {
		String[][] tileNumbers;
		try {
			tileNumbers = readFromFile(fileName);
		} catch (IOException e) {
			System.err.println("Error while reading file in BoardBuilder");
			e.printStackTrace();
			return null;
		}

		HashMap<Position, Tile> tileMap = new HashMap<>();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Position curPos = new Position(x, y);
				Tile curTile = getTile(tileNumbers[y][x]);
				tileMap.put(curPos, curTile);
			}
		}

		return tileMap;
	}


	/**
	 * Maps the tileNumber to the corresponding Tile with the rotation and any related GameObjects
	 * TODO - add the rest of the tiles
	 * @param tileNumber
	 * @return
	 */
	public static Tile getTile(String tileNumber) {
		int tileType = Integer.parseInt(tileNumber.substring(0,1));
		int tileRotation = Integer.parseInt(tileNumber.substring(1,2));
		int numberOfGO = Integer.parseInt(tileNumber.substring(2,3));
		GameObject[] gameObjects = new GameObject[numberOfGO];
		Direction tileDirection = getDirection(tileRotation);

		//builds array of Game Objects related to the tile
		int j = 0;
		for (int i = 0; i < numberOfGO*2; i+=2) {
			int gameObjectType = Integer.parseInt(tileNumber.substring(3+i,4+i));
			int gameObjectDir = Integer.parseInt(tileNumber.substring(4+i,5+i));
			gameObjects[j] = getGameObject(gameObjectType, gameObjectDir);
			j++;
		}


		switch (tileType){
			case 0: return new NormalTile(gameObjects, tileDirection);
			case 1: return new HoleTile(gameObjects, tileDirection);
			case 2: return new RepairTile(gameObjects, tileDirection);
			case 3: return new OptionsTile(gameObjects, tileDirection);
			case 4: return new ConveyorBeltTile(gameObjects, tileDirection);
			case 5: return new DoubleConveyorBeltTile(gameObjects, tileDirection);
			case 6: return new RotationLeftTile(gameObjects, tileDirection);
			case 7: return new RotationRightTile(gameObjects, tileDirection);
			default: return new NormalTile(gameObjects, tileDirection);
		}
	}


	/**
	 * Returns the GameObject corresponding to the int type and direction:
	 *
	 * @param type
	 * @param dir
	 * @return
	 */
	private static GameObject getGameObject(int type, int dir){
		Direction direction = getDirection(dir);

		switch (type){
			case 0: return new Wall(direction);
			case 1: return new Laser(direction);
			case 2: return new Flag(direction);
			default:
				System.err.println("Error while getting Game Object in BoardBuilder");
				System.exit(1);
				return null;
		}
	}


	/**
	 * Maps int to corresponding Direction
	 *
	 * @param dir
	 * @return
	 */
	private static Direction getDirection(int dir){
		switch (dir){
			case 0: return Direction.NORTH;
			case 1: return Direction.SOUTH;
			case 2: return Direction.EAST;
			case 3: return Direction.WEST;
			default: return Direction.NORTH;
		}
	}


	/**
	 * Reads the file character by character and returns a 2D int Array containing the tile number of each position
	 *
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String[][] readFromFile(String filename) throws IOException {
		ClassLoader classLoader = BoardBuilder.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		//first two lines are the dimensions
		height = Integer.parseInt(bufferedReader.readLine());
		width = Integer.parseInt(bufferedReader.readLine());

		String[][] tileNumbers = new String[height][width];

		for (int y=0; y<height; y++){
			String line = bufferedReader.readLine();
			String[] numbers = line.split(" ");

			for (int x=0; x<width; x++){
				tileNumbers[y][x] = numbers[x];
			}
		}
		bufferedReader.close();
		return tileNumbers;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
