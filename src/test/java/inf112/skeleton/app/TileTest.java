package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Tiles.HoleTile;
import inf112.skeleton.app.GameMechanics.Tiles.NormalTile;
import inf112.skeleton.app.GameMechanics.Tiles.RepairTile;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import org.junit.Test;


import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TileTest {

	/**
	 * Testing that the hasGameObject works as expected when finding the object - should return 0(position of
	 * the GameObject)
	 */
	@Test
	public void hasGameObjectFoundTest(){
		Tile normalTile = new NormalTile(new GameObject[0], Direction.NORTH);
		GameObject wall = new Wall(Direction.NORTH);

		normalTile.addGameObject(wall);

		assertEquals(0,normalTile.hasGameObject(wall));
	}

	/**
	 * Testing that the hasGameObject works as expected when not finding the object - should return -1
	 */
	@Test
	public void hasGameObjectNotFoundTest(){
		Tile normalTile = new NormalTile(new GameObject[0], Direction.NORTH);
		normalTile.addGameObject(new Wall(Direction.NORTH));
		normalTile.addGameObject(new Wall(Direction.WEST));
		assertEquals(-1,normalTile.hasGameObject(new Wall(Direction.EAST)));
	}

	/**
	 * Testing that the hasWallinDir method works as expected for a tile with a wall North
	 */
	@Test
	public void hasWallInDirTrueTest() {
		Tile normalTile = new NormalTile(new GameObject[0], Direction.NORTH);
		normalTile.addGameObject(new Wall(Direction.NORTH));

		assertTrue(normalTile.hasWallInDir(Direction.NORTH));
	}

	/**
	 * Testing that the hasWallinDir method works as expected for a tile with no wall in the given direction
	 */
	@Test
	public void hasWallInDirFalseTest() {
		Tile normalTile = new NormalTile(new GameObject[0], Direction.NORTH);
		assertFalse(normalTile.hasWallInDir(Direction.NORTH));
	}
}
