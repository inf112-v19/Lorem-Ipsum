package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Board.BoardBuilder;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.NormalTile;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BoardBuilderTest {

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
	 * Testing that the buildBoard method works as expected for a board containing only NormalTiles
	 */
	@Test
	public void boardBuilderBuildBoardTest() {
		HashMap<Position, Tile> correctTileMap = new HashMap<>();

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 5; x++) {
				Position curPos = new Position(x, y);
				Tile curTile = new NormalTile(new GameObject[0], Direction.NORTH);
				correctTileMap.put(curPos, curTile);
			}
		}

		String testFile = "Boards/BoardBuilderTest2.txt";
		BoardBuilder bb = new BoardBuilder();
		HashMap<Position, Tile> tileMapTest = bb.buildBoard(testFile);

		assertEquals(correctTileMap, tileMapTest);
	}


	/**
	 * Testing that the getTile works as expected for a NormalTile containing a Laser
	 */
	@Test
	public void boardBuilderGetTileTest() {
		BoardBuilder bb = new BoardBuilder();
		GameObject[] gameObjects = {new Laser(Direction.NORTH)};
		Tile correctTile = new NormalTile(gameObjects, Direction.NORTH);
		Tile testTile = bb.getTile("00110");

		assertEquals(correctTile, testTile);
	}
}
