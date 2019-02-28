package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;


import inf112.skeleton.app.GameObjects.Laser;
import inf112.skeleton.app.GameObjects.Wall;
import inf112.skeleton.app.Tiles.HoleTile;
import inf112.skeleton.app.Tiles.NormalTile;
import inf112.skeleton.app.Tiles.RepairTile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestInit.class)
public class SpriteSheetTest {

	private SpriteSheet spriteSheet;

	@Before
	public void setUp() {
		spriteSheet = new SpriteSheet();
	}

	@Test
	public void initNormalTileTextureTest() {
		NormalTile normalTile = new NormalTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexure(normalTile.getSpriteType());
			assertTrue(textureRegion != null );
	}

	@Test
	public void initRepairTileTextureTest() {
		RepairTile repairTile = new RepairTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexure(repairTile.getSpriteType());
		assertTrue(textureRegion != null );
	}

	@Test
	public void initHoleTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexure(holeTile.getSpriteType());
		assertTrue(textureRegion != null );
	}

	@Test
	public void NormalTileTextureIsEqualToNormalTileTextureTest() {
		NormalTile nTile = new NormalTile(null, Direction.NORTH);
		TextureRegion tile1 = spriteSheet.getTexure(nTile.getSpriteType());
		TextureRegion tile2 = spriteSheet.getTexure(SpriteType.NORMAL_TILE);
		assertEquals(tile1,tile2);
	}

	@Test
	public void HoleTileIsNotNormalTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		NormalTile normalTile = new NormalTile(null, Direction.NORTH);
		TextureRegion holeTileText = spriteSheet.getTexure(holeTile.getSpriteType());
		TextureRegion normalTileText = spriteSheet.getTexure(normalTile.getSpriteType());
		assertNotEquals(holeTileText, normalTileText);
	}

	@Test
	public void laserTextureCorrectTest() {
		Laser laserNorth = new Laser(Direction.NORTH);
		TextureRegion laserTexNorth = spriteSheet.getTexure(laserNorth.getSpriteType());

		Laser laserSouth = new Laser(Direction.SOUTH);
		TextureRegion laserTexSouth = spriteSheet.getTexure(laserSouth.getSpriteType());

		Laser laserEast = new Laser(Direction.EAST);
		TextureRegion laserTexEast = spriteSheet.getTexure(laserEast.getSpriteType());

		Laser laserWest = new Laser(Direction.WEST);
		TextureRegion laserTexWest = spriteSheet.getTexure(laserWest.getSpriteType());

		boolean laserNorthIsVertical = laserTexNorth.equals(spriteSheet.getTexure(SpriteType.LASER_VERTICAL));
		boolean laserSouthIsVertical = laserTexSouth.equals(spriteSheet.getTexure(SpriteType.LASER_VERTICAL));
		boolean laserEastIsHorizontal = laserTexEast.equals(spriteSheet.getTexure(SpriteType.LASER_HORIZONTAL));
		boolean laserWestIsHorizontal = laserTexWest.equals(spriteSheet.getTexure(SpriteType.LASER_HORIZONTAL));

		assertTrue(laserNorthIsVertical && laserSouthIsVertical && laserEastIsHorizontal && laserWestIsHorizontal);
	}

	@Test
	public void WallTextureCorrectTest() {
		Wall wallNorth = new Wall(Direction.NORTH);
		TextureRegion WallTexNorth = spriteSheet.getTexure(wallNorth.getSpriteType());

		Wall wallSouth = new Wall(Direction.SOUTH);
		TextureRegion WallTexSouth = spriteSheet.getTexure(wallSouth.getSpriteType());

		Wall wallEast = new Wall(Direction.EAST);
		TextureRegion WallTexEast = spriteSheet.getTexure(wallEast.getSpriteType());

		Wall wallWest = new Wall(Direction.WEST);
		TextureRegion WallTexWest = spriteSheet.getTexure(wallWest.getSpriteType());

		boolean WallNorthIsVertical = WallTexNorth.equals(spriteSheet.getTexure(SpriteType.WALL_NORTH));
		boolean WallSouthIsVertical = WallTexSouth.equals(spriteSheet.getTexure(SpriteType.WALL_SOUTH));
		boolean WallEastIsHorizontal = WallTexEast.equals(spriteSheet.getTexure(SpriteType.WALL_EAST));
		boolean WallWestIsHorizontal = WallTexWest.equals(spriteSheet.getTexure(SpriteType.WALL_WEST));

		assertTrue(WallNorthIsVertical && WallSouthIsVertical && WallEastIsHorizontal && WallWestIsHorizontal);
	}






}