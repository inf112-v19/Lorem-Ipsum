package inf112.skeleton.app.GUI;

import inf112.skeleton.app.Tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	public static Tile[][] buildBoard(String fileName) throws IOException {
		int[][] tileNumbers = readFromFile(fileName);
		Tile[][] tiles = new Tile[height][width];

		for (int i = 0; i < height; i++) {
			for (int x = 0; x < width; x++) {
				tiles[i][x] = findTile(tileNumbers[i][x]);
			}
		}

		return tiles;
	}


	// TODO - need to return the corresponding tile according to the tile number
	private static Tile findTile(int tileNumber) {
		return null;
	}


	/**
	 * Reads the file character by character and returns a 2D int Array containing the tile number of each position
	 *
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static int[][] readFromFile(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		//first two lines are the dimensions
		height = Integer.parseInt(bufferedReader.readLine());
		width = Integer.parseInt(bufferedReader.readLine());

		int[][] tileNumbers = new int[height][width];

		for (int i=0; i<height; i++){
			String line = bufferedReader.readLine();
			String[] numbers = line.split(" ");

			for (int x=0; x<width; x++){
				tileNumbers[i][x] = Integer.parseInt(numbers[x]);
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
