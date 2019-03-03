package inf112.skeleton.app;

import inf112.skeleton.app.Board.BoardBuilder;
import inf112.skeleton.app.GameObjects.GameObject;
import inf112.skeleton.app.GameObjects.Laser;
import inf112.skeleton.app.Interfaces.IGameObject;
import inf112.skeleton.app.Tiles.NormalTile;
import inf112.skeleton.app.Tiles.Tile;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardTest {

	/**
	 * Testing that the readFromFile method in boardBuilder returns the correct 2D String array
	 *
	 * @throws IOException
	 */
	@Test
	public void boardBuilderReadFromFileTest() throws IOException {

		String[][] correctNumbers = {{"1","2","3","4","5"},{"6","7","8","9","10"},{"11","12","13","14","15"}};

		String testFile = "Boards/BoardBuilderTest1.txt";
		BoardBuilder bb = new BoardBuilder();

		String tileNumbers[][] = bb.readFromFile(testFile);

		assertArrayEquals(tileNumbers, correctNumbers);
	}

	/**
	 * Testing that the buildBoard method works as expected
	 */
	@Test
	public void boardBuilderBuildBoardTest() {
		HashMap<Position, Tile> correctTileMap = new HashMap<>();

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 5; x++) {
				Position curPos = new Position(x, y);
				Tile curTile = new NormalTile(null, Direction.NORTH);
				correctTileMap.put(curPos, curTile);
			}
		}

		String testFile = "Boards/BoardBuilderTest2.txt";
		BoardBuilder bb = new BoardBuilder();
		HashMap<Position, Tile> tileMapTest = bb.buildBoard(testFile);

		assertEquals(correctTileMap, tileMapTest);
	}


	/**
	 * Testing that the getTile works as expected
	 */
	@Test
	public void boardBuilderGetTileTest() {
		BoardBuilder bb = new BoardBuilder();
		GameObject[] gameObjects = {new Laser(Direction.NORTH)};
		Tile correctTile = new NormalTile(gameObjects, Direction.NORTH);
		Tile testTile = bb.getTile("00100");

		assertEquals(correctTile, testTile);
	}

}
