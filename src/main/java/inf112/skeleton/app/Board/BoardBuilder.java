package inf112.skeleton.app.Board;

import inf112.skeleton.app.*;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.Tiles.HoleTile;
import inf112.skeleton.app.Tiles.NormalTile;
import inf112.skeleton.app.Tiles.RepairTile;
import inf112.skeleton.app.Tiles.Tile;

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
				Tile curTile = findTile(tileNumbers[y][x]);
				tileMap.put(curPos, curTile);

			}
		}

		return tileMap;
	}


	/**
	 * Maps the tileNumber to the corresponding Tile with the rotation or any related GameObjects
	 * TODO - Add the rest of the tiles and handle gameobjects
	 * @param tileNumber
	 * @return
	 */
	private static Tile findTile(String tileNumber) {
		int tileType = Integer.parseInt(tileNumber.substring(0,1));
		int rotation = Integer.parseInt(tileNumber.substring(2,3));
		int numberOfGO = Integer.parseInt(tileNumber.substring(3,4));
		HashMap<IGameObject, Integer> gameObjects = new HashMap<>();

		for (int i = 0; i < numberOfGO; i++) {

		}

		switch (tileType){
			case 0: return new NormalTile();
			case 1: return new HoleTile();
			case 2: return new RepairTile();
			default: return new NormalTile();
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

		return tileNumbers;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
