package inf112.skeleton.app.GUI;

import inf112.skeleton.app.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BoardBuilder {
	private static int height;
	private static int width;

	/**
	 * Creates a 2D tile grid array from an input text file
	 *
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static HashMap<Position, Tile> buildBoard(String fileName)  {
		int[][] tileNumbers;
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
	 * Maps the tileNumber to the corresponding Tile
	 * TODO - Add the rest of the tiles
	 * @param tileNumber
	 * @return
	 */
	private static Tile findTile(int tileNumber) {
		switch (tileNumber){
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
	public static int[][] readFromFile(String filename) throws IOException {
		ClassLoader classLoader = BoardBuilder.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());

		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		//first two lines are the dimensions
		height = Integer.parseInt(bufferedReader.readLine());
		width = Integer.parseInt(bufferedReader.readLine());

		int[][] tileNumbers = new int[height][width];

		for (int y=0; y<height; y++){
			String line = bufferedReader.readLine();
			String[] numbers = line.split(" ");

			for (int x=0; x<width; x++){
				tileNumbers[y][x] = Integer.parseInt(numbers[x]);
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
