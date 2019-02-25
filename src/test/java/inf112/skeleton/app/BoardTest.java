package inf112.skeleton.app;

import inf112.skeleton.app.Board.BoardBuilder;
import inf112.skeleton.app.Tiles.HoleTile;
import inf112.skeleton.app.Tiles.NormalTile;
import inf112.skeleton.app.Tiles.RepairTile;
import inf112.skeleton.app.Tiles.Tile;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardTest {

	/**
	 * Testing that the readFromFile method in boardBuilder returns the correct 2D int array
	 *
	 * @throws IOException
	 */
	@Test
	public void boardBuilderReadFromFileTest() throws IOException {

		int[][] correctNumbers = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};

		String testFile = "BoardBuilderTest1.txt";
		BoardBuilder bb = new BoardBuilder();

		int tileNumbers[][] = bb.readFromFile(testFile);

		assertArrayEquals(tileNumbers, correctNumbers);
	}

	/**
	 * Testing that the buildBoard method works as expected
	 */
	@Test
	public void boardBuilderBuildBoardTest() {
		int[][] correctNumbers = {{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1}};
		HashMap<Position, Tile> correctTileMap = new HashMap<>();

		for (int y = 0; y < correctNumbers.length; y++) {
			for (int x = 0; x < correctNumbers[0].length; x++) {
				Position curPos = new Position(x, y);
				Tile curTile;
				switch(correctNumbers[y][x]){
					case 0: curTile = new NormalTile();
						break;
					case 1: curTile = new HoleTile();
						break;
					case 2: curTile = new RepairTile();
						break;
					default: curTile = new NormalTile();
				}
				correctTileMap.put(curPos, curTile);
			}
		}

		String testFile = "BoardBuilderTest2.txt";
		BoardBuilder bb = new BoardBuilder();
		HashMap<Position, Tile> tileMapTest = bb.buildBoard(testFile);

		assertEquals(correctTileMap, tileMapTest);
	}

}
