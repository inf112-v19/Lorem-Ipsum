package inf112.skeleton.app.GameMechanics.Board;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class BoardBuilder {
	private int height;
	private int width;

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
	public HashMap<Position, Tile> buildBoard(String fileName)  {
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
	 * 
	 * @param tileNumber
	 * @return
	 */
	public Tile getTile(String tileNumber) {
		int tileType = Integer.parseInt(tileNumber.substring(0,1), 16);
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
			case 8: return new SpawnTile(gameObjects, tileDirection);
			case 9: return new LaserBaseTile(gameObjects, tileDirection);
			case 10: return new DoubleLaserBaseTile(gameObjects, tileDirection);
			default:
				System.err.println("Error reading tile type in BoardBuilder");
				return new NormalTile(gameObjects, tileDirection);
		}
	}


	/**
	 * Returns the GameObject corresponding to the int type and direction:
	 *
	 * @param type
	 * @param dir
	 * @return
	 */
	private GameObject getGameObject(int type, int dir){
		Direction direction = getDirection(dir);

		switch (type){
			case 0: return new Wall(direction);
			case 1: return new Laser(direction);
			case 2: return new Flag(direction, 0);
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
	private Direction getDirection(int dir){
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
	public String[][] readFromFile(String filename) throws IOException {
		//Previous method for loading resource - This gave error for Anna in Oblig3
		//ClassLoader classLoader = BoardBuilder.class.getClassLoader();
		//File file = new File(classLoader.getResource(filename).getFile());

		//New method for loading resource - impossible for me to test if this solution works for Anna since both
		//solutions work for all members of Lorem Ipsum
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(filename);
		InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		//first two lines are the dimensions
		height = Integer.parseInt(bufferedReader.readLine());
		width = Integer.parseInt(bufferedReader.readLine());

		String[][] tileNumbers = new String[height][width];

		for (int y=height-1; y>-1; y--){
			String line = bufferedReader.readLine();
			if (line != null){
				String[] numbers = line.split(" ");
				
				for (int x=0; x<width; x++){
					tileNumbers[y][x] = numbers[x];
				}
			}
			else {
				System.err.println("Specified wrong height parameter in board input file");
				bufferedReader.close();
				System.exit(1);
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
