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

public class TileTest {

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
